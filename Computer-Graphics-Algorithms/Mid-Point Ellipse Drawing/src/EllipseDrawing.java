import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EllipseDrawing extends Applet implements ActionListener {
	private TextField xCenterEditText;
	private TextField yCenterEditText;
	private TextField xRadiusEditText;
	private TextField yRadiusEditText;
	private Label xCenterTextView;
	private Label yCenterTextView;
	private Label xRadiusTextView;
	private Label yRadiusTextView;
	private Button drawButton;
	private Button clearButton;
	private Graphics g;
	
	public void init() {
		xCenterEditText=new TextField(); xCenterEditText.setColumns(5);
		yCenterEditText=new TextField(); yCenterEditText.setColumns(5);
		xRadiusEditText=new TextField(); xRadiusEditText.setColumns(5);
		yRadiusEditText=new TextField(); yRadiusEditText.setColumns(5);
		
		xCenterTextView=new Label("Center X: ");
		yCenterTextView=new Label("Center Y: ");
		xRadiusTextView=new Label("Radius X: ");
		yRadiusTextView=new Label("Radius Y: ");
		
		drawButton=new Button("Draw"); drawButton.addActionListener(this);
		clearButton=new Button("Clear"); clearButton.addActionListener(this);
		
		add(xCenterTextView); add(xCenterEditText);
		add(yCenterTextView); add(yCenterEditText);
		add(xRadiusTextView); add(xRadiusEditText);
		add(yRadiusTextView); add(yRadiusEditText);
		add(drawButton);
		add(clearButton);
		g=getGraphics();
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource()==drawButton) {
			int xCenter=Integer.parseInt(xCenterEditText.getText());
			int yCenter=Integer.parseInt(yCenterEditText.getText());
			int Rx=Integer.parseInt(xRadiusEditText.getText());
			int Ry=Integer.parseInt(yRadiusEditText.getText());
			drawEllipse(xCenter,yCenter,Rx,Ry);
		}
		else if(action.getSource()==clearButton) {
			Dimension d=getSize();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, d.width, d.height);
		}
	}
	private void Plot(int xCenter,int yCenter,int x,int y) {
		int width=1;
		int height=1;
		g.drawOval(xCenter+x, yCenter+y, width, height);
		g.drawOval(xCenter+x, yCenter-y, width, height);
		g.drawOval(xCenter-x, yCenter+y, width, height);
		g.drawOval(xCenter-x, yCenter-y, width, height);
	}
	private void drawEllipse(int xCenter,int yCenter,int Rx,int Ry) {
		g.setColor(Color.BLACK);
		int Rx2=Rx*Rx;
		int Ry2=Ry*Ry;
		int twoRx2=2*Rx2;
		int twoRy2=2*Ry2;
		int p;
		int x=0;
		int y=Ry;
		int px=0;
		int py=twoRx2*y;
		
		Plot(xCenter,yCenter,x,y);
		p=(int)Math.abs(Math.abs(Ry2-(Rx2*Ry)+(0.25*Rx2)));
		while(px<py) {
			x++;
			px+=twoRy2;
			if(p<0) {
				p+=Ry2+px;
			}
			else {
				y--;
				py-=twoRx2;
				p+=Ry2+px-py;
			}
			Plot(xCenter,yCenter,x,y);
		}
		p=(int)Math.round((Ry2*(x+0.5)*(x+0.5))+Rx2*(y-1)*(y-1)-Rx2*Ry2);
		while(y>0) {
			y--;
			py-=twoRx2;
			if(p>0) {
				p+=Rx2-py;
			}
			else {
				x++;
				px+=twoRy2;
				p+=Rx2-py+px;
			}
			Plot(xCenter,yCenter,x,y);
		}
	}
}
