import com.sun.media.jfxmedia.events.PlayerEvent;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Stack;

public class MindTheEights extends Player{
    /*MindTheEights should always be aware of any eights they are holding.
      This player will their eights until late in the game, but won't hold
      on to them too long (as they are worth a lot of points). Once any
      opponent goes down to one card, it's time to play your eight. If you
      have two eights, start playing them once an opponent goes down to
      two cards. (Expand this for 3 or 4 or more eights.)*/
    public MindTheEights(Card[] cards)
    {
        this.hand = new ArrayList<Card>(Arrays.asList(cards));
    }

    //decides which suit to make the eight


    public int numberOfEights()
    {
        int count = 0;
        for(Card cardInHand: this.hand)
        {
            if(cardInHand.getRank() == 8)
            {
                count++;
            }
        }
        return count;
    }

    public Card pickCard(DiscardPile discardPile, ArrayList<Player> players)
    {
        int leaderHandSize = -1;
        for(Player player: players)
        {
            if(leaderHandSize == -1){
                leaderHandSize = player.hand.size();
            }else{
                if(player.hand.size() < leaderHandSize){
                    leaderHandSize = player.hand.size();
                }
            }
        }
        if(leaderHandSize <= this.numberOfEights() && this.numberOfEights() != 0)
        {
            for(Card cardInHand: this.hand)
            {
                if(cardInHand.getRank()==8)
                {
                    return cardInHand;
                }
            }
        }
        else
        {
            //if all that's left in the players hand is eights then use them.
            if(this.hand.size() <= this.numberOfEights()){
                return this.hand.get(0);
            }
            for (Card cardInHand: this.hand)
            {
                if(cardInHand.getRank() != 8 && cardInHand.validPLay(discardPile))
                {
                    return cardInHand;
                }
            }

        }
        return null;
    }

    @Override
    public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players)
    {
        while(true)
        {
           Card playingCard = this.pickCard(discardPile, players);
           if(playingCard!=null && playingCard.getRank() == 8)
           {
               this.hand.remove(this.hand.indexOf(playingCard));
               playingCard = this.bestEight();
               discardPile.add(playingCard);
               break;
           }
           if(playingCard != null)
           {
               discardPile.add(this.hand.remove(this.hand.indexOf(playingCard)));
               break;
           }
           else
           {
               this.hand.add(drawPile.pop());
               if(drawPile.empty())
               {
                   System.out.println("The Draw Pile is empty");
                   return true;
               }
               continue;
           }
        }
        if(this.hand.size() == 0){
            return true;
        }else{
            return false;
        }
    }
}