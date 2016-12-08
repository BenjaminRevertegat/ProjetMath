package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.MyControllerTest;
import model.FFT;

import Main.Main;
import model.Complex;
import model.Echantillon;

public class MyViewTest implements ActionListener, Observer {

	// Mon Controller
	private MyControllerTest myController = null;
	// Mon mod�le
	private Echantillon myEchantillon = null;

	private JFrame frame = null;
	private JPanel contentPane = null;
	private JSpinner spinner = null;
	private JButton button = null;
	private JButton enlever = null;
	private JLabel label = null;
	private JTable table=null;
	private DefaultListModel ContenueList=null;
	private JList list=null;
	private ListSelectionListener l;
	private JTextField reel =null;
	private JTextField imaginaire =null;
	private JPanel modifieComplexPane =null;
	private JPanel listPane = null;
	private JLabel labelImg =null;
	private JLabel labelRel =null;
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
	
	
	
	
	
	public MyViewTest(Echantillon model, MyControllerTest controller) {
		this.myEchantillon = model;
		this.myEchantillon.addObserver(this);
		this.myController = controller;
		buildFrame(model.getSignal());
	}

	private void buildFrame(ArrayList<Complex> tab) {
		frame = new JFrame();	
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1,2));
		
		//boutons pour les fcts usuelles
		usuelPanel= new JPanel();
		usuelPanel.setLayout(new GridLayout(3,1));
		bsinus =new JButton("sinus");
		bcosinus= new JButton("cosinus");
		usuelPanel.add(bcosinus);
		usuelPanel.add(bsinus);
		// param�tres pour les fcts usuelles
		Pas=new JTextField();
		Debut=new JTextField();
		Fin=new JTextField();
		TextPas=new JLabel("Choissisez le pas");
		TextDebut=new JLabel("debut de l'intervalle");
		TextFin=new JLabel("fin de l'intervalle");
		choixPanel = new JPanel();
		choixPanel.setLayout(new GridLayout(2,3));
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
		modifieComplexPane =new JPanel();
		modifieComplexPane.setLayout(new GridLayout(3,2));
		labelRel= new JLabel("Re()=");
		labelRel.setSize(20,10);
		modifieComplexPane.add(labelRel);
		reel = new JTextField();
		modifieComplexPane.add(reel);
		labelImg =  new JLabel("Img()=");
		labelImg.setSize(20,10);
		modifieComplexPane.add(labelImg);		
		imaginaire = new JTextField();
		modifieComplexPane.add(imaginaire);
		modifieComplexPane.add(button);
		
		//suppr un complex
		enlever = new JButton("Supprimer");
		enlever.addActionListener(this);
		enlever.setName("Enlever");
		modifieComplexPane.add(enlever);
		
		//affichage de la liste des complexes
		listPane = new JPanel();
		ContenueList =new DefaultListModel();
        list = new JList(ContenueList);
	list.addListSelectionListener(l);
	
		for (int i=0; i<tab.size(); i++)
		{

			ContenueList.addElement(tab.get(i));;
			
		}	
		list.setVisible(true);
		listPane.add(list);
		
		contentPane.add(usuelPanel);
		contentPane.add(modifieComplexPane);
		contentPane.add(listPane);


		

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
	
	public void setFrameSize(int a, int b)
	{
		frame.setSize(a, b);	
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
		
	
		Object composant = e.getSource();
		composant.getClass();
		System.out.println(composant.getClass().getName());
		if (composant.getClass().getName()=="Ajouter")
		{
			ContenueList.addElement(new Complex((int)(Math.random()*100),(int)(Math.random()*100)));
		}
		if (composant.getClass().getName()=="Enlever")
		{
			ContenueList.remove(0);
		}
	}
	
	
	public void valueChanged(ListSelectionEvent e) {
		
      System.out.println("azerazeraz");
      
  }
	
	
	

}
