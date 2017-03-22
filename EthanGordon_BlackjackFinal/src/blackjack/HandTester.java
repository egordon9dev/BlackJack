package blackjack;

/**
 * @author Ethan Gordon
 */
public class HandTester {

    /**
     * tests hand
     *
     * @param args
     */
    public static void main(String args[]) {
        Shoe shoe = new Shoe(4);
        Hand hand = new Hand();
        for (int i = 0; i < 100; i++) {
            hand.drawCard(shoe.drawCard());
            hand.drawCard(shoe.drawCard());
            for (int j = 0; j < 3 && Math.random() > 0.35; j++) {
                hand.drawCard(shoe.drawCard());
            }
            System.out.println(hand + "\n" + "val: " + hand.getVal() + "  bust: " + hand.isBust()
                    + "  charlie: " + hand.isCharlie() + "  blackjack: " + hand.isBlackjack()
                    + "  splittable: " + hand.isSplitable() + "\n");
            hand.clear();
        }
    }
}
/*
OUTPUT

4 of hearts	7 of diamonds	Queen of spades	8 of hearts	King of diamonds	
val: 39  bust: true  charlie: false  blackjack: false  splittable: false

Ace of spades	6 of clubs	4 of spades	
val: 21  bust: false  charlie: false  blackjack: false  splittable: false

3 of clubs	4 of hearts	
val: 7  bust: false  charlie: false  blackjack: false  splittable: false

7 of diamonds	2 of diamonds	7 of spades	Queen of diamonds	3 of hearts	
val: 29  bust: true  charlie: false  blackjack: false  splittable: false

Jack of hearts	9 of spades	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false

3 of clubs	Ace of clubs	Queen of hearts	8 of diamonds	9 of hearts	
val: 31  bust: true  charlie: false  blackjack: false  splittable: false

6 of clubs	5 of clubs	
val: 11  bust: false  charlie: false  blackjack: false  splittable: false

King of hearts	Ace of clubs	4 of diamonds	8 of spades	
val: 23  bust: true  charlie: false  blackjack: false  splittable: false

6 of spades	Queen of hearts	
val: 16  bust: false  charlie: false  blackjack: false  splittable: false

Ace of diamonds	7 of spades	
val: 18  bust: false  charlie: false  blackjack: false  splittable: false

10 of diamonds	6 of hearts	Jack of clubs	
val: 26  bust: true  charlie: false  blackjack: false  splittable: false

Ace of diamonds	2 of spades	
val: 13  bust: false  charlie: false  blackjack: false  splittable: false

7 of clubs	2 of diamonds	Ace of spades	9 of clubs	8 of clubs	
val: 27  bust: true  charlie: false  blackjack: false  splittable: false

6 of hearts	3 of hearts	Queen of clubs	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false

Queen of clubs	5 of diamonds	
val: 15  bust: false  charlie: false  blackjack: false  splittable: false

King of spades	Jack of clubs	10 of hearts	Ace of spades	7 of clubs	
val: 38  bust: true  charlie: false  blackjack: false  splittable: false

Jack of diamonds	5 of hearts	Ace of spades	10 of diamonds	Ace of clubs	
val: 27  bust: true  charlie: false  blackjack: false  splittable: false

5 of spades	Jack of diamonds	
val: 15  bust: false  charlie: false  blackjack: false  splittable: false

5 of spades	9 of hearts	
val: 14  bust: false  charlie: false  blackjack: false  splittable: false

4 of hearts	Queen of hearts	
val: 14  bust: false  charlie: false  blackjack: false  splittable: false

3 of diamonds	8 of clubs	
val: 11  bust: false  charlie: false  blackjack: false  splittable: false

Queen of clubs	King of clubs	3 of hearts	Queen of clubs	5 of diamonds	
val: 38  bust: true  charlie: false  blackjack: false  splittable: false

5 of clubs	2 of clubs	King of clubs	
val: 17  bust: false  charlie: false  blackjack: false  splittable: false

6 of hearts	5 of hearts	
val: 11  bust: false  charlie: false  blackjack: false  splittable: false

6 of spades	Jack of clubs	3 of spades	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false

Queen of hearts	10 of clubs	
val: 20  bust: false  charlie: false  blackjack: false  splittable: true

Jack of diamonds	8 of spades	7 of hearts	9 of clubs	Queen of diamonds	
val: 44  bust: true  charlie: false  blackjack: false  splittable: false

10 of spades	9 of hearts	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false

King of clubs	7 of diamonds	7 of spades	Ace of spades	
val: 25  bust: true  charlie: false  blackjack: false  splittable: false

6 of diamonds	Jack of clubs	3 of diamonds	5 of clubs	7 of spades	
val: 31  bust: true  charlie: false  blackjack: false  splittable: false

10 of hearts	4 of hearts	
val: 14  bust: false  charlie: false  blackjack: false  splittable: false

9 of hearts	Jack of diamonds	2 of hearts	6 of diamonds	10 of spades	
val: 37  bust: true  charlie: false  blackjack: false  splittable: false

3 of clubs	10 of spades	2 of hearts	6 of clubs	
val: 21  bust: false  charlie: false  blackjack: false  splittable: false

5 of clubs	2 of hearts	
val: 7  bust: false  charlie: false  blackjack: false  splittable: false

7 of hearts	8 of spades	
val: 15  bust: false  charlie: false  blackjack: false  splittable: false

Ace of hearts	Queen of diamonds	5 of clubs	
val: 16  bust: false  charlie: false  blackjack: false  splittable: false

Ace of diamonds	6 of clubs	10 of diamonds	3 of clubs	
val: 20  bust: false  charlie: false  blackjack: false  splittable: false

9 of spades	5 of hearts	
val: 14  bust: false  charlie: false  blackjack: false  splittable: false

6 of diamonds	Queen of spades	5 of spades	Queen of hearts	6 of clubs	
val: 37  bust: true  charlie: false  blackjack: false  splittable: false

5 of diamonds	Ace of diamonds	4 of spades	
val: 20  bust: false  charlie: false  blackjack: false  splittable: false

Ace of diamonds	7 of clubs	3 of diamonds	2 of spades	King of clubs	
val: 23  bust: true  charlie: false  blackjack: false  splittable: false

Jack of spades	Ace of spades	8 of hearts	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false

4 of clubs	7 of diamonds	Queen of hearts	8 of hearts	
val: 29  bust: true  charlie: false  blackjack: false  splittable: false

9 of spades	Queen of diamonds	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false

8 of diamonds	2 of hearts	
val: 10  bust: false  charlie: false  blackjack: false  splittable: false

4 of diamonds	5 of hearts	King of hearts	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false

King of clubs	2 of clubs	
val: 12  bust: false  charlie: false  blackjack: false  splittable: false

Ace of clubs	Ace of clubs	6 of diamonds	10 of diamonds	6 of spades	
val: 24  bust: true  charlie: false  blackjack: false  splittable: false

3 of spades	8 of hearts	
val: 11  bust: false  charlie: false  blackjack: false  splittable: false

6 of hearts	3 of spades	
val: 9  bust: false  charlie: false  blackjack: false  splittable: false

Ace of hearts	Queen of spades	4 of spades	2 of clubs	King of diamonds	
val: 27  bust: true  charlie: false  blackjack: false  splittable: false

7 of clubs	9 of hearts	5 of clubs	
val: 21  bust: false  charlie: false  blackjack: false  splittable: false

3 of hearts	10 of hearts	Jack of spades	
val: 23  bust: true  charlie: false  blackjack: false  splittable: false

2 of clubs	6 of clubs	3 of clubs	
val: 11  bust: false  charlie: false  blackjack: false  splittable: false

9 of clubs	6 of diamonds	
val: 15  bust: false  charlie: false  blackjack: false  splittable: false

8 of spades	2 of hearts	
val: 10  bust: false  charlie: false  blackjack: false  splittable: false

10 of spades	Ace of clubs	Queen of clubs	King of hearts	8 of spades	
val: 39  bust: true  charlie: false  blackjack: false  splittable: false

King of clubs	King of hearts	3 of clubs	
val: 23  bust: true  charlie: false  blackjack: false  splittable: false

7 of diamonds	4 of spades	6 of hearts	
val: 17  bust: false  charlie: false  blackjack: false  splittable: false

3 of spades	2 of diamonds	King of clubs	
val: 15  bust: false  charlie: false  blackjack: false  splittable: false

6 of hearts	9 of diamonds	
val: 15  bust: false  charlie: false  blackjack: false  splittable: false

6 of spades	Ace of spades	3 of spades	Queen of diamonds	Jack of hearts	
val: 30  bust: true  charlie: false  blackjack: false  splittable: false

Jack of clubs	Ace of diamonds	
val: 21  bust: false  charlie: false  blackjack: true  splittable: false

10 of diamonds	Queen of spades	
val: 20  bust: false  charlie: false  blackjack: false  splittable: true

6 of diamonds	4 of diamonds	10 of clubs	7 of diamonds	Jack of spades	
val: 37  bust: true  charlie: false  blackjack: false  splittable: false

Jack of clubs	Jack of diamonds	7 of spades	
val: 27  bust: true  charlie: false  blackjack: false  splittable: false

4 of hearts	2 of diamonds	7 of diamonds	
val: 13  bust: false  charlie: false  blackjack: false  splittable: false

5 of diamonds	7 of clubs	10 of diamonds	
val: 22  bust: true  charlie: false  blackjack: false  splittable: false

7 of spades	5 of clubs	9 of clubs	
val: 21  bust: false  charlie: false  blackjack: false  splittable: false

9 of diamonds	Queen of hearts	Jack of spades	
val: 29  bust: true  charlie: false  blackjack: false  splittable: false

Ace of clubs	6 of diamonds	
val: 17  bust: false  charlie: false  blackjack: false  splittable: false

5 of hearts	6 of hearts	Jack of hearts	4 of diamonds	6 of clubs	
val: 31  bust: true  charlie: false  blackjack: false  splittable: false

9 of hearts	10 of clubs	4 of hearts	Jack of clubs	
val: 33  bust: true  charlie: false  blackjack: false  splittable: false

6 of hearts	Jack of clubs	3 of diamonds	Queen of diamonds	6 of spades	
val: 35  bust: true  charlie: false  blackjack: false  splittable: false

Jack of diamonds	Ace of spades	9 of hearts	2 of diamonds	10 of diamonds	
val: 32  bust: true  charlie: false  blackjack: false  splittable: false

6 of hearts	7 of diamonds	
val: 13  bust: false  charlie: false  blackjack: false  splittable: false

Jack of clubs	Queen of hearts	
val: 20  bust: false  charlie: false  blackjack: false  splittable: true

King of clubs	Ace of hearts	8 of hearts	5 of clubs	6 of diamonds	
val: 30  bust: true  charlie: false  blackjack: false  splittable: false

8 of spades	8 of spades	
val: 16  bust: false  charlie: false  blackjack: false  splittable: true

5 of hearts	9 of spades	
val: 14  bust: false  charlie: false  blackjack: false  splittable: false

3 of spades	9 of diamonds	Ace of spades	5 of spades	Ace of diamonds	
val: 19  bust: false  charlie: true  blackjack: false  splittable: false

Jack of clubs	7 of hearts	King of hearts	5 of clubs	5 of hearts	
val: 37  bust: true  charlie: false  blackjack: false  splittable: false

Jack of clubs	Ace of hearts	10 of hearts	7 of diamonds	9 of diamonds	
val: 37  bust: true  charlie: false  blackjack: false  splittable: false

King of clubs	4 of hearts	5 of diamonds	4 of clubs	3 of spades	
val: 26  bust: true  charlie: false  blackjack: false  splittable: false

8 of clubs	3 of spades	Queen of spades	6 of hearts	Queen of spades	
val: 37  bust: true  charlie: false  blackjack: false  splittable: false

King of clubs	2 of clubs	Ace of hearts	3 of clubs	Jack of hearts	
val: 26  bust: true  charlie: false  blackjack: false  splittable: false

Ace of diamonds	Jack of diamonds	King of diamonds	Ace of spades	Queen of spades	
val: 32  bust: true  charlie: false  blackjack: false  splittable: false

9 of clubs	5 of diamonds	7 of hearts	
val: 21  bust: false  charlie: false  blackjack: false  splittable: false

8 of hearts	Ace of diamonds	Jack of hearts	Queen of diamonds	9 of clubs	
val: 38  bust: true  charlie: false  blackjack: false  splittable: false

3 of hearts	5 of diamonds	Queen of diamonds	7 of spades	4 of clubs	
val: 29  bust: true  charlie: false  blackjack: false  splittable: false

10 of hearts	2 of clubs	3 of diamonds	Jack of spades	6 of diamonds	
val: 31  bust: true  charlie: false  blackjack: false  splittable: false

Ace of diamonds	7 of spades	
val: 18  bust: false  charlie: false  blackjack: false  splittable: false

Jack of diamonds	8 of diamonds	9 of diamonds	
val: 27  bust: true  charlie: false  blackjack: false  splittable: false

7 of clubs	Queen of hearts	6 of clubs	
val: 23  bust: true  charlie: false  blackjack: false  splittable: false

2 of diamonds	King of diamonds	Queen of spades	8 of hearts	
val: 30  bust: true  charlie: false  blackjack: false  splittable: false

10 of diamonds	Jack of clubs	9 of diamonds	2 of spades	2 of spades	
val: 33  bust: true  charlie: false  blackjack: false  splittable: false

8 of hearts	6 of clubs	
val: 14  bust: false  charlie: false  blackjack: false  splittable: false

Queen of hearts	5 of hearts	King of hearts	Queen of clubs	
val: 35  bust: true  charlie: false  blackjack: false  splittable: false

Ace of diamonds	Ace of spades	9 of spades	8 of diamonds	7 of hearts	
val: 26  bust: true  charlie: false  blackjack: false  splittable: false

King of diamonds	9 of clubs	
val: 19  bust: false  charlie: false  blackjack: false  splittable: false



*/
