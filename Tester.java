import java.util.ArrayList;

public class Tester {
    public static void main(String[] args){

        Adventurer f1 = new Diglett("My hero");
        Adventurer f2 = new Shaymin();

        Adventurer b1 = new Boss("Ash Ketchup");
        Adventurer m1 = new CodeWarrior("defautl minion");
        ArrayList<Adventurer> team1 = new ArrayList<Adventurer>();
        ArrayList<Adventurer> team2 = new ArrayList<Adventurer>();

        team2.add(b1); //t2 is enemy, t1 is player

        team1.add(f1);
        team1.add(f2);
        Adventurer.setEneFriends(team1, team2);

        f1.setSpecial(50);
        f2.setSpecial(20);
        b1.setSpecial(10);

        System.out.println(b1.support());
        System.out.println(f1.toStringDebug());
        System.out.println(f2.toStringDebug());
        System.out.println(b1.toStringDebug());

    }
}
