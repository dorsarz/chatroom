import java.io.BufferedReader;
import java.io.IOException;

public class multiThread implements Runnable{
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

