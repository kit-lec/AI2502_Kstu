package practice.inventory;

import java.time.LocalDateTime;

public class Inventory {

    // ★ 필드는 수정하지 마세요 ★
    private Long id;  // 상품고유 일련번호 (숫자, 필수, 중복불가)
    private String name;  // 상품 이름 (문자열, 필수)
    private Integer price; // 상품 가격  (숫자, 필수)

    private Integer count; // 상품 재고  (숫자, 기본 0)
    private LocalDateTime createdDate;  // 상품 등록일  (등록날짜 )

    // TODO : 이 클래스에는 생성자, getter&setter, toString 만 추가합니다

    // 생성자로 초기값을 설정해주기(초기화)
    public Inventory(Long id, String name, Integer price, Integer count, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.createdDate = createdDate;
    }

    // getter&setter 을 만들어서 외부에서 객체의 필드에 접근할 수 있도록 하는 메서드를 만든다.
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // toString 오버라이딩 하기
    @Override
    public String toString() {
        return id + " | " + name + " | " + price + "원 | " + count + "개 | " + createdDate;
    }
} // end Model
















