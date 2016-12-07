package model;

public class Complex {

	// Partie réel
	private double PEntiere;
	// Partie imaginaire
	private double PImaginaire;

	public Complex(double r, double i) {
		PEntiere = r;
		PImaginaire = i;
	}

	// initialiser un réel
	public Complex(double r) {
		PEntiere = r;
		PImaginaire = 0;
	}

	public Complex(Complex c) {
		PEntiere = c.getPEntiere();
		PImaginaire = c.getPImaginaire();
	}

	public String toString() {
		String sortie = new String();

		if ((PEntiere < 0.00001) && (PEntiere > -0.00001)) {
			PEntiere = 0;
		}
		if ((PImaginaire < 0.00001) && (PImaginaire > -0.00001)) {
			PImaginaire = 0;
		}
		if (PEntiere != 0) {
			sortie = sortie + PEntiere + " ";
		}
		if (this.PImaginaire == 0) {
			// On ne fait rien
		}
		if (this.PImaginaire < 0) {
			sortie = sortie + PImaginaire + "i";
		}
		if (this.PImaginaire > 0) {
			sortie = sortie + " +" + this.PImaginaire + "i";
		}
		return sortie;
	}

	public double getPEntiere() {
		return PEntiere;
	}

	public void setPEntiere(double pEntiere) {
		PEntiere = pEntiere;
	}

	public double getPImaginaire() {
		return PImaginaire;
	}

	public void setPImaginaire(double pImaginaire) {
		PImaginaire = pImaginaire;
	}

	// renvoi la valeur du complexe à la puissance donnée entière
	public Complex Puissance(int pw) {

		double module = Math
				.sqrt(this.getPEntiere() * this.getPEntiere() + this.getPImaginaire() * this.getPImaginaire());
		double argument = Math.atan2(this.getPImaginaire(), this.getPEntiere());
		// cos équivalent à la partie réelle du complexe
		double coseq = Math.cos(pw * argument);
		// sin équivalent à la partie imaginaire du complexe
		double sineq = Math.sin(pw * argument);
		// module à la puissance pw
		double moduleq = Math.pow(module, pw);
		// On arrondi sinus et cosinus
		if ((sineq < 0.00001) && (sineq > -0.00001)) {
			sineq = 0;
		}
		if ((coseq < 0.00001) && (coseq > -0.00001)) {
			coseq = 0;
		}
		Complex resultat = new Complex(moduleq * coseq, moduleq * sineq);
		return resultat;
	}

	public Complex Addition(Complex B) {
		double reel = this.getPEntiere() + B.getPEntiere();
		double imaginaire = this.getPImaginaire() + B.getPImaginaire();
		Complex resultat = new Complex(reel, imaginaire);
		return resultat;

	}

	public Complex Soustraction(Complex B) {
		double reel = this.getPEntiere() - B.getPEntiere();
		double imaginaire = this.getPImaginaire() - B.getPImaginaire();
		Complex resultat = new Complex(reel, imaginaire);
		return resultat;
	}

	public Complex Multiplication(Complex B) {
		double reel = this.getPEntiere() * B.getPEntiere() - this.getPImaginaire() * B.getPImaginaire();
		double imaginaire = this.getPEntiere() * B.getPImaginaire() + this.getPImaginaire() * B.getPEntiere();
		;

		Complex resultat = new Complex(reel, imaginaire);
		return resultat;
	}

	public boolean equals(Complex B) {
		if (this.getPEntiere() == B.getPEntiere() && this.getPImaginaire() == B.getPImaginaire())
			return true;
		else
			return false;
	}

	// return a new Complex object whose value is (this * b)
	public Complex times(Complex b) {
		Complex a = this;
		double real = a.PEntiere * b.PEntiere - a.PImaginaire * b.PImaginaire;
		double imag = a.PEntiere * b.PImaginaire + a.PImaginaire * b.PEntiere;
		return new Complex(real, imag);
	}
	
	public double module(){
		 double module = Math
				.sqrt(this.getPEntiere() * this.getPEntiere() + this.getPImaginaire() * this.getPImaginaire());
		 return module;
	}

}
