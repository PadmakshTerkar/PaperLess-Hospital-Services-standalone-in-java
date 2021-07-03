import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;
import javax.imageio.*;
import java.io.*;

class UpdatePatient extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JTextField tf1,tf2,tf3,tf4,tf5;
	JButton b1;
	Connection con;
	PreparedStatement ps;
	JPanel pBackground;

	UpdatePatient()
	{
		setLayout(null);

		l1=new JLabel("PATIENT_ID");
		l1.setSize(150,30);
		l1.setLocation(50,50);
		add(l1);

		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(200,50);
		add(tf1);

		l2=new JLabel("PATIENT_	NAME");
		l2.setSize(150,30);
		l2.setLocation(50,100);
		add(l2);
		
		l6=new JLabel("Only Character");
		l6.setSize(150,30)      ;
		l6.setLocation(460,100);
		add(l6);


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

		l3=new JLabel("PATIENT_PHONE");
		l3.setSize(150,30);
		l3.setLocation(50,150);
		add(l3);
		
		l7=new JLabel("10 Digits");
                l7.setSize(150,30)      ;
                l7.setLocation(460,150);
                add(l7);

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
		

		l4=new JLabel("PATIENT_ADDRESS");
		l4.setSize(150,30);
		l4.setLocation(50,200);
		add(l4);

		tf4=new JTextField();
		tf4.setSize(250,30);
		tf4.setLocation(200,200);
		add(tf4);

		l5=new JLabel("PATIENT_EMAIL");
		l5.setSize(150,30);
		l5.setLocation(50,250);
		add(l5);

		tf5=new JTextField();
		tf5.setSize(250,30);
		tf5.setLocation(200,250);
		add(tf5);
		
		b1=new JButton("Save");
		b1.addActionListener(this);
		b1.setSize(100,30);
		b1.setLocation(250,320);
		add(b1);
	
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("Hospital.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,600,500,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(600,500);
		pBackground.setLocation(0,0);
		add(pBackground);



		setTitle("UPDATE_PATIENT");
		setSize(600,500);
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
					String pname=tf2.getText();
					
					String padd=tf4.getText();
					
					String pphno=tf3.getText();
					String pemail=tf5.getText();

					

					ps=con.prepareStatement("Update patient set pname=?,pphno=?,padd=?,pemail=? where pid=?");
					ps.setInt(5,pid);
					ps.setString(1,pname);					ps.setString(3,padd);
					ps.setBigDecimal(2,new BigDecimal(pphno));		ps.setString(4,pemail);
					

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
	public static void main(String[] args)
	{
		UpdatePatient ob1=new UpdatePatient();
	}

}

