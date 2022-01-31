import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Passing these tests doesn't guarantee any grade on the assignment.
 *
 * Some JUnit versions seem to have a problem with assertThrows.
 * I tested my code successfully using JUnit 4.13. If you cannot
 * get it to work for your version, remove the assertThrows lines
 * and you should be fine.
 *
 * @author Prakhar Mittal
 * @author CS 1332 TAs
 * @version 1.0
 */
public class BSTTest {

    private static final int TIMEOUT = 200;
    private BST<Integer> tree;

    @Before
    public void setup() {
        tree = new BST<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorNullData() {
        assertThrows(IllegalArgumentException.class, () -> {
            tree = new BST<>(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorNullElement() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(null);
        data.add(2);

        assertThrows(IllegalArgumentException.class, () -> {
            tree = new BST<>(data);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorValid() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(5);
        data.add(2);
        data.add(7);
        data.add(1);
        data.add(3);
        data.add(6);
        data.add(8);
        data.add(4);
        data.add(9);
        data.add(10);

        tree = new BST<>(data);
        assertEquals(10, tree.size());

        assertEquals((Integer) 5, tree.getRoot().getData());
        assertEquals((Integer) 2, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 7, tree.getRoot().getRight().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, tree.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 4, tree.getRoot().getLeft().getRight().getRight().getData());
        assertEquals((Integer) 6, tree.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 8, tree.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 9, tree.getRoot().getRight().getRight().getRight().getData());
        assertEquals((Integer) 10, tree.getRoot().getRight().getRight().getRight().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddNullData() {
        assertThrows(IllegalArgumentException.class, () -> {
            tree.add(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        tree.add(6);
        assertEquals(1, tree.size());

        tree.add(6);
        assertEquals(1, tree.size());

        assertEquals((Integer) 6, tree.getRoot().getData());
        assertNull(tree.getRoot().getLeft());
        assertNull(tree.getRoot().getRight());

        tree.add(3);
        tree.add(8);
        tree.add(2);
        tree.add(4);
        tree.add(9);
        tree.add(5);
        assertEquals(7, tree.size());

        tree.add(9);
        assertEquals(7, tree.size());

        assertEquals((Integer) 6, tree.getRoot().getData());
        assertEquals((Integer) 3, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 8, tree.getRoot().getRight().getData());
        assertEquals((Integer) 2, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 4, tree.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 9, tree.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 5, tree.getRoot().getLeft().getRight().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveDataNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            tree.remove(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            tree.remove(5);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSizeOneNotFound() {
        tree.add(6);
        assertEquals(1, tree.size());

        assertThrows(NoSuchElementException.class, () -> {
            tree.remove(5);
        });

        assertEquals(1, tree.size());
        assertEquals((Integer) 6, tree.getRoot().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSizeOneFound() {
        Integer i = new Integer(129);

        tree.add(i);
        assertEquals(1, tree.size());

        assertSame(i, tree.remove(129));
        assertEquals(0, tree.size());

        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeaf() {
        Integer i = new Integer(170);
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(11);
        tree.add(16);
        tree.add(4);
        tree.add(6);
        tree.add(i);
        assertEquals(10, tree.size());

        assertSame(i, tree.remove(170));
        assertEquals(9, tree.size());

        assertEquals((Integer) 10, tree.getRoot().getData());
        assertEquals((Integer) 5, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 15, tree.getRoot().getRight().getData());
        assertEquals((Integer) 3, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 7, tree.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 11, tree.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 16, tree.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 4, tree.getRoot().getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 6, tree.getRoot().getLeft().getRight().getLeft().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveOneChild() {
        Integer i = new Integer(160);
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(11);
        tree.add(i);
        tree.add(170);
        tree.add(180);
        assertEquals(9, tree.size());

        assertSame(i, tree.remove(160));
        assertEquals(8, tree.size());

        assertEquals((Integer) 10, tree.getRoot().getData());
        assertEquals((Integer) 5, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 15, tree.getRoot().getRight().getData());
        assertEquals((Integer) 3, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 7, tree.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 11, tree.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 170, tree.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 180, tree.getRoot().getRight().getRight().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChildren() {
        Integer i = new Integer(250);
        Integer j = new Integer(210);
        Integer k = new Integer(150);
        tree.add(j);
        tree.add(k);
        tree.add(i);
        tree.add(30);
        tree.add(170);
        tree.add(220);
        tree.add(260);
        tree.add(270);
        tree.add(280);
        assertEquals(9, tree.size());

        assertSame(i, tree.remove(250));
        assertEquals(8, tree.size());

        assertEquals((Integer) 210, tree.getRoot().getData());
        assertEquals((Integer) 150, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 260, tree.getRoot().getRight().getData());
        assertEquals((Integer) 30, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 170, tree.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 220, tree.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 270, tree.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 280, tree.getRoot().getRight().getRight().getRight().getData());

        assertSame(j, tree.remove(210));
        assertEquals(7, tree.size());

        assertEquals((Integer) 220, tree.getRoot().getData());
        assertEquals((Integer) 150, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 260, tree.getRoot().getRight().getData());
        assertEquals((Integer) 30, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 170, tree.getRoot().getLeft().getRight().getData());
        assertNull(tree.getRoot().getRight().getLeft());
        assertEquals((Integer) 270, tree.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 280, tree.getRoot().getRight().getRight().getRight().getData());

        assertSame(k, tree.remove(150));
        assertEquals(6, tree.size());

        assertEquals((Integer) 220, tree.getRoot().getData());
        assertEquals((Integer) 170, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 260, tree.getRoot().getRight().getData());
        assertEquals((Integer) 30, tree.getRoot().getLeft().getLeft().getData());
        assertNull(tree.getRoot().getLeft().getRight());
        assertEquals((Integer) 270, tree.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 280, tree.getRoot().getRight().getRight().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testGetDataNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            tree.get(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testGetEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            tree.get(5);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer i = 600;
        Integer j = 300;
        Integer k = 800;
        Integer l = 200;
        Integer m = 400;
        Integer n = 900;

        tree.add(i);
        tree.add(j);
        tree.add(k);
        tree.add(l);
        tree.add(m);
        tree.add(n);
        assertEquals(6, tree.size());

        assertSame(i, tree.get(600));
        assertSame(j, tree.get(300));
        assertSame(k, tree.get(800));
        assertSame(l, tree.get(200));
        assertSame(m, tree.get(400));
        assertSame(n, tree.get(900));

        assertThrows(NoSuchElementException.class, () -> {
            tree.get(1000);
        });

        assertThrows(NoSuchElementException.class, () -> {
            tree.get(-1000);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testContainsDataNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            tree.contains(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        tree.add(6);
        tree.add(3);
        tree.add(8);
        tree.add(2);
        tree.add(4);
        tree.add(9);
        assertEquals(6, tree.size());

        assertTrue(tree.contains(6));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(9));

        assertFalse(tree.contains(1));
        assertFalse(tree.contains(5));
        assertFalse(tree.contains(10));
    }

    @Test(timeout = TIMEOUT)
    public void testTraversalsEmpty() {
        List<Integer> output = new ArrayList<>();

        assertEquals(output, tree.preorder());
        assertEquals(output, tree.inorder());
        assertEquals(output, tree.postorder());
        assertEquals(output, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> preorder = new ArrayList<>();
        preorder.add(3);
        preorder.add(0);
        preorder.add(1);
        preorder.add(2);
        preorder.add(8);
        preorder.add(4);
        preorder.add(6);
        preorder.add(5);
        preorder.add(7);

        assertEquals(preorder, tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> inorder = new ArrayList<>();
        inorder.add(0);
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);
        inorder.add(8);

        assertEquals(inorder, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> postorder = new ArrayList<>();
        postorder.add(2);
        postorder.add(1);
        postorder.add(0);
        postorder.add(5);
        postorder.add(7);
        postorder.add(6);
        postorder.add(4);
        postorder.add(8);
        postorder.add(3);

        assertEquals(postorder, tree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(3);
        levelorder.add(0);
        levelorder.add(8);
        levelorder.add(1);
        levelorder.add(4);
        levelorder.add(2);
        levelorder.add(6);
        levelorder.add(5);
        levelorder.add(7);

        assertEquals(levelorder, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(-1, tree.height());

        tree.add(6);
        assertEquals(1, tree.size());
        assertEquals(0, tree.height());

        tree.add(3);
        assertEquals(2, tree.size());
        assertEquals(1, tree.height());

        tree.add(8);
        assertEquals(3, tree.size());
        assertEquals(1, tree.height());

        tree.add(10);
        assertEquals(4, tree.size());
        assertEquals(2, tree.height());

        tree.add(2);
        assertEquals(5, tree.size());
        assertEquals(2, tree.height());

        tree.add(4);
        assertEquals(6, tree.size());
        assertEquals(2, tree.height());

        tree.add(1);
        assertEquals(7, tree.size());
        assertEquals(3, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        tree.add(2);
        tree.add(0);
        tree.add(1);
        assertEquals(3, tree.size());

        tree.clear();
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testIsBST() {
        BSTNode<Integer> root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(25));
        root.setRight(new BSTNode<>(75));
        root.getLeft().setLeft(new BSTNode<>(12));
        root.getLeft().setRight(new BSTNode<>(37));
        root.getLeft().getLeft().setLeft(new BSTNode<>(10));
        root.getLeft().getLeft().setRight(new BSTNode<>(15));
        root.getLeft().getLeft().getRight().setLeft(new BSTNode<>(13));
        root.getLeft().getRight().setRight(new BSTNode<>(40));

        assertTrue(tree.isBST(root));

        root = new BSTNode<>(20);
        root.setLeft(new BSTNode<>(15));
        root.setRight(new BSTNode<>(38));
        root.getLeft().setLeft(new BSTNode<>(6));
        root.getRight().setRight(new BSTNode<>(50));
        root.getLeft().getLeft().setRight(new BSTNode<>(21));

        assertFalse(tree.isBST(root));
    }
}
