import java.io.*;
import java.util.*;
import java.awt.*;


public class TxtFile {
	

	String txt;
	ArrayList<Tuple> tuple = new ArrayList<Tuple>();
	Set<Character> charac = new TreeSet<Character>();
	private ArrayList<Node> NodeList = new ArrayList<Node>();
	HashMap<Character, String> hmap = new HashMap<Character, String>();
	
	

	/*public TxtFile(String txt, ArrayList<Tuple> tuple, Set<Character> charac, ArrayList<Node> nodeList,
			HashMap<Character, String> hmap) {
		super();
		this.txt = txt;
		this.tuple = tuple;
		this.charac = charac;
		NodeList = nodeList;
		this.hmap = hmap;
	}*/
// Getters and Setters
	public TxtFile(String filepath) throws IOException {
		this.txt=this.openFile(filepath);
	}
	
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public ArrayList<Tuple> getTuple() {
		return tuple;
	}
	public void setTuple(ArrayList<Tuple> tuple) {
		this.tuple = tuple;
	}
	public Set<Character> getCharac() {
		return charac;
	}
	public void setCharac(Set<Character> charac) {
		this.charac = charac;
	}
	public ArrayList<Node> getNodeList() {
		return NodeList;
	}
	public void setNodeList(ArrayList<Node> nodeList) {
		NodeList = nodeList;
	}
	public HashMap<Character, String> getHmap() {
		return hmap;
	}
	public void setHmap(HashMap<Character, String> hmap) {
		this.hmap = hmap;
	}
	
	
	
	
	// Open the txt file by using the given filepath
	public String openFile(String filepath) throws IOException {
		String txt="";
		InputStream ips=new FileInputStream(filepath); 
		   InputStreamReader ipsr=new InputStreamReader(ips);
		   BufferedReader br=new BufferedReader(ipsr);
		   String aLine;
		   
		   while ((aLine=br.readLine())!=null){
			   txt += aLine;
		   }
		   br.close(); 
		   return txt;
		
	}
	
	// Get the list of the characters of the text with the frequencies
	public void Alphabet() {
		for(int i=0; i<this.txt.length();i++) {
			if(charac.contains(this.txt.charAt(i)) == false) {
				charac.add(this.txt.charAt(i));
				Tuple aTuple = new Tuple(this.txt.charAt(i),1);
				aTuple.setCharacter(this.txt.charAt(i));
				aTuple.setFrequency(1);
				this.tuple.add(aTuple);
				//System.out.println(tuple);
			}
			
			else { 
				for(Tuple j:this.tuple) {
					if(this.txt.charAt(i)==j.getCharacter()) {
						j.setFrequency(j.getFrequency() +1);
						
					}
				}
			}
			
			
		}	
		
	}
	// method to sort the alphabet 
	public void SortedAlphabet() {
		for(int i=1;i<this.tuple.size();i++) {
			
			char charTemp = this.tuple.get(i).character;
			int freqTemp = this.tuple.get(i).frequency;
			int j =i;
			while(j>0 && this.tuple.get(j-1).frequency > freqTemp) {
				this.tuple.get(j).setFrequency(this.tuple.get(j-1).frequency);
				this.tuple.get(j).setCharacter(this.tuple.get(j-1).character);
				j--;
				//System.out.println(freqTemp);
				//System.out.println(charTemp);
			}
			this.tuple.get(j).setFrequency(freqTemp);
			this.tuple.get(j).setCharacter(charTemp);	
		}
		
	}
	
	
	//creation of the Nodes by using the Tuples
	public ArrayList<Node> nodeList(){
		
		for(int i=0;i<this.tuple.size();i++) {
			//System.out.println(this.tuple.get(i).frequency);
			this.NodeList.add(new Node(this.tuple.get(i).frequency,this.tuple.get(i).character,null,null,""));
		}
		
		return this.NodeList;
	}
	//Method to sort the nodes 
	public ArrayList<Node> sortedNodes(ArrayList<Node> list){
		for(int i=1;i<list.size();i++) {
			int freqTemp = list.get(i).getFrequency();
			char charTemp = list.get(i).getCharac();
			Node rightSonTemp = list.get(i).getRightSon();
			Node leftSonTemp = list.get(i).getLeftSon();
			String binaryCode = list.get(i).getBinaryCode();
			
			int j=i;
			while(j>0 && list.get(j-1).getFrequency()>freqTemp) {
				list.get(j).setFrequency(list.get(j-1).getFrequency());
				list.get(j).setCharac(list.get(j-1).getCharac());
				list.get(j).setRightSon(list.get(j-1).getRightSon());
				list.get(j).setLeftSon(list.get(j-1).getLeftSon());
				list.get(j).setBinaryCode(list.get(j-1).getBinaryCode());
				j--;
			}
			list.get(j).setFrequency(freqTemp);
			list.get(j).setCharac(charTemp);
			list.get(j).setRightSon(rightSonTemp);
			list.get(j).setLeftSon(leftSonTemp);
			list.get(j).setBinaryCode(binaryCode);
		}
		
		return list;
	}
	
	//Creation of the tree, we have to combine the 2 smallest frequencies and create another node. 
	public Node createTree() {
		ArrayList<Node> a =(ArrayList<Node>)NodeList.clone();
		while(a.size()>1) {
			Node small1 = a.get(0);
			a.remove(0);
			Node small2 = a.get(0);
			a.remove(0);
			a.add(new Node(small1.getFrequency()+small2.getFrequency(),'€',small1,small2,""));
			a=this.sortedNodes(a);
			
			
		}
		return a.get(0);
	}
	
	//Generation of the binary code with the Huffman method.
	public void coding(Node root,String binaryCode) {
		if(root.isLeaf()==false) {
			coding(root.getLeftSon(),binaryCode +"0");
			coding(root.getRightSon(),binaryCode+"1");
		}
		else {
			root.setBinaryCode(binaryCode);
			this.hmap.put(root.getCharac(),root.getBinaryCode());
		}
		
	}
	
	public void codeFile(String filepath) throws IOException {
		
		String binary ="";
		for(int i =0; i<this.txt.length();i++) {
			binary=binary+this.getHmap().get(this.txt.charAt(i));
		}
		while(binary.length()%8!=0) {
			binary = binary+'0';
		}
		
		ArrayList<String> bytes =new ArrayList<String>();
		for(int i=8;i<=binary.length();i+=8) {
			bytes.add(binary.substring(i-8,i));
		}
		
		File file = new File(filepath);
		file.createNewFile();
		FileWriter fileWriter1 = new FileWriter(file);
		for(String i:bytes) {
			int aByte = Integer.parseInt(i,2);
			fileWriter1.write((byte)aByte);
		}
		fileWriter1.close();
		
	}
	// Get the compression rate of the text, (doesn't work) 
	public float compression(String filepathBin, String filepathTxt) {
		File bin = new File(filepathBin);
		File txt = new File(filepathTxt);
		double bytesTxt = txt.length();
		double bytesBin = bin.length();
		return(float)(1-(bytesBin/bytesTxt));
		
	}
	
	
	

	
}
