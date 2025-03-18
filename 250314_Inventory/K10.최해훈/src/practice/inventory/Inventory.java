package practice.inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Inventory {

    // ★ 필드는 수정하지 마세요 ★
    private Long id;  // 상품고유 일련번호 (숫자, 필수, 중복불가)
    private String name;  // 상품 이름 (문자열, 필수)
    private Integer price; // 상품 가격  (숫자, 필수)

    private Integer count; // 상품 재고  (숫자, 기본 0)
    private LocalDateTime createdDate;  // 상품 등록일  (등록날짜-현재시간으로 포매팅 yyyy-MM-dd HH:mm:ss)

    // TODO : ↓ 생성자, getter&setter, toString 만 추가합니다

    public Inventory(Long id, String name, Integer price, Integer count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.createdDate = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    public String getFormattedDate() { return createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }

    @Override
    public String toString() {
        return String.format("| %-5d | %-10s | %-10d | %-10d | %s |", id, name, price, count, getFormattedDate());
    }


} // end Model














