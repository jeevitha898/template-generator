import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class gui {
    public static void main(String[] args) {
        JFrame f = new JFrame(); // Creating instance of JFrame
        f.setTitle("Template Generator"); // Setting the title
        // Setting the icon for the application
        try {
            ImageIcon icon = new ImageIcon("icon.jpeg");
            f.setIconImage(icon.getImage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Icon not found. Default icon will be used.");
        }

        // Creating buttons
        JButton hostnameButton = new JButton("Hostname");
        hostnameButton.setBounds(50, 50, 120, 40); // x, y, width, height

        JButton switchnameButton = new JButton("Switch Name");
        switchnameButton.setBounds(50, 120, 130, 40); // x, y, width, height

        JButton ipaddressButton = new JButton("IP Address");
        ipaddressButton.setBounds(50, 190, 130, 40); // x, y, width, height

        JButton subnetButton = new JButton("Subnet");
        subnetButton.setBounds(50, 260, 130, 40); // x, y, width, height

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setBounds(50, 330, 130, 40); // x, y, width, height

        JButton OKButton = new JButton("OK");
        OKButton.setBounds(50, 400, 100, 40); // x, y, width, height

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(200, 400, 130, 40); // x, y, width, height

        // Creating text fields for user input
        JTextField hostnameField = new JTextField();
        hostnameField.setBounds(200, 50, 150, 40); // x, y, width, height

        JTextField switchnameField = new JTextField();
        switchnameField.setBounds(200, 120, 150, 40); // x, y, width, height

        JTextField ipaddressField = new JTextField();
        ipaddressField.setBounds(200, 190, 150, 40); // x, y, width, height

        JTextField subnetField = new JTextField();
        subnetField.setBounds(200, 260, 150, 40); // x, y, width, height

        JTextField filePathField = new JTextField();
        filePathField.setBounds(200, 330, 150, 40); // x, y, width, height
        filePathField.setEditable(false); // Make the field read-only

        // Adding components to the frame
        f.add(hostnameButton);
        f.add(hostnameField);
        f.add(switchnameButton);
        f.add(switchnameField);
        f.add(ipaddressButton);
        f.add(ipaddressField);
        f.add(subnetButton);
        f.add(subnetField);
        f.add(chooseFileButton);
        f.add(filePathField);
        f.add(OKButton);
        f.add(clearButton);

        // Inside your ActionListener for the OK button
        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieving input values
                    String hostname = hostnameField.getText();
                    String ipaddress = ipaddressField.getText();
                    String subnet = subnetField.getText();
                    String filePath = filePathField.getText();
        
                    if (hostname.isEmpty()) {
                        JOptionPane.showMessageDialog(f, "Hostname cannot be empty.");
                        return;
                    }
        
                    if (ipaddress.isEmpty()) {
                        JOptionPane.showMessageDialog(f, "IP Address cannot be empty.");
                        return;
                    }
        
                    // if (subnet.isEmpty()) {
                    //     JOptionPane.showMessageDialog(f, "Subnet cannot be empty.");
                    //     return;
                    // }
        
                    if (filePath.isEmpty()) {
                        JOptionPane.showMessageDialog(f, "No file selected.");
                        return;
                    }
        
                    // Reading the content of the chosen file
                    File selectedFile = new File(filePath);
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
        
                    // Writing the modified content to a new file named as hostname
                    File newFile = new File(hostname + ".txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
        
                    String line;
                    boolean insideVlan1 = false;
        
                    while ((line = reader.readLine()) != null) {

                        if (line.trim().startsWith("hostname")) {
                            line = "hostname " + hostname; // Modify hostname in the cloned file
                        }
                        
                        // Check if we are inside the interface Vlan1 section
                        if (line.trim().startsWith("interface Vlan1")) {
                            insideVlan1 = true; // We're inside the Vlan1 section
                        }
                    
                        if (insideVlan1) {
                            // Modify ip address inside the interface Vlan1 section
                            if (line.trim().startsWith("ip address")) {
                                line = "ip address " + ipaddress + " " + subnet; // Modify IP address only in Vlan1
                            }
                    
                            // Check for the end of the Vlan1 section (next line starts with '!')
                            if (line.trim().startsWith("!")) {
                                insideVlan1 = false; // Exit Vlan1 section after encountering '!'
                            }
                        } else {
                            // Leave other ip addresses unchanged (no modification outside Vlan1)
                            if (line.trim().startsWith("ip address")) {
                                // No modification needed for IPs outside of Vlan1
                            }
                        }
                    
                        // Write the line to the new file
                        writer.write(line);
                        writer.newLine();
                    }
                    
                      
        
                    reader.close();
                    writer.close();
        
                    JOptionPane.showMessageDialog(f, "File cloned and updated as " + hostname + ".txt");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(f, "Error processing file: " + ioException.getMessage());
                }
            }
        });
        

        // ActionListener for the Choose File button
        chooseFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                // Adding filter for .txt files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(f);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // ActionListener for the Clear button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear only the input fields, not the file path
                hostnameField.setText("");
                switchnameField.setText("");
                ipaddressField.setText("");
                subnetField.setText("");
            }
        });

        // Setting frame properties
        f.setSize(400, 500); // Adjusting frame size for better appearance
        f.setLayout(null);
        f.setVisible(true);
    }
}
