package controller;

import model.Echantillon;
import model.FFT;
import view.MyViewTest;

public class MyControllerTest {
	//Modele
	private Echantillon myEchantillon = null;
	//View
	public MyViewTest view = null;

	public MyControllerTest(Echantillon echantillon){
		this.myEchantillon = echantillon;
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

	//public void notifyXXXXXXChanged(int aaa) {
	//	this.XXX.setXXXX(aaa);
	//}
}
