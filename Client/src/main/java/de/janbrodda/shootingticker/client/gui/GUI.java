package de.janbrodda.shootingticker.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (Exception e) {
				}

				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 596, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 570, 407);
		frame.getContentPane().add(tabbedPane);

		JPanel panelUploadCompetition = new JPanel();
		tabbedPane.addTab("Wettkampf hochladen", null, panelUploadCompetition, null);

		JPanel panelManageCompetitions = new JPanel();
		tabbedPane.addTab("Wettk\u00E4mpfe verwalten", null, panelManageCompetitions, null);
		panelManageCompetitions.setLayout(null);

		JPanel panelSettings = new JPanel();
		tabbedPane.addTab("Einstellungen", null, panelSettings, null);
		panelSettings.setLayout(null);

		textField = new JTextField();
		textField.setBounds(126, 11, 252, 20);
		panelSettings.add(textField);
		textField.setColumns(10);

		JLabel lblServeradresse = new JLabel("Server-Adresse:");
		lblServeradresse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServeradresse.setBounds(10, 14, 106, 14);
		panelSettings.add(lblServeradresse);

		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBenutzername.setBounds(10, 39, 106, 14);
		panelSettings.add(lblBenutzername);

		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswort.setBounds(10, 64, 106, 14);
		panelSettings.add(lblPasswort);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(126, 36, 252, 20);
		panelSettings.add(textField_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(126, 64, 252, 20);
		panelSettings.add(passwordField);
	}
}
