package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {
	private ArrayList al;
	public void setup1() {
		al = new ArrayList();
		for(long i=0;i<=30;i++) {
			al.add(i);
		}
	}
	@Test
	public void addTest() {
		setup1();
		assertEquals("The value 0 has not been created", true, al.iterativeSearch(0));
		assertEquals("The value 15 has not been created", true, al.iterativeSearch(15));
		assertEquals("The value 30 has not been created", true, al.iterativeSearch(30));
	}
	@Test
	public void recursiveSearchTest() {
		setup1();
		assertEquals("The value 3 does not exist", true, al.recursiveSearch(3));
		assertEquals("The value 13 does not exist", true, al.recursiveSearch(13));
		assertEquals("The value 23 does not exist", true, al.recursiveSearch(23));
	}
	@Test
	public void recursiveDeleteTest() {
		setup1();
		assertEquals("The value 3 does not exist", true, al.recursiveDelete(3));
		assertEquals("The value 13 does not exist", true, al.recursiveDelete(13));
		assertEquals("The value 23 does not exist", true, al.recursiveDelete(23));
		assertEquals("The value 3 has not been deleted", false, al.recursiveSearch(3));
		assertEquals("The value 13 has not been deleted", false, al.recursiveSearch(13));
		assertEquals("The value 23 has not been deleted", false, al.recursiveSearch(23));
	}
	@Test
	public void iterativeSearchTest() {
		setup1();
		assertEquals("The value 6 does not exist", true, al.iterativeSearch(6));
		assertEquals("The value 16 does not exist", true, al.iterativeSearch(16));
		assertEquals("The value 26 does not exist", true, al.iterativeSearch(26));
	}
	@Test
	public void iterativeDeleteTest() {
		setup1();
		assertEquals("The value 6 does not exist", true, al.iterativeDelete(6));
		assertEquals("The value 16 does not exist", true, al.iterativeDelete(16));
		assertEquals("The value 26 does not exist", true, al.iterativeDelete(26));
		assertEquals("The value 6 has not been deleted", false, al.iterativeSearch(6));
		assertEquals("The value 16 has not been deleted", false, al.iterativeSearch(16));
		assertEquals("The value 26 has not been deleted", false, al.iterativeSearch(26));
	}
}
