import java.io.BufferedReader;
import java.util.Scanner; 
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ID3 {
	
	static int count_nodes = 0;
	static int leaf_no=0;
	
	public static void main(String args[])
	{
		try 
		{
		
			String trainingpath=args[0];
			String validatefilepath=args[1];
			String testfilepath =args[2];
				
			File file=new File(trainingpath);
			FileReader filereader=new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			String str = br.readLine();
		
			String[] attributes = str.split(",");//taking the attributes names by reading the first line from the file.
			
		/*	for(int i=0;i<attributes.length;i++)
				System.out.println(attributes[i]);*/
			
			String[] data=new String[attributes.length];
			
			// creating root node 
			Node root = new Node();
			root.attributes=attributes;
			root.depth=0;
			root.node_counter=0;
			root.name="root";
				
			while((str=br.readLine())!=null)
			{
				data=str.split(",");
				if(!str.isEmpty())
					root.value.add(data); // here value is the Array_List 
			}
			
			MakeTree t=new MakeTree();
			t.buildTree(root);
			
			// print the tree
			PrintTree p1=new PrintTree();
			p1.printTree(root.leftnode,"");
			p1.printTree(root.rightnode,"");
			
		
			int total_attri=root.attributes.length-1;
			System.out.println("\n\nPre-Pruned Accuracy");
			System.out.println("---------------------------------------------");
			System.out.println("Number of training instances=  "+root.value.size());
			System.out.println("Number of training attributes=	"+total_attri);
			System.out.println("Total number of nodes in the tree=  "+p1.final_count);
			System.out.println("Number of leaf nodes in the tree= "+p1.leaf_no);
			
			//putting validation set here
			Validate v= new Validate(); 
			v.traintest(root, trainingpath);
			v.validtest(root, validatefilepath);
			v.testingtest(root,testfilepath);
			
			//pruning starts here
			double prunning_fac = Double.parseDouble(args[3]);
			int nodes_to_prune = (int)(prunning_fac*t.node_counter);
			System.out.println("\n\nPost-Pruned Accuracy");
			System.out.println("-----------------------------------------------");
			
			
			PrunningTree pt =new PrunningTree();
			pt.tree_prune(root,nodes_to_prune, t.node_counter);
			PrintTree p2=new PrintTree();
			p2.printTree(root.leftnode,"");
			p2.printTree(root.rightnode,"");
			System.out.println("\n\nNumber of training instances=  "+root.value.size());
			System.out.println("Number of training attributes=  "+total_attri);			
			System.out.println("Total number of nodes in the tree=  "+p2.final_count);
			System.out.println("Number of leaf nodes in the tree=  "+p2.leaf_no);
			Validate v1= new Validate();
			
			v1.traintest(root, trainingpath);
			v1.validtest(root, validatefilepath);
			v1.testingtest(root,testfilepath);
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}// end of class
