/**
 * The LightOff Event. Creates the LightOff Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class LightOff extends Event {

    /**
     * Instantiates a new Light off.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public LightOff(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.setVariable("Lights", "Off");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Light is off";
    }

}
