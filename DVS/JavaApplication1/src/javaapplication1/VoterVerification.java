package javaapplication1;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VoterVerification implements ActionListener {
   
    JFrame f1;
    JButton b1,b2;
    JLabel l1,l2,l3;
    JTextField t1;
    JPasswordField t2;
    JRadioButton rb1;
    ImageIcon img;
   public VoterVerification()
    { 
     f1=new JFrame();
     f1.setTitle("Voter Verification");
     f1.setSize(400,400);
     f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f1.setLayout(null);
      f1.getContentPane().setBackground(new Color(230,230,250));
          
    
     l1=new JLabel("Voter ID");
     l2=new JLabel("Pin");
     
     l1.setFont(new Font("Serif",Font.BOLD,18));
     l2.setFont(new Font("Serif",Font.BOLD,18));
     
     img=new ImageIcon("C:\\Users\\Red Devil\\Desktop\\Project\\VoterVerification1.jpg");
     l3=new JLabel(img);
     l3.setSize(400,400);
     
     t1=new JTextField();
     t2=new JPasswordField();
     
     
     b1=new JButton("Ok");
     b2=new JButton("Back");
      b1.setBackground(new Color(248,246,255));
     b2.setBackground(new Color(248,246,255));
     
     rb1=new JRadioButton("Forget Pin");
     
    l1.setBounds(80,80,90,18);
     l2.setBounds(100,110,90,18);
     t1.setBounds(160,80,180,25);
     t2.setBounds(160,110,180,25);
     b1.setBounds(80,180,60,20);
     b2.setBounds(160,180,100,20);
     rb1.setBounds(100,150,100,20);
     
     
     f1.add(l1);
     f1.add(t1);
     f1.add(l2);
     f1.add(t2);
     f1.add(b1);
     f1.add(b2);
     f1.add(rb1);
     f1.add(l3);
     f1.setVisible(true);

     b1.addActionListener(this);
     b2.addActionListener(this);
     rb1.addActionListener(this);
     }
    
    public void actionPerformed(ActionEvent e)
    { 
        if (e.getSource()==b1 )
        { 
            int x=0,y=0;
            
        try{
        
               Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                String sql="Select * from voter";
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(sql);
                while(rs.next())
                {
                   if(t1.getText().equals(rs.getString("Vid"))&&(t2.getText()).equals(rs.getString("Password")))
                { 
                   
                    x=1;
                   if(Integer.parseInt(rs.getString("vote"))==1)
                   {
                       JOptionPane.showMessageDialog(null, "You have voted already","Error", JOptionPane.ERROR_MESSAGE);
                      y=1;
                       f1.dispose();
                       new VoterVerification();
                       rs.close();
                       con.close();
                    }
                }
               
                
                }
             }
             catch(Exception ex)
            {
                System.out.println(ex);
             }
        if(x==1&&y==0)
        {
            try{
             Class.forName("com.mysql.jdbc.Driver");
                Connection con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                 String sql="update Voter set vote=? where Vid=?";
                   PreparedStatement st=con1.prepareStatement(sql);
                   st.setInt(1,1);
                    st.setString(2,t1.getText());
                    st.executeUpdate();
                     new BallotBox();
                        f1.dispose();
            }
            catch(Exception e1)
            { 
                 System.out.println(e1);
              }
         }
        if(x==0)
        {
            JOptionPane.showMessageDialog(null, "Incorrect ID or Password","Error", JOptionPane.ERROR_MESSAGE);  
         }
        
        
         }
        if(e.getSource()==b2)
        { 
          new MainClass();
          f1.dispose();
         }
        if(e.getSource()==rb1) 
        {
            new VoterRecovery();
            f1.dispose();
            
         }
      }
    
    
public static void main(String args[])
{
     new VoterVerification();
 }

}

