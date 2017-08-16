import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class HamperLeaderTest{
  public static void printPiles(Card drawCard, Card disCard)
    {
      System.out.println("Draw Pile : " + drawCard.toString());
      System.out.println("Discard Pile : " + disCard.toString());
    }

  public static void main(String[] args){
    //create the discard piles and draw piles
    DiscardPile discardPile = new DiscardPile();
  	Stack<Card> drawPile = new Stack<Card>();

    Card[] basic = { new Card("Clubs", "10"), new Card("Spades", "7"), new Card("Diamonds", "9"), new Card("Hearts", "8")};
    Card[] oneCard = {new Card("Spades", "10")};
    Card[] twoCards = {new Card("Diamonds", "Ace"), new Card("Diamonds", "Queen")};
    Card[] threeCards = {new Card("Hearts", "Queen"), new Card("Hearts", "King"), new Card("Hearts", "Jack")};
    Card[] winningHand = {new Card("Diamonds", "Ace"), new Card("Diamonds", "Queen"), new Card("Diamonds", "Queen"), new Card("Clubs", "Queen"), new Card("Clubs", "King"), new Card("Diamonds", "8")};

    HamperLeader player= new HamperLeader(basic);

    //if the next player is a leader, try to hamper the leader
    Player[] players = new Player[3];
		players[0] = new BadPlayer(twoCards);
		players[1] = new HamperLeader(basic);
		players[2] = new BadPlayer(oneCard);
    System.out.println("This is a test that the next player is a leader.");
    ArrayList<Player> people =new ArrayList<Player>(Arrays.asList(players));
		System.out.println("Player's hand : " + player.handToString());
		System.out.println("The size of previous players' hand : " + players[0].getSizeOfHand());
		System.out.println("The size of next players' hand : " + players[2].getSizeOfHand());
    discardPile.add(new Card ("Spades", "6"));
    drawPile.push(new Card ("Hearts", "9"));
    printPiles(drawPile.peek(), discardPile.top());
    players[1].play(discardPile, drawPile, people);
    printPiles(drawPile.peek(), discardPile.top());


    /*test if the previous player is a leader, try to hold on the power card. If there is no
    avalable card to play(except power cards), pick cards from draw pile.
    */
    Player[] players1 = new Player[3];
    players1[0] = new BadPlayer(oneCard);
    players1[1] = new HamperLeader(basic);
    players1[2] = new BadPlayer(twoCards);
    discardPile.add(new Card ("Spades", "6"));
    drawPile.push(new Card ("Hearts", "9"));
    System.out.println("This is a test that the previous player is a leader.");
    ArrayList<Player> people1 =new ArrayList<Player>(Arrays.asList(players1));
    System.out.println("Player's hand : " + player.handToString());
		System.out.println("The size of previous players' hand : " + players1[0].getSizeOfHand());
		System.out.println("The size of next players' hand : " + players1[2].getSizeOfHand());
    drawPile.push(new Card("Spades", "Jack"));
    printPiles(drawPile.peek(), discardPile.top());
    players[1].play(discardPile, drawPile, people1);
    printPiles(drawPile.peek(), discardPile.top());
  }
}
