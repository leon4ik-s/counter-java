package FileLetterCounter;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Run();
    }

    public static void Run() {
        File readFile = fread.OpenInputFile();
        if (readFile == null) {
            System.out.println("The program is terminated...");
            return;
        }
        int[] letter_counts = fread.CountLettersFromInputFile(readFile);
        fwrite.WriteResultsIntoFile(letter_counts, readFile.getName());
    }
}