import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
import java.sql.*;

public class Newpassword extends JFrame implements ActionListener
 {  
    JLabel l0,l1, l2;
    JTextField tf1;    
    JButton submit,back;  
    JPasswordField p1,p2;
   Newpassword()
   {
        setVisible(true);  
        setSize(700, 700);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("New Password");  
        l0=new JLabel("User Id");
        l1 = new JLabel("New Password");  
        l2 = new JLabel("Confirm Passowrd:");  
        
        tf1=new JTextField();
        p1 = new JPasswordField();  
        p2=new JPasswordField();
        submit= new JButton("Submit");  
        back = new JButton("Close");
	  
        submit.addActionListener(this);  
        back.addActionListener(this);   
        
        l0.setBounds(50, 70, 200, 30);  
        l1.setBounds(50, 140, 200, 30);   
        l2.setBounds(50,210,200,30);
        tf1.setBounds(300, 70, 200, 30);
        p1.setBounds(300, 140, 200, 30);  
        p2.setBounds(300,210,200,30);  
        submit.setBounds(130, 270, 100, 30);  
        back.setBounds(250, 270, 100, 30);  
	  
        add(l0);
        add(l1);  
        add(l2);
        add(tf1);
        add(p1);    
        add(p2);  
        add(submit);  
        add(back); 
   }
   public void actionPerformed(ActionEvent e)
   {
    if(e.getSource()==submit)
    {
        String valid="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        String userid=tf1.getText();
        String npass=p1.getText();
        String cpass=p2.getText();
        if(npass.equals(cpass) && npass.matches(valid))
         {
        try{
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Browser","root","S@ndy2892");  
                         PreparedStatement ps = con.prepareStatement("Update Signup data set password=? where Userid=?");  
		 	 ps.setString(1,npass);
			 ps.setString(2,userid);	
			 ps.executeUpdate();
			 new dialog();
           }
         catch (Exception ex)   
             {  
                System.out.println(ex);  
             }  
     }
        else
        {
         JOptionPane.showMessageDialog(null,"Please check your Password and type again");
            tf1.setText("");
            p1.setText("");
            p2.setText("");
        }
    }
    else
    {
      new forget();  
      setVisible(false);
    }
   }
   public static void main(String args[])
   {
    new Newpassword();
   }
 }
