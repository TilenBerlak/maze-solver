import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LabyrinthReader {

    // Matrix used to store values from the labyrinth file.
    private int[][] labyrinthMatrix;

    public LabyrinthReader(String file) throws FileNotFoundException {

        Scanner scr = new Scanner(new FileReader(file));

        // Reading first line of the file to find dimensions of the matrix

        String s = "";
        if(scr.hasNext())
            s = scr.nextLine();

        String[] sArray = s.split("\\,");

        int dimension = sArray.length;

        labyrinthMatrix = new int[dimension][dimension];


        // Writing the first line to the matrix.

        for(int j = 0; j < labyrinthMatrix[0].length; j++) {

            labyrinthMatrix[0][j] = Integer.parseInt(sArray[j]);
        }


        // Reading other lines and writing them to the matrix.

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

    // Returns the labyrinth matrix.
    public int[][] getLabyrinth() {
        return labyrinthMatrix;
    }

    // Prints the labyrinth matrix.
    public void printLabyrinth() {

        for(int[] e1 : labyrinthMatrix) {
            for(int e2 : e1) {
                System.out.print(e2 + " ");
            }
            System.out.println();
        }

    }

}
