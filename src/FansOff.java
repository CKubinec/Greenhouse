/**
 * The FanOff Event. Creates the FanOff Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class FansOff extends Event {

    /**
     * Instantiates a new Fans off.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public FansOff(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.setVariable("Fans", "Off");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Fan is off";
    }
}
