/*
 * Bresenham Line Drawing Algorithm
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Drawing extends Applet implements ActionListener{
	TextField editTextX1,editTextX2,editTextY1,editTextY2;
	Label textViewX1,textViewX2,textViewY1,textViewY2;
	Button drawButton,clearButton;
	public void init() {
		editTextX1=new TextField();
		editTextX2=new TextField();
		editTextY1=new TextField();
		editTextY2=new TextField();
		
		editTextX1.setColumns(10);
		editTextX2.setColumns(10);
		editTextY1.setColumns(10);
		editTextY2.setColumns(10);
		
		textViewX1=new Label("X1: ");
		textViewX2=new Label("X2: ");
		textViewY1=new Label("Y1: ");
		textViewY2=new Label("Y2: ");
		
		drawButton=new Button("Draw");
		clearButton=new Button("Clear");
		
		add(textViewX1);
		add(editTextX1);
		add(textViewY1);
		add(editTextY1);
		
		add(textViewX2);
		add(editTextX2);
		add(textViewY2);
		add(editTextY2);
		
		add(drawButton);
		add(clearButton);
		drawButton.addActionListener(this);
		clearButton.addActionListener(this);		
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		String strx;
		if(a.getSource()==drawButton) {
			Graphics g=getGraphics();
			g.setColor(Color.RED);
			int x1=Integer.parseInt(editTextX1.getText());
			int x2=Integer.parseInt(editTextX2.getText());
			int y1=Integer.parseInt(editTextY1.getText());
			int y2=Integer.parseInt(editTextY2.getText());
			int dx=Math.abs(x2-x1);
			int dy=Math.abs(y2-y1);
			int delta2Y=2*dy;
			int delta2X=2*dx;
			int p=delta2Y-dx;
			int x,y,steps;
			if(x1>x2) {
				x=x2;
				y=y2;
				steps=x1;
			}
			else {
				x=x1;
				y=y1;
				steps=x2;
			}
			g.drawOval(x, y, 1, 1);
			strx="("+x+","+y+")"+" , ";
			while(x<steps) {
				x++;
				if(p<0) {
					p+=delta2Y;
				}
				else {
					y++;
					p+=delta2Y-delta2X;
				}
				g.drawOval(x, y,1, 1);
				strx=strx+"("+x+","+y+")"+" , ";
			}
			g.drawString(strx, 300, 300);	//display the co-ordinate values as generated in the algorithm
		}
		else if(a.getSource()==clearButton) {
			Graphics g=getGraphics();	//clear screen and text fields
			Dimension d = getSize();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, d.width, d.height);
			editTextX1.setText("");
			editTextX2.setText("");
			editTextY1.setText("");
			editTextY2.setText("");	
		}
	}
}
