public class Tester {
    public static void main(String[] args){
        Adventurer f1 = new Diglett("My hero");
        Adventurer f2 = new Diglett("My other hero");

        Adventurer ene1 = new CodeWarrior("villain 1");
        Adventurer ene2 = new CodeWarrior("villan 2");
        f1.addEnemy(ene1);
        f1.addEnemy(ene2);

        f1.addFriend(f2);

        System.out.println(f1.attack(ene2));
        System.out.println(f1.attack(ene2));
        System.out.println(f1.specialAttack(ene2));
        System.out.println(f1.support());


        System.out.println(f1.toStringDebug());
        System.out.println(f2.toStringDebug());


        System.out.println(ene2.toStringDebug());
        System.out.println(ene1.toStringDebug());


    }
}
