package practice.inventory;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryMain {
    private static Long counter = 1L;

    public static final String DIRECTORY = "data";
    public static final String FILE = "inventory.dat";

    static ArrayList<Inventory> inventory = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        existDirFile();
        readFile();

        while (true) {
            System.out.println();
            showMenu();

            int number = sc.nextInt();
            System.out.println();

            if (number == 0) {
                System.out.println("프로그램을 종료합니다.");
                saveFile();
                break;
            } else if (number == 1) addItem();
            else if (number == 2) showInventory();
            else if (number == 3) modifyItem();
            else if (number == 4) removeItem();
            else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
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

    public static void existDirFile() {
        File dir = new File(DIRECTORY);
        File file = new File(DIRECTORY + File.separator + FILE);

        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("data 폴더를 생성하였습니다.");
            }
        } else {
            System.out.println("data 폴더가 이미 존재합니다.");
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("inventory.dat 파일 생성 완료");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("상품정보 파일을 읽어왔습니다.");
        }
    }

    public static void saveFile() {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(DIRECTORY + File.separator + FILE));
        ) {
            for (Inventory i : inventory) {
                bw.write(i.getId() + "\t" + i.getName() + "\t" + i.getPrice() + "\t" + i.getCount() + "\n");
            }
            bw.flush();
            System.out.println("파일을 저장합니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile() {
        File file = new File(DIRECTORY + File.separator + FILE);
        String line;
        Long idCount = 1L;

        try (
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            while((line = br.readLine()) != null) {
                String[] items = line.split("\t");
                Long id = Long.parseLong(items[0]);
                String name = items[1];
                int price = Integer.parseInt(items[2]);
                int count = Integer.parseInt(items[3]);

                inventory.add(new Inventory(id, name, price, count));
                idCount++;
            }
            counter = idCount;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addItem() {
        System.out.print("아이템 항목 입력을 시작합니다.\n아이템 이름을 입력해주세요 : ");
        String name = sc.next();

        System.out.print("아이템 가격을 정해주세요 : ");
        Integer price = sc.nextInt();

        System.out.print("몇개 등록하시겠습니까 ? ");
        Integer count = sc.nextInt();

        inventory.add(new Inventory(counter++, name, price, count));
        System.out.println("아이템이 추가되었습니다.");

    }

    public static void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("현재 등록된 아이템이 없습니다.");
        }

        System.out.println("모든 아이템 항목을 출력합니다.");
        System.out.println("|   id  |    name    |    price   |    count   |         time        |");
        System.out.println("-".repeat(70));

        for (Inventory i : inventory) {
            System.out.println(i);
        }

    }

    public static void modifyItem() {
        System.out.print("변경할 상품 id를 입력해주세요 : ");
        Long id = sc.nextLong();
        boolean found = false;

        for (Inventory i : inventory) {
            if (i.getId().equals(id)) {
                System.out.println("선택한 항목은 다음과 같습니다.");
                System.out.println(i);

                sc.nextLine(); // 버퍼 비우기
                System.out.print("상품명을 입력해주세요 : ");
                String name = sc.nextLine();

                System.out.print("가격을 입력해주세요 : ");
                Integer price = sc.nextInt();

                System.out.print("개수를 입력해주세요 : ");
                Integer count = sc.nextInt();

                i.setName(name);
                i.setPrice(price);
                i.setCount(count);

                found = true;
                System.out.println("상품 정보가 수정되었습니다.");
                break;
            }
        }

        if (!found) {
            System.out.println("존재하지 않는 id : " + id);
        }
    }

    public static void removeItem() {
        System.out.print("삭제할 상품 id를 입력해주세요 : ");
        Long id = sc.nextLong();
        boolean removed = inventory.removeIf(i -> i.getId().equals(id));

        if (removed) {
            System.out.println("상품이 삭제되었습니다.");
        } else {
            System.out.println("존재하지 않는 id : " + id);
        }
    }

}


