package Browsermanagement;
import static Browsermanagement.Browselect.selected;
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

class Browelement
{
  static Set selelement=new HashSet();
  static String maindir,str;
  static JLabel l1;
  static int k,l;
  static String s;
  static String[] element={"History","Extensions","Cookies","Passwords","Bookmarks"};
  static int lenx=element.length;
 static JCheckBox[] data = new JCheckBox[lenx];
  static int limit=lenx*100;
  static JButton all,del,back;
  static JFrame jf=new JFrame();
  public static void browelement()
          {
            jf.setVisible(true);
            jf.setSize(400,400);
            jf.setLayout(null);
            l1=new JLabel("Select option to clear");
   l1.setBounds(70,50,200,30);
   jf.add(l1);
   for(k=0,l=0;l<limit&&k<lenx;l=l+40,k++)
        {
s= element[k];  
      data[k] = new JCheckBox(s);
              data[k].setBounds(100,80+l,200,50);
              jf.add(data[k]);       
}
            all=new JButton("Select all");
            del=new JButton("Clear data");
            back=new JButton("Back");
            all.setBounds(120,330,100,40);
            del.setBounds(220,330,100,40);
            back.setBounds(320,330,100,40);
            jf.add(all);
            jf.add(del);
            jf.add(back);
all.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent evt) {
    if (evt.getClickCount()%2!=0) {
    for(int i=0;i<lenx;i++)
                  data[i].setSelected(true);
    }
    else if(evt.getClickCount()%2==0){
      for(int i=0;i<lenx;i++)
                  data[i].setSelected(false);
    }
  }
       });
 
del.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
             {
                 Iterator iterator1 = selected.iterator();
for(int i=0;i<lenx;i++)
    {  
          if(data[i].isSelected()){      
          selelement.add(data[i].getLabel());    
          }
          }
System.out.println(selelement);
for(int i=0;(iterator1.hasNext())&&i<selected.size();i++)
{
 str = (String) iterator1.next();
        switch(str)
        {
           case "chrome":
                System.out.println("chrome");
                maindir= ("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\");
		deletefiles(maindir);
                break;
           case "iexplore":
                System.out.println("iexplore");
                break;
           case "Safari":
                System.out.println("safari");
                maindir=("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\Apple Computer\\Safari\\");
                deletefiles(maindir);
                break;
           case "firefox":
                System.out.println("firefox");
                maindir=("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\noj1w363.default-release-1573743039881\\");
               deletefirefox(maindir);
                break;
           case "Maxthon":
                System.out.println("Maxthon");
                break;
           case "torch":
                System.out.println("torch");
                maindir=("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\Torch\\User Data\\Default\\");
                deletefiles(maindir);
                break;
           case "opera":
                System.out.println("opera");
                maindir= ("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\Opera Software\\Opera Stable\\");
                deletefiles(maindir);
                break;
               default:
                System.out.println("no match");
        }
        JOptionPane.showMessageDialog((Component)e.getSource()," Data deleted successfully ");
}             }
         private void deletefiles(String maindir) {
         String dir;
	dir=maindir;
             Iterator iterator = selelement.iterator();
        for(int i=0;(iterator.hasNext())&&i<selelement.size();i++)
        {
		s= (String) iterator.next();
		if(s.equals("History")||s.equals("Cookies")||s.equals("Extensions")||s.equals("Bookmarks")){
	        if(str.equals("Safari"))
                     maindir=maindir+s+".plist";        
                else
                      maindir=maindir+s;	
                 System.out.println(maindir);
			try{
				File f= new File(maindir);  
				if (f.isFile()) {
					if(f.delete())                      
					{  
						System.out.println(f.getName() + " deleted");  
					}  
					else  
					{  
						System.out.println("failed");  
					} 
				}
				else
				{
					boolean success=deleteDir(f); 
				}
			}  
			catch(Exception a)  
			{  
				a.printStackTrace();  
			}

		}
            else
		{
			maindir=maindir+"Login Data";
			try{
				File f= new File(maindir);  
				if (f.isFile()) {
					if(f.delete())                      
					{  
						System.out.println(f.getName() + " deleted");  
					}  
					else  
					{  
						System.out.println("failed");  
					} 
				}
				else
				{
					boolean success=deleteDir(f); 
				}
			}  
			catch(Exception a)  
			{  
				a.printStackTrace();  
			}


	         }
                maindir=dir;
} }
          private void deletefirefox(String maindir) 
          {
         String dir;
	dir=maindir;
             Iterator iterator = selelement.iterator();
        for(int i=0;(iterator.hasNext())&&i<selelement.size();i++)
        {
		String srt= (String) iterator.next();
           switch(srt){
         
          case "History":maindir=maindir+"places.sqlite";
                        deletefire(maindir);
                        break;
          case "Cookies":maindir=maindir+"cookies.sqlite";
                        deletefire(maindir);
                        break;
          case "Bookamarks":maindir=maindir+"places.sqlite";
                        deletefire(maindir);
                         break;
          case "Extensions":maindir=maindir+"extensions";
                         deletefire(maindir);
                         break;       
                         
                         
           }
        maindir=dir;}}
public void deletefire(String maindir){
    System.out.println(maindir);
                            try{
				File f= new File(maindir);  
				if (f.isFile()) {
					if(f.delete())                      
					{  
						System.out.println(f.getName() + " deleted");  
					}  
					else  
					{  
						System.out.println("failed");  
					} 
				}
				else
				{
					boolean success=deleteDir(f); 
				}
			}  
			catch(Exception a)  
			{  
				a.printStackTrace();  
			}

		}

public  boolean deleteDir(File dir) {
      if (dir.isDirectory()) {
         String[] children = dir.list();
         for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir (new File(dir, children[i]));
            
            if (!success) {
               return false;
            }
         }
      }
       System.out.println("The directory is deleted.");
      return dir.delete();
    }
           
});
    
          back.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
          System.out.println("Back");
          //new Browselect();
          jf.setVisible(false);
          }
          });
          }
public static void main(String args[])
  {
    browelement();
  }}

