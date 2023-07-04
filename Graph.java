/**Takes textfile, creates a graph using the given data
  * runs djkstras algorithm and counts the operations 
  * while the algorith runs 
  *@author Weiss Textbook
  */
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;


// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
//                              --> Add additional edge
// void printPath( String w )   --> Print path after alg is run
// void unweighted( String s )  --> Single-source unweighted
// void dijkstra( String s )    --> Single-source weighted
// void negative( String s )    --> Single-source negative weighted
// void acyclic( String s )     --> Single-source acyclic
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm.  Exceptions are thrown if errors are detected.

public class Graph
{
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );

    /**
     * method that adds a new edge to the graph.
     * @param sourceName String represnts the name of the source vertex
     * @param destName String represnts the name of the destination vertex
     * @param cost the cost of using traversing the edge from source to destination
     */
    public void addEdge( String sourceName, String destName, double cost )
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adj.add( new Edge( w, cost ) );
    }


    /**
     * method which returns the vertex with the name provided
     * @param vertexName String with the name of the vertex
     * @return Vertex with the name provided
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        return v;
    }

    
    /**
     * method which resets all vertex values to their default values
     */
    private void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }

    

    /**
     * method which determines single-source weighted shortest-path algorithm. (Dijkstra) 
     * using priority queues based on the binary heap
     * @param startName String the name of the first node in the graph
     *        the algorithm should use as the sourse
     * @return array of intgers containing counters for various operations
     */
    public int [] dijkstra( String startName )
    {
        PriorityQueue<Path> pq = new PriorityQueue<Path>( );
        int vCount = 0;   // counts vertex process
        int eCount = 0;   // counts edge process
        int pCount = 0;   // counts priority queue operations

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        clearAll( );
        pq.add( new Path( start, 0 ) );
        start.dist = 0;
        
        int nodesSeen = 0;
        while( !pq.isEmpty( ) && nodesSeen < vertexMap.size( ) )
        {
            pCount += (int)Math.log(pq.size() )/ Math.log(2);// priority queue operation
            Path vrec = pq.remove( );
            Vertex v = vrec.dest;
                       
            if( v.scratch != 0 )  // already processed v
                continue;
                
            v.scratch = 1;
            nodesSeen++;
            vCount++;// vetex operation
            
          
           
            for( Edge e : v.adj )
            {
                Vertex w = e.dest;         
                double cvw = e.cost;
                
                if( cvw < 0 )
                    throw new GraphException( "Graph has negative edges" );
                    
                eCount++; //edge operation 
                  
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist +cvw;
                    w.prev = v;
                    pq.add( new Path( w, w.dist ) );
                    pCount += (int)Math.log(pq.size() )/ Math.log(2);// priority queue operation
                }
            }
        }
        
        // an array containing the values calulated from running the algorithm
        int [] array = new int[3];

         array[0] = (vCount);
         array[1] = (eCount);
         array[2] = (pCount);
        return array;
        
    }


    /**
     * method which:
     * 1. Reads a file containing edges
     * 2. Forms the graph;
     * 3. Runs the shortest path algorithm.
     * 4.Writes calculated value to results file 
     *
     * @param testName int which represents test number n
     * @param fileName String contains the data for generating graph
     * The data file is a sequence of lines of the format
     *    source destination cost
     * @param number of vertices
     * @param number of edges
     */
    public static void calculate(int testName, String fileName, int vertices, int edges)
        {
        Graph g = new Graph( );
        String firstVertex = "" ;
        try
        {   	
            FileReader fin = new FileReader(fileName);
            Scanner graphFile = new Scanner( fin );
            
             // Read the edges and insert into fraph
            String line;
            while( graphFile.hasNextLine( ) )
            {
                line = graphFile.nextLine( );
                StringTokenizer st = new StringTokenizer( line );
                boolean beginVertexFound = false;

                try
                {
                    if( st.countTokens( ) != 3 )
                    {
                        System.err.println( "Skipping ill-formatted line " + line );
                        continue;
                    }
                    String source  = st.nextToken( );
                         
                         //identifies start node
                          if (!beginVertexFound)
                           {  firstVertex = source;
                            beginVertexFound = true;
                           }
                    String dest    = st.nextToken( );
                    int    cost    = Integer.parseInt( st.nextToken( ) );
                    g.addEdge( source, dest, cost );
                }
                catch( NumberFormatException e )
                 { System.err.println( "Skipping ill-formatted line " + line ); }
             }
         }
         catch( IOException e )
           { System.err.println( e ); }

         //run djkstras algorithm
         int [] array = g.dijkstra( firstVertex );
         System.out.println( "\nTest "+testName+" complete" );
         
         //Write results to a text file
         try
               {    
                  File test_data = new File( "C://Users//roche//OneDrive - University of Cape Town//CSC2001//Assignment_5//assign5//data//results.txt");
                  FileWriter writer = new FileWriter(test_data,true); 
                  writer.write(vertices +", " +edges+", "+array[0]+", "+array[1]+", "+array[2]+"\n");
                   writer.close();
              }
              catch(Exception ex1)
              { System.out.println("The result file does not exist");}
                 
         
         
         }
}
