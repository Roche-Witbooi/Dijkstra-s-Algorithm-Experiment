/** Helper method that generate the various types of data need for the graph experiment
  * @author Roche Witbooi (WTBROC002)
  * 03/04/2023
  */
import java.util.Random;

public class DataGenerator
{

       /** method generates a 2D array which specifies the number of vertices and edges for the 
         * experiment in fixed intervals
         * @returns 2D array of integers with vertices and edges numbers in the format:
         * [v1, e1, e2, e3, e4 ,e5]
         * [v2, e1, e2, e3, e4 ,e5]
         * [v3, e1, e2, e3, e4 ,e5]
         * where v1 is the number of verices in a grah and e1, e2, e3, e4, e5 are varying number of edges
         * for a graph with v1 verices
         */
       public static int [][] generateData ()
       {
               int nSets = 5;  // number of vertices sets
               int vIn = 10;   // the interval spacing between vertices groups
               int eIn = 15;   // the interval spacing between vertices groups
               int nE = 5;     // number of edge sets
               int begV = 10;  // number of vertices in first set
               int begE =20;   // number of edges (e1)
               
               int [][] data = new int[nSets][nE+1];
               for (int i =0; i < nSets ; i++)
               {   
                   data[i][0] = (i+1)*vIn;
                   for (int j =1 ; j <= nE ; j ++)
                     {data[i][j] = begE+((j-1)*eIn);}                    
               }
               return data;                
      }
      
       /** method generates a 2D array which specifies the number of vertices and edges for the 
         * experiment in intervals proportional to the number of verices
         * and the type of graph specified
         * @returns 2D array of integers with vertices and edges numbers in the format:
         * [v1, e1, e2, e3, e4 ,e5]
         * [v2, e1, e2, e3, e4 ,e5]
         * [v3, e1, e2, e3, e4 ,e5]
         * where v1 is the number of verices in a grah and e1, e2, e3, e4, e5 are varying number of edges
         * for a graph with v1 verices
         */
        public static int [][] dataGenerator ()
       {
               int nSets = 5;        // number of vertices sets
               int vIn = 5;          // the interval spacing between vertices groups
               int nE = 20;          // number of edge sets 
               int graphType = 3;    // 1 sparse; 2 fairly dense ; 3 dense ; 4 even
               int [][] data = new int[nSets][nE+1];
             
               
               for (int i =0; i < nSets ; i++)
               {    int begin = 0;
                    int interval = 0;
                    int v =(i+1)*vIn;
                    data [i][0] = v;
                    
                    switch (graphType)
                    {
                      case 1 : // sparse graph with edges ranging from 0% -50% of max number of edges
                         
                           interval = (int)(v/nE);
                           break;
                           
                      case 2: // fairly dense with edges ranging froms -50% of max number of edges
                           interval = (int)(((v*v*.9)/2)/nE);
                           break;
                           
                      case 3:// dense graphs with edges ranging from 50% -90% of the max number of edges
                           interval = (int)(((v*v*.9)/2)/nE);
                           begin = (int)(((v*v*.9)/2)- interval);
                           break;
                      
                      case 4:// dense graphs with edges ranging from 0% -90% of the max number of edges
                           interval = (int)(((v*v*.8)-(v*v*.2))/(nE-1));
                           begin = 2*v;

                           break;                
                     }
                    
                     for (int j =1 ; j <= nE ; j ++)
                     {
                        if (graphType ==1 || graphType ==2)
                           { data[i][j] =  interval*(j); } 
                           
                        else if (graphType ==4)
                          {data[i][j] = begin+  interval*(j-1);}
                          
                        else
                          { data[i][j] = begin+  interval*(j);}
                     }                    
               }
                 return data;                
      }

      
       /** method generates a 2D array which specifies edges in a graph 
         * @returns 2D array of integers with vertices and edges numbers in the format:
         * [v1, v2, c1] <- edge 1
         * [v3, v2, c2] <- edge 2
         * [v2, e1, c3] <- edge 3
         * where v1 number of the source vertex, v2 is number of the destination vertex
         * and cn represents the cost of the edges
         * for a graph with v1 verices
         */
      public static int[][] generateEdge(int numNodes, int numEdges)
      {  
               
            int [][] edges = new int [numEdges][3];  
            Random rand = new Random(); 
            int i = 0;
                  
              while (i < numEdges)
              {
                 // generates random vertex source ab=nd destination for edge
                 int beginEdge = rand.nextInt(numNodes);
                 int endEdge = rand.nextInt(numNodes);
                     
                       if (beginEdge == endEdge) // ensures no edges have the same source and destination
                         continue;
                           
                       boolean ok = true ;  
                       
                       // ensures no edges are repeated
                       for (int j = 0 ; j <=  i ; j++)   
                        {
                          if (edges[j][0]== beginEdge && edges[j][1] == endEdge)
                           { ok = false;}
                        }
                        
                        if (ok == false)
                           { continue;}
                        else
                            {
                              edges[i][0] = beginEdge;
                              edges[i][1] = endEdge;
                              edges[i][2] = rand.nextInt(10)+1;
                              i++;
                            }
                     }
                    return edges;
              }     
         
                
}



