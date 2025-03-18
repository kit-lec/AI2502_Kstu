package practice.cardcompany;

public class Company {
	// static 변수
    public static int CARD_NUMBER = 10000;
    private static Company instance = null;

    private Company() {
    }

    public static Company getInstance(){
        if(instance == null){
            instance = new Company();
        }
        return instance;
    }

    public Card createCard(){
        CARD_NUMBER++;
        return new Card(CARD_NUMBER);
    }
		
} // end class
