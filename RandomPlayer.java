import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class RandomPlayer extends Player{
    //RandomPlayer should play a random valid card.

    public RandomPlayer(Card[] cards){
        this.hand = new ArrayList<Card>(Arrays.asList(cards));
    }

    public static Card randomValidCard(ArrayList<Card> cards, DiscardPile discardPile){
        //create list to keep track of cards already tried but were invalid
        ArrayList<Card> tried = new ArrayList<Card>(cards.size());
        for(int i = 0; i < cards.size(); i++) {
            //get random number between 0 and size of the list of cards
            int rand = (int) (Math.random() * cards.size());
            //create card at the index of the random number
            Card trying = cards.get(rand);
            //see if it is valid and if it is return it
            if (!tried.contains(trying) && trying.validPLay(discardPile)) {
                return trying;
            }
        }
        return null;

    }
    @Override
    public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
        while(true){
            Card playingCard = randomValidCard(this.hand, discardPile);
            if(playingCard == null){
                this.hand.add(drawPile.pop());
                if(drawPile.empty())
                {
                    System.out.println("The Draw Pile is empty");
                    return true;
                }
                continue;
            }else{
                discardPile.add(this.hand.remove(hand.indexOf(playingCard)));
                break;
            }
        }
        if(this.hand.size() == 0){
            return true;
        }else{
            /*if(this.getSizeOfHand() == 1){
                System.out.println("knock");
            }*/
            return false;
        }

    }
}