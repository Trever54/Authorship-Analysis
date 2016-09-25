import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * This class manages the JFrame User Interface. 
 * It includes formatting of the JFrame and action listeners.
 * @author Trever
 * @version 2.0
 */
public class DisplayManager extends JFrame{

    /** Auto Generated serial Version ID. */
    private static final long serialVersionUID = -3238106715883671250L;

    /** Constructor that sets up the JFrame and opens the Main Menu content */
    public DisplayManager() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Authorship Analysis");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(dim.width / 2, dim.height / 2);
        this.setLocation((dim.width / 2) - (this.getSize().width / 2), (dim.height / 2) - (this.getSize().height/2));
        this.setVisible(true);
        mainMenu();
    }
    
    /** Main method that acts as the command line driver. */
    public static void main(String[] args) {
        DisplayManager window = new DisplayManager();
    }
    
    /**
     * The Main Menu of the program user interface.
     */
    private void mainMenu() {
        Font buttonFont = new Font("Font", Font.PLAIN, 32); 
        
        // Components
        JButton storeButton = new JButton("Store Data");
        JButton findButton = new JButton("Find Author");
        JButton removeButton = new JButton("Remove Data");
        JButton helpButton = new JButton("Help");
        storeButton.setFont(buttonFont);
        findButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        helpButton.setFont(buttonFont);
        final JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(4, 1));
        mainMenu.add(storeButton);
        mainMenu.add(findButton);
        mainMenu.add(removeButton);
        mainMenu.add(helpButton);
        
        setContentPane(mainMenu);
        revalidate();
        
        // Action Listeners
        storeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storeDataMenu();
            }
        });
        
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findAuthorMenu();
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeDataMenu();
            }
        });
        
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpMenu();
            }
        });
    }
    
    /**
     * The Store Menu of the program user interface.
     * Handles storing data on authors using text
     * files of the authors works.
     */
    private void storeDataMenu() {   
        Font buttonFont = new Font("Font", Font.PLAIN, 32); 
        Font textFont = new Font("Font2", Font.PLAIN, 22);
        
        // Components
        JLabel authorName = new JLabel("Author Name:");
        JLabel pathLabel1 = new JLabel("File Path:");
        JLabel pathLabel2 = new JLabel("File Path:");
        JLabel pathLabel3 = new JLabel("File Path:");
        final JTextField authorText = new JTextField("");
        final JTextField pathText1 = new JTextField("");
        final JTextField pathText2 = new JTextField("");
        final JTextField pathText3 = new JTextField("");
        JButton backButton = new JButton("Back");
        JButton analyzeButton = new JButton("Analyze and Store");
        final JTextArea consoleText = new JTextArea("");
        authorName.setFont(textFont);
        pathLabel1.setFont(textFont);
        pathLabel2.setFont(textFont);
        pathLabel3.setFont(textFont);
        authorText.setFont(textFont);
        pathText1.setFont(textFont);
        pathText2.setFont(textFont);
        pathText3.setFont(textFont);
        backButton.setFont(buttonFont);
        analyzeButton.setFont(buttonFont);
        consoleText.setFont(new Font("console", Font.PLAIN, 14));
        consoleText.setEditable(false);
        final JPanel authorInput = new JPanel();
        authorInput.setLayout(new BoxLayout(authorInput, BoxLayout.X_AXIS));
        authorInput.add(authorName);
        authorInput.add(Box.createRigidArea(new Dimension(10, 0)));
        authorInput.add(authorText);
        final JPanel pathInput = new JPanel();
        pathInput.setLayout(new BoxLayout(pathInput, BoxLayout.X_AXIS));
        pathInput.add(Box.createRigidArea(new Dimension(10, 0)));
        pathInput.add(pathLabel1);
        pathInput.add(Box.createRigidArea(new Dimension(10, 0)));
        pathInput.add(pathText1);
        pathInput.add(Box.createRigidArea(new Dimension(10, 0)));
        pathInput.add(pathLabel2);
        pathInput.add(Box.createRigidArea(new Dimension(10, 0)));
        pathInput.add(pathText2);
        pathInput.add(Box.createRigidArea(new Dimension(10, 0)));
        pathInput.add(pathLabel3);
        pathInput.add(Box.createRigidArea(new Dimension(10, 0)));
        pathInput.add(pathText3);
        final JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.add(backButton);
        buttonRow.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonRow.add(analyzeButton);
        final JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 1));
        top.add(authorInput);
        top.add(pathInput);
        final JPanel console = new JPanel();
        console.setLayout(new BoxLayout(console, BoxLayout.X_AXIS));
        consoleText.setSize(this.getWidth() - 20, this.getHeight() 
                - (top.getHeight() + buttonRow.getHeight() + 20));
        consoleText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        console.add(Box.createRigidArea(new Dimension(10, 0)));
        console.add(consoleText);
        console.add(Box.createRigidArea(new Dimension(10, 0)));  
        final JPanel storeDataMenu = new JPanel();
        storeDataMenu.setLayout(new BoxLayout(storeDataMenu, BoxLayout.Y_AXIS));
        storeDataMenu.add(top);
        storeDataMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        storeDataMenu.add(console);
        storeDataMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        storeDataMenu.add(buttonRow);

        setContentPane(storeDataMenu);
        revalidate();
        
        // Action Listeners
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });
        
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!authorText.getText().equals("")) {
                    List<String> files = new ArrayList<String>();
                    if (!pathText1.getText().equals("")) {
                        files.add(pathText1.getText());
                    } 
                    if (!pathText2.getText().equals("")) {
                        files.add(pathText2.getText());
                    }
                    if (!pathText3.getText().equals("")) {
                        files.add(pathText3.getText());
                    }
                    if (files.size() != 0) {
                        String[] fileArray = new String[files.size()];
                        fileArray = files.toArray(fileArray);                
                        try {
                            AuthorshipAnalysis aa = new AuthorshipAnalysis(authorText.getText(), fileArray);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        consoleText.setText(AuthorshipAnalysis.outputText);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: File path fields can not all be empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: Author field can not be empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }    
            }
        });    
    }
    
    /**
     * The Find Menu of the program user interface.
     * Used to analyze an unknown text file and
     * give proposed authors for each metric.
     */
    private void findAuthorMenu() {
        Font font = new Font("Font", Font.PLAIN, 32); 
        
        // Components
        JLabel fileToFind = new JLabel("File to Analyze:");
        final JTextField pathText = new JTextField("");
        JButton backButton = new JButton("Back");
        JButton analyzeButton = new JButton("Analyze and Find");
        final JTextArea consoleText = new JTextArea("");  
        fileToFind.setFont(font);
        pathText.setFont(font);
        analyzeButton.setFont(font);   
        backButton.setFont(font);
        consoleText.setFont(new Font("console", Font.PLAIN, 14));
        consoleText.setEditable(false);
        final JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 1));
        top.add(fileToFind);
        top.add(pathText);
        final JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.add(backButton);
        buttonRow.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonRow.add(analyzeButton);
        final JPanel console = new JPanel();
        console.setLayout(new BoxLayout(console, BoxLayout.X_AXIS));
        consoleText.setSize(this.getWidth() - 20, this.getHeight() - (top.getHeight() + buttonRow.getHeight() + 20));
        consoleText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        consoleText.setEditable(false);
        console.add(Box.createRigidArea(new Dimension(10, 0)));
        console.add(consoleText);
        console.add(Box.createRigidArea(new Dimension(10, 0)));   
        final JPanel findAuthorMenu = new JPanel();
        findAuthorMenu.setLayout(new BoxLayout(findAuthorMenu, BoxLayout.Y_AXIS));
        findAuthorMenu.add(top);
        findAuthorMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        findAuthorMenu.add(console);
        findAuthorMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        findAuthorMenu.add(buttonRow);

        setContentPane(findAuthorMenu);
        revalidate();
        
        // Action Listeners
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });
        
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!pathText.getText().equals("")) {
                    try {
                        AuthorshipAnalysis aa = new AuthorshipAnalysis(1, pathText.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    consoleText.setText(AuthorshipAnalysis.outputText);
                }  else {
                    JOptionPane.showMessageDialog(null, "ERROR: The text field can not be empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });    
    }

    /**
     * The Remove Menu of the program user interface.
     * Handles removing a single author or removing
     * all stored data.
     */
    private void removeDataMenu() {
        Font font = new Font("Font", Font.PLAIN, 32); 
        
        // Components
        JLabel authorName = new JLabel("Author Name:");
        final JTextField authorText = new JTextField("");
        JButton removeAuthor = new JButton("Remove Author");
        JButton removeAll = new JButton("Clear All Data");
        JButton backButton = new JButton("Back");
        authorName.setFont(font);
        authorText.setFont(font);
        removeAuthor.setFont(font);
        removeAll.setFont(font);
        backButton.setFont(font);
        
        final JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.add(backButton);
        final JPanel removeInput = new JPanel();
        removeInput.setLayout(new BoxLayout(removeInput, BoxLayout.X_AXIS));
        removeInput.add(Box.createRigidArea(new Dimension(10, 0)));
        removeInput.add(authorName);
        removeInput.add(Box.createRigidArea(new Dimension(10, 0)));
        removeInput.add(authorText);
        removeInput.add(Box.createRigidArea(new Dimension(10, 0)));
        removeInput.add(removeAuthor);
        removeInput.add(Box.createRigidArea(new Dimension(10, 0)));
        final JPanel formatInput = new JPanel();
        formatInput.setLayout(new GridLayout(5, 1));
        formatInput.add(removeInput);
        formatInput.add(Box.createRigidArea(new Dimension(0, 0)));
        formatInput.add(removeAll);
        final JPanel removeMenu = new JPanel();
        removeMenu.setLayout(new BoxLayout(removeMenu, BoxLayout.Y_AXIS));
        removeMenu.add(formatInput);
        removeMenu.add(buttonRow);
        
        setContentPane(removeMenu);
        revalidate();
        
        // Action Listeners
        removeAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!authorText.getText().equals("")) {
                    try {
                        AuthorshipAnalysis aa = new AuthorshipAnalysis(0, authorText.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }   
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: Author text field is empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        removeAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File clearFile = new File(AuthorshipAnalysis.SERIAL_FILENAME);
                if(clearFile.exists()) {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all memory?", "Message", JOptionPane.YES_NO_OPTION); 
                    if (result == JOptionPane.YES_OPTION) {
                        clearFile.delete();
                        JOptionPane.showMessageDialog(null, "Memory Cleared. Success!", "Message", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "There is no memory to clear!", "Message", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });   
    }
    
    /**
     * The Help Menu of the program user interface.
     * Gives instructions on how to use the program.
     */
    private void helpMenu() {
        Font font = new Font("Font", Font.PLAIN, 32); 
        
        // Components
        JTextArea consoleText = new JTextArea(
                "This menu gives a brief explanation of how to use this program by section."
                + "\n\nSTORE DATA"
                + "\nThe authors name to be stored is entered in the 'Author Name' text field."
                + "\nFile paths to text files of that authors works are placed in the 'file path'"
                + "\nsections. Up to three can be analyzed at a time, but only one has to be entered"
                + "\nto analyze and store an author. Results of the analyzed data will be displayed "
                + "\nin the text area. After the author is stored, more data can be added by storing "
                + "\nadditional text files under that same authors name."
                + "\n\nFIND AUTHOR"
                + "\nPut the file path to a text file of an unknown authors works in the input section."
                + "\nThe analysis of that text file will appear in the text area which gives matching "
                + "\nauthors for each metric the program implements."
                + "\n\nREMOVE DATA"
                + "\nA single author can be removed by typing in that authors name and hitting the 'remove author'"
                + "\nbutton. All serialized data can be deleted by clicking the 'Clear All Data' button."   
                );
        JButton backButton = new JButton("Back to the Main Menu");
        backButton.setFont(font); 
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        final JPanel console = new JPanel();
        console.setLayout(new BoxLayout(console, BoxLayout.X_AXIS));
        consoleText.setSize(this.getWidth(), this.getHeight() - (backButton.getHeight() + 20));
        consoleText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        consoleText.setFont(new Font("console", Font.PLAIN, 14));
        consoleText.setEditable(false);
        console.add(Box.createRigidArea(new Dimension(10, 0)));
        console.add(consoleText);
        console.add(Box.createRigidArea(new Dimension(10, 0)));   
        final JPanel helpMenu = new JPanel();
        helpMenu.setLayout(new BoxLayout(helpMenu, BoxLayout.Y_AXIS));
        helpMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        helpMenu.add(console);
        helpMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        helpMenu.add(backButton);
        
        setContentPane(helpMenu);
        revalidate();
        
        // Action Listeners
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });
    } 
}
