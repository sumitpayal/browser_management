package Browsermanagement;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;
 
public class forget extends JFrame  implements ActionListener ,ItemListener
{  
    JLabel l1,lq,la;  
    JTextField tf0,tf1;  
    JButton Done ,Back;  
    JComboBox jc;
    String ques;
    forget()  
    {  
        setVisible(true);  
        setSize(800, 900);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Forget Password");  
        l1 = new JLabel("User Name");
        lq=new JLabel("Question");
        la=new JLabel("Answer");
        tf0 = new JTextField();  
        tf1=new JTextField();
        
        Done= new JButton("Done");  
        Back = new JButton("Back");  
        Done.addActionListener(this);  
        Back.addActionListener(this); 
        
        String[] q = {"Pet name","Hobby","Favorite song","Favorite car"};
        jc=new JComboBox(q);
        jc.addItemListener(this);
        l1.setBounds(50, 70, 200, 30);
        lq.setBounds(50,120,200,30);
        la.setBounds(50,170,200,30);
        tf0.setBounds(300, 70, 200, 30);  
        jc.setBounds(300,120,200,30);
        tf1.setBounds(300,170,200,30);
        Done.setBounds(200, 220, 100, 30);  
        Back.setBounds(330, 220, 100, 30); 
        add(l1); 
        add(tf0);      
        add(lq);
        add(la);
                add(jc);
        add(tf1);
        add(Done);  
        add(Back); 
    }  
    
    public void itemStateChanged(ItemEvent e) {
           if(e.getSource()==jc)
        {
          ques=(String)jc.getSelectedItem();
        }}
    
    public void actionPerformed(ActionEvent e)  
    {  
        String username   = tf0.getText();
        String ans=tf1.getText();
        String q="";
        if (e.getSource() == Done)  
         {  
              try  
              {  
                   
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Browser","root","S@ndy2892");
              PreparedStatement ps = con.prepareStatement("Select *  from Signup where Username = ? AND question = ? AND answer=?");  
	      ps.setString(1, tf0.getText());  
              ps.setString(2, ques); 
              ps.setString(3,tf1.getText());  
              ResultSet result = ps.executeQuery();             
                    
                    if(result.next()){
                     new Newpassword();
                     setVisible(false);
                    }
                    else{
                    JOptionPane.showMessageDialog(this," Your security answer is incorrect !please enter a valid answer ");
                    }
         }
              catch(Exception ex)   
                   {  
                       System.out.println(ex);  
                   }  }
          else 
          {  
            new Login();
	    setVisible(false); 
          }  
    }  
    public static void main(String args[])  
    {  
        new forget();  
    }  
}  
 