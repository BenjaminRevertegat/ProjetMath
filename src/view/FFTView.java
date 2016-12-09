package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.MyControllerTest;
import model.Complex;
import model.Echantillon;
import model.FFT;
import model.FonctionUsuelle;

public class FFTView implements ActionListener, Observer {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoView.class);
	// Mon Controller
	private MyControllerTest myController = null;
	// Mon modèle
	private Echantillon myEchantillon = null;
	private FFT myfft = null;

	// Fenetre
	private JFrame frame = null;
	private JPanel contentPane = null;
	
	public FFTView(Echantillon model, MyControllerTest controller) {
		this.myEchantillon = model;
		this.myEchantillon.addObserver(this);
		this.myController = controller;
		buildFrame(model.getSignal());
	}
	
	private void buildFrame(ArrayList<Complex> signal) {
		frame = new JFrame();
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		
		
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

	public void setFrameSize(int a, int b) {
		frame.setSize(a, b);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
