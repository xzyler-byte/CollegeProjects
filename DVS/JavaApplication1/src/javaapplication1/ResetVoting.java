
package javaapplication1;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class ResetVoting {
    ResetVoting()
    { 
    JTextField username = new JTextField();
    JTextField password = new JPasswordField();
    Object[] message = {"Username:", username,"Password:", password};
    int x=0;
    int option = JOptionPane.showConfirmDialog(null, message, "Confirm Admin", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) 
    {
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
        String sql = "select * from admin";
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next())
        {    
            if (username.getText().equals(rs.getString("UserName")) && password.getText().equals(rs.getString("Password"))) 
            { 
                x=1;
            }
        }
        if(x==1)
        {
           try{
                
                String sql1 = "Update candidate set Count=0";
                 String sql2 = "Update voter set vote=0";
                PreparedStatement ps1 = con.prepareStatement(sql1);
               ps1.executeUpdate(sql1);
               ps1.executeUpdate(sql2);
               JOptionPane.showMessageDialog(null,"Reset Done!!");
               new AdminAction();
               
            }
           catch(Exception ex)
           { 
               System.out.println(ex);
            }

        } 
      
       else {
         JOptionPane.showMessageDialog(null,"Incorrect Name or Password" ,"Error",JOptionPane.ERROR_MESSAGE);
        
       }
   }
    
    catch(Exception e)
    {
        System.out.println(e);
     } 

    }
}
        
 public static void main(String []args)
 {
     new ResetVoting();
  }
      }

