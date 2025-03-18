package practice.inventory;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InventoryMain {

    public static long serialId = 1L;

    public static String line(String letter, int count) {
        return letter.repeat(count);
    }

    public static String line = line("-",25);

    // 입력 상품

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = startPage();
        while (input != 0) {
            if (input == 1) {
                addItems();

            } else if (input == 2) {
                if (items.isEmpty()) {
                    items = fruits;
                }
                viewItems();

            } else if (input == 3) {
                if (items.isEmpty()) {
                    items = fruits;
                }
                changeItems();

            } else if (input == 4) {
                if (items.isEmpty()) {
                    items = fruits;
                }
                deleteItems();

            } else {
                System.out.println("""
                        [ERROR] 입력오류
                        유효하지 않은 값입니다.
                        <다시 입력해 주세요>.
                        """);
            }
            input = startPage();
        }
        System.out.println(line);
        System.out.println("[종료]");
    } // end main

    public static List<Inventory> items = new ArrayList<>();

    // 예시 상품
    public static List<Inventory> fruits =
            new ArrayList<>(Arrays.asList(
                    new Inventory(1L, "apple", 1000, 25, LocalDateTime.now()),
                    new Inventory(2L, "banana", 1500, 50, LocalDateTime.now()),
                    new Inventory(3L, "cherry", 2000, 75, LocalDateTime.now())

            ));


    public static int startPage() {
        Scanner sc = new Scanner(System.in);
        String title = line("=", 25);
        System.out.printf("""
                %s
                상품정보 관리 프로그램 v1.0
                %s
                 [1] 입력
                 [2] 열람
                 [3] 수정
                 [4] 삭제
                 [0] 종료
                %s
                선택:
                %s
                """, title, title, title, title);
        return sc.nextInt();

    }


    public static List<Inventory> addItems() {
        Scanner sc = new Scanner(System.in);
        // 입력
        System.out.println("[입력]");
        System.out.println(line);
        System.out.println("항목 입력을 시작합니다.");
        System.out.println(line);
        Inventory item = new Inventory();
        item.setId(serialId);
        serialId++;

        item.setCreatedDate(LocalDateTime.now());
        System.out.println("항목 이름을 입력해주세요.");
        System.out.println(line);
        String name = sc.nextLine();
        item.setName(name);
        if (name.isEmpty()) {
            System.out.println("""
                    [ERROR] 입력오류
                    항목 이름이 입력되지 않았습니다 : 
                    <다시 입력해 주세요.>""");
            name = sc.next();
        }

        System.out.println(line);
        System.out.println("아이템 가격을 정해주세요.");
        System.out.println(line);
        int price = sc.nextInt();
        item.setPrice(price);

        System.out.println(line);
        System.out.println("몇 개 등록하시겠습니까?");
        System.out.println(line);
        int count = sc.nextInt();
        item.setCount(count);

        items.add(item);
        System.out.println(line);
        System.out.println("[입력항목]");
        System.out.println(line);
        System.out.printf("[상품번호] : %d \n", item.getId());
        System.out.printf("[상품명칭] : %s \n", item.getName());
        System.out.printf("[상품가격] : %d \n", item.getPrice());
        System.out.printf("[상품수량] : %d \n", item.getCount());
        System.out.printf("[등록일시] : %s \n", item.getTime());
        System.out.println(line);
        System.out.println("<입력완료>");
            return items;
    }

    public static List<Inventory> viewItems() {
        // 열람
        Scanner sc = new Scanner(System.in);

        System.out.println("[열람]");
        System.out.println(line);
        System.out.println("모든 항목을 출력합니다.");
        System.out.println(line);
        // System.out.println(items);
        System.out.println("| id[0] | name[1] | price[2] | count[3] | time[4]");
        for (Inventory item : items) {
            System.out.println(line);
            System.out.printf("| %d | %s | %d | %d | %s |\n", item.getId(), item.getName(), item.getPrice(), item.getCount(), item.getTime());
        }
        System.out.println(line);
        System.out.println("<출력완료>");

        return items;
    }

    public static List<Inventory> changeItems() {
        Scanner sc = new Scanner(System.in);

        System.out.println("[수정]");
        System.out.println(line);
        viewItems();
        System.out.println(line);
        System.out.println("수정할 항목을 선택해 주세요. (ID 입력)");

        System.out.println(line);
        System.out.println("[선택] : ");

        long choice = sc.nextLong();
        System.out.println("선택 항목 : " + choice);
        System.out.println(line);

        boolean found = false;
        for (Inventory item : items) {
            if (item.getId() == choice) {
                found = true;
                System.out.println("| id[0] | name[1] | price[2] | count[3] | time[4]");
                System.out.println(line);
                System.out.printf("| %d | %s | %d | %d | %s |\n", item.getId(), item.getName(), item.getPrice(), item.getCount(), item.getTime());
                System.out.println("아이템 이름을 입력해주세요.");
                System.out.println(line);
                String name = sc.next();
                item.setName(name);

                System.out.println(line);
                System.out.println("아이템 가격을 정해주세요.");
                System.out.println(line);
                int price = sc.nextInt();
                item.setPrice(price);

                System.out.println(line);
                System.out.println("몇 개 등록하시겠습니까?");
                System.out.println(line);
                int count = sc.nextInt();
                item.setCount(count);
                items.set((int) (choice - 1), item);

                System.out.println(line);
                System.out.println("[수정내용]");
                System.out.println(line);
                System.out.printf("[상품번호] : %d \n", item.getId());
                System.out.printf("[상품명칭] : %s \n", item.getName());
                System.out.printf("[상품가격] : %d \n", item.getPrice());
                System.out.printf("[상품수량] : %d \n", item.getCount());
                System.out.printf("[등록일시] : %s \n", item.getTime());
                System.out.println(line);
                System.out.println("<수정완료>");
                return items;
            }


        }
        if (!found) {
            System.out.println("""
                    [ERROR] 입력오류
                    존재하지 않는 항목입니다.
                    <다시 입력해 주세요.>:""");
        }
        return items;
    }

    public static List<Inventory> deleteItems() {
        Scanner sc = new Scanner(System.in);

        System.out.println("[삭제]");
        System.out.println(line);
        viewItems();
        System.out.println("삭제할 항목을 선택해 주세요. (ID 입력)");
        long delete = sc.nextLong();

        Iterator<Inventory> iterator = items.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Inventory item = iterator.next();
            if (item.getId() == delete) {
                found = true;
                System.out.println(line);
                System.out.println("[삭제항목]");
                System.out.println(line);
                System.out.printf("[상품번호] : %d \n", item.getId());
                System.out.printf("[상품명칭] : %s \n", item.getName());
                System.out.printf("[상품가격] : %d \n", item.getPrice());
                System.out.printf("[상품수량] : %d \n", item.getCount());
                System.out.printf("[등록일시] : %s \n", item.getTime());
                System.out.println(line);
                iterator.remove();
                System.out.println("<삭제완료>");
            }
        }
        if (!found) {
            System.out.println("""
                    [ERROR] 입력오류
                    존재하지 않는 항목입니다.
                    <다시 입력해 주세요.>:""");
        }
        return items;
    }
}

