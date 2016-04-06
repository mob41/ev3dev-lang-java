package ev3dev.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;

/***
 * A class for reading/writing to the EV3 driver system classes
 * @author Anthony
 *
 */
public class Sysclass {
	
	public static File[] getAllSubClass(String class_name){
		File file = new File(Def.SYSTEM_CLASS_PATH + class_name);
		File[] files = file.listFiles();
		return files;
	}
	
	/***
	 * Reads the property of the class specified.
	 * @param class_name The class name
	 * @param property The property name of the class.
	 * @return The value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public static String getAttribute(String class_name, String property) throws FileNotFoundException, IOException, AccessControlException{
		File file = new File(Def.SYSTEM_CLASS_PATH + class_name + "/" + property);
		class_name = class_name.toLowerCase();
		property = property.toLowerCase();
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
	
	/***
	 * Reads the property of the class & subclass specified.
	 * @param class_name The class name.
	 * @param subclass The Sub-class name.
	 * @param property The property name of the class
	 * @return The value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public static String getAttribute(String class_name, String subclass, String property) throws FileNotFoundException, IOException, AccessControlException{
		return getAttribute(class_name, subclass + "/" + property);
	}
	
	/***
	 * Writes the property of the class & subclass specified.
	 * @param class_name The class name.
	 * @param subclass The Sub-class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public static void setAttribute(String class_name, String subclass, String property, String new_value) throws FileNotFoundException, AccessControlException{
		setAttribute(class_name, subclass + "/" + property, new_value);
	}
	
	/***
	 * Writes the property of the class specified.
	 * @param class_name The class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public static void setAttribute(String class_name, String property, String new_value) throws FileNotFoundException, AccessControlException{
		PrintWriter out = new PrintWriter(Def.SYSTEM_CLASS_PATH + class_name + "/" + property);
		class_name = class_name.toLowerCase();
		property = property.toLowerCase();
		new_value = new_value.toLowerCase();
		out.write(new_value);
		out.flush();
		out.close();
	}
	
	/***
	 * A function to separate space from a spaced-array.
	 * @param space_array A string
	 * @return A array of the string/space-array
	 */
	public static String[] separateSpace(String space_array){
		int i;
		int j;
		String sep;
		List<String> list = new ArrayList<String>(50);
		for (i = 0; i < space_array.length(); i++){
			for (j = i; j < space_array.length(); j++){
				if (space_array.charAt(j) == ' '){
					break;
				}
			}
			if (j == space_array.length()){
				break;
			}
			sep = space_array.substring(i, j + 1);
			list.add(sep);
			i = j;
		}
		Object[] objarr = list.toArray();
		String[] strarr = new String[objarr.length];
		for (i = 0; i < strarr.length; i++){
			strarr[i] = (String) objarr[i];
		}
		return strarr;
	}
	
	private static String readFile(File file) throws IOException{
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
	
	public static String getHardwareName(String classname, String subclassname, String address){
		File[] sub = Sysclass.getAllSubClass(classname);
		File file;
		for (File asub : sub){
			file = new File(asub.getAbsolutePath() + "/address");
			try {
				if(readFile(file).equals(address)){
					return asub.getName();
				}
			} catch (Exception ignore){}
		}
		return null;
	}
}
