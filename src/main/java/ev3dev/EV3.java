package ev3dev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.AccessControlException;

public class EV3 {
	
	public static final String SYSTEM_CLASS_PATH = "/sys/class/";
	
	/***
	 * Reads the variable of the class specified.
	 * @param class_name The class name
	 * @param variable
	 * @return The variable of the class.
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's variable
	 * @throws AccessControlException If you are trying to write to a read-only variable, read from a write-only variable
	 */
	public static String read(String class_name, String variable) throws FileNotFoundException, IOException, AccessControlException{
		File file = new File(SYSTEM_CLASS_PATH + class_name + "/" + variable);
		class_name = class_name.toLowerCase();
		variable = variable.toLowerCase();
		FileInputStream in = new FileInputStream(file);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return sb.toString();
	}
	
	public static void write(String class_name, String variable, String new_value) throws FileNotFoundException, AccessControlException{
		PrintWriter out = new PrintWriter(SYSTEM_CLASS_PATH + class_name + "/" + variable);
		class_name = class_name.toLowerCase();
		variable = variable.toLowerCase();
		new_value = new_value.toLowerCase();
		out.write(new_value);
		out.flush();
		out.close();
	}
}
