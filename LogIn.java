import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.imageio.*;
import java.io.*;

class LogIn extends JFrame implements ActionListener
{
        JLabel l0,l1,l2,l3,l4;
        JTextField tf1;
	JPasswordField tf2;
        JButton b1,b2;
	JComboBox jb1;	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	JPanel pBackground;

       	LogIn()
        {

                setLayout(null);

		l0=new JLabel("TYPE");
                l0.setSize(100,30);
                l0.setLocation(50,10);
                add(l0);

		String type[]={"ReceptionList","Doctor"};
		jb1=new JComboBox(type);
		jb1.setSize(250,30);
                jb1.setLocation(150,10);
                add(jb1);

                l1=new JLabel("User ID");
                l1.setSize(100,30);
                l1.setLocation(50,50);
                add(l1);
		

                tf1=new JTextField();
                tf1.setSize(250,30);
                tf1.setLocation(150,50);
                add(tf1);
		
		l2=new JLabel("Password");
		l2.setSize(100,30);
		l2.setLocation(50,100);
		add(l2);
		
		tf2=new JPasswordField();
		tf2.setSize(250,30);
		tf2.setLocation(150,100);
		add(tf2);

                b1=new JButton("Login");
                b1.addActionListener(this);
		b1.setSize(100,30);
                b1.setLocation(150,150);
                add(b1);

                b2=new JButton("Cancel");
		b2.addActionListener(this);
                b2.setSize(100,30);
                b2.setLocation(300,150);
                add(b2);
		
		l3=new JLabel("Forget Password?");
		l3.setForeground(Color.BLUE);
		l3.setSize(200,30);
		l3.setLocation(300,200);
		l3.addMouseListener(new MouseAdapter()   {   
        	public void mouseClicked(MouseEvent e)   
        	{   
			setVisible(false);
        	      new ForgetPwd();
          	
        	}   
		});
		add(l3);


		l4=new JLabel("Signup");
		l4.setForeground(Color.BLUE);
		l4.setSize(200,30);
		l4.setLocation(100,200);
		l4.addMouseListener(new MouseAdapter()   {   
        	public void mouseClicked(MouseEvent e)   
        	{   
			setVisible(false);
        	      new SignUp();
          	
        	}   
		});
		add(l4);
	
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,500,300,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(500,300);
		pBackground.setLocation(0,0);
		add(pBackground);

		
		setTitle("PAPERLESS HOSPITAL SERVICES");
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String s=(String)ae.getActionCommand();
		String uname=tf1.getText();
		String Passwd=tf2.getText();
		String l_type=(String)jb1.getSelectedItem();
		System.out.println(" "+uname+" "+Passwd+" "+l_type);
		
		if(s.equals("Login"))
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
					
					if(l_type.equals("ReceptionList"))
				{	System.out.println("Recp");
					ps=con.prepareStatement("Select pwd from receptionlist where rname=?");}
					else if(l_type=="Doctor")
					ps=con.prepareStatement("Select password from doctor where dname=?");
					ps.setString(1,uname);

					String paswd="";

					rs=ps.executeQuery();
 					while(rs.next())
		                   	{
                                                        	paswd=rs.getString(1);
								break;
		                        }
			if (Passwd.compareTo(paswd)==0)
			{
				JOptionPane.showMessageDialog(null, "Login Successfull");
				if(l_type=="ReceptionList")
					new HomeScreen();
				else if(l_type=="Doctor")
					new CheckUp();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Login Fail");
				new LogIn();
			}
		
		                        rs.close();

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

			
		}
		else if(s.equals("Cancel"))
		{
			
			tf1.setText("");
			tf2.setText("");
									
		}
	}
	
	public static void main(String args[])
	{
		LogIn ob=new LogIn();
	}
}

