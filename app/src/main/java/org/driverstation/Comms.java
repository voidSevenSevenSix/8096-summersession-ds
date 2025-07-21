package org.driverstation;

import java.io.PrintWriter;
import java.net.Socket;

public class Comms {
    private Socket socket;
    private PrintWriter out;
    private boolean success = true;

    public Comms(){
        try{
            socket = new Socket("raspberrypi", 5800);
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch(Exception e){
            System.out.println("[Comms.java] Initializing threw an exception: " + e);
            success = false;
        }
    }

    public boolean connected(){
        return success;
    }

    public void sendComms(String s){
        out.println(s);
    }
}
