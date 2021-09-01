/**
 * The WaterOn Event. Creates the WaterOn Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class WaterOn extends Event {

    /**
     * Instantiates a new Water on.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public WaterOn(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.setVariable("Water", "On");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Greenhouse water is on";
    }
}
