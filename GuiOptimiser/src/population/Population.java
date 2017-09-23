package population;

import java.util.ArrayList;

import solution.Solution;

public class Population {
	ArrayList<Solution> population = new ArrayList<Solution>();
	int populationsize;
	String app;
	public Population(int populationsize, String app){
		this.populationsize =populationsize;
		this.app = app;
	}
	
	public void randomcreate(){
		for(int i = 0; i < populationsize; i++){
			Solution s = new Solution(app);
			population.add(s);
		}
	}
	
	public void add(Solution instance){
		if(population.size()>=populationsize){
			System.out.println("population full");
		}else{
			population.add(instance);
		}
	}
	
	public Solution get(int i){
		return population.get(i);
	}
	
	public void empty(){
		population = new ArrayList<Solution>();
	}

	public int size() {
		// TODO Auto-generated method stub
		return population.size();
	}

	public boolean full() {
		// TODO Auto-generated method stub
		if(population.size() >= populationsize){
			return true;
		}else{
			return false;
		}
	}
	
}
