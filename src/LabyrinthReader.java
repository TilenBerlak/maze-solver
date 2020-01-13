/**
 * The LabyirinthReader program that reads a text file
 * with labyrinth configuration and stores the read data
 * in the matrix.  It can return the matrix, display it,
 * or find specific values.
 *
 * @author  Tilen Berlak
 * @version 1.0
 * @since   02-1-2020
 *
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LabyrinthReader {

    // Matrix used to store values from the labyrinth file.
    private int[][] labyrinthMatrix;


    /**
     * Reads the given labyrinth configuration text file
     * and saves the integer values in a 2d array representing
     * a matrix.
     * @param file This is the labyrinth configuration file.
     */
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

    /**
     * This method is used to return the labyrinth configuration
     * in a matrix format.
     * @return int[][] This returns the labyrinthMatrix.
     */
    public int[][] getLabyrinth() {
        return labyrinthMatrix;
    }

    /**
     * This method is used to display the labyrinth configuration
     * in a matrix format.
     * @return Nothing.
     */
    public void printLabyrinth() {

        for(int[] e1 : labyrinthMatrix) {
            for(int e2 : e1) {
                System.out.print(e2 + " ");
            }
            System.out.println();
        }

    }

    /**
     * This method is used to find and return the indecies
     * of the starting position (-2) in the labyrinth matrix.
     * There is only one to find.
     * @return int[] This returns the array with two indecies.
     */
    public int[] getStartPoint() {

        int[] startIndecies = new int[2];

        for(int i = 0; i < this.labyrinthMatrix.length; i++) {
            for(int j = 0; j < this.labyrinthMatrix[i].length; j++) {

                if(this.labyrinthMatrix[i][j] == -2) {
                    startIndecies[0] = i;
                    startIndecies[1] = j;
                    return startIndecies;
                }
            }
        }

        return startIndecies;

    }

    public int getStartNode() {

        int curNode = 0;
        for(int i = 0; i < this.labyrinthMatrix.length; i++) {
            for (int j = 0; j < this.labyrinthMatrix[i].length; j++) {

                if(this.labyrinthMatrix[i][j] == -2) {
                    return curNode;
                }

                curNode++;
            }
        }

        return -1;
    }

    public ArrayList<Integer> getEndNodes() {

        ArrayList<Integer> endNodes = new ArrayList<Integer>();

        int curNode = 0;
        for(int i = 0; i < this.labyrinthMatrix.length; i++) {
            for(int j = 0; j < this.labyrinthMatrix[i].length; j++) {

                if(this.labyrinthMatrix[i][j] == -3) {
                    endNodes.add(curNode);
                }

                curNode++;
            }
        }

        return endNodes;
    }

    /**
     * This method is used to find and return the indecies
     * of the end points (-3) in the labyrinth matrix.
     * There can be more than one.
     * @return int[][] This returns the 2d array with indecies of end points.
     */
    public int[][] getEndPoints() {

        int stevec = 0;

        for (int i = 0; i < this.labyrinthMatrix.length; i++) {
            for (int j = 0; j < this.labyrinthMatrix[i].length; j++) {

                if (this.labyrinthMatrix[i][j] == -3) {
                    stevec++;
                }
            }
        }

        int[][] endIndecies = new int[stevec][2];

        stevec = 0;
        for (int i = 0; i < this.labyrinthMatrix.length; i++) {
            for (int j = 0; j < this.labyrinthMatrix[i].length; j++) {

                if (this.labyrinthMatrix[i][j] == -3) {
                    endIndecies[stevec][0] = i;
                    endIndecies[stevec][1] = j;
                    stevec++;
                }
            }
        }

        return endIndecies;

    }

    public int[][] getAdjacencyMatrix() {

        int numOfNodes = this.labyrinthMatrix.length * this.labyrinthMatrix.length;

        int[][] graph = new int[numOfNodes][numOfNodes];

        int curNode = 0;
        for(int i = 0; i < this.labyrinthMatrix.length; i++) {

            for(int j = 0; j < this.labyrinthMatrix[i].length; j++) {

                if(this.labyrinthMatrix[i][j] != -1) {

                    if(i == 0) {

                        if(j == 0) {

                            graph = lookDown(graph, i, j, curNode);
                            graph = lookRight(graph, i, j, curNode);

                        } else if( j == this.labyrinthMatrix[i].length - 1) {

                            graph = lookLeft(graph, i, j, curNode);
                            graph = lookDown(graph, i, j, curNode);

                        } else {

                            graph = lookLeft(graph, i, j, curNode);
                            graph = lookDown(graph, i, j, curNode);
                            graph = lookRight(graph, i, j, curNode);

                        }
                    } else if( i == this.labyrinthMatrix.length - 1) {

                        if(j == 0) {

                            graph = lookUp(graph, i, j, curNode);
                            graph = lookRight(graph, i, j, curNode);

                        } else if( j == this.labyrinthMatrix[i].length - 1) {

                            graph = lookLeft(graph, i, j, curNode);
                            graph = lookDown(graph, i, j, curNode);

                        } else {

                            graph = lookLeft(graph, i, j, curNode);
                            graph = lookDown(graph, i, j, curNode);
                            graph = lookRight(graph, i, j, curNode);

                        }

                    } else if(j == 0) {

                        graph = lookUp(graph, i, j, curNode);
                        graph = lookDown(graph, i, j, curNode);
                        graph = lookRight(graph, i, j, curNode);

                    } else if(j == this.labyrinthMatrix[i].length) {

                        graph = lookUp(graph, i, j, curNode);
                        graph = lookLeft(graph, i, j, curNode);
                        graph = lookDown(graph, i, j, curNode);

                    } else {

                        graph = lookUp(graph, i, j, curNode);
                        graph = lookLeft(graph, i, j, curNode);
                        graph = lookDown(graph, i, j, curNode);
                        graph = lookRight(graph, i, j, curNode);

                    }
                }

                curNode++;
            }
        }

        return graph;
    }

    private int[][] lookUp(int[][] graph, int i, int j, int curNode) {

        // up
        if(this.labyrinthMatrix[i-1][j] >= 0 || this.labyrinthMatrix[i-1][j] == -3) {

            graph[curNode][curNode - this.labyrinthMatrix.length] = 1;

        }

        return graph;
    }

    private int[][] lookDown(int[][] graph, int i, int j, int curNode) {

        // down
        if(this.labyrinthMatrix[i+1][j] >= 0 || this.labyrinthMatrix[i+1][j] == -3) {

            graph[curNode][curNode + this.labyrinthMatrix.length] = 1;

        }

        return graph;
    }

    private int[][] lookLeft(int[][] graph, int i, int j, int curNode) {

        // left
        if(this.labyrinthMatrix[i][j-1] >= 0 || this.labyrinthMatrix[i][j-1] == -3) {

            graph[curNode][curNode - 1] = 1;

        }

        return graph;
    }

    private int[][] lookRight(int[][] graph, int i, int j, int curNode) {

        // right
        if(this.labyrinthMatrix[i][j+1] >= 0 || this.labyrinthMatrix[i][j+1] == -3) {

            graph[curNode][curNode + 1] = 1;

        }

        return graph;

    }

    public int getNodeWeight(int node) {

        int curNode = 0;
        for (int i = 0; i < this.labyrinthMatrix.length; i++) {
            for (int j = 0; j < this.labyrinthMatrix[i].length; j++) {

                if(curNode == node) {

                    return this.labyrinthMatrix[i][j];

                }

                curNode++;

            }
        }

        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {

        LabyrinthReader lr = new LabyrinthReader("labyrinths/labyrinth_1.txt");

        int[][] graph = lr.getAdjacencyMatrix();

        for(int[] e1 : graph) {
            for(int e2 : e1) {
                System.out.print(e2 + " ");
            }
            System.out.println();

        }

    }



}
