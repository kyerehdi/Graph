import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class MyCustomGraph<V>extends UnweightedGraph<V> {
    public MyCustomGraph() {
        super();
    }
    public MyCustomGraph(int[][]edges,int numberOfVerticles) {
        super(edges,numberOfVerticles);
    }
    public MyCustomGraph(List<Edge> edges, int numberOfVertices) {
        super(edges,numberOfVertices);

    }
    public MyCustomGraph(V[] vertices, int[][] edges) {
        super(vertices,edges);
    }
    public MyCustomGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public MyCustomGraph<V> readFile(String fileName) throws FileNotFoundException {

        List<Edge> allEdges = new ArrayList<>();
        File file= new File(fileName);
        if(!file.exists()) {
            throw new FileNotFoundException();
        }

        Scanner in= new Scanner(file);

        int numVertices =-1;
        if(in.hasNextLine())
            numVertices=Integer.parseInt(in.nextLine().trim());

        while(in.hasNextLine()) {
            String nextLine= in.nextLine().trim();
            String [] data= nextLine.split(" ");
            int orgin= Integer.parseInt(data[0]);


            for(int i=1; i<data.length;i++) {
                int destination = Integer.parseInt(data[i]);
                Edge thisEdge= new Edge(orgin,destination);
                allEdges.add(thisEdge);
            }

        }
        MyCustomGraph graph= new MyCustomGraph(allEdges,numVertices);
        return graph;
    }
    public boolean isComplete(){

        int n=vertices.size();
        int comp=((n*(n-1))/2);
        if(comp==countEdges())
       return true;
        else return false;
    }
    public boolean areAdjacent(V v1, V v2){
        if(getNeighbors((Integer)v1).contains(v2))
            return true;
        else
        return false;
    }
    public boolean isConnected(){
        if(vertices.size()<2)
            return false;
if(dfs(0).getNumberOfVerticesFound()==vertices.size())
    return true;
        else return false;
    }

    public List<V> getShortestPath(V begin, V end){
        List<V>SP=(List<V>)bfs((Integer) end).getPath((Integer) begin);
        if(end == SP.get(SP.size() - 1))
        return SP;
        else
            return
           null;
    }

    public boolean hasCycle(){
        List<Integer> V = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        boolean []IV= new boolean[vertices.size()];
 for(int i=0; i<vertices.size();i++) {
     parent[i] = -1;
     if (!IV[i]) {
         return hasCycleHelp(i, parent, V, IV);
     }
 }
 return false;
    }

    public boolean hasCycleHelp(int v,int [] parent,List<Integer>V, boolean[] IV) {
        V.add(v);
        IV[v]=true;
        for(Edge e : neighbors.get(v)){
            if (!IV[v]){
          parent[v]=v;
                hasCycleHelp(parent[v],parent,V,IV);
            }
           return (IV[v] && v!=parent[v]);


        }
return false;
    }



    private int countEdges(){
        int c=0;
        c++;
        printEdges();
        return c;
    }
    public void main(String args[]){

    }
}
