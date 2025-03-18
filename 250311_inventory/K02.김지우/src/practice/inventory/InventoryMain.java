package practice.inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class InventoryMain {
    static Scanner sc = new Scanner(System.in);
    // main 메소드 외의 메소드에서도 사용할거라 main 밖에 static 으로 선언함

    // 아이템 항목들 담는 배열 리스트
    static List<Inventory> inventoryList = new ArrayList<Inventory>();

    private static long idNum = 1;

    public static void main(String[] args) {
        while(true){
            showMenu();
            int num = sc.nextInt();
            sc.nextLine(); // num 입력받고 남은 "\n" 을 없애준다.

            if (num == 1){
                // 입력
                addItem();
            }
            else if (num == 2){
                // 열람
                showItems();
            }
            else if (num == 3){
                // 수정
                updateItem();
            }
            else if (num == 4) {
                // 삭제
                deleteItem();
            }
            else if (num == 0)
                break;
            else{
                System.out.println("선택하신 번호의 항목은 존재하지 않습니다.");
                System.out.println("재입력해주십시오");
                System.out.println();
            }
        }
    } // end main


    // 메뉴 보여주는 메소드
    public static void showMenu() {
        System.out.println("상품정보 관리 프로그램 v1.0");
        System.out.println("-----------------------");
        System.out.println("[1] 입력");
        System.out.println("[2] 열람");
        System.out.println("[3] 수정");
        System.out.println("[4] 삭제");
        System.out.println("[0] 종료");
        System.out.println("-----------------------");
        System.out.println("선택: ");
    }


    // 아이템 입력하는 메소드
    public static void addItem(){
        Integer parsePrice;
        Integer parseCount;
        String parseItemName;

        Inventory inventory = new Inventory();

        System.out.println("아이템 항목 입력을 시작합니다.");

        System.out.println("아이템 이름을 입력해주세요.");
        String itemName = sc.nextLine();
        if(itemName.trim().isEmpty()){
            parseItemName = null;
        }
        else
        {
            parseItemName = itemName.trim();
            inventory.setName(parseItemName);

        }

        System.out.println("아이템 가격을 정해주세요.");
        String price = sc.nextLine();
        if(price.trim().isEmpty()){
            parsePrice = null;
        }
        else{
            parsePrice = Integer.parseInt(price);
            inventory.setPrice(parsePrice);
        }

        System.out.println("몇 개 등록하시겠습니까?");
        String count = sc.nextLine();
        if(count.trim().isEmpty()){
            parseCount = 0;
            inventory.setCount(parseCount);
        }
        else{
            parseCount = Integer.parseInt(count);
            inventory.setCount(parseCount);
        }


        if(parseItemName == null || parsePrice == null){
            if(parseItemName == null){
                System.out.println("Error: 이름이 입력되지 않았습니다.");
            }
            if (parsePrice == null) {
                System.out.println("Error: 가격이 입력되지 않았습니다.");
            }
            System.out.println();
            return;
        }

        inventory.setId(idNum);

        inventory.setCreatedDate(LocalDateTime.now());

        inventoryList.add(inventory);
        idNum++;
        System.out.println();
    }

    public static void showItems() {
        System.out.println("모든 아이템 항목을 출력합니다.");
        System.out.println("id  name    price   count   time");

        for (Inventory i : inventoryList) {
            System.out.println("--------------------------------");
            System.out.println(i.getId() + " | " + i.getName() + " | " + i.getPrice() + "원" + " | " + i.getCount() + "개" + " | " + i.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        System.out.println();
    }

    public static void updateItem(){
        System.out.println("선택한 항목의 상품의 내용을 변경합니다.");
        System.out.println("변경할 상품 id를 입력해주세요");

        int inputId = sc.nextInt();
        sc.nextLine();
        System.out.println("선택한 항목은 다음과 같습니다.");

        if(inputId >= idNum){
            System.out.println("Error] 존재하지 않는 id: " + inputId);
            System.out.println();
            return;
        }

        // id 매치하여 해당 inventory를 담을 인스턴스
        Inventory updateInventory = new Inventory();
        for(Inventory i : inventoryList){
            if(i.getId() == inputId){
                updateInventory= i;
            }
        }

        System.out.println("--------------------------------");
        System.out.println(updateInventory.getId() + " | " + updateInventory.getName() + " | " + updateInventory.getPrice() + "원" + " | " + updateInventory.getCount() + "개" + " | " + updateInventory.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println("상품명을 입력해주세요.");
        String updateItemName = sc.nextLine();
        if(updateItemName.trim().isEmpty()){
            updateItemName = null;
        } else{
            updateItemName = updateItemName.trim();
            updateInventory.setName(updateItemName);
        }

        System.out.println("가격을 입력해주세요.");
        String updatePrice = sc.nextLine();
        if(updatePrice.trim().isEmpty()){
            updatePrice = null;
        } else{
            updatePrice = updatePrice.trim();
            updateInventory.setPrice(Integer.parseInt(updatePrice));
        }

        System.out.println("개수를 입력해주세요.");
        String updateCount = sc.nextLine();
        if(updateCount.trim().isEmpty()){
            updateCount = null;
        } else{
            updateCount = updateCount.trim();
            updateInventory.setCount(Integer.parseInt(updateCount));
        }

        if (updateItemName == null) {
            System.out.println("Error] 이름이 입력되지 않았습니다.");
            System.out.println();
        }
        System.out.println("성공 여부: 1");
        System.out.println();
    }

    public static void deleteItem(){
        System.out.println("선택한 항목의 상품을 삭제합니다.");
        System.out.println("삭제할 상품의 id를 입력해주세요");

        System.out.println("선택");
        int inputId = sc.nextInt();
        sc.nextLine();

        if(inputId >= idNum){
            System.out.println("Error] 존재하지 않는 id: " + inputId);
            System.out.println();
            return;
        }

        Iterator<Inventory> iterator = inventoryList.iterator();
        while (iterator.hasNext()) {
            Inventory i = iterator.next();
            if (i.getId() == inputId) {
                iterator.remove(); // 안전하게 요소 삭제
            }
        }

        System.out.println("성공 여부: 1");
    }
} // end class

// 에러 발생 시 다시 메뉴로 돌아오기
// id 번호 static으로 관리 하기