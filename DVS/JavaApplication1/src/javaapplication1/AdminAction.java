
package javaapplication1;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class AdminAction implements ActionListener {
    JFrame f1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    JLabel l1;
    AdminAction()
    {
        f1=new JFrame();
        f1.setTitle("AdminAction");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(400,400);
         f1.getContentPane().setBackground(new Color(230,230,250));
       
        f1.setLayout(null);
        l1=new JLabel("Welcome "+AdminVerification.returnName());
        l1.setFont(new Font("Serif",Font.BOLD,15));
        
        
        b1=new JButton("Modify Candidate");
        b2=new JButton("Modify Voters");
        b3=new JButton("Check Result");
        b4=new JButton("Reset Voting");
        b5=new JButton("Exit");
 
        
         b1.setBackground(new Color(248,246,255));
     b2.setBackground(new Color(248,246,255));
      b3.setBackground(new Color(248,246,255));
     b4.setBackground(new Color(248,246,255));
      b5.setBackground(new Color(248,246,255));
       
    
       
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        
                
        l1.setBounds(10,10,200,15);
        b1.setBounds(100,40,150,30);
        b2.setBounds(100,90,150,30);
        b3.setBounds(100,140,150,30);
        b4.setBounds(100,190,150,30);
        
        b5.setBounds(150,250,100,30);
       
        
        f1.add(l1);
        f1.add(b1);
        f1.add(b2);
        f1.add(b3);
        f1.add(b4);
        f1.add(b5);
        
         f1.setVisible(true);
            
     }
    public void actionPerformed(ActionEvent e)
    {
         if (e.getSource()==b1 )
        { 
          f1.dispose();
          new CandidateDetail();
         }
          if (e.getSource()==b2 )
        { 
          f1.dispose();
          new VoterDetail();
         }
           if (e.getSource()==b3 )
        { 
          
          new CheckResult();
         }
            if (e.getSource()==b4 )
        { 
            
            new ResetVoting();
         }
             if (e.getSource()==b5 )
        { 
          f1.dispose();
         }
             
     }
    public static void main(String []args)
    {
     new AdminAction();
     }
}
