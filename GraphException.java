/**Used to signal violations of preconditions for
  *various shortest path algorithms.
  *@author Weiss Textbook
  */
class GraphException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
   
    
  /**method that produces an exception when a graph condition is violated
	 *@param name String error message
	 */
  public GraphException( String name )
    {
        super( name );
    }
}
