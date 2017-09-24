import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

public class scatter extends JFrame{
		private XYDataset dataset;
		scatter(String name){
			super(name);
		}
		
		public JFreeChart run(XYDataset dataset, String title,String Axis_X, String Axis_Y){
			this.dataset = dataset;
			//create chart
			JFreeChart chart = ChartFactory.createScatterPlot(
			        title, Axis_X, Axis_Y, dataset,
			        PlotOrientation.VERTICAL,true,true,true);
			//Changes background color
		    XYPlot plot = (XYPlot)chart.getPlot();
		    plot.setBackgroundPaint(new Color(255,228,196));
		    
		   
		    // Create Panel
		    ChartPanel panel = new ChartPanel(chart);
		    setContentPane(panel);
		    return chart;
		}
		
	}