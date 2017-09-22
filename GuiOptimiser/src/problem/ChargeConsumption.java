package problem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import calculation.Calculation;
import guioptimiser.Capture;

public class ChargeConsumption {
	ArrayList<String> guiComponents = new ArrayList<>();
	ArrayList<ArrayList<Integer>> RGB = new ArrayList<>();
	ArrayList<Integer> partition;
	private String TARGET_APP;
	private static final int TARGET_APP_RUNNINGTIME = 3000;
	private static final String JAVA_COMMAND = "java -jar ";
	private double consumption = -1;
	
	
	public ChargeConsumption(String app){
		TARGET_APP = app;
		
		
		
		guiComponents.add("mainFrameColor"); // both apps
		guiComponents.add("jButton1");// both apps
		guiComponents.add("jButton2");
		guiComponents.add("jButton3");
		guiComponents.add("jButton4");
		guiComponents.add("jButton5");
		guiComponents.add("jButton6");
		guiComponents.add("jButton7");
		guiComponents.add("jButton8");
		guiComponents.add("jButton9");
		guiComponents.add("jButton10");
		guiComponents.add("jButton11");
		guiComponents.add("jButton12");
		guiComponents.add("jButton13");
		guiComponents.add("jButton14");
		guiComponents.add("jButton15");
		guiComponents.add("jButton16");
		guiComponents.add("jButton17");
		guiComponents.add("jButton18");
		  
		guiComponents.add("jTextField1");// both apps
		guiComponents.add("jTextField1TextColor");// both apps
		
		guiComponents.add("jLabel1");// both apps
		
		guiComponents.add("jPanel1");// both apps
		guiComponents.add("jPanel2");
		guiComponents.add("jPanel3");
		guiComponents.add("jPanel4");
		guiComponents.add("jPanel5");
		
		try {
			partition = getpartition();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public ArrayList<ArrayList<Integer>> getRGB(){
		return RGB;
	}
	
	public void setRGB(ArrayList<ArrayList<Integer>> rgb){
		RGB = new ArrayList<>();
//		for(int i = 0; i < rgb.size(); i++){
//			RGB.add(rgb.get(i));
//		}
		RGB = rgb;
	}
	
	public double quick_evaluate(){
		double fitness = 0;
		Calculation cal = new Calculation();
		
//		System.out.println(applist.get(TARGET_APP).size() + "\t" + RGB.size());
		if((!satisfiedconstraint()) ){
			fitness = 0-Double.MAX_VALUE;
		}else{
			for(int i = 0; i < RGB.size(); i++){
				double numberofpixels = partition.get(i);
				fitness = fitness - numberofpixels * cal.calculateChargeconsumptionPerPixel(RGB.get(i).get(0), RGB.get(i).get(1), RGB.get(i).get(2));
//				System.out.println(fitness);
			}
		}
		return fitness;
	}
	
	public ArrayList<Integer> getpartition() throws IOException{
		ArrayList<Integer> partition= new ArrayList<Integer>();
		File input = new File(TARGET_APP + ".txt");
		if(input.exists()){
			Scanner in = new Scanner(input);
			while(in.hasNextLine()){
				partition.add(Integer.parseInt(in.nextLine()));
			}
		}else{
			FileWriter out = new FileWriter(input);
			
			Calculation cl = new Calculation();
			create_red(-1);
			double base = 0 - evaluate(false);
			for(int i = 0; i < guiComponents.size(); i++){
				create_red(i);
				double current =0 - evaluate(false);
				int numberofpixels = (int) ((current-base)/cl.get_red_consumption_per_pixel());
				System.out.println("current: " + current + "\tbase: " +base + "\tred:" + cl.get_red_consumption_per_pixel() +"\tnumberofpixels" +numberofpixels);
				partition.add(numberofpixels);
				out.write(numberofpixels + "\n");
				out.flush();
			}
			out.close();
		}

		return partition;
	}
	// mode= 1~27, -1 for all black
	public void create_red(int mode){
		RGB = new ArrayList<>();
		for(int i =0; i <27; i++){
			if(mode == i){
				RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{255, 0, 0})));
				continue;
			}
			RGB.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0})));
		}
	}
	
	//flag = true means consider the constraints, false means not
	public double evaluate(boolean flag){
		double fitness = 0;
		if((!satisfiedconstraint()) && flag){
			fitness = 0 - Double.MAX_VALUE;
		}else{
			saveToCSV(getParentDir().concat("color.csv"), guiComponents, RGB);
			String filename = getScreenShot(TARGET_APP, TARGET_APP_RUNNINGTIME);
			Calculation cal = new Calculation();
			try {
				fitness = 0 - cal.calculateChargeConsumptionOfImage(filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return fitness;
	}
	
	public boolean satisfiedconstraint(){
		int r = RGB.get(19).get(0) - RGB.get(20).get(0);
		int g = RGB.get(19).get(1) - RGB.get(20).get(1);
		int b = RGB.get(19).get(2) - RGB.get(20).get(2);
		
		if(Math.sqrt(r*r + g*g + b*b) < 128){
			return false;
		}	
		return true;
	}
	
	public String getScreenShot(String path, int targetAppRunningtime) {
		String filename = "";
		try {
            //java -jar C:\Users\Mahmoud-Uni\Documents\NetBeansProjects\calculator\dist\calculator.jar

            //path = "\""+path+"\"";
//            System.out.println("Target App" + path);
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec(JAVA_COMMAND.concat(path));
            try {
                Thread.sleep(targetAppRunningtime);
                Capture capture = new Capture();
                filename = capture.takeScreenShoot();
//                BufferedReader stdError = new BufferedReader(new
//                InputStreamReader(process.getErrorStream()));
//                String line = "";
//                while((line=stdError.readLine())!=null)
//                {
//                    System.out.println("error!");
//                    System.out.println(line);
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("Target App");
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
	
    public static void saveToCSV(String filePath, ArrayList<String> guiComponents, ArrayList<ArrayList<Integer>> RGB) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(new File(filePath)));
            String line = "";
            for (int i = 0; i < guiComponents.size(); i++) {
                line += guiComponents.get(i).concat(",").concat(RGB.get(i).toString().replace("[", "").replace("]", "").replaceAll("\\s", "")) + "\n";
//                System.out.println(line);
            }
            br.write(line);
            br.flush();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getParentDir() {
        String dir = "";
        try {
            File temp = new File("temp");
            dir = temp.getAbsolutePath().replace("temp", "");
//            System.out.println(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dir;
    }
    
    public void output(){
    	saveToCSV(getParentDir().concat("bestcolor.csv"), guiComponents, RGB);
    	
    }

}
