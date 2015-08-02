/**
 * @since: 29.05.14
 * @author: Андрей
 */
public class NativeClass {

    static {
        System.load("C:\\Users\\Андрей\\IdeaProjects\\Native\\nativeCode\\Native.dll");
    }

    public static native int printOne();

    public static void main(String[] args) {
        NativeClass.printOne();
    }
}
