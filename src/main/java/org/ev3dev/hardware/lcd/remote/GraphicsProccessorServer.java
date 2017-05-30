package org.ev3dev.hardware.lcd.remote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GraphicsProccessorServer extends Thread{

    public static final int DEFAULT_MAX_THREADS = 50;
    
    public static final int DEFAULT_PORT = 6718;
    
    private final int port;
    
    private final int maxThreads;
    
    private boolean running = false;
    
    public GraphicsProccessorServer(){
        this(DEFAULT_PORT);
    }
    
    public GraphicsProccessorServer(int port){
        this(port, DEFAULT_MAX_THREADS);
    }
    
    public GraphicsProccessorServer(int port, int maxThreads){
        this.port = port;
        this.maxThreads = maxThreads;
    }
    
    @Override
    public void run(){
        if (!running){
            running = true;
            
            try {
                ServerSocket srvSock = new ServerSocket(port);
                Socket socket;
                System.out.println("Server started. Waiting for new connection...");
                while(running){
                    socket = srvSock.accept();
                    System.out.println("Connection incoming: " + socket.getInetAddress().getHostAddress() + " starting socket thread");
                    ServerSocketHandler handler = new ServerSocketHandler(socket);
                    handler.start();
                }
                srvSock.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            running = false;
        }
    }

    public int getPort() {
        return port;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public boolean isRunning() {
        return running;
    }
}
