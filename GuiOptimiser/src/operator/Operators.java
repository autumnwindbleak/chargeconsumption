package operator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Operators {
	private String targetsheet = "screenOn";
	private String INPUTFILENAME = "input/sbse-assignment02-nexus6.xlsx";
	
	public ArrayList<Double[]> get_data_from_xlsx(String filename, int samplenumber) throws IOException{
		File output = new File("input/pre.txt");
		FileWriter out = new FileWriter(output);
		ArrayList<Double[]> data = new ArrayList<Double[]>();
		
		InputStream inputStream = new FileInputStream(new File(filename));
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		int sheetnum = workbook.getNumberOfSheets();
		
		boolean found = false;
		int index = -1;
		
		for(int i = 0; i < sheetnum; i++){
			if(workbook.getSheetName(i).equals(targetsheet)){
				index = i;
				found = true;
				break;
			}
		}
		
		if(!found){
			System.out.println("Can't find target sheet.");
			System.exit(0);
		}
		
		XSSFSheet sheet = workbook.getSheetAt(index);
		for(int i = 2; i < samplenumber + 2; i++ ){
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
                continue;
            }
			Double[] line = new Double[8];
			// Black: 10 14 Red: 19 23 Green: 28 32 Blue: 37 41
			line[0] = row.getCell(10).getNumericCellValue();
			line[1] = row.getCell(14).getNumericCellValue();
			line[2] = row.getCell(19).getNumericCellValue();
			line[3] = row.getCell(23).getNumericCellValue();
			line[4] = row.getCell(28).getNumericCellValue();
			line[5] = row.getCell(32).getNumericCellValue();
			line[6] = row.getCell(37).getNumericCellValue();
			line[7] = row.getCell(41).getNumericCellValue();
			data.add(line);
			out.write(line[0] + "," + line[1] + "," + line[2] + "," + line[3] + ","+ line[4] + ","+ line[5] + ","+ line[6] + ","+ line[7] + "\n");
			out.flush();
		}
		out.close();
		return data;
	}
	
	public ArrayList<Double[]> read(int samplenumber) throws IOException{
		File pre = new File("input/pre.txt");
		ArrayList<Double[]> data = new ArrayList<Double[]>();
		if(!pre.exists()){
			data = get_data_from_xlsx(INPUTFILENAME,samplenumber);
		}else{
			Scanner in = new Scanner(pre);
			while(in.hasNextLine()){
				String[] sline = in.nextLine().split(",");
				Double[] line = new Double[8];
				for(int i = 0; i < line.length; i++){
					line[i] = Double.parseDouble(sline[i]); 
				}
				data.add(line);
			}
		}
		return data;
	}

	public static ArrayList<Color> read_image(String imagepath) throws IOException{
		File image = new File(imagepath);
		BufferedImage input = ImageIO.read(image);
		ArrayList<Color> pixels = new ArrayList<Color>();
		int width = input.getWidth();
		int height = input.getHeight();
//		System.out.println("w:" + width + " " + height);
		int count = 0;
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				count++;
				Color c = new Color(input.getRGB(i, j));
				pixels.add(c);
			}
		}
		return pixels;
	}
}
