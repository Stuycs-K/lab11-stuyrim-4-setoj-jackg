import java.util.ArrayList;

public class Tester {
    public static void main(String[] args){
        System.out.println(Game.createRandomAdventurer());
        System.out.println(Game.createRandomAdventurer());
        System.out.println(Game.createRandomAdventurer());

        Adventurer f1 = new Diglett("My hero");
        Adventurer f2 = new Shaymin();

        Adventurer ene1 = new CodeWarrior("villain 1");
        Adventurer ene2 = new Shaymin("Evil hedgehog");
        ArrayList<Adventurer> team1 = new ArrayList<Adventurer>();
        ArrayList<Adventurer> team2 = new ArrayList<Adventurer>();

        team2.add(ene1);
        team2.add(ene2); //t2 is enemy, t1 is player

        team1.add(f1);
        team1.add(f2);

        for(Adventurer t1: team1){
          t1.setFriends(team1);
          t1.setEnemies(team2);
        }

        for(Adventurer t2: team2){
          t2.setFriends(team2);
          t2.setEnemies(team1);
        }

        f1.setSpecial(50);
        System.out.println(f1.specialAttack(ene2));
        System.out.println(f1.support());
        System.out.println(f2.attack(ene1));
        System.out.println(ene2.attack(f1));
        System.out.println(ene2.toStringDebug());
        System.out.println(ene1.toStringDebug());
        f2.setSpecial(20);
        System.out.println(f2.specialAttack(ene2));


        System.out.println(f1.specialAttack(ene2));

        System.out.println(f1.toStringDebug());
        System.out.println(f2.toStringDebug());


        System.out.println(ene2.toStringDebug());
        System.out.println(ene1.toStringDebug());


    }
}
