package practice.inventory;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryMain {
    private static Long counter = 1L;

    public static void main(String[] args) {
        ArrayList<Inventory> inventory = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            int number = sc.nextInt();

            if (number == 0) break;

            if (number == 1) {

                System.out.print("아이템 항목 입력을 시작합니다.\n 아이템 이름을 입력해주세요 : ");
                String name = sc.next();

                System.out.print("아이템 가격을 정해주세요 : ");
                Integer price = sc.nextInt();

                System.out.println("몇개 등록하시겠습니까 ? ");
                Integer count = sc.nextInt();

                inventory.add(new Inventory (counter++, name, price, count));
            } else if (number == 2) {
                System.out.println("모든 아이템 항목을 출력합니다.");
                System.out.println("\tid\t\t\tname\t\t\tprice\t\t\tcount\t\t\ttime");

                for (Inventory i : inventory) {
                    System.out.println(i);
                }
            } else if (number == 3) {
                System.out.print("선택할 항목의 상품의 내용을 변경합니다.\n변경할 상품 id를 입력해주세요 : ");
                Long id = sc.nextLong();
                boolean setItem = false;

                for (Inventory i : inventory) {
                    if (i.getId().equals(id)) {
                        System.out.println("선택한 항목은 다음과 같습니다.");
                        System.out.println(i);

                        System.out.print("상품명을 입력해주세요 : ");
                        String name = sc.nextLine();

                        System.out.print("가격을 입력해주세요 : ");
                        Integer price = sc.nextInt();
                        sc.nextLine();

                        System.out.print("개수를 입력해주세요 : ");
                        Integer count = sc.nextInt();

                        i.setName(name);
                        i.setPrice(price);
                        i.setCount(count);

                        setItem = true;
                        System.out.println("성공 여부 : " + setItem);
                        break;
                    } else {
                        System.out.println("존재하지 않는 id : " + id);
                    }
                }
            } else if (number == 4) {
                System.out.print("선택할 항목의 상품을 삭제합니다.\n삭제할 상품 id를 입력해주세요 : ");
                Long id = sc.nextLong();
                boolean removeItem = false;

                for (Inventory i : inventory) {
                    if (i.getId().equals(id)) {
                        inventory.remove(i);

                        removeItem = true;
                        System.out.println("성공 여부 : " + removeItem);
                    } else {
                        System.out.println("존재하지 않는 id : " + id);
                    }
                }

            } else continue;
        }
    }

    public static void showMenu() {
        System.out.print("""
                상품정보 관리 프로그램 v1.0
                --------------------------
                [1] 입력
                [2] 열람
                [3] 수정
                [4] 삭제
                [0] 종료
                --------------------------
                선택 :  """);
    }
}

