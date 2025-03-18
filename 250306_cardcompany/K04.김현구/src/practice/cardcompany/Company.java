package practice.cardcompany;

public class Company {

    // 유일한 company 객체 저장(이전 문제에서 시급과 같은 원리로 static 을 사용한다.)
    private static Company instance = null;  // null 로 해야 singleton 유지 가능하다.
    private int cardNumber = 10000;  // 초기 카드 넘버는 10000이므로 10000으로 초기화

    // 생성자 (new Company();을 하려면 있어야 한다.) (singleton 유지를 위해 private 사용)
    private Company() {}

    // 다른 파일에서 이용할 수 있도록 작업.
    public static Company getInstance() {
        if (instance == null) {
            instance = new Company();
        }
        return instance;
    }

    // 카드는 회사에서 발급하므로, Company 에서 발급해야한다.
    public Card createCard() {
        cardNumber++;
        return new Card(cardNumber);
    }





	// TODO
	// 필요한 변수, 메소드, 생성자 정의하기
		
} // end class
