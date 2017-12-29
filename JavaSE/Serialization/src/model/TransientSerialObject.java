package model;

import java.io.Serializable;

public class TransientSerialObject implements Serializable {

    private static final String NAME = "name"; // static data doesn't serialaze

    private transient byte version = 100;

    private byte count = 0;

    @Override
    public String toString() {
        return "{version=" + version + ", count=" + count + "}";
    }
}
