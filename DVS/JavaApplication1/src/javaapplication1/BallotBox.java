
package javaapplication1;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BallotBox implements ActionListener{
    JFrame f1;
    JComboBox cb1;
    JButton b1;
    JLabel l1,l2,l3;
    JTextField t1;
    ImageIcon img;
    
    BallotBox()
    {
     f1=new JFrame();
     f1.setSize(400,400);
     f1.setTitle("BallotBox");
     f1.setLayout(null);
    
     f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
   cb1=new JComboBox();
     /*DataBase Connect*/
    try{
               Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                String sql="Select * from candidate";
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(sql);
     
                 while(rs.next())
                {
                    String name=rs.getString("Name");
                    cb1.addItem(name);
                }
    }
        
     catch(Exception ex)
     {
          System.out.println(ex);
      }
   
     
     
     b1=new JButton("Vote");
      b1.setBackground(new Color(248,246,255));
    
     img=new ImageIcon("C:\\Users\\Red Devil\\Desktop\\Project\\Ballotbox.jpg");
     
     l1=new JLabel("Select Your Candidate");
     l2=new JLabel("PS:Select your candidate wisely");
     l3=new JLabel(img);
     l3.setSize(400,400);
     
     Font font= new Font("SERIF",Font.BOLD,14);
     
     l1.setBounds(110,40,180,25);
     l2.setBounds(20,250,250,20);
     cb1.setBounds(110,80,180,30);
     b1.setBounds(110,120,90,25);
     
      l3=new JLabel(img);
     l3.setSize(400,400);
    
     l1.setFont(font);
     l2.setFont(font);
     
     
     f1.add(l1);
     f1.add(l3);
     f1.add(l2);
     f1.add(b1);
     f1.add(cb1);
     f1.add(l3);
      f1.setVisible(true);
     b1.addActionListener(this);
     }
    public void actionPerformed(ActionEvent e)
    { 
        
        if(e.getSource()==b1)
        {   
           
            int a=JOptionPane.showConfirmDialog(null,"Are you sure?");
            if(a==0)
            {
                
                 f1.dispose();
                JOptionPane.showMessageDialog(null,"Thank You");
               try{
                   int coun=0;
                        String s= cb1.getSelectedItem().toString();
                     Class.forName("com.mysql.jdbc.Driver");
                      Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                     String sql="Select * from candidate where Name=? ";
                     PreparedStatement st=con.prepareStatement(sql);
                     st.setString(1,s);
                     ResultSet rs=st.executeQuery();
                     while(rs.next())
                     { 
                            coun=Integer.parseInt(rs.getString("Count"));
                     }
                     rs.close();
                     coun=coun+1;
                     String sql1="Update candidate set Count=? where Name=? ";
                     PreparedStatement st1=con.prepareStatement(sql1);
                     st1.setInt(1,coun);
                     st1.setString(2,s);
                     st1.executeUpdate();
                     con.close();
                     new MainClass();
                 
                } 
                catch(Exception ex)
                { 
                    System.out.println(ex);

                    }
             }
             if(a==1)
                {
                    JOptionPane.showMessageDialog(null,"Please reselect your Candidate");
                }

          }
    }
public static void main(String args[])
        { 
        new BallotBox();
         }
}
