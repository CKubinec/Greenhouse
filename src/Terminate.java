/**
 * The Terminate Event. Creates the Terminate Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class Terminate extends Event {

    /**
     * Instantiates a new Terminate.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public Terminate(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.getEventList().forEach(Event::stop);
        //System.out.println(toString());
        greenhouseControls.print(toString());
        greenhouseControls.notRunning();
        greenhouseControls.clearArrays();
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Terminating";
    }

}
