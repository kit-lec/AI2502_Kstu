package practice.inventory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryMain {

    public static long id = 0;
    public static List<Inventory> inventories = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        display();
        int choice = sc.nextInt();
        sc.nextLine();  // 버퍼 비우기

        while (true) {
            if (choice == 0) break;

            switch (choice) {
                case 1: addItem(sc); break;
                case 2: showItem(); break;
                case 3: setItem(sc); break;
                case 4: deleteItem(sc); break;
                default:
                    System.out.println("선택하신 번호의 항목은 존재하지 않습니다.");
                    System.out.println("재입력해주십시오.");
            }

            display();
            choice = sc.nextInt();
            sc.nextLine();  // 버퍼 비우기
        }
    }


    public static void display() {
        System.out.println("상품정보 관리 프로그램 v1.0");
        System.out.println("-".repeat(20));
        System.out.println("""
                [1] 입력
                [2] 열람
                [3] 수정
                [4] 삭제
                [0] 종료""");
        System.out.println("-".repeat(20));
        System.out.println("선택: ");
    }


    public static void addItem(Scanner sc) {
        Inventory item = new Inventory();

        try {
            System.out.println("\n아이템 항목 입력을 시작합니다.");

            System.out.println("아이템 이름을 입력해주세요.");
            item.setName(sc.nextLine());

            System.out.println("아이템 가격을 정해주세요.");
            String price = sc.nextLine();

            System.out.println("몇 개 등록하시겠습니까?");
            String count = sc.nextLine();
            if (count.isEmpty()) count = "0";

            item.setCreatedDate(LocalDateTime.now());

            if (item.getName().isEmpty()) {
                System.out.println("[에러] 이름은 필수 입력입니다.");
                System.out.println("다시 입력하세요.\n");
                return;
            }
            item.setPrice(Integer.parseInt(price));
            item.setCount(Integer.parseInt(count));

            item.setId(++id);

            inventories.add(item);

            System.out.println("입력 완료\n");
        }
        catch (NumberFormatException e) {
            System.out.println("[에러] 가격과 개수는 숫자를 입력하세요.\n");
            System.out.println("다시 입력하세요.\n");
        }
    }


    public static void showItem() {
        System.out.println("모든 아이템 항목을 출력합니다.");
        System.out.println("id\tname\tprice\tcount\ttime");

        for (Inventory item : inventories) {
            System.out.println(item.toString());
        }
        System.out.println();
    }


    public static void setItem(Scanner sc) {
        System.out.println("\n선택한 항목의 상품의 내용을 변경합니다.");
        System.out.println("변경할 상품 id를 입력해주세요.");
        int findId = sc.nextInt();
        sc.nextLine();  // 버퍼 비우기
        if ((findId > inventories.size()) && (findId > 0)) {
            System.out.println("[에러] ID 입력 오류\n");
            return;
        }

        System.out.println("선택한 항목은 다음과 같습니다.");
        System.out.println("-".repeat(20));

        try {
            for (int i = 0; i < inventories.size(); i++) {
                if (inventories.get(i).getId() == findId) {
                    System.out.println(inventories.get(i).toString());

                    System.out.println("상품명을 입력해주세요.");
                    String name = sc.nextLine();

                    System.out.println("가격을 입력해주세요.");
                    String price = sc.nextLine();

                    System.out.println("개수를 입력해주세요.");
                    String count = sc.nextLine();

                    if (name.isEmpty()) {
                        System.out.println("[에러] 이름은 필수 입력입니다.\n");
                        return;
                    }
                    if (price.isEmpty()) {
                        System.out.println("[에러] 가격은 필수 입력입니다.\n");
                        return;
                    }
                    if (count.isEmpty()) {
                        System.out.println("[에러] 개수는 필수 입력입니다.\n");
                        return;
                    }

                    inventories.get(i).setName(name);
                    inventories.get(i).setPrice(Integer.parseInt(price));
                    inventories.get(i).setCount(Integer.parseInt(count));
                }
            }
            System.out.println("성공 여부: 1");
        }
        catch (NumberFormatException e) {
            System.out.println("[에러] 가격과 개수는 숫자로 입력하세요.\n");
            System.out.println("다시 입력하세요.\n");
        }
    }


    public static void deleteItem(Scanner sc) {
        System.out.println("\n선택한 항목의 상품을 삭제합니다.");
        System.out.println("삭제할 상품 id를 입력해주세요.");
        int removeId = sc.nextInt();
        sc.nextLine();  // 버퍼 지우기

        if ((removeId > inventories.size()) && (removeId > 0)) {
            System.out.println("[에러] ID 입력 오류\n");
            return;
        }

        for (int i = 0; i < inventories.size(); i++) {
            if (inventories.get(i).getId() == removeId) {
                inventories.remove(i);
            }
        }
    }

} // end class
