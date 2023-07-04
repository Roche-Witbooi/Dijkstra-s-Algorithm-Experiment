/**Represents an entry in the priority queue for Dijkstra's algorithm.
  *@author Weiss Textbook
  */
class Path implements Comparable<Path>
{
    public Vertex     dest;   // w
    public double     cost;   // d(w)
    
    /** contructor for path
      * @param d Vertex which is vertex end of an edge
      * @param c Double which is cost of an edge
      */
    public Path( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
    
    /** method that compares the costs of two paths
      * @param rhs Path the path the current path is being compared to
      * @return integer representing the outcome of the comparision
      *                -1 : current path cost is less than path provided
      *                 0 : current path cost is same as path provided
      *                 1 : current path cost is more than path provided
      */
    public int compareTo( Path rhs )
    {
        double otherCost = rhs.cost;
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
}
