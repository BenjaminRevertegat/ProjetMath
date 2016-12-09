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
import model.FonctionUsuelle;

public class InfoView implements ActionListener, Observer {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoView.class);
	// Mon Controller
	private MyControllerTest myController = null;
	// Mon modèle
	private Echantillon myEchantillon = null;
	private FonctionUsuelle usu = null;

	// Fenetre
	private JFrame frame = null;
	private JPanel contentPane = null;

	// List des complexe + supprimer complexe
	private JPanel listPane = null;
	private DefaultListModel<Complex> ContenueList = null;
	private JList<Complex> list = null;
	private JButton enlever = null;
	private JButton button = null;

	//ajout d'un réel
	private JTextField reel = null;
	private JTextField imaginaire = null;
	private JPanel modifieComplexPane = null;
	private JLabel labelImg = null;
	private JLabel labelRel = null;
	
	//Fonction usuelle
	private JButton bsinus = null;
	private JButton bcosinus = null;
	private JPanel usuelPanel = null;
	private JTextField Pas=null;
	private JTextField Debut=null;
	private JTextField Fin=null;
	private JLabel TextPas=null;
	private JLabel TextDebut=null;
	private JLabel TextFin=null;
	private JPanel choixPanel = null;
	
	private JTextField puissance=null;
	private JLabel TextPuissance=null;
	private JPanel puissancePanel = null;

	public InfoView(Echantillon model, FonctionUsuelle usu, MyControllerTest controller) {
		this.myEchantillon = model;
		this.myEchantillon.addObserver(this);
		this.usu = usu;
		this.usu.addObserver(this);
		this.myController = controller;
		buildFrame(model.getSignal());
	}

	private void buildFrame(ArrayList<Complex> signal) {
		frame = new JFrame();
		listPane = new JPanel();
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		
		puissancePanel = new JPanel();
		puissancePanel.setLayout(new GridLayout(2, 1));
		puissance = new JTextField();
		TextPuissance = new JLabel("taille Fenetre : 2^N \n => N = ");
		puissancePanel.add(TextPuissance);
		puissancePanel.add(puissance);

		// boutons pour les fcts usuelles
		usuelPanel = new JPanel();
		usuelPanel.setLayout(new GridLayout(3, 1));
		bsinus = new JButton("sinus");
		bsinus.addActionListener(this);
		bcosinus = new JButton("cosinus");
		bcosinus.addActionListener(this);
		usuelPanel.add(bcosinus);
		usuelPanel.add(bsinus);
		
		// paramètres pour les fcts usuelles
		Pas = new JTextField();
		Debut = new JTextField();
		Fin = new JTextField();
		TextPas = new JLabel("Pas :");
		TextDebut = new JLabel("Debut :");
		TextFin = new JLabel("Fin :");
		choixPanel = new JPanel();
		choixPanel.setLayout(new GridLayout(2, 3));
		choixPanel.add(TextPas);
		choixPanel.add(TextDebut);
		choixPanel.add(TextFin);
		choixPanel.add(Pas);
		choixPanel.add(Debut);
		choixPanel.add(Fin);
		usuelPanel.add(choixPanel);

		// ajout des champs pour ajouter des complexes
		button = new JButton("Ajouter");
		button.addActionListener(this);
		button.setName("Ajouter");
		modifieComplexPane = new JPanel();
		modifieComplexPane.setLayout(new GridLayout(3, 2));
		labelRel = new JLabel("Re()=");
		labelRel.setSize(20, 10);
		modifieComplexPane.add(labelRel);
		reel = new JTextField();
		modifieComplexPane.add(reel);
		labelImg = new JLabel("Img()=");
		labelImg.setSize(20, 10);
		modifieComplexPane.add(labelImg);
		imaginaire = new JTextField();
		modifieComplexPane.add(imaginaire);
		modifieComplexPane.add(button);

		// suppr un complex
		enlever = new JButton("Supprimer");
		enlever.addActionListener(this);
		enlever.setName("Enlever");
		modifieComplexPane.add(enlever);

		// affichage de la liste des complexes
		ContenueList = new DefaultListModel<Complex>();
		list = new JList<Complex>(ContenueList);
		for (int i = 0; i < signal.size(); i++) {
			ContenueList.addElement(signal.get(i));
		}
		list.setVisible(true);
		listPane.add(list);

		contentPane.add(puissancePanel);
		contentPane.add(usuelPanel);
		contentPane.add(modifieComplexPane);
		contentPane.add(listPane);

		frame.setContentPane(contentPane);
		frame.setTitle("Informations");
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
		if (o instanceof Echantillon) {
			if (arg instanceof ArrayList<?>) {
				ContenueList.removeAllElements();
				for (int i = 0; i < ((ArrayList<Complex>) arg).size(); i++) {
					ContenueList.addElement(((ArrayList<Complex>) arg).get(i));
					;
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getActionCommand();
		LOGGER.debug(source.toString());
		if (source == "Supprimer") {
			myController.removeLastElementSignal(myEchantillon.getSignal());
		}
		if (source == "Ajouter") {
			myController.addElementSignal(
					new Complex(Double.parseDouble(reel.getText()), Double.parseDouble(imaginaire.getText())));
		}
		if (source == "cosinus") {
			myController.notifyPasChanged(Double.parseDouble(Pas.getText()));
			myController.notifyDebutChanged(Double.parseDouble(Debut.getText()));
			myController.notifyFinChanged(Double.parseDouble(Fin.getText()));
			myController.effectueCos();
			LOGGER.debug("Cos effectué");
		}
		if (source == "sinus") {
			myController.notifyPasChanged(Double.parseDouble(Pas.getText()));
			myController.notifyDebutChanged(Double.parseDouble(Debut.getText()));
			myController.notifyFinChanged(Double.parseDouble(Fin.getText()));
			myController.effectueSin();
			LOGGER.debug("Sin effectué");
		}
		// pour la fft :
		//myController.notifyPuissanceChanged(Integer.parseInt(puissance.getText()));
	}

}
