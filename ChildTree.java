
public class ChildTree {

	public Node rightnode;
	public Node leftnode;
	
	public ChildTree() {
		leftnode=new Node();
		rightnode=new Node();
	}
	
	public void buildChild(Node node,int attri_index,int depth,int node_counter) {
		
		leftnode.depth=depth;
		rightnode.depth=depth;
		
		// taking attributes in temporary array
		String[] array_tp=node.attributes;
		for (int i=attri_index;i<node.attributes.length-1;i++) // removing the splitting index and shifting the attributes
			array_tp[i]=array_tp[i+1];
		
		leftnode.attributes = new String[array_tp.length-1];
		rightnode.attributes = new String[array_tp.length-1];
		// putting new list of attributes in left and right child
		for(int i=0;i<leftnode.attributes.length;i++){

			leftnode.attributes[i] = array_tp[i];
			rightnode.attributes[i] = array_tp[i];
		}
		
		
		/*leftnode.attributes=array_tp.clone();                   
		rightnode.attributes=array_tp.clone();*/
		
		// removing the split index data and then shifting the data row-by-row
		for(int i=0;i<node.value.size();i++){
			
			if(node.value.get(i)[attri_index].equals("0")){
				String[] array_tp1 = node.value.get(i);
				
				for(int j = attri_index;j<array_tp1.length-1;j++)
					array_tp1[j] = array_tp1[j+1];
				
				// copying into another array
				//String[] array_tp2 = new String[array_tp1.length-1];
				String[] array_tp2=array_tp1.clone();
				for(int j = 0;j<array_tp1.length-1;j++)
					array_tp2[j] = array_tp1[j];
				
				leftnode.value.add(array_tp2); // generate new table for leftnode
			}
			else{
				
				String[] array_tp1 = node.value.get(i);
				for(int k = attri_index;k<array_tp1.length-1;k++){
					
					array_tp1[k] = array_tp1[k+1];
				}
			//	String[] array_tp2 = new String[array_tp1.length-1];
				String[] array_tp2=array_tp1.clone();
//				for(int k = 0;k<array_tp1.length-1;k++)
//					array_tp2[k] = array_tp1[k];
				
				rightnode.value.add(array_tp2);
			}
		}// end of assigning values to the left and right node 
		
		leftnode.parent=rightnode.parent=node;
		leftnode.node_counter=node_counter;
		rightnode.node_counter=node_counter+1;
		for (int i=0;i<leftnode.value.size();i++) {
			if (leftnode.value.get(i)[leftnode.attributes.length-1].equals("0"))
				leftnode.count_0++;
			else 
				leftnode.count_1++;
		}
		if (leftnode.count_0>leftnode.count_1) 
			leftnode.label=0;
		else 
			leftnode.label=1;
		
		for (int i=0;i<rightnode.value.size();i++) {
			if (rightnode.value.get(i)[rightnode.attributes.length-1].equals("0"))
				rightnode.count_0++;
			else 
				rightnode.count_1++;
		}
		if (rightnode.count_0>rightnode.count_1) 
			rightnode.label=0;
		else 
			rightnode.label=1;
		
		leftnode.node_counter=node_counter;
		rightnode.node_counter=node_counter+1;
		depth++;
	}
}
