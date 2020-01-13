
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class IDDFS {

    public static void search(int[][] graph, int startNode, ArrayList<Integer> endNodes, LabyrinthReader lr)
    {
        for (int depthLimit = 0; depthLimit < graph.length; depthLimit++)
        {

            boolean[] marked = new boolean[graph.length];
            int[] from = new int[graph.length];


            Stack<Integer> stack = new Stack<Integer>();

            from[startNode] = -1;
            marked[startNode] = true;
            stack.push(startNode);

            while(!stack.isEmpty())
            {
                int curNode = stack.peek();

                if (endNodes.contains(curNode))
                {
                    System.out.println("Resitev IDDFS v vozliscu " + curNode);
                    //System.out.print("Pot: " + curNode);

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
                    System.out.println("Globina iskanja je " + depthLimit);


                    return;
                }

                boolean found = false;
                if (stack.size() <= depthLimit)
                {
                    // najdi neobiskanega naslednjika

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
                }

                if (!found)
                {
                    stack.pop();
                }
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        LabyrinthReader lr = new LabyrinthReader("labyrinths/labyrinth_8.txt");

        int[][] graph = lr.getAdjacencyMatrix();

        int startNode = lr.getStartNode();
        ArrayList<Integer> endNodes = lr.getEndNodes();

        IDDFS.search(graph, startNode, endNodes, lr);

    }

}