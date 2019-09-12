

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

public class CandidateDetail implements ActionListener {
    JFrame f1;
    JButton b1,b2,b3,b4;
    JLabel l2,l3,l4,l5,l6;
    JTextField t2,t3,t4,t5;
   public CandidateDetail()
    {
        f1=new JFrame();
        f1.setTitle("Candidate Detail");
        f1.setSize(400,400);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLayout(null);
        f1.getContentPane().setBackground(new Color(230,230,250));
        

     l2=new JLabel("Name");
     l3=new JLabel("Sex");
     l4=new JLabel("Age");
     l5=new JLabel("City");
     l6=new JLabel("Enter Candidate Detail:");
     l6.setFont(new Font("Serif",Font.BOLD,18));
     
    
     t2=new JTextField();
     t3=new JTextField();
     t4=new JTextField();
     t5=new JTextField();
    
     b1=new JButton("Insert");
     b2=new JButton("Update");
     b3=new JButton("Delete");
     b4=new JButton("Back");
     
     b1.setBackground(new Color(248,246,255));
     b2.setBackground(new Color(248,246,255));
     b3.setBackground(new Color(248,246,255));
     b4.setBackground(new Color(248,246,255));
     
    l2.setBounds(20,40,80,20);
    t2.setBounds(120,40,180,25);
    l3.setBounds(20,80,50,20);
    t3.setBounds(120,80,180,25); 
    l4.setBounds(20,120,50,20);
    t4.setBounds(120,120,180,25);
    l5.setBounds(20,160,50,20);
    t5.setBounds(120,160,180,25);
    l6.setBounds(20,10,200,20); 
    
       
    b1.setBounds(10,250,90,20);
    b2.setBounds(110,250,90,20);
    b3.setBounds(210,250,90,20);
    b4.setBounds(310,250,70,20);
    
     
     f1.add(l2);
     f1.add(t2);
     f1.add(l3);
     f1.add(t3);
     f1.add(l4);
     f1.add(t4);
     f1.add(l5);
     f1.add(t5);
    f1.add(l6);
     f1.add(b1);
     f1.add(b2);
      f1.add(b3);
     f1.add(b4);
     
      f1.setVisible(true);
     b1.addActionListener(this);
     b2.addActionListener(this);
      b3.addActionListener(this);
     b4.addActionListener(this);
     
     
    
         
     }
     public void actionPerformed(ActionEvent e)
    { 
       
        if (e.getSource()==b1 )
        { 
            String name= t2.getText();
            String sex=t3.getText();
            int age=Integer.parseInt(t4.getText());
            String city=t4.getText();
            if(age<=23)
            {
            JOptionPane.showMessageDialog(null,"The person is not eligible for being candidate");
            JOptionPane.showMessageDialog(null,"Please Re-enter Data");
            }
            else
            {

            try{
                  Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                    String sql="insert into candidate(Name,Sex,Age,City) values(?,?,?,?)";
                   PreparedStatement st=con.prepareStatement(sql);
                    st.setString(1, name);
                    st.setString(2,sex);
                    st.setInt(3,age);
                    st.setString(4,city);
                    st.executeUpdate();
                    con.close();
                 }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Empty Field");
                   System.out.println(ex);
                 }
                 
                 JOptionPane.showMessageDialog(null,"Data Inserted");
                new AdminAction();
                 f1.dispose();
       }
        }
       if(e.getSource()==b2)
        {
            String name= t2.getText();
            String sex=t3.getText();
            int age=Integer.parseInt(t4.getText());
            String city=t4.getText();
            if(age<=23)
            {
            JOptionPane.showMessageDialog(null,"The person is not eligible for being candidate");
            JOptionPane.showMessageDialog(null,"Please Re-enter Data");
            }
            else{
                try{
                  Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                    String sql="update candidate set Name=?,Sex=?,Age=?,City=? where Cid=?";
                    int id1=Integer.parseInt(JOptionPane.showInputDialog(f1,"Enter Candidate Id which is to be Updated"));
                    
                    PreparedStatement st=con.prepareStatement(sql);
                    st.setString(1, name);
                    st.setString(2,sex);
                    st.setInt(3,age);
                    st.setString(4,city);
                    st.setInt(5, id1);
                    st.executeUpdate();   
                    con.close();
                 }
                catch(Exception ex)
                {
                  
                 }
                JOptionPane.showMessageDialog(null,"Data Updated");
                new AdminAction();
                 f1.dispose();
         }
        }
       
        if(e.getSource()==b3)
        { 
            try{
                  Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/dvoting","root","");
                    String sql="delete from candidate where Cid=?";
                   PreparedStatement st=con.prepareStatement(sql);
                   int id1=Integer.parseInt(JOptionPane.showInputDialog(f1,"Enter Candidate Id which is to be Deleted"));
                    st.setInt(1,id1);
                    st.executeUpdate();
                    con.close();
                 }
                catch(Exception ex)
                {
                   
                 }
                 JOptionPane.showMessageDialog(null,"Data Deleted");
                new AdminAction();
                 f1.dispose(); 
         
         }
         if(e.getSource()==b4)
        { 
          new AdminAction();
          f1.dispose();
         }
      }
    
public static void main(String args[])
{ 
new CandidateDetail();
 }
}





    

