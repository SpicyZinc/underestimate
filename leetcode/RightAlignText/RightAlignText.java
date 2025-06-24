/*
Pika hackranker
write code to rightAlign
words separated by space, if a word is too long, break it
and also pad left empty with spaces.

idea:
current word + next word, 不要加上length
有长度 加一个空格
*/

import java.util.*;

public class RightAlignText {
    public static void main(String[] args) {
        // The sentence that includes the longest word about the disease
        List<String> words = List.of(
            "The", "disease", "pneumonoultramicroscopicsilicovolcanoconiosis", 
            "is", "caused", "by", "inhaling", "very", "fine", "silica", "particles."
        );
        int maxWidth = 30;  // Set maxWidth for the sentence

        RightAlignText eg = new RightAlignText();
        List<String> rightAlignedText = eg.rightAlign(maxWidth, words);

        // Print the result
        for (String line : rightAlignedText) {
            System.out.println("'" + line + "'");
        }
    }

    public List<String> rightAlign(int maxWidth, List<String> words) {
        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            // If the word is longer than the maxWidth, break it into smaller parts
            if (word.length() > maxWidth) {
                // Break the word and add each part
                int index = 0;
                while (index < word.length()) {
                    int end = Math.min(index + maxWidth, word.length());
                    String part = word.substring(index, end);
                    result.add(rightAlignLine(maxWidth, part));
                    // update index
                    index = end;
                }
            } else {
                // Check if the word can fit in the current line
                if (currentLine.length() + word.length() <= maxWidth) {
                    if (currentLine.length() > 0) {
                        // 如果有 先加一个space
                        currentLine.append(" ");
                    }
                    currentLine.append(word);
                } else {
                    // Right-align the current line and add to result
                    result.add(rightAlignLine(maxWidth, currentLine.toString()));
                    currentLine = new StringBuilder(word);  // Start a new line with the current word
                }
            }
        }

        // Add the last line to the result (if it's not empty)
        if (currentLine.length() > 0) {
            result.add(rightAlignLine(maxWidth, currentLine.toString()));
        }

        return result;
    }

    // Helper method to right-align a line of text
    private String rightAlignLine(int maxWidth, String line) {
        int padding = maxWidth - line.length();
        StringBuilder rightAlignedLine = new StringBuilder();

        // Add spaces at the beginning to right-align the text
        for (int i = 0; i < padding; i++) {
            rightAlignedLine.append(" ");
        }

        rightAlignedLine.append(line);  // Add the original line after the spaces
        return rightAlignedLine.toString();
    }
}
