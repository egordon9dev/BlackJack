package blackjack;

/**
 * @author Ethan Gordon
 */
public class PlayerTester {

    /**
     * tests player
     *
     * @param args
     */
    public static void main(String args[]) {
        Shoe shoe = new Shoe(4);
        for (int i = 0; i < 8; i++) {
            Player player = new Player();
            player.setMoney(Math.random() * 10000.0);
            while (!player.isBankrupt()) {
                player.reset();
                player.setBet(Math.random() * 3000.0);
                player.betMoney(player.getBet());
                System.out.println("betting $" + (((int) player.getBet() * 100) / 100.0));
                do {
                    player.drawCard(shoe.drawCard());
                    player.updateActivity();
                } while (player.isActive());
                if (!player.isBust()) {
                    player.earn(2 * player.getBet());
                }
                System.out.println(player.getPlayers().get(0).getHand().toString() + player.getMoney());
                if (player.getMoney() <= 0.0) {
                    player.setBankrupt(true);
                }
            }
        }
    }
}
/*
OUTPUT


betting $457.0
5 of hearts	5 of clubs	2 of hearts	8 of spades	2 of clubs	1365.8751924331393
betting $693.0
8 of clubs	8 of clubs	Queen of diamonds	672.6670480464707
betting $1582.0
Queen of hearts	King of spades	6 of spades	-909.9132815824189
betting $277.0
8 of spades	Queen of hearts	2 of diamonds	2 of spades	5190.945718322959
betting $818.0
8 of hearts	3 of diamonds	7 of hearts	8 of clubs	4372.815390826439
betting $2410.0
7 of hearts	King of spades	5 of spades	1962.0106434171576
betting $1237.0
10 of spades	4 of diamonds	2 of clubs	3 of hearts	4 of hearts	724.3419994269116
betting $2145.0
Ace of spades	Jack of spades	2869.8757941198837
betting $2019.0
4 of diamonds	Ace of hearts	10 of hearts	10 of spades	850.5163151567142
betting $2556.0
Ace of clubs	9 of diamonds	7 of diamonds	5 of hearts	-1706.4405696978367
betting $2626.0
Queen of clubs	3 of spades	King of diamonds	185.11497440319818
betting $859.0
Ace of spades	2 of clubs	Queen of spades	Jack of hearts	-674.7654946715284
betting $1737.0
6 of clubs	Ace of hearts	3 of diamonds	King of diamonds	Queen of diamonds	-587.3881466561065
betting $2962.0
6 of diamonds	6 of hearts	Queen of spades	-1661.1822850906897
betting $850.0
9 of clubs	Jack of spades	8 of diamonds	8173.229196203511
betting $1349.0
6 of clubs	9 of clubs	8 of spades	6823.880154314934
betting $1087.0
4 of diamonds	Ace of diamonds	Queen of diamonds	4 of spades	9 of hearts	5736.053429664038
betting $2987.0
King of hearts	Jack of diamonds	Queen of spades	2748.685459893989
betting $696.0
3 of clubs	Ace of hearts	3 of diamonds	7 of spades	Jack of hearts	2051.927528801762
betting $644.0
King of clubs	6 of hearts	8 of clubs	1407.4027341310984
betting $898.0
King of hearts	Ace of diamonds	2305.4241900830293
betting $2547.0
Queen of clubs	3 of spades	King of diamonds	-242.40553264453274
betting $1590.0
9 of diamonds	10 of spades	6 of hearts	-1000.1196190617467
betting $2766.0
5 of spades	2 of clubs	2 of clubs	3 of clubs	3 of spades	9582.542564489451
betting $2311.0
King of spades	Jack of diamonds	5 of spades	7270.637293981099
betting $2695.0
Ace of spades	5 of hearts	10 of clubs	6 of spades	4575.6264694585825
betting $945.0
7 of spades	9 of diamonds	9 of hearts	3630.260405845062
betting $1440.0
3 of diamonds	3 of diamonds	5 of hearts	Ace of clubs	7 of hearts	5071.233271694857
betting $2197.0
Ace of spades	10 of clubs	7268.414858380496
betting $2994.0
Jack of spades	3 of clubs	King of spades	4273.914140884419
betting $1476.0
Ace of clubs	King of clubs	5750.105636038064
betting $1713.0
10 of clubs	3 of hearts	10 of hearts	4036.4569249798606
betting $814.0
Queen of diamonds	4 of clubs	6 of diamonds	Jack of hearts	3222.0816215526947
betting $2364.0
Queen of spades	9 of diamonds	Jack of clubs	857.1041806062717
betting $1932.0
3 of diamonds	9 of hearts	4 of hearts	Queen of spades	-1074.9673520930867


 */
