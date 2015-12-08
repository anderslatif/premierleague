package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.FileDB;
import model.Goal;
import model.Match;
import model.Player;


public class Analysis{
   
   FileDB f = new FileDB();

   
    // Find optimal pay
   public void salaryCompare(String playerFirst, String playerSecond){
      Player player1 = null;
      Player player2 = null;
      
      for(Player p: f.getPlayerStats()){
         if(p.getName().toLowerCase().equals(playerFirst.toLowerCase())){
            player1 = p;
         } else if(p.getName().toLowerCase().equals(playerSecond.toLowerCase())){
            player2 = p;
         }
      }
      

      
      if(player1 != null || player2 != null){
         double ratio1 = player1.getGoals()/player1.getSalary();
         double ratio2 = player2.getGoals()/player2.getSalary();
         
         System.out.println(player1.getName() + " - Goals: " + player1.getGoals() + "    Salary: " + player1.getSalary() + "   Ratio: " + ratio1);
         System.out.println(player2.getName() + " - Goals: " + player2.getGoals() + "    Salary: " + player2.getSalary() + "   Ratio: " + ratio2);
         System.out.println();
          
            if(ratio1 > ratio2){
               System.out.println(player1.getName() + " has a more optimal salary than " + player2.getName());
            }else if(ratio1 < ratio2){
               System.out.println(player2.getName() + " has a more optimal salary than " + player1.getName());   
            }else{
               System.out.println(player1.getName() + " and " + player2.getName() + " has equally optimal salaries");
            }

      } else {
         System.out.println("-----------------------------------------");
         System.out.println("Sorry, one or two of the players are not on the team.");
      }
         
   }      
   
         
         
      // Find optimal formation against opponent

     public void optimalFormation(String opponent){
      

     System.out.println("List of formations we have used against " + opponent + " and whether we have lost, tied or won:");
      for(Match m : f.getMatchScores()){
         if(m.getOpponent().toLowerCase().equals(opponent.toLowerCase())){
         
            if(m.getOurScore() > m.getOpponentScore()){
               System.out.println("Won: " + m.getFormation());
            } else if(m.getOurScore() == m.getOpponentScore()){
               System.out.println("Tie: " + m.getFormation());
            } else{
               System.out.println("Loss: " + m.getFormation());    
            } 
         }
      }  
   }


   public void findTeamCaptain(){

      //Lists of players as Player objects
      ArrayList<Player> wonPlayers = new ArrayList<Player>();         
      ArrayList<Player> playedPlayers = new ArrayList<Player>();
      //Lists of players as String
      ArrayList<String> wonString = new ArrayList<String>();               
      ArrayList<String> playedString = new ArrayList<String>();
      //Players and matches lists               
      ArrayList<Player> players = new ArrayList<Player>();      
      ArrayList<Match> matches = new ArrayList<Match>();
      //Player names and their stats with mirrored index      
      ArrayList<String> playerNames = new ArrayList<String>();
      
      ArrayList<Player> matchPlayers = new ArrayList<Player>();
      ArrayList<String> matchPlayersFromFile = new ArrayList<String>();
      ArrayList<Player> mPFF = new ArrayList<Player>();

      //Counter and holder
      int counter = 0;      
      int holder = 0;
      
      int kkk = 0;
      int mmm = 0;
      int ss = 0;
      double a = 0;
      double b = 0;
      double c = 0;
      
      //Getting the information from the files
      f.getMatchPlayersData();               
      matches = f.getMatchScores();
      players = f.getPlayerStats();
      matchPlayers = f.getMatchPlayers();
      
      //Filling out our playerNames and playerNumbers lists
      for (Player z: players){
         playerNames.add(z.getName());
         counter ++;   
      }
      double[] playerNumbers = new double[counter];
      counter=0;


      //Generating an arraylist of Player objects for wins            
      for(Match m: matches){
         counter = 0;
         mPFF = m.getPlayers();
         if (m.getOurScore() > m.getOpponentScore()){            
            for (Player qq: mPFF){ 
               wonPlayers.add(mPFF.get(counter));         
               counter++;
            }
         }   
      }
      //System.out.println(matchPlayers.get(1));
      counter = 0;
       //Generating an arraylist of Player objects for played   
      for(Match r: matches){
      counter = 0;
         mPFF = r.getPlayers();            
            for (Player qq: mPFF){ 
               wonPlayers.add(mPFF.get(counter));         
               counter++;
            }
      }
      //Converting the Player objects into Strings
      for (Player w: wonPlayers){
         wonString.add(w.getName());
      }
      for (Player x: playedPlayers){
        playedString.add(x.getName());
      }
      //Generating the stats for wins for each player
      counter = -1;
      for (String n: playerNames){         
         counter ++;
         holder = 0;
            for (String q: wonString){
               if (n.equals(q)){
                  playerNumbers[counter]= playerNumbers[counter] + 1 ;
               }               
            }            
         for (String d: playedString){
            if (n.equals(d)){ 
               holder ++;               
               }               
         }
         if (holder != 0){
         playerNumbers[counter] = ((playerNumbers[counter])/holder)*100;
         }
                        
      }
      counter = 0;
      for (double i: playerNumbers){        
         if (i > a){
             a = i;
             kkk = counter;           
         }else if(i > b){
                  if (i != a){
                  b = i;
                  mmm = counter;
                  }
               }else if (i > c){
                           if (i != b){
                              c = i;
                              ss = counter;
                           }
                     }
      counter++;                     
      }

   System.out.println(playerNames.get(kkk) + " has a win rate of " + playerNumbers[kkk] + "%");
   System.out.println(playerNames.get(mmm) + " has a win rate of " + playerNumbers[mmm] + "%");
   System.out.println(playerNames.get(ss) + " has a win rate of " + playerNumbers[ss] + "%");
      
   }
   


    }
   
//by Anders, Dennis, Thomas