import java.util.*;
// Creation of the "Node" object. Each character of the text will be associated to a Node

public class Node {
	private int frequency;
	private char charac = '@';
	private Node leftSon =null;
	private Node rightSon= null;
	private String binaryCode ="";

	
	public Node(int freq, char charac, Node leftSon, Node rightSon, String codebin) {
		super();
		this.frequency = frequency;
		this.charac = charac;
		this.leftSon=leftSon;
		this.rightSon = rightSon;
		this.binaryCode = binaryCode;
	}
		
	// Getters and Setters

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public char getCharac() {
		return charac;
	}

	public void setCharac(char charac) {
		this.charac = charac;
	}

	public Node getLeftSon() {
		return leftSon;
	}

	public void setLeftSon(Node leftSon) {
		this.leftSon = leftSon;
	}

	public Node getRightSon() {
		return rightSon;
	}

	public void setRightSon(Node rightSon) {
		this.rightSon = rightSon;
	}

	public String getBinaryCode() {
		return binaryCode;
	}

	public void setBinaryCode(String binaryCode) {
		this.binaryCode = binaryCode;
	}
	
	@Override
	
	// Return all the information about the node
	public String toString() {
		return "Node(charac= " + this.charac + ", frequency= "+this.frequency+ ", leftSon= "+this.leftSon +", rightSon= "
				+this.rightSon +", binaryCode= "+ this.binaryCode +")";
	}
	
	//if the node doesn't have any son, it's a leaf, this method will be usefull during the creation of the tree.
	public boolean isLeaf() {
		return(this.rightSon == null && this.leftSon == null);
	}
}
