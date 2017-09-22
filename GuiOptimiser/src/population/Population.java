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
}
