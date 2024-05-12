/**
 * MyLinkedList.java
 * Name: William Lin
 * PID: A17503383
 * Email: xil211@ucsd.edu
 * 
 * This file aims to use the Node class to perform
 * operations within doubly-linked lists in the
 * MyLinkedList class.
 * 
 * 3 fields are defined in this class:
 * 
 * size: to find the length of the linked list
 * head: to act as a dummy node for the start of the list
 * tail: to act as a dummy node for the end of the list
 * 
 */

import java.util.AbstractList;      

/**
 * a linked list class that performs the basic operations within a doubly-linked list
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**     
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    /**
     * constructor for MyLinkedList class
     * 
     * sets this.head and this.tail as dummy nodes
     */
    public MyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.size = 0;
    }

    /**
     * method to find the length of the doubly-linked list
     * @return this list's length
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * method to find a certain value or element at 
     * the specified index
     * @param index specified index
     * @return this specified node's value
     * @throws exception
     */
    @Override
    public E get(int index) {
        //throws an exception if index is not within the current list
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        /**
         * creates a temporary Node to store the value 
         * from the specified index
         */
        Node currentNode = getNth(index);
        return currentNode.getElement();
    }

    /**
     * method to add a specified data at the specified index
     * @param index specified index
     * @param data specified data
     * @throws exception
     */
    @Override
    public void add(int index, E data) {

        //throws an exception if there is no data to be added
        if (data == null) {
            throw new NullPointerException();
        }

        //throws an exception if index is not within the current list
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        //create a new Node with the specified data
        Node newNode = new Node(data);

        /**
         * inserts the Node depending on whether the list is empty, if the index is 
         * at the end of the list, or if the index is in the middle of the list
         */
        if (this.size == 0) {
            this.head.setNext(newNode);
            newNode.setPrev(this.head);
            this.tail.setPrev(newNode);
            newNode.setNext(this.tail);
        } else if (index == this.size) {
            this.tail.getPrev().setNext(newNode);
            newNode.setPrev(this.tail.getPrev());
            this.tail.setPrev(newNode);
            newNode.setNext(this.tail);
        } else {
            Node currentNode = getNth(index);
            currentNode.getPrev().setNext(newNode);
            newNode.setPrev(currentNode.getPrev());
            currentNode.setPrev(newNode);
            newNode.setNext(currentNode);
        }

        //update the size 
        this.size++;
    }

    /**
     * method to add a specified data at the end of the list
     * @param data specified data
     * @throws exception
     * @return if add was successful
     */
    @Override
    public boolean add(E data) {

        //throws an exception if there is no data to be added
        if (data == null) {
            throw new NullPointerException();
        }

        //create a new Node with the specified data
        Node newNode = new Node(data);

        /**
         * inserts node at the end of the list and updates pointers
         * if the list is originally empty, set head and tail pointers
         * to point at the added node
         */
        if (this.size == 0) {
            this.head.setNext(newNode);
            newNode.setPrev(this.head);
            this.tail.setPrev(newNode);
            newNode.setNext(this.tail);
        } else {
            this.tail.getPrev().setNext(newNode);
            newNode.setPrev(this.tail.getPrev());
            this.tail.setPrev(newNode);
            newNode.setNext(this.tail);
        }

        //updates size
        this.size++;
        return true;

    }


    /**
     * method to replace specified data at 
     * the specified index
     * @param index specified index
     * @param data specified data
     * @throws exception
     * @return deleted/previous data
     */
    @Override
    public E set(int index, E data) {

        //throws an exception if there is no data to be added
        if (data == null) {
            throw new NullPointerException();
        }

        //throws an exception if index is not within the current list
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        /**
         * create a temporary node with the previous data
         * and replace the previous Node with the new data
         */
        Node currentData = getNth(index);
        E previousData = currentData.getElement();
        currentData.setElement(data);

        //return the value on the previous node
        return previousData;

    }

    /**
     * method to remove a node from a specified index
     * @param index specified index
     * @throws exception
     * @return the value within the node removed
     */
    @Override
    public E remove(int index) {

        //throws an exception if index is not within the current list
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        //create a temporary node to store the removed data
        Node currentData = getNth(index);
        E removedData = currentData.getElement();

        //links the node before and after the removed node together
        //update size
        currentData.getPrev().setNext(currentData.getNext());
        currentData.getNext().setPrev(currentData.getPrev());
        size--;

        //return the value contained by the removed node
        return removedData;

    }

    /**
     * method to clear the entire linked list
     */
    @Override
    public void clear() {
        
        /**
         * resets the pointers from the head and tail,
         * removing the all non-dummy nodes, and
         * resetting the size to 0
         */
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.size = 0;
    }

    /**
     * method to check if the list is empty
     * @return whether the list is empty
     */
    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * method to find the Node at specified index
     * @param index specified index
     * @return the node at specified index
     * @throws exception
     */
    protected Node getNth(int index) {
        //throws exception if index is outside of the list size
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        //create a dummy node to store the wanted node
        Node currentNode = this.head.getNext();

        //use a for loop to locate the wanted node at the specified index
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        //return wanted node
        return currentNode;
    }  

    /**
     * method to check if a specific data exists between a start and end index
     * 
     * @param elem specified data
     * @param start start index
     * @param end end index
     * @return whether or not the specified index exists between the indices
     * @throws exception
     */
    public boolean contains(E elem, int start, int end) {

        //checks if the indices are valid
        if (start < 0 || start >= this.size || end < 0 || end > this.size) {
            throw new IndexOutOfBoundsException();
        }

        //dummy node prior to scanning
        Node currentNode = getNth(start);

        //for loop to scan between the two specified indices
        for (int i = start; i < end; i++) {
            if (currentNode.getElement().equals(elem)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    
    }
}


