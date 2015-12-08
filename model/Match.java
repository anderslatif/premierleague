package model;

import java.util.*;

public class Match{

   //Variables
   private String date;
   private String opponent;
   private int ourScore;
   private int opponentScore;
   private String formation;
   private int saves;
   private ArrayList<Player> players = new ArrayList<Player>();
   private ArrayList<Goal> goals = new ArrayList<Goal>();
   
   //Constructor
   public Match(String date, String opponent, int ourScore, int opponentScore, String formation, int saves, ArrayList<Player> players, ArrayList<Goal> goals){
   this.opponent = opponent;
   this.date = date;
   this.ourScore = ourScore;
   this.opponentScore = opponentScore;
   this.formation = formation;
   this.saves = saves;
   this.players = players;
   this.goals = goals;
   }
   
   
   //Getters
   public String getDate(){
   return date;
   }
   
   public String getOpponent(){
   return opponent;
   } 
  
   public int getOurScore(){
   return ourScore;
   }
   
   public int getOpponentScore(){
   return opponentScore;
   }
   
   public String getFormation(){
   return formation;
   }
   
   public int getSaves(){
   return saves;
   }
   
   public ArrayList<Player> getPlayers(){
   return players;
   }
   
   public ArrayList<Goal> getGoals(){
   return goals;
   }
   
   
}
// Marc