/**Representaion of a vertex used in graphs
  *@author Weiss Textbook
  */
import java.util.List;
import java.util.LinkedList;

class Vertex
{
    public String     name;   // Vertex name
    public List<Edge> adj;    // Adjacent vertices
    public double     dist;   // Cost
    public Vertex     prev;   // Previous vertex on shortest path
    public int        scratch;// Extra variable used in algorithm
    
    /** contructor for Vertex
      * @param String with name of the vertex
      */
    public Vertex( String nm )
      { name = nm; adj = new LinkedList<Edge>( ); reset( ); }
      
    /** method resets the values of the vertex instance variables to default values
      * distance = Graph.INFINITY, prev = null, scratch =0
      */
    public void reset( )
    { dist = Graph.INFINITY; prev = null; scratch = 0; }
  
}
