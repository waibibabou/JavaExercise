package citel;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

public class RecordInputStream extends FilterInputStream {

    public RecordInputStream(DataInputStream in) {
        super(in);
    }
    public String readRecord(){

        try {
            return ((DataInputStream)in).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
