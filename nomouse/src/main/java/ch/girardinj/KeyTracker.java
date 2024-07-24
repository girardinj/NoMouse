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

                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_Y)
                    mouseController.LeftClickPress();
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_X)
                    mouseController.RightClickPress();
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_S)
                    mouseController.MiddleClickPress();
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_UP)
                    mouseController.SetUpArrow(true);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_DOWN)
                    mouseController.SetDownArrow(true);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_LEFT)
                    mouseController.SetLeftArrow(true);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_RIGHT)
                    mouseController.SetRightArrow(true);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_CONTROL)
                    mouseController.setSpeedMultiplier(10);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_MENU)
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
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_Y)
                    mouseController.LeftClickRelease();
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_X)
                    mouseController.RightClickRelease();
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_S)
                    mouseController.MiddleClickRelease();
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_UP)
                    mouseController.SetUpArrow(false);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_DOWN)
                    mouseController.SetDownArrow(false);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_LEFT)
                    mouseController.SetLeftArrow(false);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_RIGHT)
                    mouseController.SetRightArrow(false);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_CONTROL)
                    mouseController.setSpeedMultiplier(5);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_MENU)
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
