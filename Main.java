package W03_T4_TicTacToe_TryLikeLecturerCode_02082018;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Main extends JFrame{
    
public static final int row=3;
public static final int column=3;
        
public static JButton[][] jbutton=new JButton[row][column];
public static  JButton reset_jbutton=new JButton("Reset");
public static JPanel jpanel_Gross,jpanel_Feld,jpanel_reset;

public static MyButtonAL al; 
public static GridLayout gridlayout;
public static char current_player='X';
public static int anzahlZuege=0;

private class MyButtonAL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton sender=(JButton)ae.getSource();
            if(sender.equals(reset_jbutton)){
                reset();
            }else{
                ++anzahlZuege;
                sender.setText(""+current_player);
                sender.removeActionListener(al);
                if(!spielVorbei()){
                current_player=(current_player=='X')?'O':'X';
                }
            }
            
        }

}

private boolean spielVorbei(){
    boolean ende=false;
    if(eq(jbutton[0][0],jbutton[1][0],jbutton[2][0])){
        ende=true;
    }
    if(eq(jbutton[0][1],jbutton[1][1],jbutton[2][1])){
        ende=true;
    }
    if(eq(jbutton[0][2],jbutton[1][2],jbutton[2][2])){
        ende=true;
    }
    if(eq(jbutton[0][0],jbutton[0][1],jbutton[0][2])){
        ende=true;
    }
    if(eq(jbutton[1][0],jbutton[1][1],jbutton[1][2])){
        ende=true;
    }
    if(eq(jbutton[2][0],jbutton[2][1],jbutton[2][2])){
        ende=true;
    }
    if(eq(jbutton[0][0],jbutton[1][1],jbutton[2][2])){
        ende=true;
    }
    if(eq(jbutton[2][0],jbutton[1][1],jbutton[0][2])){
        ende=true;
    }
   if(ende){
       JOptionPane.showMessageDialog(this, current_player+" hat gewonnen!","Hurra",JOptionPane.ERROR_MESSAGE);
   }
   if((!ende)&&(anzahlZuege==9)){
       ende=true;
       JOptionPane.showMessageDialog(this,"Niemand hat gewonnen","Unentschieden",JOptionPane.ERROR_MESSAGE);
   }
    return ende;
}

private boolean eq(JButton b1,JButton b2,JButton b3){
    String s1=b1.getText();
    String s2=b2.getText();
    String s3=b3.getText();
    
    boolean gleich=(s1.equals(s2))&&(s1.equals(s3))&&(s1.length()!=0);
    
    if(gleich){
    b1.setBackground(Color.pink);
    b2.setBackground(Color.pink);
    b3.setBackground(Color.pink);
    }
    
    return gleich;
}

private void reset(){
for(int i=0;i<row;i++){
    for(int j=0;j<column;j++){
        resetButton(jbutton[i][j]);
    }
}
current_player='X';
anzahlZuege=0;
}

private void resetButton(JButton b){
    if(b.getText().length()>0){
    b.setBackground(Color.yellow);
    b.addActionListener(al);
    b.setText("");
    }
}

public Main(){

 al=new MyButtonAL();   
 
jpanel_Gross=new JPanel();
jpanel_Gross.setLayout(null);
this.add(jpanel_Gross);

gridlayout=new GridLayout(3, 3, 5, 5);
jpanel_Feld=new JPanel();
jpanel_Feld.setLayout(gridlayout);
jpanel_Feld.setBounds(10, 10, 320, 320);
jpanel_Gross.add(jpanel_Feld);


for(int i=0;i<row;i++){
    for(int j=0;j<column;j++){
        jbutton[i][j]=initBtn(jbutton[i][j]);
    }
}

jpanel_reset=new JPanel();
jpanel_reset.setLayout(new FlowLayout());
jpanel_reset.setBounds(10, 340, 320, 40);
jpanel_Gross.add(jpanel_reset);

reset_jbutton=new JButton("Reset");
reset_jbutton.addActionListener(al);
jpanel_reset.add(reset_jbutton);
        

this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.pack();
this.setSize(350, 430);
this.setVisible(true);
this.setLocationRelativeTo(null);
this.setResizable(false);

}

private JButton initBtn(JButton btn){
    btn=new JButton("");
    btn.setPreferredSize(new Dimension(100, 100));
    
    btn.addActionListener(al);
    btn.setBackground(Color.GREEN);
    jpanel_Feld.add(btn);
    return btn;
}

public static void main(String[] args){

Main m=new Main();

}
    
}
