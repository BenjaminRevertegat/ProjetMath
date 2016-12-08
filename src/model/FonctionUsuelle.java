package model;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FonctionUsuelle {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FonctionUsuelle.class);
	private double debutIntervalle;
	private double finIntervalle;
	private double pas;
	private ArrayList<Complex> result;
	
	public FonctionUsuelle(double d, double f,double p){
		debutIntervalle = d;
		finIntervalle = f;
		pas = p;
		result = new ArrayList<Complex>();
	}
	
	public double calculTailleIntervalle(){
		return finIntervalle - debutIntervalle;
	}
	
	public void sin(){
		result = new ArrayList<Complex>();
		
		for(double i = this.debutIntervalle; i <= this.finIntervalle ; i = i+pas){
			result.add(new Complex(Math.sin(i),0));
			LOGGER.debug("Complex : " + (new Complex(Math.sin(i),0)).toString());
		}
	}
	
	public void cos(){
		result = new ArrayList<Complex>();
		
		for(double i = this.debutIntervalle; i <= this.finIntervalle ; i = i+pas){
			result.add(new Complex(Math.cos(i),0));
		}
	}

	public double getDebutIntervalle() {
		return debutIntervalle;
	}

	public void setDebutIntervalle(double debutIntervalle) {
		this.debutIntervalle = debutIntervalle;
	}

	public double getFinIntervalle() {
		return finIntervalle;
	}

	public void setFinIntervalle(double finIntervalle) {
		this.finIntervalle = finIntervalle;
	}

	public double getPas() {
		return pas;
	}

	public void setPas(double pas) {
		this.pas = pas;
	}

	public ArrayList<Complex> getResult() {
		return result;
	}

	public void setResult(ArrayList<Complex> result) {
		this.result = result;
	}
	
	

}
