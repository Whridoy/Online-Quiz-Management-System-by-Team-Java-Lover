package com.java.exam;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatDarkLaf;
import com.java.profiles.Students;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Question extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	
	private JTextArea txtQuestion;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton opA;
	private JRadioButton opB;
	private JRadioButton opC;
	private JRadioButton opD;
	private JButton btnNext;
	private JButton btnSubmit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Question frame = new Question();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Question() {
		setLookAndFeel();
		initialize();
		connect();
		//showData();
		check_Ans();
		Questions();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JLabel lblNUmber;
	
	public String number, ques, optnA, optnB, optnC, optnD;
	public String q_Id = "1";
	String answer;
	int marks = 0;
	int q = 0;
	private JLabel lblNewLabel;
	private JLabel lblMarks;
	
	
	private void showData() {
		
	}
	
	
	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");
			
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	
	private void check_Ans() {
		String ans = "";
		if(opA.isSelected()) {
			//ans = opA.getText();
			ans = "A";
		}
		else if(opB.isSelected()) {
			//ans = opB.getText();
			ans = "B";
		}
		else if(opC.isSelected()) {
			//ans = opC.getText();
			ans = "C";
		}
		else if(opD.isSelected()) {
			//ans = opD.getText();
			ans = "D";
		}
		if(ans.equals(answer)) {
			marks = marks + 1;
			String m = String.valueOf(marks);
			lblMarks.setText(m);
		}
		
		//Q number change;
		/*int Q_id1 = Integer.parseInt(q_Id);
		Q_id1 = Q_id1+1;
		q_Id = String.valueOf(Q_id1);*/
		
		//Clear Radio buttons
		buttonGroup.clearSelection();
	}
	
	private void Questions() {
		try {
			String Q_id1 = lblNUmber.getText();
			
			pst = con.prepareStatement("select * from questions where Question_ID = ?");
			pst.setString(1, Q_id1);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				/*number = rs.getString(1);
				ques = rs.getString(2);
				optnA = rs.getString(3);
				optnB = rs.getString(4);
				optnC = rs.getString(5);
				optnD = rs.getString(6);*/
				
				lblNUmber.setText(rs.getString(1));
				txtQuestion.setText(rs.getString(2));
				opA.setText(rs.getString(3));
				opB.setText(rs.getString(4));
				opC.setText(rs.getString(5));
				opD.setText(rs.getString(6));
				answer = rs.getString(7);
			}
		}
		catch(Exception ex) {
			
		}
	}
	
	private void setLookAndFeel() {
		try {
			FlatDarkLaf.setup();
			UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(0, 102, 255)));
			UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
			UIManager.put("Button.arc", 999);
			UIManager.put("Component.arc", 999);
			UIManager.put("ProgressBar.arc", 999);
			UIManager.put("TextComponent.arc", 999);
			UIManager.put("ScrollBar.width", 7);
			UIManager.put("ScrollBar.trackArc", 999);
			UIManager.put("ScrollBar.thumbArc", 999);
			UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
			UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}*/

	private void initialize() {
		setUndecorated(true);
		setDefaultLookAndFeelDecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		panelExit = new JPanel();
		panelExit.setBounds(1340, 8, 13, 13);
		panelExit.setToolTipText("Exit");
		panelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelExit.setOpaque(true);
				panelExit.setBackground(Color.decode("#fc5744"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelExit.setOpaque(false);
				panelExit.setBackground(new Color(0, 0, 0, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to close?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		panelExit.setBorder(null);
		panelExit.setOpaque(false);
		contentPane.add(panelExit);
		panelExit.setLayout(null);

		lblExit = new JLabel("x");
		lblExit.setBorder(null);
		lblExit.setFont(new Font("Corbel", Font.BOLD, 15));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(0, 1, 13, 13);
		panelExit.add(lblExit);

		panelMinimize = new JPanel();
		panelMinimize.setBounds(1300, 8, 16, 14);
		panelMinimize.setToolTipText("Minimize");
		panelMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelMinimize.setOpaque(true);
				panelMinimize.setBackground(Color.decode("#fc5744"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelMinimize.setOpaque(false);
				panelMinimize.setBackground(new Color(0, 0, 0, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		panelMinimize.setLayout(null);
		panelMinimize.setOpaque(false);
		panelMinimize.setBorder(null);
		contentPane.add(panelMinimize);

		lblMinimize = new JLabel("-");
		lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimize.setFont(new Font("Colonna MT", Font.BOLD, 30));
		lblMinimize.setBorder(null);
		lblMinimize.setBounds(0, 0, 14, 13);
		panelMinimize.add(lblMinimize);
		
		txtQuestion = new JTextArea();
		txtQuestion.setOpaque(false);
		txtQuestion.setEditable(false);
		txtQuestion.setFont(new Font("Dialog", Font.BOLD, 20));
		txtQuestion.setBounds(368, 119, 629, 72);
		contentPane.add(txtQuestion);
		
		opA = new JRadioButton("New radio button");
		buttonGroup.add(opA);
		opA.setFont(new Font("Dialog", Font.PLAIN, 22));
		opA.setBounds(368, 239, 300, 54);
		contentPane.add(opA);
		
		opB = new JRadioButton("New radio button");
		buttonGroup.add(opB);
		opB.setFont(new Font("Dialog", Font.PLAIN, 22));
		opB.setBounds(697, 239, 300, 54);
		contentPane.add(opB);
		
		opC = new JRadioButton("New radio button");
		buttonGroup.add(opC);
		opC.setFont(new Font("Dialog", Font.PLAIN, 22));
		opC.setBounds(368, 322, 300, 54);
		contentPane.add(opC);
		
		opD = new JRadioButton("New radio button");
		buttonGroup.add(opD);
		opD.setFont(new Font("Dialog", Font.PLAIN, 22));
		opD.setBounds(697, 322, 300, 54);
		contentPane.add(opD);
		
		btnNext = new JButton("NEXT");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNUmber.setText(String.valueOf(++q));
				check_Ans();
				Questions();
			}
		});
		btnNext.setBounds(438, 461, 89, 23);
		contentPane.add(btnNext);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					check_Ans();
					String name,id,inst,dep;
					int number;
					name = com.java.account.Login.name1;
					id = com.java.account.Login.id1;
					inst = com.java.account.Login.institute1;
					dep = com.java.account.Login.department1;
					number = Integer.parseInt(String.valueOf(marks));
					
					pst = con.prepareStatement("insert into result(Student_Name, ID, Institution, Department, Marks)values(?,?,?,?,?)");
					
					pst.setString(1, name);
					pst.setString(2, id);
					pst.setString(3, inst);
					pst.setString(4, dep);
					pst.setInt(5, number);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Quiz Finished Successfully!");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnSubmit.setBounds(823, 461, 89, 23);
		contentPane.add(btnSubmit);
		
		lblNUmber = new JLabel("");
		lblNUmber.setText(String.valueOf(++q));
		lblNUmber.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNUmber.setBounds(368, 45, 89, 54);
		contentPane.add(lblNUmber);
		
		lblNewLabel = new JLabel("MARKS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(56, 698, 83, 38);
		contentPane.add(lblNewLabel);
		
		lblMarks = new JLabel("");
		lblMarks.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMarks.setBounds(149, 698, 83, 38);
		contentPane.add(lblMarks);
	}
}