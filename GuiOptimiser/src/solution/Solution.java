package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import problem.ChargeConsumption;

public class Solution {

	double fitness = 0-Double.MAX_VALUE;
	ChargeConsumption cc;
	ArrayList<ArrayList<Integer>> RGB = new ArrayList<>();
	
	public Solution(String app){
		cc= new ChargeConsumption(app);
		randomcreate();
	}
	
	public void randomcreate(){
		Random randomInt = new Random();
		RGB = new ArrayList<>();
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));

        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));
        RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256)})));

	}
	

	
	
	
	
	public double getfitness(){
//		fitness = cc.evaluate(true);
		fitness = cc.quick_evaluate(RGB);
		return fitness;
	}
	
	public void output(){
		cc.output(RGB);
		System.out.print("Consumption: " + (0 - cc.quick_evaluate(RGB)));
	}
	
	public ArrayList<ArrayList<Integer>> getRGB(){
		return RGB;
	}
	
	public void setRGB(ArrayList<ArrayList<Integer>> rgb){
		RGB= rgb;
	}
}
