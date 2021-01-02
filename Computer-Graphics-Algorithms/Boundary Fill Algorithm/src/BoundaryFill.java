import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;
public class BoundaryFill extends Applet implements ActionListener {
    public static java.util.List<Integer> X=new ArrayList<Integer>();
    public static List<Integer> Y=new ArrayList<Integer>();
    private int pixelTable[][];
    private Button fillButton,drawButton,clearButton;
    private int colorCode=1; //2=RED, 1=BLACK, 0=WHITE

    public void init(){
        add(drawButton=new Button("Draw Object"));
        add(fillButton=new Button("Fill"));
        add(clearButton=new Button("Clear"));
        drawButton.addActionListener(this);
        fillButton.addActionListener(this);
        clearButton.addActionListener(this);
        pixelTable=new int[800][800];
        initializeDefaultValues();
    }
    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource()==drawButton){
            drawObject();
        }
        else if(action.getSource()==clearButton){
            Graphics g=getGraphics();
            Dimension d=getSize();
            g.setColor(Color.white);
            g.fillRect(0,0,d.width,d.height);
            X=new ArrayList<Integer>();
            Y=new ArrayList<Integer>();
            initializeDefaultValues();
        }
        else if(action.getSource()==fillButton){
            Graphics g=getGraphics();
            g.setColor(Color.RED);
            boundaryFill(51,60,2,1,g);
        }
    }
    private void initializeDefaultValues() {
        /*
        X.add(100);Y.add(100);
        X.add(150);Y.add(100);
        X.add(150);Y.add(150);
        X.add(100);Y.add(150);
        */

        //X[25, 100, 175, 175, 125, 100, 75, 50, 50]
        //Y[75, 25, 75, 150, 150, 125, 175, 125, 100]

        //X[12, 50, 87, 87, 62, 50, 37, 25, 25]
        //Y[37, 12, 37, 75, 75, 62, 87, 62, 50] fadfa
        for(int i=0;i<800;i++){
            for(int j=0;j<800;j++){
                pixelTable[i][j]=0;
            }
        }
        X.add(12);Y.add(37);
        X.add(50);Y.add(12);
        X.add(87);Y.add(37);
        X.add(87);Y.add(75);
        X.add(62);Y.add(75);
        X.add(50);Y.add(62);
        X.add(37);Y.add(87);
        X.add(25);Y.add(62);
        X.add(25);Y.add(50);
    }

    void boundaryFill(int x,int y,int fillColor,int boundaryColor,Graphics g)
    {
        if((x>=0 && x<=800)&&(x>=0 && x<=800)&&pixelTable[x][y]!=boundaryColor && pixelTable[x][y]!=fillColor)
        {
            pixelTable[x][y]=fillColor;
            g.drawOval(x,y,1,1);
            boundaryFill(x+1,y,fillColor,boundaryColor,g);
            boundaryFill(x,y+1,fillColor,boundaryColor,g);
            boundaryFill(x-1,y,fillColor,boundaryColor,g);
            boundaryFill(x,y-1,fillColor,boundaryColor,g);
        }
    }
    private void drawObject(){
        //use the X and Y list to draw the object with color selection represented by color code
        System.out.println(X);
        System.out.println(Y);
        Graphics g=getGraphics();
        g.setColor(Color.BLACK);
        for(int i=0;i<X.size()-1;i++) {
            //g.drawLine(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
            drawLine(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));

        }
        //g.drawLine(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
        drawLine(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
    }
    private void drawLine(int x0,int y0,int x1,int y1) {

        if (abs(y1 - y0) < abs(x1 - x0)) {
            if (x0 > x1)
                plotLineLow(x1, y1, x0, y0);
            else
                plotLineLow(x0, y0, x1, y1);
        }
        else{
            if (y0 > y1)
                plotLineHigh(x1, y1, x0, y0);
            else
                plotLineHigh(x0, y0, x1, y1);
        }
    }
    public void plotLineLow(int x0,int y0,int x1,int y1) {
        Graphics g=getGraphics();
        g.setColor(Color.BLACK);
        int dx = x1 - x0;
        int dy = y1 - y0;
        int yi = 1;
        if (dy< 0) {
            yi = -1;
            dy = -dy;
        }
        int D = 2 * dy - dx;
        int y = y0;
        for (int x =x0 ;x<= x1;x++) {
            //   plot(x, y);
            g.drawOval(x,y,1,1);
            pixelTable[x][y]=1;
            if(D > 0) {
                y = y + yi;
                D = D - 2 * dx;
            }
            D = D + 2 * dy;
        }
    }
    public void plotLineHigh(int x0,int y0,int x1,int y1){
        Graphics g=getGraphics();
        int dx = x1 - x0;
        int dy = y1 - y0;
        int xi = 1;
        if (dx < 0) {
            xi = -1;
            dx = -dx;
        }
        int D = 2*dx - dy;
        int x = x0;
        for(int y=y0;y<=y1;y++) {
            //plot(x,y)
            g.drawOval(x,y,1,1);
            pixelTable[x][y]=1;
            if(D > 0) {
                x = x + xi;
                D = D - 2 * dy;
            }
            D = D + 2 * dx;
        }
    }
}
