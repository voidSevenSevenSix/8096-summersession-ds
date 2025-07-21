package org.driverstation;

import java.util.Optional;

import com.studiohartman.jamepad.ControllerAxis;
import com.studiohartman.jamepad.ControllerButton;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;

public class Controller {
    ControllerManager controllers;
    ControllerIndex currentController;

    public Controller(int index){
        controllers = new ControllerManager();
        controllers.initSDLGamepad();
        currentController = controllers.getControllerIndex(index);
    }

    public Optional<ControllerState> getState(){
        controllers.update();
        try{
            ControllerState state = new ControllerState(
                (currentController.getAxisState(ControllerAxis.TRIGGERLEFT)>0),
                (currentController.getAxisState(ControllerAxis.TRIGGERRIGHT)>0),
                currentController.isButtonJustPressed(ControllerButton.LEFTBUMPER),
                currentController.isButtonJustPressed(ControllerButton.RIGHTBUMPER),
                currentController.isButtonJustPressed(ControllerButton.A),
                currentController.isButtonJustPressed(ControllerButton.B),
                currentController.isButtonJustPressed(ControllerButton.X),
                currentController.isButtonJustPressed(ControllerButton.Y),
                currentController.isButtonJustPressed(ControllerButton.DPAD_UP),
                currentController.isButtonJustPressed(ControllerButton.DPAD_DOWN),
                currentController.isButtonJustPressed(ControllerButton.DPAD_LEFT),
                currentController.isButtonJustPressed(ControllerButton.DPAD_RIGHT),
                currentController.isButtonJustPressed(ControllerButton.START),
                currentController.getAxisState(ControllerAxis.LEFTX),
                currentController.getAxisState(ControllerAxis.LEFTY),
                currentController.getAxisState(ControllerAxis.RIGHTX),
                currentController.getAxisState(ControllerAxis.RIGHTY)
            );
            return Optional.of(state);
        }
        catch(Exception e){
            System.out.println("[Controller.java]: Threw an exception: " + e);
            return Optional.empty();
        }
    }
}
