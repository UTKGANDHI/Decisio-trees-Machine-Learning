
public class PrunningTree {
	public void tree_prune(Node n,int nodes_to_prune,int total_nodes){
		int[] random = new int[nodes_to_prune];
		for(int i=0;i<nodes_to_prune;i++)
			random[i] = (int) (Math.random() * (total_nodes - 1)) + 1;
		trim_tree(n,random);
	}
	public void trim_tree(Node node,int[] random){
		
		if (node.leftnode == null && node.rightnode == null)
			return;
		
		else
		{
			for(int i=0;i<random.length;i++){
				if(node.node_counter == random[i]){
					node.leftnode = null;
					node.rightnode = null;
				}
			}
			if (!(node.leftnode == null && node.rightnode == null)){
				trim_tree(node.leftnode,random);
				trim_tree(node.rightnode,random);
			}
		}
	}
}
