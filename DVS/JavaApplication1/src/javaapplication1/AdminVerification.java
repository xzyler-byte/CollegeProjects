
package javaapplication1;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class AdminVerification implements ActionListener {
    JFrame f1;
    JButton b1,b2;
    JLabel l1,l2,l3;
    JTextField t1;
    JPasswordField t2;
    static String s;
    ImageIcon img;
   public AdminVerification()
    { 
     f1=new JFrame();
     f1.setTitle("Admin Verification");
     f1.setSize(400,400);
     f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f1.setLayout(null);
      f1.getContentPane().setBackground(new Color(230,230,250));
    
     
     l1=new JLabel("Admin Name");
     l2=new JLabel("Password");
     
     t1=new JTextField();
     t2=new JPasswordField();
     
     
     b1=new JButton("Submit");
     b2=new JButton("Back");
      b1.setBackground(new Color(248,246,255));
     b2.setBackground(new Color(248,246,255));
     
     img=new ImageIcon("C:\\Users\\Red Devil\\Desktop\\Project\\login.jpg");
     l3=new JLabel(img);
     l3.setSize(400,400);
     
    l1.setBounds(80,80,90,10);
     l2.setBounds(80,110,90,10);
     t1.setBounds(160,80,150,20);
     t2.setBounds(160,110,150,20);
     b1.setBounds(80,180,80,20);
     b2.setBounds(170,180,80,20);
     
     f1.add(l1);
     f1.add(t1);
     f1.add(l2);
     f1.add(t2);
     f1.add(b1);
     f1.add(b2);
     f1.add(l3);
      f1.setVisible(true);
     b1.addActionListener(this);
     b2.addActionListener(this);
     }
   public static String returnName()
   {
      
       return(s);
    }
    
    public void actionPerformed(ActionEvent e)
    { 
        if (e.getSource()==b1 )
        { 
            int x=0;
             s=t1.getText();
        try{
            
                 Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                String sql="Select * from Admin";
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(sql);
             while(rs.next())
             {
                if(t1.getText().equals(rs.getString("UserName"))&&(t2.getText()).equals(rs.getString("Password")))
                { 
                   
                    x=1;
                  
                }
              }
              rs.close();
               con.close();
            }
        catch(Exception ex)
            {
            System.out.println(ex);
            }
        if(x==1)
         {
            new AdminAction();
            f1.dispose();
           }
        if(x==0)
        { 
          JOptionPane.showMessageDialog(null, "Incorrect Name or Password","Error", JOptionPane.ERROR_MESSAGE); 
         }
      }
        if(e.getSource()==b2)
        { 
          new MainClass();
          f1.dispose();
         }
      }
    
public static void main(String args[])
{ 
new AdminVerification();
 }
}
