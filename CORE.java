import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CORE 
{
    private static ArrayList<PrintStream> theClientStreams = new ArrayList<PrintStream>();
    
    public static synchronized void addClientThreadPrintStream(PrintStream ps)
    {
        System.out.println("adding client thread");
        CORE.theClientStreams.add(ps);
    }

    public static synchronized void removeClientThreadPrintStream(PrintStream ps)
    {
        
        int a = CORE.theClientStreams.indexOf(ps);
        for(int i = 0; i <= a; i++ )
        {
            if(CORE.theClientStreams.indexOf(i) == CORE.theClientStreams.indexOf(a))
            {
                CORE.theClientStreams.remove(i);
            }
        }
        //ps.close();
    }

    public static void broadcastMessage(String message)
    {
        System.out.println("About to broadcast....");
        for (PrintStream ps : CORE.theClientStreams)
        {
            ps.println(message);
        }
    }
    public static void broadcastQuit()
    {
        System.out.println("About to broadcast....");
        for (PrintStream ps : CORE.theClientStreams)
        {
            ps.println("quit");
        }
    }
}