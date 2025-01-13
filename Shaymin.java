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
    if(useSpecial(2)){
      other.changeATKstatus(-2);
      return this + " threw petals in " + other + "'s " + "eyes, reducing the damage of their next attack by 2";
    }else{
      restoreSpecial(7);
      return this + " did not have enough petals to throw. Instead gathered 7 petals.";
    }
  }

  /*Deal 3-12 damage to opponent, only if caffeine is high enough.
  *Reduces caffeine by 8.
  */
  public String specialAttack(Adventurer other){
    if(useSpecial(15)){
      changeATKstatus(getFriends(), 3);
      return this + " fed strengthening petals to all allies, increasing their damage by "+ 3;
    }else{
      restoreSpecial(7);
      return "Not enough petals to feed to allies. Instead gathered 7 petals";
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    other.restoreHP(2);
    other.restoreSpecial(5);
    return  this + " found a magical berry that heals " + 2 + " hp and restores " + 5 + " " + other.getSpecialName() + " to " + other;
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    return support(this);
  }
}
