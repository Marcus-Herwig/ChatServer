import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class chatServerWorker extends Thread
{
    private Socket theClientSocket;
    private PrintStream clientOutput;
    private Scanner clientInput;

    public chatServerWorker(Socket theClientSocket)
    {
        try 
        {
            System.out.println("Connection Established...");
            this.theClientSocket = theClientSocket;
            this.clientOutput = new PrintStream(this.theClientSocket.getOutputStream());    
            //System.out.println("About to add a printstream");
            CORE.addClientThreadPrintStream(this.clientOutput);

            this.clientInput = new Scanner(this.theClientSocket.getInputStream());
        } 
        catch (Exception e) 
        {
            System.err.println("Bad things happened in thread!!!!!");
            e.printStackTrace();
        }
        
    }

    public void run()
    {
        //this is what the thread does
        this.clientOutput.println("What is your name?");
        String name = clientInput.nextLine();
        CORE.broadcastMessage(name + " has joined!");
        
        String message;
        while(true)
        {
            message = clientInput.nextLine();
            if(message.equals("/quit"))
            {
                this.clientOutput.close();
            }
            CORE.broadcastMessage(message);
        }
    }
}