/*
 * DDA Line Drawing Algorithm
 */
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class DrawingUsingWindow extends Applet implements ActionListener {
	TextField editTextX1,editTextX2,editTextY1,editTextY2;
	Label textViewX1,textViewX2,textViewY1,textViewY2;
	int x1,x2,y1,y2;
	Button drawButton,clearButton;
	public void init() {
		editTextX1=new TextField();
		editTextX2=new TextField();
		editTextY1=new TextField();
		editTextY2=new TextField();
		
		textViewX1=new Label("X1: ");
		textViewX2=new Label("X2: ");
		textViewY1=new Label("Y1: ");
		textViewY2=new Label("Y2: ");
		
		drawButton=new Button("Draw");
		clearButton=new Button("Clear");
		
		editTextX1.setColumns(10);
		editTextX2.setColumns(10);
		editTextY1.setColumns(10);
		editTextY2.setColumns(10);
		
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==drawButton) {
			Graphics g=getGraphics();
			String strx="";
			x1=Integer.parseInt(editTextX1.getText());
			x2=Integer.parseInt(editTextX2.getText());
			y1=Integer.parseInt(editTextY1.getText());
			y2=Integer.parseInt(editTextY2.getText());
			int dx=x2-x1;
			int dy=y2-y1;
			int steps;
			float xIncrement=0,yIncrement=0,x,y;
			x=x1;
			y=y1;
			if(Math.abs(dx)>Math.abs(dy)) {
				steps=Math.abs(dx);
			}
			else {
				steps=Math.abs(dy);
			}
			xIncrement=dx/(float)steps;
			yIncrement=dy/(float)steps;
			for(int i=0;i<steps;i++) {
				g.drawOval((int)x, (int)y,1, 1);
				strx=strx+"("+(int)x+","+(int)y+") , ";
				x+=xIncrement;
				y+=yIncrement;
			}
			g.drawString(strx, 300, 300);
		}
		else if(e.getSource()==clearButton) {
			Graphics g=getGraphics();
			Dimension d=getSize();		//clear screen and text fields
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, d.width, d.height);
			editTextX1.setText("");
			editTextX2.setText("");
			editTextY1.setText("");
			editTextY2.setText("");	
		}
	}
}
