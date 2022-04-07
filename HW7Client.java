import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class HW7Client
{
    public static void main(String[] args) throws Exception
    { 
        Socket s = new Socket("localhost", 2222); 
        Scanner clientInput = new Scanner(s.getInputStream());
        String question = clientInput.nextLine();
        System.out.println(question);
        Scanner localInput = new Scanner(System.in);
        PrintStream clientOutput = new PrintStream(s.getOutputStream());
        

        Thread lt = new Thread() {
            public void run()
            {
                String line;
                String running = "running";
                while(running == "running")
                {
                    line = clientInput.nextLine();
                    if(line.equals("/quit"))
                    {
                        System.out.println("User leaving!");
                        running = "not running";
                        CORE.removeClientThreadPrintStream(clientOutput);
                    }
                    else
                    {
                        System.out.println(line);
                    }

                }
            }
        };
    lt.start();

        while(true)
        {
            clientOutput.println(localInput.nextLine());
        }
        
    }
}