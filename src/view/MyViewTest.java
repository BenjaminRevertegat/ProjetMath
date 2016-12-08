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

public class MyViewTest implements ActionListener, Observer {

	// Mon Controller
	private MyControllerTest myController = null;
	// Mon modèle
	private FFT myfft = null;

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
	

	public MyViewTest(FFT model, MyControllerTest controller, ArrayList<Complex> tab) {
		this.myfft = model;
		this.myfft.addObserver(this);
		this.myController = controller;
		buildFrame(tab);
	}

	private void buildFrame(ArrayList<Complex> tab) {
		frame = new JFrame();
		frame.setSize(1200, 800);		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1,2));
		
		usuelPanel= new JPanel();
		usuelPanel.setLayout(new GridLayout(1,2));
		bsinus =new JButton("sinus");
		bcosinus= new JButton("cosinus");
		
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


	
		frame.setSize(1200, 800);
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
