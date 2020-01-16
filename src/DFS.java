
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class DFS {

    public static void search(int[][] graph, int startNode, ArrayList<Integer> endNodes, LabyrinthReader lr)
    {
        System.out.println("Iskanje DFS v " + lr.getLabyrinthName());

        boolean[] marked = new boolean[graph.length];
        int[] from = new int[graph.length];

        Stack<Integer> stack = new Stack<Integer>();

        from[startNode] = -1;
        marked[startNode] = true;
        stack.push(startNode);

        int globina = 0;
        int maxGlobina = globina;

        int stPremikov = 0;
        while(!stack.isEmpty())
        {
            int curNode = stack.peek();

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
                System.out.println("Največja globina: " + maxGlobina);

                return;
            }

            // najdi neobiskanega naslednjika
            boolean found = false;
            for (int nextNode = 0; nextNode < graph[curNode].length; nextNode++)
            {
                if (graph[curNode][nextNode] == 1 && !marked[nextNode])
                {
                    marked[nextNode] = true;
                    from[nextNode] = curNode;
                    stack.push(nextNode);

                    found = true;
                    globina++;
                    break;
                }
            }

            if(globina > maxGlobina)
                maxGlobina = globina;

            if (!found)
            {
                stack.pop();
                globina--;
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
        DFS.search(graph, startNode, endNodes, lr);
        long executionTime = System.nanoTime() - startTime;

        long nanoToMilliseconds = executionTime / 1000000;
        System.out.println("Čas izvajanja: " + nanoToMilliseconds + " ms");
    }

}