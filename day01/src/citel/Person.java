package citel;

import java.io.Serializable;

/***
**** Step #2: declare that the Family class is serializable.
***/
public class Person implements Serializable {
  // Attributes
  private String firstName;
  private String lastName;
  public static final long serialVersionUID=7654321L;
  /***
  **** Step #3: declare that the numOfSiblings attribute is not
  **** to be serialized.
  ***/
  private transient int numOfSiblings;
  // Associations
  private Family family = null;

  // Constructor
  public Person(Family fam, String f, String l) {
    firstName = f;
    lastName = l;
    family = fam;
    if ( family != null ) {
      family.addChild(this);
    }
  }

  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public int getNumberOfSiblings() {
    /***
    **** Step #4: calculate the numOfSiblings attribute
    **** This should only be done once per execution.
    ***/
    numOfSiblings=family.getChildren().size()-1;
    System.out.println("Calculating number of siblings for "+firstName+" "+lastName);

    return numOfSiblings;
  }
}
