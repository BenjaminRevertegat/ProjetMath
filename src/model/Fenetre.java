package model;

import java.util.ArrayList;

public class Fenetre {
	
	private ArrayList<Complex> val;
	
	public Fenetre(){
		val = new ArrayList<Complex>();
	}
	
	public void add(Complex c){
		val.add(c);
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
