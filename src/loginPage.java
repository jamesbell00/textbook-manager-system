import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

//this class is only created to setup a window and the background with Graphics 2D

public class loginPage extends JPanel {

	public loginPage() {
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
	Rectangle2D b1=new Rectangle2D.Double(125,150,550,250);
	g2.setPaint(Color.white);
	g2.fill(b1);
	g2.setPaint(Color.black);
	g2.draw(b1);
}
	
}
