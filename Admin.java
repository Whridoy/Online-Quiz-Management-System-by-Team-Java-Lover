package com.java.admin;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JPanel panelAddQuestion;
	private JPanel panelMenu;
	private JLabel btnAddQuestion;
	private JPanel panelUpdateQuestion;
	private JLabel btnUpdateQuestion;
	private JPanel panelDeleteQuestion;
	private JLabel btnDelteQuestion;
	private JPanel panelStudentResult;
	private JLabel btnStudentResult;
	private JPanel HomePage;
	private JPanel AddQuestionPanel;
	private JLayeredPane layeredPane;
	private JPanel UpdateQuestionPanel;
	private JPanel DeleteQuestionPanel;
	private JPanel StudentResultPanel;
	private JTextArea txtQuestion;
	private JButton btnSubmit;
	private JTextArea txtA;
	private JTextArea txtB;
	private JTextArea txtC;
	private JTextArea txtD;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Admin() {
		setLookAndFeel();
		initialize();
		connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	int quesNo = 0;
	
	private JTextField txtAns;
	private JLabel lblNewLabel;
	private JLabel lblQuestionNo;
	private JTextField txtSearch;
	private JLabel lblNewLabel_1;
	private JTextField updAns;
	private JButton btnUpdate;
	
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
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

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
		
		panelMenu = new JPanel();
		panelMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null), new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null)));
		panelMenu.setBounds(5, 4, 370, 759);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		panelAddQuestion = new JPanel();
		panelAddQuestion.setOpaque(false);
		panelAddQuestion.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelAddQuestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAddQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelAddQuestion.setOpaque(true);
				panelAddQuestion.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAddQuestion.setOpaque(false);
				panelAddQuestion.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(AddQuestionPanel);
				int ans = ++quesNo;
				lblQuestionNo.setText(String.valueOf(ans));
			}
		});
		panelAddQuestion.setBackground(new Color(0,0,0,0));
		panelAddQuestion.setBounds(0, 158, 370, 55);
		panelMenu.add(panelAddQuestion);
		panelAddQuestion.setLayout(null);
		
		btnAddQuestion = new JLabel("ADD NEW QUESTION");
		btnAddQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		btnAddQuestion.setBounds(104, 11, 256, 33);
		panelAddQuestion.add(btnAddQuestion);
		
		panelUpdateQuestion = new JPanel();
		panelUpdateQuestion.setLayout(null);
		panelUpdateQuestion.setOpaque(false);
		panelUpdateQuestion.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelUpdateQuestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelUpdateQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelUpdateQuestion.setOpaque(true);
				panelUpdateQuestion.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelUpdateQuestion.setOpaque(false);
				panelUpdateQuestion.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panelUpdateQuestion.setBackground(new Color(0,0,0,0));
		panelUpdateQuestion.setBounds(0, 224, 370, 55);
		panelMenu.add(panelUpdateQuestion);
		
		btnUpdateQuestion = new JLabel("UPDATE QUESTION");
		btnUpdateQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		btnUpdateQuestion.setBounds(104, 11, 256, 33);
		panelUpdateQuestion.add(btnUpdateQuestion);
		
		panelDeleteQuestion = new JPanel();
		panelDeleteQuestion.setLayout(null);
		panelDeleteQuestion.setOpaque(false);
		panelDeleteQuestion.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelDeleteQuestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelDeleteQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelDeleteQuestion.setOpaque(true);
				panelDeleteQuestion.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDeleteQuestion.setOpaque(false);
				panelDeleteQuestion.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panelDeleteQuestion.setBackground(new Color(0, 0, 0, 0));
		panelDeleteQuestion.setBounds(0, 290, 370, 55);
		panelMenu.add(panelDeleteQuestion);
		
		btnDelteQuestion = new JLabel("DELETE QUESTION");
		btnDelteQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		btnDelteQuestion.setBounds(104, 11, 256, 33);
		panelDeleteQuestion.add(btnDelteQuestion);
		
		panelStudentResult = new JPanel();
		panelStudentResult.setLayout(null);
		panelStudentResult.setOpaque(false);
		panelStudentResult.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelStudentResult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelStudentResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelStudentResult.setOpaque(true);
				panelStudentResult.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelStudentResult.setOpaque(false);
				panelStudentResult.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panelStudentResult.setBackground(new Color(0, 0, 0, 0));
		panelStudentResult.setBounds(0, 356, 370, 55);
		panelMenu.add(panelStudentResult);
		
		btnStudentResult = new JLabel("STUDENT'S RESULT");
		btnStudentResult.setFont(new Font("Dialog", Font.BOLD, 22));
		btnStudentResult.setBounds(104, 11, 256, 33);
		panelStudentResult.add(btnStudentResult);
		
		
		/*------------------------- Starting Panels Here ----------------------------*/
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(375, 0, 991, 768);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		/*----------------------------- Home Page Panel -----------------------------*/
		HomePage = new JPanel();
		HomePage.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		layeredPane.add(HomePage, "name_248214331803200");
		HomePage.setLayout(null);
		
		/*----------------------------- Add Question Panel -----------------------------*/
		AddQuestionPanel = new JPanel();
		AddQuestionPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		layeredPane.add(AddQuestionPanel, "name_248226911218800");
		AddQuestionPanel.setLayout(null);
		
		txtQuestion = new JTextArea();
		txtQuestion.setBounds(87, 188, 794, 82);
		AddQuestionPanel.add(txtQuestion);
		
		txtA = new JTextArea();
		txtA.setBounds(87, 345, 369, 88);
		AddQuestionPanel.add(txtA);
		
		txtB = new JTextArea();
		txtB.setBounds(512, 345, 369, 88);
		AddQuestionPanel.add(txtB);
		
		txtC = new JTextArea();
		txtC.setBounds(87, 444, 369, 88);
		AddQuestionPanel.add(txtC);
		
		txtD = new JTextArea();
		txtD.setBounds(512, 444, 369, 88);
		AddQuestionPanel.add(txtD);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String question,opA,opB,opC,opD,ans;
					
					quesNo = Integer.parseInt(lblQuestionNo.getText());
					question = txtQuestion.getText();
					opA = txtA.getText();
					opB = txtB.getText();
					opC = txtC.getText();
					opD = txtD.getText();
					ans = txtAns.getText();
					
					if(question.equals("") || opA.equals("") || opB.equals("") || opC.equals("")
							|| opD.equals("") || ans.equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill up all the field");
					}
					else {
						pst = con.prepareStatement("insert into questions(Question_ID, Question, Option_A, Option_B, Option_C, Option_D, Answer)values(?,?,?,?,?,?,?)");
						pst.setInt(1, quesNo);
						pst.setString(2, question);
						pst.setString(3, opA);
						pst.setString(4, opB);
						pst.setString(5, opC);
						pst.setString(6, opD);
						pst.setString(7, ans);

						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Question Submitted Successfully!");
					}
				}
				catch(Exception ex) {
					
				}
			}
		});
		btnSubmit.setBounds(380, 693, 233, 47);
		AddQuestionPanel.add(btnSubmit);
		
		txtAns = new JTextField();
		txtAns.setBounds(183, 580, 91, 39);
		AddQuestionPanel.add(txtAns);
		txtAns.setColumns(10);
		
		lblNewLabel = new JLabel("Question");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(87, 121, 91, 47);
		AddQuestionPanel.add(lblNewLabel);
		
		lblQuestionNo = new JLabel("");
		lblQuestionNo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblQuestionNo.setBounds(183, 121, 91, 47);
		AddQuestionPanel.add(lblQuestionNo);
		/*_________________________________________________________________________________*/
		
		/*----------------------------- Update Question Panel -----------------------------*/
		UpdateQuestionPanel = new JPanel();
		layeredPane.add(UpdateQuestionPanel, "name_271993377097300");	
		UpdateQuestionPanel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(265, 77, 91, 28);
		UpdateQuestionPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Question Number");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1.setBounds(60, 73, 179, 28);
		UpdateQuestionPanel.add(lblNewLabel_1);
		
		JTextArea updQuestion = new JTextArea();
		updQuestion.setBounds(60, 151, 830, 98);
		UpdateQuestionPanel.add(updQuestion);
		
		JTextArea updA = new JTextArea();
		updA.setBounds(60, 288, 400, 56);
		UpdateQuestionPanel.add(updA);
		
		JTextArea updB = new JTextArea();
		updB.setBounds(490, 288, 400, 56);
		UpdateQuestionPanel.add(updB);
		
		JTextArea updD = new JTextArea();
		updD.setBounds(490, 369, 400, 56);
		UpdateQuestionPanel.add(updD);
		
		JTextArea updC = new JTextArea();
		updC.setBounds(60, 369, 400, 56);
		UpdateQuestionPanel.add(updC);
		
		updAns = new JTextField();
		updAns.setColumns(10);
		updAns.setBounds(213, 486, 91, 28);
		UpdateQuestionPanel.add(updAns);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnUpdate.setBounds(418, 599, 89, 23);
		UpdateQuestionPanel.add(btnUpdate);
		/*_________________________________________________________________________________*/
		
		/*----------------------------- Delete Question Panel -----------------------------*/
		DeleteQuestionPanel = new JPanel();
		layeredPane.add(DeleteQuestionPanel, "name_272001641283300");		
		/*_________________________________________________________________________________*/
		
		/*----------------------------- Student Result Panel -----------------------------*/
		StudentResultPanel = new JPanel();
		layeredPane.add(StudentResultPanel, "name_272007106950900");
	}
}
