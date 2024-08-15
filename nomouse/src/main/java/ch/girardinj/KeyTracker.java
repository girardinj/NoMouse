package ch.girardinj;

import java.awt.AWTException;

import java.util.ArrayList;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class KeyTracker {
    
    private ArrayList<KeyTrackerActivatedListener> activatedListenerList;
    final MouseController mouseController = new MouseController();

    public KeyTracker() throws AWTException {

        activatedListenerList = new ArrayList();
             
        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
		
			@Override 
			public void keyPressed(GlobalKeyEvent event) {

                // change to a switch case
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_Y)
                    mouseController.LeftClickPress();
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_X)
                    mouseController.RightClickPress();
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_C)
                    mouseController.MiddleClickPress();
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_UP || event.getVirtualKeyCode() == GlobalKeyEvent.VK_W)
                    mouseController.SetUpArrow(true);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_DOWN || event.getVirtualKeyCode() == GlobalKeyEvent.VK_S)
                    mouseController.SetDownArrow(true);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_LEFT || event.getVirtualKeyCode() == GlobalKeyEvent.VK_A)
                    mouseController.SetLeftArrow(true);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_RIGHT || event.getVirtualKeyCode() == GlobalKeyEvent.VK_D)
                    mouseController.SetRightArrow(true);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_SHIFT)
                    mouseController.setSpeedMultiplier(10);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_CONTROL)
                    mouseController.setSpeedMultiplier(1);
                    
			}
			
			@Override 
			public void keyReleased(GlobalKeyEvent event) {
			
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_T) {
                    if (mouseController.isRunning())
                        mouseController.stop();
                    else
                        mouseController.start();
                    
                    for (KeyTrackerActivatedListener listener : activatedListenerList) {
                        listener.activated(mouseController.isRunning());
                    }
                }
                // change to a switch case
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_Y)
                    mouseController.LeftClickRelease();
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_X)
                    mouseController.RightClickRelease();
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_C)
                    mouseController.MiddleClickRelease();
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_UP || event.getVirtualKeyCode() == GlobalKeyEvent.VK_W)
                    mouseController.SetUpArrow(false);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_DOWN || event.getVirtualKeyCode() == GlobalKeyEvent.VK_S)
                    mouseController.SetDownArrow(false);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_LEFT || event.getVirtualKeyCode() == GlobalKeyEvent.VK_A)
                    mouseController.SetLeftArrow(false);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_RIGHT || event.getVirtualKeyCode() == GlobalKeyEvent.VK_D)
                    mouseController.SetRightArrow(false);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_SHIFT)
                    mouseController.setSpeedMultiplier(5);
                else if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_CONTROL)
                    mouseController.setSpeedMultiplier(5);
            }
        });

    }

    public void start() {
        mouseController.start();
        for (KeyTrackerActivatedListener listener : activatedListenerList) {
            listener.activated(mouseController.isRunning());
        }
    }

    public void stop() {
        mouseController.stop();
    }

    public void addKeyListener(KeyTrackerActivatedListener listener) {
        activatedListenerList.add(listener);
    }

    public void removeKeyListener(KeyTrackerActivatedListener listener) {
        activatedListenerList.remove(listener);
    }

}
