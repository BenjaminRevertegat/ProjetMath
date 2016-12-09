package view;

import java.awt.BorderLayout;
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
import model.ModuleFFT;

public class FFTView implements ActionListener, Observer {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoView.class);
	// Mon Controller
	private MyControllerTest myController = null;
	// Mon modèle
	private ModuleFFT myfft = null;
	private Echantillon ech = null;

	// Fenetre
	private JFrame frame = null;
	private JPanel contentPane = null;
	private JButton bcalcul = null;

	private JPanel listPane = null;
	private DefaultListModel<Double> ContenueList = null;
	private JList<Double> list = null;
	
	private JTextField puissance=null;
	private JLabel TextPuissance=null;
	private JPanel puissancePanel = null;

	public FFTView(ModuleFFT model, Echantillon ech, MyControllerTest controller) {
		this.myfft = model;
		this.myfft.addObserver(this);
		this.ech = ech;
		this.ech.addObserver(this);
		this.myController = controller;
		buildFrame(model.getModuleList());
	}

	private void buildFrame(ArrayList<Double> mod) {
		frame = new JFrame();
		contentPane = new JPanel();
		listPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		puissancePanel = new JPanel();
		puissancePanel.setLayout(new GridLayout(1,2));
		puissance = new JTextField();
		TextPuissance = new JLabel("taille Fenetre : 2^N \n => N = ");
		puissancePanel.add(TextPuissance);
		puissancePanel.add(puissance);

		JButton bcalcul = new JButton("Calcul de la FFT");
		bcalcul.addActionListener(this);
		contentPane.add(bcalcul, BorderLayout.NORTH);

		ContenueList = new DefaultListModel<Double>();
		list = new JList<Double>(ContenueList);
		for (int i = 0; i < mod.size(); i++) {
			if (mod.get(i) != null) {
				ContenueList.addElement(mod.get(i));
			}
		}
		list.setVisible(true);
		listPane.add(list);

		contentPane.add(puissancePanel,BorderLayout.SOUTH);
		contentPane.add(listPane, BorderLayout.CENTER);

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
		if (o instanceof ModuleFFT) {
			if (arg instanceof ArrayList<?>) {
				ContenueList = new DefaultListModel<Double>();
				list = new JList<Double>(ContenueList);
				for (int i = 0; i < ((ArrayList<Double>) arg).size(); i++) {
					ContenueList.addElement(((ArrayList<Double>) arg).get(i));
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getActionCommand();
		LOGGER.debug(source.toString());
		if (source == "Calcul de la FFT") {
			myController.addModule(ech);
			myController.notifyPuissanceChanged(Integer.parseInt(puissance.getText()));
		}

	}

}
