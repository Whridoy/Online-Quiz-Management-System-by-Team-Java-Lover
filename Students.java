package com.java.profiles;

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
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.FlatDarkLaf;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Students extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JPanel panelMenu;
	private JButton btnStartExa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Students frame = new Students();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Students() {
		setLookAndFeel();
		initialize();
		connect();
		//loadResult();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnResult;
	private JTable table;
	private JScrollPane scrollPane;
	
	
	
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
	
	private void loadResult() {
		try {
			String id = com.java.account.Login.id1;
			pst = con.prepareStatement("select * from result where ID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
			    column = table.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(360);
			    }
			    if (i == 1) {
			        column.setPreferredWidth(150);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(320);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(150);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(100);
			    }
			}
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			table.setRowHeight(30);
		} 
		catch (Exception ex) {

		}
	}
	
	private void initialize() {
		setUndecorated(true);
		setDefaultLookAndFeelDecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
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
		contentPane.setLayout(null);
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
		panelMenu.setBounds(5, 4, 370, 759);
		panelMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null), new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null)));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		btnStartExa = new JButton("START QUIZ");
		btnStartExa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.java.exam.Question q = new com.java.exam.Question();
				q.setVisible(true);
			}
		});
		btnStartExa.setFont(new Font("Dialog", Font.BOLD, 24));
		btnStartExa.setBounds(10, 357, 350, 45);
		panelMenu.add(btnStartExa);
		
		btnResult = new JButton("VIEW RESULT");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadResult();
			}
		});
		btnResult.setFont(new Font("Dialog", Font.BOLD, 24));
		btnResult.setBounds(10, 442, 350, 45);
		panelMenu.add(btnResult);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(491, 188, 787, 392);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
