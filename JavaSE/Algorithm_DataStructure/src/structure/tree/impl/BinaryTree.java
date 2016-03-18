package structure.tree.impl;

import structure.tree.TraverseType;
import structure.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Бинарное дерево (итеративно)
 *
 * @author: Андрей
 * @since: 08.01.14
 */
public class BinaryTree<K extends Comparable<K>, V> implements Tree<K, V> {

    private Node<K, V> root;

    public void put(K key, V value) {
        if (root != null) {
            Node<K, V> current = root;
            while (current != null) {
                int cmp = key.compareTo(current.key);
                if (cmp < 0) {
                    if (current.left == null) {
                        current.left = new Node<K, V>(key, value);
                        break;
                    } else {
                        current = current.left;
                    }
                } else if (cmp > 0) {
                    if (current.right == null) {
                        current.right = new Node<K, V>(key, value);
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    current.value = value;
                }
            }
        } else {
            root = new Node<K, V>(key, value);
        }
    }

    public V get(K key) {
        Node<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    /**
     * 1) Если у удаляемой вершины нет правого сына. Тогда мы можем убрать ее и вместо нее вставить левое поддерево, не нарушая упорядоченности<br/><br/>
     * 2) Если у левого сына может уже быть правый сын: находим в правом поддереве минимум.
     * Ясно, что его можно найти если начать в правом сыне и идти до упора влево. Т.к у найденного минимума нет левого сына, можно вырезать его по аналогии со случаем 1 и вставить его вместо удалеемой вершины.
     * Из-за того что он был минимальным в правом поддереве, свойство упорядоченности не нарушится
     */
    public void remove(K key) {
        /*Находим ключ который нужно удалить*/
        Node<K, V> current = root;
        Node<K, V> prev = current;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                prev = current;
                current = current.left;
            } else if (cmp > 0) {
                prev = current;
                current = current.right;
            } else {
                break;
            }
        }

        if (current == null) return; /*Ключ не найден*/

        /*Удаляем узел*/
        if (current.right == null) {
            if (current == prev.left) {
                prev.left = current.left;
            } else {
                prev.right = current.left;
            }
        } else {
            /*Найти минимальный елемент в поддереве*/
            Node<K, V> minEl = current;
            prev = null;
            while (minEl.left != null) {
                prev = minEl;
                minEl = minEl.left;
            }
            /**********************************/
            if (prev != null) {
                prev.left = minEl.right;
            }
            current.key = minEl.key;
            current.value = minEl.value;
        }
    }

    public void traverseTree(TraverseType traverseType) {
        traverseTree(root, traverseType);
        System.out.println();
    }

    public K max() {
        Node<K, V> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    public K min() {
        Node<K, V> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.key;
    }

    private void traverseTree(Node<K, V> current, TraverseType traverseType) {
        if (current == null)
            return;
        switch (traverseType) {
            case INORDER:
                traverseTree(current.left, traverseType);
                System.out.print(current.key + " ");
                traverseTree(current.right, traverseType);
                break;
            case PREORDER:
                System.out.print(current.key + " ");
                traverseTree(current.left, traverseType);
                traverseTree(current.right, traverseType);
                break;
            case POSTORDER:
                traverseTree(current.left, traverseType);
                traverseTree(current.right, traverseType);
                System.out.print(current.key + " ");
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
