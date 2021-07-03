import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


class CheckUp extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField tf1,tf2,tf3,tf4,tf5;
	JButton b1,b2,b3;
	JTextArea ta4,ta5;
	JPanel pBackground;

	CheckUp()
	{
		setLayout(null);

		l1=new JLabel("PATIENT_ID :");
		l1.setSize(150,30);
		l1.setLocation(100,50);
		add(l1);

		tf1=new JTextField();
		tf1.setSize(250,30);
		tf1.setLocation(200,50);
		add(tf1);
	
		
		l2=new JLabel("CHECKUP_ID :");
		l2.setSize(130,30);
		l2.setLocation(100,100);
		add(l2);

		tf2=new JTextField();
		tf2.setSize(250,30);
		tf2.setLocation(200,100);
		add(tf2);
	
		l3=new JLabel("DATE :");
		l3.setSize(50,30);
		l3.setLocation(150,150);
		add(l3);

		l6=new JLabel("DD/MM/YYYY");
		l6.setSize(100,30);
		l6.setLocation(480,150);
		add(l6);


		tf3=new JTextField();
		tf3.setSize(250,30);
		tf3.setLocation(200,150);
		add(tf3);

		tf3.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();

				if(Character.isDigit(ch) || ch=='\b' || ch=='-' || ch=='/')
				{
				String t=tf3.getText();
				if(t.contains("-") && ch=='/')
				{

				ke.setKeyChar('\b');
				}
				else if(t.contains("/") && ch=='-')
				{
				ke.setKeyChar('\b');
				}

				return;
				}
		ke.setKeyChar('\b');
				}
				});


		l4=new JLabel("VISIT QUERY :");
                l4.setSize(150,30)      ;
                l4.setLocation(100,210);
                add(l4);

                ta4=new JTextArea();
                ta4.setSize(250,120);
                ta4.setLocation(200,210);
                add(ta4);

	/*	l5=new JLabel("VISIT SOLUTION :");
                l5.setSize(150,30)      ;
                l5.setLocation(80,350);
                add(l5);

                ta5=new JTextArea();
                ta5.setSize(250,130);
                ta5.setLocation(200,350);
                add(ta5);
	*/
		b1=new JButton("PROVIDEMEDICINE");
		b1.addActionListener(this);
		b1.setSize(200,30);
		b1.setLocation(220,400);
		add(b1);
		


		b2=new JButton("Log_out");
		b2.addActionListener(this);
		b2.setSize(100,30);
		b2.setLocation(500,0);
		add(b2);
		

		b3=new JButton("Details");
		b3.addActionListener(this);
		b3.setSize(100,30);
		b3.setLocation(400,0);
		add(b3);
		
		pBackground=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				try
				{
					File f=new File("stethoscope-2617700_1920.jpg");
					Image img=ImageIO.read(f);
					g.drawImage(img,0,0,600,600,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"problem"+e);
				}
			}
		};


		pBackground.setSize(600,600);
		pBackground.setLocation(0,0);
		add(pBackground);



		setTitle("CHECKUP SCREEN");
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String P=(String)ae.getActionCommand();

		if(P.equals("PROVIDEMEDICINE"))
		{
			new Med_pre();
			//JOptionPane.showMessageDialog(null,"provided Successfully");
					

		}
		if(P.equals("Log_out"))
		{
			new LogIn();
		}
		if(P.equals("Details"))
		{
			new Work();
		}
	}
	public static void main(String[] args)
	{
		CheckUp ob1=new CheckUp();
	}

}

