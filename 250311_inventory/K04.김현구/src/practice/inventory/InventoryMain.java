package practice.inventory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryMain {

    private static Scanner sc = new Scanner(System.in);
    private static List<Inventory> itemList = new ArrayList<>();  // Inventory 을 담아놓기 위한 그릇 생성(뭐 눌렀더니 생성됨....)
    static long id1 = 1;  // 상품 ID 자동 증가
    private static String name;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 가위바위보 문제와 유사함
        while (true) {
            showMenu(); // 메뉴보여주기

            int Choice = inputChoice(sc);  // 사용자 입력
            if (Choice == 0) {
                break;    // 0 이면 종료
            }
            if (Choice == 1) {
                itemInput();
            }
            if (Choice == 2) {
                printInventory();
            }
            if (Choice == 3) {
                itemUpdate();
            }
            if (Choice == 4) {
                itemDelete();
            }

        } // end while
    } // end main

    private static void showMenu() {
        System.out.println("""
                상품정보 관리 프로그램 v1.0
                ---------------
                 [1] 입력
                 [2] 열람
                 [3] 수정
                 [4] 삭제
                 [0] 종료
                ---------------
                선택:
                """);
    }  // end ShowMenu

    private static int inputChoice(Scanner sc) {
        int num = 0;
        while (true) {  // while 로 계속 입력을 받는다.
            num = Integer.parseInt(sc.nextLine());
            if (0 <= num && num <= 4) {  // 0부터 4까지는 정상
                return num;  // 맞으면 종료
            }
            System.out.println("선택하신 번호의 항목은 존재하지 \n않습니다. 재입력해주십시오.");  // 0부터 4 제외한 걸 입력하면 출력
        }
    }  // end inputChoice

    private static void itemInput() {  // 1번을 눌렀을 때 출력
        System.out.println("아이템 항목 입력을 시작합니다.");
        System.out.println("아이템 이름을 입력해주세요.");
        String name = sc.nextLine();
        if(name.isEmpty()) {
            System.out.println("ERR-2]문자열오류 insert() 이름이 입력되지 않았습니다 :");
            return;
        }
        System.out.println("아이템 가격을 정해주세요.");
        int price;
        try{
            price = Integer.parseInt(sc.nextLine());
        }catch(Exception e) {
            System.out.println("ERR-4]가격 오류 insert() 가격에 정상적인 입력이 되지 않았습니다.:");
            return;
        }

        System.out.println("몇 개 등록하시겠습니까?");
        String count1 = sc.nextLine();  // 빈칸으로 입력할 시 String 대신 int 을 쓰면 NumberFormatException 발생 -> String 은 빈칸이 가능하기 때문이다
        int count;  // 수량을 담을 변수 선언

        if(count1.isEmpty()) {
            count = 0;
        }
        else {
            count = Integer.parseInt(count1);
        }
        Inventory item = new Inventory(id1++, name, price, count, LocalDateTime.now());
        itemList.add(item);
    }  // end itemInput

    private static void printInventory() {  // 2번을 눌렀을때 출력
        System.out.println("모든 아이템 항목을 출력합니다.");
        System.out.println("---------------------------------------");
        if (itemList.isEmpty()) {  // 만약 리스트가 비어있다면 출력하지 않음
            System.out.println("등록된 상품이 없습니다.");
        } else {  // 리스트가 비어있지 않다면
            for (int i = 0; i < itemList.size(); i++) {
                Inventory item = itemList.get(i); // 리스트에서 아이템 하나씩 꺼낸다.
                if (item != null) {  // 만약 아이템이 비어있지 않다면 출력
                    System.out.println(item.toString());
                }
            }
        }
    }  //end printInventory

    private static void itemUpdate() {
        System.out.println("선택한 항목의 상품의 내용을 변경합니다.\n" +
                "변경할 상품 id를 입력해주세요\n");
        long id = Long.parseLong(sc.nextLine());

        Inventory item = itemList.get(0);  // item 중에 어떻게 id 만 찾는지 모르겠습니다...
        if (item == null) {
            System.out.println("ERR-3]ID오류 존재하지 않는 id: 9");
            return;
        }
        System.out.println("상품명을 입력해주세요.");
        String name = sc.nextLine();
        if(name.isEmpty()){
            System.out.println("ERR-2]문자열오류 insert() 이름이 입력되지 않았습니다 :");
            return;
        }
        System.out.println("가격을 입력해주세요.");
        int price = Integer.parseInt(sc.nextLine());

        System.out.println("개수를 입력해주세요.");
        String count1 = sc.nextLine();

        item.setName(name);
        item.setPrice(price);
        item.setCount(Integer.parseInt(count1));

        System.out.println("성공 여부: 1");

        }  // end itemUpdate
    private static void itemDelete() {
        long id = Long.parseLong(sc.nextLine());
        Inventory item = itemList.get(0);  // item 중에 어떻게 id 만 찾는지 모르겠습니다...

        if (item == null) {
            System.out.println("ERR-3]ID오류 존재하지 않는 id: 9");
            return;
        }

        itemList.remove(item);
        System.out.println("성공 여부: 1");

    }  // end itemDelete
} // end class InventoryMain



