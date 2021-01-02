import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Bezier_Curve_Drawing extends Applet implements ActionListener {
    private Button drawButton,clearButton;
    private int X[];
    private int Y[];
    private double coEfficents[];
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
            bezierCurve();
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
    private void bezierCurve(){
        int controlPoints=X.length;
        coEfficents=new double[controlPoints+1];
        C_n_k(controlPoints-1); //calculate the values of coefficients to later use
        for (double u = 0.0; u < 1.0; u += 0.0005){
            //for each iteration generate point for curve based on all control points
            generateCurvePoints(u,controlPoints);
        }
    }
    private void generateCurvePoints(double u,int controlPoints){
        double blend;
        double x=0;
        double y=0;
        int n=controlPoints-1;
        for(int k=0;k<controlPoints;k++){
            blend=coEfficents[k]*(Math.pow(u,k))*Math.pow(1-u,n-k);
            x+=(double)X[k]*blend;
            y+=(double)Y[k]*blend;
        }
        Graphics g=getGraphics();
        g.setColor(Color.BLACK);
        g.drawOval((int)x,(int)y,1,1);
    }
    private int factorial(int n){
        int factor=1;
        while(n>1){
            factor*=n;
            n--;
        }
        return factor;
    }
    //To perform n!/(k!(n-k)!
    public void C_n_k(int n){
        int nFact=factorial(n);
        for(int i=0;i<=n;i++){
            coEfficents[i]=(double)(nFact/(factorial(i)*factorial(n-i)));
        }
    }
    private void initializeControlPoints(){
        X=new int[4];
        Y=new int[4];
        X[0]=200;   Y[0]=200;
        X[1]=100;   Y[1]=150;
        X[2]=200;   Y[2]=75;
        X[3]=250;   Y[3]=100;
    }
    private void drawControlPoints(){
        Graphics g=getGraphics();
        g.setColor(Color.RED);
        for(int i=0;i<X.length-1;i++){
            g.drawLine(X[i],Y[i],X[i+1],Y[i+1]);
            g.drawString("P"+(i+1),X[i],Y[i]);
        }
        g.drawString("P"+(X.length),X[X.length-1],Y[Y.length-1]);
    }
}
