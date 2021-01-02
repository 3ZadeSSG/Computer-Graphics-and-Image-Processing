import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CircleDrawing extends Applet implements ActionListener{
	private TextField xCenterEditText;
	private TextField yCenterEditText;
	private TextField radiusEditText;
	private Label xCenterTextView;
	private Label yCenterTextView;
	private Label radiusTextView;
	private Button drawButton;
	private Button clearButton;
	public void init() {
		xCenterEditText=new TextField();
		yCenterEditText=new TextField();
		radiusEditText=new TextField();
		xCenterTextView=new Label("X center: ");
		yCenterTextView=new Label("Y center: ");
		radiusTextView=new Label("Radius: ");
		drawButton=new Button("Draw");
		clearButton=new Button("Clear");
		drawButton.addActionListener(this);
		clearButton.addActionListener(this);
		add(xCenterTextView);
		add(xCenterEditText);
		add(yCenterTextView);
		add(yCenterEditText);
		add(radiusTextView);
		add(radiusEditText);
		add(drawButton);
		add(clearButton);
	}
		@Override
		public void actionPerformed(ActionEvent action) {
			Graphics g=getGraphics();
			if(action.getSource()==drawButton) {
				int radius=Integer.parseInt(radiusEditText.getText());
				int xCenter=Integer.parseInt(xCenterEditText.getText());
				int yCenter=Integer.parseInt(yCenterEditText.getText());
				int x=0;
				int y=radius;
				int p=1-radius;
				while(x<y) {
					x++;
					if(p<0) {
						p+=2*x+1;
					}
					else {
						y--;
						p+=2*(x-y)+1;
					}
				Plot(xCenter,yCenter,x,y);	
				}
			}
			else if(action.getSource()==clearButton) {
				xCenterEditText.setText("");
				yCenterEditText.setText("");
				radiusEditText.setText("");
				Dimension d=getSize();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, d.width, d.height);
			}
		}
		public void Plot(int xCenter,int yCenter,int x,int y) {
			Graphics g=getGraphics();
			g.setColor(Color.BLACK);
			g.drawOval(xCenter+x, yCenter+y, 0, 0);
			g.drawOval(xCenter+x, yCenter-y, 0, 0);
			g.drawOval(xCenter-x, yCenter+y, 0, 0);
			g.drawOval(xCenter-x, yCenter-y, 0, 0);
			g.drawOval(xCenter+y, yCenter+x, 0, 0);
			g.drawOval(xCenter+y, yCenter-x, 0, 0);
			g.drawOval(xCenter-y, yCenter+x, 0, 0);
			g.drawOval(xCenter-y, yCenter-x, 0, 0);
		}
}
