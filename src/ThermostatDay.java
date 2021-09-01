/**
 * The ThermostatDay Event. Creates the ThermostatDay Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class ThermostatDay extends Event {

    /**
     * Instantiates a new Thermostat day.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public ThermostatDay(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.setVariable("Thermostat", "Day");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Thermostat on day setting";
    }

}
