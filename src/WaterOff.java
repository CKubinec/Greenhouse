/**
 * The WaterOff Event. Creates the WaterOff Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class WaterOff extends Event {

    /**
     * Instantiates a new Water off.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public WaterOff(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.setVariable("Water", "Off");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Greenhouse water is off";
    }
}
