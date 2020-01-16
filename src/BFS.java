import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void search(int[][] graph, int startNode, ArrayList<Integer> endNodes, LabyrinthReader lr)
    {
        System.out.println("Iskanje BFS v " + lr.getLabyrinthName());

        boolean[] marked = new boolean[graph.length];
        int[] from = new int[graph.length];

        Queue<Integer> queue = new LinkedList<Integer>();

        marked[startNode] = true;
        from[startNode] = -1;

        queue.add(startNode);

        int stPremikov = 0;
        while(!queue.isEmpty())
        {
            int curNode = queue.remove();

            if (endNodes.contains(curNode))
            {

                int vsota = 0;

                while (true)
                {
                    curNode = from[curNode];
                    if (curNode != -1) {
                        if(lr.getNodeWeight(curNode) != -2) {
                            System.out.print(lr.getNodeWeight(curNode) + "+");
                            vsota += lr.getNodeWeight(curNode);
                        }
                    } else
                        break;
                }

                System.out.println("=" + vsota);

                System.out.println();
                System.out.println("Statistika");
                System.out.println("Cena najdene poti: " + vsota);
                System.out.println("Število premikov: " + stPremikov);

                return;
            }

            for (int nextNode = 0; nextNode < graph[curNode].length; nextNode++)
            {
                if (graph[curNode][nextNode] == 1 && !marked[nextNode])
                {
                    marked[nextNode] = true;
                    from[nextNode] = curNode;
                    queue.add(nextNode);
                }
            }

            stPremikov++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        LabyrinthReader lr = new LabyrinthReader("labyrinths/labyrinth_1.txt");

        int[][] graph = lr.getAdjacencyMatrix();

        int startNode = lr.getStartNode();
        ArrayList<Integer> endNodes = lr.getEndNodes();

        long startTime = System.nanoTime();
        BFS.search(graph, startNode, endNodes, lr);
        long executionTime = System.nanoTime() - startTime;

        long nanoToMilliseconds = executionTime / 1000000;
        System.out.println("Čas izvajanja: " + nanoToMilliseconds + " ms");

    }

}