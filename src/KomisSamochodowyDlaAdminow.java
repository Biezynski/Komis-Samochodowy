import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KomisSamochodowyDlaAdminow extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	Connection connection = null;
	private JTextField textFieldID;
	private JTextField textFieldMarka;
	private JTextField textFieldModel;
	private JTextField textFieldPojemnoscSilnika;
	private JTextField textFieldRocznik;
	private JTextField textFieldKolor;
	private JTextField textFieldCena;
	private JComboBox comboBoxSelect;
	
	private Login login;
	private JTextField textField_Searching;
	
	
	public void setTextFieldToBlank() {
		textFieldID.setText("");
		textFieldMarka.setText("");
		textFieldModel.setText("");
		textFieldPojemnoscSilnika.setText("");
		textFieldRocznik.setText("");
		textFieldKolor.setText("");
		textFieldCena.setText("");

	}
	
	
	

	public void refleshdata() {

		try {
			String quesry = "select * from Samochody";
			PreparedStatement pst = connection.prepareStatement(quesry);
			ResultSet rs = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "B³¹d");
		}

	}

	/**
	 * Create the frame.
	 */
	
	
	public KomisSamochodowyDlaAdminow() {
		//Frame frame= new JFrame();
		
		
		connection = sqLiteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 764, 539);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnZaladujDane = new JButton("Za\u0142aduj dane");
		btnZaladujDane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String quesry = "select * from Samochody";
					PreparedStatement pst = connection.prepareStatement(quesry);
					ResultSet rs = pst.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "B³¹d");
				}

			}
		});
		btnZaladujDane.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnZaladujDane.setBounds(527, 36, 202, 41);
		panel.add(btnZaladujDane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 130, 488, 225);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					int row = table.getSelectedRow();

					String id_ = (table.getModel().getValueAt(row, 0).toString());

					String query = "Select * from Samochody where id='" + id_ + "' ";
					PreparedStatement pst = connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						textFieldID.setText(rs.getString("id"));
						textFieldMarka.setText(rs.getString("Marka"));
						textFieldModel.setText(rs.getString("Model"));
						textFieldPojemnoscSilnika.setText(rs.getString("PojemnoœæSilnika"));
						textFieldRocznik.setText(rs.getString("Rocznik"));
						textFieldKolor.setText(rs.getString("Kolor"));
						textFieldCena.setText(rs.getString("Cena"));

					}
					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 60, 57, 17);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Marka");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 118, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Model");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 172, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Pojemno\u015B\u0107 silnika");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 221, 154, 31);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Rocznik");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 285, 85, 17);
		panel.add(lblNewLabel_4);

		JLabel lblKolor = new JLabel("Kolor");
		lblKolor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKolor.setBounds(10, 342, 85, 13);
		panel.add(lblKolor);

		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldID.setBounds(93, 60, 126, 31);
		panel.add(textFieldID);
		textFieldID.setColumns(10);

		textFieldMarka = new JTextField();
		textFieldMarka.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMarka.setBounds(93, 112, 126, 31);
		panel.add(textFieldMarka);
		textFieldMarka.setColumns(10);

		textFieldModel = new JTextField();
		textFieldModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldModel.setColumns(10);
		textFieldModel.setBounds(93, 164, 126, 31);
		panel.add(textFieldModel);

		textFieldPojemnoscSilnika = new JTextField();
		textFieldPojemnoscSilnika.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPojemnoscSilnika.setColumns(10);
		textFieldPojemnoscSilnika.setBounds(130, 221, 85, 31);
		panel.add(textFieldPojemnoscSilnika);

		textFieldRocznik = new JTextField();
		textFieldRocznik.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldRocznik.setColumns(10);
		textFieldRocznik.setBounds(93, 273, 126, 31);
		panel.add(textFieldRocznik);

		textFieldKolor = new JTextField();
		textFieldKolor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldKolor.setColumns(10);
		textFieldKolor.setBounds(93, 333, 126, 31);
		panel.add(textFieldKolor);

		JButton btnDodajSamochodDoBazy = new JButton("Dodaj Samoch\u00F3d");
		btnDodajSamochodDoBazy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String query = "insert into Samochody (Id,Marka,Model,PojemnoœæSilnika,Rocznik,Kolor,Cena) values (?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldMarka.getText());
					pst.setString(3, textFieldModel.getText());
					pst.setString(4, textFieldPojemnoscSilnika.getText());
					pst.setString(5, textFieldRocznik.getText());
					pst.setString(6, textFieldKolor.getText());
					pst.setString(7, textFieldCena.getText());

					pst.execute();

					JOptionPane.showMessageDialog(null, "Pomyœlnie dodano do bazy danych");
					
					pst.close();
					// rs.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}

				refleshdata();
				setTextFieldToBlank();
			}
		});
		btnDodajSamochodDoBazy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDodajSamochodDoBazy.setBounds(251, 366, 202, 41);
		panel.add(btnDodajSamochodDoBazy);

		JLabel lblNewLabel_5 = new JLabel("Cena");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 394, 57, 31);
		panel.add(lblNewLabel_5);

		textFieldCena = new JTextField();
		textFieldCena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCena.setColumns(10);
		textFieldCena.setBounds(93, 394, 126, 31);
		panel.add(textFieldCena);

		JButton btnEdytuj = new JButton("Edytuj");
		btnEdytuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String query = "Update Samochody set id='" + textFieldID.getText() + "',   		Marka='"
							+ textFieldMarka.getText() + "',         Model='" + textFieldModel.getText()
							+ "', PojemnoœæSilnika='" + textFieldPojemnoscSilnika.getText() + "',     Rocznik='"
							+ textFieldRocznik.getText() + "',            Kolor='" + textFieldKolor.getText()
							+ "',     Cena='" + textFieldCena.getText() + "' where id='" + textFieldID.getText() + "' ";
					PreparedStatement pst = connection.prepareStatement(query);

					pst.execute();

					JOptionPane.showMessageDialog(null, "Dane zosta³y edytowane!");

					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				refleshdata();
				setTextFieldToBlank();

			}
		});
		btnEdytuj.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdytuj.setBounds(251, 429, 202, 31);
		panel.add(btnEdytuj);

		JButton btnUsun = new JButton("Usun");
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int action = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usun¹æ rekord?", "Usuniêcie rekordu", JOptionPane.YES_NO_OPTION);
				if(action ==0) {
					
					
					
				
				try {
					String query = "Delete from Samochody where id='" + textFieldID.getText() + "'";
					PreparedStatement pst = connection.prepareStatement(query);

					pst.execute();

					JOptionPane.showMessageDialog(null, "Usuniêto Rekord!");

					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				refleshdata();
				setTextFieldToBlank();
				}
			}
		});
		btnUsun.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUsun.setBounds(251, 482, 202, 29);
		panel.add(btnUsun);

		JButton btnNewButton = new JButton("Wyczysc dane poni\u017Cej");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setTextFieldToBlank();
				
			}
		});
		btnNewButton.setBounds(10, 11, 165, 31);
		panel.add(btnNewButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				login= new Login();
				SwingUtilities.windowForComponent((Component) arg0.getSource()).dispose();
				
				
				
			}
		});
		btnLogout.setBounds(602, 463, 89, 23);
		panel.add(btnLogout);
		
		textField_Searching = new JTextField();
		textField_Searching.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			
				try {
					int row = table.getSelectedRow();

			//		String id_ = (table.getModel().getValueAt(row, 0).toString());

			//		String query = "Select * from Samochody where id='" + id_ + "' ";
					
					
					
					String selection=(String)comboBoxSelect.getSelectedItem();
					
			//		String query = "Select * from Samochody where model=? ";
					
					String query = "Select * from Samochody where "+selection+"=? ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textField_Searching.getText());
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
	//				while (rs.next()) {
	//				
	//				}
					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			
				
				
				
				
				
				
				
				
				
				
			/*	
				DefaultTableModel table1= (DefaultTableModel)table.getModel();
				String search = textField_Searching.getText().toLowerCase();
				TableRowSorter<DefaultTableModel>  tr = new TableRowSorter<DefaultTableModel>(table1);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
				*/
				
				
			}
		});
		textField_Searching.setBounds(340, 60, 154, 41);
		panel.add(textField_Searching);
		textField_Searching.setColumns(10);
		
		comboBoxSelect = new JComboBox();
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"id", "Marka", "Model", "Pojemno\u015B\u0107Silnika", "Rocznik", "Kolor", "Cena"}));
		comboBoxSelect.setBounds(332, 16, 162, 26);
		panel.add(comboBoxSelect);
		
		
		// refleshdata();
		
	}
}
