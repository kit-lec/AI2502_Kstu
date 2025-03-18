package practice.inventory;

import practice.inventory.Inventory;

import java.util.Scanner;

public class InventoryMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
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
            int choice = scanner.nextInt();

            if (choice == 0) {
                inventory.saveToFile();  // 종료 시 데이터 저장
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (choice == 1) {
                System.out.print("상품 ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // 버퍼 클리어
                System.out.print("상품 이름: ");
                String name = scanner.nextLine();
                System.out.print("상품 가격: ");
                int price = scanner.nextInt();
                System.out.print("상품 수량: ");
                int count = scanner.nextInt();
                inventory.addItem(id, name, price, count);
            } else if (choice == 2) {
                inventory.displayItems();
            } else if (choice == 3) {
                System.out.print("수정할 상품 ID: ");
                int updateId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("새 이름: ");
                String newName = scanner.nextLine();
                System.out.print("새 가격: ");
                int newPrice = scanner.nextInt();
                System.out.print("새 수량: ");
                int newCount = scanner.nextInt();
                inventory.updateItem(updateId, newName, newPrice, newCount);
            } else if (choice == 4) {
                System.out.print("삭제할 상품 ID: ");
                int deleteId = scanner.nextInt();
                inventory.removeItem(deleteId);
            } else {
                System.out.println("잘못된 선택입니다.");
            }
        }
        scanner.close();
    }
}
