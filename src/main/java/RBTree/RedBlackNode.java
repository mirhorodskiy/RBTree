package RBTree;

public class RedBlackNode<T extends Comparable<T>> {
    public static boolean RED = false;
    public static boolean BLACK = true;

    //элемент поумолчанию красный
    public boolean color;
    public RedBlackNode<T> left;
    public RedBlackNode<T> right;
    public RedBlackNode<T> parent;
    public T data;

    public RedBlackNode(T data) {
        this.data = data;
        this.color = RED;
    }

    //удалить ссылку на текущий элемент у родителя
    public void removeFromParent() {
        if (parent == null)
            return;

        // Remove current node's links from the parent
        if (parent.left == this)
            parent.left = null;
        else if (parent.right == this)
            parent.right = null;

        //у самого элемента удалим ссылку на родителя
        this.parent = null;
    }

    //сделать child левым потомком у parent
    public void setLeft(RedBlackNode<T> child) {

        // отсоединяем текущего левого от родителя
        if (left != null)
            left.parent = null;

        if (child != null) {
            child.removeFromParent();
            child.parent = this;
        }

        this.left = child;
    }

    public void setRight(RedBlackNode<T> child) {
        if (right != null) {
            right.parent = null;
        }

        if (child != null) {
            child.removeFromParent();
            child.parent = this;
        }

        this.right = child;
    }

    public static boolean isRed(RedBlackNode<?> node) {
        return getColor(node) == RED;
    }

    public static boolean isBlack(RedBlackNode<?> node) {
        return !isRed(node);
    }

    public static void setColor(RedBlackNode<?> node, boolean color) {
        if (node == null)
            return;
        node.color = color;
    }

    public static boolean getColor(RedBlackNode<?> node) {
        // As null node is considered to be black
        return node == null ? BLACK : node.color;
    }

    public static void swapColor(RedBlackNode<?> node) {
        if (node == null)
            return;

        node.color = !node.color;
    }

    public RedBlackNode<T> getGrandparent() {
        return (parent == null) ? null : parent.parent;
    }


    //брат
    public RedBlackNode<T> getSibling() {
        if (parent != null) {
            if (this == parent.right)
                return parent.left;
            else
                return parent.right;
        }
        return null;
    }


    public RedBlackNode<T> getUncle() {
        if (parent != null) { // No uncle of root
            return parent.getSibling();
        }
        return null;
    }


}

