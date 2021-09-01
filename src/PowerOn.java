import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The PowerOn fixes an issue in the Greenhouse if the power goes out.
 *
 * @author Craig Kubinec ID:3433193
 * @see Fixable
 */
public class PowerOn implements Fixable {

    GreenhouseControls greenhouseControls;

    /**
     * Instantiates a new Power on.
     *
     * @param greenhouseControls the greenhouse controls instance that are being used for current GUI.
     */
    public PowerOn(GreenhouseControls greenhouseControls) {
        this.greenhouseControls = greenhouseControls;
    }

    /**
     * Sets the systems power on back to true and zeros out the error codes.
     */

    @Override
    public void fix() {
        greenhouseControls.setVariable("Power", "True");
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
            fr.write("Time:" + formatter.format(date) + ",ErrorCode:2" + " has been fixed");
            //System.out.println("Time:" + formatter.format(date) + ",ErrorCode:2" + " has been fixed");
            greenhouseControls.print("Time:" + formatter.format(date) + ",ErrorCode:2" + " has been fixed");
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
