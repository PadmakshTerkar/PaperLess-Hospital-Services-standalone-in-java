import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import javax.imageio.*;
import java.io.*;


class ViewPatient extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l10,l6,l7,l8,l9;
	JTextField tf1,tf2,tf3,tf5,tf6,tf7,tf8,tf9;
	JTextArea ta10,ta4;	
	JButton b1,b2;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	JPanel pBackground;


	ViewPatient()
	{
		setLayout(null);

		l1=new JLabel("PATIENT_ID :");
		l1.setSize(150,30);
		l1.setLocation(200,50);
		add(l1);

		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(400,50);
		//tf1.addKeyListener(this);
		add(tf1);

		l2=new JLabel("PATIENT NAME :");
		l2.setSize(150,30);
		l2.setLocation(200,100);
		add(l2);

		tf2=new JTextField();
		tf2.setSize(250,30);
		tf2.setLocation(400,100);
		add(tf2);

		l3=new JLabel("PATIENT_PHONE :");
		l3.setSize(150,30);
		l3.setLocation(200,150);
		add(l3);

		tf3=new JTextField();
		tf3.setSize(250,30);
		tf3.setLocation(400,150);
		add(tf3);

		l4=new JLabel("PATIENT_ADDRESS :");
		l4.setSize(150,30);
		l4.setLocation(200,200);
		add(l4);

		ta4=new JTextArea();
		ta4.setSize(250,30);
		ta4.setLocation(400,200);
		add(ta4);

		l5=new JLabel("PATIENT_EMAIL :");
		l5.setSize(150,30);
		l5.setLocation(200,250);
		add(l5);

		tf5=new JTextField();
		tf5.setSize(250,30);
		tf5.setLocation(400,250);
		add(tf5);
		
		
		/*l10=new JLabel("PATIENT_DETAILS:");
		l10.setSize(150,30);
		l10.setLocation(130,100);
		add(l10);

		ta10=new JTextArea();
		ta10.setSize(300,400);
		ta10.setLocation(300,100);
		add(ta10);
		*/
		b1=new JButton("VIEW");
		b1.addActionListener(this);
		b1.setSize(100,30);
		b1.setLocation(400,550);
		add(b1);
	
		b2=new JButton("DONE");
		b2.addActionListener(this);
		b2.setSize(100,30);
		b2.setLocation(550,550);
		add(b2);
	
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,900,900,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(900,900);
		pBackground.setLocation(0,0);
		add(pBackground);


	
		setTitle("VIEW_PATIENT");
		setSize(900,900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}	
	public void keyReleased(KeyEvent e)
	{ 
    	}
	public void actionPerformed(ActionEvent ae)
	{
		String s=(String)ae.getActionCommand();

		if(s.equals("VIEW"))
		{
			int pid=Integer.parseInt(tf1.getText());
			
				try
		                 {
		                        Class.forName("org.postgresql.Driver");
		                        con=DriverManager.getConnection("jdbc:postgresql://localhost/tydb","root","redhat");

		                        if(con==null)
		                        {
 		                               System.out.println("Unable to connect....");
		                                System.exit(0);
 		                       	}
					
				
					ps=con.prepareStatement("Select pname,padd,pphno,pemail from patient where pid=?");
					ps.setInt(1,pid);

					String pname="",padd="",pphno="",pemail="";

					rs=ps.executeQuery();
 					while(rs.next())
		                   	{			//pid=rs.getString(1);	   
								
                                                        	pname=rs.getString(1);     padd=rs.getString(2);
                                                        	pphno=rs.getString(3);     pemail=rs.getString(4);
								break;
		                        }
		                        rs.close();
					//JOptionPane.showMessageDialog(null,pname+""+padd+""+pphno+""+pemail);
					
					//ta10.setText(rid);
					tf2.setText(pname);
					tf3.setText(pphno);
					ta4.setText(padd);
					tf5.setText(pemail);
					

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
				else if(s.equals("DONE"))
				{
					new HomeScreen();
				}
			
	
        
} 
    	public void keyTyped(KeyEvent e) 
	{
	}
	public static void main(String[] args)
	{
		ViewPatient ob1=new ViewPatient();
	}

}

