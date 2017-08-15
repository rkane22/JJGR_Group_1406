import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Test
{


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
        discardPile.add(new Card("Hearts", "5"));
        Stack<Card> drawPile = new Stack<Card>();
        drawPile.push(new Card("Spades", "9"));

        //create example hands for the opponents and player
        Card[] basic = { new Card("Clubs", "10"), new Card("Spades", "7"), new Card("Diamonds", "9"), new Card("Hearts", "8"), new Card("Hearts", "6")};
        Card[] oneCard = {new Card("Spades", "10")};
        Card[] twoCards = {new Card("Diamonds", "Ace"), new Card("Diamonds", "Queen")};
        Card[] threeCards = {new Card("Hearts", "Queen"), new Card("Hearts", "King"), new Card("Hearts", "Jack")};
        Card[] onlyEights = {new Card("Hearts", "8"), new Card("Diamonds", "8"), new Card("Spades", "8")};
        Card[] winningHand = {new Card("Diamonds", "Ace"), new Card("Diamonds", "Queen"), new Card("Diamonds", "Queen"), new Card("Clubs", "Queen"), new Card("Clubs", "King"), new Card("Diamonds", "8")};
        //create new MindTheEights player
        MindTheEights player;

        //shows it is aware of any eights they are holding
        player = new MindTheEights(basic);
        String expectedVal = "1";
        String actualVal = Integer.toString(player.numberOfEights());
        System.out.println("This is a test to see if the player is aware of the eights it is holding");
        testPrint(player, expectedVal, actualVal);
        player.hand.add(new Card("Spades", "8"));
        testPrint(player, "2", Integer.toString(player.numberOfEights()));

        //will play their eight if another player goes down to one card
        BadPlayer opponent1 = new BadPlayer(oneCard);
        ArrayList<Player> people = new ArrayList<Player>();
        people.add(opponent1);
        System.out.println("This is a test to see if the player will play an eight if playing against a player that only has one card left");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        printPiles(drawPile.peek(), discardPile.top());

        //works for 4 players as well
        BadPlayer opponent2 = new BadPlayer(twoCards);
        BadPlayer opponent3 = new BadPlayer(threeCards);
        people.add(opponent2);
        people.add(opponent3);
        discardPile.add(new Card("Spades", "10"));
        player.hand.add(new Card("Hearts", "8"));
        System.out.println("This is to see if the player will play an eight if playing against 4 players, and one of them has a single card left");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        printPiles(drawPile.peek(), discardPile.top());

        //same thing for if it has 2 eights
        people.remove(opponent1);
        player.hand.add(new Card("Hearts", "8"));
        player.hand.add(new Card("Spades", "8"));
        discardPile.add(new Card("Spades", "10"));
        System.out.println("This is to see if the player will play one of their two 8's if playing against two players, and one of them has two cards left");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        printPiles(drawPile.peek(), discardPile.top());

        //if it has only eights it will play them regardless of the other players cards
        MindTheEights player2 = new MindTheEights(onlyEights);
        BadPlayer opponent4 = new BadPlayer(basic);
        BadPlayer opponent5 = new BadPlayer(basic);
        people.clear();
        people.add(opponent4);
        people.add(opponent5);
        discardPile.add(new Card("Spades", "10"));
        System.out.println("This is to see if the player will play their 8's if it is all they have left even if the other players have plenty of cards left");
        System.out.println("Players hand : " + player2.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player2.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        printPiles(drawPile.peek(), discardPile.top());

        //it will draw cards even if it is able to play the eight.
        player.hand.add(new Card("Hearts", "8"));
        discardPile.add(new Card("Spades", "10"));
        player.hand.remove(0);
        drawPile.push(new Card("Spades", "4"));
        System.out.println("This is to see if the player will draw rather than play an 8 in order to save their 8's if they are not required");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        printPiles(drawPile.peek(), discardPile.top());

        //it will not play its eight if the players have more than one card
        System.out.println("Players hand : " + player.handToString());
        player.hand.clear();
        player.hand.addAll(Arrays.asList(winningHand));
        discardPile.add(new Card("Diamonds", "10"));
        System.out.println("This is to see if the player will save their 8's unless they are supposed to play them");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
        player.play(discardPile, drawPile, people);
        System.out.println("Player plays");
        System.out.println("Players hand : " + player.handToString());
        printPiles(drawPile.peek(), discardPile.top());
    }
}