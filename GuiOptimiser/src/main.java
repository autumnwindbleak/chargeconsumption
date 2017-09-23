import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import algorithm.HillClimber;
import algorithm.RandomSearch;
import problem.ChargeConsumption;
import solution.Solution;

public class main {
	public static void main(String[] args){
		int turns = 1000;
//		String app = "calculator.jar";
		String app = "simpleAPP.jar";
		Solution instance;
		ChargeConsumption cc = new ChargeConsumption(app);
		ArrayList<ArrayList<Double>> RS = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> HC = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> EA = new ArrayList<ArrayList<Double>>();
		
		for(int runs = 0 ; runs < 10; runs++){
			//RS
			ArrayList<Double> rs = new ArrayList<Double>();
			instance = new Solution(app);
			for(int i = 0; i < turns; i ++){
//				System.out.println("Randome search turns: " + i);
				instance = RandomSearch.run(instance, app);
				rs.add(cc.quick_evaluate(instance.getRGB()));
			}
			instance.output(app + " RS-1000-" + runs);
			RS.add(rs);
		}
		
		outputfiles(RS,app + "-RS");
		
		for(int runs = 0 ; runs < 10; runs++){
			//HC
			instance = new Solution(app);
			ArrayList<Double> hc = new ArrayList<Double>();
			for(int i = 0; i < turns; i ++){
//				System.out.println("HillClimbing turns: " + i);
				instance = HillClimber.run(instance);
				hc.add(cc.quick_evaluate(instance.getRGB()));
			}
			instance.output(app + " HC-1000-" + runs);
			HC.add(hc);
		}
		
		outputfiles(HC,app + "-HC");
		
		for(int runs = 0 ; runs < 10; runs++){
			//EA
			instance = new Solution(app);
			instance = algorithm.EA.run(turns, 50, app);
			instance.output(app + " EA-1000-" + runs);
			EA.add(algorithm.EA.ea);
		}
		
		outputfiles(EA,app + "-EA");
	}
	
	
	
	public static void outputfiles(ArrayList<ArrayList<Double>> list,String name){
		File output = new File("output/" + name + ".csv");
		try {
			FileWriter out = new FileWriter(output);
			for(int i = 0; i < list.size(); i++){
				for(int j = 0; j < list.get(i).size(); j++){
					out.write((0-list.get(i).get(j)) + ",");
				}
				out.write("\n");
				out.flush();
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
