package model;

import java.time.*;

public class Goal{

   private String date; 
   private int time;
   private Player player; //Object of class Player
   
   //constructor for Goal class
   public Goal(String date, int time, Player player){
      this.date = date;
      this.time = time;
      this.player = player;  
   }
    
    //getter for date
    public String getDate(){
      return date;
    }
    
    //getter for Time
    public int getTime(){
      return time;
    }
   
   public Player getPlayer(){
      return player;
   }
}