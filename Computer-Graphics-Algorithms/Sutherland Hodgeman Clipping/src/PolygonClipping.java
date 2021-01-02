import com.sun.xml.internal.ws.resources.DispatchMessages;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class PolygonClipping extends Applet implements ActionListener  {
	public static List<Integer>X=new ArrayList<Integer>();
	public static List<Integer>Y=new ArrayList<Integer>();
	private TextField editTextXmin,editTextXmax,editTextYmin,editTextYmax;
	private Label textViewXmin,textViewXmax,textViewYmin,textViewYmax;
	private Button addEdgeButton,clearButton,drawClipWindowButton,leftClipButton,rightClipButton,topClipButton,bottomClipButton;
	private int xmin,xmax,ymin,ymax;
	private static List<Integer>Xnew=new ArrayList<Integer>();
	private static List<Integer>Ynew=new ArrayList<Integer>();
	public void init() {
		addEdgeButton=new Button("Draw Polygon");
		clearButton=new Button("Clear");
		leftClipButton=new Button("Clip LEFT");
		rightClipButton=new Button("Clip RIGHT");
		topClipButton=new Button("Clip TOP");
		bottomClipButton=new Button("Clip BOTTOM");
		drawClipWindowButton=new Button("Draw Clip Window");

		
		editTextXmin=new TextField(4);
		editTextXmax=new TextField(4);
		editTextYmin=new TextField(4);
		editTextYmax=new TextField(4);

		
		textViewXmin=new Label("Xmin: ");
		textViewXmax=new Label("Xmax: ");
		textViewYmin=new Label("Ymin: ");
		textViewYmax=new Label("Ymax: ");

		
		add(addEdgeButton);
		add(clearButton);
		add(textViewXmin);add(editTextXmin);
		add(textViewYmin);add(editTextYmin);
		add(textViewXmax);add(editTextXmax);
		add(textViewYmax);add(editTextYmax);
		
		add(drawClipWindowButton);
		add(leftClipButton);
		add(rightClipButton);
		add(topClipButton);
		add(bottomClipButton);
		
		addEdgeButton.addActionListener(this);
		clearButton.addActionListener(this);
		drawClipWindowButton.addActionListener(this);
		leftClipButton.addActionListener(this);
		rightClipButton.addActionListener(this);
		topClipButton.addActionListener(this);
		bottomClipButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource()==addEdgeButton) {
			X.add(50);
			X.add(200);
			X.add(350);
			X.add(350);
			X.add(250);
			X.add(200);
			X.add(150);
			X.add(100);
			X.add(100);
			Y.add(150);
			Y.add(50);
			Y.add(150);
			Y.add(300);
			Y.add(300);
			Y.add(250);
			Y.add(350);
			Y.add(250);
			Y.add(200);
			Graphics g=getGraphics();
			for(int i=0;i<X.size()-1;i++) {
				g.drawLine(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
			}
			g.drawLine(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
		}
		else if(action.getSource()==drawClipWindowButton) {
			xmin=Integer.parseInt(editTextXmin.getText());
			ymin=Integer.parseInt(editTextYmin.getText());
			xmax=Integer.parseInt(editTextXmax.getText());
			ymax=Integer.parseInt(editTextYmax.getText());
			Graphics g=getGraphics();
			g.drawLine(xmin, ymin, xmin , ymax);
			g.drawLine(xmin, ymin, xmax, ymin);
			g.drawLine(xmax, ymin, xmax, ymax);
			g.drawLine(xmax, ymax, xmin, ymax);
		}
		else if(action.getSource()==leftClipButton) {
			for(int i=0;i<X.size()-1;i++) {
				clipl(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
			}
				clipl(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
				X.clear();
				Y.clear();
				for(int i=0;i<Xnew.size();i++){
					X.add(Xnew.get(i));
					Y.add(Ynew.get(i));
				}
				Xnew.clear();
				Ynew.clear();
		}
		else if(action.getSource()==topClipButton) {
			System.out.print("\nX size: "+X.size());
			System.out.print("\nY size: "+Y.size());
			for(int i=0;i<X.size()-1;i++) {
				clipt(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
			}
				clipt(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
				X.clear();
				Y.clear();
				for(int i=0;i<Xnew.size();i++){
					X.add(Xnew.get(i));
					Y.add(Ynew.get(i));
				}
				Xnew.clear();
				Ynew.clear();
		}
		else if(action.getSource()==rightClipButton){
			System.out.print("\nX size: "+X.size());
			System.out.print("\nY size: "+Y.size());
			for(int i=0;i<X.size()-1;i++) {
				clipr(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
			}
				clipr(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
				X.clear();
				Y.clear();
				for(int i=0;i<Xnew.size();i++){
					X.add(Xnew.get(i));
					Y.add(Ynew.get(i));
				}
				Xnew.clear();
				Ynew.clear();
		}
		else if(action.getSource()==bottomClipButton){
			
			for(int i=0;i<X.size()-1;i++) {
				clipb(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
			}
				clipb(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
				printAfterClip();
		}
		else if(action.getSource()==clearButton){
			Graphics g=getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0,0,getSize().height,getSize().width);
		}
	}
	void clipl(int xx1,int yy1,int xx2,int yy2)
	{
		float x1=(float)xx1;
		float y1=(float)yy1;
		float x2=(float)xx2;
		float y2=(float)yy2;
		float m;
	    if((x2-x1)!=0)
	        m=(y2-y1)/(x2-x1);
	    else
	        m=100000;
	    if(x1 >= xmin && x2 >= xmin)
	    {
	        Xnew.add((int)x2);
	        Ynew.add((int)y2);
	    }
	    if(x1 < xmin && x2 >= xmin)
	    {
	        Xnew.add((int)xmin);
	        Ynew.add((int)(y1+m*(xmin-x1)));
	        Xnew.add((int)x2);
	        Ynew.add((int)y2);
	        
	    }
	    if(x1 >= xmin  && x2 < xmin)
	    {
	        Xnew.add((int)xmin);
	        Ynew.add((int)(y1+m*(xmin-x1)));
	    }
	}
	void clipt(int xx1,int yy1,int xx2,int yy2)
	{
		float x1=(float)xx1;
		float y1=(float)yy1;
		float x2=(float)xx2;
		float y2=(float)yy2;
	    float m;
		if((y2-y1)!=0)
	        m=(x2-x1)/(y2-y1);
	    else
	        m=100000;
	    if(y1 <= ymax && y2 <= ymax)
	    {
	        Xnew.add((int)x2);
	        Ynew.add((int)y2);
	    }
	    if(y1 > ymax && y2 <= ymax)
	    {
	        Xnew.add((int)(x1+m*(ymax-y1)));
	        Ynew.add((int)ymax);
	        Xnew.add((int)x2);
	        Ynew.add((int)y2);
	    }
	    if(y1 <= ymax  && y2 > ymax)
	    {
	        Xnew.add((int)(x1+m*(ymax-y1)));
	        Ynew.add((int)ymax);
	    }
	}

	void clipr(int xx1,int yy1,int xx2,int yy2)
	{
		float x1=(float)xx1;
		float y1=(float)yy1;
		float x2=(float)xx2;
		float y2=(float)yy2;
		float m;
	    if((x2-x1)!=0)
	        m=(y2-y1)/(x2-x1);
	    else
	        m=100000;
	    if(x1 <= xmax && x2 <= xmax)
	    {
	        Xnew.add((int)x2);
	        Ynew.add((int)y2);
	    }
	    if(x1 > xmax && x2 <= xmax)
	    {
	        Xnew.add(xmax);
	        Ynew.add((int)(y1+m*(xmax-x1)));
	        Xnew.add((int)x2);
	        Ynew.add((int)y2);
	    }
	    if(x1 <= xmax  && x2 > xmax)
	    {
	        Xnew.add(xmax);
	        Ynew.add((int)(y1+m*(xmax-x1)));
	    }
	}

	void clipb(int xx1,int yy1,int xx2,int yy2)
	{
		float x1=(float)xx1;
		float y1=(float)yy1;
		float x2=(float)xx2;
		float y2=(float)yy2;
		float m;
	    if((y2-y1)!=0)
	        m=(x2-x1)/(y2-y1);
	    else
	        m=100000;
	    if(y1 >= ymin && y2 >= ymin)
	    {
	    	Xnew.add((int)x2);
	    	Ynew.add((int)y2);
	    }
	    if(y1 < ymin && y2 >= ymin)
	    {
	    	Xnew.add((int)(x1+m*(ymin-y1)));
	    	Ynew.add(ymin);
	    	Xnew.add((int)x2);
	    	Ynew.add((int)y2);
	    }
	    if(y1 >= ymin  && y2 < ymin)
	    {
	    	Xnew.add((int)(x2+m*(ymin-y1)));
	    	Ynew.add(ymin);
	    }
	}
	void printAfterClip() {
		Graphics g=getGraphics();
		g.setColor(Color.RED);
		for(int i=0;i<Xnew.size()-1;i++) {
			g.drawLine(Xnew.get(i), Ynew.get(i), Xnew.get(i+1), Ynew.get(i+1));
		}
		g.drawLine(Xnew.get(Xnew.size()-1), Ynew.get(Xnew.size()-1), Xnew.get(0), Ynew.get(0));
		System.out.print(Xnew);
		System.out.print(Ynew);
		
	}
}