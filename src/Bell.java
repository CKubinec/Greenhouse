/**
 * The Bell Event. Creates the bell Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class Bell extends Event {
    private int rings;

    /**
     * Instantiates a new Bell.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     * @param rings              the amount of rings that the bell should ring.
     */
    public Bell(GreenhouseControls greenhouseControls, long delayTime, int rings) {
        super(greenhouseControls, delayTime);
        this.rings = rings;
    }

    /**
     * Instantiates a new Bell.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public Bell(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
        this.rings = 1;
    }

    /**
     * To string string.
     *
     * @return the string.
     */
    public String toString() {
        return "Bing!";
    }

    /**
     * prints the action to GUI.
     *
     * @throws Exception Interruption of the Thread.
     */
    @Override
    public void action() throws Exception {
        //System.out.println(toString());
        greenhouseControls.print(toString());
        rings--;
        while (rings > 0) {
            Thread.sleep(2000);
            //System.out.println(toString());
            greenhouseControls.print(toString());
            rings--;
        }
    }
}
