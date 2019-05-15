
/**
 * @version date (CS_251_004, 2019-03-25)
 * @author Lasair Servilla
 */

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Collections;

/**
 * This program reads in a text file and then sorts the lines in 4 possible
 * ways. The first way is longest first, second is shortest first, then it can
 * sort it in lexicographic order and reverse lexicographic order.
 * If the line starts with a # the program will ignore it.
 */



public class LineSorter {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get(args[0]); //For source text file
        Path target = Paths.get(args[1]); //For target text file
        Path sort = Paths.get(args[2]); //For sort method

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

        String sort2 = sort.toString(); //Path to string, for use in switch


        //Removing all lines that begin with #
        Iterator itr = lines.iterator();
        while(itr.hasNext()){
            String singleLine = (String)itr.next();
            if(singleLine.charAt(0)== '#'){
                itr.remove();
            }
        }

//Switch statement for the possible ways to sort
        switch (sort2.toUpperCase()){
            //Sort with shortest line first
            case "SHORTESTFIRST":
                Collections.sort(lines);
                lines.sort((s1, s2) -> s1.length()-s2.length());

                break;

                //Sort with longest line first
            case "LONGESTFIRST":
                Collections.sort(lines, Collections.reverseOrder());
                lines.sort((s1, s2) -> s2.length()-s1.length());

                break;

                //Sort lexicographically
            case "LEXICOGRAPHIC":
                Collections.sort(lines);
                lines.sort((s1, s2) -> s1.compareTo(s2));

                break;

            //Sort in reverse lexicographic order
            case "REVERSE":
                Collections.sort(lines,Collections.reverseOrder());
                lines.sort((s1, s2) -> s2.compareTo(s1));

                break;

            default:
                System.out.println("Selected sorting method does not exist.");
                System.exit(0);


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
