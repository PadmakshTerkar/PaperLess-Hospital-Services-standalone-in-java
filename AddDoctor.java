import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import javax.imageio.*;
import java.io.*;

class AddDoctor extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	JTextField tf1,tf2,tf4,tf5,tf6,tf7,tf8;
	JButton b1;
	Connection con;
	PreparedStatement ps;
	JPanel pBackground;
	JTextArea ta3;

	AddDoctor()
	{
		setLayout(null);

		l1=new JLabel("DOCTOR_ID :");
		l1.setSize(150,30)	;
		l1.setLocation(150,50);
		add(l1);
		
		
		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(250,50);
		add(tf1);
	
		l2=new JLabel("DOCTOR_NAME :");
		l2.setSize(150,30)      ;
		l2.setLocation(125,100);
		add(l2);
		
		l9=new JLabel("Only Character");
		l9.setSize(150,30)      ;
		l9.setLocation(500,100);
		add(l9);

		tf2=new JTextField();
		tf2.setSize(250,30);
		tf2.setLocation(250,100);
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
		l3.setLocation(90,150);
		add(l3);

		ta3=new JTextArea();
		ta3.setSize(250,120);
		ta3.setLocation(250,150);
		add(ta3);

		l4=new JLabel("PHONE_NUMBER :");
                l4.setSize(150,30)      ;
                l4.setLocation(120,300);
                add(l4);
		
		l10=new JLabel("10 Digits");
                l10.setSize(150,30)      ;
                l10.setLocation(500,300);
                add(l10);

                tf4=new JTextField();
                tf4.setSize(250,30);
                tf4.setLocation(250,300);
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
	
		
		l5=new JLabel("SPECIFICATION :");
		l5.setSize(150,30);
		l5.setLocation(130,350);
		add(l5);
		
		tf5=new JTextField();
		tf5.setSize(250,30);
		tf5.setLocation(250,350);
		add(tf5);

		l6=new JLabel("QUALIFICATION :");
		l6.setSize(150,30);
		l6.setLocation(130,400);
		add(l6);
		
		tf6=new JTextField();
		tf6.setSize(250,30);
		tf6.setLocation(250,400);
		add(tf6);
		
		l7=new JLabel("DOCTOR EMAIL :");
		l7.setSize(150,30);
		l7.setLocation(130,450);
		add(l7);
		
		tf7=new JTextField();
		tf7.setSize(250,30);
		tf7.setLocation(250,450);
		add(tf7);

		/*tf7=addKeyListener(new KeyAdapter()
			{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				String e=tf7.getText();
				String email="^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";	
				 if(e.equals(email))
				{
				  return true;
				}
				else 
				{
					System.out.println("Invalid email...");
				}
			}*/
	
		l8=new JLabel("PASSWORD");
		l8.setSize(150,30);
		l8.setLocation(130,500);
		add(l8);
		
		tf8=new JPasswordField();
		tf8.setSize(250,30);
		tf8.setLocation(250,500);
		add(tf8);

		b1=new JButton("Save");
		b1.addActionListener(this);
		b1.setSize(100,30);
		b1.setLocation(330,550);
		add(b1);
		
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,700,700,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(700,700);
		pBackground.setLocation(0,0);
		add(pBackground);

		setTitle("ADD_DOCTOR SCREEN");
		setSize(700,700);
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
					
					int did=Integer.parseInt(tf1.getText());
					String dname=tf2.getText();
					String dadd=ta3.getText();
					String pphno=tf4.getText();
					String spec=tf5.getText();
					String qual=tf6.getText();
					String mail=tf7.getText();
					String pwd=tf8.getText();


			ps=con.prepareStatement("Insert into doctor(did,dname,dadd,specification,qualification,dphno,demail,password ) values(?,?,?,?,?,?,?,?)");
					ps.setInt(1,did);					
					ps.setString(2,dname);
					ps.setString(3,dadd);					
					ps.setString(4,spec);
					ps.setString(5,qual);
					ps.setBigDecimal(6,new BigDecimal(pphno));	
					ps.setString(7,mail);
					ps.setString(8,pwd);
						
					

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
	public void keyTyped(KeyEvent e) 
	{
	}



	public static void main(String[] args)
	{
		new AddDoctor();	
	}
}
