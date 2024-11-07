package com.peter_burbery.PDB_creator.com.peter_burbery.PDB_creator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDBCreator {

    public static void main(String[] args) {
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
        pdbNameResultField.setEditable(false); // Initially non-editable
        JLabel pdbAdminLabel = new JLabel("PDB Admin:");
        JTextField pdbAdminResultField = new JTextField(20);
        pdbAdminResultField.setEditable(false); // Initially non-editable
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
                    // Create a Name object based on user input (assuming Name is defined in a separate file)
                    Name pdbNameObj = new Name(pdbNameInput);

                    // Use the underscore uppercase property for the PDB name and PDB admin name
                    String pdbName = pdbNameObj.getUnderscoreUppercase();
                    String pdbAdmin = pdbNameObj.getUnderscoreUppercase();

                    // Populate the result fields and make them editable
                    pdbNameResultField.setText(pdbName);
                    pdbAdminResultField.setText(pdbAdmin);
                    pdbNameResultField.setEditable(true);
                    pdbAdminResultField.setEditable(true);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a valid PDB name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Show the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
