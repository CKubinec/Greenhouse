import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The Controller stores Events and is used as a way to exit system.
 *
 * @author Craig Kubinec ID:3433193
 */
public class Controller implements Serializable {
    // A class from java.util to hold Event objects:
    private final List<Event> eventList = new ArrayList<Event>();

    /**
     * Adds Event to ArrayList.
     *
     * @param c the Event being added to eventList.
     */
    public void addEvent(Event c) {
        eventList.add(c);
    }

    /**
     * Gets event list.
     *
     * @return the event list
     */
//Method to return the eventsList array.
    public List<Event> getEventList() {
        return eventList;
    }

    /**
     * Shut down.
     */
//Method to exit the system
    public void shutDown() {
        System.exit(0);
    }


} ///:~