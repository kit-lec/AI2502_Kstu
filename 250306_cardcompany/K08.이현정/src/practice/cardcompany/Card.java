package practice.cardcompany;

public class Card {

    private int cardNumber;

    Card() {
        cardNumber = Company.getInstance().cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

} // end class
