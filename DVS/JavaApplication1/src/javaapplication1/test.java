/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;

public class test {
    public static void main(String args[])
    {
       try{
        Class.forName("com.mysql.jdbc.Driver");
         Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                String sql="Select * from Admin";
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(sql);
                while(rs.next())
                {
                        System.out.println(rs.getString("Password"));
                 }
           
        con.close();
     }
     catch(Exception e)
    {
        System.out.println(e);
     }
    }
}