public class questions{
    

    public void numIslands_DFS(char[][] grid,int sr, int sc, int[][] dir){
        grid[sr][sc] = "0";

        for(int d =0;d<4;d++){
            int r = sr+dir[d][0];
            int c = sc+dir[d][1];

            if(r>=0 && c>=0 &&r<grid.length)
        }
    }


}