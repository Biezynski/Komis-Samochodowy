import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	KomisSamochodowyDlaAdminow komisSamochodowy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	
	
	
	public Login() {
		connection = sqLiteConnection.dbConnector();
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(175, 134, 195, 81);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Has\u0142o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(175, 246, 195, 64);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Zaloguj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					String query = "select * from DaneLogowania where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());

					ResultSet rs = pst.executeQuery();
					
					int count = 0;
					while (rs.next()) {

						count = count + 1;

					}

					if (count == 1) {
						
						//?????????????????????
						String zapytanie = "select * from DaneLogowania where Username=? and Password=?";
						PreparedStatement stmt = connection.prepareStatement(zapytanie);
						//Parameters
						stmt.setString(1, textField.getText());
						stmt.setString(2, passwordField.getText());
						//Execute
						stmt.executeQuery();
						
						User user = null;
						
						ResultSet res1 = stmt.executeQuery();
						
						if(res1.next()) {
							
							String rola= res1.getString("rola");
							user = new User(textField.getText(), passwordField.getText(), rola);
							
							
							switch (user.rola) {
							case "admin":
								KomisSamochodowyDlaAdminow komisSamochodowy= new KomisSamochodowyDlaAdminow();				
								komisSamochodowy.setVisible(true);
								break;

							default:
								KomisSamochodowyDlaUzytkownika komisSamochodowyDlaUzytkownika = new KomisSamochodowyDlaUzytkownika();
								komisSamochodowyDlaUzytkownika.setVisible(true);
								break;
							}
							
							
							
							
						}
						
						
						
						
						
						JOptionPane.showMessageDialog(null, "Logowanie poprawne witamy w serwisie");
						textField.setText("");
						passwordField.setText("");
						frame.dispose();
						
					
		//				KomisSamochodowyDlaAdminow komisSamochodowy= new KomisSamochodowyDlaAdminow();				
		//				komisSamochodowy.setVisible(true);
				
						
						

					} else if (count > 1) {
						JOptionPane.showMessageDialog(null, "Login albo has�o jest zduplikowane");
						textField.setText("");
						passwordField.setText("");

					} else {
						JOptionPane.showMessageDialog(null, "Login lub has�o jest b��dne! Spr�buj ponownie!");
						textField.setText("");
						passwordField.setText("");
					}
						rs.close();
						pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnNewButton.setBounds(257, 438, 271, 70);
		frame.getContentPane().add(btnNewButton);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setBounds(337, 150, 317, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		passwordField.setBounds(337, 255, 317, 40);
		frame.getContentPane().add(passwordField);
	}
}
