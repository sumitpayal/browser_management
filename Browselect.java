package Browsermanagement;
import static Browsermanagement.Browelement.browelement;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.lang.String;

class Browselect{
      public static Set selected=new HashSet();
      static JButton sub,close,all;
   	private static boolean foundFolder = false;
	static String[] browser={"opera.exe","Safari.exe","torch.exe","Maxthon.exe","iexplore.exe","firefox.exe","chrome.exe"};
	static int len=browser.length;	
	static JCheckBox[] ch = new JCheckBox[len]; 
	static Set result = new HashSet();
   	public static void main(String[] args) {
         new Browselect();
        }
            Browselect(){
		int i=0,j;
        	String maindirpath[] = {"C:/Users","C:/Program Files (x86)","C:/Program Files"};	
                        while(i<=2)

       {
		File maindir = new File(maindirpath[i]); 
       		j=findDirectory(maindir);
		i++;
	}
	System.out.println(result);
	frameb();
    
   
        }
    
               private static int findDirectory(File parentDirectory) 
               {
                   if(foundFolder) {
			foundFolder = false;
           		return 0;
       		}
       		File[] files = parentDirectory.listFiles();
		try{
       			for (File file : files) {
           			if (file.isFile()) {
					for(int i=0;i<len;i++){
					if (file.getName().equals(browser[i])) {
               					foundFolder = true;
						result.add(file.getName());
            
           			}}
  			}
           if(file.isDirectory()) 
              findDirectory(file);
	       } 
	}
catch(Exception e){}

foundFolder = false;
return 0;
   }
   public static void frameb(){
	String s;
        int j=0,i;
	JFrame f=new JFrame("Browser");
        f.setVisible(true);
        f.setSize(600,600);
        f.setLayout(null);
        JLabel l=new JLabel("Select Browsers");
	int limit=result.size();
	limit=limit*100;
	Iterator iterator = result.iterator();
        for(i=0,j=0;(iterator.hasNext())&&j<limit&&i<len;j=j+40,i++) 
        {
	s= (String) iterator.next();
	if (s.indexOf(".") > 0)
            s = s.substring(0, s.lastIndexOf("."));
	      ch[i] = new JCheckBox(s);
             ch[i].setBounds(50,50+j,200,50);
             f.add(ch[i]);
             
}
        sub=new JButton("Submit");
        close=new JButton("Close");
        all=new JButton("Select all");
        l.setBounds(30,30,100,30);
        all.setBounds(80,70+j,130,40);
        sub.setBounds(210,70+j,100,40);
        close.setBounds(310,70+j,100,40);
        f.add(l);
        f.add(all);
        f.add(sub);
        f.add(close);
        
       all.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent evt) {
    if (evt.getClickCount()%2!=0) {
    for(int i=0;i<result.size();i++)
                  ch[i].setSelected(true);
    } 
    else if(evt.getClickCount()%2==0){
      for(int i=0;i<result.size();i++)
                  ch[i].setSelected(false);
    }
  }
       });  
        sub.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
    for(int i=0;i<result.size();i++)
    {   
         if(ch[i].isSelected()){      
         selected.add(ch[i].getLabel());    
         }
         }System.out.println(selected);
              browelement();
             f.setVisible(false);
} 
             });
        close.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
            System.out.println("close"); 
        }  
    });
  }
}