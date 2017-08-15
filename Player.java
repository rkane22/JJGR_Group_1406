import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public abstract class Player{
	protected ArrayList<Card> hand;
	
	public int getSizeOfHand()
    {
		return hand.size();
	}

	//returns an eight card with the suit that works best with the rest of the player's hand
    public Card bestEight()
    {
        String mostSuit = "";
        int most = 0;
        Hashtable<String, Integer> cardSuit = new Hashtable<String, Integer>();
        //Count the number of cards in players hand for each suit
        for(Card cardInHand: this.hand)
        {
            String suit = cardInHand.getSuit();
            if(cardSuit.containsKey(suit))
            {
                cardSuit.put(suit, cardSuit.get(suit) + 1);
            }
            else
            {
                cardSuit.put(suit, 1);
            }
        }
        //find the highest one.
        for(String key: cardSuit.keySet())
        {
            if(cardSuit.get(key) > most) {
                mostSuit = key;
                most = cardSuit.get(key);
            }
        }
        return new Card(mostSuit, "8");
    }

    public String handToString()
    {
        String handString = "[";
        for(int i = 0; i < this.hand.size(); i++)
        {
            if(i == this.hand.size() - 1)
            {
                handString += this.hand.get(i).toString();
                handString += "]";
                break;
            }
            handString += (this.hand.get(i).toString() + ", ");
        }
        return handString;
    }

	/* play a card  */
	public abstract boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players);
	// return true if player wins game by playing last card
	// returns false otherwise
	// side effects: plays a card to top of discard Pile, possibly taking zero
	//               or more cards from the top of the drawPile
	//               card played must be valid card
	
}