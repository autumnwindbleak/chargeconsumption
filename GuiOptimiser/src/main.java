import algorithm.HillClimber;
import algorithm.RandomSearch;
import solution.Solution;

public class main {
	public static void main(String[] args){
		int turns = 1000;
		String app = "calculator.jar";
		Solution instance = new Solution(app);
//		System.out.println("start for");
		for(int i = 0; i < turns; i ++){
			System.out.println("turns: " + i);
//			instance = RandomSearch.run(instance, app);
			instance = HillClimber.run(instance);
		}
		instance.output();
	}
}
