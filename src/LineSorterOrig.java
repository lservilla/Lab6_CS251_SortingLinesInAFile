import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
/**
 * A CLI class to read in a text file, sort it based on length of lines, and
 * lexicographically if length of the two are equal. It will then write the
 * sorted results to a new file.
 * @author Michael Servilla
 * @version date 2017-02-28
 */
public class LineSorterOrig {
    /**
     * Main method in the class to run program.
     * @param args The first string argument is the name of the text file to
     *             read, and the second is the file to be written to.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path source = Paths.get(args[0]); //For source text file.
        Path target = Paths.get(args[1]); //For target text file
        ArrayList<String> lines = new ArrayList<>(); //New array list to store
        //and sort file.
        /**
         * Try/Catch block using resources for reading in text file.
         */
        try (BufferedReader reader = Files.newBufferedReader(source))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //Sort methods, the second using Lambda expressions.
        Collections.sort(lines);
        lines.sort((s1, s2) -> s1.length() - s2.length());

        /**
         * Try/Catch block using resources for writing sorted text file.
         */
        try (BufferedWriter writer = Files.newBufferedWriter(target))
        {
            Iterator<String> iterator = lines.iterator();

            while (iterator.hasNext()) {
                String s = iterator.next();
                writer.write(s, 0, s.length());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}