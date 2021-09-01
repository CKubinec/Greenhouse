/**
 * The LightOn Event. Creates the LightOn Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class LightOn extends Event {

    /**
     * Instantiates a new Light on.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public LightOn(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.setVariable("Lights", "On");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Light is on";
    }

}
