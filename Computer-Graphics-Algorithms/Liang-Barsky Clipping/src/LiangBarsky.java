import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class LiangBarsky extends Applet implements ActionListener {
    private TextField editTextX1,editTextX2;
    private TextField editTextY1,editTextY2;
    private TextField editTextXmin,editTextXmax;
    private TextField editTextYmin,editTextYmax;
    private Label textViewX1,textViewX2;
    private Label textViewY1,textViewY2;
    private Label textViewXmin,textViewXmax;
    private Label textViewYmin,textViewYmax;
    private Button drawLineButton,clearButton,clipButton;
    private int xmin,ymin,xmax,ymax;
    private int colorCode=0;
    private int x1,x2,y1,y2;
    public void init(){
        editTextX1=new TextField(4);editTextX2=new TextField(4);
        editTextY1=new TextField(4);editTextY2=new TextField(4);
        editTextXmin=new TextField(4);editTextXmax=new TextField(4);
        editTextYmin=new TextField(4);editTextYmax=new TextField(4);
        textViewX1=new Label("X1: ");textViewX2=new Label("X2: ");
        textViewY1=new Label("Y1: ");textViewY2=new Label("Y2: ");
        textViewXmin=new Label("Xmin: ");textViewXmax=new Label("Xmax: ");
        textViewYmin=new Label("Ymin: ");textViewYmax=new Label("Ymax: ");
        drawLineButton=new Button("Draw Line");
        clearButton=new Button("Clear all");
        clipButton=new Button("Clip");

        //========== Add all components to window ============//
        add(textViewX1);add(editTextX1);
        add(textViewY1);add(editTextY1);
        add(textViewX2);add(editTextX2);
        add(textViewY2);add(editTextY2);
        add(drawLineButton);
        add(clearButton);
        add(textViewXmin);add(editTextXmin);
        add(textViewYmin);add(editTextYmin);
        add(textViewXmax);add(editTextXmax);
        add(textViewYmax);add(editTextYmax);
        add(clipButton);
        drawLineButton.addActionListener(this);
        clearButton.addActionListener(this);
        clipButton.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource()==drawLineButton){
        	x1=Integer.parseInt(editTextX1.getText());
        	x2=Integer.parseInt(editTextX2.getText());
        	y1=Integer.parseInt(editTextY1.getText());
        	y2=Integer.parseInt(editTextY2.getText());
        	drawLine(x1,x2,y1,y2);
        }
        else if(action.getSource()==clearButton){
                Graphics g=getGraphics();
                Dimension d=getSize();
                g.setColor(Color.white);
                g.fillRect(0,0,d.width,d.height);
                editTextX1.setText(" ");editTextX2.setText(" ");
                editTextY1.setText(" ");editTextY2.setText(" ");
        }
        else if(action.getSource()==clipButton){
            xmin=Integer.parseInt(editTextXmin.getText());
            ymin=Integer.parseInt(editTextYmin.getText());

            xmax=Integer.parseInt(editTextXmax.getText());
            ymax=Integer.parseInt(editTextYmax.getText());

            Graphics g=getGraphics();
            g.setColor(Color.black);
            g.drawLine(xmin, ymin, xmin , ymax);
			g.drawLine(xmin, ymin, xmax, ymin);
			g.drawLine(xmax, ymin, xmax, ymax);
			g.drawLine(xmax, ymax, xmin, ymax);
            doClip();
        }
    }
    
    
    public void doClip() {
    	colorCode=1;
    	int dx,dy,xx1,xx2,yy1,yy2;
    	double p[]=new double[4];
    	double q[]=new double[4];
    	dx=x2-x1;
        dy=y2-y1;
    
        p[0]=-dx;
        p[1]=dx;
        p[2]=-dy;
        p[3]=dy;    
        q[0]=x1-xmin;
        q[1]=xmax-x1;
        q[2]=y1-ymin;
        q[3]=ymax-y1;
        
        for(int i=0;i<4;i++)
        {
            if(p[i]==0)
            {
                System.out.println("line is parallel to one of the clipping boundary");
                if(q[i]>=0)
                {
                    if(i<2)
                    {
                        if(y1<ymin)
                        {
                            y1=ymin;
                        }
                    
                        if(y2>ymax)
                        {
                            y2=ymax;
                        }
                    
                        drawLine(x1,x2,y1,y2);
                    }
                    
                    if(i>1)
                    {
                        if(x1<xmin)
                        {
                            x1=xmin;
                        }
                        
                        if(x2>xmax)
                        {
                            x2=xmax;
                        }
                        
                        drawLine(x1,x2,y1,y2);
                    }
                }
            }
        }
        double t1=0;
        double t2=1;
        double temp;
        for(int i=0;i<4;i++)
        {
            temp=q[i]/p[i];
            
            if(p[i]<0)
            {
                if(t1<=temp)
                    t1=temp;
            }
            else
            {
                if(t2>temp)
                    t2=temp;
            }
        }
        if(t1<t2)
        {
            xx1 = (int) (x1 + t1 * p[1]);
            xx2 = (int) (x1 + t2 * p[1]);
            yy1 = (int) (y1 + t1 * p[3]);
            yy2 = (int) (y1 + t2 * p[3]);
            drawLine(xx1,xx2,yy1,yy2);
            System.out.print(xx1+" "+yy1+","+xx2+" "+yy2);
        }
    }
    
  //used midpoint approach
  	public void drawLine(int x1,int x2,int y1,int y2) {
  		Graphics g=getGraphics();
  		if(colorCode==0) {
  			g.setColor(Color.BLACK);
  		}
  		else {
  			g.setColor(Color.RED);
  		}
  		int width=1;
  		int height=1;
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
  		}
  	}
}
