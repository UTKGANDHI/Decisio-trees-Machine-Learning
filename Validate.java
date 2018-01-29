import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Validate {

	
	int count_1 = 0, count_0 =0;
	public void traintest(Node node, String s){
		
		try{
			
			File f = new File(s);
			FileReader fr = new FileReader(f);
			
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
		
			
			String[] data = new String[node.attributes.length];
			
			while((str=br.readLine())!=null){
				
				data = str.split(",");					
				accuracy(node,data); //calculating accuracy
			
			}
			float percentage = (float)count_1/(count_1+count_0);
			System.out.println("Accuracy of the model on the training dataset = "+(percentage*100)+"%");
			count_1 =0;
			count_0 =0;
			fr.close();
			
		}
		catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
public void validtest(Node node, String s){
		
		try{
			
			File file = new File(s);
			FileReader filereader = new FileReader(file);
			
			BufferedReader br = new BufferedReader(filereader);
			String str = br.readLine();

			
			String[] data = new String[node.attributes.length];
			int i=0;
			while((str=br.readLine())!=null){
				
				data = str.split(",");
				i++;
				accuracy(node,data); 
				
			}
			float percentage = (float)count_1/(count_1+count_0);
			System.out.println("\nNumber of validation instances= "+i);
			System.out.println("Number of validation attributes= "+(node.attributes.length-1));
			
			System.out.println("Accuracy of the model on the validation dataset = "+(percentage*100)+"%");
			count_1 =0;
			count_0 =0;
			filereader.close();
			
		}
		catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	
	
	private void accuracy(Node node, String[] str) {
	
		if (!(node.leftnode == null && node.rightnode == null)) {
			
			if(str[node.leftnode.attri_index].equals("0"))
				accuracy(node.leftnode,str);
			else if(str[node.rightnode.attri_index].equals("1"))
				accuracy(node.rightnode,str);
		}
		else{
			if(Integer.parseInt(str[str.length-1]) == node.label)//node label
				count_1++;
			else
				count_0++;
		}
		
	}

	public void testingtest(Node node, String s) 
	{
		try{
			
			File f = new File(s);
			FileReader fr = new FileReader(f);
			
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			
			String[] data = new String[node.attributes.length];
			int i=0;
			while((str=br.readLine())!=null){
				
				data = str.split(",");
				i++;

				accuracy(node,data); 

			}
			float percentage = (float)count_1/(count_1+count_0);
			System.out.println("\nNumber of testing instances: "+i);
			System.out.println("Number of testing attributes: "+(node.attributes.length-1));
			
			System.out.println("Accuracy of the model on the testing dataset = "+(percentage*100)+"%");
			count_1 =0;
			count_0 =0;
			fr.close();
			
		}
		catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
		
}
	

