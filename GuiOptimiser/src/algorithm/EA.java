package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import population.Population;
import problem.ChargeConsumption;
import solution.Solution;

public class EA {
	public static String TARGET_APP;
	public static ArrayList<Double> ea;
	
	public static Solution run(int generation, int populationsize,String app){
		TARGET_APP = app;
		Population population = new Population(populationsize,TARGET_APP);
		population.randomcreate();
		ChargeConsumption cc = new ChargeConsumption(TARGET_APP);
		ea = new ArrayList<Double>();
		for(int ge = 0; ge < generation; ge++){
//			System.out.println("EA generations: " + ge);
			Population newpopulation = new Population(populationsize,TARGET_APP);
			while(!newpopulation.full()){
				Solution[] parents = tournament(10, population);
				Solution child = onebitcrossover(parents);
				child = onebitmuation(child);
				if(cc.quick_evaluate(child.getRGB()) >= cc.quick_evaluate(parents[1].getRGB())){
					newpopulation.add(child);
				}else{
					newpopulation.add(parents[1]);
				}
			}
			population = newpopulation;
			Solution best = getbest(population);
			ea.add(cc.quick_evaluate(best.getRGB()));
		}
		
		
		return getbest(population);
	}
	
	public static Solution getbest(Population population){
		Solution best = population.get(0);
		ChargeConsumption cc = new ChargeConsumption(TARGET_APP);
		double bestfitness = cc.quick_evaluate(best.getRGB());
		for(int i = 1; i < population.size(); i ++){
			double tmpfitness =cc.quick_evaluate(population.get(i).getRGB()); 
			if(tmpfitness>bestfitness){
				bestfitness = tmpfitness;
				best = population.get(i);
			}
		}
		return best;
	}
	
	
	public static Solution[] tournament(int n, Population population){
		Population p = new Population(n,TARGET_APP);
		Random ran = new Random();
		Solution[] parents = new Solution[2];
		for(int i = 0; i < n; i++){
			p.add(population.get(ran.nextInt(population.size()-1)));
		}
		parents[0]= p.get(0);
		parents[1]= p.get(1);
		ChargeConsumption cc = new ChargeConsumption(TARGET_APP);
		double p0 = cc.quick_evaluate(parents[0].getRGB());
		double p1 = cc.quick_evaluate(parents[1].getRGB());
		if(p0 > p1){
			double tmp = p1;
			p1 = p0;
			p0 = tmp;
			parents[0] = p.get(1);
			parents[1] = p.get(0);
		}
		for(int i = 2; i < n; i++){
			double pn = cc.quick_evaluate(p.get(i).getRGB());
			if(pn > p1){
				p0 = p1;
				parents[0] = parents[1];
				p1 = pn;
				parents[1] = p.get(i);
				
			}else{
				if(pn > p0){
					p0 = pn;
					parents[0] = p.get(i);
				}
			}
		}
		return parents;
	}
	
	public static Solution onebitcrossover(Solution[] parents){
		Random ran = new Random();
		int point = ran.nextInt(28);
		Solution child = new Solution(TARGET_APP);
		ArrayList<ArrayList<Integer>> rgb = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < parents[0].getRGB().size(); i++){
			if(i < point){
				rgb.add(parents[0].getRGB().get(i));
			}else{
				rgb.add(parents[1].getRGB().get(i));
			}
		}
		child.setRGB(rgb);
		return child;
	}
	
	public static Solution onebitmuation(Solution instance){
		Random ran = new Random();
		int point = ran.nextInt(28);
		ArrayList<ArrayList<Integer>> rgb = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < instance.getRGB().size(); i ++){
			if(i == point){
				rgb.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)})));
			}else{
				rgb.add(instance.getRGB().get(i));
			}
		}
		instance.setRGB(rgb);
		return instance;
	}

	
}
