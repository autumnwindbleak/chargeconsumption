package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import solution.Solution;

public class HillClimber {
	public static Solution run(Solution instance){
		ArrayList<ArrayList<Integer>> RGB = instance.getRGB();
		ArrayList<ArrayList<Integer>> rgb = new ArrayList<ArrayList<Integer>>();
		Random ran = new Random();
		int changepoint = ran.nextInt(27);
		for(int i = 0; i < RGB.size(); i++){
			if(i == changepoint){
				rgb.add(new ArrayList<Integer> (Arrays.asList(new Integer[]{ran.nextInt(256),ran.nextInt(256),ran.nextInt(256)})));
				continue;
			}
			rgb.add(RGB.get(i));
		}
		double before = instance.getfitness();
		instance.setRGB(rgb);
		double after = instance.getfitness();
		if(before > after){
			instance.setRGB(RGB);
		}
		return instance;
	}
	
}
