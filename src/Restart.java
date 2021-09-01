import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The Restart Event. Creates the Restart Event.
 *
 * @author Craig Kubinec ID:3433193
 * @see Event
 */
public class Restart extends Event {

    private final String fileName;

    /**
     * Instantiates a new Restart.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     * @param delayTime          the delay time for when event should be started.
     * @param filename           the filename
     */
    public Restart(GreenhouseControls greenhouseControls, long delayTime, String filename) {
        super(greenhouseControls, delayTime);
        fileName = filename;
    }

    /**
     * Reads in the data from the filename and creates the Events associated with them.
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run() Thread#run()
     */
    @Override
    public void run() {

        try {
            Thread.sleep(delayTime);
            action();
            String strCurrentLine;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((strCurrentLine = br.readLine()) != null) {
                Event event;
                String[] split = strCurrentLine.split(",");
                String[] first = split[0].split("=");
                String[] second = split[1].split("=");
                if (split.length == 3) {
                    String[] third = split[2].split("=");
                    event = (Event) Class.forName(first[1]).getConstructor(GreenhouseControls.class, long.class, int.class).newInstance(greenhouseControls, Long.parseLong(second[1]), Integer.parseInt(third[1]));
                } else {
                    event = (Event) Class.forName(first[1]).getConstructor(GreenhouseControls.class, long.class).newInstance(greenhouseControls, Long.parseLong(second[1]));
                }
                Thread thread = new Thread(event, first[1]);
                thread.start();
                greenhouseControls.addThread(thread);
                greenhouseControls.addEvent(event);
            }
            br.close();
        } catch (Exception e) {
            greenhouseControls.print("IMPROPER EVENT FILE \n Please use proper file to boot system");
            greenhouseControls.addEvent(new Terminate(greenhouseControls, 0));
        }
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "Restarting system";
    }

    /**
     * prints the action to GUI.
     */
    @Override
    public void action() {
        greenhouseControls.print(toString());
        //System.out.println(toString());
    }
}
