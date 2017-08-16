import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class DiscardHighPoints extends Player{

 //constructor
  public DiscardHighPoints(Card[] cards){
  this.hand = new ArrayList<Card> (Arrays.asList(cards));
  }

  //Update ArrayList Hand whenver a change is made
  //Sort array list by highest point
    public void updateHand(ArrayList<Card> arrList){
  Collections.sort(this.hand, (card1, card2) -> card2.getPoint()- card1.getPoint());
  }
  
 //Count the num of power Cards (excluding 8s)
    public int numOfPC(ArrayList<Card> arrList){
      int count =0;
      for(Card cardInHand: this.hand){
      if (cardInHand.getRank() == 2||
          cardInHand.getRank() == 4||
          cardInHand.getRank() == 7){
       count++; 
      } 
      }
      return count;
    }
  

  @Override
  public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
    
    Card discardTop = discardPile.top();
    updateHand(this.hand);
    
    //do
    
    while(true) {
      //condition when 7 is at the top of the dicard pile
      if (discardPile.top().getRank()==7 && Crazy8Game.reverseCount==0){
      Crazy8Game.reverseCount+=1;
      return false;}
      else if (discardPile.top().getRank()==7 && Crazy8Game.reverseCount==1){
    Crazy8Game.reverseCount=0;
    }
     
      
      //Condition when 4 is at the top of the discard pile
      if(discardPile.top().getRank()==4 && Crazy8Game.card4==0){
    Crazy8Game.card4 =1;
    return false;
    }
    else if(discardPile.top().getRank()==4 && Crazy8Game.card4==1){Crazy8Game.card4=0;}
    
    
    //condition when 2 is at the top of the discard pile
    if (discardPile.top().getRank()==2 && Crazy8Game.card4==0){
    Crazy8Game.card2 =1; 
    if (drawPile.empty()){return false;}
    hand.add(drawPile.pop());
    if (drawPile.empty()){return false;}
    hand.add(drawPile.pop());
    updateHand(this.hand);}
    else if(discardPile.top().getRank()==4 && Crazy8Game.card2==1){Crazy8Game.card4 =0;}
    
    
    //if hand contains 8 discard it right away
    if (hand.get(0).getRank()==8){
       discardPile.add(hand.get(0));
       hand.remove(0);
       
       if( this.hand.size() == 0 ){return true;}
       else {
         updateHand (this.hand);
         return false;}
     }
    
    int pCount = numOfPC(this.hand);
    
    if (pCount <hand.size() && pCount!=0){
    //condition when hand contains power card
    if (pCount>0){
    
    //Has Power Card? ---> yes
      //Discard them directly? ---> yes
      for (int i=0; i<=pCount; i++){
         if(hand.get(i).getSuit().equals(discardPile.top().getSuit()) ||
             hand.get(i).getRank()==discardPile.top().getRank()){
         discardPile.add(hand.get(i));
         hand.remove(i);
         
         if( this.hand.size() == 0 ){return true;}
         else {updateHand (this.hand);
           return false;}
         }       
       }
      
      //Has Power Card? ---> yes
       //Discard them directly? ---> No
        //Induce suit change through non power card
      
      for (int i=pCount; i<hand.size(); i++){
        //here int j represents the location of all the power cards
         for (int j=0; j<pCount; j++){
         if (hand.get(i).getRank()==discardPile.top().getRank() && 
             hand.get(i).getSuit().equals(hand.get(j).getSuit())){
           discardPile.add(hand.get(i));
           hand.remove(i);
           
           if( this.hand.size() == 0 ){return true;}
           else {updateHand (this.hand);
             return false;}
           }         
         }       
       }
      
      
      //If not able to induce change to highest point card suit, then play highest non power card
      
      for (int i=0; i<hand.size(); i++){
         if(hand.get(i).getRank()== discardPile.top().getRank() ||
            hand.get(i).getSuit().equals(discardPile.top().getSuit())){
           discardPile.add(hand.get(i));
           hand.remove(i);
           
           if( this.hand.size() == 0 ){return true;}
           else {updateHand (this.hand);
             return false;}         
         }         
       }
      }
      
      //still if nothing could be played, then pick one card up
      if (!drawPile.empty()){
        if (drawPile.empty()){return false;}
       this.hand.add(drawPile.pop());
       updateHand(this.hand);
       break;
       }
       else{return false;}
      
      
    } 
    
    //If hand does not contain power card
    else{
      for (int i =0; i<this.hand.size(); i++){
         if(hand.get(i).getSuit().equals(discardTop.getSuit()) ||
            hand.get(i).getRank()== discardTop.getRank()){
           discardPile.add(hand.get(i));
           hand.remove(i);
           if( this.hand.size() == 0 ){return true;}
           else {updateHand (this.hand);
             return false;}           
         }         
       }
       if (!drawPile.empty()){
         if (drawPile.empty()){return false;}
       hand.add(drawPile.pop());
       updateHand(this.hand); 
       
       }    
    }
    if(this.hand.size()==0){return true;}
    if (drawPile.empty()){return false;}
    
    updateHand(this.hand);
    }
    return false;
  }
}
    
   // while(discardTop.equals(discardPile.top()) && !drawPile.empty());
   //------------------------------------------------------------------------------------------- 
    
 