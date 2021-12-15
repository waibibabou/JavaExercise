package citel;

public class Record {
    private String stringField;
    private int integerField;
    private double doubleField;


    public Record(String stringField, int integerField, double doubleField) {
        this.stringField = stringField;
        this.integerField = integerField;
        this.doubleField = doubleField;
    }

    public String getStringField() {
        return stringField;
    }

    public int getIntegerField() {
        return integerField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    @Override
    public String toString() {
        return "Record[" +
                "'" + stringField + '\'' +
                ", " + integerField +
                ", " + doubleField +
                "]\n";
    }
}
