package practice.cardcompany;

import java.util.regex.Pattern;

public class Company {

    // 변수
    private static Company instance = null;

    public int cardNumber = 10001;

    // 생성자
    Company() {
    }


    // 메소드
    public static Company getInstance() {
        if (instance == null) {
            instance = new Company();
        }
        return instance;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Card createCard() {
        Card card = new Card();
        cardNumber++;
        return card;
    }

} // end class
