package blackjack;

/**
 * @author Ethan Gordon
 */
public class DealerTester {

    /**
     * test dealer
     * @param args
     */
    public static void main(String args[]) {
        Shoe shoe = new Shoe(4);
        Dealer dealer = new Dealer();
        for (int i = 0; i < 100; i++) {
            while (dealer.isActive()) {
                dealer.getHand().drawCard(shoe.drawCard());
                dealer.updateActivity();
            }
            System.out.println(dealer.getHand());
            if (Math.random() > 0.9) {
                System.out.println(dealer);
            }
            dealer.nextRound();
        }
    }
}
/*


Queen of hearts	2 of spades	7 of diamonds	
9 of diamonds	3 of hearts	2 of diamonds	King of clubs	
Jack of spades	4 of clubs	4 of spades	
??????	4 of clubs	4 of spades	
Queen of spades	Ace of hearts	
2 of diamonds	Ace of spades	King of clubs	Ace of diamonds	4 of diamonds	
4 of hearts	Jack of diamonds	3 of clubs	
??????	Jack of diamonds	3 of clubs	
5 of hearts	7 of hearts	Ace of clubs	King of diamonds	
9 of hearts	10 of hearts	
4 of clubs	7 of hearts	Ace of hearts	3 of clubs	6 of hearts	
7 of clubs	3 of diamonds	8 of clubs	
5 of clubs	Queen of hearts	2 of hearts	
10 of diamonds	3 of spades	Queen of hearts	
King of diamonds	6 of clubs	8 of diamonds	
Jack of diamonds	Jack of hearts	
King of clubs	6 of diamonds	2 of diamonds	
7 of spades	King of hearts	
6 of hearts	5 of spades	King of spades	
6 of spades	2 of spades	6 of spades	7 of hearts	
3 of diamonds	2 of diamonds	2 of clubs	King of spades	
Queen of spades	3 of hearts	6 of hearts	
Ace of spades	4 of clubs	9 of diamonds	9 of clubs	
6 of diamonds	2 of hearts	5 of hearts	Jack of clubs	
King of diamonds	King of clubs	
6 of diamonds	10 of clubs	4 of hearts	
??????	10 of clubs	4 of hearts	
9 of clubs	5 of clubs	Queen of hearts	
10 of spades	Jack of hearts	
8 of clubs	4 of spades	2 of spades	8 of hearts	
5 of hearts	Ace of diamonds	8 of diamonds	Jack of clubs	
4 of spades	King of spades	King of diamonds	
3 of clubs	King of spades	Jack of hearts	
7 of hearts	2 of hearts	Ace of clubs	
10 of spades	6 of spades	10 of clubs	
9 of hearts	7 of clubs	Queen of diamonds	
King of hearts	10 of hearts	
Queen of clubs	5 of diamonds	9 of hearts	
6 of diamonds	Jack of spades	8 of clubs	
4 of diamonds	Ace of spades	2 of diamonds	
2 of hearts	10 of spades	9 of hearts	
4 of spades	3 of spades	4 of clubs	3 of diamonds	9 of clubs	
5 of hearts	10 of hearts	Queen of clubs	
9 of spades	6 of hearts	4 of hearts	
Ace of clubs	5 of diamonds	8 of hearts	3 of hearts	
9 of clubs	7 of spades	6 of hearts	
8 of diamonds	4 of hearts	5 of spades	
4 of clubs	Jack of hearts	King of spades	
4 of hearts	2 of diamonds	Ace of hearts	
2 of hearts	9 of spades	Ace of diamonds	9 of spades	
Jack of diamonds	7 of hearts	
8 of diamonds	7 of hearts	Ace of hearts	5 of spades	
8 of clubs	3 of spades	10 of clubs	
4 of diamonds	King of clubs	Jack of spades	
King of hearts	9 of diamonds	
7 of hearts	3 of diamonds	9 of hearts	
Ace of hearts	6 of spades	
3 of diamonds	4 of spades	5 of spades	2 of clubs	6 of clubs	
7 of diamonds	10 of clubs	
2 of hearts	Jack of clubs	King of hearts	
Queen of hearts	8 of hearts	
2 of hearts	Queen of hearts	6 of spades	
3 of spades	4 of diamonds	9 of clubs	Queen of diamonds	
6 of diamonds	Jack of clubs	5 of hearts	
4 of hearts	4 of hearts	5 of hearts	8 of spades	
King of diamonds	6 of diamonds	Ace of spades	
5 of spades	King of clubs	5 of diamonds	
10 of diamonds	7 of diamonds	
8 of spades	2 of clubs	5 of clubs	3 of clubs	
??????	2 of clubs	5 of clubs	3 of clubs	
3 of spades	9 of spades	6 of diamonds	
7 of diamonds	7 of clubs	3 of diamonds	
10 of spades	5 of clubs	5 of hearts	
3 of diamonds	5 of diamonds	Jack of spades	
2 of diamonds	10 of spades	Jack of hearts	
4 of clubs	7 of diamonds	Jack of diamonds	
9 of diamonds	6 of spades	3 of clubs	
7 of diamonds	4 of diamonds	Ace of hearts	10 of diamonds	
7 of hearts	5 of diamonds	4 of diamonds	Ace of hearts	
6 of diamonds	4 of spades	Jack of spades	
7 of spades	2 of hearts	Ace of spades	
7 of spades	6 of clubs	3 of hearts	2 of spades	
King of diamonds	4 of diamonds	3 of clubs	
4 of clubs	9 of diamonds	3 of hearts	8 of spades	
2 of diamonds	7 of clubs	7 of spades	3 of hearts	
King of hearts	4 of spades	Ace of spades	King of hearts	
3 of clubs	5 of clubs	Queen of hearts	
??????	5 of clubs	Queen of hearts	
Jack of hearts	9 of hearts	
6 of diamonds	Queen of clubs	King of diamonds	
2 of clubs	Ace of diamonds	6 of diamonds	
Queen of clubs	Queen of spades	
Jack of clubs	4 of spades	Jack of clubs	
9 of hearts	8 of hearts	
10 of spades	Queen of hearts	
3 of diamonds	9 of clubs	7 of hearts	
6 of clubs	King of clubs	4 of spades	
8 of spades	8 of clubs	6 of hearts	
9 of spades	6 of spades	6 of spades	
Ace of spades	Queen of hearts	
8 of spades	6 of clubs	7 of spades	
4 of diamonds	Jack of diamonds	Ace of hearts	6 of clubs	
Jack of spades	10 of hearts	
Jack of spades	4 of hearts	King of hearts	
10 of spades	3 of spades	King of diamonds	


*/
