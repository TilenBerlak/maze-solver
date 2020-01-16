import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class AStar {

    public static void search(int[][] graph, int startNode, ArrayList<Integer> endNodes, int[] hCost, LabyrinthReader lr)
    {
        System.out.println("Iskanje A* v " + lr.getLabyrinthName());

        LinkedList<Integer> open = new LinkedList<Integer>();
        boolean[] closed = new boolean[graph.length];
        int[] from = new int[graph.length];

        int[] gScore = new int[graph.length];
        int[] fScore = new int[graph.length];

        for (int i = 0; i < gScore.length; i++)
        {
            gScore[i] = Integer.MAX_VALUE;
            fScore[i] = Integer.MAX_VALUE;
        }

        gScore[startNode] = 0;
        fScore[startNode] = hCost[startNode];
        from[startNode] = -1;

        open.add(startNode);

        int stPremikov = 0;
        while(!open.isEmpty())
        {
            int minVal = Integer.MAX_VALUE;
            int minPos = 0;
            int curNode = 0;

            for (int i = 0; i < open.size(); i++)
            {
                int node = open.get(i);
                if (fScore[node] < minVal)
                {
                    minVal = fScore[node];
                    minPos = i;
                    curNode = node;
                }
            }

            open.remove(minPos);
            closed[curNode] = true;

            if (endNodes.contains(curNode))
            {
                System.out.println("Resitev A* v vozliscu " + curNode);

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
                if (graph[curNode][nextNode] > 0 && !closed[nextNode])
                {
                    open.add(nextNode);
                    int dist = gScore[curNode] + graph[curNode][nextNode];

                    if (dist < gScore[nextNode])
                    {
                        from[nextNode] = curNode;
                        gScore[nextNode] = dist;
                        fScore[nextNode] = gScore[nextNode] + hCost[nextNode];
                    }
                }
            }

            stPremikov++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        LabyrinthReader lr = new LabyrinthReader("labyrinths/labyrinth_1.txt");

        int[][] graph = lr.getWeightGraph();

        int startNode = lr.getStartNode();
        ArrayList<Integer> endNodes = lr.getEndNodes();

        int[] hCost = lr.getHCost(graph);

        long startTime = System.nanoTime();
        AStar.search(graph, startNode, endNodes, hCost, lr);
        long executionTime = System.nanoTime() - startTime;

        long nanoToMilliseconds = executionTime / 1000000;
        System.out.println("Čas izvajanja: " + nanoToMilliseconds + " ms");
    }

}







