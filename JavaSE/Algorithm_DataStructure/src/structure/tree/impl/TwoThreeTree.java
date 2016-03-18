package structure.tree.impl;

import structure.tree.TraverseType;

/**
 * @since: 02.02.14
 * @author: Андрей
 */
public class TwoThreeTree<K extends Comparable<K>> {

    private Node<K> root;

    public void put(K key) {
        Node<K> current = root;
        if (current != null) {
            insert(root, key);
        } else {
            root = Node.newTwoNode(key);
        }
    }

    public K get(K key) {
        return find(root, key);
    }

    public void remove(K key) {
        //Ko change body of implemented methods use File | Settings | File Kemplates.
    }

    public void traverseKree(TraverseType traverseType) {
        //Ko change body of implemented methods use File | Settings | File Kemplates.
    }

    public K max() {
        throw new UnsupportedOperationException();
    }

    public K min() {
        throw new UnsupportedOperationException();
    }

    private Node<K> insert(Node<K> current, K key) {
        Node<K> returnNode = null;
        if (current.isTwoNode()) {
            int cmp = key.compareTo(current.leftKey);
            /*If search complete 2-node, then create 3-node*/
            if (current.isTerminal()) {
                Node<K> threeNode = Node.newThreeNode(current.leftKey, key); /*Сreate 3-node*/
                Node<K> parent = current.parent;
                if (parent != null) {
                    parent.replace(current, threeNode);
                } else {
                    root = threeNode;
                }
            } else {
                if (cmp < 0) {
                    Node<K> node = insert(current.left, key);
                    if (current != null) {
                        Node<K> threeNode = Node.newThreeNode(node.leftKey, current.leftKey);
                        threeNode.left = node.left;
                        threeNode.middle = node.right;
                        threeNode.right = current.right;
                        Node<K> parent = current.parent;
                        if (parent != null) {
                            parent.replace(node, threeNode);
                        } else {
                            root = threeNode;
                        }
                    }
                    node.unlinkNode(); // unlink(clear) node
                } else if (cmp > 0) {
                    insert(current.right, key);
                    Node<K> node = insert(current.left, key);
                    if (current != null) {
                        Node<K> threeNode = Node.newThreeNode(node.leftKey, current.leftKey);
                        threeNode.left = current.left;
                        threeNode.middle = node.left;
                        threeNode.right = node.right;
                        Node<K> parent = current.parent;
                        if (parent != null) {
                            parent.replace(node, threeNode);
                        } else {
                            root = threeNode;
                        }
                    }
                    node.unlinkNode(); // unlink(clear) node
                }
            }
        } else { // three node
            Node<K> threeNode = current;

            int leftComp = key.compareTo(threeNode.leftKey);
            int rightComp = key.compareTo(threeNode.rightKey);

             /*If search complete 3-node, then split node*/
            if (threeNode.isTerminal()) {
                returnNode = Node.splitNode(threeNode, key);
            } else {
                if (leftComp < 0) {
                    Node<K> node = insert(threeNode.left, key);
                    Node<K> a = Node.splitNode(node, node.leftKey);
                } else if (rightComp < 0) {
                    insert(threeNode.middle, key);
                } else {
                    insert(threeNode.right, key);
                }
            }
        }
        return returnNode;
    }

    private K find(Node<K> current, K key) {
        if (current == null) return null;

        if (current.isThreeNode()) {
            int leftCmp = key.compareTo(current.leftKey);
            int rightCmp = key.compareTo(current.rightKey);
            if (leftCmp == 0 || rightCmp == 0) return key;
            else if (leftCmp < 0) return find(current.left, key);
            else if (rightCmp < 0) return find(current.middle, key);
            else return find(current.right, key);
        } else {
            int cmp = key.compareTo(current.leftKey);
            if (cmp < 0) return find(current.left, key);
            else if (cmp > 0) return find(current.right, key);
            else return key;
        }
    }

    static class Node<K extends Comparable> {

        Node<K> parent;
        Node<K> left;
        Node<K> right;
        Node<K> middle;

        /* When node is 2-node, leftVal is the values, and rightVal is null.*/
        K leftKey;
        K rightKey;

        boolean twoNode;

        /**
         * Create 2-node
         *
         * @param key
         * @return 2-node
         */
        public static <K extends Comparable> Node<K> newTwoNode(K key) {
            Node<K> node = new Node<K>();
            node.leftKey = key;
            node.twoNode = true;
            return node;
        }

        /**
         * Create 3-node
         *
         * @param leftKey
         * @param rightKey
         * @return 3-node
         */
        public static <K extends Comparable> Node<K> newThreeNode(K leftKey, K rightKey) {
            Node<K> node = new Node<K>();
            if (leftKey.compareTo(rightKey) > 0) {
                node.rightKey = leftKey;
                node.leftKey = rightKey;
            } else {
                node.leftKey = leftKey;
                node.rightKey = rightKey;
            }
            node.twoNode = false;
            return node;
        }

        public static <K extends Comparable> Node<K> splitNode(Node<K> threeNode, K key) {
            K leftKey;
            K middleKey;
            K rightKey;
            if (key.compareTo(threeNode.leftKey) < 0) {
                leftKey = key;
                middleKey = threeNode.leftKey;
                rightKey = threeNode.rightKey;
            } else if (key.compareTo(threeNode.rightKey) < 0) {
                leftKey = threeNode.leftKey;
                middleKey = key;
                rightKey = threeNode.rightKey;
            } else {
                leftKey = threeNode.leftKey;
                middleKey = threeNode.rightKey;
                rightKey = key;
            }
            Node<K> parent = Node.newTwoNode(middleKey);
            parent.setLeft(Node.newTwoNode(leftKey));
            parent.setRight(Node.newTwoNode(rightKey));
            return parent;
        }

        public void replace(Node<K> currentNode, Node<K> newNode) {
            if (currentNode == left) { /* If current node is left child, then replace it */
                left = newNode;
            } else if (currentNode == right) { /* If current node is right child, then replace it */
                right = newNode;
            } else {
                middle = newNode;
            }
            newNode.parent = this;
            currentNode.parent = null;
        }

        public void unlinkNode() {
            this.left = null;
            this.middle = null;
            this.right = null;
            this.parent = null;
        }

        public void setLeft(Node<K> left) {
            this.left = left;
            if (left != null){
                left.parent = this;
            }
        }

        public void setRight(Node<K> right){
            this.right = right;
            if (right != null){
                right.parent = this;
            }
        }


        /**
         * Check null-node (leaf)
         *
         * @return true, if node is leaf
         */
        public boolean isTerminal() {
            return left == null && right == null;
        }

        public boolean isTwoNode() {
            return twoNode;
        }

        public boolean isThreeNode() {
            return !isTwoNode();
        }
    }
}
