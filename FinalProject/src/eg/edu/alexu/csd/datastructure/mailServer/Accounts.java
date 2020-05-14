package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import java.awt.Choice;
import java.awt.List;
import java.awt.TextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

public class Accounts {

	JFrame frmMailServer;
	JComboBox comboBox;
	JLabel namelabel;
	String email;
	JTabbedPane tabbedPane;
	JPanel SortPanel;
	JPanel SearchPanel;
	private final ButtonGroup buttonGroupSort = new ButtonGroup();
	private final ButtonGroup buttonGroupSearch = new ButtonGroup();
	JRadioButton rdbtnSender;
	JRadioButton rdbtnReceiver;
	JRadioButton rdbtnSubject;
	JRadioButton rdbtnDate;
	JRadioButton dateRb;
	JRadioButton subjectRb;
	JRadioButton senderRb;
	JRadioButton receiverRb;
	App app = new App();
	private JTextField searchField;
	

	

	public Accounts(String email) {
		this.email = email;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmMailServer = new JFrame();
		frmMailServer.setTitle("Mail Server");
		frmMailServer.getContentPane().setBackground(new Color(0, 0, 0));
		frmMailServer.getContentPane().setForeground(new Color(0, 0, 0));
		frmMailServer.setBounds(100, 100, 846, 646);
		frmMailServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMailServer.getContentPane().setLayout(null);
		
		JButton checkBtn = new JButton("Check your Emails");
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folderChosen = (String) comboBox.getSelectedItem();
				Sort sort = new Sort(dateRb.isSelected(), senderRb.isSelected(), receiverRb.isSelected(), subjectRb.isSelected());
				Filter filter = new Filter(rdbtnDate.isSelected(), rdbtnSender.isSelected(), rdbtnReceiver.isSelected(), rdbtnSubject.isSelected(), searchField.getText());
				Folder folder = new Folder(folderChosen, email);
				app.setViewingOptions(folder, filter, sort);
				Mails mails = new Mails(app.mails, folder);
				mails.email = email;
				mails.MailServer.setVisible(true);
				frmMailServer.setVisible(false);
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.BOLD, ١٦));
		checkBtn.setBounds(438, 242, 205, 44);
		frmMailServer.getContentPane().add(checkBtn);
		
		JButton composeBtn = new JButton("Compose an Email");
		composeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Email mail = new Email(email);
				mail.frmMailServer.setVisible(true); 
				frmMailServer.setVisible(false);
			}
		});
		composeBtn.setFont(new Font("Tahoma", Font.BOLD, ١٦));
		composeBtn.setBounds(160, 242, 192, 44);
		frmMailServer.getContentPane().add(composeBtn);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, ١٧));
		
		comboBox.setForeground(new Color(204, 0, 102));
		comboBox.setBackground(new Color(0, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Inbox", "Sent", "Drafts", "Trash"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(438, 287, 205, 27);
		frmMailServer.getContentPane().add(comboBox);
		
		JButton logOutBtn = new JButton("Log out");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SignInSignUp s = new SignInSignUp();
					s.frmMailServer.setVisible(true);
					frmMailServer.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		logOutBtn.setForeground(new Color(204, 51, 102));
		logOutBtn.setFont(new Font("Tahoma", Font.BOLD, ١٧));
		logOutBtn.setBounds(696, 556, 100, 29);
		frmMailServer.getContentPane().add(logOutBtn);
		
		namelabel = new JLabel("");
		namelabel.setFont(new Font("Tahoma", Font.BOLD, ١٧));
		namelabel.setForeground(Color.WHITE);
		namelabel.setBounds(10, 31, 642, 27);
		frmMailServer.getContentPane().add(namelabel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.setBounds(381, 324, 313, 221);
		frmMailServer.getContentPane().add(tabbedPane);
		
		SortPanel = new JPanel();
		tabbedPane.addTab("Sort   ", null, SortPanel, null);
		SortPanel.setBackground(new Color(0, 0, 0));
		SortPanel.setForeground(new Color(0, 0, 0));
		SortPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sort By :");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(204, 0, 102));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 101, 37);
		SortPanel.add(lblNewLabel);
		
		dateRb = new JRadioButton("Date (Default)");
		dateRb.setSelected(true);
		buttonGroupSort.add(dateRb);
		dateRb.setForeground(new Color(255, 255, 255));
		dateRb.setFont(new Font("Tahoma", Font.BOLD, 13));
		dateRb.setBackground(new Color(0, 0, 0));
		dateRb.setBounds(6, 53, 119, 21);
		SortPanel.add(dateRb);
		
		senderRb = new JRadioButton("Sender");
		buttonGroupSort.add(senderRb);
		senderRb.setForeground(Color.WHITE);
		senderRb.setFont(new Font("Tahoma", Font.BOLD, 13));
		senderRb.setBackground(new Color(0, 0, 0));
		senderRb.setBounds(6, 77, 119, 21);
		SortPanel.add(senderRb);
		
		receiverRb = new JRadioButton("Receiver");
		buttonGroupSort.add(receiverRb);
		receiverRb.setForeground(Color.WHITE);
		receiverRb.setFont(new Font("Tahoma", Font.BOLD, 13));
		receiverRb.setBackground(new Color(0, 0, 0));
		receiverRb.setBounds(6, 100, 119, 21);
		SortPanel.add(receiverRb);
		
		subjectRb = new JRadioButton("Subject");
		buttonGroupSort.add(subjectRb);
		subjectRb.setForeground(Color.WHITE);
		subjectRb.setFont(new Font("Tahoma", Font.BOLD, 13));
		subjectRb.setBackground(new Color(0, 0, 0));
		subjectRb.setBounds(6, 123, 119, 21);
		SortPanel.add(subjectRb);
		
		SearchPanel = new JPanel();
		tabbedPane.addTab("Filter", null, SearchPanel, null);
		SearchPanel.setLayout(null);
		SearchPanel.setForeground(Color.BLACK);
		SearchPanel.setBackground(new Color(0, 0, 0));
		
		JLabel lblFilter = new JLabel("Filter :");
		lblFilter.setForeground(new Color(204, 0, 102));
		lblFilter.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFilter.setBounds(10, 10, 101, 37);
		SearchPanel.add(lblFilter);
		
		rdbtnDate = new JRadioButton("Date");
		buttonGroupSearch.add(rdbtnDate);
		rdbtnDate.setForeground(Color.WHITE);
		rdbtnDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnDate.setBackground(new Color(0, 0, 0));
		rdbtnDate.setBounds(10, 53, 119, 21);
		SearchPanel.add(rdbtnDate);
		
		rdbtnSender = new JRadioButton("Sender");
		buttonGroupSearch.add(rdbtnSender);
		rdbtnSender.setForeground(Color.WHITE);
		rdbtnSender.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnSender.setBackground(new Color(0, 0, 0));
		rdbtnSender.setBounds(10, 76, 119, 21);
		SearchPanel.add(rdbtnSender);
		
		rdbtnReceiver = new JRadioButton("Receiver");
		buttonGroupSearch.add(rdbtnReceiver);
		rdbtnReceiver.setForeground(Color.WHITE);
		rdbtnReceiver.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnReceiver.setBackground(new Color(0, 0, 0));
		rdbtnReceiver.setBounds(10, 99, 106, 21);
		SearchPanel.add(rdbtnReceiver);
		
		rdbtnSubject = new JRadioButton("Subject");
		buttonGroupSearch.add(rdbtnSubject);
		rdbtnSubject.setForeground(Color.WHITE);
		rdbtnSubject.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnSubject.setBackground(new Color(0, 0, 0));
		rdbtnSubject.setBounds(10, 122, 93, 21);
		SearchPanel.add(rdbtnSubject);
		
		JLabel lblNewLabel_1 = new JLabel("Search for:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(129, 55, 82, 16);
		SearchPanel.add(lblNewLabel_1);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Tahoma", Font.BOLD, 12));
		searchField.setBounds(125, 80, 119, 27);
		SearchPanel.add(searchField);
		searchField.setColumns(10);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
