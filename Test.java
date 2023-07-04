public class Test 
{
       public static void main(String args[])
       {
          int [][] data = DataGenerator.dataGenerator();
         
          
          for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    System.out.print(data[i][j] + " ");
                }
                System.out.println();
            }
       }
               
}