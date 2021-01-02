import java.awt.*;
import java.applet.*;
public class Drawing extends Applet {
	int width=1;
	int height=1;
	public void paint(Graphics g) {
		int x1=10;
		int x2=50;
		int y1=5;
		int y2=80;
		int dx=x2-x1;
		int dy=y2-y1;
		int steps;
		float xIncrement = 0,yIncrement=0,x,y;
		x=x1;
		y=y1;
		if((Math.abs(dx))>(Math.abs(dy))){
			steps=Math.abs(dx);
		}
		else{
			steps=Math.abs(dy);
			xIncrement=dx/(float)steps;
			yIncrement=dy/(float)steps;
		}
		x=x1;
		y=y1;
		for(int i=1;i<=steps;i++){
			x+=xIncrement;
			y+=yIncrement;
			g.drawOval((int)x, (int)y, width, height);
		}
	}
}
