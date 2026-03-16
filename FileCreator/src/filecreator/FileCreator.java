package filecreator;

import java.awt.*;
import javax.swing.*;

public class FileCreator extends JFrame {

    JFrame textFrame; JTextArea textArea; JMenuBar menuBar;
    
    String[] saveTexts = new String[5];
    String[] fileNames = new String[5];
    
    int fileNameIndex=0;
    // String saveText = "", fileNameString = "";
    
    JButton textFile;
    
    int textX=50, textY=50;
    
    private void createMainFrame() { 
                                     
        setSize(400,300); // 1382, 744
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        JButton createTextButton = new JButton("create a text file");
        createTextButton.setFocusPainted(false);
        createTextButton.setBounds(10,10,150,20);
        createTextButton.addActionListener(v -> {
            nameATextFile();
        });
        
        add(createTextButton);
    }
    
    private void nameATextFile() {
        JFrame createFile = new JFrame();
        createFile.setSize(180,110);
        createFile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createFile.setLayout(null);
        createFile.setResizable(false);
        createFile.setLocationRelativeTo(null);
        
        JLabel createFileLabel = new JLabel("Enter the name of the file");
        createFileLabel.setBounds(0,0,145,20);
        
        JTextField fileNameText = new JTextField("new file");
        fileNameText.setBounds(0,20,160,25);
        
        fileNameText.selectAll();
        
        JButton okButton = new JButton("Ok");
        okButton.setFocusPainted(false);
        okButton.setBounds(0,50,75,20);
        okButton.addActionListener(v -> {
            int index = fileNameIndex;
            fileNames[index] = fileNameText.getText();
            fileNameIndex++;
            createATextFile(index);
            createFile.dispose();
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusPainted(false);
        cancelButton.setBounds(80,50,75,20);
        cancelButton.addActionListener(v -> {
            createFile.dispose();
        });
        
        createFile.add(createFileLabel); createFile.add(fileNameText);
        createFile.add(okButton); createFile.add(cancelButton);
        
        createFile.setVisible(true);
    }
    
    private void createATextFile(int index) {
        int textWidth = 100, textHeight = 100;
        
        textFile = new JButton(fileNames[index]);
        textFile.setFocusPainted(false);
        textFile.setBackground(Color.white);
        textFile.setBounds(textX, textY,textWidth, textHeight);
        
        textFile.setIcon(new ImageIcon(getClass().getResource("/fileCreator/text icon.png")));
        
        textFile.setHorizontalTextPosition(SwingConstants.CENTER);
        textFile.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        textFile.addActionListener(v -> {
            openATextFile(index);
        });
        
        add(textFile);
        
        
        textX += (textWidth+5);
        
        revalidate();
        repaint();
    }
    
    private void createMenuForText(int index) {
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.white);
        
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");
        
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(v -> {
            saveTexts[index] = textArea.getText();
        });
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(v -> {
            textFrame.dispose();
        });
        
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        
        menuBar.add(fileMenu); menuBar.add(editMenu); menuBar.add(formatMenu);
        menuBar.add(viewMenu); menuBar.add(helpMenu);
    }
    
    private void openATextFile(int index) {
        textFrame = new JFrame(fileNames[index]);
        textFrame.setSize(700,370);
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setLayout(new BorderLayout());
        textFrame.setLocationRelativeTo(null);
        
        createMenuForText(index);
        textFrame.setJMenuBar(menuBar);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(0,1));
        
        textArea = new JTextArea(saveTexts[index]);
        
        JScrollPane textFileScrollPane = new JScrollPane(textArea);
        
        textFrame.add(bottomPanel, BorderLayout.SOUTH);
        textFrame.add(textFileScrollPane, BorderLayout.CENTER);
        
        textFrame.setVisible(true);
    }
    
    public FileCreator() {
        createMainFrame();
    }
    
    public static void main(String[] args) {
        FileCreator fileCreator = new FileCreator();
        fileCreator.setVisible(true);
    }
}