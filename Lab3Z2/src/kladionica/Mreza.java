package kladionica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashSet;

import kladionica.Polje.status;

public class Mreza extends Panel {

	private Polje matrix[][];
	private ArrayList<Polje> array = new ArrayList<>();
	private Igra ownerI;
	
	public Mreza(Igra ownerI, int m, int n) {
		this.ownerI = ownerI;
		matrix = new Polje[m][n];
		this.setLayout(new GridLayout(m, n, 3, 3));
		this.setBackground(Color.BLACK);
	//	this.setPreferredSize(new Dimension(4 * Polje.defaultSize, 5 * Polje.defaultSize));
		populateMreza();
	}
	
	public Mreza(Igra ownerI) {
		this(ownerI, 4, 5);
	}
	
	private void populateMreza() {
		
		int t = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = new Polje(this, t);
				t++;
				this.add(matrix[i][j]);
			}
		}		
	}

	
	public ArrayList getArray() {
		return array;
	}
	
	public HashSet getHashSet() {
		
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j].getStatus().equals(status.IZABRANO))
					set.add(matrix[i][j].getLabel());
			}
		}
		return set;
	}
	
	public int getRows() {
		return matrix.length;
	}
	
	public int getColumns() {
		return matrix[0].length;
	}
	
	public void mrezaStatusChanged(Polje polje) {
		
		if(polje.getStatus().equals(status.IZABRANO))
			array.add(polje);
		else
			array.remove(polje);
		ownerI.statusChanged();
	}
	
	
}
