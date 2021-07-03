import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;
import javax.imageio.*;
import java.io.*;

public class UpdateDoctor extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
	JPasswordField tf8;
	JButton b1;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String dadd,dphno,dname;
	JPanel pBackground;

	UpdateDoctor()
	{
		setLayout(null);

		l1=new JLabel("DOCTOR_ID :");
		l1.setSize(150,30)	;
		l1.setLocation(50,50);
		add(l1);

		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(200,50);
		add(tf1);
		//tf1.addKeyListener(this);

		l2=new JLabel("DOCTOR_NAME :");
		l2.setSize(150,30)      ;
		l2.setLocation(50,100);
		add(l2);
		
		l9=new JLabel("Only Character");
		l9.setSize(150,30)      ;
		l9.setLocation(460,100);
		add(l9);

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


		l3=new JLabel("DOCOTOR_ADDRESS :");
		l3.setSize(150,30)      ;
		l3.setLocation(50,150);
		add(l3);

		tf3=new JTextField();
		tf3.setSize(250,30);
		tf3.setLocation(200,150);
		add(tf3);

		l4=new JLabel("PHONE_NUMBER :");
                l4.setSize(150,30)      ;
                l4.setLocation(50,200);
                add(l4);
	
		l10=new JLabel("10 Digits");
                l10.setSize(150,30)      ;
                l10.setLocation(460,200);
                add(l10);

		tf4=new JTextField();
                tf4.setSize(250,30);
                tf4.setLocation(200,200);
                add(tf4);
	
		tf4.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				if(Character.isDigit(ch) || ch=='\b')
				{
				String t=tf4.getText();
				if(t.length()==10)
				{
				ke.setKeyChar('\b');
				}
				return;
				}
				ke.setKeyChar('\b');
				}
				});
			
		
		l5=new JLabel("SPECIFICATION");
		l5.setSize(150,30);
		l5.setLocation(50,250);
		add(l5);
		
		tf5=new JTextField();
		tf5.setSize(250,30);
		tf5.setLocation(200,250);
		add(tf5);

		l6=new JLabel("QUALIFICATION");
		l6.setSize(150,30);
		l6.setLocation(50,300);
		add(l6);
		
		tf6=new JTextField();
		tf6.setSize(250,30);
		tf6.setLocation(200,300);
		add(tf6);

		l7=new JLabel("DOCTOR_EMAIL");
		l7.setSize(150,30);
		l7.setLocation(50,350);
		add(l7);

		tf7=new JTextField();
		tf7.setSize(250,30);
		tf7.setLocation(200,350);
		add(tf7);
	
		l8=new JLabel("PASSWORD");
		l8.setSize(150,30);
		l8.setLocation(50,400);
		add(l8);

		tf8=new JPasswordField();
		tf8.setSize(250,30);
		tf8.setLocation(200,400);
		add(tf8);
	
		
		b1=new JButton("Update");
		b1.addActionListener(this);
		b1.setSize(100,30);
		b1.setLocation(250,450);
		add(b1);

		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("Hospital.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,1350,650,this);
				}
				catch(Exception e)
 				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(1350,650);
		pBackground.setLocation(0,0);
		add(pBackground);


		setTitle("UPDATE_DOCTOR SCREEN");
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
			String s=(String)ae.getActionCommand();

		if(s.equals("Update"))
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
					
					int did=Integer.parseInt(tf1.getText());
					String dname=tf2.getText();
					
					String dadd=tf3.getText();
					
					String dphno=tf4.getText();
					String specification=tf5.getText();
					String qualification=tf6.getText();
					String demail=tf7.getText();
					int password=Integer.parseInt(tf8.getText());
					

		ps=con.prepareStatement("Update doctor set dname=?,dadd=?,demail=?,specification=?,qualification=?,dphno=?,password=? where did=?");
					
					ps.setString(1,dname);
					ps.setString(2,dadd);					
					ps.setString(3,demail);
					ps.setString(4,specification);
					ps.setString(5,qualification);
					ps.setBigDecimal(6,new BigDecimal(dphno));		
					ps.setInt(7,password);
					ps.setInt(8,did);

				int sig=ps.executeUpdate();
		                if(sig==0)
		                {
		                        JOptionPane.showMessageDialog(null,"Unable to Update....");
		                }
		                else
		                {
		                        JOptionPane.showMessageDialog(null,"Record Update succesfully...");
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
	  
    	public void keyTyped(KeyEvent e) 
	{
	}


	public static void main(String[] args)
	{
		new UpdateDoctor();	
	}
}
