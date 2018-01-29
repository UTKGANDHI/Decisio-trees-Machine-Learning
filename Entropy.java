
public class Entropy {

	// method for calculating entropy
	public double calculateEntropy(Counter c)
	{
		double p0=(double)(c.count_0)/(c.count_0+c.count_1);
		double p1=(double)(c.count_1)/(c.count_0+c.count_1);
		//System.out.println("p0="+p0);System.out.println("p1="+p1);
		if (p0==0.0 || p1==0.0){
			return 0.0;
		}
		else {
			double entropy= (-p0* Math.log10(p0)-p1*Math.log10(p1))/Math.log10(2); // entropy formula
			//System.out.println("entropy ="+entropy);
			return entropy;
		}
	}
	
	// method for deciding which attribute to choose for splitting
	public int decide_attri(Node node) {
				
		// Calculating entropy of parent 
		Counter parent_count = count_fn(node, node.attributes.length-1); 
		double parent_entropy=calculateEntropy(parent_count);
		
		// calculating entropy of each attribute
		double[] ig = new double[node.attributes.length-1];
		
		for (int i=0;i<ig.length;i++) {
			Counter c_obj = count_fn(node,i);
	
			// calculating weights for average weighted entropy
			double weight_0 = (double)c_obj.count_0/(c_obj.count_0+c_obj.count_1);
			double weight_1 = 1-weight_0;
			
			Counter c_obj0 =count_fn(node, i, "0");
			Counter c_obj1 =count_fn(node, i, "1");
			
			double entropy_class_0= calculateEntropy(c_obj0);
			double entropy_class_1= calculateEntropy(c_obj1);
			
//			System.out.println("entropy_class_0:"+entropy_class_0);
//			System.out.println("entropy_class_1:"+entropy_class_1);
			
			ig[i]=parent_entropy - (weight_0*entropy_class_0+weight_1*entropy_class_1);
			
		}
		// finding attribute having maximum Information_Gain
		double max= ig[0];int attri_index=0;
		
		for (int i=1;i<ig.length;i++) {
			if (ig[i]>max) {
				max=ig[i];
				attri_index=i;
			}
			
		}
		return attri_index;
	}
	
	// method for counting number of 0's and 1's 
	public Counter count_fn(Node node, int array_index)
	{
		Counter c_obj = new Counter();
		
		for(int i=0;i<node.value.size();i++){
			if(node.value.get(i)[array_index].equals("0"))
				c_obj.count_0++;
			else if (node.value.get(i)[array_index].equals("1"))
				c_obj.count_1++;
		}
		
		return c_obj;
	}
	
	public Counter count_fn(Node node, int array_index, String key)
	{
		Counter c_obj = new Counter();
		for (int j=0;j<node.value.size();j++) 
		{
			if (node.value.get(j)[array_index].equals(key)) {
				if (node.value.get(j)[node.attributes.length-1].equals("0")) {
					c_obj.count_0++;
				}
				else 
					c_obj.count_1++;
			}
		}
		return c_obj;
	}

}// end of class
