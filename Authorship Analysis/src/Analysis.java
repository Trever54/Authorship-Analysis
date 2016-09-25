import java.io.File;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * This class analyzes and stores data about an author
 * from a text file in the respected author object.
 * @author Trever Mock
 * @version 2.0
 */
public class Analysis {
    
    /**
     * Analyzes a text file and updates several maps of data
     * to the respected Author object.
     * @param file - the file to be analyzed
     * @param author - the author the file corresponds to
     */
    public static void analyze(String file, Author author) {
        Scanner scanner = null;
        Map<String, Double> wordMap = author.getWordMap();
        Map<Character, Double> letterMap = author.getLetterMap();
        Map<String, Double> letterPairMap = author.getLetterPairMap();
        String word;
        String letterPair;
        try {
            scanner = new Scanner(new File(file));
        } catch (Exception e) {
            System.err.println("ERROR: Invalid file path");
            JOptionPane.showMessageDialog(null, "ERROR: Invalid File Path", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            word = scanner.next().toLowerCase();  
            if (isAlphabeticalWord(word)) {
                if (wordMap.containsKey(word)) {
                    wordMap.put(word, wordMap.get(word) + 1);
                } else {
                    wordMap.put(word, new Double(1));
                }
                for (int i = 0; i < word.length(); i++) {
                    if (letterMap.containsKey(word.charAt(i))) {
                        letterMap.put(word.charAt(i), letterMap.get(word.charAt(i)) + 1);
                    } else {
                        letterMap.put(word.charAt(i), new Double(1));
                    }
                    if (i < word.length() - 1) {
                        letterPair = "" + word.charAt(i) + word.charAt(i + 1);
                        if (letterPairMap.containsKey(letterPair)) {
                            letterPairMap.put(letterPair, letterPairMap.get(letterPair) + 1);
                        } else {
                            letterPairMap.put(letterPair, new Double(1));
                        }
                    }
                }    
            }       
        }
        scanner.close();
        author.setWordMap(wordMap);
        author.setLetterMap(letterMap);
        author.setLetterPairMap(letterPairMap);
        if (author.getAvgSentenceLength() == -1) {
            author.setAvgSentenceLength(averageSentenceLength(file));
        } else {
            author.setAvgSentenceLength((author.getAvgSentenceLength() + averageSentenceLength(file)) / 2);
        } 
    }
    
    /**
     * Calculates the average sentence length in a given text file.
     * <p>
     * Sentence length is defined as number of words in a sentence.
     * <p>
     * A word is described as a sequence of any alphabetical
     * characters or apostrophes. Therefore a word does not
     * contain numbers or any symbols aside from apostrophes.
     * @param file - the text file to be analyzed
     * @return the average sentence length of the text file.
     */
    private static double averageSentenceLength(String file) {
        Scanner scanner = null;
        String word = "";
        String sentence;
        double averageSentenceLength = -1;
        try {
            scanner = new Scanner(new File(file));
        } catch (Exception e) {
            System.err.println("ERROR: Invalid file path");
            JOptionPane.showMessageDialog(null, "ERROR: Invalid File Path", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            sentence = "";
            do {
                word = scanner.next().toLowerCase();
                sentence = sentence + " " + word;
            } while (!isEndOfSentence(word) && scanner.hasNext());
            if (averageSentenceLength == -1) {
                averageSentenceLength = wordCount(sentence);
            } else {
                averageSentenceLength = (wordCount(sentence) + averageSentenceLength)/2;
            }  
        }
        scanner.close();
        return averageSentenceLength;
    }
    
    /**
     * Private helper method that calculates how many words
     * are in a sentence.
     * @param sentence - the sentence to be analyzed
     * @return the number of words in that sentence
     */
    private static double wordCount(String sentence) {
        double count = 0;
        boolean word = false;
        int endOfLine = sentence.length() - 1;  
        for (int i = 0; i < sentence.length(); i++) {
            if (Character.isLetter(sentence.charAt(i)) && i != endOfLine
                    || sentence.charAt(i) == '\'' && i != endOfLine) {
                word = true;
            } else if (!Character.isLetter(sentence.charAt(i)) && word) {
                count++;
                word = false;
            } else if (Character.isLetter(sentence.charAt(i)) && i == endOfLine) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Checks to see if this word is the end of a sentence
     * by checking if the word ends with a '.' '!' or '?' symbol.
     * @param word - the word to be checked
     * @return <code>true</code> if the word ends with a '.';
     * otherwise <code>false</code>
     */
    private static boolean isEndOfSentence(String word) {
        if(word.charAt(word.length()-1) == '.' || word.charAt(word.length()-1) == '!'
                || word.charAt(word.length()-1) == '?') {
            return true;
        }
        return false;
    }
    
    /**
     * Private static helper method that checks to see if a word contains
     * only alphabetical symbols.
     * @param word - the word to be checked
     * @return <code>true</code> if the word contains only alphabetical symbols;
     * otherwise <code>false</code>
     */
    private static boolean isAlphabeticalWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
