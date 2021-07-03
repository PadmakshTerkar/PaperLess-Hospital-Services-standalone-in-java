import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

public class Med_pre extends JFrame implements ActionListener
{
	JPanel p1;
	JTable jt;
	JLabel l3;
	JTextField total_tf,qty,Schedule;
	JComboBox med_name;
	JTextField tf3;
	JButton b1;
	JPanel pBackground;
	JTextArea recpt;  //new JTextArea(5, 20);
	JScrollPane sp;  // new JScrollPane(textArea);
	JButton nxt,rst;
	int i=0;
	Med_pre()
	{
		try
		{
			setLayout(null);
		
			total_tf=new JTextField();
			total_tf.setSize(100,40);
			total_tf.setLocation(10,10);
			add(total_tf);
			total_tf.addKeyListener(new KeyListener(){
				    public void keyPressed(KeyEvent e) {} 
				    public void keyReleased(KeyEvent e) {
					if(e.getKeyCode()==8)
						total_tf.setText("0");
					
						int nopt=Integer.parseInt(total_tf.getText());
						disp_table(nopt);				
					}
					
				    public void keyTyped(KeyEvent e) {}  							
			});

			p1=new JPanel();
			p1.setBorder(BorderFactory.createTitledBorder("Medicine"));
			p1.setSize(470,300);
			p1.setLocation(10,150);
			p1.setLayout(new GridLayout(1,2));
			add(p1);

			String dte[]={"Crochin","Benedrill","Totos"};
			med_name=new JComboBox(dte);
			med_name.setSize(100,40);
			med_name.setLocation(10,100);
			add(med_name);

			qty=new JTextField();
			qty.setSize(100,40);
			qty.setLocation(120,100);
			add(qty);


			Schedule=new JTextField();	
			Schedule.setSize(100,40);
			Schedule.setLocation(240,100);
			add(Schedule);

			nxt=new JButton("NXT");
			nxt.setSize(70,40);
			nxt.setLocation(360,100);
			nxt.addActionListener(this);
			add(nxt);
			
			/*l3=new JLabel("Dr.");
	                l3.setSize(150,30)      ;
	                l3.setLocation(200,470);
	                add(l3);

			tf3=new JTextField();
			tf3.setSize(150,30);
			tf3.setLocation(220,470);
			add(tf3);
			*/
			b1=new JButton("SEND");
	                b1.addActionListener(this);
			b1.setSize(100,30);
	                b1.setLocation(200,500);
	                add(b1);
		


			/*rst=new JButton("RST");
			rst.setSize(100,30);
			rst.setLocation(10,450);
			rst.addActionListener(this);
			add(rst);*/
			
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

		setSize(500,600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setVisible(true);
		}
		catch(Exception e)
                {
                        JOptionPane.showMessageDialog(null,"message:"+e.getMessage());
                }		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String cmd=(String)ae.getActionCommand();
		if(cmd=="NXT")
		{
			jt.setValueAt(Integer.toString(i+1),i,0);
			jt.setValueAt(med_name.getSelectedItem(),i,1);
			jt.setValueAt(qty.getText(),i,2);
			jt.setValueAt(Schedule.getText(),i,3);
			i++;
		}
		/*if(cmd=="NXT")
		{
			i=0;
		}*/
		if(cmd=="SEND")
		{
			new CheckUp();
		}
	}	
	public void disp_table(int notp)
	{
		if(notp>0)
		{		
			p1.removeAll();
			String data[][]=new String[notp][4];
			for(int i=0;i<notp;i++)
				for(int j=0;j<4;j++)
					data[i][j]="";
			String column[]={"id","Medicine","Quantity","Schedule"};
			jt=new JTable(data,column);    
        		jt.setBounds(0,0,200,300);
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			for(int i=0;i<notp;i++)
			jt.setRowHeight(i,30);
		
			jt.getColumnModel().getColumn(0).setPreferredWidth(50);
			jt.getColumnModel().getColumn(1).setPreferredWidth(250);
			jt.getColumnModel().getColumn(2).setPreferredWidth(60);
			jt.getColumnModel().getColumn(3).setPreferredWidth(90);			

			sp=new JScrollPane(jt);
			sp.setSize(470,300);			
			p1.add(sp);

			repaint();
			revalidate();
		}
	}
	public static void main(String args[])
	{
		new Med_pre();
	}
}
