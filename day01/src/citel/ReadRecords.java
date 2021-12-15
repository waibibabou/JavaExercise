package citel; /***
**** What classes need to be imported?
***/
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadRecords {
  public static void main(String[] args) {
    Record record;

    try {
      /***
      **** Step 1: declare and initialize the record input stream
      ***/
      RecordInputStream recordInputStream=new RecordInputStream(new DataInputStream(new FileInputStream("record.db")));


      /***
      **** Step 2: read the five records from the record input stream
      **** Don't forget to close the top-level stream.
      ***/
      for(int i=0;i<5;i++){
        System.out.println(recordInputStream.readRecord());
      }
      recordInputStream.close();

      // Handle excpetions
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
