package citel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteFamilies {
  public static void main(String[] args) {
    Family family1 = new Family();
    Family family2 = new Family();
    Family family3 = new Family();
    Person inez_xxx = new Person(null, "Inez", "Xxx");
    Person ward_willcutt = new Person(null, "Ward", "Willcutt");
    Person joanie_willcutt = new Person(family1, "Joanie", "Willcutt");
    Person jeanie_willcutt = new Person(family1, "Jeanie", "Willcutt");
    Person pat_willcutt = new Person(family1, "Pat", "Willcutt");
    Person bob_willcutt = new Person(family1, "Bob", "Willcutt");
    Person wendy_willcutt = new Person(family1, "Wendy", "Willcutt");
    Person elizabeth_brown = new Person(null, "Elizabeth", "Brown");
    Person ulyss_basham = new Person(null, "Ulyss", "Basham");
    Person duane_basham = new Person(family2, "Duane", "Basham");
    Person brenda_basham = new Person(family3, "Brenda", "Basham");
    Person deareen_basham = new Person(family3, "Deareen", "Basham");
    Person bryan_basham = new Person(family3, "Bryan", "Basham");

    // set up relationships
    family1.setMother(inez_xxx);
    family1.setFather(ward_willcutt);
    family2.setMother(elizabeth_brown);
    family2.setFather(ulyss_basham);
    family3.setMother(pat_willcutt);
    family3.setFather(duane_basham);

    // queries
    System.out.println("Pat Willcutt has " + pat_willcutt.getNumberOfSiblings() + " sibilings.");
    System.out.println("Bryan Basham has " + bryan_basham.getNumberOfSiblings() + " sibilings.");

    try {
      /***
      **** Step 5: declare and initialize output stream objects
      ***/
      ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("families.ser"));


      /***
      **** Step 6: write the three families to the object stream
      **** Don't forget to flush and close the top-level stream when done.
      ***/
      objectOutputStream.writeObject(family1);
      objectOutputStream.writeObject(family2);
      objectOutputStream.writeObject(family3);
      objectOutputStream.close();

      // Handle exceptions
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
