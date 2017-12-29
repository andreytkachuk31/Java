package model;

import java.io.Serializable;

public class SingletonSerialObject implements Serializable {

    public final static SingletonSerialObject INSTANCE = new SingletonSerialObject();

    private SingletonSerialObject() {}

    private Object readResolve() {
        return INSTANCE;
    }
}
