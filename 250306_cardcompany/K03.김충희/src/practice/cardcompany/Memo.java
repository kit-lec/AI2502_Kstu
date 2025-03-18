package practice.cardcompany;

public class Memo {
    public static void main(String[] args) {
        Company company = Company.getInstance();  // 여러번 호출해도 동일 객체

        Card myCard = company.createCard();  // 첫번째 카드생성 고유번호 10001 부여
        Card yourCard = company.createCard();

        System.out.println(myCard.getCardNumber());  // 10001 출력
        System.out.println(yourCard.getCardNumber());




    }
}
