package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import DataStructures.LinkedList;

import java.io.File;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Email {

	 JFrame frmMailServer;
	private JTextField subjectEmail;
	private JTextField toEmail;
	JTextArea emailContent;
	String sender;
	JLabel checkTxt;
	JTextArea checkBox;
	Button sendBtn;
	Button saveBtn;
	File ChosenFile;
	LinkedList attachList = new LinkedList();
	JComboBox comboBox;
	App app = new App();


	
	public Email(String sender) {
		this.sender = sender;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMailServer = new JFrame();
		frmMailServer.setResizable(false);
		frmMailServer.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, ١٣));
		frmMailServer.setTitle("Mail Server");
		frmMailServer.getContentPane().setBackground(Color.BLACK);
		frmMailServer.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, ٢٥));
		lblNewLabel.setForeground(new Color(204, 51, 102));
		lblNewLabel.setBorder(new LineBorder(new Color(204, 51, 102)));
		lblNewLabel.setBounds(33, 31, 79, 54);
		frmMailServer.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Subject : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, ٢٠));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(139, 31, 92, 46);
		frmMailServer.getContentPane().add(lblNewLabel_1);
		
		JLabel lblSendTo = new JLabel("Send to :");
		lblSendTo.setForeground(Color.WHITE);
		lblSendTo.setFont(new Font("Tahoma", Font.PLAIN, ٢٠));
		lblSendTo.setBounds(139, 87, 92, 46);
		frmMailServer.getContentPane().add(lblSendTo);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, ٢٠));
		lblEmail.setBounds(33, 135, 92, 46);
		frmMailServer.getContentPane().add(lblEmail);
		
		subjectEmail = new JTextField();
		subjectEmail.setFont(new Font("Tahoma", Font.BOLD, ١٦));
		subjectEmail.setBounds(241, 41, 416, 30);
		frmMailServer.getContentPane().add(subjectEmail);
		subjectEmail.setColumns(10);
		
		toEmail = new JTextField();
		toEmail.setFont(new Font("Tahoma", Font.BOLD, ١٦));
		toEmail.setColumns(10);
		toEmail.setBounds(241, 98, 416, 30);
		frmMailServer.getContentPane().add(toEmail);
		
		emailContent = new JTextArea();
		emailContent.setFont(new Font("Tahoma", Font.PLAIN, ١٥));
		emailContent.setBounds(118, 156, 600, 182);
		frmMailServer.getContentPane().add(emailContent);
		
		JLabel lblAttachments = new JLabel("Attachments :");
		lblAttachments.setForeground(Color.WHITE);
		lblAttachments.setFont(new Font("Tahoma", Font.PLAIN, ٢٠));
		lblAttachments.setBounds(33, 368, 134, 46);
		frmMailServer.getContentPane().add(lblAttachments);
		
		Button attachBtn = new Button("Add an attachment.");
		attachBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				ChosenFile = chooser.getSelectedFile();
				attachList.add(ChosenFile);
				comboBox.addItem(ChosenFile);
			}
		});
		attachBtn.setForeground(new Color(255, 255, 255));
		attachBtn.setFont(new Font("Dialog", Font.BOLD, ١٤));
		attachBtn.setBackground(new Color(204, 51, 102));
		attachBtn.setBounds(195, 380, 298, 34);
		frmMailServer.getContentPane().add(attachBtn);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accounts c = new Accounts(sender);
				c.frmMailServer.setVisible(true);
				c.namelabel.setText(sender);
				frmMailServer.setVisible(false);
				
			}
		});
		backBtn.setForeground(new Color(204, 51, 102));
		backBtn.setFont(new Font("Tahoma", Font.BOLD, ١٧));
		backBtn.setBounds(722, 554, 100, 29);
		frmMailServer.getContentPane().add(backBtn);
		
		saveBtn = new Button("Save to drafts");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reciever = toEmail.getText();
				String content = emailContent.getText();
				String sub = subjectEmail.getText();
				Mail mail = new Mail(reciever, sender, sub, content);
				mail.attachments = attachList;
				if(app.compose(mail))
					{
					  checkTxt.setText("Saved Successfully!");
					  checkBox.setText("You have to wait for at least one minute\nbefore you can save another email.");
					}
				else
					{
					checkTxt.setText("Failed to save email.");
					checkBox.setText("*Make sure the email you entered exists.\r\n" + 
							"*Make sure the subject you entered is not blank\r\n" + 
							"and does not contain these characters :\"\\*/:?\"<>|\"");
					}
				toEmail.setText("");
				emailContent.setText("");
				subjectEmail.setText("");				
			}
		});
		saveBtn.setBackground(new Color(204, 51, 102));
		saveBtn.setFont(new Font("Dialog", Font.BOLD, ١٣));
		saveBtn.setBounds(533, 512, 154, 30);
		frmMailServer.getContentPane().add(saveBtn);
		
		sendBtn = new Button("Save and Send");
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reciever = toEmail.getText();
				String content = emailContent.getText();
				String sub = subjectEmail.getText();
				Mail mail = new Mail(reciever, sender, sub, content);
				mail.attachments = attachList;
				if(app.compose(mail) && app.sendEmail(mail))
				{
					checkTxt.setText("Sent Successfully!");
					checkBox.setText("You have to wait for one minute\nbefore you can send another email.");
				}
				else
					{
					checkTxt.setText("Failed to send the Email!");
					checkBox.setText("*Make sure the email you entered exists.\r\n" + 
							"*Make sure the subject you entered is not blank\r\n" + 
							"and does not contain these characters :\"\\*/:?\"<>|\"");
					}
				toEmail.setText("");
				emailContent.setText("");
				subjectEmail.setText("");				
			}	
		}
		);
		
		sendBtn.setFont(new Font("Dialog", Font.BOLD, ١٣));
		sendBtn.setBackground(new Color(204, 51, 102));
		sendBtn.setBounds(533, 554, 154, 30);
		frmMailServer.getContentPane().add(sendBtn);
		
		checkTxt = new JLabel("");
		checkTxt.setFont(new Font("Tahoma", Font.PLAIN, ١٨));
		checkTxt.setBackground(new Color(255, 255, 255));
		checkTxt.setForeground(new Color(255, 255, 255));
		checkTxt.setBounds(86, 447, 241, 30);
		frmMailServer.getContentPane().add(checkTxt);
		
		checkBox = new JTextArea();
		checkBox.setEnabled(false);
		checkBox.setText("\r\n");
		checkBox.setBackground(Color.BLACK);
		checkBox.setForeground(Color.WHITE);
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox.setBounds(86, 487, 327, 73);
		frmMailServer.getContentPane().add(checkBox);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setForeground(new Color(199, 21, 133));
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setBounds(516, 379, 289, 29);
		frmMailServer.getContentPane().add(comboBox);
		
		JButton removeAttach = new JButton("Remove selected one");
		removeAttach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = (File)comboBox.getSelectedItem();
				comboBox.removeItem(file);
				for(int i=0; i<attachList.size(); i++)
				{
					File f = (File) attachList.get(i);
					if(file.getPath().equals(f.getPath()))
					{
						attachList.remove(i);
						attachList.show();
						break;
					}
				}
			}
		});
		removeAttach.setFont(new Font("Tahoma", Font.BOLD, 12));
		removeAttach.setBounds(573, 431, 165, 30);
		frmMailServer.getContentPane().add(removeAttach);
		frmMailServer.setBounds(100, 100, 846, 646);
		frmMailServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
