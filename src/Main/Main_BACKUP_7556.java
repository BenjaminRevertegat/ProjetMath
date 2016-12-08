package Main;

import java.util.ArrayList;

import controller.MyControllerTest;
import model.Complex;
import model.Echantillon;
import model.FFT;
import view.MyViewTest;

public class Main {

	public static void main(String[] args) {
		
		// INI Modele
		//////////////////////////////////////////////////
		ArrayList<Complex> t = new ArrayList<Complex>();
		for( int i = 0 ; i< 20 ; i++){
			t.add(new Complex(i,0));
		}
		int puissance = 2;
		Echantillon ech = new Echantillon(t,puissance);
		ech.rempliFenetre();
		
		ech.affiche();
		/*
		FFT fft = new FFT(t);
		fft.effectuefft();
		System.out.println("Aj  = " + fft.outputToString());
		fft.effectueModule();
		System.out.println("Module : " + fft.moduleToString());
		*/
		///////////////////////////////////////////////////
		
		// INI Controller-modèle
	//	MyControllerTest control = new MyControllerTest(fft);
		//INI View
<<<<<<< HEAD
	//	MyViewTest view = new MyViewTest(fft,control);
		// INI Controller-view
	//	control.init(view);
=======
		MyViewTest view = new MyViewTest(fft,control,tab);
		// INI Controller-view
		
		control.init(view);
>>>>>>> 3695f0ad9316d1a4118701430ff36838322a489a
		
		//AFFICHAGE :
		//control.displayViews();
	}

}
