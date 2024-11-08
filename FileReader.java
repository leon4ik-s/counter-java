package FileLetterCounter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class fread {
    public static File OpenInputFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide a txt file-name to read from: ");
        String fileName = scanner.nextLine();

        File file = new File("inputFiles/" + fileName);

        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            System.out.println("'inputFiles' directory does not exist");
            System.out.println("Please make sure it's located in the project folder");
            return null;
        }
        if (!parentDir.canRead()) {
            System.out.println("You have no permission to read from 'inputFiles'");
            System.out.println("Please change the permissions to make it readable");
            return null;
        }

        // Проверка файла
        if (!file.exists()) {
            System.out.println("The file '" + fileName + "' does not exist");
            System.out.println("Make sure your file is in the inputFiles/ directory of the project");
            return null;
        } else if (!file.isFile() || !file.getName().endsWith(".txt")) {
            System.out.println("Invalid file. Please ensure it's a file and has a .txt extension");
            return null;
        } else if (!file.canRead()) {
            System.out.println("You have no permission to read " + fileName);
            System.out.println("Please change the permissions to make it readable");
            return null;
        } else {
            System.out.println("The file " + fileName + " exists!");
            System.out.println("Counting has been started!");
            return file;
        }
    }

    public static int[] CountLettersFromInputFile(File file) {
        int[] letter_counts = new int[52]; // Для букв A-Z и a-z

        try (FileInputStream fis = new FileInputStream(file)) {
            int c;
            while ((c = fis.read()) != -1) {
                char ch = (char) c;
                if (ch >= 'A' && ch <= 'Z') {
                    letter_counts[ch - 'A']++;
                } else if (ch >= 'a' && ch <= 'z') {
                    letter_counts[26 + ch - 'a']++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return letter_counts;
    }
}