package practice.cardcompany;

public class Card {
	
	private int cardNumber;   // 카드 번호를 나타냄

    public Card(int cardNumber) {   // 카드 번호 저장 생성자
        this.cardNumber = cardNumber;
    }
	public int getCardNumber() {   // 카드 번호 반환
        return cardNumber;
    }
} // end class
