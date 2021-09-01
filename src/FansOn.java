/**
 * The FanOn Event. Creates the FanOn Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class FansOn extends Event {

    /**
     * Instantiates a new Fans on.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public FansOn(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * Prints the action to GUI
     */
    public void action() {
        greenhouseControls.setVariable("Fans", "On");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Fan is on";
    }
}
