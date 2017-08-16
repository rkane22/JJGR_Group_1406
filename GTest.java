import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GTest
{


    
  
  public static void printHand(Player player){
  System.out.println("Player's Hand: " + player.handToString());
  }
  
  public static void testPrint(Player player, String expectedVal, String actualVal)
    {

        System.out.println("Players Hand : " + player.handToString());
        System.out.println("Expected : " + expectedVal);
        System.out.println("Actual : " + actualVal);
    }

    public static void printPiles(Card drawCard, Card disCard)
    {
        System.out.println("Draw Pile : " + drawCard.toString());
        System.out.println("Discard Pile : " + disCard.toString());
    }

    public static void main(String[] args)
    {
        //create the necessary piles
        DiscardPile discardPile = new DiscardPile();
        discardPile.add(new Card("Hearts", "2"));
        Stack<Card> drawPile = new Stack<Card>();
        drawPile.push(new Card("Spades", "9"));
        drawPile.push(new Card("Hearts", "8"));
          
        //create example hands for the opponents and player
        Card[] basic = { new Card("Clubs", "10"), new Card("Spades", "7"), new Card("Diamonds", "9"), new Card("Hearts", "8"), new Card("Hearts", "6")};
        Card[] oneCard = {new Card("Spades", "10")};
        Card[] twoCards = {new Card("Diamonds", "Ace"), new Card("Diamonds", "Queen")};
        Card[] threeCards = {new Card("Hearts", "Queen"), new Card("Hearts", "King"), new Card("Hearts", "Jack")};
        Card[] onlyEights = {new Card("Hearts", "8"), new Card("Diamonds", "8"), new Card("Spades", "8")};
        Card[] winningHand = {new Card("Diamonds", "Ace"), new Card("Diamonds", "Queen"), new Card("Diamonds", "Queen"), new Card("Clubs", "Queen"), new Card("Clubs", "King"), new Card("Diamonds", "8")};
        //create new MindTheEights player
        DiscardHighPoints dhp1;

        //shows it is aware of power cards (excluding 8s) it is holding
        dhp1 = new DiscardHighPoints(basic);
        String expectedVal = "1";
        String actualVal = Integer.toString(dhp1.numOfPC(dhp1.hand));
        System.out.println("Test how many Power Cards (excluding 8s) player is holding");
        testPrint(dhp1, expectedVal, actualVal);
        dhp1.hand.add(new Card("Spades", "2"));
        testPrint(dhp1, "2", Integer.toString(dhp1.numOfPC(dhp1.hand)));

        //show if the ArrayList is sorted in descending order
        dhp1.updateHand(dhp1.hand);
        System.out.println ("\nTest to find out if hand is sorted in descending order of points");
        printHand(dhp1);
        
      //Test to show that Player will player their number 8 first thing
        BadPlayer bp1 = new BadPlayer (basic);
        System.out.println ("BadPlayer's Hand contain: " + bp1.handToString());
        
        
         Player[] players = new Player[2];
         players[0] = dhp1;
         players[1] = bp1;
        ArrayList<Player> people = new ArrayList<Player> (Arrays.asList(players));
        dhp1.play (discardPile, drawPile, people);
        printHand(dhp1);
        System.out.println ("Discard pile: " + discardPile.top());
         printHand(dhp1);
    }
}
        