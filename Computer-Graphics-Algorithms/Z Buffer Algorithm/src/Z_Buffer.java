import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Z_Buffer extends Applet implements ActionListener {
    private int height=800;
    private int width=800;
    private int zBuffer[][]=new int[800][800];
    private int squareBuffer[][];
    private int rectangleBuffer[][];
    private Button drawButton,clearButton;
    public void init(){
        add(drawButton=new Button("Draw"));
        add(clearButton=new Button("Clear"));
        drawButton.addActionListener(this);
        clearButton.addActionListener(this);
        initialize();
    }
    private void initialize(){
        squareBuffer=new int[height][width];
        rectangleBuffer=new int[height][width];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                zBuffer[i][j]=Integer.MAX_VALUE;
                squareBuffer[i][j]=Integer.MAX_VALUE;
                rectangleBuffer[i][j]=Integer.MAX_VALUE;
            }
        }
        int squareLen=50;
        for(int i=55;i<55+squareLen;i++){
            for(int j=55;j<55+squareLen;j++){
                squareBuffer[i][j]=7;
            }
        }
        int rectangleLen=70;
        int rectangleWid=70;
        for(int i=80;i<80+rectangleLen;i++){
            for(int j=80;j<80+rectangleWid;j++){
                rectangleBuffer[i][j]=5;
            }
        }
    }
    private void doZBuffer(){
        updateBuffer(squareBuffer);
        updateBuffer(rectangleBuffer);
        drawObjects();
    }
    private void updateBuffer(int [][]buffer){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(zBuffer[i][j]>buffer[i][j]){
                    zBuffer[i][j]=buffer[i][j];
                }
            }
        }
    }
    private void drawObjects(){
        Graphics g=getGraphics();
        // code 5 for red
        // cide 7 for black
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(zBuffer[i][j]==5){
                    g.setColor(Color.RED);
                    g.drawOval(i,j,1,1);
                }
                else if(zBuffer[i][j]==7){
                    g.setColor(Color.BLACK);
                    g.drawOval(i,j,1,1);
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource()==drawButton){
            doZBuffer();
            drawObjects();
        }
        else if(action.getSource()==clearButton){
            clearWindow();
        }
    }
    private void clearWindow(){
        Graphics g=getGraphics();
        g.setColor(Color.WHITE);
        Dimension d=getSize();
        g.fillRect(0,0,d.height,d.width);
    }
}
