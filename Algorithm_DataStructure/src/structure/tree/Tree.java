package structure.tree;

/**
 * @author: Андрей
 * @since: 01.02.14
 */
public interface Tree<K extends Comparable<K>, V> {

    void put(K key, V value);

    V get(K key);

    void remove(K key);

    void traverseTree(TraverseType traverseType);

    public K max();

    public K min();
}
