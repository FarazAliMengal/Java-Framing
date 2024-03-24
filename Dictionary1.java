import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Dictionary1 implements ActionListener
{
	JFrame win;
	JTextField txt1,txt2,txt3,txt4;
	JButton btn1, btn2,btn3;

	JLabel lbl1,lbl2,lbl3,lbl4,lbl5;
	ImageIcon img,icon1;
	Panel panel;
	Image imgone,scaledimg;

	String url=null;
	String username=null;
	String password=null;

	Connection conn=null;
	Statement st=null;

	Dictionary1()
	{
		win = new JFrame();
		win.setLayout(null);
		win.getContentPane().setBackground(Color.white);

		Font font1 = new Font("Brush Script MT",Font.ITALIC,40);
		Font font2 = new Font("Arial",Font.BOLD,20);

		lbl2 = new JLabel("English to Englisg-Dictionary");
		lbl2.setBounds(0,10,1920,120);
		lbl2.setBackground(Color.gray);
		lbl2.setHorizontalAlignment(txt1.CENTER);
		lbl2.setFont(font1);
		win.add(lbl2);

		txt1 = new JTextField();
		txt1.setBounds(0,130,1920,10);
		txt1.setBackground(Color.gray);
		txt1.setHorizontalAlignment(txt1.CENTER);
		txt1.setFont(font1);
		win.add(txt1);

		panel = new Panel();
		panel.setBounds(160,180,800,800);
		panel.setBackground(Color.white);
		win.add(panel);

		img = new ImageIcon("D://dic.jpg");
		lbl1 = new JLabel("",img, JLabel.CENTER);
		lbl1.setBounds(-70,-50,1580,870);
		panel.add(lbl1);


		icon1 = new ImageIcon("D://data.png");
        imgone = icon1.getImage();
        scaledimg = imgone.getScaledInstance(150,90,Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(scaledimg);
        lbl2.setIcon(icon1);

        lbl3 = new JLabel("Search Word");
        lbl3.setBounds(1110,220,200,50);
        lbl3.setFont(font1);
        win.add(lbl3);

        txt2 = new JTextField();
        txt2.setBounds(1300,220,500,50);
        txt2.setFont(font1);
        win.add(txt2);


        btn1 = new JButton("search");
        btn1.setBounds(1650,300,150,50);//set button Location
        btn1.setBackground(new Color(192,192,192));//set background color
       /// btn4.setForeground(Color.BLACK); // Set text color
      	btn1.setBorder(BorderFactory.createLineBorder(Color.black));//set Border Color
        btn1.setFont(font2);
        win.add(btn1);

        icon1 = new ImageIcon("D://search.png");
        imgone = icon1.getImage();
        scaledimg = imgone.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(scaledimg);
        btn1.setIcon(icon1);



        btn2 = new JButton("Save");
        btn2.setBounds(1110,760,150,50);//set button Location
        btn2.setBackground(new Color(192,192,192));//set background color
       /// btn4.setForeground(Color.BLACK); // Set text color
      	btn2.setBorder(BorderFactory.createLineBorder(Color.black));//set Border Color
        btn2.setFont(font2);
        win.add(btn2);

        icon1 = new ImageIcon("D://add.png");
        imgone = icon1.getImage();
        scaledimg = imgone.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(scaledimg);
        btn2.setIcon(icon1);



        btn3 = new JButton("reset");
        btn3.setBounds(1650,760,150,50);//set button Location
        btn3.setBackground(new Color(192,192,192));//set background color
       /// btn4.setForeground(Color.BLACK); // Set text color
      	btn3.setBorder(BorderFactory.createLineBorder(Color.black));//set Border Color
        btn3.setFont(font2);
        win.add(btn3);

        icon1 = new ImageIcon("D://reset.png");
        imgone = icon1.getImage();
        scaledimg = imgone.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(scaledimg);
        btn3.setIcon(icon1);

        txt3 = new JTextField();
        txt3.setBounds(1110,400,700,300);
       // txt3.setEditable(false);
        txt3.setBorder(BorderFactory.createLineBorder(Color.gray));//set Border Color
        txt3.setHorizontalAlignment(txt3.CENTER);
        txt3.setFont(font1);
        win.add(txt3);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);

		win.setExtendedState(JFrame.MAXIMIZED_BOTH);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}

	public void actionPerformed(ActionEvent ob)
	{
		if(btn1 == ob.getSource())
		{

				String word = txt2.getText();

				try 
				{
					url = "jdbc:mysql://localhost:3306/dic";
					username = "root";  
					password = "";

					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(url,username,password);
					st = conn.createStatement();


					String sql = "Select * From words where word = '"+word+"'";
					//st.executeUpdate(sql);

					ResultSet r = st.executeQuery(sql);

					while(r.next())
						{
							//String word1 = r.getString(1);
							String meaning1 = r.getString(2);

							txt3.setText(""+meaning1);
							
						}
						JOptionPane.showMessageDialog(null,"SuccessFully Done");
			}		
			catch(Exception e)
						{
							System.out.print("");
						}	
		}

		if(btn2 == ob.getSource())
		{

				String words = txt2.getText();
				String meaning1 = txt3.getText();
				try 
				{
					url = "jdbc:mysql://localhost:3306/dic";
					username = "root";
					password = "";

					Class.forName("com.mysql.cj.jdbc.Driver");

					conn = DriverManager.getConnection(url,username,password);
					st = conn.createStatement();

					String sql = "insert into words (word,meaning) Values ('"+words+"','"+meaning1+"')";
					st.executeUpdate(sql);

					JOptionPane.showMessageDialog(null,"SuccessFully Done");

				}

				catch(Exception e)
				{
					System.out.print(e);
				}
			
		}

		if(btn3 == ob.getSource())
		{
			txt2.setText("");
			txt3.setText("");
		}
	}

	public static void main(String[] args) {
		 new Dictionary1();
	}
}
