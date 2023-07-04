/**Represents an edge in the graph.
  *@author Weiss Textbook
  */
class Edge
{
    public Vertex     dest;   // Second vertex in Edge
    public double     cost;   // Edge cost
    
    /** contructor for Edge
      * @param d Vertex ,the vertex at the end of the edge
      * @param c double the cost of the edge
      */
    public Edge( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
}
