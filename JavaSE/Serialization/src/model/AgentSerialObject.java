package model;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public final class AgentSerialObject implements Serializable {

    private final Date startDate;

    private final Date endDate;

    public AgentSerialObject(Date startDate, Date endDate) {
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    @Override
    public String toString() {
        return "{startDate=" + startDate + ", endDate=" + endDate + '}';
    }

    private static class SerializationProxy implements Serializable {

        private final Date startDate;

        private final Date endDate;

        private SerializationProxy(AgentSerialObject serialObject) {
            this.startDate = serialObject.startDate;
            this.endDate = serialObject.endDate;
        }

        private Object readResolve() {
            return new AgentSerialObject(startDate, endDate);
        }
    }
}
