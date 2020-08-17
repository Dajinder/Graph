import java.util.ArrayList;
import java.util.Arrays;
public class graph{
    public static class Edge{
        int v;
        int w;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w)); // to make bidirectional
    }

    public static void display(){
        for(int i=0;i<N;i++){
            System.out.print(i+" -> ");
            for(Edge e : graph[i]){
                System.out.print("("+e.v+", "+e.w+")");
            }
            System.out.println();
        }
    }

    public static void constructGraph(){
        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<Edge>();
        }

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        // display();
    }

    public static int findEdge(int u, int v){
        for(int i=0;i<graph[u].size();i++){
            Edge e = graph[u].get(i);
            if(e.v == v){
                return i;
            }
        }
        return -1;
    }
    public static void removeEdge(int u, int v){
        int idx1 = findEdge(u, v);
        graph[u].remove(idx1);

        int idx2 = findEdge(v, u);
        graph[v].remove(idx2);
    }

    


    public static boolean hasPath(int src, int dest, boolean[] visited){
        if(src == dest){
            return true;
        }

        visited[src] = true;
        for(Edge e : graph[src]){
            if(visited[e.v]!=true){
                boolean hasnbrPath = hasPath(e.v,dest,visited);
                if(hasnbrPath == true){
                    return true;
                }
            }
        }

        return false;
    }



    public static void allPath(int src, int dest, boolean[] vis, String psf){
        if(src == dest){
            System.out.println(psf + src);
            return ;
        }

        vis[src] = true;

        for(Edge e: graph[src]){
            if(vis[e.v]!=true){
                allPath(e.v,dest,vis,psf+e.v);
            }
        }

        vis[src] = false;

    }

    public static void largestPath(int src, int dest, boolean[] vis, int wsf, String psf){
        int max = (int)-1e8;
        
        if(src == dest){
            if(wsf > max){
                max = wsf;
            }   
            System.out.println(max);
            return ;
        }
    
        vis[src] = true;
    
        for(Edge e: graph[src]){
            if(vis[e.v]!=true){
                largestPath(e.v, dest, vis, wsf+e.w, psf+e.v);
            }
        }
    
        vis[src] = false;
        
    }

    public static void gcc()

    public static int hamiltoninanPathAndCycle(int src, int origsrc, int edgeCount, boolean[] vis, String ans){
        if(edgeCount == N-1){
            int idx = findEdge(src, origsrc);
            if(idx!=-1){
                System.out.println("Cycle : "+ src + ans);
            }else{
                System.out.println("path : "+ src + ans);
            }

        }
        

        
        vis[src] = true;
        int count = 0;
        for(Edge e:graph[src]){
            if(vis[e.v]!=true){
                count += hamiltoninanPathAndCycle(e.v, origsrc, edgeCount+1, vis, ans + src + " ");
            }
        }

        vis[src] = false;
        
        return count;
    }

    public static void GCC_DFS(int src, boolean [] vis){
        vis[src] = true;
        for(Edge e:graph[src]){
            if(vis[e.v]!=true){
                GCC_DFS(e.v, vis);
            }
        }
    }

    public static int GCC(){
        boolean[]vis = new boolean[N];
        int count = 0;
        for(int i=0;i<N;i++){
            if(vis[i]!=true){
                GCC_DFS(i, vis);
                count++;    
            }            
        }
        return count;
    }
    
 

    
    public static void solve(){ 
        
        // removeEdge(1, 2);
        // display();

        boolean[] vis = new boolean[N];

        // System.out.println(hasPath(0, 6, vis));
        
        
        // int src= 0;
        // int dest = 6;
        // allPath(src, dest, vis , src + "");

        // largestPath(src, dest, vis, 0, src + "");


        // System.out.println(hamiltoninanPathAndCycle(0, 0, 0, vis, "")); 

        // System.out.println(GCC()); 
    }
    public static void main(String[] args){
        constructGraph();
 
        solve();
    }
}