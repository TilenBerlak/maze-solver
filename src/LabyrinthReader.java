import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LabyrinthReader {

    private int[][] labyrinthMatrix;

    public LabyrinthReader(String file) throws FileNotFoundException {

        Scanner scr = new Scanner(new FileReader(file));

        String s = "";
        if(scr.hasNext())
            s = scr.nextLine();

        String[] sArray = s.split("\\,");

        int dimension = sArray.length;

        labyrinthMatrix = new int[dimension][dimension];

        for(int j = 0; j < labyrinthMatrix[0].length; j++) {

            labyrinthMatrix[0][j] = Integer.parseInt(sArray[j]);
        }

        int i = 1;
        while(scr.hasNextLine()) {

            String line = scr.nextLine();

            String[] lineArray = line.split("\\,");

            for(int j = 0; j < lineArray.length; j++) {
                labyrinthMatrix[i][j] = Integer.parseInt(lineArray[j]);
            }

            i++;
        }

        scr.close();

    }

    public int[][] getLabyrinth() {
        return labyrinthMatrix;
    }

}
