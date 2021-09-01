/**
 * The Controller exception throws Exception if problem with GreenhouseControls.
 *
 * @author Craig Kubinec ID:3433193
 * @see GreenhouseControls
 */
public class ControllerException extends Exception {

    /**
     * Instantiates a new Controller exception. Calls shutDown() in current GreenhouseControls.
     *
     * @param greenhouseControls the current greenhouse controls instance.
     */
    public ControllerException(GreenhouseControls greenhouseControls) {
        greenhouseControls.shutDown();
    }
}
