package ch.girardinj;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.atomic.AtomicBoolean;


public class MouseController {
    
    private Thread mouseThread;
    private Runnable mouseRunnable;
    private AtomicBoolean isRunning;

    private Robot robot;

    private int upArrowPressed;
    private final Object upLock = new Object();
    private int downArrowPressed;
    private final Object downLock = new Object();
    private int leftArrowPressed;
    private final Object leftLock = new Object();
    private int rightArrowPressed;
    private final Object rightLock = new Object();
    private int speedMultiplier;
    private final Object speedMultiplierLock = new Object();
    
    private final int WAIT = 10;

    public MouseController() throws AWTException {
        this.upArrowPressed = 0;
        this.downArrowPressed = 0;
        this.leftArrowPressed = 0;
        this.rightArrowPressed = 0;
        this.speedMultiplier = 5;


        robot = new Robot();

        mouseRunnable = () -> {

            while(isRunning.get())
            {
                Point actualMousePosition = MouseInfo.getPointerInfo().getLocation();

                
                int xAxis = getLeftArrow() + getRightArrow();
                int yAxis = getUpArrow() + getDownArrow();

                //Vector2D vector = new Vector2D(xAxis, yAxis).normalize();
                Vector2D vector = new Vector2D(xAxis, yAxis);
                vector.mult(getSpeedMultiplier());

                vector.add(actualMousePosition.getX(), actualMousePosition.getY());
                
                int x = (int)Math.round(vector.getX());
                int y = (int)Math.round(vector.getY());
                
                robot.mouseMove(x, y);

            
                try {
                    Thread.sleep(WAIT);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }   
            }
        };

        isRunning = new AtomicBoolean(false);
    }

    public void start() {
        mouseThread = new Thread(mouseRunnable);
        mouseThread.setDaemon(true);
        isRunning.set(true);
        mouseThread.start();
    }

    public void stop() {
        //mouseThread.interrupt();
        isRunning.set(false);
    }

    public void LeftClickPress() {
        if (isRunning())
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void LeftClickRelease() {
        if (isRunning())
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void RightClickPress() {
        if (isRunning())
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void RightClickRelease() {
        if (isRunning())
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void middleClickPress() {
        if (isRunning())
            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
    }

    public void middleClickRelease() {
        if (isRunning())
            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }

    public boolean isRunning() {
        return isRunning.get();
    }

    public void SetUpArrow(boolean isPressed) {
        synchronized(upLock) {
            this.upArrowPressed = isPressed ? -1 : 0;
        }
    }

    public void SetDownArrow(boolean isPressed) {
        synchronized(downLock) {
            this.downArrowPressed = isPressed ? 1 : 0;
        }
    }
    
    public void SetLeftArrow(boolean isPressed) {
        synchronized(leftLock) {
            this.leftArrowPressed = isPressed ? -1 : 0;
        }
    }
    
    public void SetRightArrow(boolean isPressed) {
        synchronized(rightLock) {
            this.rightArrowPressed = isPressed ? 1 : 0;
        }
    }

    public void setSpeedMultiplier(int speedMultiplier) {
        synchronized(speedMultiplierLock) {
            this.speedMultiplier = speedMultiplier;
        }
    }

    private int getUpArrow() {
        synchronized(upLock) {
            return this.upArrowPressed;
        }
    }

    private int getDownArrow() {
        synchronized(downLock) {
            return this.downArrowPressed;
        }
    }

    private int getLeftArrow() {
        synchronized(leftLock) {
            return this.leftArrowPressed;
        }
    }

    private int getRightArrow() {
        synchronized(rightLock) {
            return this.rightArrowPressed;
        }
    }

    private int getSpeedMultiplier() {
        synchronized(speedMultiplierLock) {
            return this.speedMultiplier;
        }
    }

    
}
