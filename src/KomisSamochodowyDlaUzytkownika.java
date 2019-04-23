import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KomisSamochodowyDlaUzytkownika extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	Connection connection = null;
	private JComboBox comboBoxSelect;
	private Login login;
	private JTextField textField_Searching;
	
	

	public void refleshdata() {

		try {
			String quesry = "select * from Samochody";
			PreparedStatement pst = connection.prepareStatement(quesry);
			ResultSet rs = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "B��d");
		}

	}
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public KomisSamochodowyDlaUzytkownika() {
		/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 614, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Witamy w panelu dla plebsu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(35, 101, 579, 209);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(143, 374, 46, 14);
		panel.add(lblNewLabel_1);
	
	
	
	*/
	
	
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
							JOptionPane.showMessageDialog(null, "B��d");
						}

					}
				});
				btnZaladujDane.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnZaladujDane.setBounds(221, 11, 202, 41);
				panel.add(btnZaladujDane);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(28, 130, 701, 305);
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

							
							/*
							
							while (rs.next()) {
								textFieldID.setText(rs.getString("id"));
								textFieldMarka.setText(rs.getString("Marka"));
								textFieldModel.setText(rs.getString("Model"));
								textFieldPojemnoscSilnika.setText(rs.getString("Pojemno��Silnika"));
								textFieldRocznik.setText(rs.getString("Rocznik"));
								textFieldKolor.setText(rs.getString("Kolor"));
								textFieldCena.setText(rs.getString("Cena"));

							}
							
							*/
							pst.close();

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
				scrollPane.setViewportView(table);
				
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
				textField_Searching.setBounds(28, 61, 154, 41);
				panel.add(textField_Searching);
				textField_Searching.setColumns(10);
				
				comboBoxSelect = new JComboBox();
				comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"id", "Marka", "Model", "Pojemno\u015B\u0107Silnika", "Rocznik", "Kolor", "Cena"}));
				comboBoxSelect.setBounds(20, 11, 162, 26);
				panel.add(comboBoxSelect);
				
				
				// refleshdata();
	
	
	}
}
