package practice.inventory;

import java.io.*;
import java.util.*;

class Inventory {
    private HashMap<Integer, Item> items; // 상품 ID를 Key로 하고 Item 객체를 Value로 저장
    private final String DIRECTORY_PATH = "data"; // 데이터 저장 폴더
    private final String FILE_PATH = DIRECTORY_PATH + "/inventory.txt"; // 데이터 저장 파일

    // 생성자: 데이터 불러오기 + 폴더/파일 생성
    public Inventory() {
        items = new HashMap<>();
        createDirectoryIfNotExists();
        createFileIfNotExists();
        loadFromFile();
    }

    // 폴더 생성 메서드
    private void createDirectoryIfNotExists() {
        File dir = new File(DIRECTORY_PATH);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("데이터 폴더 생성 완료!");
            } else {
                System.out.println("데이터 폴더 생성 실패!");
            }
        }
    }

    // 파일이 없으면 생성하는 메서드
    private void createFileIfNotExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("새로운 데이터 파일을 생성했습니다.");
                } else {
                    System.out.println("데이터 파일 생성 실패!");
                }
            } catch (IOException e) {
                System.out.println("파일 생성 중 오류 발생: " + e.getMessage());
            }
        }
    }

    // 파일에서 데이터 불러오기
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("저장된 데이터가 없습니다.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int price = Integer.parseInt(data[2]);
                int count = Integer.parseInt(data[3]);
                items.put(id, new Item(id, name, price, count)); // HashMap에 저장
            }
            System.out.println("데이터를 불러왔습니다.");
        } catch (IOException e) {
            System.out.println("파일 로드 오류: " + e.getMessage());
        }
    }

    // 데이터 저장 (파일에 쓰기)
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Item item : items.values()) {
                bw.write(item.toFileFormat());
                bw.newLine();
            }
            System.out.println("데이터를 저장했습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }

    // 상품 추가 (중복 검사 추가)
    public void addItem(int id, String name, int price, int count) {
        if (items.containsKey(id)) {
            System.out.println("이미 존재하는 상품 ID입니다.");
            return;
        }
        items.put(id, new Item(id, name, price, count));
        System.out.println("상품이 추가되었습니다.");
    }

    // 상품 조회 (출력 형식 개선)
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        System.out.println("ID | Name | Price | Count");
        System.out.println("----------------------------------");
        for (Item item : items.values()) {
            System.out.println(item);
        }
        System.out.println("----------------------------------");
    }

    // 상품 수정
    public void updateItem(int id, String newName, int newPrice, int newCount) {
        if (!items.containsKey(id)) {
            System.out.println("해당 ID의 상품이 없습니다.");
            return;
        }
        Item item = items.get(id);
        item.setName(newName);
        item.setPrice(newPrice);
        item.setCount(newCount);
        System.out.println("상품 정보가 수정되었습니다.");
    }

    // 상품 삭제
    public void removeItem(int id) {
        if (items.remove(id) != null) {
            System.out.println("상품이 삭제되었습니다.");
        } else {
            System.out.println("해당 ID의 상품이 존재하지 않습니다.");
        }
    }
}

// 상품(Item) 클래스
class Item {
    private int id;
    private String name;
    private int price;
    private int count;

    public Item(int id, String name, int price, int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toFileFormat() {
        return id + "," + name + "," + price + "," + count;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + price + "원 | " + count + "개";
    }
}
