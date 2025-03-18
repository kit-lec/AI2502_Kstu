package practice.inventory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryMain {

    // List 사용 X -> HashSet, HashMap 사용
    // id 값도 저장

    private static long id = 0;
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "inventory.dat";

    private static HashMap<Long, Inventory> inventories = new HashMap<>();     // key = id, value = item

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CreateDirFile();

        // 파일 읽어오기
        try (
                InputStream in = new FileInputStream(DIRECTORY_NAME + "/" + FILE_NAME);
                ObjectInputStream oin = new ObjectInputStream(in);
        ) {
            inventories = (HashMap<Long, Inventory>) oin.readObject();

            long maxId = 0;
            for (Long id : inventories.keySet()) {
                if (id > maxId) {
                    maxId = id;
                }
            }
            InventoryMain.id = maxId;

            System.out.println("상품 정보 파일을 읽어왔습니다.\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("상품 정보 파일을 모두 읽어왔습니다.\n");
            System.out.println();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        // 메뉴 출력 후 입력 받아 알맞는 메소드 실행
        int choice = -1;
        while (true) {
            try {
                display();
                choice = sc.nextInt();
                sc.nextLine();  // 버퍼 비우기

                switch (choice) {
                    case 0:
                        // inventories 정보를 파일로 저장한 뒤 종료
                        try (
                                OutputStream out = new FileOutputStream(DIRECTORY_NAME + "/" + FILE_NAME);
                                ObjectOutputStream oout = new ObjectOutputStream(out);
                        ) {
                            oout.writeObject(inventories);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println("프로그램을 종료합니다.");
                        System.out.println("파일을 저장합니다.");
                        break;

                    case 1:
                        // [1] 입력: item 추가
                        addItem(sc);
                        break;

                    case 2:
                        // [2] 열람: 전체 item 출력
                        showItem();
                        break;

                    case 3:
                        // [3] 수정: item 1개 내용 변경
                        setItem(sc);
                        break;

                    case 4:
                        // [4] 삭제: item 1개 삭제
                        deleteItem(sc);
                        break;

                    default:
                        System.out.println("선택하신 번호의 항목은 존재하지 않습니다.");
                        System.out.println("재입력해주십시오.");
                }

                if (choice == 0) break;
            }
            catch (InputMismatchException e) { // TODO: 메뉴에서 문자 입력으로 인한 예외 발생 시에도 다시 입력할 수 있도록 하기
                System.out.println("범위 내의 숫자를 입력해주세요. (0 ~ 4)");
                sc.nextLine();  // 버퍼 지우기
            }
        }
    }


    // {현재작업경로}/data/inventory.dat 생성
    public static void CreateDirFile() {
        String dirPath = System.getProperty("user.dir") + File.separator + InventoryMain.DIRECTORY_NAME;
        String filePath = System.getProperty("user.dir") + File.separator + InventoryMain.DIRECTORY_NAME + File.separator + InventoryMain.FILE_NAME;

        // data 디렉토리 생성
        File d = new File(dirPath);

        if (!d.exists()) {    // 존재하지 않는 경우 디렉토리 생성
            if (d.mkdir()) {
                System.out.println("data 폴더를 생성하였습니다.");
            } else {
                System.out.println("data 폴더 생성에 실패했습니다.");
            }
        } else {
            System.out.println("data 폴더가 이미 존재합니다.");
        }

        // inventory.dat 파일 생성
        File f = new File(filePath);

        if (!f.exists()) {
            try {
                if (f.createNewFile()) {
                    System.out.println("파일 생성 성공");
                } else {
                    System.out.println("파일 생성 실패");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("파일이 이미 존재합니다.");
        }
    }


    // 전체 메뉴 출력
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


    // [1] 입력: item 추가
    public static void addItem(Scanner sc) {
        Inventory item = new Inventory();

        try {
            System.out.println("\n아이템 항목 입력을 시작합니다.");

            System.out.println("아이템 이름을 입력해주세요.");
            String name = sc.nextLine();

            System.out.println("아이템 가격을 정해주세요.");
            String price = sc.nextLine();

            System.out.println("몇 개 등록하시겠습니까?");
            String count = sc.nextLine();
            if (count.isEmpty()) count = "0";

            item.setCreatedDate(LocalDateTime.now());

            if (name.isEmpty()) {
                System.out.println("[에러] 이름은 필수 입력입니다.");
                System.out.println("다시 입력하세요.\n");
                return;
            }
            item.setName(name);

            if (price.isEmpty()) {
                System.out.println("[에러] 가격은 필수 입력입니다.\n");
                return;
            } else {
                item.setPrice(Integer.parseInt(price));
                if (item.getPrice() < 0) {
                    System.out.println("[에러] 가격은 0원 이상이어야 합니다.\n");
                    return;
                }
            }

            item.setCount(Integer.parseInt(count));
            if (item.getCount() < 0) {
                System.out.println("[에러] 개수는 0개 이상이어야 합니다.\n");
                return;
            }

            item.setId(++InventoryMain.id);

            InventoryMain.inventories.put(InventoryMain.id, item);

            System.out.println("입력 완료\n");
        } catch (NumberFormatException e) {
            System.out.println("[에러] 가격과 개수는 숫자를 입력하세요.\n");
            System.out.println("다시 입력하세요.\n");
        }
    }


    // [2] 열람: 전체 item 출력
    public static void showItem() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.println("id\tname\tprice\tcount\ttime");

        for (Inventory item : InventoryMain.inventories.values()) {
            System.out.println(item.toString());
        }
        System.out.println();
    }


    // [3] 수정: item 1개 내용 변경
    public static void setItem(Scanner sc) {
        System.out.println("\n선택한 항목의 상품의 내용을 변경합니다.");
        System.out.println("변경할 상품 id를 입력해주세요.");
        long findId = sc.nextInt();
        sc.nextLine();  // 버퍼 비우기
        if ((findId > InventoryMain.inventories.size()) && (findId > 0)) {
            System.out.println("[에러] ID 입력 오류\n");
            return;
        }

        System.out.println("\n선택한 항목은 다음과 같습니다.");
        System.out.println("-".repeat(20));

        try {
            System.out.println(InventoryMain.inventories.get(findId).toString());

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

            InventoryMain.inventories.get(findId).setName(name);
            InventoryMain.inventories.get(findId).setPrice(Integer.parseInt(price));
            InventoryMain.inventories.get(findId).setCount(Integer.parseInt(count));

            System.out.println("성공 여부: 1\n");
        } catch (NumberFormatException e) {
            System.out.println("[에러] 가격과 개수는 숫자로 입력하세요.\n");
            System.out.println("다시 입력하세요.\n");
        }
    }


    // [4] 삭제: item 1개 삭제
    public static void deleteItem(Scanner sc) {
        System.out.println("\n선택한 항목의 상품을 삭제합니다.");
        System.out.println("삭제할 상품 id를 입력해주세요.");
        long removeId = sc.nextInt();
        sc.nextLine();  // 버퍼 지우기

        if ((removeId > InventoryMain.inventories.size())) {
            System.out.println("[에러] ID 입력 오류\n");
            return;
        }

        InventoryMain.inventories.remove(removeId);

        System.out.println("삭제 완료\n");
    }

} // end class
