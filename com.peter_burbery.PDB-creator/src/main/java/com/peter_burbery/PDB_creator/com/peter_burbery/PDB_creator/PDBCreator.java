package com.peter_burbery.PDB_creator.com.peter_burbery.PDB_creator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PDBCreator {

	private static String OraclePluggableDatabaseDirectory;

	public static void main(String[] args) {
		// Load settings.json for the OraclePluggableDatabaseDirectory
		loadSettings();

		SwingUtilities.invokeLater(PDBCreator::createAndShowGUI);
	}

	private static void createAndShowGUI() {
		// Create the frame
		JFrame frame = new JFrame("PDB Creator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(new GridBagLayout());

		// Create components
		JLabel instructionLabel = new JLabel("Enter PDB name:");
		JTextField pdbNameField = new JTextField(20);
		JLabel pdbNameLabel = new JLabel("PDB Name:");
		JTextField pdbNameResultField = new JTextField(20);
		pdbNameResultField.setEditable(false);
		JLabel pdbAdminLabel = new JLabel("PDB Admin:");
		JTextField pdbAdminResultField = new JTextField(20);
		pdbAdminResultField.setEditable(false);
		JButton createButton = new JButton("Generate");

		// Set layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.add(instructionLabel, gbc);

		gbc.gridx = 1;
		frame.add(pdbNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.add(pdbNameLabel, gbc);

		gbc.gridx = 1;
		frame.add(pdbNameResultField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		frame.add(pdbAdminLabel, gbc);

		gbc.gridx = 1;
		frame.add(pdbAdminResultField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		frame.add(createButton, gbc);

		// Add action listener for the button
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pdbNameInput = pdbNameField.getText().trim();
				if (!pdbNameInput.isEmpty()) {
					// Create a Name object based on user input
					Name pdbNameObj = new Name(pdbNameInput);

					// Use the underscore uppercase property for the PDB name and PDB admin name
					String pdbName = pdbNameObj.getUnderscoreUppercase();
					String pdbAdmin = pdbNameObj.getUnderscoreUppercase();

					// Dynamically determine folder location
					String fileName = pdbNameObj.getDashLowercase();
					String fileLocation = OraclePluggableDatabaseDirectory + fileName;

					// Display the folder location in a dialog
					JOptionPane.showMessageDialog(frame, "Pluggable Database Directory: " + fileLocation,
							"Directory Info", JOptionPane.INFORMATION_MESSAGE);

					// Populate the result fields and make them editable
					pdbNameResultField.setText(pdbName);
					pdbAdminResultField.setText(pdbAdmin);
					pdbNameResultField.setEditable(true);
					pdbAdminResultField.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter a valid PDB name.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Show the frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void loadSettings() {
		JSONParser parser = new JSONParser();
		try (InputStream input = PDBCreator.class.getResourceAsStream("/settings.json")) {
			if (input == null) {
				throw new RuntimeException("settings.json not found in resources");
			}
			JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(input));
			OraclePluggableDatabaseDirectory = (String) jsonObject.get("OraclePluggableDatabaseDirectory");
		} catch (Exception e) {
			e.printStackTrace();
			OraclePluggableDatabaseDirectory = "C:\\oracle-pluggable-database\\"; // Fallback to default
		}
	}
}
