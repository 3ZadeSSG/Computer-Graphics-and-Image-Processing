
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class Transformations extends Applet implements ActionListener, ItemListener {
    private Button drawObjectButton,clearWindowButton,selectButton;
    private static Choice transformationList;
    private static Choice shearList;
    public static List<Integer> X=new ArrayList<Integer>();
    public static List<Integer> Y=new ArrayList<Integer>();
    private static int colorCode=0; //0= Black, 1=Red
    private TextField editXShear,editYShear,editXTranslate,editYTranslate;
    private Label textViewXShear,textViewYShear,textViewXTranslate,textViewYTranslate;
    private TextField editTheta,editXPivot,editYPivot,editScale;
    private Label textViewTheta,textViewXPivot,textViewYPivot,textViewScale;
    private TextField editReflectionMatrix;
    private Label textViewReflectionMatrix;
    public void init(){
        transformationList=new Choice();
        shearList=new Choice();
        transformationList.add("Select Action");
        transformationList.add("Translation"); //
        transformationList.add("Scaling");     //
        transformationList.add("Rotation");    //
        transformationList.add("Shear");       //
        transformationList.add("Reflection");  //
        shearList.add("X Shear");
        shearList.add("Y Shear");
        shearList.add("XY Shear");

        add(drawObjectButton=new Button("Draw"));
        add(clearWindowButton=new Button("Clear Window"));
        add(transformationList);
        add(selectButton=new Button("Select"));
        add(shearList);

        add(textViewXShear=new Label("X Shear: "));add(editXShear=new TextField(3));
        add(textViewYShear=new Label("Y Shear: "));add(editYShear=new TextField(3));
        add(textViewXTranslate=new Label("X Translate: "));add(editXTranslate=new TextField(3));
        add(textViewYTranslate=new Label("Y Translate: "));add(editYTranslate=new TextField(3));
        add(textViewTheta=new Label("Angle: "));add(editTheta=new TextField(3));
        add(textViewXPivot=new Label("X Pivot: "));add(editXPivot=new TextField(3));
        add(textViewYPivot=new Label("Y Pivot: "));add(editYPivot=new TextField(3));
        add(textViewScale=new Label("Scale Factor: ")); add(editScale=new TextField(3));
        add(textViewReflectionMatrix=new Label("Reflection Matrix seperated by ',': ")); add(editReflectionMatrix=new TextField(8));
        drawObjectButton.addActionListener(this);
        clearWindowButton.addActionListener((this));
        selectButton.addActionListener(this);
        transformationList.addItemListener(this);

        initializeDefaultValues();  //to be used to initialized default values for transformation matrices
    }
    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource()==drawObjectButton){
            colorCode=1;
            drawObject();
        }
        else if(action.getSource()==clearWindowButton){
            Graphics g=getGraphics();
            Dimension d=getSize();
            g.setColor(Color.white);
            g.fillRect(0,0,d.width,d.height);
            X=new ArrayList<Integer>();
            Y=new ArrayList<Integer>();
            initializeDefaultValues();
        }
        else if(action.getSource()==selectButton) {
            if (transformationList.getSelectedItem() == "Scaling") {
                double scaleFactor = Double.parseDouble(editScale.getText());
                double scaleMatrix[][] = {{scaleFactor, 0, 0},
                        {0, scaleFactor, 0},
                        {0, 0, 1}};
                //Performing P'=SxP
                transformationOpeartion(scaleMatrix);
                colorCode = 0;
                drawObject();
            } else if (transformationList.getSelectedItem() == "Translation") {
                double xTranslate=Double.parseDouble(editXTranslate.getText());
                double yTranslate=Double.parseDouble(editYTranslate.getText());
                double translationMatrix[][] = {{1, 0, xTranslate},
                        {0, 1, yTranslate},
                        {0, 0, 1}};
                //Performing P'=TxP
                transformationOpeartion(translationMatrix);
                colorCode = 0;
                drawObject();
            } else if (transformationList.getSelectedItem() == "Rotation") {
                double theta=Double.parseDouble(editTheta.getText());
                double xPivot=Double.parseDouble(editXPivot.getText());
                double yPivot=Double.parseDouble(editYPivot.getText());
                double pivotPoint[] = {xPivot, yPivot};
                theta = Math.toRadians(theta);
                double cos_theta = Math.cos(theta);
                double sin_theta = Math.sin(theta);
                System.out.println(cos_theta);
                System.out.println(sin_theta);
                double rotationMatrix[][] = {{cos_theta, -sin_theta, (pivotPoint[0] * (1 - cos_theta)) + (pivotPoint[1] * sin_theta)},
                        {sin_theta, cos_theta, (pivotPoint[1] * (1 - cos_theta)) - (pivotPoint[0] * sin_theta)},
                        {0, 0, 1}};
                transformationOpeartion(rotationMatrix);
                colorCode = 0;
                drawObject();
            } else if (transformationList.getSelectedItem() == "Shear") {
                double xShear = Double.parseDouble(editXShear.getText());
                double yShear = Double.parseDouble(editYShear.getText());
                double xShearMatrix[][] = {{1, xShear, 0},
                        {0, 1, 0},
                        {0, 0, 1}};
                double yShearMatrix[][] = {{1, 0, 0},
                        {yShear, 1, 0},
                        {0, 0, 1}};
                double xyShearMatrix[][] = {{1, xShear, 0},
                        {yShear, 1, 0},
                        {0, 0, 1}};
                if (shearList.getSelectedItem() == "X Shear") {
                    transformationOpeartion(xShearMatrix);
                } else if (shearList.getSelectedItem() == "Y Shear") {
                    transformationOpeartion(yShearMatrix);
                } else if (shearList.getSelectedItem() == "XY Shear") {
                    transformationOpeartion(xyShearMatrix);
                }
                colorCode = 0;
                drawObject();
            } else if (transformationList.getSelectedItem() == "Reflection") {
                    String arr[]=editReflectionMatrix.getText().split(",");
                    double reflectionMatrix[][]=new double[3][3];
                    int index=0;
                    for(int i=0;i<3;i++){
                        for(int j=0;j<3;j++){
                            reflectionMatrix[i][j]=Double.parseDouble(arr[index++]);
                        }
                    }
                    displayMatrix(reflectionMatrix);
                    transformationOpeartion(reflectionMatrix);
                    colorCode=0;
                    drawObject();
            }
        }
    }

    /* All helper functions to perform computation under algorithms
    * */
    private void transformationOpeartion(double transformMatrix[][]){
        // perform the matrix multiplication of homogeneous matrix and transformation matrix to get new homogeneous matrix
        for(int i=0;i<X.size();i++){
            double tempMatrix[][]={{(double)X.get(i)},{(double)Y.get(i)},{1}};
            double resultMatrix[][]=multiplyMatrix(transformMatrix,tempMatrix);
            X.set(i,(int)resultMatrix[0][0]);
            Y.set(i,(int)resultMatrix[1][0]);
        }
    }
    private void drawObject(){
        //use the X and Y list to draw the object with color selection represented by color code
        System.out.println(X);
        System.out.println(Y);
        Graphics g=getGraphics();
        if(colorCode==1) {
            g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.BLACK);
        }
        for(int i=0;i<X.size()-1;i++) {
            g.drawLine(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
        }
        g.drawLine(X.get(X.size()-1), Y.get(X.size()-1), X.get(0), Y.get(0));
    }

    private double[][] multiplyMatrix(double m1[][],double m2[][]){
        double result[][]=new double[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }
    private void displayMatrix(double matrix[][]){
        for(int i=0;i<matrix.length;i++){
            System.out.print("\n");
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print(" " + matrix[i][j]);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        //whenever an item from list of actions is selected hide all other parameter input boxes except the one selected
        textViewXShear.setVisible(false); editXShear.setVisible(false);
        textViewYShear.setVisible(false); editYShear.setVisible(false);
        textViewXTranslate.setVisible(false); editXTranslate.setVisible(false);
        textViewYTranslate.setVisible(false); editYTranslate.setVisible(false);
        textViewTheta.setVisible(false); editTheta.setVisible(false);
        textViewXPivot.setVisible(false); editXPivot.setVisible(false);
        textViewYPivot.setVisible(false); editYPivot.setVisible(false);
        textViewScale.setVisible(false); editScale.setVisible(false);
        textViewReflectionMatrix.setVisible(false); editReflectionMatrix.setVisible(false);
        shearList.setVisible(false);

        if(event.getItem()=="Scaling"){
            textViewScale.setVisible(true);
            editScale.setVisible(true);
        }
        if(event.getItem()=="Translation"){
            textViewXTranslate.setVisible(true); editXTranslate.setVisible(true);
            textViewYTranslate.setVisible(true); editYTranslate.setVisible(true);
        }
        if(event.getItem()=="Rotation"){
            textViewTheta.setVisible(true); editTheta.setVisible(true);
            textViewXPivot.setVisible(true); editXPivot.setVisible(true);
            textViewYPivot.setVisible(true); editYPivot.setVisible(true);
        }
        if(event.getItem()=="Shear"){
            textViewXShear.setVisible(true); editXShear.setVisible(true);
            textViewYShear.setVisible(true); editYShear.setVisible(true);
            shearList.setVisible(true);
        }
        if(event.getItem()=="Reflection"){
            textViewReflectionMatrix.setVisible(true); editReflectionMatrix.setVisible(true);
        }
    }
    private void initializeDefaultValues(){
        editXShear.setText("2");
        editYShear.setText("3");
        editXTranslate.setText("30");
        editYTranslate.setText("30");
        editTheta.setText("90");
        editXPivot.setText("150");
        editYPivot.setText("150");
        editScale.setText("0.5");
        editReflectionMatrix.setText("1,0,0,0,-1,0,0,0,1"); //by default reflection along X axis

        //points to draw square
/*
        X.add(100);Y.add(100);
        X.add(300);Y.add(100);
        X.add(300);Y.add(300);
        X.add(100);Y.add(300);
*/
        //Points to draw a polygon

        X.add(50);    Y.add(150);
        X.add(200);     Y.add(50);
        X.add(350);     Y.add(150);
        X.add(350);     Y.add(300);
        X.add(250);     Y.add(300);
        X.add(200);     Y.add(250);
        X.add(150);     Y.add(350);
        X.add(100);     Y.add(250);
        X.add(100);     Y.add(200);


       /*
        X.add(0);Y.add(0);
        X.add(1);Y.add(1);
        X.add(5);Y.add(2);
       */
    }
}
