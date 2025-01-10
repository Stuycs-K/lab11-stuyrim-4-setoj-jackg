public class Tester {
    public static void main(String[] args){
        Adventurer testy = new Diglett("Digllet");
        Adventurer test2 = new CodeWarrior("code warr");

        System.out.println(testy.attack(test2));
        System.out.println(testy.attack(test2));
        System.out.println(testy.specialAttack(test2));


        System.out.println(testy.toStringDebug());
        System.out.println(test2.toStringDebug());


    }
}
