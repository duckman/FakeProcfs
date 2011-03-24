package fakeprocfs;

import java.io.File;

/**
 *
 * @author duckman
 */
public class FakeProcfs
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        File proc = new File("/proc");
        
        if(!proc.exists())
        {
            System.out.println("/proc doesn't exist, so creating it");
            proc.mkdir();
        }
        
        if(proc.isDirectory())
        {
            System.out.println("Starting to populate /proc");
            new Thread(new ProcThread(proc)).start();
        }
        else
        {
            System.err.println("/proc is not a directory");
        }
    }
}
