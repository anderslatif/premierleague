package view;

import controller.Analysis;
import controller.Statistics;
import model.FileDB;

import java.util.Scanner;


public class Menu{


   Statistics statistics = new Statistics();
   Analysis analysis = new Analysis();
   
   Scanner spaceScanner = new Scanner(System.in).useDelimiter("\\n");  // spaceScanner accepts an entire name (first, last) as a token
   Scanner scanner = new Scanner(System.in);
   
   int choice;
  
   boolean connectedToDatabase = false;  // Is set as fault when a menu object is created
                                         // This is to ensure that we will connect to the database when the menu() method is called
   

      public void menu(){
               
         System.out.println("-----------------------------------------"); 
         System.out.println(" Welcome to Arsenal Statistics ");
         System.out.println("    Please choice a number.");
         System.out.println("1: To get methods for analysis.");  // methods in class: controller.Analysis
         System.out.println("2: To get methods for statistics."); // methods in class: controller.Statistics
         
         if(connectedToDatabase == true){
            
               if(scanner.hasNextInt()){
                  choice = scanner.nextInt();
               } else{
                  System.out.println("Error, not a number.");
                  scanner.next();
                  menu();
               }
            
         switch(choice){
         
            case 1:
               System.out.println("-----------------------------------------");
               System.out.println("Please choice a number.");
               System.out.println("1: To compare salary between 2 players");
               System.out.println("2: To get the optimal formation against a specific opponent");
               System.out.println("3: To get the optimal team captain");
               
               if(scanner.hasNextInt()){
                  choice = scanner.nextInt();
               } else{
                  System.out.println("Error, not a number.");
                  scanner.next();
                  menu();
               }
                          
               switch(choice){ 
               
                  case 1:  // Find optimal salary by comparing the salary and the goal scoring of two players
                     System.out.println("Please write the name of the first player (e.g. Bacary Sagna): ");
                     String player1 = spaceScanner.nextLine();
                     System.out.println("Please write the name of the second player (e.g. Olivier Giroud): ");
                     String player2 = spaceScanner.nextLine();
                
                     analysis.salaryCompare(player1, player2);
                     menu();
                      
                  
                  case 2: // Get formations against a specific opponent and whether we won, tied or lost
                     System.out.println("Please write the desired opponent (e.g. Aston Villa): ");
                     String desiredOpponent = spaceScanner.nextLine();
                     analysis.optimalFormation(desiredOpponent);
                     menu();
                      
                  case 3:  // Find the team captain by comparing presence in won matches
                     analysis.findTeamCaptain();
                     menu();
               }
               
            case 2:  
            System.out.println("-----------------------------------------");
            System.out.println("Please choose a number.");
            System.out.println("1: To connect to database");
            System.out.println("2: To get match info from a specific date");
            System.out.println("3: To get the name, position and salary of the team's players");
            System.out.println("4: To get a list of matches played");
            System.out.println("5: To get match results from a list of matches");
            System.out.println("6: To get who played against who, on which date and with what formation");
            System.out.println("7: To get saves from a specific goalkeeper");
            System.out.println("8: To get how many times a specific player has won and lost");
            System.out.println("9: To get how many matches a specific player has played");
            System.out.println("10: To get who played in a specific match");
            
            
            if(scanner.hasNextInt()){
                  choice = scanner.nextInt();
               } else{
                  System.out.println("Error, not a number.");
                  scanner.next();
                  menu();
               }

            switch(choice){
            
               case 1: // get updated data from database
                  connectedToDatabase = false;     // When we call the menu() method, it will connect to the database first
                  menu();                          // The reason we have this feature is to allow for new updated information...
                                                   // ...if recent matches have been played since opening up the program
   
               case 2: // print match information based on the date
                              
                  System.out.print("Please enter the date as: dd-mm-yyyy (e.g. 02-11-2013):  ");
                  String date = scanner.next();
                  statistics.getMatchInfo(date);
                  menu();
           
               case 3:  // print information about the players
                  System.out.println("Name, position and salary of the teams players: ");
                  statistics.teamPosition();
                  menu();
                  
                                    
               case 4:   // list of matches played
                  System.out.println();
                  statistics.matchDate();
                  menu();
               
               case 5:  // different list of matches played
                  statistics.matchResult();
                  menu();
                  
               case 6:  // yet a different list with different information about the matches 
                  statistics.matchFormation();
                  menu();
               
               case 7:  // print amount of saves, only works if you input a goalkeeper
                  System.out.println("Please enter the goalkeeper's name (e.g. Wojciech Szczesny): ");
                  
                  String goalKeeper = spaceScanner.nextLine();
                  statistics.amountOfSavesPerMatch(goalKeeper);
                  menu();
               
               case 8: // see how many times a specific player has won and/or lost
                  System.out.println("Please choose a player (e.g. Bacary Sagna): ");
                  String readPlayer = spaceScanner.nextLine();
                  statistics.matchesWonAndLost(readPlayer);
                  menu();
               
               case 9: // amount of matches played
                  System.out.println("Please choose a player (e.g. Per Mertesacker): ");
                  String selectedPlayer = spaceScanner.nextLine();
                  statistics.amountOfPlayedMatches(selectedPlayer);
                  menu();
                  
               case 10: // prints a roster from a match in the season, we have numbered the matches chronological (date) with 1 being the first match
                  System.out.println("Please input a number from 1 to 38 to select a match:");
                  
                  int matchNumber = 0;
                  
                  if(scanner.hasNextInt()){
                     matchNumber = scanner.nextInt();
                  } else{
                     System.out.println("Error, not a number.");
                     scanner.next();
                     menu();
                  }
                  
                  if(matchNumber > 0 && matchNumber <= 38){
                     statistics.getMatchPlayerNames(matchNumber);
                  } else{
                     System.out.println("The number you chose is not correct.");
                  }
                  
                  menu();
             }   
         }
      } else { 
            
            System.out.println("-----------------------------------------");
            System.out.println("We are now connecting to the database....");

            FileDB f = new FileDB();
               
            f.getPlayerStats();
            f.getMatchScores();
            connectedToDatabase = true;  // calling the methods in FileDatabase that get our data from the files and set the connection to true
            menu();
      
      }
   }
}
//by Anders, Marc, Søren, Thomas