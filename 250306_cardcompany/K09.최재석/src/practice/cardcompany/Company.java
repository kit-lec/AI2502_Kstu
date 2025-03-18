package practice.cardcompany;

public class Company {

    private static Company instance;     // 객체 지정
    private int companyNumber = 10000;   // 카드 번호 시작(10000)지정

    private Company(){}   // 외부 연결 방지용 private

    public static Company getInstance() {   // 모든 인스턴스에서 공유되는 instance
        if (instance == null) {
            instance = new Company();  // new로 객체 생성
        }
        return instance;   // 객체 반환 (새로운 객체를 만들지 않기 위해)
    }
    public Card createCard() {
        return new Card(++companyNumber);  // 카드 번호 1씩 증가
    }
		
} // end class
