// Fig. 24.23: DisplayAuthors.java
// Displaying the contents of the authors table.

/*
Lots of try catches working with DB
Line comments are from MONDAY 22 OCT
 */
import java.sql.Connection; // Est. the connection to the DB
import java.sql.Statement;
import java.sql.DriverManager; // this one throws err
import java.sql.ResultSet; // holds data to be used in our program
import java.sql.ResultSetMetaData; // self explanatory
import java.sql.SQLException;

public class DisplayAuthors 
{
   public static void main(String args[])
   {
      final String DATABASE_URL = "jdbc:derby:lib\\books"; // Not gonna change, thus fina;
      final String SELECT_QUERY = 
         "SELECT authorID, firstName, lastName FROM authors"; // This is directly SQL.
	   // Make sure table & field names match up

	   /*
	   * THIS TRY BLOCK IS SUPER FUCKING IMPORTANT!!! UNDERSTAND IT!
	   * */
      // use try-with-resources to connect to and query the database
      try (  
         Connection connection = DriverManager.getConnection(
            DATABASE_URL, "deitel", "deitel");  // user/pass are params in
		      // Getconnection classcovered in...video 2??? You'd have to have created a username and password
		      // some of this stuff is within the Apache Derby tutorial
         Statement statement = connection.createStatement(); 
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) // ResultSet SUPER imporant
      // (it's THE DATA), this works getting passed the params on l.20, authorID, first and Last name
		  //    SQL query.
      {
         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount(); // Figuring out how many columns are in
	      // the query (l.21)
         
         System.out.printf("Authors Table of Books Database:%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i)); // Formats as left align, 8
	      // spaces preceding. Authorname, firstname, lastname
         System.out.println();
         
         // display query results
         while (resultSet.next()) // We don't know how many results we'll have.
         {
            for (int i = 1; i <= numberOfColumns; i++)
               System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
         } 
      } // AutoCloseable objects' close methods are called now 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }
      // TOTALLY OPTIONAL. Catches any exception.
      catch (Exception ex){
	      System.out.println("Fuckin' whoops!");
      }
      
   } 
} // end class DisplayAuthors



/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/

 