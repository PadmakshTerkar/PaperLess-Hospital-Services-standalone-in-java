import java.math.BigDecimal;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.imageio.*;
import java.io.*;



class ForgetPwd extends JFrame implements ActionListener
{
	JLabel l,l0,l1,l2,l3,l4,l5;
	JTextField tf,tf1,tf2,tf4;
	JPasswordField tf3;
	JButton b1;
	JComboBox jb1;
	Connection con;
	PreparedStatement ps;
	Connection con1;
	PreparedStatement ps1;
	JPanel pBackground;


	ForgetPwd()
	{
		setLayout(null);

		l0=new JLabel("TYPE");
                l0.setSize(100,30);
                l0.setLocation(50,10);
                add(l0);

		String type[]={"ReceptionList"};
		jb1=new JComboBox(type);
		jb1.setSize(250,30);
                jb1.setLocation(150,10);
                add(jb1);

		

		l=new JLabel("USER_id :");
		l.setSize(150,30);
		l.setLocation(50,50);
		add(l);
		
		tf=new JTextField();
		tf.setSize(250,30);
		tf.setLocation(150,50);
		add(tf);

		l1=new JLabel("USERNAME :");
		l1.setSize(150,30);
		l1.setLocation(50,100);
		add(l1);
		
		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(150,100);
		add(tf1);

		tf1.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				if(Character.isLetter(ch) || ch=='\b')
				{
				String t=tf1.getText();
				//if(t.length()==10)
				//{
				//	ke.setKeyChar('\b');
				//}
				return;
				}
				ke.setKeyChar('\b');
				}
				});


		
		l2=new JLabel("PHONE");
		l2.setSize(150,30);
		l2.setLocation(50,150);
		add(l2);
		
		l5=new JLabel("10 Digits");
                l5.setSize(150,30)      ;
                l5.setLocation(150,50);
                add(l5);

		tf2=new JTextField();
		tf2.setSize(250,30);
		tf2.setLocation(150,150);
		add(tf2);

		tf2.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				if(Character.isDigit(ch) || ch=='\b')
				{
				String t=tf2.getText();
				if(t.length()==10)
				{
				ke.setKeyChar('\b');
				}
				return;
				}
				ke.setKeyChar('\b');
				}
				});
	

		l4=new JLabel("EMAIL");
		l4.setSize(150,30);
		l4.setLocation(50,200);
		add(l4);
		
		tf4=new JTextField();
		tf4.setSize(250,30);
		tf4.setLocation(150,200);
		add(tf4);

		l3=new JLabel("PASSWORD");
		l3.setSize(150,30);
		l3.setLocation(50,250);
		add(l3);
		
		tf3=new JPasswordField();
		tf3.setSize(250,30);
		tf3.setLocation(150,250);
		add(tf3);

		


		b1=new JButton("Signup");
                b1.addActionListener(this);
		b1.setSize(100,30);
                b1.setLocation(150,300);
                add(b1);
		
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,500,600,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};

		pBackground.setSize(500,600);
		pBackground.setLocation(0,0);
		add(pBackground);

		setTitle("Forget_Password Screen");
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


	}
	public void actionPerformed(ActionEvent ae)
	{

		String s=(String)ae.getActionCommand();
		String uid=tf.getText();
		String usrname=tf1.getText();
		String dphno=tf2.getText();
		String newemail=tf4.getText();
								
		String Pwd=tf3.getText();
		String l_type=(String)jb1.getSelectedItem();
		
			
		if(s.equals("Signup"))
		{
			//JOptionPane.showMessageDialog(null,"Signup Successfull");
			//System.exit(0);
 				try
		                 {
		                        Class.forName("org.postgresql.Driver");
		                        con=DriverManager.getConnection("jdbc:postgresql://localhost/tydb","root","redhat");

		                        if(con==null)
		                        {
 		                               System.out.println("Unable to connect....");
		                                System.exit(0);
 		                       	}
					int usid=Integer.parseInt(tf.getText());
					String uname=tf1.getText(); 
					String email=tf4.getText();
					String phno=tf2.getText();
					String pwd=tf3.getText();
			

					ps=con.prepareStatement("Update receptionlist set rname=?,email=?,phno=?,pwd=? where rid=?");
					ps.setInt(5,usid);					
					ps.setString(1,uname);		ps.setString(2,email);
					ps.setBigDecimal(3,new BigDecimal(phno));		ps.setString(4,pwd);


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
         			  JOptionPane.showMessageDialog(null,"ERROR:"+ex.getMessage());
         			}
				new LogIn();
		}
	}

	public static void main(String[] args)
	{
		ForgetPwd ob=new ForgetPwd();
	}
} 
