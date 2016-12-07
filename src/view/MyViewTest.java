package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import controller.MyControllerTest;
import model.FFT;

public class MyViewTest implements ActionListener, Observer {

	// Mon Controller
	private MyControllerTest myController = null;
	// Mon modèle
	private FFT myfft = null;

	private JFrame frame = null;
	private JPanel contentPane = null;
	private JSpinner spinner = null;
	private JButton button = null;


	public MyViewTest(FFT model, MyControllerTest controller) {
		this.myfft = model;
		this.myfft.addObserver(this);
		this.myController = controller;
		buildFrame();
	}

	private void buildFrame() {
		frame = new JFrame();
		contentPane = new JPanel();
		spinner = new JSpinner();
		contentPane.add(spinner);
		button = new JButton("Mettre à jour");
		button.addActionListener(this);
		contentPane.add(button);
		frame.setContentPane(contentPane);
		frame.setTitle("FFT");
		frame.pack();
	}

	public void close() {
		frame.dispose();
	}

	public void display() {
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// if (arg instanceof Integer)
				// spinner.setValue((Integer) arg);
				// System.out.println("[SpinnerVolumeView] : update");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this.controller.notifyVolumeChanged(Integer.parseInt(this.spinner.getValue().toString()));

	}

}
