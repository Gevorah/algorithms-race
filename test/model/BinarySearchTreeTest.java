package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTreeTest {
	private BinarySearchTree bst;
	public void setup1() {
		bst = new BinarySearchTree();
		bst.recursiveAdd(16);
		bst.recursiveAdd(6);
		bst.recursiveAdd(9);
		bst.recursiveAdd(23);
		bst.recursiveAdd(26);
		bst.recursiveAdd(30);
		bst.recursiveAdd(1);
		bst.recursiveAdd(3);
		bst.recursiveAdd(29);
	}
	public void setup2() {
		bst = new BinarySearchTree();
		bst.iterativeAdd(16);
		bst.iterativeAdd(6);
		bst.iterativeAdd(9);
		bst.iterativeAdd(23);
		bst.iterativeAdd(26);
		bst.iterativeAdd(30);
		bst.iterativeAdd(1);
		bst.iterativeAdd(3);
		bst.iterativeAdd(29);
	}
	@Test
	public void recursiveAddTest() {
		setup1();
		assertEquals("The value 23 does not exist", true, bst.recursiveSearch(23));
		assertEquals("The value 16 does not exist", true, bst.recursiveSearch(16));
		assertEquals("The value 1 does not exist", true, bst.recursiveSearch(1));
	}
	@Test
	public void recursiveSearchTest() {
		setup1();
		assertEquals("The value 3 does not exist", true, bst.recursiveSearch(3));
		assertEquals("The value 23 does not exist", true, bst.recursiveSearch(23));
		assertEquals("The value 13 exist", false, bst.recursiveSearch(13));
		assertEquals("The value 0 exist", false, bst.recursiveSearch(0));
	}
	@Test
	public void recursiveDeleteTest() {
		setup1();
		bst.recursiveDelete(3);
		bst.recursiveDelete(16);
		bst.recursiveDelete(29);
		assertEquals("The value 3 has not been deleted", false, bst.recursiveSearch(3));
		assertEquals("The value 16 has not been deleted", false, bst.recursiveSearch(16));
		assertEquals("The value 29 has not been deleted", false, bst.recursiveSearch(29));
	}
	@Test
	public void iterativeAddTest() {
		setup2();
		assertEquals("The value 23 does not exist", true, bst.iterativeSearch(23));
		assertEquals("The value 16 does not exist", true, bst.iterativeSearch(16));
		assertEquals("The value 1 does not exist", true, bst.iterativeSearch(1));
	}
	@Test
	public void iterativeSearchTest() {
		setup2();
		assertEquals("The value 3 does not exist", true, bst.iterativeSearch(3));
		assertEquals("The value 23 does not exist", true, bst.iterativeSearch(23));
		assertEquals("The value 13 does not exist", false, bst.iterativeSearch(13));
		assertEquals("The value 0 does not exist", false, bst.iterativeSearch(0));
	}
	@Test
	public void iterativeDeleteTest() {
		setup2();
		assertEquals("The value 3 does not exist", true, bst.iterativeDelete(3));
		assertEquals("The value 16 does not exist", true, bst.iterativeDelete(16));
		assertEquals("The value 29 does not exist", true, bst.iterativeDelete(29));
		assertEquals("The value 3 has not been deleted", false, bst.iterativeSearch(3));
		assertEquals("The value 16 has not been deleted", false, bst.iterativeSearch(16));
		assertEquals("The value 29 has not been deleted", false, bst.iterativeSearch(29));
	}
}
