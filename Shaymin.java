public class Shaymin extends Adventurer{
  int petals, petalsMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Shaymin(String name, int hp){
    super(name,hp);
    petalsMax = 50;
    petals = 10;
  }


  public Shaymin(String name){
    this(name,30);
  }

  public Shaymin(){
    this("Adorable Hedgehog");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "petals";
  }

  public int getSpecial(){
    return petals;
  }

  public void setSpecial(int n){
    petals = n;
  }

  public int getSpecialMax(){
    return petalsMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 caffeine*/
  public String attack(Adventurer other){
    other.changeATKstatus(-2);
    if(useSpecial(2)){
      return this + " threw petals in the opponent's eyes, reducing the damage of their next attack by 2";
    }else{
      return this + " did not have enough petals to throw. Instead gathered 7 petals.";
    }
  }

  /*Deal 3-12 damage to opponent, only if caffeine is high enough.
  *Reduces caffeine by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " used their "+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough caffeine to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
