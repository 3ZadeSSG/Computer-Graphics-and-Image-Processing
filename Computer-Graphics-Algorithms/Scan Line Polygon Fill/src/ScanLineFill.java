import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;


public class ScanLineFill extends Applet implements ActionListener {
    public static java.util.List<Integer> X=new ArrayList<Integer>();
    public static List<Integer> Y=new ArrayList<Integer>();
    public List<Integer>tempVertexList=new ArrayList<Integer>();
    private int pixelTable[][];
    private Button drawButton,clearButton,fillButton;

    public void init(){
        add(drawButton=new Button("Draw"));
        add(fillButton=new Button("Scan Line Fill"));
        add(clearButton=new Button("Clear"));
        drawButton.addActionListener(this);
        fillButton.addActionListener(this);
        clearButton.addActionListener(this);
        pixelTable=new int[800][800];
        initializeComponents();
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource()==drawButton){
            drawObject();
        }
        else if(action.getSource()==fillButton){
            scanLineFill();
        }
        else if(action.getSource()==clearButton){
            Graphics g=getGraphics();
            Dimension d=getSize();
            g.setColor(Color.white);
            g.fillRect(0,0,d.width,d.height);
            X=new ArrayList<Integer>();
            Y=new ArrayList<Integer>();
            initializeComponents();
        }
    }
    public void scanLineFill(){
        Graphics g=getGraphics();
        g.setColor(Color.RED);
        int yMax= Collections.max(Y);
        int yMin= Collections.min(Y);
        int xMax=Collections.max(X);
        int xMin=Collections.min(X);
        for(int y=yMin;y<=yMax;y++){
            int prev=0;
            tempVertexList=new ArrayList<>();
            for(int x=xMin;x<=xMax;x++){
                if(pixelTable[x][y]==1 && pixelTable[x+1][y]==0){
                    tempVertexList.add(x);
                    prev=1;
                }
                else if(pixelTable[x][y]==0){
                    prev=0;
                }
            }
            if(tempVertexList.size()%2==0) {
                for (int i = 0; i < tempVertexList.size() - 1; i = i + 2) {
                    g.drawLine(tempVertexList.get(i), y, tempVertexList.get(i + 1), y);
                }
            }
            else{
                for (int i = 0; i < tempVertexList.size() - 1; i = i + 1) {
                    g.drawLine(tempVertexList.get(i), y, tempVertexList.get(i + 1), y);
                }
            }
        }
    }
    public void initializeComponents(){
        X.add(10);Y.add(140);
        X.add(30);Y.add(110);
        X.add(40);Y.add(50);
        X.add(50);Y.add(40);
        X.add(110);Y.add(30);
        X.add(140);Y.add(10);

        for(int i=0;i<800;i++){
            for(int j=0;j<800;j++){
                pixelTable[i][j]=0;
            }
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
