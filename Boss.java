public class Boss extends Adventurer{
  int pokeballs, pokeballsMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Boss(String name, int hp){
    super(name,hp);
    pokeballsMax = 10;
    pokeballs = 1;
  }


  public Boss(String name){
    this(name,30);
  }

  public Boss(){
    this("Ash Ketchup");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "pokeballs";
  }

  public int getSpecial(){
    return pokeballs;
  }

  public void setSpecial(int n){
    pokeballs = n;
  }

  public int getSpecialMax(){
    return pokeballsMax;
  }


  public String attack(Adventurer other){
    int numTaken =  (int) (Math.random() * 3);
    restoreSpecial(numTaken);
    return this + "grabbed" + numTaken + "poke-balls from their pouch.";
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
  /*spawns new adven ally*/
  public String support(Adventurer other){

    if(getSpecial() < 2){
      return attack(other);
    }
    int addlToThrow = getSpecial() / 2;
    if(addlToThrow > 1){ //max throw 2
      addlToThrow = 1;
    }
    useSpecial(2 * (1 + addlToThrow));

    Adventurer toSpawn = createSummon();
    addToTeam(toSpawn);

    String ret = this + " threw a poke-ball, spawning " + ( 1 + addlToThrow) + " minions: " + toSpawn;
    for(int i = 0; i < addlToThrow; i++){
      toSpawn = createSummon();
      addToTeam(toSpawn);
      ret += " and " + toSpawn;
    }
    return ret;
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    return support(this);
  }
}
