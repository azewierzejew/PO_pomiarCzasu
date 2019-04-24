package time;

/**
 * Class representing treap tree. Treap is a bst tree by values and heap by
 * randomized keys. Can add, find and remove in expected complexity of log(n).
 * 
 * @author Antoni Żewierżejew
 */
public class Treap {

    /**
     * Randomized key generator. Static key generator used for all new keys, by
     * default seeded with 0, can be reseeded.
     */
    private static Random rng = new Random(0);
    /**
     * Left son.
     */
    private Treap left;
    /**
     * Right son.
     */
    private Treap right;
    /**
     * Value stored in node.
     */
    private final int value;
    /**
     * Random key assigned to node.
     */
    private final int key;

    /**
     * Generic constructor. Uses rng to generate key.
     * 
     * @param value node's value
     */
    private Treap(int value) {
        left = null;
        right = null;
        this.value = value;
        key = rng.get();
        System.err.println("key: " + key);
    }

    /**
     * Sets left and right as sons to root.
     * 
     * @param root  tree's root
     * @param left  left son
     * @param right right son
     * @return root
     */
    private static Treap attach(Treap root, Treap left, Treap right) {
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * Splits given tree by value. If value is in tree adds it to the first
     * component.
     * 
     * @param root  tree's root
     * @param value split point
     * @return pair of split trees
     */
    private static Pair<Treap, Treap> splitLeft(Treap root, int value) {
        if (root == null) {
            return new Pair<Treap, Treap>(null, null);
        }
        if (root.value <= value) {
            Pair<Treap, Treap> tmp = splitLeft(root.right, value);
            return new Pair<Treap, Treap>(attach(root, root.left, tmp.first()), tmp.second());
        }
        Pair<Treap, Treap> tmp = splitLeft(root.left, value);
        return new Pair<Treap, Treap>(tmp.first(), attach(root, tmp.second(), root.right));
    }

    /**
     * Splits given tree by value. If value is in tree adds it to the second
     * component.
     * 
     * @param root  tree's root
     * @param value split point
     * @return pair of split trees
     */
    private static Pair<Treap, Treap> splitRight(Treap root, int value) {
        if (root == null) {
            return new Pair<Treap, Treap>(null, null);
        }
        if (root.value < value) {
            Pair<Treap, Treap> tmp = splitRight(root.right, value);
            return new Pair<Treap, Treap>(attach(root, root.left, tmp.first()), tmp.second());
        }
        Pair<Treap, Treap> tmp = splitRight(root.left, value);
        return new Pair<Treap, Treap>(tmp.first(), attach(root, tmp.second(), root.right));
    }

    /**
     * Merges two tree into single tree. It is required that all values in left tree
     * are strictly smaller than all values in the right tree.
     * 
     * @param left  left tree
     * @param right right tree
     * @return merged tree
     */
    private static Treap merge(Treap left, Treap right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.key < right.key) {
            return attach(left, left.left, merge(left.right, right));
        }
        return attach(right, merge(left, right.left), right.right);
    }

    /**
     * Checks if value is present in the tree.
     * 
     * @param root  tree's root
     * @param value value to be checked
     * @return true if value is present in tree, false otherwise
     */
    public static boolean isMember(Treap root, int value) {
        if (root == null)
            return false;
        if (root.value < value) {
            return isMember(root.right, value);
        }
        if (root.value > value) {
            return isMember(root.left, value);
        }
        return true;
    }

    /**
     * Adds value to tree. If value already present in tree does nothing.
     * 
     * @param root  tree's root
     * @param value value to be added
     * @return new root
     */
    public static Treap add(Treap root, int value) {
        if (isMember(root, value))
            return root;
        Treap node = new Treap(value);
        Pair<Treap, Treap> tmp = splitLeft(root, value);
        return merge(tmp.first(), merge(node, tmp.second()));
    }

    /**
     * Removes value from tree. If value is not present in tree does nothing.
     * 
     * @param root  tree's root
     * @param value value to be removed
     * @return new root
     */
    public static Treap remove(Treap root, int value) {
        if (!isMember(root, value))
            return root;
        Pair<Treap, Treap> tmp = splitLeft(root, value);
        Pair<Treap, Treap> tmp2 = splitRight(tmp.first(), value);
        return merge(tmp2.first(), tmp.second());
    }

    /**
     * Sets new seed for key generator.
     * 
     * @param seed new seed
     */
    public static void setSeed(int seed) {
        rng = new Random(seed);
    }

}
