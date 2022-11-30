
package prakstrukdat_uas;

public class Vertex {
    String name;
    int status;
    int predecessors;
    int pathLength;
    
    Vertex(String name){
       this.name = name;
    }
    
    public String toString(){
        return name;
    }
}
