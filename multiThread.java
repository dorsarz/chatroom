import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class multiThread implements Runnable{
  public Socket socket;
  private String name;
  private ArrayList<PrintWriter> clients;

    public multiThread(Socket socket, String name,ArrayList<PrintWriter> clients){
        this.name=name;
        this.socket=socket;
        this.clients=clients;
    }

    @Override
    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(name + ": " + message);
                for (int i = 0; i < clients.size(); i++) {
                    clients.get(i).println(name + ": " + message);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}