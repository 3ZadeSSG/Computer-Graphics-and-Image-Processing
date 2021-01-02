import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CohenLineClip extends Applet implements ActionListener {
    TextField editTextX1,editTextX2,editTextY1,editTextY2,editTextXmin,editTextXmax,editTextYmin,editTextYmax;
    Label textViewX1,textViewX2,textViewY1,textViewY2,textViewXmin,textViewXmax,textViewYmin,textViewYmax;
    Button drawButton,clearButton,clipButton;
    int INSIDE = 0; // 0000
    int LEFT = 1;   // 0001
    int RIGHT = 2;  // 0010
    int BOTTOM = 4; // 0100
    int TOP = 8;    // 1000
    int x_min;
    int x_max;
    int y_min;
    int y_max;
    int colorCode=0;
    public void init() {
        editTextX1=new TextField();	editTextX2=new TextField();
        editTextY1=new TextField();	editTextY2=new TextField();
        editTextXmin=new TextField(); editTextXmax=new TextField();
        editTextYmin=new TextField(); editTextYmax=new TextField();
        editTextX1.setColumns(5); editTextX2.setColumns(5);
        editTextY1.setColumns(5); editTextY2.setColumns(5);
        textViewX1=new Label("X1: "); textViewX2=new Label("X2: ");
        textViewY1=new Label("Y1: "); textViewY2=new Label("Y2: ");
        textViewXmin=new Label("X min: "); textViewXmax=new Label("X max: ");
        textViewYmin=new Label("Y min: "); textViewYmax=new Label("Y max: ");
        drawButton=new Button("Draw");
        clearButton=new Button("Clear");
        clipButton=new Button("Clip");
        add(textViewX1); add(editTextX1);
        add(textViewY1); add(editTextY1);
        add(textViewX2); add(editTextX2);
        add(textViewY2); add(editTextY2);
        add(drawButton);
        add(clearButton);
        add(textViewXmin); add(editTextXmin);
        add(textViewXmax); add(editTextXmax);
        add(textViewYmin); add(editTextYmin);
        add(textViewYmax); add(editTextYmax);
        add(clipButton);
        drawButton.addActionListener(this);
        clearButton.addActionListener(this);
        clipButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource()==drawButton) {
            colorCode=0;
            int x1=Integer.parseInt(editTextX1.getText());
            int x2=Integer.parseInt(editTextX2.getText());
            int y1=Integer.parseInt(editTextY1.getText());
            int y2=Integer.parseInt(editTextY2.getText());
            drawLine(x1,x2,y1,y2);
        }
        else if(action.getSource()==clearButton) {
            Graphics g=getGraphics();
            Dimension d=getSize();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, d.width+d.height, d.height+d.width);
            editTextX1.setText("");
            editTextX2.setText("");
            editTextY1.setText("");
            editTextY2.setText("");
            colorCode=0;
        }
        else if(action.getSource()==clipButton) {
            int Xmin=Integer.parseInt(editTextXmin.getText());
            int Xmax=Integer.parseInt(editTextXmax.getText());
            int Ymin=Integer.parseInt(editTextYmin.getText());
            int Ymax=Integer.parseInt(editTextYmax.getText());
            Graphics g=getGraphics();
            int w=Xmax-Xmin;
            int h=Ymax-Ymin;

            //g.drawRect(Xmin, Ymin, Xmax-Xmin, Ymax-Ymin);
            //g.drawRect(Xmin,Ymin,w,h);

            doClipping();
        }
    }
    public void doClipping() {
        x_min=Integer.parseInt(editTextXmin.getText());
        x_max=Integer.parseInt(editTextXmax.getText());
        y_min=Integer.parseInt(editTextYmin.getText());
        y_max=Integer.parseInt(editTextYmax.getText());
        int x1=Integer.parseInt(editTextX1.getText());
        int x2=Integer.parseInt(editTextX2.getText());
        int y1=Integer.parseInt(editTextY1.getText());
        int y2=Integer.parseInt(editTextY2.getText());
        cohenShuterlandClip(x1,y1,x2,y2);
    }
    public void cohenShuterlandClip(double x1, double y1,double x2, double y2) {
        Graphics g=getGraphics();
        int code1 = computeCode(x1, y1);
        int code2 = computeCode(x2, y2);
        boolean accept = false;
        while (true){
            if ((code1 == 0) && (code2 == 0)){
                accept = true;
                break;
            }
            else if ((code1 & code2)!=0){
                break;
            }
            else{
                int code_out;
                double x=0, y=0;
                if (code1 != 0) {
                    code_out = code1;
                }
                else {
                    code_out = code2;
                }
                if ((code_out & TOP)!=0){
                    x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
                    y = y_max;
                }
                else if ((code_out & BOTTOM)!=0){
                    x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
                    y = y_min;
                }
                else if ((code_out & RIGHT)!=0){
                    y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
                    x = x_max;
                }
                else if ((code_out & LEFT)!=0){

                    y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
                    x = x_min;
                }
                if (code_out == code1){
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                }
                else{
                    x2 = x;
                    y2 = y;
                    code2 = computeCode(x2, y2);
                }
            }
        }
        if (accept){
            colorCode=1;
            drawLine((int)x1,(int)x2,(int)y1,(int)y2);
            //g.drawString(x1+" "+x2+" "+y1+" "+y2, 100, 100);
        }
    }
    public int computeCode(double x, double y)
    {
        int code = INSIDE;

        if (x < x_min)
            code |= LEFT;
        else if (x > x_max)
            code |= RIGHT;
        if (y < y_min)
            code |= BOTTOM;
        else if (y > y_max)
            code |= TOP;
        return code;
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


