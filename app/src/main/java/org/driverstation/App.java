package org.driverstation;

import java.util.Optional;

import com.google.gson.Gson;

public class App {
    public static Comms comms;
    public static Controller controller = new Controller(0);
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        System.out.println("[DS]: Starting Driverstation");
        comms = new Comms();
        if(!comms.connected()){
            return;
        }
        System.out.println("[DS]: Comms connected.");
        while(true){
            Optional<ControllerState> stateOptional = controller.getState();
            if(stateOptional.isPresent()){
                ControllerState state = stateOptional.get();
                comms.sendComms(gson.toJson(state));
                if(state.start){
                    System.out.println("[DS]: Exiting.");
                    break;
                }
            }
            else{
                break;
            }
            try{
                Thread.sleep(50);
            }
            catch(Exception e){
                System.out.println("[DS]: Failed sleeping, crashing...");
                return;
            }
        }

    }
}
