package citel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFamilies {
  public static void main(String[] args) {
    Family family1;
    Family family2;
    Family family3;
    Person pat_willcutt;
    Person bryan_basham;

    try {
      /***
      **** Step 8: declare and initialize input stream objects
      ***/
      ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("families.ser"));

      /***
      **** Step 9: read the three families from the object stream
      **** Don't forget to close the top-level stream when done.
      ***/
      family1= (Family) objectInputStream.readObject();
      family2= (Family) objectInputStream.readObject();
      family3= (Family) objectInputStream.readObject();
      objectInputStream.close();

      pat_willcutt = family1.findChild("Pat");
      bryan_basham = family3.findChild("Bryan");

      // queries
      System.out.println("Pat Willcutt has " + pat_willcutt.getNumberOfSiblings() + " sibilings.");
      System.out.println("Bryan Basham has " + bryan_basham.getNumberOfSiblings() + " sibilings.");

      // Handle exceptions
    } catch (IOException e1) {
      e1.printStackTrace();
    } catch (ClassNotFoundException e2) {
      e2.printStackTrace();
    }
  }
}
