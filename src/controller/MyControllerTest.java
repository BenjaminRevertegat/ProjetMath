package controller;

import java.util.ArrayList;

import model.Complex;
import model.Echantillon;
import model.FFT;
import model.FonctionUsuelle;
import model.ModuleFFT;
import view.FFTView;
import view.InfoView;

public class MyControllerTest {
	// Modele
	private Echantillon myEchantillon = null;
	private FonctionUsuelle usu = null;
	private ModuleFFT modfft = null;
	// View
	public InfoView view = null;
	public FFTView fftview = null;
	

	public MyControllerTest(Echantillon ech, FonctionUsuelle usu, ModuleFFT modfft) {
		this.myEchantillon = ech;
		this.usu = usu;
		this.modfft = modfft;
	}

	public void init(InfoView v, FFTView f) {
		this.view = v;
		this.fftview = f;
	
	}

	public void displayViews() {
		this.view.display();
		this.fftview.display();
	}

	public void closeViews() {
		this.view.close();
		this.fftview.close();
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

	public void removeLastElementSignal(ArrayList<Complex> signal) {
		myEchantillon.getSignal().remove(myEchantillon.getSignal().size() - 1);
		this.myEchantillon.setSignal(myEchantillon.getSignal());
	}

	public void addElementSignal(Complex c) {
		this.myEchantillon.add(c);
	}

	public void effectueCos() {
		this.usu.cos();
		this.myEchantillon.setSignal(this.usu.getResult());
	}

	public void effectueSin() {
		this.usu.sin();
		this.myEchantillon.setSignal(this.usu.getResult());
	}

	public void addModule(Echantillon ech) {
		for (int i = 0; i < ech.getFenetreList().size(); i++) {
			FFT fft = new FFT(ech.getFenetreList().get(i).getVal());
			fft.effectuefft();
			fft.effectueModule();
			modfft.addFFT(fft.getOutput(), fft.getModuleList());
		}
	}
}
