import java.util.ArrayList;

public class Node {
		
	public Node rightnode;
	public Node leftnode;
	public Node parent;
	public int label;
	String name;int count_0;int count_1;int node_counter;
	int depth;
	public int attri_index;
	public ArrayList<String[]> value =new ArrayList<String[]>();
	public String[] attributes;
	
	void assignLeft(Node node){
		this.leftnode = node;
	}
	void assignRight(Node node){
		this.rightnode = node;
	}
	
	
}
