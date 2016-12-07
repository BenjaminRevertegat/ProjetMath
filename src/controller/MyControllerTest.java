package controller;

import model.FFT;
import view.MyViewTest;

public class MyControllerTest {
	//Modele
	private FFT myFFT = null;
	//View
	public MyViewTest view = null;

	public MyControllerTest(FFT fft){
		this.myFFT = fft;
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
