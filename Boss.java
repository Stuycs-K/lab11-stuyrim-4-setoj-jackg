public class Boss extends Adventurer{
  private int pokeballs, pokeballsMax;
  private final int MAX_SUMMONS = 3;
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
    return this + " grabbed " + numTaken + " poke-balls from their pouch.";
  }

  /*Completely obliterates opponent.
  */
  public String specialAttack(Adventurer other){
    if(useSpecial(5)){
      other.setHP(-1);
      return this + " captured " + other + ", removing all of their hp" ;
    }else{
      return "Not enough pokeballs to capture. Instead " + attack(other);
    }

  }


  /*spawns new adven ally*/
  public String support(Adventurer other){

    if(getSpecial() < 2){
      return "Does not have enough pokeballs to spawn a pokemon. Instead " + attack(other);
    }

    if(getFriends().size() > MAX_SUMMONS){
      return this + " has already summoned the maximum amount of pokemon. Instead " + attack(other);
    }

    int toThrow = getSpecial() / 2;
    if(toThrow > 2){ //max throw 2
      toThrow = 2;
    }
    useSpecial(2 * (toThrow));

    String ret = this + " threw a poke-ball, spawning " + ( toThrow) + " minions: ";
    
    for(int i = 0; i < toThrow; i++){
      Adventurer toSpawn = createSummon();
      addToTeam(toSpawn);
      ret += toSpawn + " and ";
    }
    return ret.substring(ret.length() - 3);
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    return support(this);
  }
}
