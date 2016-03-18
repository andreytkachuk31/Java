package structure.bag;

/**
 * Контейнер - это коллекция не поддерживающая удаление
 *
 * @author : andrey.tkachuk31
 * @since : 18.10.13
 */
public interface Bag<Item> extends Iterable<Item> {

    void add(Item item);

    boolean isEmpty();

    int size();
}