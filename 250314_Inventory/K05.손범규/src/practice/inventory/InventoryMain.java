package practice.inventory;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InventoryMain {

    public static long id = 1L;
    private static final String DATA_FILE = "data/inventory.dat";

    public static String line = line("-", 25);

    public static String line(String letter, int count) {
        return letter.repeat(count);
    }

    public static String line() {
        return line;
    }

    public static Scanner sc = new Scanner(System.in);
    public static LocalDateTime now = LocalDateTime.now();

    // 입력 상품
    // public static List<Inventory> items = new ArrayList<>();

    public static Map<Long, Inventory> itemsMap = new HashMap<>();

    // 예시 상품
    // public static List<Inventory> fruits;

//    static {
//        fruits = new ArrayList<>(Arrays.asList(
//                new Inventory(1L, "apple", 1000, 25, now),
//                new Inventory(2L, "banana", 1500, 50, now),
//                new Inventory(3L, "cherry", 2000, 75, now),
//                new Inventory(4L, "date", 3000, 100, now)
//        ));
//    }


    public static void main(String[] args) {
        loadInventory();
        while (true) {
            int input = startPage();
            switch (input) {
                case 0:
                    saveInventory();
                    System.out.println("[종료]");
                    return;
                case 1:
                    addItems();
                    break;
                case 2:
                    viewItems();
                    break;
                case 3:
                    changeItems();
                    break;
                case 4:
                    deleteItems();
                    break;
                default:
                    error("입력오류", "유효하지 않은 입력입니다.");
                    break;
            }
        }
    } // end main


    public static int startPage() {

        String headline = line("=", 25);
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
                """, headline, headline, headline, headline);
        int input = sc.nextInt();
        sc.nextLine();
        return input;

    }


    public static Map<Long, Inventory> addItems() {

        // 입력
        System.out.println("[입력]");
        System.out.println(line);
        System.out.println("상품 입력을 시작합니다.");
        System.out.println(line);

        Inventory item = new Inventory();
        item.setId(id);
        id++;

        item.setCreatedDate(now);
        System.out.println("상품 이름을 입력해주세요.");
        System.out.println(line);
        String name = sc.nextLine();
        item.setName(name);

        if (name.isEmpty()) {
            error("입력오류", "유효하지 않은 입력입니다.");
            name = sc.next();
            item.setName(name);
        }

        System.out.println(line);
        System.out.println("상품 가격을 정해주세요.");
        System.out.println(line);
        int price = sc.nextInt();
        item.setPrice(price);

        System.out.println(line);
        System.out.println("몇 개 등록하시겠습니까?");
        System.out.println(line);
        int count = sc.nextInt();
        item.setCount(count);

        itemsMap.put(item.getId(), item);
        list("입력", item);
        return itemsMap;
    }

    public static Map<Long, Inventory> viewItems() {

        // 열람
        System.out.println("[열람]");
        System.out.println(line);
        System.out.println("재고 상품 목록");
        System.out.println(line);
        // System.out.println(items);
        System.out.println("| id[0] | name[1] | price[2] | count[3] | time[4]");
        for (Inventory item : itemsMap.values()) {
            System.out.println(line);
            System.out.printf("| %d | %s | %d | %d | %s |\n", item.getId(), item.getName(), item.getPrice(), item.getCount(), item.getTime());
        }
        System.out.println(line);
        System.out.println("<출력완료>");

        return itemsMap;
    }

    public static Map<Long, Inventory> changeItems() {

        System.out.println("[수정]");
        System.out.println(line);
        viewItems();
        System.out.println(line);
        System.out.println("수정할 상품을 선택해 주세요. (ID 입력)");

        System.out.println(line);
        System.out.println("[선택] : ");

        long choice = sc.nextLong();
        System.out.println("선택 상품 : " + choice);
        System.out.println(line);

        boolean found = false;
        for (Inventory item : itemsMap.values()) {
            if (item.getId() == choice) {
                found = true;
                System.out.println("| id[0] | name[1] | price[2] | count[3] | time[4]");
                System.out.println(line);
                System.out.printf("| %d | %s | %d | %d | %s |\n", item.getId(), item.getName(), item.getPrice(), item.getCount(), item.getTime());
                System.out.println("상품 이름을 입력해주세요.");
                System.out.println(line);
                String name = sc.next();
                item.setName(name);

                System.out.println(line);
                System.out.println("상품 가격을 정해주세요.");
                System.out.println(line);
                int price = sc.nextInt();
                item.setPrice(price);

                System.out.println(line);
                System.out.println("몇 개 등록하시겠습니까?");
                System.out.println(line);
                int count = sc.nextInt();
                item.setCount(count);
                itemsMap.put(item.getId(), item);


                list("수정", item);
                return itemsMap;
            }


        }
        if (!found) {
            error("탐색오류", "존재하지 않는 상품입니다.");
        }
        return itemsMap;
    }

    public static Map<Long, Inventory> deleteItems() {
        System.out.println("[삭제]");
        System.out.println(line);
        viewItems();
        System.out.print("삭제할 상품을 선택해 주세요. (ID 입력): ");

        long delete = sc.nextLong();

        Iterator<Map.Entry<Long, Inventory>> iterator = itemsMap.entrySet().iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Map.Entry<Long, Inventory> entry = iterator.next();
            Inventory item = entry.getValue();

            if (item.getId() == delete) {
                found = true;
                list("삭제", item);
                iterator.remove(); // ✅ Correct way to remove while iterating
                System.out.println("<삭제완료>");
                break; // ✅ No need to continue after deletion
            }
        }

        if (!found) {
            error("탐색오류", "존재하지 않는 상품입니다.");
        }
        return itemsMap;
    }

    public static void error(String eCode, String eMsg) {
        System.out.printf("""
                [ERROR] %s
                %s
                <다시 입력해 주세요.>:""", eCode, eMsg);
    }

    public static void list(String title, Inventory item) {
        System.out.println(line);
        System.out.printf("<%s항목> \n", title);
        System.out.println(line);
        System.out.printf("[상품번호] : %d \n", item.getId());
        System.out.printf("[상품명칭] : %s \n", item.getName());
        System.out.printf("[상품가격] : %d \n", item.getPrice());
        System.out.printf("[상품수량] : %d \n", item.getCount());
        System.out.printf("[등록일시] : %s \n", item.getTime());
        System.out.println(line);
        System.out.printf("<%s완료> \n", title);
    }

    public static void saveInventory() {
        File directory = new File("data");
        File file = new File(DATA_FILE);

        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("'data' 폴더가 생성되었습니다.");
            } else {
                System.out.println("'data' 폴더 생성 실패.");
                return;
            }
        }


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(itemsMap);
            System.out.println("데이터 저장 완료.");
        } catch (IOException e) {
            System.out.println("저장 오류: " + e.getMessage());
        }
    }

    public static void loadInventory() {
        File directory = new File("data");
        File file = new File(DATA_FILE);

        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("'data' 폴더가 생성되었습니다.");
            } else {
                System.out.println("'data' 폴더 생성 실패.");
                return;
            }
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("'inventory.dat' 파일이 생성되었습니다.");
                } else {
                    System.out.println("'inventory.dat' 파일 생성 실패.");
                }
            } catch (IOException e) {
                System.out.println("파일 생성 오류: " + e.getMessage());
            }
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                itemsMap = (Map<Long, Inventory>) obj;
                System.out.println("데이터 불러오기 완료.");
            }
        } catch (EOFException e) {
            System.out.println("저장된 데이터가 없습니다. 새로운 재고를 등록하세요.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("불러오기 오류: " + e.getMessage());
        }
    }

}

