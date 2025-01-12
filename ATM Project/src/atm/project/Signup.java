package atm.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class Signup extends JFrame implements ActionListener {

	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15;
	JTextField t1, t2, t3, t4, t5, t6, t7;
	JRadioButton /* male */ r1, /* female */ r2, /* other */ r6, /* married */ r3, /* unmarried */ r4, /* other */ r5;
	JButton b, b2;
	JDateChooser dateChooser;

	Random ran = new Random();
	long first4 = (ran.nextLong() % 9000L) + 1000L;
	String first = "" + Math.abs(first4);

	Signup() {

		setTitle("NEW ACCOUNT APPLICATION FORM");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		/*
		 * JLabel l11 = new JLabel(i3); l11.setBounds(20, 0, 100, 100); add(l11);
		 */

		JLabel jl11 = new JLabel(i3);
		jl11.setBounds(20, 0, 100, 100);
		add(jl11);

		l1 = new JLabel("APPLICATION FORM NO. " + first);
		l1.setFont(new Font("Raleway", Font.BOLD, 38));

		l2 = new JLabel("Page 1: Personal Details");
		l2.setFont(new Font("Raleway", Font.BOLD, 22));

		l3 = new JLabel("Name:");
		l3.setFont(new Font("Raleway", Font.BOLD, 20));

		l4 = new JLabel("Father's Name:");
		l4.setFont(new Font("Raleway", Font.BOLD, 20));

		l5 = new JLabel("Date of Birth:");
		l5.setFont(new Font("Raleway", Font.BOLD, 20));

		l6 = new JLabel("Gender:");
		l6.setFont(new Font("Raleway", Font.BOLD, 20));

		l7 = new JLabel("Email Address:");
		l7.setFont(new Font("Raleway", Font.BOLD, 20));

		l8 = new JLabel("Marital Status:");
		l8.setFont(new Font("Raleway", Font.BOLD, 20));

		l9 = new JLabel("Address:");
		l9.setFont(new Font("Raleway", Font.BOLD, 20));

		l10 = new JLabel("City:");
		l10.setFont(new Font("Raleway", Font.BOLD, 20));

		l11 = new JLabel("Pin Code:");
		l11.setFont(new Font("Raleway", Font.BOLD, 20));

		l12 = new JLabel("State:");
		l12.setFont(new Font("Raleway", Font.BOLD, 20));

		l13 = new JLabel("Date");
		l13.setFont(new Font("Raleway", Font.BOLD, 14));

		l14 = new JLabel("Month");
		l14.setFont(new Font("Raleway", Font.BOLD, 14));

		l15 = new JLabel("Year");
		l15.setFont(new Font("Raleway", Font.BOLD, 14));

		t1 = new JTextField();
		t1.setFont(new Font("Raleway", Font.BOLD, 14));

		t2 = new JTextField();
		t2.setFont(new Font("Raleway", Font.BOLD, 14));

		t3 = new JTextField();
		t3.setFont(new Font("Raleway", Font.BOLD, 14));

		t4 = new JTextField();
		t4.setFont(new Font("Raleway", Font.BOLD, 14));

		t5 = new JTextField();
		t5.setFont(new Font("Raleway", Font.BOLD, 14));

		t6 = new JTextField();
		t6.setFont(new Font("Raleway", Font.BOLD, 14));

		t7 = new JTextField();
		t7.setFont(new Font("Raleway", Font.BOLD, 14));

		b = new JButton("Next");
		b.setFont(new Font("Raleway", Font.BOLD, 14));
		// b.setBackground(Color.BLACK);
		// b.setForeground(Color.WHITE);

		b2 = new JButton("Back");
		b2.setFont(new Font("Raleway", Font.BOLD, 14));

		r1 = new JRadioButton("Male");
		r1.setFont(new Font("Raleway", Font.BOLD, 14));
		r1.setBackground(Color.WHITE);

		r2 = new JRadioButton("Female");
		r2.setFont(new Font("Raleway", Font.BOLD, 14));
		r2.setBackground(Color.WHITE);

		r6 = new JRadioButton("Other");
		r6.setFont(new Font("Raleway", Font.BOLD, 14));
		r6.setBackground(Color.WHITE);

		ButtonGroup groupgender = new ButtonGroup();
		groupgender.add(r1);
		groupgender.add(r2);
		groupgender.add(r6);

		r3 = new JRadioButton("Married");
		r3.setFont(new Font("Raleway", Font.BOLD, 14));
		r3.setBackground(Color.WHITE);

		r4 = new JRadioButton("Unmarried");
		r4.setFont(new Font("Raleway", Font.BOLD, 14));
		r4.setBackground(Color.WHITE);

		r5 = new JRadioButton("Other");
		r5.setFont(new Font("Raleway", Font.BOLD, 14));
		r5.setBackground(Color.WHITE);

		ButtonGroup groupstatus = new ButtonGroup();
		groupstatus.add(r3);
		groupstatus.add(r4);
		groupstatus.add(r5);

		dateChooser = new JDateChooser();
		// dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		dateChooser.setForeground(new Color(105, 105, 105));
		dateChooser.setBounds(137, 337, 200, 29);
		add(dateChooser);

		setLayout(null);
		l1.setBounds(140, 20, 600, 40);
		add(l1);

		l2.setBounds(290, 80, 600, 30);
		add(l2);

		l3.setBounds(100, 140, 100, 30);
		add(l3);

		t1.setBounds(300, 140, 400, 30);
		add(t1);

		l4.setBounds(100, 190, 200, 30);
		add(l4);

		t2.setBounds(300, 190, 400, 30);
		add(t2);

		l5.setBounds(100, 240, 200, 30);
		add(l5);

		dateChooser.setBounds(300, 240, 400, 30);

		l6.setBounds(100, 290, 200, 30);
		add(l6);

		r1.setBounds(300, 290, 60, 30);
		add(r1);

		r2.setBounds(450, 290, 90, 30);
		add(r2);

		r6.setBounds(635, 290, 120, 30);
		add(r6);

		l7.setBounds(100, 340, 200, 30);
		add(l7);

		t3.setBounds(300, 340, 400, 30);
		add(t3);

		l8.setBounds(100, 390, 200, 30);
		add(l8);

		r3.setBounds(300, 390, 100, 30);
		add(r3);

		r4.setBounds(450, 390, 100, 30);
		add(r4);

		r5.setBounds(635, 390, 100, 30);
		add(r5);

		l9.setBounds(100, 440, 200, 30);
		add(l9);

		t4.setBounds(300, 440, 400, 30);
		add(t4);

		l10.setBounds(100, 490, 200, 30);
		add(l10);

		t5.setBounds(300, 490, 400, 30);
		add(t5);

		l11.setBounds(100, 540, 200, 30);
		add(l11);

		t6.setBounds(300, 540, 400, 30);
		add(t6);

		l12.setBounds(100, 590, 200, 30);
		add(l12);

		t7.setBounds(300, 590, 400, 30);
		add(t7);

		b.setBounds(620, 640, 80, 30);
		add(b);
		b.addActionListener(this);

		b2.setBounds(520, 640, 80, 30);
		add(b2);
//     b2.addActionListener(this);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new Login().setVisible(true);

			}
		});

		getContentPane().setBackground(Color.WHITE);

		setSize(850, 720);
		setLocation(340, 35);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

		String formno = first;
		String name = t1.getText();
		String father_name = t2.getText();
		String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String gender = null;
		if (r1.isSelected()) {
			gender = "Male";
		} else if (r2.isSelected()) {
			gender = "Female";
		} else if (r6.isSelected()) {
			gender = "Other";
		}

		String email = t3.getText();
		String marital_status = null;
		if (r3.isSelected()) {
			marital_status = "Married";
		} else if (r4.isSelected()) {
			marital_status = "Unmarried";
		} else if (r5.isSelected()) {
			marital_status = "Other";
		}

		String address = t4.getText();
		String city = t5.getText();
		String pincode = t6.getText();
		String state = t7.getText();

		try {

			if (t6.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");
			} else {
				Conn c1 = new Conn();
				String q1 = "insert into signup values('" + formno + "','" + name + "','" + father_name + "','" + dob
						+ "','" + gender + "','" + email + "','" + marital_status + "','" + address + "','" + city
						+ "','" + pincode + "','" + state + "')";
				c1.s.executeUpdate(q1);

				new Signup2(first).setVisible(true);
				setVisible(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Signup().setVisible(true);
	}
}
