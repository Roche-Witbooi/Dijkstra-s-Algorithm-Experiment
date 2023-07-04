/** Writes data from result text files to a CSV files 
  * @author Roche Witbooi (WTBROC002)
  * @since 30/05/2023
  */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class ResultsToCSV
{

   /** method that reads in data points from text file and inserts it into CSV file
     * @param String with data textfile name
     */
   public static void toCS(String tFile)
   {
              
        try {
                    BufferedReader fileReader = new BufferedReader(new FileReader(tFile));
                    FileWriter fileWriter = new FileWriter("C://Users//roche//OneDrive - University of Cape Town//CSC2001//Assignment_5//assign5//data//results.csv"); // directory where files should be stored
                      
                        String line ="vertices, edges, vCount, eCount, qCount"; // data headers
                        while (line != null)
                         {
                            String[] items = line.split(",");// seperates data points
                            for (int i = 0; i < items.length; i++)
                             {
                                fileWriter.write(items[i]);
                                if (i < items.length - 1)// adds a comma to seperate data points
                                 fileWriter.write(",");
                             }
                            fileWriter.write("\n");
                            line = fileReader.readLine();
                         }
      
                  fileReader.close();
                  fileWriter.close();
                  System.out.println("Data transferred from text file to CSV file successfully.");
                  
               }
               catch (IOException e)// handles exception when results textfile does not exist
               {  System.out.println("The file you wish to write fromo does not exist"); }
    }

}