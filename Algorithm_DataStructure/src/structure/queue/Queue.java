package structure.queue;

/**
 * Очередь
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public interface Queue<Item> extends Iterable<Item> {

    void enqueue(Item item);

    Item dequeue();

    boolean isEmpty();

    int size();
}
