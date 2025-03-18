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
    Book () {}

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
        String bookInfo = String.format("%5s| %-20s| %10s| %10s",
                no, title, BookManagerMain.formatPrice(price), publishedAt);
        return bookInfo;
    }
}
