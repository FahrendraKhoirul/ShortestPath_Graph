
package prakstrukdat_uas;


public class DirectedWeightedGraph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    
    private static final int TEMPORARY = 1;
    private static final int PERMANENT = 2;
    private static final int NULL = -1;
    private static final int INFINITY = 99999;
    
    public DirectedWeightedGraph(){
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int i=0; i<MAX_VERTS; i++){
            for(int j=0; j<MAX_VERTS; j++){
                adjMat[i][j]=0;
            }
        }
    }
    
    public void addVertex(String label){
        vertexList[nVerts++] = new Vertex(label);
    }
    
    public void addEdge(String start, String end, int length){
        int intStart = findName(start);
        int intEnd = findName(end);
        if(intStart < 0 || intEnd < 0){
            System.out.println("\n!! Gagal Menambahkan Jalur !!\n");
        }else{
            adjMat[intStart][intEnd] = length;
            System.out.println("Jalur Berhasil Ditambahkan : \n" + start + " --> " + end + " : " + length + " km\n");      
        }
    }
    
    private int findName(String name){
        int status = -1;
        for(int i=0; i<nVerts; i++){
            if(vertexList[i].toString().equals(name)){
                status = i;
            }
        }
        return status;
    }
    
    private void dijkstra(int intSource){
        int v,c;
        
        for(v=0; v<nVerts; v++){
            vertexList[v].status = TEMPORARY;
            vertexList[v].pathLength = INFINITY;
            vertexList[v].predecessors = NULL;
        }
           
        vertexList[intSource].pathLength = 0;    
        
        
        while(true){
            c = tempVertexMinPL();
            
            if(c==NULL){
                return;
            }
            vertexList[c].status = PERMANENT;
            
            for(v=0; v<nVerts; v++){
                if(isAdjacent(c,v) && vertexList[v].status  == TEMPORARY){
                    if(vertexList[c].pathLength + adjMat[c][v] < vertexList[v].pathLength){
                        vertexList[v].predecessors = c;
                        vertexList[v].pathLength = vertexList[c].pathLength + adjMat[c][v];
                    }
                }
            }
        }      
    }
    
    public void findPaths(String source){
        int intSource = findName(source);
        if(intSource < 0){
            System.out.println("\n!! Kota tidak ditemukan !!\n");
            return;
        }else{
            dijkstra(intSource);
            System.out.println("-------------------------------------------");
            System.out.println("Kota Asal : " + source + "\n");
            for(int v=0; v<nVerts; v++){
                System.out.println("Kota Tujuan : " + vertexList[v].toString());
                if(vertexList[v].pathLength == INFINITY){
                    System.out.println("Tidak ada jalur yang tersedia dari " + source + " ke " + vertexList[v].toString() + "\n");
                }else{
                    findPath(intSource, v);
                } 
            }
        }
    }
    
    public void findPaths(String kotaAsal, String kotaTujuan){
        int intAsal = findName(kotaAsal);
        int intTujuan = findName(kotaTujuan);
        dijkstra(intAsal);
        if(vertexList[intAsal].pathLength == INFINITY){
            System.out.println("Tidak ada jalur yang tersedia dari " + kotaAsal + " ke " + vertexList[intTujuan].toString() + "\n");
        }else{
            findPath(intAsal, intTujuan);
        } 
    }
    
    private void findPath(int source, int end){
        int i, u;
        int [] path = new int[nVerts];
        int length=0;
        int count=0;
        
        while(source != end){
            count++;
            path[count] = end;
            u = vertexList[end].predecessors;
            length += adjMat[u][end];
            end = u;
        }
        count++;
        path[count]=source;
        
        System.out.print("Jalur Terdekat : ");
        for(i=count; i>0; i--){
            System.out.print(vertexList[path[i]].toString());
            if(i-1 == 0){
                System.out.print(".");
            }else{
                System.out.print(" --> ");
            }
        }
        System.out.println("\nJarak : " + length + "\n");
    }
    
    private boolean isAdjacent(int start, int end){
        if(adjMat[start][end] == 0){
            return false;
        }else{
            return true;
        }
    }
    
    private int tempVertexMinPL(){
        int min = INFINITY;
        int index = NULL;
        for(int v=0; v<nVerts; v++){
            if(vertexList[v].status == TEMPORARY && vertexList[v].pathLength < min){
                min = vertexList[v].pathLength;
                index = v;
            }
        }
        return index;
    }
    
    public void printGraph(){
        System.out.println("\nDaftar Jarak Antar Kota:");
        for(int row = 0; row < nVerts; row++){
            for(int col=0; col < nVerts; col++){
                if(adjMat[row][col] != 0){
                    System.out.println(vertexList[row].name 
                    + " --> " + vertexList[col].name + " = " + adjMat[row][col]);
                }
            }
        }
        System.out.println("");
    }
    
    public void printVertex(){
        System.out.println("\nDaftar Nama Kota:");
        for(int i = 0; i < nVerts; i++){
            System.out.println(i+1 + ". " + vertexList[i].toString());
        }
        System.out.println("");
    }
}
