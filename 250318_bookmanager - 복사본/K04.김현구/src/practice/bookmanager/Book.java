package practice.bookmanager;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Lombok 은 사용하지 않습니다
public class Book {

    // ★ 필드는 수정하지 마세요 ★
    String no;   // 도서번호. 알파벳대문자1자리+ 숫자4자리 형식.  고유한 값.
    String title;  // 최소 한글자 이상!
    Integer price;  // 0 이상의 정수
    LocalDate publishedAt;  // 날짜.

    // TODO : ↓ 생성자, getter&setter, toString 만 추가합니다  (다른 메소드드은 여기에 만들지 마세요)

    // 생성자
    public Book(String no, String title, Integer price, LocalDate publishedAt) {
        this.no = no.toUpperCase();
        this.title = title;
        this.price = price;
        this.publishedAt = publishedAt;
    }

    // getter (각 필드값 반환)
    public String getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    // setter (필드 값 변경)  도서번호가 없는 이유는 고유한 값이라고 조건에 있으므로
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    // toString() (객체 정보를 문자열로 변환하여 출력)
    @Override
    public String toString() {
        // 출판일을 yyyy-MM-dd 형식으로 변환 (LocalDate → LocalDateTime 변환)
        String formattedDate = publishedAt.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 가격을 "10,000" 형식으로 변환
        DecimalFormat df = new DecimalFormat("#,###");
        String formattedPrice = df.format(price);

        return String.format("%s | %-10s | %10s원 | %s", no, title, formattedPrice, formattedDate);
    }

    // 이 클래스에 데이터 조작 관련 메소드 작성하지 마세요
}  // end class Book