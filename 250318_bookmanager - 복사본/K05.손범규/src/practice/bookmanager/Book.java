package practice.bookmanager;

import java.io.Serializable;
import java.time.LocalDate;

// Lombok 은 사용하지 않습니다
public class Book implements Serializable {

    // ★ 필드는 수정하지 마세요 ★
    String no;   // 도서번호. 알파벳대문자1자리+ 숫자4자리 형식.  고유한 값.
    String title;  // 최소 한글자 이상!
    Integer price;  // 0 이상의 정수
    LocalDate publishedAt;  // 날짜.

    // TODO : ↓ 생성자, getter&setter, toString 만 추가합니다  (다른 메소드드은 여기에 만들지 마세요)

    // Constructor
    public Book() {}
    public Book(String no, String title, Integer price, LocalDate publishedAt) {}

    // Getter & Setter

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    // ToString

    @Override
    public String toString() {
        return no + '|' +
               title + '|' +
               price + "|" +
               publishedAt;
    }


    // 이 클래스에 데이터 조작 관련 메소드 작성하지 마세요
}
