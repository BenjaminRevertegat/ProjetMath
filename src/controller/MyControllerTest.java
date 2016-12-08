package controller;

import java.util.ArrayList;

import model.Complex;
import model.Echantillon;
import model.FFT;
import model.FonctionUsuelle;
import view.MyViewTest;

public class MyControllerTest {
	//Modele
	private Echantillon myEchantillon = null;
	private FonctionUsuelle usu = null;
	private FFT fft = null;
	//View
	public MyViewTest view = null;

	public MyControllerTest(Echantillon ech,FonctionUsuelle usu,FFT fft){
		this.myEchantillon = ech;
		this.usu = usu;
		this.fft = fft;
	}
	
	public void init (MyViewTest v)
	{
		this.view = v;
	}
	
	public void displayViews() {
		this.view.display();
	}

	public void closeViews() {
		this.view.close();
	}

	public void notifyPuissanceChanged(int p) {
		this.myEchantillon.setPuissance(p);
	}
	
	public void notifySignalChanged(ArrayList<Complex> newSignal) {
		this.myEchantillon.setSignal(newSignal);
	}
	
	public void notifyDebutChanged(double start) {
		this.usu.setDebutIntervalle(start);
	}
	
	public void notifyFinChanged(double end) {
		this.usu.setFinIntervalle(end);
	}
	
	public void notifyPasChanged(double pas) {
		this.usu.setPas(pas);
	}
	
	public void notifyOutputChanged(ArrayList<Complex> out) {
		this.fft.setOutput(out);
	}
	
	public void notifyPasChanged(ArrayList<Double> mod) {
		this.fft.setModuleList(mod);
	}
}
