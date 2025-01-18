import java.util.Random;
import java.util.ArrayList;

public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
  private ArrayList<Adventurer> friends;
  private ArrayList<Adventurer> enemies;
  private int ATKstatus;
  private boolean dead;
  
  public void addEnemy(Adventurer enemy){
    enemies.add(enemy);
  }
  public void addFriend(Adventurer friend){
    friends.add(friend);
  }
  public void kill(){
    dead = true;
  }
  public ArrayList<Adventurer> getEnemies(){
    return enemies;
  }
  public void setFriends(ArrayList<Adventurer> friends){//Creates a party of friends
    this.friends = friends;
  }

  public void setEnemies(ArrayList<Adventurer> enemies){ //
    this.enemies = enemies;
  }

  public ArrayList<Adventurer> getFriends(){
    return friends;
  }
  public Adventurer createRandomAdventurer(){//does not deal with parties
    int adventurerNumber = (int) (Math.random() * 3); //excludes boss
    Adventurer newAven = null;
    if(adventurerNumber == 0){
      newAven = new CodeWarrior();
    }else if(adventurerNumber == 1){
      newAven=  new Shaymin();
    }else if(adventurerNumber == 2){
      newAven = new Diglett();
    }
    return newAven;
  }

  public Adventurer createSummon(){
    int adventurerNumber = (int) (Math.random() * 3); //excludes boss
    Adventurer newAven = null;
    if(adventurerNumber == 0){
      newAven = new CodeWarrior("Evil CodeWarrior", 25);
    }else if(adventurerNumber == 1){
      newAven=  new Shaymin("Evil Hedgehog", 25);
    }else if(adventurerNumber == 2){
      newAven = new Diglett("Evil Diglett", 25);
    }
    return newAven;
  }

    public static void setEneFriends(ArrayList<Adventurer> friends, ArrayList<Adventurer> enemies){
      for(Adventurer enemy : enemies){
        enemy.setFriends(enemies);
        enemy.setEnemies(friends);
      }
      for(Adventurer friend : friends){
        friend.setFriends(friends);
        friend.setEnemies(enemies);
      }
    }

  public void addToTeam(Adventurer newTeammate){
    this.addFriend(newTeammate); //ONLY DO ONCE BC this.friends is set as equal to Enemies.enemies
  }

  //Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  public void changeATKstatus(ArrayList<Adventurer> targets, int amt){
    for(Adventurer target:targets){
      target.changeATKstatus(amt);
    }
  }
  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);

  /*
  Note to self: Buffs/Debuffs do not properly apply
  */

  public void applyDamage(int amount){
    this.HP -= amount;
  }
  public String damageOther(Adventurer target, int amount){
    String buffdebuff = "";

    if(ATKstatus < 0){
      buffdebuff = " (" + ATKstatus + " debuff from " + amount + ")";
    }else if(ATKstatus > 0){
      buffdebuff = " (" + ATKstatus + " buff from " + amount + ")";
    }
    amount += ATKstatus;
    ATKstatus = 0;
    target.applyDamage(amount);
    return amount + buffdebuff;
  }
  public String damageOther(ArrayList<Adventurer> targets, int amount){
    String buffdebuff = "";
    if(ATKstatus < 0){
      buffdebuff = " (" + ATKstatus + " debuff from " + amount + ")";
    }else if(ATKstatus > 0){
      buffdebuff = " (" + ATKstatus + " buff from " + amount + ")";
    }
    amount += ATKstatus;
    ATKstatus = 0;
    for(Adventurer target: targets){
      target.applyDamage(amount);

    }
    return amount + buffdebuff;
  }
  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10);
  }

  public Adventurer(String name, int hp){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    this.enemies = new ArrayList<Adventurer>();
    this.friends = new ArrayList<Adventurer>();
    this.ATKstatus = 0;
    this.dead = false;
  }
  public boolean isDead(){
    return dead;
  }

  public void changeATKstatus(int change){
    ATKstatus += change;
  }

  public int getATKstatus(){
    return ATKstatus;
  }

  public String getATKstatusdebug(){
    return "ATK Buffs/Debuffs:  " + getATKstatus();
  }
  //toString method
  public String toString(){
    return this.getName();
  }

  public boolean useSpecial(int amtused){
    if(getSpecial() >= amtused){
      setSpecial(getSpecial() - amtused);
      return true;
    }else{
      return false;
    }
  }
  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }

  public void restoreHP(int amt){
    if(getHP() + amt >=  getmaxHP()){
      setHP(getmaxHP());
    }else{
      setHP(amt + getHP());
    }
  }

  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  public String toStringDebug(){
    return getName() + ": " + getSpecialDebug() + ", " + getHPDebug() + ", " + getATKstatusdebug() + ", Enemies : " + getEnemies() + ", Friends " + getFriends() ;
  }

  public String getSpecialDebug(){
    return getSpecialName() + " " + getSpecial() + "/" + getSpecialMax();
  }

  public String getHPDebug(){
    return "HP " + getHP() + "/" + getmaxHP();
  }

  //Set Methods
  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }
}
