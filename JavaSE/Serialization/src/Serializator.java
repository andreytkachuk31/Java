import model.AgentSerialObject;
import model.CustomSerialObject;
import model.SimpleSerialObject;
import model.SingletonSerialObject;
import model.TransientSerialObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import static constant.Constants.SERIALIZATION_FILE;

public class Serializator {

    public static void main(String[] args) {
        serialization();
    }

    private static void serialization() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZATION_FILE))) {
            oos.writeObject(new SimpleSerialObject());
            oos.writeObject(new TransientSerialObject());
            oos.writeObject(new CustomSerialObject());
            oos.writeObject(SingletonSerialObject.INSTANCE);
            oos.writeObject(new AgentSerialObject(new Date(), new Date()));

            System.out.println("Serialization has been done");
        } catch (IOException e) {
            System.out.println("Serialization was failed: '" + e + "'");
        }
    }
}
