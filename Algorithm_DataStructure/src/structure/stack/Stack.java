package structure.stack;

/**
 * Стек
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public interface Stack<Item> extends Iterable<Item> {

    void push(Item item);

    Item pop();

    boolean isEmpty();

    int size();

}
