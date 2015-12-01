package controller;

import model.FileDB;
import model.Match;
import model.Player;

import java.util.ArrayList;


public class Statistics{

   //Matches won
   
   //Find teamleader
   
   //Find optimal pay
   
   //Find optimal formation against opponent

   FileDB f = new FileDB();
   

                           // change to date object
   public void getMatchInfo(String date){
     
      for(Match m : f.getMatchScores()){
         if(m.getDate().equals(date)){
            System.out.println("-----------------------------------------"); 
            System.out.println("Our Score: " + m.getOurScore());
            System.out.println("Opponent Score: " + m.getOpponentScore());
            System.out.println("Our formation: " + m.getFormation());
            System.out.println("Match goals: " + m.getGoals());
         }
      }

   }
   
   

   public void getMatchPlayerNames(int selectedMatch){
      
      Match theMatch = f.getMatchScores().get(selectedMatch);
      
      ArrayList<Player> gottenPlayers = theMatch.getPlayers();
      
      for(Player p : gottenPlayers){
         System.out.print(p.getName());
         System.out.println("  goals: " + p.getGoals());
      }
   }





}