import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
    // test();
  }

  public static void test(){
    Text.clear();
    drawBackground();
    TextBox(2, 2, 2, 4, "123456789");
    //drawText("12345678910111213141516171819202122232425262728293031323334353637383940414243444546474849650123456789101112131415161718192021222324252627282930313233343536373839404142434445464748496501234567891011121314151617181920212223242526272829303132333435363738394041424344454647484965012345678910111213141516171819202122232425262728293031323334353637383940414243444546474849650", 10, 10);

    //TextBox(10, 10, 10, 10, "ddddddddddddddddddd");
    Text.go(31,0);

  }
  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
        for (int i = 0; i < WIDTH; i++){
            Text.go(0, i);
            System.out.print(Text.colorize("@",100));
        }
        for (int i = 0; i < HEIGHT; i++){
            Text.go(i, 0);
            System.out.print(Text.colorize("@",100));
        }
        for (int i = 0; i < HEIGHT; i++){
            Text.go(i, 80);
            System.out.print(Text.colorize("@",100));
        }
        for (int i = 0; i < WIDTH + 1; i++){
            Text.go(30, i);
            System.out.print(Text.colorize("@",100));
        }
        for (int i = 0; i < WIDTH - 25; i++){
            Text.go(16, i + 2);
            System.out.print(Text.colorize("^",3));
        }
        for (int i = 0; i < WIDTH - 25; i++){
            Text.go(15, i + 2);
            System.out.print(Text.colorize("_",3));
        }
        for (int i = 0; i < HEIGHT - 2; i++){
            Text.go(i+2, 56);
            System.out.print(Text.colorize("|",3));
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
    Text.go(row, col);
    for (int i = 0; i < row; i++) {
      Text.go(row + i, col);
      for (int n = 0; n < col; n ++) {
        System.out.print(" ");
      }
      System.out.print("\n");
    }
    while(placed < text.length()){
      Text.go(row, col);
      if(text.length() - placed > width){
        System.out.print(text.substring(placed, placed + width));
        placed += width;
        System.out.print("\n");
        row++;
      }else{
        System.out.println(text.substring(placed));
        placed = text.length();
      }
    }
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int adventurerNumber = (int) (Math.random() * 3); //excludes boss
      if(adventurerNumber == 0){
        return new CodeWarrior();
      }else if(adventurerNumber == 1){
        return new Shaymin();
      }else if(adventurerNumber == 2){
        return new Diglett();
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
      for(int i = 0; i < party.size(); i++){
        TextBox(startRow, 2 + WIDTH * (i) / party.size(), 1 + WIDTH * (i + 1) / party.size(), 1, party.get(i).getName());
        TextBox(startRow + 1, 2 + WIDTH * (i) / party.size(), 1 + WIDTH * (i + 1) / party.size(), 1, "HP: " + party.get(i).getHP());
       TextBox(startRow + 2, 2 + WIDTH * (i) / party.size(), 1 + WIDTH * (i + 1) / party.size(), 1, party.get(i).getSpecialName() + ": " + party.get(i).getSpecial());

    }}


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    return output;
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer>enemies){

    drawBackground();
    TextBox(2, 2, 100, 100, "Your Party: ");

    //draw player party
    drawParty(party, 3);

    TextBox(14, 2, 100, 100, "The Enemies: ");
    drawParty(enemies, 15); //this is the scuffed part
    //draw enemy party

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
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer());
    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    //V basic party
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
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack #/special #/ support #/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      Text.go(HEIGHT - 4, 2);
      System.out.println(preprompt);
      Text.go(HEIGHT - 3, 2);

      //example debug statment
      // TextBox(24,2,80,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        input = userInput(in);
        //  public static void TextBox(int row, int col, int width, int height, String text){

        //Process user input for the last Adventurer:
        if(input.startsWith("attack") || input.startsWith("a")){
          int choice = Integer.parseInt(input.substring(input.length() - 1)) - 1; //will not work on multi-digit input.
          TextBox(HEIGHT - 2,2, WIDTH - 2, 2, party.get(whichPlayer).attack(enemies.get(choice)));
        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          int choice = Integer.parseInt(input.substring(input.length() - 1)) - 1; //will not work on multi-digit input.
          System.out.println(party.get(whichPlayer).specialAttack(enemies.get(choice)));

        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          int choice = Integer.parseInt(input.substring(input.length() - 1)) - 1; //will not work on multi-digit input.
          System.out.println(party.get(whichPlayer).support(party.get(choice)));

        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen(party, enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
