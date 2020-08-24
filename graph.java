import java.util.Stack;
import java.util.PriorityQueue;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.HashSet;
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
    // static int N = 8; // use this for topological sort
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w)); // to make bidirectional // comment this for topo sort to make graph unidirectional
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
        addEdge(0, 3, 40);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        // addEdge(2, 5, 10);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);


        //==============Use below set of edges for topo sort====================

        // addEdge(0,1,10);
        // addEdge(0,6,10);
        // addEdge(1,2,10);
        // addEdge(2,3,10);
        // addEdge(5,3,10);
        // addEdge(4,5,10);
        // addEdge(7,4,10);
        // addEdge(7,6,10);
        



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


    public static void gcc(){
        
        ArrayList<ArrayList<Integer>>comps = new ArrayList<>();
        boolean[] visited = new boolean[N];
        for(int i = 0;i<N;i++){
            if(visited[i]!=true){
                ArrayList<Integer>comp = new ArrayList<>();
                drawtreeComponents(i,visited,comp);
                comps.add(comp);
            }
        }
        System.out.println(comps);
        
    }
    

    public static void drawtreeComponents(int src, boolean[] visited,ArrayList<Integer>comp){
        visited[src] = true;
        comp.add(src);
        for(Edge e : graph[src]){
            if(visited[e.v]!=true){
                drawtreeComponents(e.v,visited,comp);
            }
        }
    }

    public static void isConnected(){
        
        ArrayList<ArrayList<Integer>>comps = new ArrayList<>();
        boolean[] visited = new boolean[N];
        for(int i = 0;i<N;i++){
            if(visited[i]!=true){
                ArrayList<Integer>comp = new ArrayList<>();
                drawtreeComponents(i,visited,comp);
                comps.add(comp);
            }
        }
        System.out.println(comps.size()==1);
        
    }


    public static void NoOfIslands(int[][] arr){
        
        int count = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for(int i=0;i<arr.length;i++){
            for(int j = 0; j<arr[i].length;j++){
                if(arr[i][j] ==0 && visited[i][j] !=true){
                    drawComponents(arr,i,j,visited);
                    count++;
                }
            }
        }

        System.out.print(count);
    }

    public static void drawComponents(int[][] arr, int i, int j, boolean[][] visited){
        if(i<0 || j<0 || i>=arr.length || j>=arr[0].length || arr[i][j] == 1 || visited[i][j] == true){
            return ;
        }
        
        visited[i][j] = true;

        drawComponents(arr,i,j-1,visited);
        drawComponents(arr,i-1,j,visited);
        drawComponents(arr,i+1,j,visited);
        drawComponents(arr,i,j+1,visited);
    }


    public static void hamiltonian(int src,HashSet<Integer> visited, String psf, int osrc){
        if(visited.size() == graph.length-1){
            System.out.print(psf);
            boolean closingEdgFound = false;
            for(Edge e:graph[src]){
                if(e.v == osrc){
                    closingEdgFound = true;
                    break;
                }
                
            }

            if(closingEdgFound == true){
                System.out.println(" : Hcycle");
            }else{
                System.out.println(" : Hpath");
            }

            return;
        }
        int count = 0;
        visited.add(src);
        for(Edge e:graph[src]){
            if(visited.contains(e.v)!=true){
                hamiltonian(e.v,visited,psf+e.v,osrc);
                count++;
            }
        }
        visited.remove(src);
        // System.out.println(count);
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

        // gcc();

        // isConnected();

        // int[][] arr = {{0,0,1,1,1,1},{0,0,1,1,1,1},{1,1,0,0,1,0},{1,1,1,0,1,0},{1,1,1,0,1,0},{1,1,1,1,1,0}};
        // NoOfIslands(arr);
        
        // boolean[] visited = new boolean[N];

        // HashSet<Integer> visited = new HashSet<>();
        // int src=0;
        // hamiltonian(src,visited,src+"",src); 
    }


    public static class Pair{
        int v;
        String psf;

        Pair(int v, String psf){
            this.v = v;
            this.psf = psf;
        }
        
    }

    public static void printBfs(int src, String psf){
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(src,src+""));
        boolean[] visited = new boolean[N];
        while(queue.size()!=0){
            
            // remove
            Pair rem = queue.removeFirst();
            
            // mark*
            if(visited[rem.v]==true){
                continue;
            }else{
                visited[rem.v] = true;
            }

            //work
            System.out.println(rem.v+" @ "+rem.psf);
            
            // add*
            for(Edge e: graph[rem.v]){
                if(visited[e.v]==false){
                    queue.add(new Pair(e.v,rem.psf+e.v));
                }
            }
        }
    }

    public static void cycle(){
        boolean[] visited = new boolean[N];
        for(int i=0;i<N;i++){
            if(visited[i]==false){
                boolean cyclePresent = isCycle(i,visited);
                if(cyclePresent){
                    System.out.println(true);
                    return;
                }
            }
        }
        System.out.println(false);
    }
    public static boolean isCycle(int src, boolean[] visited){
        ArrayDeque<Pair> que = new ArrayDeque<>();
        que.add(new Pair(src,src+""));

        while(que.size()>0){
            // remove
            Pair rem = que.removeFirst();
            // mark
            if(visited[rem.v]!=false){
                return true;
            }              
            visited[rem.v] = true;
            // add unvisited neighbours

            for(Edge e:graph[rem.v]){
                if(visited[e.v]!=true){
                    que.add(new Pair(e.v,rem.psf+e.v));
                }
            }
        }
        return false;
    }
    
    public static void bfs(){
        // int src = 2;
        // printBfs(src,src+"");

        cycle();
    }

    public static class Pair_2{
        int v;
        String psf;
        int level;

        Pair_2(int v, String psf, int level){
            this.v = v;
            this.psf = psf;
            this.level = level;
        }
    }

    public static void bipartite(){
        int[] visited = new int[N];
        Arrays.fill(visited,-1);
        for(int i=0;i<N;i++){
            if(visited[i] == -1){
                boolean isComponentBipartite = compBipartite(i,visited);
                if(isComponentBipartite == false){
                    System.out.print(false);
                    return;
                }
            }
        }

        System.out.print(true);
    }

    public static boolean compBipartite(int src, int[] visited){
        ArrayDeque<Pair_2> que = new ArrayDeque<>();
        que.add(new Pair_2(src,src+"",0));

        while(que.size()>0){
            // remove

            Pair_2 rem = que.removeFirst();

            // mark

            if(visited[rem.v]!=-1){
                // to do some work
                if(rem.level != visited[rem.v]){
                    return false;
                }  
            }else{
                visited[rem.v] = rem.level;
            }
            
            // add value
            for(Edge e : graph[rem.v]){
                if(visited[e.v]==-1){
                    que.add(new Pair_2(e.v,rem.psf+e.v,rem.level+1));
                }
            }
        }
        return true;
    }


    
    
    

    // public static void perfectFriends(int n, int k){
    //     ArrayList<Pair_3>[] newgraph = new ArrayList[n];
    //     for(int i=0;i<n;i++){
    //         newgraph[i] = new ArrayList<>();
    //     }


    //     newgraph[u].add(new Edge(v,w));
    //     graph[v].add(new Edge(u,w));




    //     ArrayList<ArrayList<Integer>> comp = new ArrayList<>();
    //     boolean[] visited = new boolean[n];
    //     for(int i=0;i<n;i++){
    //         if(visited[i] == false){
    //             graphcomp(i,visited,comp);
    //             comps.add(comp);
    //         }
    //     }
    //     // System.out.println(comps);

    //     int paircount = 0;
    //     for(int i=0;i<comps.size();i++){
    //         for(int j=i+1;j<comps.size();j++){
    //             int count = comps.get(i).size()*comps.get(j).size();
    //             pairscount += count;
    //         }
    //     }

    //     System.out.println(count);
    // }

    // public static void graphcomp(int src,boolean[] visited, ArrayList<Integer>comp){
    //     visited[src] = true;
    //     comp.add(src);
    //     for(Pair_3 e : newgraph[src]){
    //         if(visited[e.n]!=true){
    //             graphcomp(e.n,visited,comp);
    //         }
    //     }

    //     visited[src] = false;
    // }


    public static class Pair_3{
        int v;
        int time;

        Pair_3(int v, int time){
            this.v = v;
            this.time = time;
        }

    }

    public static void spredInfection(int src, int t){
        ArrayDeque<Pair_3> que = new ArrayDeque<>();
        que.add(new Pair_3(src,1));
        int[] visited = new int[N];
        int count = 0;
        while(que.size()!=0){
            // remove
            Pair_3 rem = que.removeFirst();

            // mark
            if(visited[rem.v]>0){
                continue;
            }
            visited[rem.v] = rem.time;
            if(rem.time>t){
                break;
            }
            count++;

            // add

            for(Edge e:graph[rem.v]){
                if(visited[e.v] == 0){
                    que.add(new Pair_3(e.v,rem.time+1));
                }
            }
        }

        System.out.println(count);
    }

    public static class Pair_4 implements Comparable<Pair_4>{
        int v ;
        String psf;
        int wsf;
        Pair_4(int v,String psf, int wsf){
            this.v = v;
            this.psf = psf;
            this.wsf = wsf;
        }

        public int compareTo(Pair_4 o){
            return this.wsf - o.wsf;
        }
    }
    
    public static void shortestweigthdist(int src){ //djkstra's algorithm
        PriorityQueue<Pair_4> pq = new PriorityQueue<>();
        pq.add(new Pair_4(src,src+"",0));
        boolean[] visited = new boolean[N];
        while(pq.size()!=0){
            // remove
            Pair_4 rem = pq.remove();

            // mark
            if(visited[rem.v] == true){
                continue;
            }
            visited[rem.v] = true;

            // work
            System.out.println(rem.v + " via " + rem.psf + " @ "+ rem.wsf);
            
            // add
            for(Edge e:graph[rem.v]){
                if(visited[e.v]==false){
                    pq.add(new Pair_4(e.v, rem.psf+e.v, rem.wsf+e.w));
                }
            }
        }

    }

    public static class Pair_5 implements Comparable<Pair_5>{
        int v;
        int acqv;
        int wt;

        Pair_5(int v, int acqv, int wt){
            this.v = v;
            this.acqv = acqv;
            this.wt = wt;
        }

        public int compareTo(Pair_5 o){
            return this.wt - o.wt;
        }

    }


    public static void minimumSpanningTree(int src){
        PriorityQueue<Pair_5>pq = new PriorityQueue<>();
        pq.add(new Pair_5(src,-1,0));
        boolean[] visited = new boolean[N];

        while(pq.size()!=0){
            // remove
            Pair_5 rem = pq.remove();

            // mark
            if(visited[rem.v]==true){
                continue;
            }
            visited[rem.v] = true;

            // work
            if(rem.acqv!=-1){
                System.out.println(rem.v + " acq. from " + rem.acqv + " @ " + rem.wt);
            }
            

            // add
            for(Edge e:graph[rem.v]){
                if(visited[e.v]==false){
                    pq.add(new Pair_5(e.v,rem.v,e.w));
                }
            }
        }
    }

    public static void topo_dfs(int src, boolean[] visited,Stack st){
        visited[src] = true;

        for(Edge e: graph[src]){
            if(visited[e.v]!=true){
                topo_dfs(e.v,visited,st);
            }
        }
        st.push(src);
    }

    public static void topologicalsort(){
        boolean [] visited = new boolean[N];
        Stack<Integer> st = new Stack<>();

        for(int i=0;i<N;i++){
            if(visited[i]!=true){
                topo_dfs(i,visited,st);
            }
        }


        while(st.size()!=0){
            System.out.print(st.pop()+" ");
        }
    }

    public static void topo_bfs(){   //khans algo 
        int[] incident_edges = new int[N];
        
        
        for(int i=0;i<N;i++){
            for(Edge e:graph[i]){
                incident_edges[e.v]++;
            }
        }


        Queue<Integer>que = new ArrayDeque<>();
        Queue<Integer>ans = new ArrayDeque<>();
        for(int i=0;i<incident_edges.length;i++){
            // System.out.print(incident_edges[i]);
            if(incident_edges[i]==0){
                que.add(i);
            }
        }

        while(que.size()!=0){
            int vtx = que.remove();
            
            for(Edge e:graph[vtx]){
                if(--incident_edges[e.v]==0){
                    ans.add(e.v);
                }
            }
        }

        
            while(ans.size()!=0){
                System.out.print(ans.remove()+" ");
            }
        

    }


    public static class Pair_6{
        int v;
        String psf;

        Pair_6(int v, String psf){
            this.v = v;
            this.psf = psf;
        }
    }


    //same as bfs, but instead of queue we use stack to make it iterative and it will trace reverse euler
    public static void dfs_iterative(int src){  
        boolean[] visited = new boolean[N];
        Stack<Pair_6> st = new Stack<>();

        st.push(new Pair_6(src,src+" "));
        while(st.size()!=0){
            // remove
            Pair_6 rem = st.pop();

            // mark
            if(visited[rem.v]==true){
                continue;
            }else{
                visited[rem.v] = true;
            }

            // work
            System.out.println(rem.v + " @ " + rem.psf);

            // add
            for(Edge e:graph[rem.v]){
                if(visited[e.v]!=true){
                    st.push(new Pair_6(e.v, rem.psf+e.v));
                }
            }
        }
    }

    public static void main(String[] args){
        constructGraph();
 
        // solve();
        // bfs();
        // bipartite();
        // spredInfection(6,3);
        // shortestweigthdist(0);
        // minimumSpanningTree(0);
        // topologicalsort();
        // topo_bfs();
        dfs_iterative(0);
    }
}