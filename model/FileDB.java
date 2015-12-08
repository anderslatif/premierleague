package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner; 
import java.io.File;

// These imports all relate to creating Data objects from String, we do not currently use the method
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class FileDB{

   
   private ArrayList<String> matchGoalsFromFile = new ArrayList<String>();   
   
   private ArrayList<String> matchPlayersFromFile = new ArrayList<String>();



   //-----------------------------------------------------------------------------
         // returns an ArrayList of Player Objects for all the players contracted to our team
 public  ArrayList<Player> getPlayerStats(){

                        // ArrayList containing the unprocessed data - String - from our file 
      ArrayList<String> playerStatsFromFile = new ArrayList<String>(); 
                        
                  
         ArrayList<Player> players = new ArrayList<Player>();
         
         try{
                       // read from file with Scanner, use , as delimiter
            Scanner scanner = new Scanner(new File("Players.csv")); 
            scanner.useDelimiter(",");
   
            while(scanner.hasNext()){                 
                     playerStatsFromFile.add(scanner.next());          
            }
            
            scanner.close();
            playerStatsFromFile.removeAll(Arrays.asList("", null));  // remove indices that are just empty strings
                                                                     // .csv creates these if we have double-clicked on cells without inputting chars
   
                   
            // now we create player object and add them to an arraylist called players
            // we increment by five because there are five parameters in the player constructor
            for(int i = 0 ; i <= playerStatsFromFile.size() - 5 ; i +=6){  
               int savedGoals = Integer.valueOf(playerStatsFromFile.get(i+4).trim());                                                                                                                                                         
               Player player = new Player(playerStatsFromFile.get(i), Integer.valueOf(playerStatsFromFile.get(i+1)), playerStatsFromFile.get(i+2), Integer.valueOf(playerStatsFromFile.get(i+3)), savedGoals); 
               players.add(player);
            }
         
         } catch(FileNotFoundException e){
            System.err.println("FileNotFoundException: No players on the team.");
            e.printStackTrace();
         }
            

         return players;   
   }




   //-----------------------------------------------------------------------------

      // returns an ArrayList of Match Objects
   public  ArrayList<Match> getMatchScores(){
   
     getMatchPlayersData();  // calling the two other methods that also have their own files which they read from
     getGoalData();
     
         ArrayList<String> matchScoresFromFile = new ArrayList<String>();
      
         
         ArrayList<Match> matches = new ArrayList<Match>();
            
         try {

            Scanner scanner = new Scanner(new File("Matches.csv"));
            
            scanner.useDelimiter(",");
            
            while(scanner.hasNext()){                 
                     matchScoresFromFile.add(scanner.next());          
            }
            
            scanner.close();
            matchScoresFromFile.removeAll(Arrays.asList("", null));
                      
                              // we increment by 7 since we take in 7 parameters from the file
            for(int i = 0 ; i < matchScoresFromFile.size() - 6 ; i +=7){
               
                
               String date = matchScoresFromFile.get(i);
               String opponent = matchScoresFromFile.get(i+1);
               int ourScore = Integer.valueOf(matchScoresFromFile.get(i+2));
               int opponentScore = Integer.valueOf(matchScoresFromFile.get(i+3));
               String formation = matchScoresFromFile.get(i+4);
               int saves = Integer.valueOf(matchScoresFromFile.get(i+5));
   
               
                // the last two parameters are method calls that return an ArrayList of Player objects and Goal objects
                // each time the methods are called it returns an ArrayList corresponding to exactly one line in the files
                // this way we ensure that the MatchPlayers and the MatchGoals are the players and goals linked to this exact Match object                                                                                                                                         
               Match match = new Match(date, opponent, ourScore, opponentScore, formation, saves, getMatchPlayers(), getMatchGoals()); 
               matches.add(match);
   
             }  
          } catch(FileNotFoundException e){
            System.err.println("FileNotFoundException: No matches played.");
            e.printStackTrace();
         }
        
          return matches;
          
   }
   
    //-----------------------------------------------------------------------------
   
   public void getMatchPlayersData(){
   
      ArrayList<String> matchPlayersFromFile = new ArrayList<String>();
      
        try {
      
         Scanner scanner = new Scanner(new File("MatchPlayers.csv"));
            
            scanner.useDelimiter(",");
            
            while(scanner.hasNext()){                 
                     matchPlayersFromFile.add(scanner.next());          
            }
            scanner.close();
      matchPlayersFromFile.removeAll(Arrays.asList("", null));
      
      } catch(FileNotFoundException e){
         System.err.println("FileNotFoundException: No players on the pitch.");
         e.printStackTrace();
      }    
   
   
      this.matchPlayersFromFile = matchPlayersFromFile;
   }
   
   
   public ArrayList<Player> getMatchPlayers(){
   
      ArrayList<Player> matchPlayers = new ArrayList<Player>();
      if(matchPlayersFromFile.size() > 10){
            int i = 0;
            while(!(matchPlayersFromFile.get(i).contains("buffer"))){  // we use buffer to indicate that a new line occurs
               for(Player p: getPlayerStats()){                        // since our ArrayList is one-dimensional
                  if(p.getName().trim().equals(matchPlayersFromFile.get(i).trim())){ 
                     matchPlayers.add(p);
                     }
                  }   
               
               i++;
             }    
             while(i >= 0){   // we delete the amount of raw data indices we have used up, so next time we are ready to start from index 0 again
               matchPlayersFromFile.remove(0);
               i--;
             }     
         }
         return matchPlayers;
      }   
      
      //-----------------------------------------------------------------------------

   public void getGoalData(){
      
      ArrayList<String> matchGoalsFromFile = new ArrayList<String>();
      
      try { 
         Scanner scanner = new Scanner(new File("MatchGoals.txt"));// notice that this is a .txt-file. Not a .csv-file.
                                                                   // the reason for this is that the csv-file adds an extra comma on the first line
                                                                   // which it doesn't in the other files
                                                                   // using txt allows us to edit the file
         scanner.useDelimiter(",");
            
         while(scanner.hasNext()){                 
            matchGoalsFromFile.add(scanner.next());          
         }
         
         scanner.close();
         matchGoalsFromFile.removeAll(Arrays.asList("", null));
         
         this.matchGoalsFromFile = matchGoalsFromFile;
      } catch(FileNotFoundException e){
         System.err.println("FileNotFoundException: Null goals scored.");
         e.printStackTrace(); 
      }    
      
   }
         
         //---------------------------
  
     
   public ArrayList<Goal> getMatchGoals(){ 
      
      ArrayList<Goal> matchGoals = new ArrayList<Goal>();
       
      if(matchGoalsFromFile.size() > 2){ 
      
            int i = 0;
            Player player = null;
          
         while(!(matchGoalsFromFile.get(i).contains("buffer"))){
          
               for(Player p: getPlayerStats()){
                     if(matchGoalsFromFile.get(i+2).equals(p.getName())){
                        player = p;
                     }
                    
                  }    
                  Goal goal = new Goal(matchGoalsFromFile.get(i), Integer.valueOf(matchGoalsFromFile.get(i+1)), player);
                  if(!(player == null)){
                     matchGoals.add(goal);
                  }
            i += 3;      
         }     
      
         while(i >= 0){
            matchGoalsFromFile.remove(0);
            i--;
         }  

    }

      return matchGoals;
   }            


      // This is a very easy implementation if desired, we have left the method in just in case but do not use it as is
    public static Date getDate(String date){
        try {
            return new SimpleDateFormat("dd-mm-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }
     
     
     


}
//by Anders, Dennis, Marc, Søren