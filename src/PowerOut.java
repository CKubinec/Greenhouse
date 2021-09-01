/**
 * Used to create a new PowerOut event to simulate problems a greenhouse may have. Sets
 * error code to 2 to let user know its a Window Malfunction error.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class PowerOut extends Event {

    /**
     * Instantiates a new Power out.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public PowerOut(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI and sets variables to reflect PowerOut.
     *
     * @throws Exception the exception
     */
    public void action() throws Exception {
        greenhouseControls.setVariable("ErrorCode", 2);
        greenhouseControls.setVariable("Power", "False");
        //System.out.println(toString());
        greenhouseControls.print(toString());
        throw new ControllerException(greenhouseControls);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "ERROR!! Power Out!";
    }

}
