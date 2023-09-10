import org.junit.*;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class TestM {
	/*
        4.b.1
     */
	@Test
	public void testNodeCoverageCase1() {
		//arg length = 0
		//i = 1
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M m = new M();
		m.m("", 1);

		assertEquals("zero" + System.lineSeparator(), outContent.toString());
		// This will cover nodes: n1, n3, n4, n8, n10, n11,
	}

	@Test
	public void testNodeCoverageCase2() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M m = new M();
		m.m("a", 1);

		assertEquals("a" + System.lineSeparator(), outContent.toString());
		// This will cover nodes: n1, n3, n5, n8, n9, n11
	}

	@Test
	public void testNodeCoverageCase3() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M m = new M();
		m.m("ab", 1);

		assertEquals("b" + System.lineSeparator(), outContent.toString());
		// This will cover nodes: n1, n3, n6, n7, n8, n9, n11
	}

	@Test
	public void testNodeCoverageCase4() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M m = new M();
		m.m("ab", 0);

		assertEquals("b" + System.lineSeparator(), outContent.toString());
		// This will cover nodes: n1, n2, n3, n6, n7, n8, n9, n11
	}



	/*
    4.b.2
     */
	@Test
    /*
    testPath1 covers the case when arg is an empty string and i is 0,
    thereby traversing the edges [1, 2], [2, 3], [3, 4], [4, 8], [8, 10], [10, 11]
     */
	public void testEdgePath1() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("", 0);

		String expectedOutput = "zero\n";
		assertEquals("Test failed: Expected output 'zero', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		// This test covers edges: [1, 2], [2, 3], [3, 4], [4, 8], [8, 10], [10, 11]
	}

	@Test
    /*
    testPath2 covers the case when arg is a string of length 1 and i is 1, thus
    covering edges: [1, 3], [3, 5], [5, 8], [8, 9], [9, 11]
    */
	public void testEdgePath2() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("a", 1);

		String expectedOutput = "a\n";
		assertEquals("Test failed: Expected output 'b', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		// This test covers edges: [1, 2], [2, 3], [3, 5], [5, 8], [8, 10], [10, 11]
	}

	@Test
	public void testEdgePath3() {
        /*
        testPath3 covers the case when arg is a string of length 2 and i is 1,
        resulting in the traversal of the edges
        [1, 3], [3, 6], [6, 7], [7, 8], [8, 9], [9, 11]
         */
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("ab", 1);

		String expectedOutput = "b\n";
		assertEquals("Test failed: Expected output 'b', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		// This test covers edges: [1, 3], [3, 6], [6, 7], [7, 8], [8, 9], [9, 11]
	}

	@Test
	public void testEdgePath4() {
        /*
        testPath4 covers the case when arg has a length greater than 2 and i is 1,
        which covers the edges [1, 3], [3, 7], [7, 8], [8, 9], [9, 11]
         */
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("abc", 1);

		String expectedOutput = "b\n";
		assertEquals("Test failed: Expected output 'b', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		// This test covers edges: [1, 2], [2, 3], [3, 7], [7, 8], [8, 10], [10, 11]
	}

	/*
        Prime Path Coverage

     */
	@Test
	public void testPrimePath1() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("ab", 0);

		String expectedOutput = "b\n";
		assertEquals("Test failed: Expected output 'b', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		//Path [1,2,3,6,7,8,9,11]
	}

	@Test
	public void testPrimePath2() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("a", 0);

		String expectedOutput = "a\n";
		assertEquals("Test failed: Expected output 'a', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		//Test Path: [1,2,3,5,8,9,11]
	}

	@Test
	public void testPrimePath3() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("", 0);

		String expectedOutput = "zero\n";
		assertEquals("Test failed: Expected output 'zero', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		//Test Path: [1,2,3,4,8,10,11],
	}

	@Test
	public void testPrimePath4() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("abc", 0);

		String expectedOutput = "b\n";
		assertEquals("Test failed: Expected output 'b', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		//Test Path: [1,2,3,4,8,10,11],
	}

	@Test
	public void testPrimePath5() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("ab", 0);

		String expectedOutput = "b\n";
		assertEquals("Test failed: Expected output 'b', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		//Test Path: [1,3,6,7,8,9,11]
	}

	@Test
	public void testPrimePath6() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		M mObj = new M();
		mObj.m("", 1);

		String expectedOutput = "zero\n";
		assertEquals("Test failed: Expected output 'zero', but got '" + outContent.toString().trim() + "'", expectedOutput, outContent.toString());

		//Test Path: [1,3,4,8,10,11]
	}
}

class M {
	public static void main(String [] argv){
		M obj = new M();
		if (argv.length > 0)
			obj.m(argv[0], argv.length);
	}

	public void m(String arg, int i) {
		int q = 1;
		A o = null;
		Impossible nothing = new Impossible();
		if (i == 0)
			q = 4;
		q++;
		switch (arg.length()) {
			case 0: q /= 2; break;
			case 1: o = new A(); new B(); q = 25; break;
			case 2: o = new A(); q = q * 100;
			default: o = new B(); break;
		}
		if (arg.length() > 0) {
			o.m();
		} else {
			System.out.println("zero");
		}
		nothing.happened();
	}
}

class A {
	public void m() {
		System.out.println("a");
	}
}

class B extends A {
	public void m() {
		System.out.println("b");
	}
}

class Impossible{
	public void happened() {
		// "2b||!2b?", whatever the answer nothing happens here
	}
}

