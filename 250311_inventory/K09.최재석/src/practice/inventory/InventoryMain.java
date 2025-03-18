package practice.inventory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryMain {

    public static final List<Inventory> inventoryList = new ArrayList<>();  // 리스트 초기화
    public static long idCounter = 1;  // Inventory.java의 id(상품 일련번호)를 1씩 증가

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu1();

            int userChoice = Integer.parseInt(sc.nextLine());

            if (userChoice == 1) {
                additem(sc);
            }
            if (userChoice == 2) {
                viewitem();
            }
            if (userChoice == 3) {
                updateitem(sc);
            }
            if (userChoice == 4) {
                deleteItem(sc);
            }
            if (userChoice == 0) break;
        }
        sc.close();
    }

    public static void showMenu1() {
        System.out.println("""
                상품정보 관리 프로그램 v1.0
                ---------------
                 [1] 입력
                 [2] 열람
                 [3] 수정
                 [4] 삭제
                 [0] 종료
                ---------------
                선택:""");
    }


    public static void additem(Scanner sc) {
        System.out.println("""
                아이템 항목 입력을 시작합니다.
                아이템 이름을 입력해주세요.
                """);
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("ERR-2] 문자열오류 insert() 이름이 입력되지 않았습니다 :");
            return;  // 틀렸으니 호출 종료 후 되돌아가기
        }

        System.out.println("아이템 가격을 정해주세요.");
        int price = Integer.parseInt(sc.nextLine());
        if (price < 0) {
            System.out.println("ERR-4] 가격 오류 insert() 가격에 정상적인 입력이 되지 않았습니다.");
            return;
        }

        System.out.println("몇 개 등록하시겠습니까?");
        String countinput = sc.nextLine();
        int count = countinput.isEmpty() ? 0 : Integer.parseInt(countinput);
        // 입력이 비어있는지 확인 후 //  비어있다면 0으로 설정 // 비어있지 않다면 숫자변환

        inventoryList.add(new Inventory(idCounter, name, price, count, LocalDateTime.now()));
        idCounter++;
    }


    public static void viewitem() {
        System.out.println("""
                모든 아이템 항목을 출력합니다.
                id   name  price  count  time
                """);

        if (inventoryList.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        for (Inventory item : inventoryList) {
            System.out.println(item);
        }
    }


    public static void updateitem(Scanner sc) {
        System.out.println("선택한 항목의 상품의 내용을 변경합니다.\n변경할 상품 id를 입력해주세요");
        int id = Integer.parseInt(sc.nextLine());

        Inventory item = null;  // 변수 선언을 위해 초기화. 이후의 입력을 위해.
        for (Inventory item2 : inventoryList){}
    }



    public static void deleteItem(Scanner sc) {
            System.out.println("선택한 항목의 상품을 삭제 합니다.\n삭제할 상품 id를 입력해주세요");
            long id = Long.parseLong(sc.nextLine());







    }
} // end class
