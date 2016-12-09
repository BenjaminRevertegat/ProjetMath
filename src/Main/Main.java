package Main;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import controller.MyControllerTest;
import model.Complex;
import model.Echantillon;
import model.FFT;
import model.FonctionUsuelle;
import view.AfficheGraph;
import view.FFTView;
import view.InfoView;


public class Main {

	public static void main(String[] args) {
		
		// INI Modele
		//////////////////////////////////////////////////
		FonctionUsuelle f = new FonctionUsuelle(-Math.PI/2,Math.PI/2,Math.PI/2);
		int puissance = 1;
		f.sin();
		Echantillon ech = new Echantillon(f.getResult(),puissance);
		ech.rempliFenetre();
		ech.affiche();
		
		System.out.println("========================");
		for(int i=0; i<ech.getFenetreList().size();i++){
			FFT fft = new FFT(ech.getFenetreList().get(i).getVal());
			fft.effectuefft();
			System.out.println("Aj  = " + fft.outputToString());
			fft.effectueModule();
			System.out.println("Module : " + fft.moduleToString());
			System.out.println("========================");
		}
		

		

		
		///////////////////////////////////////////////////
		
		ArrayList<Double> testGraph= new ArrayList<Double>();
		for (int i=0; i<9; i++)
		{
		testGraph.add((double)Math.random());
		}
	
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AfficheGraph(testGraph).setVisible(true);
			}
		});
	
		
		
		
		
		
		FFT fftlocal = new FFT(ech.getFenetreList().get(0).getVal());
		// INI Controller-mod�le
		MyControllerTest control = new MyControllerTest(ech, f, fftlocal);
		//INI View

		InfoView view = new InfoView(ech,f,control);
		FFTView fftview = new FFTView(ech,control);
		

	
		
		
		// INI Controller-view
		view.setFrameSize(600, 200);
		fftview.setFrameSize(400, 600);
		control.init(view,fftview);
		
		//AFFICHAGE :
		control.displayViews();
		
	}

}
