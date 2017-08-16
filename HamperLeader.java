import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class HamperLeader extends Player{
  /*will try to hamper the progress of the leader if the leader is
  either the next or previous player. If the next player is the
  leader (least amount of cards) then this player will try to
  hamper their progress by playing a power card. If the previous
  player is the leader, this player will hold on to their power
  cards until the direction of play is reversed and then hamper
  them (if this player has a seven they will change direction so
  that they can try to hamper the leader).
  */
  public HamperLeader(Card[] cards){
    this.hand= new ArrayList<Card>(Arrays.asList(cards));
  }

  //find the playable power cards in hand
  public Card playablePowerCards(DiscardPile discardPile, ArrayList players){
    ArrayList<Card> playablePowerCards = new ArrayList<Card>();
    for(Card card:this.hand){
      if(card.getSuit() == discardPile.top().getSuit() && (card.getRank()==2 || card.getRank()==4 ||card.getRank()==7)){
        playablePowerCards.add(card);
        return card;
      }
    }
    return null;
  }
  //find the playable cards expect 2,4,7
  public Card playableNormalCards(DiscardPile discardPile, ArrayList players){
    ArrayList<Card> playableNormalCards = new ArrayList<Card>();
    for(Card card:this.hand){
      if(card.getSuit() == discardPile.top().getSuit() || card.getRank()== discardPile.top().getRank()){
        if(card.getRank() !=2 && card.getRank() != 4 && card.getRank() !=7){
          playableNormalCards.add(card);
          return card;
        }
      }
    }
    return null;
  }

  //check whether the next player is a leader
  public boolean nextIsLeader(ArrayList<Player> players){
    for(int i=0; i< players.size(); i++){
      if(players.get(i).getSizeOfHand()> players.get(i+1).getSizeOfHand()){
        return true;
      }else if(players.get(players.size()-1).getSizeOfHand()> players.get(0).getSizeOfHand()){
        return  true;
      }
    }
    return false;
  }

  //check whether the previous player a leader
  public boolean preIsLeader(ArrayList<Player> players){
    for (int i=1; i< players.size()-1; i+=1){
      if(players.get(i).getSizeOfHand()> players.get(i-1).getSizeOfHand()){
        return  true;
      }else if(players.get(0).getSizeOfHand()>players.get(players.size()-1).getSizeOfHand()){
        return true;
      }
    }
    return false;
  }


  @Override
  public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
    while(true){
      Card playingCard= playablePowerCards(discardPile, players);
      Card tryingCard= playableNormalCards(discardPile,players);
      if(nextIsLeader(players)== true && playingCard != null){
        this.hand.remove(this.hand.indexOf(playingCard));
        discardPile.add(playingCard);
        break;
      }
      if(preIsLeader(players)== true && tryingCard != null){
        this.hand.remove(this.hand.indexOf(tryingCard));
        discardPile.add(tryingCard);
        break;
      }
      else{
        this.hand.add(drawPile.pop());
        continue;
      }
    }
    if (this.hand.size() == 0){
      return true;
    }else{
      return false;
    }
  }
}
