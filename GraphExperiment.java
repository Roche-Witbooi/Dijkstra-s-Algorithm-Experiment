/** Program runs graph Experiment which test the complexity of Djkstras algorithms
  * @author Roche Witbooi (WTBROC002)
  * 03/04/2023
  */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphExperiment
{

   public static void main (String args[])
   {
         int testName = 0;
         int [][] data =  DataGenerator.dataGenerator();    

         //creates the text file where experiment results are stored
         File file = new File( "C://Users//roche//OneDrive - University of Cape Town//CSC2001//Assignment_5//assign5//data//results.txt" ); 
         if (file.exists()) 
            {System.out.println("File already exists."); }
         else 
            {
               try 
                  {file.createNewFile();}
                  catch (IOException e)
                 {System.out.println("File results file could not be created");}
             }        
             
            //runs the test for each set of vetices and edges 
            for (int row = 0 ; row < data.length ; row++)
               {
                    for (int col = 1 ; col < data[row].length ; col++)
                       {
                           testName++;
                           String fileName ="C://Users//roche//OneDrive - University of Cape Town//CSC2001//Assignment_5//assign5//data//_v"+ data[row][0]+"_e"+data[row][col]+".txt";
                             
                              try
                              {  //creates the input data for each test 
                                 File test_data = new File(fileName);
                                 test_data.createNewFile();
                                 FileWriter writer = new FileWriter(test_data); 
                                 
                                 int [][] array = DataGenerator.generateEdge( data[row][0], data[row][col]);
                                 for (int i = 0 ; i < data[row][col] ; i ++)
                                     { writer.write("Node"+array [i][0] +"  Node"+ array[i][1]+"  "+ array[i][2]+ "\n");}
                                     
                                 writer.close();                                 
                                 Graph.calculate(testName,fileName, data[row][0], data[row][col]);
                                                         
                              }
                              catch(Exception ex1)
                              {System.out.println("A data set for this test already exists");}
       
                        }
         }
         //Transfers textfile data to CSV file
        ResultsToCSV.toCS( "C://Users//roche//OneDrive - University of Cape Town//CSC2001//Assignment_5//assign5//data//results.txt");
        System.out.println("\nExperiment Complete! Please check results file.");
   }
}