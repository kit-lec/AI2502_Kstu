package practice.inventory;

import java.time.LocalDateTime;

public class Inventory {

    // ★ 필드는 수정하지 마세요 ★
    private Long id;  // 상품고유 일련번호 (숫자, 필수, 중복불가)
    private String name;  // 상품 이름 (문자열, 필수)
    private Integer price; // 상품 가격  (숫자, 필수)

    private Integer count; // 상품 재고  (숫자, 기본 0)
    private LocalDateTime createdDate;  // 상품 등록일  (등록날짜 )



    // TODO : ↓ 생성자, getter&setter, toString 만 추가합니다

    public Inventory(long idCounter, String name, int price, int count, LocalDateTime now) {
    }  // InventoryMain.java의 inventoryList.add용 생성자

} // end Model
















