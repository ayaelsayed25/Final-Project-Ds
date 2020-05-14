package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Mails {

	JFrame MailServer;
	String[] mailsString;
	String[] mails;
	JList list;
	Folder folder;
	String email;
	/**
	 * Create the application.
	 */
	public Mails(String[] mails, Folder folder) {
		this.mails = mails;
		String[] mailsString = new String[mails.length];
		for(int i=0; i<mails.length; i++)
		{
			String[] temp = mails[i].split(",");
			String s = String.join("               ", temp);
			mailsString[i] = s;
			
		}
		this.mailsString = mailsString;
		this.folder = folder;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MailServer = new JFrame();
		MailServer.getContentPane().setBackground(Color.BLACK);
		MailServer.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(822, 451);
		scrollPane.setLocation(10, 119);
		MailServer.getContentPane().add(scrollPane);
		
		
		list = new JList(mailsString);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list.getSelectedIndex();
				String email = mails[index];
				String[] temp = email.split(",");
				String path = folder.Dir.getPath() + "\\" + temp[1];
				try {
					Desktop.getDesktop().open(new File(path));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		list.setForeground(new Color(255, 255, 255));
		list.setBackground(new Color(0, 0, 0));
		scrollPane.setViewportView(list);
		list.setVisibleRowCount(mailsString.length);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JLabel lblNewLabel = new JLabel("Emails");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(204, 0, 102));
		lblNewLabel.setBounds(34, 10, 109, 46);
		MailServer.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID                    Date                    Sender                    Receiver                    Subject  ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(21, 66, 798, 43);
		MailServer.getContentPane().add(lblNewLabel_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accounts c = new Accounts(email);
				c.namelabel.setText(email);
				c.frmMailServer.setVisible(true);
				MailServer.setVisible(false);
			}
		});
		btnBack.setForeground(new Color(204, 51, 102));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnBack.setBounds(719, 29, 100, 29);
		MailServer.getContentPane().add(btnBack);
		MailServer.setResizable(false);
		MailServer.setBounds(100, 100, 846, 646);
		MailServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
