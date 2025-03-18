package practice.bookmanager;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Lombok 은 사용하지 않습니다
public class Book implements Serializable {

    // ★ 필드는 수정하지 마세요 ★
    String no;   // 도서번호. 알파벳대문자1자리+ 숫자4자리 형식.  고유한 값.
    String title;  // 최소 한글자 이상!
    Integer price;  // 0 이상의 정수
    LocalDate publishedAt;  // 날짜.

    // TODO : ↓ 생성자, getter&setter, toString 만 추가합니다  (다른 메소드드은 여기에 만들지 마세요)

    public Book(){

    }

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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d"); // 원하는 형식 설정
        String formattedDate = publishedAt.format(formatter);
        String result = "";

        System.out.printf("%5s   %-19s   %,10d   %s \n", no, title, price, formattedDate);
        return result;
    }

    // 이 클래스에 데이터 조작 관련 메소드 작성하지 마세요
}
