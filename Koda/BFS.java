package Koda;
/*
Napaka je v vrstici 64, ker sesteva kolilko node je prehodilo,
namesto vrednosti polja.
*/
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
                        && mat[row][col] != -1 && !visited[row][col] ;
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
			int dist = node.dist;
			
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
					q.add(new Node(i + row[k], j + col[k], dist + 1));
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
        LabyrinthReader labirint = new LabyrinthReader("Matrike\\labyrinth_1.txt");
        int[][] mat = labirint.getLabyrinth();
        
        int[] start=labirint.getStartPoint();
        int startX=start[0];
        int startY=start[1];
        
        int[][] konec=labirint.getEndPoints();
        int konecX=konec[0][0];
        int konecY=konec[0][1];

        algoritm(mat, startX,startY, konecX, konecY);
/*



        for(int i=0;i<lab.length;i++){
            for(int j=0;j<lab[i].length;j++){
                if(lab[i][j]==-1 ){
                    System.out.println("*");
                }
            }
        }
   
    }
   
    void najdena_pot_do_cilja() {

    }

    void cena_najdene_poti() {

    }

    void stevilo_premikov_na_najdeni_poti() {

    }*/
    
}
}