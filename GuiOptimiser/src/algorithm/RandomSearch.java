package algorithm;

import solution.Solution;

public class RandomSearch {
	public static Solution run(Solution instance,String app){
		Solution temp = new Solution(app);
		double f1 = instance.getfitness();
		double f2 = temp.getfitness();
		
		if(f1 < f2){
//			System.out.println("f1<f2:"+f2+"\t" +f1 +"\t" + f2);
			return temp;
		}else{
//			System.out.println("f1>=f2"+ f1 + "\t" + f1 + "\t" + f2);
			return instance;
		}
	}
}
