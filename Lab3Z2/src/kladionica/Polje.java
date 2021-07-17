package kladionica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas {
	
	public static enum status {SLOBODNO, IZABRANO}
	private Mreza ownerM;
	private int number;
	private status currentStatus;
	public static final int defaultSize = 75;
	private int width = defaultSize, height = defaultSize;
	
	public Polje(Mreza ownerM, int number) {
		this.ownerM = ownerM;
		this.number = number;
		this.setBackground(Color.ORANGE);
		this.setPreferredSize(new Dimension(defaultSize, defaultSize));
		currentStatus = status.SLOBODNO;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(currentStatus.equals(status.SLOBODNO)){
					currentStatus = status.IZABRANO;
				}else
					currentStatus = status.SLOBODNO;
				ownerM.mrezaStatusChanged(Polje.this);
				repaint();
			}
		});
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polje other = (Polje) obj;
		if (currentStatus != other.currentStatus)
			return false;
		if (height != other.height)
			return false;
		if (number != other.number)
			return false;
		if (ownerM == null) {
			if (other.ownerM != null)
				return false;
		} else if (!ownerM.equals(other.ownerM))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	@Override
	public void paint(Graphics g) {
		
		
		//centriranje sa stackoverflowa-a
		//https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
		width = this.getWidth();
		height = this.getHeight();
		Font f;
		int fontSize = (this.height >= this.width) ? this.width / 3 : this.height / 3;

		if(currentStatus.equals(status.IZABRANO)) {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, this.width, this.height);
			g.setColor(Color.WHITE);
			g.setFont(f = new Font("Arial", Font.BOLD, fontSize));
			FontMetrics metrics = g.getFontMetrics(f);
			int x = (this.width - metrics.stringWidth(Integer.toString(this.getLabel()))) / 2;
			int y =((this.height - metrics.getHeight()) / 2) + metrics.getAscent();
			g.drawString(Integer.toString(this.getLabel()), x, y);
		}else {		
		g.setColor(Color.BLACK);
		g.setFont(f = new Font("Arial", Font.BOLD, fontSize));  
		FontMetrics metrics = g.getFontMetrics(f);
		int x = (this.width - metrics.stringWidth(Integer.toString(this.getLabel()))) / 2;
		int y =((this.height - metrics.getHeight()) / 2) + metrics.getAscent();
		//System.out.println(x);
		//System.out.println(y);
		g.drawString(Integer.toString(this.getLabel()), x, y);  
		}
	}
	
	public void setStatus(status s) {
		currentStatus = s;
	}
	
	public status getStatus() {
		return currentStatus;
	}
	
	public void setSize() {
		//TODO mozda?
	}
	
	public int getLabel() {
		return number;
	}
		
}
