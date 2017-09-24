import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.problem.ZDT.ZDT1;


public class main{
	class MyComparator implements Comparator<double[]>{
		@Override
		public int compare(double[] a,double[] b){
			if(a[0] - b[0] == 0){
				return 0;
			}else{
				if(a[0] - b[0] > 0){
					return 1;
				}else{
					return -1;
				}
			}
		}
	}
	private ArrayList<double[][]> run(String algorithm, int populationsize, int objectivenumber){
		//configure and run the DTLZ2 function
		List<NondominatedPopulation> result = new Executor()
				.withProblemClass(ZDT1.class)
				.withAlgorithm(algorithm)
				.withMaxEvaluations(10000)
				.withProperty("populationSize", populationsize)
				.runSeeds(10);
		ArrayList<double[][]> data = new ArrayList<double[][]>();
		

		for(int i =0; i < result.size(); i++){
			int count = 0;
			double[][] tmp = new double[result.get(i).size()][objectivenumber];
			for(Solution solution : result.get(i)){
				for(int j = 0; j < objectivenumber; j++){
//					System.out.println("j:" + j + "\tcount "+ count + "\tobjectives: " + solution.getNumberOfObjectives() + "\ttmpsize: " + tmp.length);
					tmp[count][j] = solution.getObjective(j);
				}
				count++;		
			}
			Arrays.sort(tmp,new MyComparator());	
			data.add(tmp);
		}
		return data;
	}
	
	private double[][] get_average(ArrayList<double[][]> data){
		int max = 0;
		for(int i = 0; i < data.size(); i++){
			if(data.get(i).length>max){
				max = data.get(i).length;
			}
		}
		
		double[][] average = new double[max][data.get(0)[0].length];
		for(int i = 0; i < average.length; i++){
			for(int j = 0; j < average[i].length; j++){
				average[i][j] = 0;
			}
		}
		for(int i = 0; i < data.size(); i++){
			for(int j = 0; j < data.get(i).length; j++){
				for(int k = 0; k < average[j].length; k++){
//					System.out.println(i + "\t" + j + "\t" + k +"\t" +data.get(i).length + "\t" + max);
					average[j][k] += data.get(i)[j][k];
				}
			}
		}
		
		for(int i = 0; i < average.length; i++){
			for(int j = 0; j < average[i].length; j++){
				average[i][j] = average[i][j]/data.size(); 
			}
		}
	
		return average;
	}
	
	private XYDataset get_average_inDataset(double[][] average){
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series = new XYSeries("Average");
		for(int i = 0; i < average.length; i++){
			series.add(average[i][0],average[i][1]);
		}
		dataset.addSeries(series);
		return dataset;
	}
	
	private double[] get_standard_devision(double[][] average, ArrayList<double[][]> data){
		//Standard devision
		double[] sd = new double[average.length];
		Arrays.fill(sd, 0);
		for(int i = 0; i < data.size(); i++){
			for(int j = 0; j < data.get(i).length; j++){
				//distance square
				for(int k = 0; k < average[j].length; k++){
					 sd[j] += (data.get(i)[j][k] - average[j][k]) * (data.get(i)[j][k] - average[j][k]);
				}
			}
		}
		for(int i = 0; i < sd.length; i++){
			sd[i] = Math.sqrt(sd[i]/10);
		}
		return sd;
	}
	
	
	private XYDataset get_XYdataset(ArrayList<double[][]> result){
		XYSeriesCollection dataset = new XYSeriesCollection();
		for(int i = 0; i< result.size(); i++){
			XYSeries series = new XYSeries((i+1) +" Runs");
			for(int j = 0; j < result.get(i).length; j++){
				series.add(result.get(i)[j][0],result.get(i)[j][1]);	
			}
			dataset.addSeries(series);
		}
		return dataset;
	}
	
	public void save_chart(String filename, JFreeChart chart){
	    File outputimage = new File("output/"+ filename +".png");
	    try {
			ChartUtilities.saveChartAsPNG(outputimage, chart, 1080, 980);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		int populationsize;
		String algorithm,problem;
		if(args.length == 0){
//			populationsize = 20;
			populationsize = 100;
			
//			algorithm = "NSGAII";
			algorithm = "MOEAD";
			
//			problem = "ZDT1";
			problem = "ZDT2";
		}else{
			algorithm = args[0];
			problem = args[1];
			populationsize = Integer.parseInt(args[2]);
		}
		
		
		main m = new main();
		ArrayList<double[][]> data = m.run(algorithm, populationsize,2);
		
		XYDataset dataset = m.get_XYdataset(data);
		scatter sc = new scatter(algorithm + "-" + problem);
		JFreeChart chart1 = sc.run(dataset,algorithm + "-" + problem + "-" + populationsize,"Objective 0","Objective 1");
		m.save_chart(algorithm + "-" + problem + "-" + populationsize +"-all", chart1);

		double[][] average = m.get_average(data);
		dataset = m.get_average_inDataset(average);
		sc = new scatter(algorithm + "-" + problem + "-average");
		JFreeChart chart2 = sc.run(dataset,algorithm + "-" + problem + "-" + populationsize,"Objective 0","Objective 1");
		m.save_chart(algorithm + "-" + problem + "-" + populationsize + "-average", chart2);
		
		double[] sd = m.get_standard_devision(average, data);
		
		File sdfile = new File("output/" + algorithm + "-" + problem + "-" + populationsize + ".txt");
		try {
			FileWriter out = new FileWriter(sdfile);
			out.write(algorithm + "-" + problem + "-" + populationsize + "Standard Devision are:\n");
			for(int i = 0; i < sd.length; i++){
				out.write(sd[i] + "\n");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(algorithm + "-" + problem + "-" + populationsize + "Standard Devision are:");
		for(int i = 0; i < sd.length; i++){
			System.out.println(sd[i]);
		}
		
		
		
		
		
		//output in window
		
//	    SwingUtilities.invokeLater(() -> {
//	      scatter sc = new scatter(algorithm + "-" + "ZDT1");
//	      sc.run(dataset,algorithm + "-" + "ZDT1","Objective 0","Objective 1");
//	      sc.setSize(1000, 600);
//	      sc.setLocationRelativeTo(null);
//	      sc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//	      sc.setVisible(true);
//	    }); 
	}

}
