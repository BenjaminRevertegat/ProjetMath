package model;

import java.util.ArrayList;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FFT extends Observable {

	private static final Logger LOGGER = LoggerFactory.getLogger(FFT.class);
	// Vecteur de base : u0,u1,u2,u3,...
	private ArrayList<Complex> input;
	// tableau des a0, a1, aé, .... sortie de la FFT
	private ArrayList<Complex> output;
	// liste des modules des output
	private ArrayList<Double> moduleList;
	// renvoi les coeffs dans l'ordre croissant
	private static ArrayList<Complex> omegaList = new ArrayList<Complex>();

	public FFT(ArrayList<Complex> input) {
		this.input = input;
	}

	public void setOmegaList() {
		int tailleInverse = 2;
		while (tailleInverse <= input.size()) {
			omegaList.add(coeffW(tailleInverse));
			tailleInverse = tailleInverse * 2;
		}
		LOGGER.debug("Liste des Omegas pour N = " + input.size());
	}

	public void effectuefft() {
		if (input.size() % 2 != 0) {
			LOGGER.warn("Attention, la taille de l'input n'est pas en 2^N");
		} else {
			output = new ArrayList<Complex>();
			output = this.fft(input);
			LOGGER.debug("FFT effectué ");
		}

	}

	public void effectueModule() {
		moduleList = new ArrayList<Double>();
		for (int i = 0; i < output.size(); i++) {
			moduleList.add(output.get(i).module());
		}
		LOGGER.debug("Calcul du module effectué");
	}

	private ArrayList<Complex> fft(ArrayList<Complex> x) {
		int n = x.size();
		// base case
		if (n == 1) {
			ArrayList<Complex> back = new ArrayList<Complex>();
			back.add(x.get(0));
			return back;
		}

		// radix 2 Cooley-Tukey FFT
		if (n % 2 != 0) {
			throw new RuntimeException("n is not a power of 2");
		}

		// fft of even terms
		ArrayList<Complex> even = new ArrayList<Complex>();
		for (int k = 0; k < n / 2; k++) {
			even.add(x.get(2 * k));
		}
		ArrayList<Complex> q = new ArrayList<Complex>();
		q = fft(even);

		// fft of odd terms
		ArrayList<Complex> odd = new ArrayList<Complex>();
		for (int k = 0; k < n / 2; k++) {
			odd.add(x.get(2 * k + 1));
		}
		ArrayList<Complex> r = new ArrayList<Complex>();
		r = fft(odd);

		// combine
		ArrayList<Complex> y = new ArrayList<Complex>();
		ArrayList<Complex> v = new ArrayList<Complex>();
		for (int k = 0; k < n / 2; k++) {
			double kth = -2 * k * Math.PI / n;
			Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
			y.add(q.get(k).Addition(wk.times(r.get(k))));
			v.add(q.get(k).Soustraction(wk.times(r.get(k))));
		}
		y.addAll(v);
		return y;
	}

	// cette fonction renvoi un coefficient W pour une taille donnée
	public static Complex coeffW(int N) // N est le coefficient en bas à droite
										// du w,
	// il est égale au nombre d'éléments dans le
	// vecteur obtenu
	{
		double re = Math.cos(-(2 * Math.PI) / N);
		double im = Math.sin(-(2 * Math.PI) / N);
		if ((re < 0.00001) && (re > -0.00001)) {
			re = 0;
		}
		if ((im < 0.00001) && (im > -0.00001)) {
			im = 0;
		}
		Complex Coef = new Complex(re, im);
		return Coef;

	}

	public String outputToString() {
		String message = new String();

		for (int i = 0; i < output.size(); i++) {
			message = message + output.get(i).toString() + " | ";
		}
		return message;
	}

	public String moduleToString() {
		String message = new String();

		for (int i = 0; i < moduleList.size(); i++) {
			message = message + moduleList.get(i) + " | ";
		}
		return message;
	}

	public ArrayList<Complex> getInput() {
		return input;
	}

	public void setInput(ArrayList<Complex> input) {
		this.input = input;
	}

	public ArrayList<Complex> getOutput() {
		return output;
	}

	public void setOutput(ArrayList<Complex> output) {
		this.output = output;
	}

	public static ArrayList<Complex> getOmegaList() {
		return omegaList;
	}

	public static void setOmegaList(ArrayList<Complex> omegaList) {
		FFT.omegaList = omegaList;
	}

}
