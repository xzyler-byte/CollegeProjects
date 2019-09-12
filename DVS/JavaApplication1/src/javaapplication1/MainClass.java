
package javaapplication1;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
public class MainClass implements ActionListener {
JFrame jf1;
JButton jb1,jb2,jb3;
ImageIcon img;
JLabel jl1,jl2;
 MainClass()
   {
    jf1=new JFrame();
    jf1.setSize(400,400);
    jf1.setLayout(null);
    
    jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    jb1=new JButton("Voter");
    jb2=new JButton("Administrator");
    jb3=new JButton("Exit");
     jb1.setBackground(new Color(248,246,255));
     jb2.setBackground(new Color(248,246,255));
     jb3.setBackground(new Color(248,246,255));
     
    jb1.setBounds(50,100,100,30);
    jb2.setBounds(50,150,120,40);
    jb3.setBounds(50,260,80,30);
    
    img=new ImageIcon("C:\\Users\\Red Devil\\Desktop\\Project\\nepal-vote.jpg");
    jl1=new JLabel(img);
    jl1.setSize(400,400);
    
    jl2=new JLabel("Login as:");
    jl2.setBounds(50,60,150,40);
     jl2.setFont(new Font("Serif",Font.BOLD,20));
    
    jf1.add(jb1);
    jf1.add(jb2);
    jf1.add(jb3);
    jf1.add(jl2);
    jf1.add(jl1);
    jf1.setVisible(true);
    
    jb1.addActionListener(this);
    jb2.addActionListener(this);
    jb3.addActionListener(this);
   
    }
   public void actionPerformed(ActionEvent e)
   { 
       if(e.getSource()==jb1)
       { 
          new VoterVerification();
          jf1.dispose();
        }
        if(e.getSource()==jb2)
       { 
           new AdminVerification();
         jf1.dispose();
        }
         if(e.getSource()==jb3)
       { 
            jf1.dispose();
        }
    }
    public static void main(String[] args) {
       new MainClass(); 
    }
    
}


