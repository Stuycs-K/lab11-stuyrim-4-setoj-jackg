import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;
  private static final int LEFT_SIZE = 56;
  public static void main(String[] args) {
    Text.clear();
    run();
    // test();
  }

  public static void test(){
    drawBackground();
    // TextBox(2, 2, 2, 4, "123456789");
    //drawText("12345678910111213141516171819202122232425262728293031323334353637383940414243444546474849650123456789101112131415161718192021222324252627282930313233343536373839404142434445464748496501234567891011121314151617181920212223242526272829303132333435363738394041424344454647484965012345678910111213141516171819202122232425262728293031323334353637383940414243444546474849650", 10, 10);

    //TextBox(10, 10, 10, 10, "ddddddddddddddddddd");
    Text.go(31,0);

  }
  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){

        Text.go(0, 1);
        System.out.print(Text.colorize("█".repeat(WIDTH), 100));
        for (int i = 0; i < HEIGHT; i++){
            Text.go(i, 0);
            System.out.print(Text.colorize("█",100));
        }
        for (int i = 0; i < HEIGHT; i++){
            Text.go(i, 80);
            System.out.print(Text.colorize("█",100));
        }

        Text.go(30, 1);
        System.out.print(Text.colorize("█".repeat(WIDTH), 100));
        Text.go(25, 1);
        System.out.print(Text.colorize("█".repeat(WIDTH), 100));
        Text.go(13, 1);
        System.out.print(Text.colorize("█".repeat(WIDTH - 24), 100));
        Text.go(16, 2);
        System.out.print(Text.colorize("^".repeat(23), 100));

        Text.go(15, 2);
        System.out.print(Text.colorize("-".repeat(23), 100));
        for (int i = 0; i < HEIGHT - 6; i++){
            Text.go(i+2, 56);
            System.out.print(Text.colorize("█",3));
        }

  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){

    if (startCol + s.length() < WIDTH){
      Text.go(startRow, startCol);
      System.out.print(s);
    }
    else {
      Text.go(startRow, startCol);
      System.out.print(s.substring(0, WIDTH - startCol));
      System.out.print("\n");
      for (int i = 0; i < (startCol + s.length()) / WIDTH; i++){
        if (startCol + 1 + i < HEIGHT){
          Text.go(startRow + i + 1, 2);
          if (s.substring(WIDTH - startCol + WIDTH * i).length() < WIDTH) {
            System.out.print(s.substring(WIDTH - startCol + WIDTH * i));
          }
          else {
            System.out.print(s.substring(WIDTH - startCol + WIDTH * i, WIDTH - startCol + WIDTH * (i + 1) - 2));
          }
          System.out.print("\n");
        }

      }
    }


  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){

    int placed = 0;

    if(text.length() > width * height){
      text = text.substring(0, width * height);
    }
    Text.go(row, col);
    for (int i = 0; i < height; i++) {
      Text.go(row + i, col);
      System.out.print(" ".repeat(width));
    }

    while(placed < text.length()){
      Text.go(row, col);
      if(text.length() - placed > width){
        System.out.print(text.substring(placed, placed + width));
        placed += width;
        // System.out.print("\n");
        row++;
      }else{
        System.out.print(text.substring(placed));
        placed = text.length();
      }
    }
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int adventurerNumber = (int) (Math.random() * 3); //excludes boss
      if(adventurerNumber == 0){
        return new CodeWarrior("Bad codewarior");
      }else if(adventurerNumber == 1){
        return new Shaymin("Bad Shaymin");
      }else if(adventurerNumber == 2){
        return new Diglett("Bad Diglett");
      }
      System.out.println("something went wrong");
      return null;
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){
      double printableWidth = LEFT_SIZE - 2;
      for(int i = 0; i < party.size(); i++){
        TextBox( startRow,(int)  ( 2 + printableWidth * (i) / party.size()), (int) ( printableWidth / party.size()), 1, party.get(i).getName());
        TextBox(startRow + 1,(int)  ( 2 + printableWidth * (i) / party.size()),(int) ( printableWidth / party.size()), 1, "HP: " + colorByPercent(party.get(i).getHP(), party.get(i).getmaxHP()));
       TextBox(startRow + 2, (int)  (2 + printableWidth * (i) / party.size()), (int)  (printableWidth / party.size()), 1, party.get(i).getSpecialName() + ": " + party.get(i).getSpecial());
    }

  }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = "";
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    
    // under 75% : yellow
    output = hp + " / " + maxHP;

    if ((float) hp  / maxHP < .75) {
      output = Text.colorize(hp+ "/" + maxHP,  43 );
    }
    // otherwise : white
    if ((float) hp  / maxHP < .25) {
      
      output = Text.colorize(hp+ "/" + maxHP, 41 );
    }
    if(hp == 0){
      output = Text.colorize(hp + "/" + maxHP, Text.CYAN);
    }
    return output;
  }




  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer>enemies, ArrayList<String> pastActions){
    Text.clear();
    drawBackground();
    TextBox(2, 2, LEFT_SIZE -2, 1, "Your Party: ");

    //draw player party
    drawParty(party, 3);

    TextBox(14, 2, LEFT_SIZE -2, 1, "The Enemies: ");
    drawParty(enemies, 15);
    //height is 30 

    for(int i = 0; i < pastActions.size(); i++){
      TextBox((i  * (HEIGHT - 7))/pastActions.size() + 2 , LEFT_SIZE + 1, WIDTH - (LEFT_SIZE + 1), (HEIGHT - 7) / pastActions.size(), pastActions.get(i));
    }
  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location

      //show cursor

      String input = in.nextLine();

      //clear the text that was written

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }


  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    int numEnemies = (int) (Math.random() * 3) + 1;
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    if(numEnemies == 1){
      enemies.add(new Boss());
    }else{
      for(int i = 0; i < numEnemies; i++){
        enemies.add(createRandomAdventurer());

      }
    }

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.

    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    party.add(new Diglett());
    party.add(new CodeWarrior());
    party.add(new Shaymin());
    //Note to self: This party system and other party system is EXTREMELY scuffed
    Adventurer.setEneFriends(party, enemies);

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    int dead = 0;
    ArrayList<String> log = new ArrayList<String>();
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies, log);//initial state.

    //Main loop

    //display this prompt at the start of the game.


    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
     
      if(partyTurn){

        if(!party.get(whichPlayer).isDead()){
        try{
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit ";
        TextBox(HEIGHT - 4, 2,  WIDTH - 2, 3, prompt);
        Text.go(HEIGHT - 2,2);
        input = userInput(in);

        //Process user input for the last Adventurer:
        if(input.startsWith("attack") || input.startsWith("a")){
          int choice = Integer.parseInt(input.substring(input.length() - 1)) - 1; //will not work on multi-digit input.
          String action = party.get(whichPlayer).attack(enemies.get(choice));
          // TextBox(HEIGHT - 1,2, WIDTH - 2, 2, action);
          // log.add(Text.colorize("Party: ", Text.GREEN) + action);
          log.add("Party: " + action);

        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          int choice = Integer.parseInt(input.substring(input.length() - 1)) - 1; //will not work on multi-digit input.
          String action = party.get(whichPlayer).specialAttack(enemies.get(choice));
          // TextBox(HEIGHT - 1,2, WIDTH - 2, 2, action);
          log.add("Party: " + action);
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          int choice = Integer.parseInt(input.substring(input.length() - 1)) - 1; //will not work on multi-digit input.
          String action = party.get(whichPlayer).support(party.get(choice));
          // TextBox(HEIGHT - 1,2, WIDTH - 2, 2, action);
          log.add("Party: " + action);

        }
        else{
          TextBox(HEIGHT - 4,2, WIDTH - 2, 2, "Invalid input. Make sure your input is in form 'command target' like su 1. Press Enter before inputting your new command.");
          TextBox(HEIGHT - 2, 2, WIDTH - 2, 1, " ");

          Text.go(HEIGHT - 2,2);
          input = userInput(in);
          continue;
        }
      }
        catch(NumberFormatException | IndexOutOfBoundsException e){
          TextBox(HEIGHT - 4,2, WIDTH - 2, 2, "Invalid input. Make sure your input is in form 'command target' like su 1. Press Enter before inputting your new command.");
          TextBox(HEIGHT - 2, 2, WIDTH - 2, 1, " ");
          Text.go(HEIGHT - 2,2);
          input = userInput(in);
          continue;
        }
      }
        if(log.size() > 3){
          log.remove(0);
        }
        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer >= party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";
          // TextBox(HEIGHT - 4,2, WIDTH - 2, 2, prompt);
          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!
        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        ArrayList<String> choices = new ArrayList<String>();
        choices.add("atk");
        choices.add("su");
        choices.add("sp");
        int actionChoice = (int) (Math.random() * choices.size());

        //Decide where to draw the following prompt:
        String prompt = "press enter to see enemy " +  enemies.get(whichOpponent) + "'s turn";
        TextBox(HEIGHT - 4,2, LEFT_SIZE - 2, 1, prompt);
        Text.go(HEIGHT - 2, 1);
        input = userInput(in);
        if(choices.get(actionChoice).equals("atk")){
          int target  = (int) (Math.random() * party.size());
          // String action = Text.colorize("Enemy: ", Text.RED)+ enemies.get(whichOpponent).attack(party.get(target));
          String action = "Enemy: "+ enemies.get(whichOpponent).attack(party.get(target));
          log.add(action);

        }
        if(choices.get(actionChoice).equals("su")){
          int target  = (int) (Math.random() * enemies.size());
          // String action = Text.colorize("Enemy: ", Text.RED) + enemies.get(whichOpponent).support(enemies.get(target));
          String action = "Enemy: "+ enemies.get(whichOpponent).attack(party.get(target));
          log.add(action);

        }
        if(choices.get(actionChoice).equals("sp")){
          int target  = (int) (Math.random() * party.size());
          // String action = Text.colorize("Enemy: ", Text.RED) + enemies.get(whichOpponent).specialAttack(party.get(target));
          String action = "Enemy: "+ enemies.get(whichOpponent).attack(party.get(target));
          log.add(action);
        }
        if(log.size() > 3){
          log.remove(0);
        }
        drawScreen(party, enemies, log);
        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
      }

      //display the updated screen after input has been processed.
      for(int i = 0; i < enemies.size(); i++){
        if(enemies.get(i).getHP() <= 0){
          enemies.remove(i);
        }
      }
      for(int i = 0; i < party.size(); i++){
        if(party.get(i).getHP() <= 0){
          party.get(i).kill();
          party.get(i).setHP(0);
          dead++;
        }
      }
      
      if(enemies.size() == 0){
        log.add(Text.colorize("You win! yay!. Press enter to quit", Text.GREEN));
        drawScreen(party, enemies, log);
        // System.out.println("Game over. Press enter to quit");
        String a = userInput(in);
        break;
      }else if( dead == party.size() ){
        log.add(Text.colorize("You lose. Press enter to quit", Text.RED));
        drawScreen(party, enemies, log);
        String a = userInput(in);
        break;
      }

      drawScreen(party, enemies, log);

    }
  

    //After quit reset things:
    quit();
  }
}
