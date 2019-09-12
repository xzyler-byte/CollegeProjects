
package javaapplication1;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class VoterRecovery implements ActionListener{
    JFrame f1;
    JTextField t1,t2;
    JLabel l1,l2;
    JButton b1,b2;
    
    VoterRecovery()
    {
       f1=new JFrame();
       f1.setSize(400,400);
       f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f1.setTitle("Recovery");
       f1.setLayout(null);
        f1.getContentPane().setBackground(new Color(230,230,250));
        
       l1=new JLabel("Enter Your Name:");
       l2=new JLabel("Enter CitizenShip no:");
       
       t1=new JTextField();
       t2=new JTextField();
    
       b1=new JButton("Submit");
       b2=new JButton("Back");
       
       
       l1.setBounds(80,60,150,20);
       t1.setBounds(80,80,150,20);
       l2.setBounds(80,110,150,20);
       t2.setBounds(80,130,150,20);
       b1.setBounds(80,180,80,20);
       b2.setBounds(180,180,80,20);
       
       
       f1.add(l1);
       f1.add(t1);
       f1.add(l2);
       f1.add(t2);
       f1.add(b1);
       f1.add(b2);
       f1.setVisible(true);

       b1.addActionListener(this);
       b2.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e)
    {
        String name=t1.getText();
        if(e.getSource()==b1)
        { 
            int x=0;
            
            int ctz=Integer.parseInt(t2.getText());
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                String sql="Select * from voter";
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(sql);
                while(rs.next())
                {
                    if(name.equals(rs.getString("Name"))&&ctz==Integer.parseInt(rs.getString("Citizenship_No")))
                    { 
                    x=1;
                    }
                }
                }
            catch(Exception ex)
            {
                System.out.println(ex);
             }
            
            if(x==1)
            {
                try{
                    JLabel l=new JLabel(name);
                     JTextField password = new JPasswordField();
                      Object[] message = {"Username:",l,"New Password:", password};
                      int option = JOptionPane.showConfirmDialog(null, message, "Reset Password", JOptionPane.OK_CANCEL_OPTION);
                      
                     Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                    String sql="update Voter set Password=? where Name=?";
                   PreparedStatement st=con.prepareStatement(sql);
                   st.setString(1,password.getText());
                    st.setString(2,name);
                    st.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null,"Successfully Password Changed");
                    new VoterVerification();
                    f1.dispose();
                }
            catch(Exception ex)
            {
                System.out.println(ex);
             }
             }
        else {
                JOptionPane.showMessageDialog(null,"Incorrect Name or Citizenship_No" ,"Error",JOptionPane.ERROR_MESSAGE);
            }
         }
        if(e.getSource()==b2)
        { 
         new VoterVerification();
         f1.dispose();
         }
    }
    
    public static void main(String []args)
    {
        new VoterRecovery();
     }
}
