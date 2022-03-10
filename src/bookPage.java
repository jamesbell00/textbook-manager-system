


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//this class is only created to setup a window and the background with Graphics 2D

public class bookPage extends JPanel {
	
	
	public bookPage(){
		//Setting Panel
		setLayout(null);
		this.setPreferredSize(new Dimension(800,600));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
	//Setting background of header
	Graphics2D g2= (Graphics2D) g;  
	Rectangle2D header= new Rectangle2D.Double(0,0,800,75);
	g2.setPaint(Color.blue.darker());
	g2.fill(header);
	g2.setPaint(Color.black);
	g2.draw(header);

	//Setting background of page content
	Rectangle2D b1=new Rectangle2D.Double(125,90,550,150);
	g2.draw(b1);
	g2.setPaint(Color.white);
	g2.fill(b1);
	g2.setPaint(Color.black);
	Rectangle2D b2=new Rectangle2D.Double(125,240,550,350);
	g2.draw(b2);
	g2.setPaint(Color.white);
	g2.fill(b2);
	g2.setPaint(Color.black);
	Rectangle2D b3=new Rectangle2D.Double(150,290,110,50);
	g2.draw(b3);
	g2.setPaint(Color.white);
	g2.fill(b3);
	g2.setPaint(Color.black);
	Rectangle2D b4=new Rectangle2D.Double(260,290,390,50);
	g2.draw(b4);
	g2.setPaint(Color.white);
	g2.fill(b4);
	g2.setPaint(Color.black);
	Rectangle2D b5=new Rectangle2D.Double(150,340,110,50);
	g2.draw(b5);
	g2.setPaint(Color.white);
	g2.fill(b5);
	g2.setPaint(Color.black);
	Rectangle2D b6=new Rectangle2D.Double(260,340,390,50);
	g2.draw(b6);
	g2.setPaint(Color.white);
	g2.fill(b6);
	g2.setPaint(Color.black);
	Rectangle2D b7=new Rectangle2D.Double(150,390,110,50);
	g2.draw(b7);
	g2.setPaint(Color.white);
	g2.fill(b7);
	g2.setPaint(Color.black);
	Rectangle2D b8=new Rectangle2D.Double(260,390,390,50);
	g2.draw(b8);
	g2.setPaint(Color.white);
	g2.fill(b8);
	g2.setPaint(Color.black);
	Rectangle2D b9=new Rectangle2D.Double(150,470,500,50);
	g2.draw(b9);
	g2.setPaint(Color.white);
	g2.fill(b9);
	g2.setPaint(Color.black);
	
	
}
}