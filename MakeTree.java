public class MakeTree {
	int depth=0,node_counter=1;
	
	public void buildTree(Node node) {

		boolean flag = false;
		for(int i=1;i<node.value.size();i++){
			if(!node.value.get(i)[node.attributes.length-1].equals(node.value.get(0)[node.attributes.length-1])){
				flag = true;
				break;
			}
		}	
		if (flag==true && node.attributes.length>1) {
			Entropy entropy= new Entropy();
			int attri_index=entropy.decide_attri(node);
		//	System.out.println(attri_index);
			  
			String name=node.attributes[attri_index];
			depth++;
			//System.out.println(name);
			
			ChildTree ct=new ChildTree();
			ct.buildChild(node, attri_index, depth,node_counter);
			node_counter+=2;
			
			Node leftnode=new Node();leftnode=ct.leftnode;
			Node rightnode=new Node();rightnode=ct.rightnode;
			
			leftnode.name=name;rightnode.name=name;
			
			leftnode.attri_index=attri_index;
			rightnode.attri_index=attri_index;
			node.assignLeft(leftnode);
			node.assignRight(rightnode);
			
			buildTree(leftnode);
			buildTree(rightnode);
		
		}
	}
}
