import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The GreenhouseControls is the main Controller where all code is started once the user has selected an
 * eventFile and booted the system. The code in this class is triggered by the GUI that it is associated with.
 *
 *
 * @author Craig Kubinec ID:3433193
 * @see GUI
 */
public class GreenhouseControls extends Controller {

    private transient final GUI gui;
    private final GreenhouseControls GC;
    private List<TwoTuple<Object, Object>> variable = new ArrayList<>();
    private transient final List<Thread> threads = new ArrayList<>();
    private String filename;
    private boolean running = false;


    /**
     * Instantiates a new Greenhouse controls.
     *
     * @param gui the GUI that is associated with current instance of GreenhouseControls.
     */
    GreenhouseControls(GUI gui) {
        this.gui = gui;
        this.GC = this;
    }

    /**
     * Prints a message to the GUI
     *
     * @param message the message
     */
    void print(String message) {
        gui.getTxtArea().append(message + "\n");
    }

    /**
     * Returns if system is currently active or not.
     *
     * @return the boolean if system is currently running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the running param to false.
     */
    public void notRunning() {
        running = false;
    }

    /**
     * Clears all arrays
     */
    public void clearArrays() {
        threads.clear();
        variable.clear();
    }

    /**
     * Adds a thread to array.
     *
     * @param thread the thread
     */
    public void addThread(Thread thread) {
        threads.add(thread);
    }

    /**
     * Sets filename.
     *
     * @param name the name
     */
    public void setFilename(String name) {
        this.filename = name;
    }

    /**
     * Gets filename.
     *
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets variable.
     *
     * @param key   the key
     * @param value the value
     */
    public synchronized void setVariable(Object key, Object value) {
        variable.removeIf(tuple -> tuple.first == key);
        variable.add(new TwoTuple<>(key, value));
    }

    /**
     * Variable to string. Loops through variable array and prints to GUI.
     */
    public void variableToString() {
        //System.out.println("State of System:{");
        print("State of System:{");
        for (TwoTuple<Object, Object> tuple : variable) {
            //System.out.println(tuple.toString());
            print(tuple.toString());
        }
        print("}");
        System.out.println("}");
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        for (TwoTuple<Object, Object> tuple : variable) {
            if (tuple.first == "ErrorCode") {
                return tuple.second.toString();
            }
        }
        return null;
    }

    /**
     * Overridden shutdown() method to print the error onto the console and into a a new File called error.log
     * before the system is shutdown. Will also serialize and save the state of the system into a file called dump.out
     * which the user can use to reboot the system and fix errors.
     */
    @Override
    public void shutDown() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        try {
            FileWriter fr = new FileWriter("error.log");
            fr.write("Time:" + formatter.format(date) + ",ErrorCode:" + getErrorCode());
            //System.out.println("Time:" + formatter.format(date) + ",ErrorCode:" + getErrorCode());
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("dump.out");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(GC);
            out.writeObject(variable);
            out.writeObject(filename);
            out.writeObject(running);
            out.flush();
            fileOut.flush();
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.shutDown();
    }

    /**
     * Pause events.
     */
    public void pauseEvents() {
        getEventList().forEach(Event::pause);
    }

    /**
     * Resume events.
     */
    public void resumeEvents() {
        getEventList().forEach(Event::unPause);
    }

    /**
     * Gets fixable.
     *
     * @param errorCode the error code
     * @return the fixable
     */
//Method to create a new Fixable Object and run its methods and fix the system depending on what
    //the error of the system was.
    public Fixable getFixable(int errorCode) {
        if (errorCode == 1) {
            return new FixWindow(this).run();
        } else if (errorCode == 2) {
            return new PowerOn(this).run();
        }
        print("Error Code not valid");
        return null;
    }

    /**
     * Initializes the variable array with default values and Creates the Restart Event to read in the rest of
     * the events the user wants through the file that's selected.
     */
    public void boot() {
        running = true;
        try {
            variable.add(new TwoTuple<>("Lights", "Off"));
            variable.add(new TwoTuple<>("Water", "Off"));
            variable.add(new TwoTuple<>("Fans", "Off"));
            variable.add(new TwoTuple<>("WindowOk", "True"));
            variable.add(new TwoTuple<>("Power", "True"));
            variable.add(new TwoTuple<>("ErrorCode", "0"));
            variable.add(new TwoTuple<>("Thermostat", "Day"));
            variable.add(new TwoTuple<>("EventFile", filename));
            Event e = (Event) Class.forName("Restart").getConstructor(GreenhouseControls.class, long.class, String.class).newInstance(this, 0, filename);
            Thread thread = new Thread(e, "Restart");
            thread.start();
            threads.add(thread);

        } catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Invalid number of parameters");
            print("Invalid number of parameters");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The Restore class is the class responsible for making the system restart after a crash.
     * It will fill GreenhouseControls with the saved state of the
     * last run before it crashed and start the threads that never ran.
     * It will also remove any errors from the eventList Array.
     *
     * @author Craig Kubinec ID:3433193
     */
    public class Restore {
        private final String file;
        private GreenhouseControls greenhouseControls;

        /**
         * Instantiates a new Restore.
         *
         * @param fileName           the file name
         * @param greenhouseControls the current instance of greenhouseControls.
         */
        public Restore(String fileName, GreenhouseControls greenhouseControls) {
            this.file = fileName;
            this.greenhouseControls = greenhouseControls;
            reboot();
        }

        /**
         * The Method that reads in the serialized object file dump.out and restarts the system with its info after
         * removing all the error events in the eventsList array and correcting the system time to match with the new
         * system time at reboot.
         */

        public void reboot() {

            try {
                greenhouseControls.running = true;
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                GreenhouseControls oldGreenhouseControls = (GreenhouseControls) in.readObject();
                variable = (List<TwoTuple<Object, Object>>) in.readObject();
                filename = (String) in.readObject();
                running = (Boolean) in.readObject();
                variableToString();
                String errorCode = "";
                for (TwoTuple<Object, Object> tuple : variable) {
                    if (tuple.first.equals("ErrorCode")) {
                        errorCode = tuple.second.toString();
                    }
                }
                int error = Integer.parseInt(errorCode);
                getFixable(error);
                in.close();
                fileIn.close();
                oldGreenhouseControls.getEventList().removeIf(event -> event instanceof WindowMalfunction | event instanceof PowerOut);
                for (int i = 0; i < oldGreenhouseControls.getEventList().size(); i++) {
                    Event event = oldGreenhouseControls.getEventList().get(i);
                    event.greenhouseControls = greenhouseControls;
                    event.start();
                    Thread thread = new Thread(event);
                    thread.start();
                    threads.add(thread);
                }

            } catch (IOException e) {
                //System.out.println("File invalid, please use the dump.out file");
                print("File invalid, please use the dump.out file");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
} ///:~