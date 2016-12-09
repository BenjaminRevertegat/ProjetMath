package model;

import java.util.ArrayList;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Echantillon extends Observable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Echantillon.class);
	// liste des complexe représentant le signal
	private ArrayList<Complex> signal;
	// 2^puissance : taille d'une fenetre
	private int puissance;
	// liste des fenetres
	private ArrayList<Fenetre> fenetreList;

	public Echantillon(ArrayList<Complex> input, int p) {
		signal = input;
		puissance = p;
		fenetreList = new ArrayList<Fenetre>();
		LOGGER.debug("echantillon créé");
		setChanged();
		notifyObservers(this.signal);
		notifyObservers(this.puissance);
	}

	public void rempliFenetre() {
		Fenetre local = new Fenetre();
		double taille = Math.pow(2, puissance);
		int finFenetre = 0;
		// on parcours toutes les valeurs du signal !
		for (int i = 0; i < signal.size(); i++) {
			// si on n'a pas atteind la fin de la fenetre
			LOGGER.debug("complex numéro : " + i);
			// On atteinds la fin d'une fenetre
			if (finFenetre == taille) {
				// on ajoute l'ancien 1
				finFenetre = 1;
				LOGGER.debug("nouvelle fenetre");
				// fenetrelist ajout une fenetre
				fenetreList.add(local);
				// on réinitialise la fenetre
				local = new Fenetre();
				// on ajoute le dernier i de la fenetre d'avant
				local.add(signal.get(i - 1));
			}
			// ajout de i dans la fenetre actuelle.
			local.add(signal.get(i));
			// on avance dans la fenetre
			finFenetre++;
		}
		// On rempli la dernière fenetre de 0
		if (local.size() < taille) {
			while (local.size() < taille) {
				local.add(new Complex(0, 0));
				LOGGER.debug("ajout d'un 0");
			}
		}
		fenetreList.add(local);
		setChanged();
		notifyObservers(this.fenetreList);

	}

	public void affiche() {
		LOGGER.debug("nombre de Fenetre : " + fenetreList.size());
		for (int i = 0; i < fenetreList.size(); i++) {
			LOGGER.info("Fenetre " + i + " :");
			fenetreList.get(i).affiche();
		}
	}

	public ArrayList<Complex> getSignal() {
		return signal;
	}

	public void setSignal(ArrayList<Complex> signal) {
		this.signal = signal;
		setChanged();
		notifyObservers(this.signal);
	}

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
		setChanged();
		notifyObservers(this.puissance);
	}

	public ArrayList<Fenetre> getFenetreList() {
		return fenetreList;
	}

	public void setFenetreList(ArrayList<Fenetre> fenetreList) {
		this.fenetreList = fenetreList;
		setChanged();
		notifyObservers(this.fenetreList);
	}
	
	public void add(Complex c){
		signal.add(c);
		setChanged();
		notifyObservers(this.signal);
	}
	

}
