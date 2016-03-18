package structure.tree.impl;

import structure.tree.TraverseType;
import structure.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Бинарное дерево (рекурсивно)
 *
 * @author: Андрей
 * @since: 09.01.14
 */
public class BinaryTreeRekursia<K extends Comparable<K>, V> implements Tree<K, V> {

    private Node<K, V> root;

    public void put(K key, V value) {
        put(root, key, value);
    }

    public V get(K key) {
        return get(root, key);
    }

    /**
     * 1) Если у удаляемой вершины нет правого сына. Тогда мы можем убрать ее и вместо нее вставить левое поддерево, не нарушая упорядоченности<br/><br/>
     * 2) Если у левого сына может уже быть правый сын: находим в правом поддереве минимум.
     *    Ясно, что его можно найти если начать в правом сыне и идти до упора влево. Т.к у найденного минимума нет левого сына, можно вырезать его по аналогии со случаем 1 и вставить его вместо удалеемой вершины.
     *    Из-за того что он был минимальным в правом поддереве, свойство упорядоченности не нарушится
     */
    public void remove(K key) {
        remove(root, key);
    }

    public void traverseTree(TraverseType traverseType) {
        traverseTree(root, traverseType);
    }

    public K max() {
        return max(root).key;
    }

    public K min() {
        return min(root).key;
    }

    private Node<K, V> put(Node<K, V> current, K key, V value) {
        if (current == null) {
            return new Node<K, V>(key, value);
        } else if (key.equals(current.key)) {
            current.value = value;
            return current;
        } else {
            if (key.compareTo(current.key) < 0) current.left = put(current.left, key, value);
            else current.right = put(current.right, key, value);
        }
        return current;
    }

    private V get(Node<K, V> current, K key) {
        if (current == null) return null;
        int cmp = key.compareTo(current.key);
        if (cmp < 0) return get(current.left, key);
        else if (cmp > 0) return get(current.right, key);
        else return current.value;
    }

    private Node<K, V> remove(Node<K, V> current, K key) {
        if (key == null) return null;
        int cmp = key.compareTo(current.key);
        if (cmp < 0) current.left = remove(current.left, key);
        else if (cmp > 0) current.right = remove(current.right, key);
        else {
            if (current.right == null) return current.left;
            if (current.left == null) return current.right;
            Node temp = current;
            current = min(temp.right);
            temp.key = current.key;
            temp.value = current.value;
            deleteMin(temp.right);
        }
        return current;
    }

    private void traverseTree(Node current, TraverseType traverseType) {
        if (current == null)
            return;
        switch (traverseType) {
            case INORDER:
                traverseTree(current.left, traverseType);
                System.out.println(current.key + " ");
                traverseTree(current.right, traverseType);
                break;
            case PREORDER:
                System.out.println(current.key + " ");
                traverseTree(current.left, traverseType);
                traverseTree(current.right, traverseType);
                break;
            case POSTORDER:
                traverseTree(current.left, traverseType);
                traverseTree(current.right, traverseType);
                System.out.println(current.key + " ");
                break;
            case WIDEORDER:
                Queue<Node<K, V>> queue = new LinkedList<Node<K, V>>();
                queue.offer(current);
                while (!queue.isEmpty()){
                    Node<K, V> node = queue.poll();
                    System.out.print(node.key + " ");
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                break;
        }
    }

    private Node<K, V> deleteMin(Node<K, V> current) {
        if (current.left == null) return current.right;
        current.left = deleteMin(current.left);
        return current;
    }

    private Node<K, V> min(Node current) {
        if (current.left == null) return current;
        return min(current.left);
    }

    private Node<K, V> max(Node current) {
        if (current.right == null) return current;
        return min(current.right);
    }

    static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }
    }
}
