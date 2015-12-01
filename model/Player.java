package model;

public class Player{

   private String name;
   private double salary;
   private String position;
   private int goals;
   private int saves;
   
   public Player(String name, double salary, String position, int goals, int saves){
      this.name = name;
      this.salary = salary;
      this.position = position;
      this.goals = goals;
      this.saves = saves;
   }   
   
   public String getName(){
      return name;
   }   

   public double getSalary(){
      return salary;
   }
   
   public String getPosition(){
      return position;
   }
   
   public int getGoals(){
      return goals;
   }
   
   public int getSaves(){
      return saves;
   }
   
   public void setSalary(double salary){
      this.salary = salary;
   }
   
   public void setGoals(){
      this.goals = goals;
   }
   
   public void setSaves(){
      this.saves = saves;
   }      
                  


}