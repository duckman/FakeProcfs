package fakeprocfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duckman
 */
public class ProcThread implements Runnable
{
    private File proc;
    private boolean go;
    private Random rand;
    private long[][] stat;
    
    public ProcThread(File proc)
    {
        this.proc = proc;
        rand = new Random();
        stat = new long[4][10];
        for(int cpu=0;cpu<stat.length;cpu++)
        {
            for(int time=0;time<stat[cpu].length;time++)
            {
                stat[cpu][time] = 0;
            }
        }
    }
    
    @Override
    public void run()
    {
        go = true;
        
        while(go)
        {
            stat();
            meminfo();
            
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(ProcThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop()
    {
        go = false;
    }
    
    public void stat()
    {
        File fstat = new File("/proc/stat");
        try
        {
            PrintStream out = new PrintStream(fstat);
            
            for(int cpu=0;cpu<stat.length;cpu++)
            {
                long count = 0;
                for(int time=0;time<3;time++)
                {
                    long add = rand.nextInt(333);
                    stat[cpu][time] += add;
                    count += add;
                }
                stat[cpu][3] += 1000-count;
            }
            
            out.print("cpu ");
            for(int time=0;time<stat[0].length;time++)
            {
                int count = 0;
                for(int cpu=0;cpu<stat.length;cpu++)
                {
                    count += stat[cpu][time];
                }
                out.print(" "+count);
            }
            out.println();
            
            for(int cpu=0;cpu<stat.length;cpu++)
            {
                out.print("cpu"+cpu);
                for(int time=0;time<stat[cpu].length;time++)
                {
                    out.print(" "+stat[cpu][time]);
                }
                out.println();
            }
            
            out.println("intr 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
            out.println("ctxt 0");
            out.println("btime 0");
            out.println("processes 0");
            out.println("procs_running 0");
            out.println("procs_blocked 0");
            out.println("softirq 0 0 0 0 0 0 0 0 0 0 0");
            
            out.close();
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace(System.err);
        }
    }
    
    public void meminfo()
    {
        File fstat = new File("/proc/meminfo");
        try
        {
            PrintStream out = new PrintStream(fstat);
            
            out.println("MemTotal:        4060528 kB");
            out.println("MemFree:           50240 kB");
            out.println("Buffers:          229124 kB");
            out.println("Cached:          1257928 kB");
            out.println("SwapCached:        12088 kB");
            out.println("Active:          2423200 kB");
            out.println("Inactive:        1364948 kB");
            out.println("Active(anon):    1734820 kB");
            out.println("Inactive(anon):   566612 kB");
            out.println("Active(file):     688380 kB");
            out.println("Inactive(file):   798336 kB");
            out.println("Unevictable:           0 kB");
            out.println("Mlocked:               0 kB");
            out.println("SwapTotal:       8393956 kB");
            out.println("SwapFree:        8334464 kB");
            out.println("Dirty:               488 kB");
            out.println("Writeback:             0 kB");
            out.println("AnonPages:       2291176 kB");
            out.println("Mapped:            32196 kB");
            out.println("Shmem:               336 kB");
            out.println("Slab:             180516 kB");
            out.println("SReclaimable:     154896 kB");
            out.println("SUnreclaim:        25620 kB");
            out.println("KernelStack:        2152 kB");
            out.println("PageTables:        11020 kB");
            out.println("NFS_Unstable:          0 kB");
            out.println("Bounce:                0 kB");
            out.println("WritebackTmp:          0 kB");
            out.println("CommitLimit:    10424220 kB");
            out.println("Committed_AS:    3723312 kB");
            out.println("VmallocTotal:   34359738367 kB");
            out.println("VmallocUsed:      283556 kB");
            out.println("VmallocChunk:   34359449588 kB");
            out.println("HugePages_Total:       0");
            out.println("HugePages_Free:        0");
            out.println("HugePages_Rsvd:        0");
            out.println("HugePages_Surp:        0");
            out.println("Hugepagesize:       2048 kB");
            out.println("DirectMap4k:        7680 kB");
            out.println("DirectMap2M:     4186112 kB");
            
            out.close();
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace(System.err);
        }
    }
}
