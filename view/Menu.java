package view;

import model.FileDB;
import controller.Statistics;

import java.util.Scanner;

public class Menu{


   Statistics stats = new Statistics();
   
   Scanner scanner = new Scanner(System.in);
   
   int choose;
   String date;
   
   boolean connectedToDatabase = false;
   
      
      
      
   
   public void menu(){
            
      System.out.println("-----------------------------------------"); 
      System.out.println(" Welcome to Arsenal Statistics ");
      System.out.println("    Please choose a number");
      System.out.println("1: Get updated information from database.");
      System.out.println("2: Get information about a match based on the date.");
    
      if(connectedToDatabase == true){
         
            if(scanner.hasNextInt()){
               choose = scanner.nextInt();
            } else{
               System.out.println("Error, not a number.");
               scanner.next();
               menu();
            }
         
         
         
         switch(choose){
         
            case 1: // get updated data from database
               connectedToDatabase = false;   
               menu();

            case 2: // get match information based on the date
                           
               System.out.print("Enter the date: ");
               date = scanner.next();
               stats.getMatchInfo(date);
               menu();
        
            case 3:
               //indsæt af vores metoder
               System.out.println("test af systemet");
               menu();
        
                   
            case 4:   
                 //indsæt af vores metoder
               System.out.println("Hej med dig");
               menu();
                         
               
            default:
               System.out.println("Wrong number. Please try again.");
               menu();
          } 
       }  else{     
                
               FileDB fromFileDB = new FileDB();
                             
               fromFileDB.getPlayerStats();
               fromFileDB.getMatchScores();
               
               connectedToDatabase = true;
               
               System.out.println("-----------------------------------------"); 
               System.out.println("We are now connecting to the database...");
               System.out.println("      Please wait a moment......");
               System.out.println("Just kidding, our program is really fast.");
               
               menu();
      }
   }

}