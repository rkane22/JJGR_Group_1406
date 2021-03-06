import java.util.HashMap;

import jdk.nashorn.internal.runtime.OptimisticBuiltins;

public class Card implements Comparable<Card>{
  
  /* handy arrays for ranks and suits    */
  /* do NOT change these                 */
  public final static String[] RANKS = { "None", "None", 
    "2", "3", "4", "5", "6", "7", "8", "9", "10", 
    "Jack", "Queen", "King", "Ace"};
  public final static String[] SUITS = { "Diamonds", 
    "Clubs", "Hearts", "Spades", "None"};
  
 protected String suit;
 protected String rank;
 protected int point;
 protected HashMap<String, Integer> rankValue;
 
 /** creates a card with specified suit and rank
  * 
  * @param suit is the suit of the card (must be a string from Card.SUITS)
  * @param rank is the rank of the card (must be a string from Card.RANKS)
  */
  public Card(String suit, String rank){
   // assume input is valid!
   this.suit = suit; 
   this.rank = rank;
   this.point = 0;
   this.rankValue = new HashMap<String,Integer>(15);
   for(int r = 2; r < RANKS.length; r+=1){
    this.rankValue.put(RANKS[r], r);
   }
  }
  
  //set point of card according to the point system
  //assigning points to the card
  public int getPoint(){
  if(this.getRank()==8){this.point=50;}
  else if (this.getRank()==2 ||this.getRank()==4){this.point=25;}
   else if (this.getRank() ==7) {this.point=20;}
   else if (this.getRank() ==11 ||
            this.getRank() ==12 ||
            this.getRank() ==13) {this.point=10;}
   else if (this.getRank() ==14) {this.point=1;}
   else {this.point = this.getRank();}
   
   return this.point;
  }
  
    
 //takes discard pile as input
 //returns true if the card is a valid play and false if it is not
 public boolean validPLay(DiscardPile top){
  Card topCard = top.top();
  if(topCard.suit.equals(this.suit)){
   return true;
  }else{
   if(topCard.rank.equals(this.rank)||this.rank.equals(Card.RANKS[8])){
    return true;
   }else{
    return false;
   }
  }
 }
  
  /** the numerical representation of the rank of the current card
  *  <p>
  * ranks have the numerical values
  *  2 = 2, 3 = 3, ..., 10 = 10
   *  Jack = 11, Queen = 12, King = 13, Ace = 14
   *  
  * @return the numerical rank of this card 
  */
  public int getRank(){
  if(this.rank.equals(RANKS[0])){ return -1; }   // "no card"
  return rankValue.get(this.rank);
 }
 
 /** the string representation of the rank of the current card 
  *
  * @return the string representation of the rank of this card (must be a string from Card.RANKS) 
  */
 public String getRankString(){ return this.rank; }
  
 
 /** the suit of the current card 
  *
  * @return the suit of this card (must be a string from Card.SUITS) 
  */
 public String getSuit(){ return this.suit; }
  
  @Override
 public int compareTo(Card other){
  if(this.suit.equals(other.suit) && this.getRank() == other.getRank()){
   return 0;
  }else if(this.suit.equals(other.suit) && this.getRank() != other.getRank()){
   if(this.getRank() > other.getRank()){
    return 1;
   }else{
    return -1;
   }
  }else{
   if(this.getRank() > other.getRank()){
    return 2;
   }else{
    return -2;
   }
  }
 }
  
  @Override
  public final String toString(){
  // outputs a string representation of a card object
    if(this.rank.equals(RANKS[0])){ 
   return "no card"; 
  }
  
    int r = getRank();
    if( r >= 2 && r <= 14 ){
      return r + getSuit().substring(0,1);
  }
    return "no card";
  }
  
}

