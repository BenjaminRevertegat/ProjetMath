package Main;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import controller.MyControllerTest;
import model.Complex;
import model.Echantillon;
import model.FFT;
import model.FonctionUsuelle;
<<<<<<< HEAD
import model.ModuleFFT;
=======
import view.AfficheGraph;
>>>>>>> 777a7f046b876aef16c0b4b6ea9e4e2c078347f4
import view.FFTView;
import view.InfoView;


public class Main {

	public static void main(String[] args) {
		
		// INI Modele
		FonctionUsuelle f = new FonctionUsuelle(-Math.PI/2,Math.PI/2,Math.PI/2);
		int puissance = 1;
		f.sin();
		Echantillon ech = new Echantillon(f.getResult(),puissance);
		ech.rempliFenetre();
		ech.affiche();
		
		ModuleFFT mfft = new ModuleFFT();
		for(int i=0; i<ech.getFenetreList().size();i++){
			FFT fft = new FFT(ech.getFenetreList().get(i).getVal());
			fft.effectuefft();
			fft.effectueModule();
			mfft.addFFT(fft.getOutput(),fft.getModuleList());
		}
<<<<<<< HEAD
=======
		

		

		
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
>>>>>>> 777a7f046b876aef16c0b4b6ea9e4e2c078347f4
		// INI Controller-modèle
		MyControllerTest control = new MyControllerTest(ech, f,mfft);
		//INI View

		InfoView view = new InfoView(ech,f,control);
		FFTView fftview = new FFTView(mfft,ech,control);
		

	
		
		
		// INI Controller-view
		view.setFrameSize(600, 200);
		fftview.setFrameSize(400, 600);
		control.init(view,fftview);
		
		//AFFICHAGE :
		control.displayViews();
		
	}

}
