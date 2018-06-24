package registersystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import playermp3.Resource;
import playermp3gui.PlayerGUI;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frameRegistrerScreen;
	private JTextField textName;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frameRegistrerScreen.setVisible(true);
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
		frameRegistrerScreen = new JFrame();
		frameRegistrerScreen.setBounds(100, 100, 408, 244);
		frameRegistrerScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameRegistrerScreen.getContentPane().setLayout(null);
		
		textName = new JTextField();
		textName.setBounds(165, 24, 202, 20);
		frameRegistrerScreen.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(109, 27, 46, 14);
		frameRegistrerScreen.getContentPane().add(lblName);
		
		JLabel lblPassword = new JLabel("Senha");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(109, 58, 46, 14);
		frameRegistrerScreen.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirmar senha");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setBounds(59, 89, 99, 14);
		frameRegistrerScreen.getContentPane().add(lblConfirmPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(165, 55, 202, 20);
		frameRegistrerScreen.getContentPane().add(pwdPassword);
		
		pwdConfirmPassword = new JPasswordField();
		pwdConfirmPassword.setBounds(165, 86, 202, 20);
		frameRegistrerScreen.getContentPane().add(pwdConfirmPassword);
		
		JButton btnRegister = new JButton("Cadastrar");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String password = new String(pwdPassword.getPassword());
				if(!password.isEmpty() && !textName.getText().isEmpty()) {
					String confirmPassword = new String(pwdConfirmPassword.getPassword());
				
					if(!password.equals(confirmPassword )) {
						
						pwdPassword.setText(null);
						pwdConfirmPassword.setText(null);
						JOptionPane.showMessageDialog(null,"As senhas digitadas não coicindem. Porfavor, digite-as novamente", "Senhas distintas", JOptionPane.WARNING_MESSAGE);
					
					}else {
						
						Resource.writeInFile("txtFiles\\users.txt",textName.getText()+";"+password);
						JOptionPane.showMessageDialog(null,"Usuário Cadastrado!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
						PlayerGUI.main(null);
						frameRegistrerScreen.dispose();		
					}
				}else {
					JOptionPane.showMessageDialog(null,"Há campos vazios no cadastro!", "Campos vazios", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRegister.setBounds(167, 161, 89, 23);
		frameRegistrerScreen.getContentPane().add(btnRegister);
	}
}
