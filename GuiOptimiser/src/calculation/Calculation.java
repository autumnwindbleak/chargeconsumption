package calculation;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import operator.Operators;


public class Calculation {
		
	private static double consumption_R = -1;
	private static double consumption_G = -1;
	private static double consumption_B = -1;
	private static double consumption_Black = -1;
	
	public double get_red_consumption_per_pixel() throws IOException{	
			Operators op = new Operators();
			return cal_average(7205,op.read(7205))[0]/3686400;
	}
	

	
	public double calculateChargeConsumptionOfImage(String imagepath) throws IOException{
		double consumption = 0;
		ArrayList<Color> pixels = Operators.read_image(imagepath);
//		System.out.println("there are " + pixels.size() + " in this pic");
		for(int i = 0; i <pixels.size(); i++){
			Color c = pixels.get(i);
			consumption = consumption + calculateChargeconsumptionPerPixel(c.getRed(), c.getGreen(), c.getBlue());
		}
		return consumption;
	}
	
	
	
	public double calculateChargeconsumptionPerPixel(int R, int G, int B) {
		if(consumption_R == -1||consumption_G == -1||consumption_B == -1||consumption_Black == -1){
			Operators op = new Operators();
			ArrayList<Double[]> data;
			try {
				data = op.read(7205);
				set_consumption(cal_average(7205,data));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return R * consumption_R + G * consumption_G + B * consumption_B + consumption_Black; 
	}

	

	public double[] cal_average(int sample, ArrayList<Double[]> data){
		Double[] base = data.get(0);
		Double[] line = data.get(sample-1);
		double black = (line[1] - base[1]) / (line[0] - base[0]) * 3.6;
		double red = (line[3] - base[3]) / (line[2] - base[2]) * 3.6 - black;
		double green = (line[5] - base[5]) / (line[4] - base[4]) * 3.6 - black;
		double blue = (line[7] - base[7]) / (line[6] - base[6]) * 3.6 - black;
		return new double[]{red,green,blue,black};
	}
	
	public void ouputaverage(double[] rgb, int sample){
		System.out.println("The average charge used per hour considering the first " + sample +" sample is:");
		System.out.println("Red: " + rgb[0] + "mA\tGreen: " + rgb[1] + "mA\tBlue: " + rgb[2] + "mA\n");
		
	}
	
	public void set_consumption(double[] rgb){
		consumption_R = rgb[0]/255/3686400;
		consumption_G = rgb[1]/255/3686400;
		consumption_B = rgb[2]/255/3686400;
		consumption_Black = rgb[3]/3686400;
//		System.out.println("con_r:\t" + consumption_R +"\tcon_g:\t" + consumption_G + "\tcon_b:\t" + consumption_B);
	}

    public static void main(String[] args) throws IOException {
    	Calculation ca = new Calculation();
        Operators op = new Operators();
        //7205
        ArrayList<Double[]> data = op.read(7205);
        
        
        System.out.println("Exercise 1, part 1:");
        ca.ouputaverage(ca.cal_average(10,data), 10);
        ca.ouputaverage(ca.cal_average(100,data), 100);
        ca.ouputaverage(ca.cal_average(1000,data), 1000);
//        ca.ouputaverage(ca.cal_average(7205,data), 7205);

        System.out.println("\n\nExercise 1, part 2");
        
        double consumption = 0;
        String image = "input/image-1506053131832.png";
        consumption = ca.calculateChargeConsumptionOfImage(image);
        System.out.println("Image: " + image + " consumption: " + consumption + "mA");
        
        image = "input/image-1506056595008.png";
        consumption = ca.calculateChargeConsumptionOfImage(image);
        System.out.println("Image: " + image + " consumption: " + consumption + "mA");
        
        image = "input/image-1506056649747.png";
        consumption = ca.calculateChargeConsumptionOfImage(image);
        System.out.println("Image: " + image + " consumption: " + consumption + "mA");
    }

}
