package model;

import java.io.Serializable;

public class SimpleSerialObject implements Serializable {

    private byte version = 100;

    private byte count = 0;

    @Override
    public String toString() {
        return "{version=" + version + ", count=" + count + "}";
    }
}