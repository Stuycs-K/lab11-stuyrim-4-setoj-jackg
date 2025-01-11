public class Diglett extends Adventurer{
    int depth, depthMax;
    
    /*the other constructors ultimately call the constructor
    *with all parameters.*/
    public Diglett(String name, int hp){
      super(name,hp);
      depthMax = 100;
      depth = 10; //start 10 depth
    }
  
  
    public Diglett(String name){
      this(name,30); //start 30 hp by default
    }
  
    public Diglett(){
      this("Diggy");
    }
  
    /*The next 8 methods are all required because they are abstract:*/
    public String getSpecialName(){
      return "depth";
    }
  
    public int getSpecial(){
      return depth;
    }
  
    public void setSpecial(int n){
        //note to self: this can go over limit (irrelevant to diglett but might be relevant for shaymin)
      depth = n;
    }
  
    public int getSpecialMax(){
      return depthMax;
    }
    public String toStringDebug(){
        return "Digglett " + getName() + ": " + getSpecialDebug() + ", " + getHPDebug();
    }
  
    /*not done */
    /*Deal 2-7 damage to opponent, restores 2 caffeine*/
    public String attack(Adventurer other){
        return attack();

    }

    public String attack(){
        restoreSpecial(10);
        return this + " dug into the ground, restoring 10 depth!";
    }
  
    /*AOE damage to all enemies
    */
    public String specialAttack(Adventurer other){
        if(getSpecial() >= 30){
            //execute attack

            return this + " burst out of the ground and dealt " +  damageOther(getEnemies(), 20)+" damage to all enemies";
        }else{
            return "Not enough depth to burst out and attack. Instead "+attack( other);
        }
  
    }

    /*Increases hp of all allies by 2 */
    public String support(Adventurer other){
        return support();}

    public String support(){
        if(getSpecial() >= 5){
            setSpecial(getSpecial()-5);
            for(Adventurer friend: getFriends()){
                friend.setmaxHP(friend.getmaxHP() + 2);
            }
            
            return this + " stabilized the earth, increasing the max HP of all allies by 2";
          }else{
            return "Not enough depth to stabilize the earth. Instead "+attack();
          }
    }
  }
  