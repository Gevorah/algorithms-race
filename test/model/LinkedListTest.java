package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	private LinkedList ll;
	public void setup1() {
		ll = new LinkedList();
		for(long i=0;i<=30;i++) {
			ll.recursiveAdd(i);
		}
	}
	public void setup2() {
		ll = new LinkedList();
		for(long i=0;i<=30;i++) {
			ll.iterativeAdd(i);
		}
	}
	@Test
	public void recursiveAddTest() {
		setup1();
		assertEquals("The value 0 does not exist", true, ll.recursiveSearch(0));
		assertEquals("The value 15 does not exist", true, ll.recursiveSearch(15));
		assertEquals("The value 30 does not exist", true, ll.recursiveSearch(30));
	}
	@Test
	public void recursiveSearchTest() {
		setup1();
		assertEquals("The value 3 does not exist", true, ll.recursiveSearch(3));
		assertEquals("The value 13 does not exist", true, ll.recursiveSearch(13));
		assertEquals("The value 23 does not exist", true, ll.recursiveSearch(23));
	}
	@Test
	public void recursiveDeleteTest() {
		setup1();
		ll.recursiveDelete(3);
		ll.recursiveDelete(13);
		ll.recursiveDelete(23);
		assertEquals("The value 3 has not been deleted", false, ll.recursiveSearch(3));
		assertEquals("The value 13 has not been deleted", false, ll.recursiveSearch(13));
		assertEquals("The value 23 has not been deleted", false, ll.recursiveSearch(23));
	}
	@Test
	public void iterativeAddTest() {
		setup2();
		assertEquals("The value 0 does not exist", true, ll.iterativeSearch(0));
		assertEquals("The value 15 does not exist", true, ll.iterativeSearch(15));
		assertEquals("The value 30 does not exist", true, ll.iterativeSearch(30));
	}
	@Test
	public void iterativeSearchTest() {
		setup2();
		assertEquals("The value 6 does not exist", true, ll.iterativeSearch(6));
		assertEquals("The value 16 does not exist", true, ll.iterativeSearch(16));
		assertEquals("The value 26 does not exist", true, ll.iterativeSearch(26));
	}
	@Test
	public void iterativeDeleteTest() {
		setup2();
		assertEquals("The value 6 does not exist", true, ll.iterativeDelete(6));
		assertEquals("The value 16 does not exist", true, ll.iterativeDelete(16));
		assertEquals("The value 26 does not exist", true, ll.iterativeDelete(26));
		assertEquals("The value 6 has not been deleted", false, ll.iterativeSearch(6));
		assertEquals("The value 16 has not been deleted", false, ll.iterativeSearch(16));
		assertEquals("The value 26 has not been deleted", false, ll.iterativeSearch(26));
	}
}
