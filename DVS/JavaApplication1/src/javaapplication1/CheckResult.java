package javaapplication1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
public class CheckResult  {
JFrame f1;
JTable table;
String clmn[]={"Candidate","City","Votes"};
 CheckResult()
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
            f1 = new JFrame("Check Result");
            f1.setSize(400,400);
            f1.getContentPane().setBackground(new Color(230,230,250));

            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f1.setLayout(new BorderLayout()); 
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(clmn);

            table = new JTable();
            table.setModel(model); 
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFillsViewportHeight(true);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
            String Name= "";
            String city="";
            int vote=0;
            String Name1= "";
            String winner="";
            
                try
                { 
                String sql1 = "select * from candidate";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery();
                while(rs1.next())
                {
                    Name = rs1.getString("Name");
                    city=rs1.getString("City");
                    vote = Integer.parseInt(rs1.getString("Count"));
                    model.addRow(new Object[]{Name,city,vote});
                    

                 }
                f1.add(scroll);
                f1.setVisible(true);
                }
                catch(Exception ex)
                {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
public static void main(String[] args) {
       new CheckResult(); 
    }
    
}


