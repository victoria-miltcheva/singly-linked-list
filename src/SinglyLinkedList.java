import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the Singly Linked List data structure. Each node stores
 * a reference to the next node as well as the data stored within it.
 * 
 * @author Victoria Miltcheva
 *
 * @param <T> a generic type.
 */

public class SinglyLinkedList<T> implements Iterable<T> {

  private final String OUT_OF_BOUNDS = "Index does not exist.";
  private Node head;
  private Node tail;
  private int length = 0;

  /**
   * Default constructor. Creates an empty list.
   */
  public SinglyLinkedList() {
    head = tail = null;
  }

  /**
   * Constructs a list consisting of a single node.
   * 
   * @param node data.
   */
  public SinglyLinkedList(T data) {
    tail = head = new Node(data);
    ++length;
  }

  class Node {
    Node next;
    T data;

    Node() {
      next = this;
    }

    Node(T data) {
      next = this;
      this.data = data;
    }
  }

  /** 
   * Implementation of the Iterator class - permits iteration through
   * SinglyLinkedList nodes.
   */
  class SinglyLinkedListIterator implements Iterator<T> {
    SinglyLinkedList<T>.Node node;

    public SinglyLinkedListIterator(SinglyLinkedList<T> list) {
      node = list.getHeadNode();
    }

    /**
     * Indicates whether there is node proceeding the current one.
     * 
     * @return boolean
     */
    @Override
    public boolean hasNext() {
      return node != null;
    }

    /**
     * If a next node exists, return the current node's data and set
     * the current node to the next one.
     * 
     * @return current node's data.
     */
    @Override
    public T next() {
      if (this.hasNext()) {
        T data = node.data;
        node = node.next;
        return data;
      }

      throw new NoSuchElementException();
    }
  }

  /**
   * @return number of nodes in the list.
   */
  public int getLength() {
    return length;
  }

  /*
   * Inserts a new node containing data at a given index.
   */
  public void insert(int index, T data) {
    Node previous;

    if (index == 0) {
      unshift(data);
      return;
    } else if (index == this.length - 1) {
      push(data);
      return;
    }

    try {
      previous = getNode(index - 1);
    } catch (Exception e) {
      throw e;
    }

    Node insertion = new Node(data);
    insertion.next = previous.next;
    previous.next = insertion;
    ++length;
  }

  /**
   * Removes a node at a given index.
   * 
   * @param index at which to remove node.
   */
  public void remove(int index) {
    checkLength();

    if (index == length - 1) {
      pop();
      return;
    } else if (index == 0) {
      shiftNode();
    } else {
      getNode(index).next = null;
      getNode(index - 1).next = getNode(index + 1);
      --length;
    }
  }

  /**
   * @param index at which to get data.
   * @return node data at a given index.
   */
  public T get(int index) {
    return getNode(index).data;
  }

  /**
   * Inserts a new node at the end of the list. If the list is empty,
   * the new node becomes both the tail and head. Otherwise, the new
   * node becomes the tail.
   * 
   * @param data to be pushed.
   */
  public void push(T data) {
    if (length == 0) {
      tail = head = new Node(data);
      ++length;
      return;
    } else if (length == 1) {
      tail = head.next = new Node(data);
      ++length;
      return;
    }

    Node previous = tail;
    tail = previous.next = new Node(data);
    tail.next = null;
    ++length;
  }

  /**
   * Removes the tail from the list and sets the new tail to the previous element.
   * 
   */
  public void pop() {
    checkLength();

    if (length == 1) {
      head = tail = null;
    } else {
      getNode(length - 2).next = null;
      tail = getNode(length - 2);
    }

    --length;
  }

  /**
   * Removes the previous head node.
   * 
   * @return previous head node data.
   */
  public T shift() {
    return shiftNode().data;
  }

  /**
   * Inserts an item at the beginning of the list.
   * 
   * @param data to be unshifted.
   */
  public void unshift(T data) {
    if (length == 0) {
      head = tail = new Node(data);
      return;
    }

    Node previousHead = this.head;
    head = new Node(data);
    head.next = previousHead;
    ++length;
  }

  /**
   * @return The data attribute of the head node.
   */
  public T getHead() {
    return getHeadNode().data;
  }

  /**
   * @return the data attribute of the tail node.
   */
  public T getTail() {
    return getTailNode().data;
  }

  /**
   * Wrapper method for getHead().
   * 
   * @return the data attribute of the head node.
   */
  public T peek() {
    return getHead();
  }

  /**
   * Removes all nodes from the list.
   */
  public void clear() {
    tail = head = null;
    length = 0;
  }

  /**
   * @return boolean indicating if the list is empty.
   */
  public boolean isEmpty() {
    return length == 0;
  }

  /**
   * Used to iterate over nodes with the for-each loop.
   * 
   * @return An iterator.
   */
  @Override
  public Iterator<T> iterator() {
    return new SinglyLinkedListIterator(this);
  }

  /**
   * @return node at a given index.
   */
  private Node getNode(int index) {
    Node current = head;

    for (int i = 0; i < length; ++i) {
      if (index != i) {
        current = current.next;
        continue;
      }

      return current;
    }

    throw new IndexOutOfBoundsException(OUT_OF_BOUNDS);
  }

  /**
   * Removes the previous head node and returns it.
   * 
   * @return the head node which was removed.
   */
  private Node shiftNode() {
    checkLength();

    Node previousHead = head;
    Node temp = head.next;
    head.next = null;
    head = temp;
    --length;

    return previousHead;
  }

  /**
   * Checks that the list is not empty, if it is an exception is thrown.
   * 
   */
  private void checkLength() {
    if (isEmpty()) {
      throw new IndexOutOfBoundsException(OUT_OF_BOUNDS);
    }
  }

  /**
   * @return the head node.
   */
  private Node getHeadNode() {
    return head;
  }

  /**
   * @return the tail node.
   */
  private Node getTailNode() {
    return tail;
  }
}
