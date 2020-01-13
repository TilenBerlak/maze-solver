
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSF {
	 /*
     * Vrednost | Pomen
     *    -1    | Zid
     *  >= 0    | Hodnik
     *    -2    | Začetno polje
     *    -3    | Ciljno polje
     */
	public static void search(int[][] graph, int startNode, ArrayList<Integer> endNodes, LabyrinthReader lr )
	{
		boolean[] marked = new boolean[graph.length];
		int[] from = new int[graph.length];
		
		
		Stack<Integer> stack = new Stack<Integer>();
        
        from[startNode] = -1;
		marked[startNode] = true;
		stack.push(startNode);
	
		System.out.println("Dajem v vrsto vozlisce " + startNode);
		
		while(!stack.isEmpty())
		{
			int curNode = stack.peek();
			System.out.println("Odstranjujem iz vrste vozlisce " + curNode);
			
			if (endNodes.contains(curNode))
			{
				System.out.println("Resitev BFS v vozliscu " + curNode);
				System.out.print("Pot: " + curNode);
				
				while (true)
				{
					curNode = from[curNode];
					if (curNode != -1)
						System.out.print(" <-- " + curNode);
					else
						break;
				}
				
				return;
			}
			boolean found = false;
			for (int nextNode = 0; nextNode < graph[curNode].length; nextNode++)
			{
				if (graph[curNode][nextNode] == 1 && !marked[nextNode])
				{
					marked[nextNode] = true;
					from[nextNode] = curNode;
					stack.push(nextNode);
					
					System.out.println("Dajem v vrsto vozlisce " + nextNode);
                    found =true;
                    break;
                }
            }
            if(!found)
                stack.pop();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
        LabyrinthReader lr = new LabyrinthReader("Matrike/labyrinth_1.txt");

        int[][] graph = lr.getAdjacencyMatrix();

        int startNode = lr.getStartNode();
        ArrayList<Integer> endNodes = lr.getEndNodes();
		
		
		BSF.search(graph, startNode, endNodes,lr);

	}

