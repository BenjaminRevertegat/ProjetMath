package Main;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import controller.MyControllerTest;
import model.Complex;
import model.Echantillon;
import model.FFT;
import model.FonctionUsuelle;
import model.ModuleFFT;
import view.AfficheGraph;
import view.FFTView;
import view.InfoView;


public class Main {

	public static void main(String[] args) {
		
		// INI Modele
		FonctionUsuelle f = new FonctionUsuelle(-Math.PI/2,Math.PI/2,Math.PI/4);
		int puissance = 2;
		f.sin();
		Echantillon ech = new Echantillon(f.getResult(),puissance);
		ech.rempliFenetre();
		ech.affiche();
		//Première valeur par défault
		ModuleFFT mfft = new ModuleFFT();
		for(int i=0; i<ech.getFenetreList().size();i++){
			FFT fft = new FFT(ech.getFenetreList().get(i).getVal());
			fft.effectuefft();
			fft.effectueModule();
			mfft.addFFT(fft.getOutput(),fft.getModuleList());
		}
		///Graph
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AfficheGraph(mfft.getModuleList()).setVisible(true);
			}
		});
		
		// INI Controller-modèle
		MyControllerTest control = new MyControllerTest(ech, f,mfft);
		//INI View

		InfoView view = new InfoView(ech,f,control);
		FFTView fftview = new FFTView(mfft,ech,control);

		
		// INI Controller-view
		view.setFrameSize(600, 400);
		fftview.setFrameSize(400, 600);
		control.init(view,fftview);
		
		//AFFICHAGE :
		control.displayViews();
		
	}

}
