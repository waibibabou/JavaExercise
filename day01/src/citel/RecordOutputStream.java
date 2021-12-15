package citel;

import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class RecordOutputStream extends FilterOutputStream {

    public RecordOutputStream(DataOutputStream out) {
        super(out);
    }
    public void writeRecord(Record record){
        try {
            ((DataOutputStream) out).writeChars(record.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
