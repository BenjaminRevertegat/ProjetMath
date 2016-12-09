package view;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.ModuleFFT;

/**
 * Code adapté de XYLineChartExample utilisant la XYDatabase
 * @author www.codejava.net
 *
 */

public class AfficheGraph extends JFrame implements Observer{
	
	private ModuleFFT mfft = null;

	public AfficheGraph(ArrayList<Double> tab) {
		super("Graphique fréquenciel");
		mfft = new ModuleFFT();
		mfft.setModuleList(tab);
		mfft.addObserver(this);
		JPanel chartPanel = createChartPanel(tab);
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel(ArrayList<Double> tab) {
		String chartTitle = "Grapique résultant de la FFT";
		String yAxisLabel = "Modules";
		String xAxisLabel = "Pas";
		
		XYDataset dataset = createDataset(tab);
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
				xAxisLabel, yAxisLabel, dataset);
		
		
		customizeChart(chart);
		
		// saves the chart as an image files
		File imageFile = new File("XYLineChart.png");
		int width = 640;
		int height = 480;
		
		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		return new ChartPanel(chart);
	}

	private XYDataset createDataset(ArrayList<Double> tab) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries donnees = new XYSeries("FFT des valeurs");
		
		for(int i=0; i<tab.size(); i++)
		{
			donnees.add((double)i,tab.get(i));
		}
				
		dataset.addSeries(donnees);
		return dataset;
	}
	
	private void customizeChart(JFreeChart chart) {
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(1.0f));
		
		// sets renderer for lines
		plot.setRenderer(renderer);
		
		// sets plot background
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		
		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
	}
	/*
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AfficheGraph(new ArrayList<Double>()).setVisible(true);
			}
		});
	}*/

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ModuleFFT) {
			if (arg instanceof ArrayList<?>) {
				for (int i = 0; i < ((ArrayList<Double>) arg).size(); i++) {
					this.createDataset((ArrayList<Double>) arg);
				}
			}
		}
		
	}
}