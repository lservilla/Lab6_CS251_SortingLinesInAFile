import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class LineWriter {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get(args[0]); //For source text file
        Path target = Paths.get(args[1]); //For target text file


        //List of lines from source code
        ArrayList<String> lines = new ArrayList<>();

        //Reading in lines from source text file
        try (BufferedReader reader = Files.newBufferedReader(source))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //Writing out new sorted lines, and without lines starting with #
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
