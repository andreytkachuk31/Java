package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CustomSerialObject implements Serializable {

    private transient String version = "100";

    private transient byte count = 0;

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeObject("custom:" + version);
        s.writeObject(count);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        this.version = (String) s.readObject();
        this.count = (byte) s.readObject();
    }

    @Override
    public String toString() {
        return "{version=" + version + ", count=" + count + "}";
    }
}
