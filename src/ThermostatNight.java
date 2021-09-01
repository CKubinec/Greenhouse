/**
 * The ThermostatNight Event. Creates the ThermostatNight Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class ThermostatNight extends Event {

    /**
     * Instantiates a new Thermostat night.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     */
    public ThermostatNight(GreenhouseControls greenhouseControls, long delayTime) {
        super(greenhouseControls, delayTime);
    }

    /**
     * prints the action to GUI.
     */
    public void action() {
        greenhouseControls.setVariable("Thermostat", "Night");
        //System.out.println(toString());
        greenhouseControls.print(toString());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Thermostat on night setting";
    }
}
