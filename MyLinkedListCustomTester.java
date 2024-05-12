/**
 * MyLinkedList.java
 * Name: William Lin
 * PID: A17503383
 * Email: xil211@ucsd.edu
 * 
 * This file aims to use the JUnit test to test MyLinkedList.java
 * 
 * 3 fields are defined in this class:
 * 
 * emptyIntegerList: an empty list for checking methods in empty lists and integer operations
 * threeStringList: a list with three nodes aside from the head and tail dummy nodes to
 * check methods in full lists and String operations
 * strData: a three String arraylist used in the threeStringList
 * 
 */

import static org.junit.Assert.*;

import org.junit.*;

public class MyLinkedListCustomTester {

	// Optional: add test variables here
    private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
    private String[] strData = {"CSE 12", "Paul Cao", "Christine Alvarado"};

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
        emptyIntegerList = new MyLinkedList<Integer>();
        threeStringList = new MyLinkedList<>();
	}

    private void populateLinkedList() {
        MyLinkedList<String>.Node node0 =  
            this.threeStringList.new Node(this.strData[0]);
        MyLinkedList<String>.Node node1 =  
            this.threeStringList.new Node(this.strData[1]);
        MyLinkedList<String>.Node node2 =  
            this.threeStringList.new Node(this.strData[2]);

        this.threeStringList.head.next = node0;
        node0.prev = this.threeStringList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = this.threeStringList.tail;
        this.threeStringList.tail.prev = node2;
        this.threeStringList.size = 3;
    }

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		this.populateLinkedList();

		assertEquals(true,emptyIntegerList.add(1));
		assertEquals(Integer.valueOf(1), emptyIntegerList.get(0));
		assertEquals(1, emptyIntegerList.size());
		assertEquals(true, emptyIntegerList.add(2));
		assertEquals(Integer.valueOf(1), emptyIntegerList.get(0));
		assertEquals(Integer.valueOf(2), emptyIntegerList.get(1));
		assertEquals(2, emptyIntegerList.size());

		assertEquals(true, threeStringList.add("Joe Biden"));
		assertEquals(String.valueOf("Joe Biden"), 
		threeStringList.get(3));
		assertEquals(4, threeStringList.size());

		assertThrows(NullPointerException.class, 
		() -> threeStringList.add(null));

	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		// TODO: add your test here

		emptyIntegerList.add(0, 1);
		emptyIntegerList.add(1, 2);
		emptyIntegerList.add(2,3);

		assertEquals(Integer.valueOf(1), emptyIntegerList.get(0));
		assertEquals(Integer.valueOf(2), emptyIntegerList.get(1));
		assertEquals(Integer.valueOf(3), emptyIntegerList.get(2));
		
		emptyIntegerList.add(0, 0);

		assertEquals(Integer.valueOf(0), emptyIntegerList.get(0));
		assertEquals(Integer.valueOf(1), emptyIntegerList.get(1));
		assertEquals(Integer.valueOf(2), emptyIntegerList.get(2));
		assertEquals(Integer.valueOf(3), emptyIntegerList.get(3));
		assertEquals(4,emptyIntegerList.size());

		this.populateLinkedList();

		threeStringList.add(0,"Makima");

		assertEquals(String.valueOf("Makima"), threeStringList.get(0));
		assertEquals(4, threeStringList.size());

		assertThrows(NullPointerException.class, 
		() -> threeStringList.add(0,null));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.add(5,"games"));

	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		// TODO: add your test here
		emptyIntegerList.add(0, 1);
		emptyIntegerList.add(1, 2);
		emptyIntegerList.add(2,3);

		assertEquals(Integer.valueOf(1), emptyIntegerList.get(0));
		assertEquals(Integer.valueOf(2), emptyIntegerList.get(1));
		assertEquals(Integer.valueOf(3), emptyIntegerList.get(2));
		
		emptyIntegerList.add(1, 0);

		assertEquals(Integer.valueOf(1), emptyIntegerList.get(0));
		assertEquals(Integer.valueOf(0), emptyIntegerList.get(1));
		assertEquals(Integer.valueOf(2), emptyIntegerList.get(2));
		assertEquals(Integer.valueOf(3), emptyIntegerList.get(3));
		assertEquals(4,emptyIntegerList.size());

		this.populateLinkedList();

		threeStringList.add(1,"Makima");

		assertEquals(String.valueOf("CSE 12"), threeStringList.get(0));
		assertEquals(String.valueOf("Makima"), threeStringList.get(1));
		assertEquals(4, threeStringList.size());

		assertThrows(NullPointerException.class, 
		() -> threeStringList.add(0,null));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.add(5,"games"));

	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
		assertThrows(IndexOutOfBoundsException.class, 
		() -> emptyIntegerList.remove(0));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> emptyIntegerList.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> emptyIntegerList.remove(1));
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		// TODO: add your test here
		this.populateLinkedList();

		assertEquals(String.valueOf("Paul Cao"), threeStringList.remove(1));

		assertEquals(String.valueOf("CSE 12"), 
		threeStringList.get(0));
		assertEquals(String.valueOf("CSE 12"), 
		threeStringList.head.getNext().getElement());
		assertEquals(String.valueOf("Christine Alvarado"), 
		threeStringList.get(1));
		assertEquals(String.valueOf("Christine Alvarado"), 
		threeStringList.tail.getPrev().getElement());
		assertEquals(2, threeStringList.size());

		assertEquals(String.valueOf("Christine Alvarado"), 
		threeStringList.remove(1));
		assertEquals(String.valueOf("CSE 12"), 
		threeStringList.get(0));
		assertEquals(String.valueOf("CSE 12"), 
		threeStringList.head.getNext().getElement());
		assertEquals(String.valueOf("CSE 12"), 
		threeStringList.tail.getPrev().getElement());
		assertEquals(1, threeStringList.size());



	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		// TODO: add your test here

		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.set(-1,"James"));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.set(3,"James"));

	}

	/**
	 * Aims to test the contains(E data, int start, int end) method.
	 * Data argument exists in the list but outside the given range. 
	 */
	@Test
	public void testCustomContainsExistsOutOfRange() {
		// TODO: add your test here

		this.populateLinkedList();

		assertEquals(false,threeStringList.contains("Christine Alvarado", 0,2));

		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.contains("Paul Cao", -10, 0));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.contains("Paul Cao", 10, 0));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.contains("Paul Cao", 0, -10));
		assertThrows(IndexOutOfBoundsException.class, 
		() -> threeStringList.contains("Paul Cao", 0, 10));
		

	}
}
