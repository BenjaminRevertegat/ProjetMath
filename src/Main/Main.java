package Main;

import java.util.ArrayList;

import controller.MyControllerTest;
import model.Complex;
import model.Echantillon;
import model.FFT;
import model.FonctionUsuelle;
import view.MyViewTest;

public class Main {

	public static void main(String[] args) {
		
		// INI Modele
		//////////////////////////////////////////////////
		FonctionUsuelle f = new FonctionUsuelle(-Math.PI/2,Math.PI/2,Math.PI/8);
		int puissance = 2;
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
		FFT fftlocal = new FFT(ech.getFenetreList().get(0).getVal());
		// INI Controller-modèle
		MyControllerTest control = new MyControllerTest(ech, f, fftlocal);
		//INI View

	//	MyViewTest view = new MyViewTest(fft,control);
		// INI Controller-view
	//	control.init(view);
		MyViewTest view = new MyViewTest(ech,control);
		// INI Controller-view
		view.setFrameSize(1200, 400);
		control.init(view);
		
		//AFFICHAGE :
		control.displayViews();
	}

}
