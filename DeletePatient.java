import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;
import javax.imageio.*;
import java.io.*;


class DeletePatient extends JFrame implements ActionListener
{
	JLabel l1;
	JTextField tf1;
	JButton b1;
	Connection con;
	PreparedStatement ps;
	JPanel pBackground;

	DeletePatient()
	{
		setLayout(null);

		
		l1=new JLabel("PATIENT_Id :");
		l1.setSize(150,30);
		l1.setLocation(100,50);
		add(l1);
		
		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(200,50);
		add(tf1);
		
		b1=new JButton("Delete");
		b1.addActionListener(this);
		b1.setSize(100,30);
		b1.setLocation(200,100);
		add(b1);
		
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,500,400,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(500,400);
		pBackground.setLocation(0,0);
		add(pBackground);



		setTitle("DELETE_PATIENT");
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String d=(String)ae.getActionCommand();

		if(d.equals("Delete"))
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
					

					ps=con.prepareStatement("Delete from patient where pid=?");
					ps.setInt(1,pid);					
					

				int sig=ps.executeUpdate();
		                if(sig==0)
		                {
		                        JOptionPane.showMessageDialog(null,"Unable to delete....");
		                }
		                else
		                {
		                        JOptionPane.showMessageDialog(null,"Record delete succesfully...");
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
		DeletePatient ob1=new DeletePatient();
	}

}
