import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class client {
    public static void main(String[] args) {
        try {
            System.out.println("Connected to the server");
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner=new Scanner(System.in);
            new Thread(new multiThread(in)).start();
            while (scanner.hasNextLine()) {
            out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
   class multiThread implements Runnable{
    private BufferedReader reader;
    public multiThread(BufferedReader reader){
        this.reader=reader;
    }

    @Override
    public void run() {
        String message;
        try{
            while ((message= reader.readLine())!=null) {
                System.out.println(message);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}