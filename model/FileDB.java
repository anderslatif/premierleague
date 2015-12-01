package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner; 
import java.io.File;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class FileDB{
  
  
   
   private ArrayList<String> matchGoalsFromFile = new ArrayList<String>();   
   

   
   //-----------------------------------------------------------------------------
      // returns a trimmed ArrayList of Players
 public  ArrayList<Player> getPlayerStats(){

      
      ArrayList<String> playerStatsFromFile = new ArrayList<String>(); //values from file
         
                  
         ArrayList<Player> players = new ArrayList<Player>();
         
         try{
         
            Scanner scanner = new Scanner(new File("Players.csv"));
            scanner.useDelimiter(",");
   
            while(scanner.hasNext()){                 
                     playerStatsFromFile.add(scanner.next());          
            }
            
            playerStatsFromFile.removeAll(Arrays.asList("", null));
   
                   
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

      // returns an ArrayList of match info
   public  ArrayList<Match> getMatchScores(){

         ArrayList<String> matchScoresFromFile = new ArrayList<String>();
         ArrayList<Match> matches = new ArrayList<Match>();

         getGoalData();

      
         
      
         // creates an ArrayList of match objects
         //ArrayList<Match> matches = new ArrayList<Match>();
            
         try {

            Scanner scanner = new Scanner(new File("Matches.csv"));
            
            scanner.useDelimiter(",");
            
            while(scanner.hasNext()){                 
                     matchScoresFromFile.add(scanner.next());          
            }
            
            matchScoresFromFile.removeAll(Arrays.asList("", null));
            
            ArrayList<Player> newPlayers = getPlayerStats();
            
   
            for(int i = 0 ; i < matchScoresFromFile.size() - 6 ; i +=7){//is i += 6 and - 8 correct? 
               
                
               String date = matchScoresFromFile.get(i);
               String opponent = matchScoresFromFile.get(i+1);
               int ourScore = Integer.valueOf(matchScoresFromFile.get(i+2));
               int opponentScore = Integer.valueOf(matchScoresFromFile.get(i+3));
               String formation = matchScoresFromFile.get(i+4);
               int saves = Integer.valueOf(matchScoresFromFile.get(i+5));
   
               
                // newplayers of type ArrayList is wrong cause we do not save individual players related to a match anywhere..                                                                                                                                          
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
  
   public ArrayList<Player> getMatchPlayers(){
   
      ArrayList<String> matchPlayersFromFile = new ArrayList<String>();
      
      matchPlayersFromFile.removeAll(Arrays.asList("", null));
      
      ArrayList<Player> matchPlayers = new ArrayList<Player>();

      try {
      
         Scanner scanner = new Scanner(new File("MatchPlayers.csv"));
            
            scanner.useDelimiter(",");
            
            while(scanner.hasNext()){                 
                     matchPlayersFromFile.add(scanner.next());          
            }
            
            
            int i = 0;
            while(!(matchPlayersFromFile.get(i).contains("buffer"))){
               for(Player p: getPlayerStats()){
                  if(matchPlayersFromFile.get(i).trim().equals(p.getName())){
                     matchPlayers.add(p);
                     }else{
                        //System.out.println("Not a team player");
                       }
                  }   
               
               i++;
             }    
             while(i >= 0){
               matchPlayersFromFile.remove(0);
               i--;
             }     
      } catch(FileNotFoundException e){
         System.err.println("FileNotFoundException: No players on the pitch.");
         e.printStackTrace();
      }    
             
         
         return matchPlayers;
      }   
      
      //-----------------------------------------------------------------------------

   public void getGoalData(){
      
      ArrayList<String> matchGoalsFromFile = new ArrayList<String>();
      
      try { 
         Scanner scanner = new Scanner(new File("MatchGoals.csv"));
            
         scanner.useDelimiter(",");
            
         while(scanner.hasNext()){                 
            matchGoalsFromFile.add(scanner.next());          
         }
         
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
                  matchGoals.add(goal);
              
            i += 3;      
         }     
      
         while(i >= 0){
            matchGoalsFromFile.remove(0);
            i--;
         }
         

    }

      return matchGoals;
   }            



    public static Date getDate(String date) {
        try {
            return new SimpleDateFormat("dd-mm-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }


}