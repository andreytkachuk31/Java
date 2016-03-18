package algorithm.haphman;

/**
 * @author Andrii_Tkachuk
 * @since 4/14/2015
 */
public class CharacterFrequencyNode extends CharacterFrequency {

    private CharacterFrequencyNode left;
    private CharacterFrequencyNode right;

    public CharacterFrequencyNode(CharacterFrequency element) {
        super(element.getSymbol(), element.getFrequency());
        if (element instanceof CharacterFrequencyNode) {
            CharacterFrequencyNode elementNode = (CharacterFrequencyNode) element;
            this.left = elementNode.getLeft();
            this.right = elementNode.getRight();
        }
    }

    public CharacterFrequencyNode(CharacterFrequencyNode left, CharacterFrequencyNode right) {
        super('\0', left.getFrequency() + right.getFrequency());
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public CharacterFrequencyNode getLeft() {
        return left;
    }

    public CharacterFrequencyNode getRight() {
        return right;
    }
}
