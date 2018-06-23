package registersystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Register {

	private JFrame frame;
	private JTextField textName;
	private JTextField textMail;
	private JTextField textPassword;
	private JTextField textConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 244);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textName = new JTextField();
		textName.setBounds(165, 24, 202, 20);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(165, 55, 202, 20);
		frame.getContentPane().add(textMail);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(165, 86, 202, 20);
		frame.getContentPane().add(textPassword);
		
		textConfirmPassword = new JTextField();
		textConfirmPassword.setColumns(10);
		textConfirmPassword.setBounds(165, 117, 202, 20);
		frame.getContentPane().add(textConfirmPassword);
		
		JButton btnRegister = new JButton("Cadastrar");
		btnRegister.setBounds(167, 161, 89, 23);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(109, 27, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblMail = new JLabel("E-mail");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setBounds(109, 58, 46, 14);
		frame.getContentPane().add(lblMail);
		
		JLabel lblPassword = new JLabel("Senha");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(109, 89, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirmar senha");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setBounds(56, 120, 99, 14);
		frame.getContentPane().add(lblConfirmPassword);
	}

}
