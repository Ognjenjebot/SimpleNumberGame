package kladionica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

public class Igra extends Frame{

	Mreza net = new Mreza(this);
	Panel controlPanel = new Panel();
	Panel statusBar = new Panel();
	Label randomNumber = new Label();
	Label balans = new Label("0");
	Label kvota = new Label("0");
	Label dobitak = new Label("0");
	TextField textfield = new TextField(5);
	Button igraj = new Button("Igraj");
	
	public Igra() {
		setTitle("Igra");
	//	setBounds(300, 300, 600,500);
		setResizable(true);
		populateWindow();
		pack();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	private void populateWindow() {
		//net = new Mreza(this);  ovde, ili gore
		add(net, BorderLayout.CENTER);
		//net.setPreferredSize(new Dimension(4 * Polje.defaultSize + 4 * 3, 5 * Polje.defaultSize + 5 * 3));
		//ipak radi samo ako se stavi za svaki kanvas prefferedSize(new Dimension(75,75))
		
		//trigger nek bude kad se dugme pritisne
		randomNumber.setText("");
		randomNumber.setForeground(Color.WHITE);
		randomNumber.setFont(new Font("Arial", Font.BOLD, 17));   //posto se slabo videlo
		statusBar.setBackground(Color.GRAY);
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		statusBar.add(randomNumber);
		//promene nek se desavaju u metodi dugmeta Igra
		
		controlPanel.setLayout(new GridLayout(5,1));
		Panel t1 = new Panel(new FlowLayout(FlowLayout.LEFT));
		t1.add(new Label("Balans:"));
		t1.add(balans);
		controlPanel.add(t1);
		
		Panel t2 = new Panel(new FlowLayout(FlowLayout.LEFT));
		t2.add(new Label("Ulog:"));
		textfield.setText("100");
		textfield.addTextListener((ae) -> {
			updateDobitak();
		});
		t2.add(textfield);
		controlPanel.add(t2);
		
		Panel t3 = new Panel(new FlowLayout(FlowLayout.LEFT));
		t3.add(new Label("Kvota:"));
		t3.add(kvota);
		controlPanel.add(t3);
		
		Panel t4 = new Panel(new FlowLayout(FlowLayout.LEFT));
		t4.add(new Label("Dobitak:"));
		t4.add(dobitak);
		controlPanel.add(t4);
		
		Panel t5 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		t5.add(igraj);
		controlPanel.add(t5);
		
		igraj.setEnabled(false);
		
		igraj.addActionListener((ae) -> {
			//TODO: heshset se uzima i gleda se da li je broj od generatora
			//u heshsetu
			HashSet<Integer> set = net.getHashSet();
			int t = Generator.generateNum(0, net.getRows() * net.getColumns());
			randomNumber.setText(Integer.toString(t));
			if(set.contains(t)) {
				statusBar.setBackground(Color.GREEN);
				randomNumber.setBackground(Color.GREEN);
				updateBalans("-" + textfield.getText());
				updateBalans(dobitak.getText());
			}else {
				statusBar.setBackground(Color.RED);
				randomNumber.setBackground(Color.RED);
				updateBalans("-" + textfield.getText());
			}
		});
		
		controlPanel.setBackground(Color.LIGHT_GRAY);
		add(controlPanel, BorderLayout.EAST);
		add(statusBar, BorderLayout.SOUTH);
	}

	public Mreza getMreza() {
		return net;
	}
	
	public void statusChanged() {
		// TODO Auto-generated method stub
		if(!net.getArray().isEmpty()) {
			igraj.setEnabled(true);
		}else
			igraj.setEnabled(false);
		
		updateKvota();
		updateDobitak();
	}
	
		
	
	private void updateDobitak() {
		
		try {
		double d = Double.parseDouble(textfield.getText()); //dobitak.getText();
		double k = Double.parseDouble(kvota.getText());
		dobitak.setText(Double.toString(round((d * k),2 )));
		dobitak.revalidate();
		}catch(NumberFormatException e) {
			System.out.println("Neispravan unos!");  //ili moze negde drugde da se ispise
		}	
	}

	private void updateKvota() {
		double t;
		
		t = ((double)(net.getRows() * net.getColumns()) / (double)net.getArray().size());
		t = round(t, 2);
		kvota.setText(Double.toString(t));
		kvota.revalidate();
		this.updateDobitak();
		
	}

	private void updateBalans(String x) {
		double t = Double.parseDouble(x);  //dobitak
		double k = Double.parseDouble(balans.getText());
		balans.setText(Double.toString(round((t + k), 2)));
		balans.revalidate();
	}
	//sa stackoverflowa	, zaokruzuje na odredjeni broj decimalnih mesta
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static void main(String[] args) {
		new Igra();
	}
	
}
