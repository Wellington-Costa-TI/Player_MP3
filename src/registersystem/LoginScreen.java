package registersystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import playermp3.Resource;
import playermp3gui.PlayerGUI;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LoginScreen {

	private JFrame frame;
	private JTextField textUserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
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
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 345, 257);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textUserName = new JTextField();
		textUserName.setBounds(107, 77, 141, 20);
		frame.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(50, 80, 57, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(61, 111, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		JButton btnEnter = new JButton("Entrar");
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				ArrayList <String> users = Resource.readFile("txtFiles\\users.txt");
				String password = new String(passwordField.getPassword());
				for (int i = 0; i < users.size(); i++) {
					
					String[] components = users.get(i).split(";");
					
					if(textUserName.getText().contains(components[0]) && password.contains(components[1]) ) {
						PlayerGUI.main(null);
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(null,"senha ou usuario errado", "ERRO", JOptionPane.ERROR_MESSAGE);
					}
					
				}
					
			}
			
		});
		btnEnter.setBounds(128, 151, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(107, 108, 141, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblLogin.setBounds(115, 11, 118, 41);
		frame.getContentPane().add(lblLogin);
	}
}
