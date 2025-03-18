package practice.cardcompany;

public class Card {
    private int cardNumber;

    Card(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        this.cardNumber = cardNumber++;
        return cardNumber;
    }
} // end class
