import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        AtomicInteger clientCounter = new AtomicInteger(0);
        ArrayList<PrintWriter> clients =new ArrayList<>();
        try {
            System.out.println("Chat Server is running on port 1234");
            serverSocket = new ServerSocket(1234);
            while (true){

                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                clients.add(out);
                String clientName="User" + clientCounter.incrementAndGet();
                Thread thread=new Thread(new multiThread(socket,clientName,clients));
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}