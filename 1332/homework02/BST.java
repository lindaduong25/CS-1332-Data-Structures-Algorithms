import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
 *
 * @author Linda Duong
 * @version 1.0
 * @userid lduong8
 * @GTID 903568287
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 * https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null.");
        }
        size = 0;
        for (T element : data) {
            if (element == null) {
                throw new IllegalArgumentException("No element in the data can be null.");
            }
            add(element);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        root = addHelper(data, root);
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        if (size == 0) {
            throw new NoSuchElementException("Since the size is 0, the data does not exist.");
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        if (size == 0 || getHelper(data, root) == null) {
            throw new NoSuchElementException("If size is 0 the data doesn't exist, or the data was simply not found.");
        }
        return getHelper(data, root);
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }
        return containsHelper(data, root);
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> treeList = new ArrayList<T>(size);
        preOrderHelper(root, treeList);
        return treeList;
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> treeList = new ArrayList<T>(size);
        inOrderHelper(root, treeList);
        return treeList;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> treeList = new ArrayList<T>(size);
        postOrderHelper(root, treeList);
        return treeList;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> treeList = new ArrayList<>();
        if (root == null) {
            return treeList;
        }
        Queue<BSTNode<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.remove();
            treeList.add(curr.getData());
            if (curr.getLeft() != null) {
                q.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                q.add(curr.getRight());
            }
        }
        return treeList;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return heightHelper(root, 0);
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Determines if a binary tree is a valid BST.
     *
     * This must be done recursively. Do NOT modify the tree passed in.
     *
     * If the order property is violated, this method may not need to traverse
     * the entire tree to function properly, so you should only traverse the
     * branches of the tree necessary to find order violations and only do so once.
     * Failure to do so will result in an efficiency penalty.
     *
     * EXAMPLES: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * isBST(50) should return true, since for every node, the node's left
     * subtree is less than the node's data, and the node's right subtree is
     * greater than the node's data.
     *
     *             20
     *           /    \
     *         21      38
     *        /          \
     *       6          50
     *        \
     *         12
     *
     * isBST(20) should return false, since 21 is in 20's left subtree.
     *
     *
     * Should have a worst-case running time of O(n).
     *
     * @param node the root of the binary tree
     * @return true if the tree with node as the root is a valid BST,
     *         false otherwise
     */
    public boolean isBST(BSTNode<T> node) {
        return isBSTHelper(node, null, null);
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    // helper methods
    // compareTo method

    /**
     * Helper method for add(T data)
     * @param data the data to be added
     * @param curr the current node of the tree
     * @return node in the direction of traversal
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> curr) {
        if (curr == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        }
        return curr;
    }

    /**
     * Helper method for remove(T data)
     * @param curr the current node in the tree
     * @param data data to be removed
     * @param dummy acts as the child node
     * @return the node that was altered to get parent node of node that is going to be removed
     */
    private BSTNode<T> removeHelper(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("No element in the data can be null");
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(curr.getRight(), data, dummy));
        } else {
            // data found case
            dummy.setData(curr.getData());
            size--;
            // if 0 children case
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                BSTNode<T> dummy2 = new BSTNode<T>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        return curr;
    }

    /**
     * Helper method for remove(T data) that helps remove the successor
     * @param curr the current node in the the tree
     * @param dummy acts as the child node
     * @return the successor node
     */
    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        }
        curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
        return curr;
    }

    /**
     * Helper method for contains() and get()
     * @param data the data to find/search for
     * @param curr the current node of the tree
     * @return data if it is found, otherwise return same data passed in
     */
    private T getHelper(T data, BSTNode<T> curr) {
        if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                return null;
            }
            return getHelper(data, curr.getRight());
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                return null;
            }
            return getHelper(data, curr.getLeft());
        } else {
            return curr.getData();
        }
    }

    /**
     * Helper method for contains()
     * @param data data we are checking
     * @param node current node in BST
     * @return true or false depending on if the data is found in the tree
     */
    private boolean containsHelper(T data, BSTNode<T> node) {
        if (node == null) {
            return false;
        }
        if (data.compareTo(node.getData()) < 0) {
            return containsHelper(data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            return containsHelper(data, node.getRight());
        }
        return true;
    }

    /**
     * Helper method for preOrder()
     * @param curr the current node of the tree
     * @param treeList list of tree's data
     */
    private void preOrderHelper(BSTNode<T> curr, List<T> treeList) {
        if (curr == null) {
            return;
        }
        treeList.add(curr.getData());
        preOrderHelper(curr.getLeft(), treeList);
        preOrderHelper(curr.getRight(), treeList);
    }
    /**
     * Helper method for inOrder()
     * @param curr the current node of the tree
     * @param treeList list of tree's data
     */
    private void inOrderHelper(BSTNode<T> curr, List<T> treeList) {
        if (curr == null) {
            return;
        }
        inOrderHelper(curr.getLeft(), treeList);
        treeList.add(curr.getData());
        inOrderHelper(curr.getRight(), treeList);
    }
    /**
     * Helper method for postOrder()
     * @param curr the current node of the tree
     * @param treeList list of tree's data
     */
    private void postOrderHelper(BSTNode<T> curr, List<T> treeList) {
        if (curr == null) {
            return;
        }
        postOrderHelper(curr.getLeft(), treeList);
        postOrderHelper(curr.getRight(), treeList);
        treeList.add(curr.getData());
    }

    /**
     * Helper method that recursively finds a tree's height
     * @param curr current node to start with that has the initial height
     * @param heightTree initial height of current node
     * @return the height of a node
     */
    private int heightHelper(BSTNode<T> curr, int heightTree) {
        int heightRight = heightTree;
        int heightLeft = heightTree;
        if (curr.getLeft() != null) {
            heightLeft = heightHelper(curr.getLeft(), heightTree + 1);
        }
        if (curr.getRight() != null) {
            heightRight = heightHelper(curr.getRight(), heightTree + 1);
        }
        return Math.max(heightRight, heightLeft);
    }

    /**
     * Helper method for isBST()
     * @param <T> the generic type that allows for various data types and restrictions
     * @param node the current root node
     * @param min lower constraint
     * @param max higher constraint
     * @return true or false depending on if the BST is valid
     */
    private static <T extends Comparable<? super T>> boolean isBSTHelper(BSTNode<T> node, T min, T max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.getData().compareTo(min) < 0) {
            return false;
        }
        if (max != null && node.getData().compareTo(max) > 0) {
            return false;
        }
        return isBSTHelper(node.getLeft(), min, node.getData()) && isBSTHelper(node.getRight(), node.getData(), max);
    }

    // extra methods for practice exams
    // public int recursiveSum() {
    //     return recursiveSumHelper(root);
    // }
    // private int recursiveSumHelper(BSTNode<T> current) {
    //     if (current == null) {
    //         return 0;
    //     }
    //     int left = recursiveSumHelper(current.getLeft());
    //     int right = recursiveSumHelper(current.getRight());
    //     return current.getData() + left + right;
    // }

    public int numOfExternal() {
        return numOfExternalHelper(root);
    }
    private int numOfExternalHelper(BSTNode<T> current) {
        if (current == null) {
            return 0;
        }
        int left = numOfExternalHelper(current.getLeft());
        int right = numOfExternalHelper(current.getRight());
        if (current.getLeft() == null && current.getRight() == null) {
            return left + right + 1;
        }
        return left + right;
    }

    public static void main(String[] args) {
        BST<Integer> myTree = new BST<>();
        myTree.add(56);
        //myTree.remove(1);
        // myTree.add(28);
        // myTree.add(79);
        // myTree.add(18);
        // myTree.add(35);
        // myTree.add(62);
        // myTree.add(95);
        // myTree.add(7);
        // myTree.add(21);
        // myTree.add(47);
        // myTree.add(88);
        // myTree.add(119);
        //System.out.println(myTree.getRoot().getLeft());
        System.out.println(myTree.numOfExternal());
    }
}
