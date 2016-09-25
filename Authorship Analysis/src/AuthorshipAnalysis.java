import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * This is the main class that reads and analyzes
 * text files, storing them in author objects.
 * @author Trever Mock
 * @version 2.0
 */
public class AuthorshipAnalysis implements Serializable {
  
    /** Default state save/restore file name. */
    public static final String SERIAL_FILENAME = "memoryAA.ser";
    
    /** Default Serial ID */
    private static final long serialVersionUID = -1978588042209236627L;
    
    /** List of author objects */
    public static List<Author> authors;
    
    /** String to be written on the JFrame TextArea */
    public static String outputText = "";
    
    /**
     * Constructor that takes in an Author name and an
     * arbitrary number of text files that contain the
     * authors works. The constructor stores the analyzed
     * data and saves the results using serialization.
     * @param authorName - the authors name
     * @param files - text files of the authors works to be
     * analyzed
     * @throws IOException 
     */
    public AuthorshipAnalysis(String authorName, String... files) 
    throws IOException {
        outputText = "";
        // load a list of authors if the file exists
        File restoreFile = new File(SERIAL_FILENAME);
        if(restoreFile.exists()) {
            restore(AuthorshipAnalysis.SERIAL_FILENAME);
        } else {
            authors = new ArrayList<Author>();
        }
        
        // find the author in the list or create a new one
        Author author = new Author(authorName);
        boolean found = false;
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).toString().equals(author.toString())) {
                author = authors.get(i);
                authors.remove(i);
                outputText = outputText + "Author found in memory!\n\n";
                found = true;
            }
        } 
        if (!found) {
            outputText = outputText + "Author not found in memory. New Author Created!\n\n";
        }
        
        // Analyze the author
        for(int i = 0; i < files.length; i++) {
            Analysis.analyze(files[i], author);
        }
        
        // add the author to the list
        authors.add(author);
        
        // update outputText
        outputText = outputText + "Author Updated: " + author
                + "\nAverage Word Length: " + roundDecimal(author.getAvgWordLength())
                + "\nAverage Sentence Length: " + roundDecimal(author.getAvgSentenceLength())
                + "\nRatio of Word to Sentence Length " + roundDecimal(author.getWordToSentenceRatio())
                + "\nVocabulary Richness (Unique Words/Total Words): " + roundDecimal(author.getVocabularyRichness())
                + "\nMost Frequent Letter: " + author.getMostFrequentLetter()
                + "\nLeast Frequent Letter: " + author.getLeastFrequentLetter()
                + "\nMost Frequent Letter Pair: " + author.getMostFrequentLetterPair()
                + "\nLeast Frequent Letter Pair: " + author.getLeastFrequentLetterPair()
                + "\n";
                
        
        // save the list of authors
        try {
            save(AuthorshipAnalysis.SERIAL_FILENAME);
            outputText = outputText + "\nAuthor Updated Successfully!";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Alternate constructor used for finding an author or removing
     * an author.
     * <p>
     * To remove an author, use 0 as the first parameter.
     * <p>
     * To analyze a text file and find author matches, use 1 as the first parameter.
     * @param option - 0 to remove an author, 1 to analyze a text file and find author matches
     * @param s - The author (if option 0) or the text file (if option 1)
     * @throws IOException
     */
    public AuthorshipAnalysis(int option, String s) 
    throws IOException {
        outputText = "";
        // load a list of authors if the file exists
        File restoreFile = new File(SERIAL_FILENAME);
        if(restoreFile.exists()) {
            restore(AuthorshipAnalysis.SERIAL_FILENAME);
        } else {
            authors = new ArrayList<Author>();
        } 
        if (option == 0) {    // Remove Author
            String authorName = s;
            boolean found = false;
            for (Author author : authors) {
                if (author.toString().toLowerCase().equals(authorName.toLowerCase())) {
                    authors.remove(author);
                    found = true; 
                    break;
                }
            } 
            // save the list of authors
            try {
                save(AuthorshipAnalysis.SERIAL_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // JOptionPane message to user
            if (found) {
                JOptionPane.showMessageDialog(null, "Author successfully deleted!", "Message", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Author not found in memory!", "Message", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (option == 1) {   // Find Unknown Author
            String file = s;
            for (Author author : authors) {
                if (author.toString().equals("unknown")) {
                    authors.remove(author);
                    break;
                }
            }
            
            Author unknown = new Author("unknown");
            Analysis.analyze(file, unknown);
            
            outputText = outputText + "---Analysis for the unkown text file---\n\n";   
            // Find closest matches
            // Average Word length
            Author temp = authors.get(0);
            for (Author author : authors) {
                if (Math.abs(author.getAvgWordLength() - unknown.getAvgWordLength()) < temp.getAvgWordLength()) {
                    temp = author;
                }
            }
            outputText = outputText + "Average Word Length Match: " + temp + " with a difference of: " 
                            + roundDecimal(Math.abs(temp.getAvgWordLength() - unknown.getAvgWordLength())) + "\n";
            // Average Sentence length
            temp = authors.get(0);
            for (Author author : authors) {
                if (Math.abs(author.getAvgSentenceLength() - unknown.getAvgSentenceLength()) < temp.getAvgSentenceLength()) {
                    temp = author;
                }
            }
            outputText = outputText + "Average Sentence Length Match: " + temp + " with a difference of: " 
                            + roundDecimal(Math.abs(temp.getAvgSentenceLength() - unknown.getAvgSentenceLength())) + "\n";
            // Word to Sentence Ratio
            temp = authors.get(0);
            for (Author author : authors) {
                if (Math.abs(author.getWordToSentenceRatio() - unknown.getWordToSentenceRatio()) < temp.getWordToSentenceRatio()) {
                    temp = author;
                }
            }
            outputText = outputText + "Word to Sentence Ratio Match: " + temp + " with a difference of: " 
                    + roundDecimal(Math.abs(temp.getWordToSentenceRatio() - unknown.getWordToSentenceRatio())) + "\n";
            // Vocabulary Richness
            temp = authors.get(0);
            for (Author author : authors) {
                if (Math.abs(author.getVocabularyRichness() - unknown.getVocabularyRichness()) < temp.getVocabularyRichness()) {
                    temp = author;
                }
            }
            outputText = outputText + "Vocabulary Richness Match: " + temp + " with a difference of: " 
                    + roundDecimal(Math.abs(temp.getVocabularyRichness() - unknown.getVocabularyRichness())) + "\n";
            // Most Frequent Letter
            boolean match = false;
            Character mostFrequentLetter = unknown.getMostFrequentLetter();
            outputText = outputText + "\nThe most frequent letter used in the unknown text is: " + "'" + mostFrequentLetter + "'\n";
            for (Author a : authors) {
                if (a.getMostFrequentLetter().equals(mostFrequentLetter)) {
                    outputText = outputText + "    " + a + " also has '" + mostFrequentLetter + "' as their most frequent letter.\n";
                    match = true;
                }
            }
            if (!match) {
                outputText = outputText + "    No other author has '" + mostFrequentLetter + "' as their most frequent letter.\n"; 
            }
            // Least Frequent Letter
            match = false;
            Character leastFrequentLetter = unknown.getLeastFrequentLetter();
            outputText = outputText + "\nThe least frequent letter used in the unknown text is: " + "'" + leastFrequentLetter + "'\n";
            for (Author a : authors) {
                if (a.getLeastFrequentLetter().equals(leastFrequentLetter)) {
                    outputText = outputText + "    " + a + " also has '" + leastFrequentLetter + "' as their least frequent letter.\n";
                    match = true;
                }
            }
            if (!match) {
                outputText = outputText + "    No other author has '" + leastFrequentLetter + "' as their least frequent letter.\n"; 
            }
            // Most Frequent Letter Pair
            match = false;
            String mostFrequentLetterPair = unknown.getMostFrequentLetterPair();
            outputText = outputText + "\nThe most frequent letter pair used in the unknown text is: " + "'" + mostFrequentLetterPair + "'\n";
            for (Author a : authors) {
                if (a.getMostFrequentLetterPair().equals(mostFrequentLetterPair)) {
                    outputText = outputText + "    " + a + " also has '" + mostFrequentLetterPair + "' as their most frequent letter Pair.\n";
                    match = true;
                }
            }
            if (!match) {
                outputText = outputText + "    No other author has '" + mostFrequentLetterPair + "' as their most frequent letter Pair.\n"; 
            }
            // Least Frequent Letter Pair
            match = false;
            String leastFrequentLetterPair = unknown.getLeastFrequentLetterPair();
            outputText = outputText + "\nThe least frequent letter pair used in the unknown text is: " + "'" + leastFrequentLetterPair + "'\n";
            for (Author a : authors) {
                if (a.getLeastFrequentLetterPair().equals(leastFrequentLetterPair)) {
                    outputText = outputText + "    " + a + " also has '" + leastFrequentLetterPair + "' as their least frequent letter pair.\n";
                    match = true;
                }
            }
            if (!match) {
                outputText = outputText + "    No other author has '" + leastFrequentLetterPair + "' as their least frequent letter pair.\n"; 
            }    
        } else {
            System.out.println("ERROR: Unknown Command");
        }
    }
    
    /**
     * Saves this AuthorshipAnalysis to a file.
     * @param filename the name of the file in which to save this object under;
     * if null, uses default file name
     * @return <code>true</code> if successful save; <code>false</code> otherwise
     * @throws java.io.IOException if unexpected IO error
     */
    public final boolean save(final String filename)
    throws java.io.IOException {
        boolean success = true;
        String listFile = filename;
        if(listFile == null) {
            listFile = AuthorshipAnalysis.SERIAL_FILENAME;
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(listFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(authors);
            out.close();
            fileOut.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            success = false;
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    /**
     * Saves this AuthorshipAnalysis to a file.
     * @param filename the name of the file in which to save this object;
     * if null, uses default file name.
     * @return <code>true</code> if successsful save;
     * <code>false</code> otherwise
     * @throws java.io.IOException if unexpected IO error
     */
    @SuppressWarnings("unchecked")
    public final boolean restore(final String filename)
    throws java.io.IOException {
        boolean success = false;
        String listFile = filename;
        if (listFile == null) {
            listFile = AuthorshipAnalysis.SERIAL_FILENAME;
        }
        try {
            FileInputStream fileIn = new FileInputStream(listFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            try {
                authors = (List<Author>) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
    
    private double roundDecimal(double d) {
        double result = d*100;
        int i = (int) result;
        result = (double) i;
        return result/100;
    }
}
