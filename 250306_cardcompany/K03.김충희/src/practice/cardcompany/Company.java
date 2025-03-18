package practice.cardcompany;

import java.lang.module.Configuration;

public class Company {

    public int num = 10000;
    private static Company instance = null;

    public static Company getInstance(){
        if (instance == null) {
            instance = new Company();
        }
        return instance;
    }

    public static Card createCard(){
        System.out.println("첫번째 카드 생성");
        return new Card();

    }





















} // end class
