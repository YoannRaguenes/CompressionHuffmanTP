import java.io.*;
import java.util.*;
import java.awt.*;


public class Main {
	public static void main(String[] args) throws IOException {
		TxtFile alice = new TxtFile("C:/alice.txt");
		System.out.println(alice.openFile("C:/alice.txt"));
		alice.Alphabet();
		alice.SortedAlphabet();
		System.out.println(alice.nodeList());
		Node n1=alice.createTree();
		alice.coding(n1, "");
		System.out.println(alice.getHmap());
		//alice.codeFile("C:\\");
		
		
		
}
	}
