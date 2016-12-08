package Main;

import java.util.ArrayList;

import controller.MyControllerTest;
import model.Complex;
import model.FFT;
import view.MyViewTest;

public class Main {

	public static void main(String[] args) {
		
		// INI Modele
		ArrayList<Complex> tab = new ArrayList<>();
		for ( int i=0; i < 8; i++){
			tab.add(new Complex(i,4-i));
		}
		
		
		//////////////////////////////////////////////////
		ArrayList<Complex> t = new ArrayList<Complex>();
		for( int i = 1 ; i< 5 ; i++){
			t.add(new Complex(i,0));
		}
		/*
		t.add(new Complex(0,0));
		t.add(new Complex(1,0));
		t.add(new Complex(0,0));
		t.add(new Complex(1,0));
		
		t.add(new Complex(0,0));
		t.add(new Complex(1,0));
		t.add(new Complex(1,0));
		t.add(new Complex(1,0));
		*/
		
		FFT fft = new FFT(t);
		fft.effectuefft();
		System.out.println("Aj  = " + fft.outputToString());
		fft.effectueModule();
		System.out.println("Module : " + fft.moduleToString());
		///////////////////////////////////////////////////
		
		// INI Controller-modèle
		MyControllerTest control = new MyControllerTest(fft);
		//INI View
		MyViewTest view = new MyViewTest(fft,control,tab);
		// INI Controller-view
		
		control.init(view);
		
		control.displayViews();
	}

}
