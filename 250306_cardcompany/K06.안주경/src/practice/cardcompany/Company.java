package practice.cardcompany;

public class Company {

	private static Company instance; // singleton
	private int currentCardNumber = 10000; // 카드 번호 초기값 설정
											// 각 카드가 생성될 때 cardNumber에 할당


	private Company() {} // 생성자 private>외부에서 못하게
						// class 내부에서만 생성되게 할거야

	//생성하는 메소드는 외부에서 접근가능하게 public 
	public static Company getInstance() {
		if (instance == null) {
			instance = new Company(); //객체 없으면 생성
		}
		return instance;
	}

	public Card createCard() {
		return new Card(++currentCardNumber);
	}

		
} // end class
