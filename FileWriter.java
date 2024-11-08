package FileLetterCounter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class fwrite{

    public static void WriteResultsIntoFile(int[] letter_counts, String inputFileName) {
        File res_file = FileCreator(inputFileName);
        if (res_file == null) {
            System.out.println("The program is terminated...");
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(res_file)) {

            for (int i = 0; i < letter_counts.length; i++) {
                char letter;
                if (i < 26) {
                    letter = (char) ('A' + i); // Заглавные буквы A-Z
                } else {
                    letter = (char) ('a' + (i - 26)); // Строчные буквы a-z
                }
                String res = letter + ": " + letter_counts[i] + "\n";
                // Преобразование строки в байты
                byte[] myBytes = res.getBytes();
                fos.write(myBytes);
            }

            System.out.println("Successfully wrote results to the file: " + res_file.getPath());
        } catch (IOException e) {
            System.out.println("Failed to write results into file: " + e.getMessage());
        }
    }

    public static File FileCreator(String inputFileName) {

        File file = new File("outputFiles/results_" + inputFileName);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            System.out.println("outputFiles directory does not exist");
            System.out.println("Please make sure it's located in the project folder");
            return null;
        }
        if (!parentDir.canWrite()) {
            System.out.println("You have no permission to write in 'outputFiles'");
            System.out.println("Please change the permissions to make it writable");
            return null;
        }
        return file; // Все проверки пройдены
    }
}