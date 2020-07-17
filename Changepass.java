package Browsermanagement;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
import java.sql.*;

public class Changepass extends JFrame implements ActionListener
 {  
    JLabel l0,l1, l2,l3;
    JTextField tf1;    
    JButton submit,back;  
    JPasswordField p0,p1,p2;
   Changepass()
   {
        setVisible(true);  
        setSize(700, 700);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Change Password");  
        l0=  new JLabel("User Name");
        l1 = new JLabel("Password");  
        l2 = new JLabel("New Password:");  
        l3=  new JLabel("Confirm Password");
        
        tf1   =  new JTextField();
        p0    =  new JPasswordField();  
        p1    =  new JPasswordField();
        p2    =  new JPasswordField();
        submit=  new JButton("Submit");  
        back =  new JButton("Close");
	  
        submit.addActionListener(this);  
        back.addActionListener(this);   
        
        l0.setBounds(50, 70, 200, 30);  
        l1.setBounds(50, 140, 200, 30);   
        l2.setBounds(50,210,200,30);
        l3.setBounds(50,280,200,30);
        tf1.setBounds(300, 70, 200, 30);
        p0.setBounds(300, 140, 200, 30);  
        p1.setBounds(300,210,200,30);  
        p2.setBounds(300,280,200,30);
        
        submit.setBounds(130,350, 100, 30);  
        back.setBounds(250,350, 100, 30);  
	  
        add(l0);
        add(l1);  
        add(l2);
        add(l3);
        add(tf1);
        add(p0);    
        add(p1);
        add(p2);
        add(submit);  
        add(back); 
   }
   public void actionPerformed(ActionEvent e)
   {
       String valid="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
       
     String userName = tf1.getText();
     String oldpass= p0.getText();
     String npass = p1.getText();
     String cpass= p2.getText();
     
    if(e.getSource()==submit)
    {
        try{
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Browser","root","S@ndy2892");  
                         Statement st=con.createStatement();
                         ResultSet rs = st.executeQuery("Select * from Signup");
        while(rs.next())
        {
            String uname=rs.getString("Username");
            String pass=rs.getString("password");
            if(uname.equals(userName) && pass.equals(oldpass))
            {
              if(npass.equals(cpass) && npass.matches(valid))
              {
                   PreparedStatement ps = con.prepareStatement("Update Signup data set password=? where Username=?");  
		   ps.setString(1,npass);
		   ps.setString(2,userName);	
		   ps.executeUpdate();
		   new dialog();
                   p0.setText("");
            tf1.setText("");
            p1.setText("");
            p2.setText("");                       
              }
        else
        {
           JOptionPane.showMessageDialog(null,"Please check your Password and type again");
            p0.setText("");
            tf1.setText("");
            p1.setText("");
            p2.setText("");
        }
            }
           }
        }
         catch (Exception ex)   
             {  
                System.out.println(ex);  
             }  
     }
    else{
     setVisible(false);
    }
    }
   public static void main(String args[])
   {
    new Changepass();
   }
}
