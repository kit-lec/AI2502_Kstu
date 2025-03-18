package practice.cardcompany;

public class Company {

	// 필요한 변수, 메소드, 생성자 정의하기

    // 변수
	private static Company instance = null;
    static int serial = 10000;

    // 메소드
    public static Company getInstance() {
        if (instance == null) {
            instance = new Company();
        }
        return instance;
    }

    public Card createCard() {
        Card card = new Card();
        Company.serial++;
        card.cardNumber = Company.serial;
        return card;
    }

    // 생성자
    private Company(){}



} // end class
