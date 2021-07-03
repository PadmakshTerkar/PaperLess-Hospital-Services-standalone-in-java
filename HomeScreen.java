import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;


public class HomeScreen extends JFrame implements ActionListener
{
	JMenuBar mb;
 	JMenuItem ad_doc,rm_doc,up_doc,ad_pat,rm_pat,up_pat,vw_pat,log_out; 
	JMenu doc,pat,Home;
	JPanel pBackground;

	HomeScreen()
	{
		setLayout(null);

		mb=new JMenuBar();
		mb.setSize(200,50);
		mb.setLocation(0,0);
		add(mb);

		doc=new JMenu("DOCTOR");
		pat=new JMenu("PATIENT");
		Home=new JMenu("Home");
		
		ad_doc=new JMenuItem("ADD_DOCTOR");
		rm_doc=new JMenuItem("DEL_DOCTOR");
		up_doc=new JMenuItem("UPD_DOCTOR");
		ad_pat=new JMenuItem("ADD_PATIENT");
		rm_pat=new JMenuItem("DEL_PATIENT");
		up_pat=new JMenuItem("UPD_PATIENT");
		vw_pat=new JMenuItem("VIEW_PATIENT");
		log_out=new JMenuItem("Log_Out");
		ad_doc.addActionListener(this);
		rm_doc.addActionListener(this);
		up_doc.addActionListener(this);
		ad_pat.addActionListener(this);
		rm_pat.addActionListener(this);
		up_pat.addActionListener(this);
		vw_pat.addActionListener(this);
		log_out.addActionListener(this);
		doc.add(ad_doc);
		doc.add(rm_doc);
		doc.add(up_doc);


		pat.add(ad_pat);
		pat.add(rm_pat);
		pat.add(up_pat);
		pat.add(vw_pat);
		
		Home.add(log_out);
		
		mb.add(doc);
		mb.add(pat);
		mb.add(Home);
		setJMenuBar(mb);
	
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,500,500,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(500,500);
		pBackground.setLocation(0,0);
		add(pBackground);
				
		setSize(500,500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		String name=(String)e.getActionCommand();
		
		if(name.equals("ADD_DOCTOR"))
		{ 
			new AddDoctor();
		}
		else if(name.equals("DEL_DOCTOR"))
		{
			new DeleteDoctor();
		}
		else if(name.equals("UPD_DOCTOR"))
		{
			new UpdateDoctor();
		}
		else if(name.equals("ADD_PATIENT"))
		{
			new AddPatient();
		}
		else if(name.equals("DEL_PATIENT"))
		{
			new DeletePatient();
		}
		else if(name.equals("UPD_PATIENT"))
		{
			new UpdatePatient();
		}
		else if(name.equals("VIEW_PATIENT"))
		{
			new ViewPatient();
		}
		else if(name.equals("Log_Out"))
		{
			new LogIn();
		}		
	}
	public static void main(String[] args)
	{
		new HomeScreen();
	}
}
