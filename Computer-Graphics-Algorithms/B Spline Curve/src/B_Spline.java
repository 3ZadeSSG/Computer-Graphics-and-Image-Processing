import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class B_Spline extends Applet implements ActionListener {
    private Button drawButton,clearButton;
    private int X[];
    private int Y[];
    private double coEfficents[];
    private int Ti[];
    private double uVec[];
    public void init(){
        add(drawButton=new Button("Draw Button"));
        add(clearButton=new Button("Clear Button"));
        drawButton.addActionListener(this);
        clearButton.addActionListener(this);
        initializeControlPoints();
    }
    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource()==drawButton){
            drawControlPoints();
            //b_Spline_Curve();
            drawBSplineCurve();
        }
        else if(action.getSource()==clearButton){
            clearWindow();
        }
    }
    private void clearWindow(){
        Graphics g=getGraphics();
        Dimension d=getSize();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,d.height,d.width);
    }
    ///////////////////////////////////////////
    private void drawBSplineCurve()
    {
        int n, d;
        d=3;
        n = X.length;
        uVec=new double[n+d];
        int i;
        for(i=0;i<n+d;i++)
        {
            uVec[i]=(((double)i)/(n+d-1));
        }
        double x, y, basis, u;
        for(u=uVec[d-1];u<=uVec[n];u+=0.0005)
        {
            x = 0;
            y = 0;
            for(i=0;i<X.length;i++) {
                basis = blend(u, i, d);
                x += basis * X[i];
                y += basis * Y[i];
            }
            Graphics g=getGraphics();
            g.setColor(Color.RED);
            g.drawOval((int)x,(int)y,1,1);
            System.out.println("X: "+x+" Y:"+y);
        }
    }
    private double blend(double u, int k, int d)
    {
        if(d==1)
        {
            if(uVec[k]<=u && u<uVec[k+1])
                return 1;
            return 0;
        }
        double b;
        b = ((u-uVec[k])/(uVec[k+d-1]-uVec[k])*blend(u, k, d-1)) + ((uVec[k+d]-u)/(uVec[k+d]-uVec[k+1])*blend(u, k+1, d-1));
        return b;
    }
    private void initializeControlPoints(){
        X=new int[4];
        Y=new int[4];
        X[0]=200;   Y[0]=200;
        X[1]=100;   Y[1]=150;
        X[2]=200;   Y[2]=75;
        X[3]=250;   Y[3]=100;
        /*
        X[0]=100;   Y[0]=200;
        X[1]=200;   Y[1]=500;
        X[2]=300;   Y[2]=600;
        X[3]=400;   Y[3]=400;
        X[4]=500;   Y[4]=300;
        X[5]=600;   Y[5]=500;
        */
    }
    private void drawControlPoints(){
        Graphics g=getGraphics();
        g.setColor(Color.BLACK);
        for(int i=0;i<X.length-1;i++){
            g.drawLine(X[i],Y[i],X[i+1],Y[i+1]);
            g.drawString("P"+(i+1),X[i],Y[i]);
        }
        g.drawString("P"+(X.length),X[X.length-1],Y[Y.length-1]);
    }
}
