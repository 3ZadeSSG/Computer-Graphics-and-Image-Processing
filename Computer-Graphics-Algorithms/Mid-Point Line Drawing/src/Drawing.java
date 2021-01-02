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
		if(a.getSource()==drawButton) {
			Graphics g=getGraphics();
			g.setColor(Color.RED);
			String strx;
			int width=1;
			int height=1;
			int x1=Integer.parseInt(editTextX1.getText());
			int x2=Integer.parseInt(editTextX2.getText());
			int y1=Integer.parseInt(editTextY1.getText());
			int y2=Integer.parseInt(editTextY2.getText());
			
			int dx=x2-x1;
			int dy=y2-y1;
			int decisionParameter=dy-(dx/2);
			int x=x1;
			int y=y1;
			g.fillOval(x, y, width, height);
			strx="("+x+","+y+"), ";
			while(x<x2) {
				x++;
				if(decisionParameter<0) {
					decisionParameter+=dy;
				}
				else {
					decisionParameter+=dy-dx;
					y++;
				}
				g.fillOval(x, y, width, height);
				strx=strx+"("+x+","+y+"), ";
			}
			g.drawString(strx, 300, 300);
		}
		else if(a.getSource()==clearButton) {
			Graphics g=getGraphics();
			Dimension d=getSize();
			g.setColor(Color.WHITE);
			g.fillOval(0, 0, d.width, d.height);
			editTextX1.setText("");
			editTextX2.setText("");
			editTextY1.setText("");
			editTextY2.setText("");
		}
	}
}
