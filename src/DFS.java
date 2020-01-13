
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class DFS {

    public static void search(int[][] graph, int startNode, ArrayList<Integer> endNodes, LabyrinthReader lr)
    {
        boolean[] marked = new boolean[graph.length];
        int[] from = new int[graph.length];

        Stack<Integer> stack = new Stack<Integer>();

        from[startNode] = -1;
        marked[startNode] = true;
        stack.push(startNode);

        System.out.println("Iskanje DFS");
        int stPremikov = 0;
        while(!stack.isEmpty())
        {
            stPremikov++;

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

                    }

                    else
                        break;
                }

                System.out.println("=" + vsota);
                System.out.println("Cena najdene poti: " + vsota);
                System.out.println("Število premikov: " + stPremikov);

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
                    break;
                }
            }

            if (!found)
            {
                stack.pop();
                //System.out.println("Odstranjum s sklada vozlisce " + curNode);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        LabyrinthReader lr = new LabyrinthReader("labyrinths/labyrinth_5.txt");

        int[][] graph = lr.getAdjacencyMatrix();

        int startNode = lr.getStartNode();
        ArrayList<Integer> endNodes = lr.getEndNodes();

        DFS.search(graph, startNode, endNodes, lr);

    }

}