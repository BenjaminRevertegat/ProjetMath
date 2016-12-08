package model;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fenetre {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Complex.class);
	//List des complex de la fenetre
	private ArrayList<Complex> val;
	
	public Fenetre(){
		val = new ArrayList<Complex>();
	}
	
	public void add(Complex c){
		val.add(c);
	}
	
	public void affiche(){
		for (int i = 0 ; i < val.size() ; i++){
			LOGGER.info(val.get(i).toString());
		}
	}

	public ArrayList<Complex> getVal() {
		return val;
	}

	public void setVal(ArrayList<Complex> val) {
		this.val = val;
	}

	public int size() {
		return val.size();
	}

	
}
