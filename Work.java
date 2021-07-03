import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import javax.imageio.*;
import java.io.*;


class Work extends JFrame implements ActionListener
{
	JLabel l1;
	JTable jt;
	JTextField tf1;
	JButton b1,b2;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	JPanel pBackground,p1;
	JScrollPane sp;
	JList list=new JList();
	DefaultListModel listModel=new DefaultListModel();
	


	Work()
	{
		setLayout(null);
		
			p1=new JPanel();
			p1.setBorder(BorderFactory.createTitledBorder("Patients_Checked_by_Doctor "));
			p1.setSize(470,300);
			p1.setLocation(10,150);
			p1.setLayout(new GridLayout(1,2));
			add(p1);


		l1=new JLabel("Doctor_ID :");
		l1.setSize(150,30);
		l1.setLocation(50,50);
		add(l1);

		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(200,50);
		add(tf1);

		b1=new JButton("VIEW");
		b1.addActionListener(this);
		b1.setSize(100,30);
		b1.setLocation(70,100);
		add(b1);
	
		b2=new JButton("DONE");
		b2.addActionListener(this);
		b2.setSize(100,30);
		b2.setLocation(220,100);
		add(b2);
		

		
		/*ls=new JList("");
		ls.setSize(250,30);
		ls.setLocation(200,100);	
		*/
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


	
		setTitle("WORK_DETAILS");
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
		System.out.println("hiii"+s);
		//listModel=new DefaultListModel();
		if(s.equals("VIEW"))
		{
			int did=Integer.parseInt(tf1.getText());
			
				try
		                 {
		                        Class.forName("org.postgresql.Driver");
		                        con=DriverManager.getConnection("jdbc:postgresql://localhost/tydb","root","redhat");

		                        if(con==null)
		                        {
 		                               System.out.println("Unable to connect....");
		                                System.exit(0);
 		                       	}
					
					
					ps=con.prepareStatement("Select pname from patient where did=? ");
					ps.setInt(1,did);

					String pname="";
					rs=ps.executeQuery();

					while(rs.next())
		                   	{			//pid=rs.getString(1);	   
								 pname=rs.getString(1);
								System.out.println("hii"+pname);
                                                        	listModel.addElement(pname);
							
		                        }

					list.setModel(listModel);
					p1.add(list);					
					setVisible(true);
					
					
		                        rs.close();
					
					
					//JOptionPane.showMessageDialog(null,pname+""+padd+""+pphno+""+pemail);
					
					//ta10.setText(rid);
				
				
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
					new CheckUp();
				}
			
	
        
}
    	

	
	public static void main(String[] args)
	{
		Work ob1=new Work();
	}

}
 
