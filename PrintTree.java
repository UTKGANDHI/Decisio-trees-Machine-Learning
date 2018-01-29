
public class PrintTree {

	int final_count = 0,leaf_no=0;
	void printTree(Node node, String delimiter) {
		int value = 0;
		if(node == node.parent.leftnode)
			value = 0;
		else
			value = 1;
		
		if (node == null)
			return;
		
		System.out.println();
		System.out.print(delimiter + node.name + " = " + value);
		final_count++;
		
		if (node.leftnode == null && node.rightnode == null) {	
			if(node.count_1 > node.count_0)
				System.out.print(" : 1");
			else
				System.out.print(" : 0"); 
			leaf_no++;
		} 
		else {
			printTree(node.leftnode, delimiter + "| ");
			printTree(node.rightnode, delimiter + "| ");
	   }
	}
}
