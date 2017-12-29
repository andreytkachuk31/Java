import model.AgentSerialObject;
import model.CustomSerialObject;
import model.SimpleSerialObject;
import model.SingletonSerialObject;
import model.TransientSerialObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static constant.Constants.SERIALIZATION_FILE;

public class Deserializator {

    public static void main(String[] args) {
        deserialization();
    }

    private static void deserialization() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALIZATION_FILE))) {
            SimpleSerialObject simpleSerialObject = (SimpleSerialObject) ois.readObject();
            TransientSerialObject transientSerialObject = (TransientSerialObject) ois.readObject();
            CustomSerialObject customSerialObject = (CustomSerialObject) ois.readObject();
            SingletonSerialObject singletonSerialObject = (SingletonSerialObject) ois.readObject();
            AgentSerialObject agentSerialObject = (AgentSerialObject) ois.readObject();

            System.out.println("Simple: " + simpleSerialObject);
            System.out.println("Transient: " + transientSerialObject);
            System.out.println("Custom: " + customSerialObject);
            System.out.println("Singleton: " + singletonSerialObject);
            System.out.println("Agent: " + agentSerialObject);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
