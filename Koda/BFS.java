package Koda;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;

class Node{
    int x,y, dist;

    Node(int x, int y, int dist){
        this.x=x;
        this.y=y;
		this.dist=dist;
		
		
    }
};

public class BFS {
    private static final int M = 11;
	private static final int N = 11;

	// Below arrays details all 4 possible movements from a cell
	private static final int row[] = { -1, 0, 0, 1 };
	private static final int col[] = { 0, -1, 1, 0 };
    /*
     * Vrednost | Pomen
     *    -1    | Zid
     *  >= 0    | Hodnik
     *    -2    | Začetno polje
     *    -3    | Ciljno polje
     */
     static boolean isValid(int mat[][], boolean visited[][], int row, int col)
	{
		return (row >= 0) && (row < M) && (col >= 0) && (col < N)
                        && mat[row][col] >= 0 && !visited[row][col] ;
    }
     static void algoritm(int mat[][], int i, int j, int x, int y)
	{
		// construct a matrix to keep track of visited cells
		boolean[][] visited = new boolean[M][N];

		// create an empty queue
		Queue<Node> q = new ArrayDeque<>();

		// mark source cell as visited and enqueue the source node
		visited[i][j] = true;
		q.add(new Node(i, j, 0));
		
		// stores length of longest path from source to destination
		int min_dist = Integer.MAX_VALUE;

		// run till queue is not empty
		while (!q.isEmpty())
		{
			// pop front node from queue and process it
			Node node = q.poll();
			
			
			// (i, j) represents current cell and dist stores its
			// minimum distance from the source
			i = node.x;
			j = node.y;
			int a=i;
			int b=j;
			int dist = node.dist;
			
			System.out.println("["+node.x+","+node.y+"]");
			// if destination is found, update min_dist and stop
			if (i == x && j == y)
			{
				
					
				min_dist = dist;
			
				break;
			}

			// check for all 4 possible movements from current cell
			// and enqueue each valid movement
			for (int k = 0; k < 4; k++)
			{
				// check if it is possible to go to position
				// (i + row[k], j + col[k]) from current position
				if (isValid(mat, visited, i + row[k], j + col[k]))
				{
					// mark next cell as visited and enqueue it
					visited[i + row[k]][j + col[k]] = true;
					q.add(new Node(i + row[k], j + col[k], dist+(node.x+node.y)));

				}
				
			}
		}

		if (min_dist != Integer.MAX_VALUE) {
			System.out.print("The shortest path from source to destination " +
									 "has length " + min_dist);
		}
		else {
			System.out.print("Destination can't be reached from given source");
		}
	}

    public static void main(String[] args) throws FileNotFoundException {
  
		int [][] mat={
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },
			{-1,-2, 4, 4,-1, 4, 4, 4, 4, 4,-1 },
			{-1,-1,-1, 4,-1, 4,-1,-1,-1, 4,-1 },
			{-1, 4, 4, 4, 4, 4,-1, 4, 4, 4,-1 },
			{-1, 4,-1, 4,-1, 4,-1, 4,-1, 4,-1 },
			{-1, 4,-1, 4,-1, 4,-1,-3,-1, 4,-1 },
			{-1, 4,-1, 4,-1, 4,-1,-1,-1, 4,-1 },
			{-1, 4, 4, 4,-1, 4, 4, 4, 4, 4,-1 },
			{-1,-1,-1, 4,-1, 4,-1, 4,-1, 4,-1 },
			{-1, 4, 4, 4, 4, 4, 4, 4,-1, 4,-1 },
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 }
		};
           
        
        int startX=1;
	int startY=1;
		        	 
        int konecX=7;
        int konecY=5;

        algoritm(mat, startX,startY, konecX, konecY);

/*
private int konec(int mat[][]){
	int[] koncna =new int[2];

		for (int j = 0; j < mat.length; j++) {
            for (int g = 0; g < mat[j].length; g++) {

                if (mat[j][g] == -3) {
                    koncna[0] = j;
					koncna[1] = g;			
				}			
			}
			
		}
		return koncna[0] & koncna[1];

}
	public int start (int mat[][]){
		int[] startIndecies = new int[2];

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {

                if(mat[i][j] == -2) {
                    startIndecies[0] = i;
                    startIndecies[1] = j;
                    
                }
			}	
	}
	return startIndecies[0] & startIndecies[1];
*/
}}
