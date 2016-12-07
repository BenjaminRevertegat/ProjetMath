package model;

import java.util.ArrayList;
import java.util.Observable;

public class Echantillon extends Observable {

	// liste des complexe représentant le signal
	private ArrayList<Complex> signal;
	// 2^puissance
	private int puissance;
	private int frequenceEchant;
	// liste des fenetres
	private ArrayList<Fenetre> fenetreList;

	public Echantillon(ArrayList<Complex> input, int p, int fr) {
		signal = input;
		puissance = p;
		frequenceEchant = fr;
		fenetreList = new ArrayList<Fenetre>();
	}

	public void rempliFenetre() {
		Fenetre local = new Fenetre();
		double taille = Math.pow(2, puissance);
		int finFenetre = 1;
		local.add(new Complex(0,0));

		for (int i = 0; i < signal.size(); i++) {
			//si on n'a pas atteind la fin de la fenetre
			if (finFenetre < taille) {
				//ajout de i dans la fenetre actuelle.
				local.add(signal.get(i));
				//on avance dans la fenetre
				finFenetre++;
			} else {
				//on ajoute l'ancien 1
				finFenetre = 1;
				//fenetrelist ajout une fenetre
				fenetreList.add(local);
				//on réinitialise la fenetre
				local = new Fenetre();
				//on ajoute le dernier i de la fenetre d'avant
				local.add(signal.get(i));
			}

		}
		//On rempli la dernière fenetre de 0
		if (local.size() < taille){
			while(local.size() < taille)
				local.add(new Complex(0,0));
		}

	}
	
	

}
