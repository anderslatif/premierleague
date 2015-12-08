package controller;

import model.FileDB;
import model.Goal;
import model.Match;
import model.Player;

import java.util.ArrayList;

public class Statistics{

   
   FileDB f = new FileDB();
   
   //Prints out the name, position and salary of the teams players
   public void teamPosition(){
      for(Player p: f.getPlayerStats()){
         System.out.printf("%s%-23s %s%s %20s %s\n", "Name: ",p.getName(), " Position: ", p.getPosition(), " Salary in GPB: ", p.getSalary());
      }
   
   }
   //Prints out who played on what date
   public void matchDate(){
      for( Match m: f.getMatchScores()){
         System.out.println("Match date: " + m.getDate() + " Arsenal vs " +  m.getOpponent());
      
      }
   
   }
   //Prints who scored the goal and when. Also prints out the final score of the game
   public void matchResult(){
      for(Match m: f.getMatchScores()){
      System.out.println("             -----------------");
         System.out.println("Opponent score: " + m.getOpponentScore() + " Our score: "  + m.getOurScore() + " on " + m.getDate());        
            for(Goal g: m.getGoals()){
            
                  System.out.println(g.getPlayer() + " scored a goal at minute " + g.getTime());
                  
            } 
         }
      }

   //Prints who played against who, on which date and with what formation
   public void matchFormation(){
      for(Match m: f.getMatchScores()){
         System.out.printf("%-20s %-20s %s %s %s %s\n", "Arsenal played against " , m.getOpponent() , "on" , m.getDate() , "with this formation" , m.getFormation());
      }
   }
                               
    public void getMatchInfo(String date){
     
      for(Match m : f.getMatchScores()){
         if(m.getDate().equals(date)){
            System.out.println("-----------------------------------------"); 
            System.out.println("Our Score: " + m.getOurScore());
            System.out.println("Opponent Score: " + m.getOpponentScore());
            System.out.println("Our formation: " + m.getFormation());
            System.out.println("Match goals: " + m.getGoals());
         } else {
            System.out.println("-----------------------------------------");
            System.out.println("No matches played on that date or out of season.");
            break;
         }
      }

   }

      
   public void amountOfSavesPerMatch(String goalKeeper){
            
            outerLoop:
            for(Match m : f.getMatchScores()){
               for(Player p : m.getPlayers()){       
                  
                  if(p.getName().toLowerCase().equals(goalKeeper.toLowerCase()) && p.getPosition().equals("GK")){
                     System.out.println("Date of match: " + m.getDate() + "    Amount of saves: " + m.getSaves());      
                  } else {
                     System.out.println("-----------------------------------------");
                     System.out.println("Sorry, not a goalkeeper on the team.");
                     break outerLoop;
                  } 
                  
               }  
            } 
   }

   
   public void matchesWonAndLost(String player){
   
      boolean playerFound = false;
      
      int wonCounter = 0;
      int tieCounter = 0;
      int lossCounter = 0;
      
      for(Match m : f.getMatchScores()){
         for(Player p : m.getPlayers()){       
            
            if(p.getName().toLowerCase().equals(player.toLowerCase())){
               playerFound = true;
               
               if(m.getOurScore() > m.getOpponentScore()){
                  wonCounter++;
               } else if(m.getOurScore() == m.getOpponentScore()){
                  tieCounter++;
               } else{
                  lossCounter++;   
               }
            }
         }  
      }
      
      if(playerFound == true){
         System.out.println("Games that " + player + " has won: " + wonCounter);
         System.out.println("Games that " + player + " has tied: " + tieCounter);
         System.out.println("Games that " + player + " has lost: " + lossCounter);
      } else {
         System.out.println("-----------------------------------------");
         System.out.println("Player not found on the team.");
      }
   }   
   
   
   
   public void amountOfPlayedMatches(String player){
      
      boolean playerFound = false; 
      int playedMatchesCounter = 0;
      
      for(Match m : f.getMatchScores()){
         for(Player p : m.getPlayers()){       
 
            if(p.getName().toLowerCase().equals(player.toLowerCase())){
               playerFound = true;
            
               playedMatchesCounter++;
            }
         }  
      }
      if(playerFound == true){
         System.out.println(player + " has played in: " + playedMatchesCounter + " matches."); 
      } else{
         System.out.println("-----------------------------------------");
         System.out.println("Player not found on the team.");
      }     
   }
   
   


        
   

   public void getMatchPlayerNames(int selectedMatch){
      
      Match theMatch = f.getMatchScores().get(selectedMatch);
      
      ArrayList<Player> gottenPlayers = theMatch.getPlayers();
      
      for(Player p : gottenPlayers){
         System.out.println(p.getName());
      }
   }

   
   


}


//by Anders, Marc, Søren