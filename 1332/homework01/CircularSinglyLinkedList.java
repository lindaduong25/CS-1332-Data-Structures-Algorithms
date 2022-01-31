import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Linda Duong
 * @version 1.0
 * @userid lduong8
 * @GTID 903568287
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                "Index is either less than 0 or greater than the size which is an invalid input.");
        } else if (data == null) {
            throw new IllegalArgumentException("The data is null and therefore cannot be added.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            CircularSinglyLinkedListNode<T> current = head;
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data);
            // 1, 2, 3, 4 -> 1 (add 5 at index 2) so should be 1, 2, 5, 3, 4
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null therefore cannot be added.");
        }
        if (size == 0) {
            head = new CircularSinglyLinkedListNode<T>(data);
            head.setNext(head);
        }
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(head.getData());
        if (size == 1) {
            head.setNext(newNode);
            head.setData(data);
            newNode.setNext(head);
        } else {
            newNode.setNext(head.getNext());
            head.setData(data);
            head.setNext(newNode);
        }
        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addToFront(data);
        head = head.getNext();
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        T removedData;
        if (isEmpty()) { // if size is 0 then it would be empty
            throw new IndexOutOfBoundsException("The array is empty");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index is out of bounds because the index inputted was either less than 0 or greater than the size.");
        }
        if (index == 0) {
            removedData = removeFromFront();
        } else if (index == size - 1) {
            removedData = removeFromBack();
        } else {
            CircularSinglyLinkedListNode<T> current = head;
            int num = 0;
            while (current.getNext() != head && num < index - 1) {
                current = current.getNext();
                num++;
            }
            removedData = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            size--;
        }
        return removedData;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty therefore there is nothing to remove from the front.");
        }
        T removedData = head.getData();
        if (size == 1) {
            head = null;
        } else {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        }
        size--;
        return removedData;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        T removedData;
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty therefore there is nothing to remove from the back.");
        }
        if (size == 1) {
            removedData = head.getData();
            head = null;
            size--;
            return removedData;
        }
        CircularSinglyLinkedListNode<T> current = head;
        // 1, 2 -> 1 should be 1, 2
        while (current.getNext().getNext() != head) {
            current = current.getNext();
        }
        removedData = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        size--;
        return removedData;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The array is empty");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index out of bounds because the index inputted is either less than 0 or grater than the size.");
        } else if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The target data is null.");
        }
        if (isEmpty()) { // if size == 0
            throw new NoSuchElementException("The array is empty");
        }
        if (size == 1) {
            if (head.getData().equals(data)) {
                T removedData = head.getData();
                head = null;
                size--;
                return removedData;
            } else {
                throw new NoSuchElementException("The target data was not found in the list.");
            }
        }
        CircularSinglyLinkedListNode<T> current = head;
        CircularSinglyLinkedListNode<T> marker = null;
        // 1, 2, 3 -> 1
        // 1, 2, - > 1
        while (current.getNext() != head) {
            if (current.getNext().getData().equals(data)) {
                marker = current;
            }
            current = current.getNext();
        }
        T removedData = null;
        if (marker == null && head.getData().equals(data)) {
            removedData = head.getData();
            removeFromFront();
        }
        if (marker != null) {
            removedData = marker.getNext().getData();
            marker.setNext(marker.getNext().getNext());
            size--;
        }
        if (removedData == null) {
            throw new NoSuchElementException("The target data was not found in the list.");
        }
        return removedData;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] anArray = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> current = head;
        if (isEmpty()) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            anArray[i] = current.getData();
            current = current.getNext();
        }
        for (T item : anArray) {
            System.out.println(item);
        }
        return anArray;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    // public static void main(String[] args) {
    //     CircularSinglyLinkedList<String> listy = new CircularSinglyLinkedList<>();
    //     //listy.addToFront(2);
    //     // listy.addToFront(2);
    //     // listy.addToFront(2);
    //     // listy.addToFront(2);
    //     // listy.addToFront(2);
    //     // listy.addToFront(2);
    //     // listy.addToBack(3);
    //     // listy.addToBack(3);
    //     // listy.addToFront(3);
    //     //listy.removeAtIndex(0);
    //     // listy.toArray();
    //     // listy.removeLastOccurrence(2);
    //     // System.out.println(listy.getHead());
    //     // System.out.println(listy.getHead().getNext());
    //     //System.out.println(listy.getHead().getNext().getNext());
    //     //System.out.println(listy.getHead().getNext().getNext().getNext());

    //     listy.addToBack("A");
    //     //listy.removeLastOccurrence("B");
    //     //listy.addAtIndex(0, "A");
    //     //listy.get(0);
    //     System.out.println(listy.getHead().getNext());
    //     System.out.println(listy.size);
    // }
}