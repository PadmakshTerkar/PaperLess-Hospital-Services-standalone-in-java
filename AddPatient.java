import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;
import javax.imageio.*;
import java.io.*;

class AddPatient extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField tf1,tf2,tf3,tf5,tf6;
	JTextArea ta4;
	JButton b1;
	Connection con;
	PreparedStatement ps;
	JPanel pBackground;


	AddPatient()
	{
		setLayout(null);

		l1=new JLabel("PATIENT_ID :");
		l1.setSize(150,30);
		l1.setLocation(50,50);
		add(l1);
		
		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(200,50);
		add(tf1);

		l2=new JLabel("PATIENT_NAME :");
		l2.setSize(150,30);
		l2.setLocation(50,100);
		add(l2);

		l7=new JLabel("Only Character");
		l7.setSize(150,30)      ;
		l7.setLocation(480,100);
		add(l7);

		tf2=new JTextField();
		tf2.setSize(250,30);
		tf2.setLocation(200,100);
		add(tf2);

		tf2.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				if(Character.isLetter(ch) || ch=='\b')
				{
				String t=tf2.getText();
				//if(t.length()==10)
				//{
				//	ke.setKeyChar('\b');
				//}
				return;
				}
				ke.setKeyChar('\b');
				}
				});


		l3=new JLabel("PATIENT_PHONE :");
		l3.setSize(150,30);
		l3.setLocation(50,150);
		add(l3);

		l8=new JLabel("10 Digits");
                l8.setSize(150,30)      ;
                l8.setLocation(480,150);
                add(l8);
		
		
		tf3=new JTextField();
		tf3.setSize(250,30);
		tf3.setLocation(200,150);
		add(tf3);

		tf3.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				if(Character.isDigit(ch) || ch=='\b')
				{
				String t=tf3.getText();
				if(t.length()==10)
				{
				ke.setKeyChar('\b');
				}
				return;
				}
				ke.setKeyChar('\b');
				}
				});
	

		l4=new JLabel("PATIENT_ADDRESS :");
                l4.setSize(150,30)      ;
                l4.setLocation(50,200);
                add(l4);

                ta4=new JTextArea();
                ta4.setSize(250,180);
                ta4.setLocation(200,200);
                add(ta4);		
		
		l5=new JLabel("PATIENT_EMAIL :");
		l5.setSize(150,30);
		l5.setLocation(50,400);
		add(l5);
		
		tf5=new JTextField();
		tf5.setSize(250,30);
		tf5.setLocation(200,400);
		add(tf5);

		l6=new JLabel("Doctor id :");
		l6.setSize(150,30);
		l6.setLocation(50,450);
		add(l6);
		
		tf6=new JTextField();
		tf6.setSize(250,30);
		tf6.setLocation(200,450);
		add(tf6);


		b1=new JButton("Save");
		b1.addActionListener(this);
		b1.setSize(100,30);
		b1.setLocation(250,550);
		add(b1);
		
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,800,800,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(800,800);
		pBackground.setLocation(0,0);
		add(pBackground);


		setTitle("ADD_PATIENT SCREEN");
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String s=(String)ae.getActionCommand();

		if(s.equals("Save"))
		{
			try
		                 {
		                        Class.forName("org.postgresql.Driver");
		                        con=DriverManager.getConnection("jdbc:postgresql://localhost/tydb","root","redhat");

		                        if(con==null)
		                        {
 		                               System.out.println("Unable to connect....");
		                                System.exit(0);
 		                       	}
					
					int pid=Integer.parseInt(tf1.getText());
					int rid=Integer.parseInt("1");
					String pname=tf2.getText();
					
					String padd=ta4.getText();
					
					String pphno=tf3.getText();
					String pemail=tf5.getText();
					int did=Integer.parseInt(tf6.getText());

					ps=con.prepareStatement("Insert into patient(pid,rid,pname,padd,pphno,pemail,did) values(?,?,?,?,?,?,?)");
					ps.setInt(1,pid);					ps.setInt(2,rid);
					ps.setString(3,pname);					ps.setString(4,padd);
					ps.setBigDecimal(5,new BigDecimal(pphno));		ps.setString(6,pemail);
					ps.setInt(7,did);
				int sig=ps.executeUpdate();
		                if(sig==0)
		                {
		                        JOptionPane.showMessageDialog(null,"Unable to insert....");
		                }
		                else
		                {
		                        JOptionPane.showMessageDialog(null,"Record inserted succesfully...");
		                }
		
             		           ps.close();
             		           con.close();
					
				}
				catch(SQLException ex)
         			{
         	 			 JOptionPane.showMessageDialog(null,ex.getMessage());
         			}
         			catch(ClassNotFoundException ex)
         			{
         				   System.out.println("Class Not Found");
         			}
         			catch(Exception ex)
         			{
         			  JOptionPane.showMessageDialog(null,"ERROR3:"+ex.getMessage());
         			}

			JOptionPane.showMessageDialog(null,"Save Successfully");
			new HomeScreen();
		}
	}


	public static void main(String[] args)
	{
		AddPatient ob=new AddPatient();	
	}
}
