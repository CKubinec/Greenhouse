import java.io.Serializable;

/**
 * The Main Class for GreenhouseControls to create an Event for the Greenhouse to run.
 *
 * @author Craig Kubinec ID:3433193
 * @see GreenhouseControls
 */
public abstract class Event implements Serializable, Runnable {

    protected GreenhouseControls greenhouseControls;
    private long eventTime;
    protected long delayTime;
    private boolean running;
    private boolean pause;
    private long pauseTime;

    /**
     * Instantiates a new Event.
     *
     * @param greenhouseControls the current instance of greenhouseControls.
     * @param delayTime          the delay time for the event to run at.
     */
    public Event(GreenhouseControls greenhouseControls, long delayTime) {
        this.greenhouseControls = greenhouseControls;
        this.delayTime = delayTime;
        this.running = true;
        this.pause = false;
        this.pauseTime = 0;
        start();
    }

    /**
     * Start. Sets the time the Event will run at.
     */
    public void start() {
        eventTime = System.currentTimeMillis() + delayTime;
    }

    /**
     * Restarting. Sets the time the Event will run at after pausing the Thread.
     */
    public void restarting() {
        eventTime = System.currentTimeMillis() + pauseTime;
    }

    /**
     * Allows the System to know when to run the Event when its time is Greater than current time.
     *
     * @return true if Event time is greater than the current time
     */
    public boolean ready() {
        return System.currentTimeMillis() >= eventTime;
    }

    /**
     * Pauses the Event.
     */
    public void pause() {
        pause = true;
    }

    /**
     * Resumes the event.
     */
    public void unPause() {
        synchronized (this) {
            pause = false;
            restarting();
            notify();
        }
    }

    /**
     * Stops the Event.
     */
    public void stop() {
        running = false;
    }

    /**
     * Calls Events action method.
     *
     * @throws Exception the Exception.
     */
    public abstract void action() throws Exception;

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run() Thread#run()
     */
    @Override
    public void run() {
        while (running) {
            if (ready()) {
                try {
                    action();
                    running = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pause) {
                synchronized (this) {
                    try {
                        pauseTime = eventTime - System.currentTimeMillis();
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
///:~
