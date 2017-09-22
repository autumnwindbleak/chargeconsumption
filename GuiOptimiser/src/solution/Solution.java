package solution;

import java.util.ArrayList;

import problem.ChargeConsumption;

public class Solution {

	double fitness = 0-Double.MAX_VALUE;
	ChargeConsumption cc;
	
	public Solution(String app){
		cc= new ChargeConsumption(app);
	}
	
	public double getfitness(){
//		fitness = cc.evaluate(true);
		fitness = cc.quick_evaluate();
		return fitness;
	}
	
	public void output(){
		cc.output();
		System.out.print("Consumption: " + (0 - cc.quick_evaluate()));
	}
	
	public ArrayList<ArrayList<Integer>> getRGB(){
		return cc.getRGB();
	}
	
	public void setRGB(ArrayList<ArrayList<Integer>> rgb){
		cc.setRGB(rgb);
	}
}
