import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

 class server {
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
  class multiThread implements Runnable{
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