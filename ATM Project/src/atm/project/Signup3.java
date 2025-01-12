package atm.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Signup3 extends JFrame implements ActionListener {

	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
	JRadioButton r1, r2, r3, r4;
	JButton b1, b2, b3;
	JCheckBox c1, c2, c3, c4, c5, c6, c7;
	String formno;

	Signup3(String formno) {
		this.formno = formno;
		setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l14 = new JLabel(i3);
		l14.setBounds(150, 0, 100, 100);
		add(l14);

		l11 = new JLabel("Form No:");
		l11.setFont(new Font("Raleway", Font.BOLD, 14));

		l12 = new JLabel(formno);
		l12.setFont(new Font("Raleway", Font.BOLD, 14));

		//
		l1 = new JLabel("Page 3: Account Details");
		l1.setFont(new Font("Raleway", Font.BOLD, 22));

		//
		l2 = new JLabel("Account Type:");
		l2.setFont(new Font("Raleway", Font.BOLD, 18));

		r1 = new JRadioButton("Saving Account");
		r1.setFont(new Font("Raleway", Font.PLAIN, 16));
		r1.setBackground(Color.WHITE);

		r2 = new JRadioButton("Fixed Deposit Account");
		r2.setFont(new Font("Raleway", Font.PLAIN, 16));
		r2.setBackground(Color.WHITE);

		r3 = new JRadioButton("Current Account");
		r3.setFont(new Font("Raleway", Font.PLAIN, 16));
		r3.setBackground(Color.WHITE);

		r4 = new JRadioButton("Recurring Deposit Account");
		r4.setFont(new Font("Raleway", Font.PLAIN, 16));
		r4.setBackground(Color.WHITE);

		//
		l3 = new JLabel("Card Number:");
		l3.setFont(new Font("Raleway", Font.BOLD, 18));

		l4 = new JLabel("XXXX-XXXX-XXXX-0000");
		l4.setFont(new Font("Raleway", Font.BOLD, 18));

		l5 = new JLabel("(Your 16-digit Card number)");
		l5.setFont(new Font("Raleway", Font.PLAIN, 12));

		l6 = new JLabel("*It will appear on ATM Card/Cheque Book and Statements*");
		l6.setFont(new Font("Raleway", Font.PLAIN, 12));

		l7 = new JLabel("PIN:");
		l7.setFont(new Font("Raleway", Font.BOLD, 18));

		l8 = new JLabel("XXXX");
		l8.setFont(new Font("Raleway", Font.BOLD, 18));

		l9 = new JLabel("(4-digit password)");
		l9.setFont(new Font("Raleway", Font.PLAIN, 12));

		//
		l10 = new JLabel("Services Required:");
		l10.setFont(new Font("Raleway", Font.BOLD, 18));

		c1 = new JCheckBox("ATM CARD");
		c1.setBackground(Color.WHITE);
		c1.setFont(new Font("Raleway", Font.PLAIN, 15));

		c2 = new JCheckBox("Internet Banking");
		c2.setBackground(Color.WHITE);
		c2.setFont(new Font("Raleway", Font.PLAIN, 15));

		c3 = new JCheckBox("Mobile Banking");
		c3.setBackground(Color.WHITE);
		c3.setFont(new Font("Raleway", Font.PLAIN, 15));

		c4 = new JCheckBox("EMAIL Alerts");
		c4.setBackground(Color.WHITE);
		c4.setFont(new Font("Raleway", Font.PLAIN, 15));

		c5 = new JCheckBox("Cheque Book");
		c5.setBackground(Color.WHITE);
		c5.setFont(new Font("Raleway", Font.PLAIN, 15));

		c6 = new JCheckBox("E-Statement");
		c6.setBackground(Color.WHITE);
		c6.setFont(new Font("Raleway", Font.PLAIN, 15));

		// acknowledgment
		// c7 = new JCheckBox("I hereby declares that the above entered details correct
		// to the best of my knowledge.",true);
		c7 = new JCheckBox("I hereby declares that the above entered details correct.", true);
		c7.setBackground(Color.WHITE);
		c7.setFont(new Font("Raleway", Font.BOLD, 12));

		//
		b1 = new JButton("Submit");
		b1.setFont(new Font("Raleway", Font.BOLD, 14));
		// b1.setBackground(Color.BLACK);
		// b1.setForeground(Color.WHITE);

		b2 = new JButton("Cancel");
		b2.setFont(new Font("Raleway", Font.BOLD, 14));
		// b2.setBackground(Color.BLACK);
		// b2.setForeground(Color.WHITE);

		b3 = new JButton("Back");
		b3.setFont(new Font("Raleway", Font.BOLD, 14));

		// grouping gender for single radio btn selection
		ButtonGroup groupgender = new ButtonGroup();
		groupgender.add(r1);
		groupgender.add(r2);
		groupgender.add(r3);
		groupgender.add(r4);

		setLayout(null);

		// dont edit...
		// top right Form No display
		l11.setBounds(700, 10, 70, 30);
		add(l11);

		// formno display
		// l12.setBounds(770,10,40,30);
		add(l12);

		l12.setBounds(770, 10, 40, 30);
		add(l12);
		// till here...

		// title Page 3......(dont edit here)
		l1.setBounds(280, 40, 400, 40);
		add(l1);

		// account type
		l2.setBounds(100, 115, 200, 30);
		add(l2);

		// radio btn saving acc
		r1.setBounds(200, 150, 150, 30);
		add(r1);

		// radio btn fixed dep acc
		r2.setBounds(500, 150, 300, 30);
		add(r2);

		// radio btn current acc
		r3.setBounds(200, 185, 250, 30);
		add(r3);

		// radio btn recurring dep acc
		r4.setBounds(500, 185, 250, 30);
		add(r4);

		// card no
		l3.setBounds(100, 230, 200, 30);
		add(l3);

		// card no xxxx
		l4.setBounds(330, 230, 250, 30);
		add(l4);

		// 16digit card no txt
		l5.setBounds(100, 265, 200, 15);
		add(l5);

		// it will appear... txt
		l6.setBounds(330, 265, 500, 15);
		add(l6);

		// pin
		l7.setBounds(100, 300, 200, 30);
		add(l7);

		// pin xxxx
		l8.setBounds(330, 300, 200, 30);
		add(l8);

		// 4 digit psk txt
		l9.setBounds(100, 330, 200, 15);
		add(l9);

		// service req txt
		l10.setBounds(100, 360, 200, 30);
		add(l10);

		/// /// /// ///

		// checkbox atm card
		c1.setBounds(200, 400, 200, 30);
		add(c1);

		// checkbox internet banking
		c2.setBounds(500, 400, 200, 30);
		add(c2);

		// checkbox mobile banking
		c3.setBounds(200, 450, 200, 30);
		add(c3);

		// checkbox email alerts
		c4.setBounds(500, 450, 200, 30);
		add(c4);

		// checkbox cheque book
		c5.setBounds(200, 500, 200, 30);
		add(c5);

		//// checkbox e statement
		c6.setBounds(500, 500, 200, 30);
		add(c6);

		/// /// /// ///

		/*
		 * //checkbox atm card c1.setBounds(100,400,200,30); add(c1);
		 * 
		 * //checkbox internet banking c2.setBounds(350,400,200,30); add(c2);
		 * 
		 * //checkbox mobile banking c3.setBounds(100,450,200,30); add(c3);
		 * 
		 * //checkbox email alerts c4.setBounds(350,450,200,30); add(c4);
		 * 
		 * //checkbox cheque book c5.setBounds(100,500,200,30); add(c5);
		 * 
		 * ////checkbox e statement c6.setBounds(350,500,200,30); add(c6);
		 */

		// this is acknowledgment location
		/*
		 * c7.setBounds(100,680,600,20); add(c7);
		 */

		// checkbox acknowledgment
		c7.setBounds(215, 590, 600, 20);
		add(c7);

		// this is button location
		/*
		 * b1.setBounds(250,720,100,30); add(b1);
		 * 
		 * b2.setBounds(420,720,100,30); add(b2);
		 */

		// submit btn
		b1.setBounds(250, 625, 100, 30);
		add(b1);

		// cancel btn
		b2.setBounds(420, 625, 100, 30);
		add(b2);

		b3.setBounds(30, 625, 100, 30);
		add(b3);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new Signup2("").setVisible(true);
			}
		});

		getContentPane().setBackground(Color.WHITE);

		setSize(850, 720);
		setLocation(340, 35);
		setVisible(true);

		b1.addActionListener(this);
		b2.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae) {
		String accountType = null;
		if (r1.isSelected()) {
			accountType = "Saving Account";
		} else if (r2.isSelected()) {
			accountType = "Fixed Deposit Account";
		} else if (r3.isSelected()) {
			accountType = "Current Account";
		} else if (r4.isSelected()) {
			accountType = "Recurring Deposit Account";
		}

		Random ran = new Random();
		long first7 = (ran.nextLong() % 90000000L) + 5040936000000000L;
		String cardnumber = "" + Math.abs(first7);

		long first3 = (ran.nextLong() % 9000L) + 1000L;
		String pin = "" + Math.abs(first3);

		String facility = "";
		if (c1.isSelected()) {
			facility = facility + " ATM Card,";
		}
		if (c2.isSelected()) {
			facility = facility + " Internet Banking,";
		}
		if (c3.isSelected()) {
			facility = facility + " Mobile Banking,";
		}
		if (c4.isSelected()) {
			facility = facility + " EMAIL Alerts,";
		}
		if (c5.isSelected()) {
			facility = facility + " Cheque Book,";
		}
		if (c6.isSelected()) {
			facility = facility + " E-Statement,";
		}

		try {
			if (ae.getSource() == b1) {

				if (accountType.equals("")) {
					JOptionPane.showMessageDialog(null, "Fill all the required fields");
				} else {
					Conn c1 = new Conn();
					// String q1 = "insert into signup3
					// values('"+formno+"','"+accountType+"','"+cardno+"','"+pin+"','"+facility+"')";
					String q1 = "insert into signupthree values('" + formno + "','" + accountType + "','" + cardnumber
							+ "','" + pin + "','" + facility + "')";
					String q2 = "insert into login values('" + formno + "','" + cardnumber + "','" + pin + "')";
					c1.s.executeUpdate(q1);
					c1.s.executeUpdate(q2);
					JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\n Pin:" + pin);

					new Deposit(pin).setVisible(true);
					setVisible(false);
				}

			} else if (ae.getSource() == b2) {
				System.exit(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Signup3("").setVisible(true);
	}

}
