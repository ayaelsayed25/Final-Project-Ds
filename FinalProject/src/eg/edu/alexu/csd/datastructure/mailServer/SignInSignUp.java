package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import java.awt.Canvas;
import javax.swing.JTextArea;

public class SignInSignUp {

	JFrame frmMailServer;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JTextField newNameField;
	private JTextField newEmailField;
	private JPasswordField newPasswordField;
	private JPanel panel;
	private JLabel signinPane;
	JTextArea signUpPane;
	App app = new App();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInSignUp window = new SignInSignUp();
					window.frmMailServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public SignInSignUp() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frmMailServer = new JFrame();
		frmMailServer.setResizable(false);
		frmMailServer.getContentPane().setForeground(new Color(255, 255, 255));
		frmMailServer.getContentPane().setBackground(new Color(0, 0, 0));
		frmMailServer.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign In\r\n");
		lblNewLabel.setBounds(28, 85, 153, 49);
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, ٣٥));
		lblNewLabel.setForeground(new Color(204, 0, 102));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		frmMailServer.getContentPane().add(lblNewLabel);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, ١٥));
		emailField.setBounds(169, 200, 185, 32);
		frmMailServer.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, ٢٢));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(47, 200, 62, 32);
		frmMailServer.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, ٢٢));
		lblPassword.setBounds(28, 270, 108, 32);
		frmMailServer.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 276, 185, 31);
		frmMailServer.getContentPane().add(passwordField);
		
		JButton signinBtn = new JButton("Sign in");
		signinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String password = new String(passwordField.getPassword());
				if(!app.signin(email, password))
				{
					signinPane.setText("Wrong Email or Password!");
					emailField.setText("");
					passwordField.setText("");
				}
				else
				{
					Accounts c = new Accounts(email);
					c.frmMailServer.setVisible(true);
					c.namelabel.setText(email);
					frmMailServer.setVisible(false);
					
				}
			}
		});
		signinBtn.setFont(new Font("Tahoma", Font.PLAIN, ١٤));
		signinBtn.setBackground(new Color(240, 240, 240));
		signinBtn.setBounds(169, 365, 85, 32);
		frmMailServer.getContentPane().add(signinBtn);
		
		JLabel lblNewLabel_2 = new JLabel("You don't have an account yet? Click here to sign up!\r\n");
		lblNewLabel_2.setForeground(new Color(204, 0, 102));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, ١٥));
		lblNewLabel_2.setBounds(10, 459, 367, 37);
		frmMailServer.getContentPane().add(lblNewLabel_2);
		
		JButton signupBtn = new JButton("Sign up");
		signupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		signupBtn.setFont(new Font("Tahoma", Font.PLAIN, ١٤));
		signupBtn.setBackground(SystemColor.menu);
		signupBtn.setBounds(387, 465, 85, 25);
		frmMailServer.getContentPane().add(signupBtn);
		
		panel = new JPanel();
		panel.setVisible(false);
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(471, 85, 361, 371);
		frmMailServer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, ٢٢));
		lblEmail.setBounds(0, 200, 86, 32);
		panel.add(lblEmail);
		
		JLabel lblPassword_1 = new JLabel("Password :");
		lblPassword_1.setForeground(Color.WHITE);
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, ٢٢));
		lblPassword_1.setBounds(0, 255, 126, 32);
		panel.add(lblPassword_1);
		
		newNameField = new JTextField();
		newNameField.setFont(new Font("Tahoma", Font.PLAIN, ١٥));
		newNameField.setColumns(10);
		newNameField.setBounds(146, 148, 185, 32);
		panel.add(newNameField);
		
		newEmailField = new JTextField();
		newEmailField.setFont(new Font("Tahoma", Font.PLAIN, ١٥));
		newEmailField.setColumns(10);
		newEmailField.setBounds(146, 206, 185, 32);
		panel.add(newEmailField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(146, 261, 185, 31);
		panel.add(newPasswordField);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = newNameField.getText();
				String email = newEmailField.getText();

				String password = new String(newPasswordField.getPassword());
				Contact contact = new Contact(name, email, password);
				if(!app.signup(contact)) {
					signUpPane.setText("Invalid Data:\r\n" + 
							"*Email may be used.\r\n" + 
							"*Email should not contain \"\\*/:?\"<>|\"\r\n" + 
							"*Make sure no field is blank");
			        }
				else
			        {
			        	signUpPane.setText("Signed Up successfully!");
			        }
				newNameField.setText("");
				newEmailField.setText("");
				newPasswordField.setText("");
			}
		});
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, ١٤));
		submitBtn.setBackground(SystemColor.menu);
		submitBtn.setBounds(146, 339, 85, 32);
		panel.add(submitBtn);
		
		JLabel lblName = new JLabel(" Name :");
		lblName.setBounds(0, 142, 108, 32);
		panel.add(lblName);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.BOLD, ٢٢));
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setForeground(new Color(204, 0, 102));
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, ٣٥));
		lblSignUp.setBackground(Color.BLACK);
		lblSignUp.setAlignmentY(1.0f);
		lblSignUp.setAlignmentX(1.0f);
		lblSignUp.setBounds(0, 0, 153, 49);
		panel.add(lblSignUp);
		
		signinPane = new JLabel(""); 
		signinPane.setHorizontalAlignment(SwingConstants.CENTER);
		signinPane.setForeground(new Color(255, 255, 255));
		signinPane.setFont(new Font("Tahoma", Font.PLAIN, ١٦));
		signinPane.setBounds(62, 329, 308, 26);
		frmMailServer.getContentPane().add(signinPane);
		
		signUpPane = new JTextArea();
		signUpPane.setFont(new Font("Tahoma", Font.PLAIN, ١٥));
		signUpPane.setForeground(Color.WHITE);
		signUpPane.setBackground(Color.BLACK);
		signUpPane.setText("\r\n");
		signUpPane.setBounds(539, 487, 248, 80);
		frmMailServer.getContentPane().add(signUpPane);
		frmMailServer.setTitle("Mail Server\r\n");
		frmMailServer.setBackground(new Color(0, 0, 0));
		frmMailServer.setBounds(100, 100, 846, 646);
		frmMailServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
}
