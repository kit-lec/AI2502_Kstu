package practice.cardcompany;

public class Company {
    private int cardNumber = 10000;
    private static Company instance = null;

    public static Company getInstance() {
        if (instance == null) {
            instance = new Company();
        }
        return instance;
    }

//    public int createCard() {
//        return
//    }

} // end class
