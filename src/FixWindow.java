import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The type Fix window.
 *
 * @author Craig Kubinec ID:3433193
 */
public class FixWindow implements Fixable {

    GreenhouseControls greenhouseControls;

    /**
     * Instantiates a new Fix window.
     *
     * @param greenhouseControls the greenhouse controls
     */
    public FixWindow(GreenhouseControls greenhouseControls) {
        this.greenhouseControls = greenhouseControls;
    }

    /**
     * Sets the systems power on back to true and zeros out the error codes
     */
    @Override
    public void fix() {
        greenhouseControls.setVariable("WindowOk", "Yes");
        greenhouseControls.setVariable("ErrorCode", "0");
    }

    /**
     * Writes the fix to the screen and into a file called fix.log
     */
    @Override
    public void log() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        try {
            FileWriter fr = new FileWriter("fix.log");
            fr.write("Time:" + formatter.format(date) + ",ErrorCode:1" + " has been fixed");
            //System.out.println("Time:" + formatter.format(date) + ",ErrorCode:1" + " has been fixed");
            greenhouseControls.print("Time:" + formatter.format(date) + ",ErrorCode:1" + " has been fixed");
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Run fixable.
     *
     * @return the fixable
     */
    public Fixable run() {
        fix();
        log();
        return null;
    }
}
