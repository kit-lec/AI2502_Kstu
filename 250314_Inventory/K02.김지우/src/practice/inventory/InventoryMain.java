package practice.inventory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InventoryMain {
    static Scanner sc = new Scanner(System.in);
    private static long idNum = 0;

    // 아이템 항목들 담는 배열 리스트
//    static List<Inventory> inventoryMap = new ArrayList<Inventory>();
    static HashMap<Long, Inventory> inventoryMap = new HashMap<>();

    public static void main(String[] args) {
        String filePath = "data/inventory.dat";

        // ./data 디렉토리 존재 확인 및 생성 부분
        String path = System.getProperty("user.dir") + File.separator + "data";

        File dataDir = new File(path);
        if(!dataDir.exists()){
            if(dataDir.mkdir()) System.out.println("data 폴더를 생성하였습니다!");
        }
        else System.out.println("data 폴더가 이미 존재합니다!");

        // data dir 생성되었으면 inventory.dat 파일 생성 (생성된 inventory 들을 담는 파일)
        // 이미 파일 있으면 읽어만 오자
        File inventoryFile = new File(dataDir, "inventory.dat");

        if(!inventoryFile.exists()){
            try {
                if(inventoryFile.createNewFile()) System.out.println("inventory.dat 파일 생성!");
                else System.out.println("inventory.dat 파일 생성 실패!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            System.out.println("inventory.dat 파일 존재.");
        }

        // inventory.dat 을 읽어오는 동작 시작~

        try(
                InputStream in = new FileInputStream(filePath);
                ObjectInputStream oin = new ObjectInputStream(in);
        ) {
            inventoryMap = (HashMap<Long, Inventory>) oin.readObject();
            System.out.println("상품 정보 파일을 모두 읽어왔습니다!");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(EOFException e){
            System.out.println("상품 정보 파일을 모두 읽어왔습니다!");
            System.out.println();
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        while(true){
            showMenu();
            int num = sc.nextInt();
            sc.nextLine(); // num 입력받고 남은 "\n" 을 없애준다.

            if (num == 1){
                // 입력
                addItem();
            }
            else if (num == 2){
                // 열람
                showItems();
            }
            else if (num == 3){
                // 수정
                updateItem();
            }
            else if (num == 4) {
                // 삭제
                deleteItem();
            }
            else if (num == 0){
                // 종료 전에 파일 저장해야지..
                try(
                        OutputStream out = new FileOutputStream(filePath);
                        ObjectOutputStream oout = new ObjectOutputStream(out);
                ) {
                    oout.writeObject(inventoryMap);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("프로그램을 종료합니다.");
                System.out.println("파일을 저장합니다.");
                break;
            }
            else{
                System.out.println("선택하신 번호의 항목은 존재하지 않습니다.");
                System.out.println("재입력해주십시오");
                System.out.println();
            }
        }
    } // end main


    // 메뉴 보여주는 메소드
    public static void showMenu() {
        System.out.println("상품정보 관리 프로그램 v1.0");
        System.out.println("-----------------------");
        System.out.println("[1] 입력");
        System.out.println("[2] 열람");
        System.out.println("[3] 수정");
        System.out.println("[4] 삭제");
        System.out.println("[0] 종료");
        System.out.println("-----------------------");
        System.out.println("선택: ");
    }


    // 아이템 입력하는 메소드
    public static void addItem(){
        Integer parsePrice;
        Integer parseCount;
        String parseItemName;

        Inventory inventory = new Inventory();
        System.out.println("아이템 항목 입력을 시작합니다.");

        try{
            System.out.println("아이템 이름을 입력해주세요.");
            String inputName = sc.nextLine();
            inventory.setName(inputName.trim()); // 일단 Empty 문자열이든 뭐든 담아보자

            System.out.println("아이템 가격을 정해주세요.");
            String inputPrice = sc.nextLine();

            System.out.println("몇 개 등록하시겠습니까?");
            String inputCount = sc.nextLine();

            if(inputName.isEmpty() || inputPrice.isEmpty()) {
                System.out.println("Err] 이름 또는 가격이 입력되지 않았습니다.");
                System.out.println();
                return;
            }

            inventory.setPrice(Integer.parseInt(inputPrice));
            inventory.setName(inputName.trim());

            if(inputCount.isEmpty()) {
                inventory.setCount(0);
            }
            else inventory.setCount(Integer.parseInt(inputCount));
        }
        catch(NumberFormatException e) { // 가격, 숫자에 숫자 이외의 값이 입력된 예외 처리
            System.out.println("가격과 개수는 숫자가 입력되어야 합니다.");
            System.out.println();
            return;
        }

        if (inventoryMap.keySet().size() != 0){
            idNum = Collections.max(inventoryMap.keySet()) + 1;
            inventory.setId(idNum);
        }
        else{
            idNum++;
            inventory.setId(idNum);
        }

        inventory.setCreatedDate(LocalDateTime.now());

        inventoryMap.put(idNum, inventory); // 리스트에 최종적으로 추가
        System.out.println();

    }

    public static void showItems() {
        System.out.println("모든 아이템 항목을 출력합니다.");
        System.out.println("id  name    price   count   time");

        Set<Long> kSet = inventoryMap.keySet();
        for (Long k : kSet) {
            Inventory value = inventoryMap.get(k);
            System.out.println("--------------------------------");
            System.out.println(value.getId() + " | " + value.getName() + " | " + value.getPrice() + "원" + " | " + value.getCount() + "개" + " | " + value.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        System.out.println();
    }

    public static void updateItem(){
        long numInputId;
        try{
            System.out.println("선택한 항목의 상품의 내용을 변경합니다.");
            System.out.println("변경할 상품 id를 입력해주세요");
            String inputId = sc.nextLine();

            System.out.println("선택한 항목은 다음과 같습니다.");
            numInputId = Integer.parseInt(inputId);
        } catch(NumberFormatException e) {
            System.out.println("id는 숫자가 입력되어야 합니다");
            System.out.println();
            return;
        }

        if(!inventoryMap.containsKey(numInputId)){ // inputId 와 같은 값의 key가 없다면! 즉 존재하지 않는 id 입력하면.
            System.out.println("Error] 존재하지 않는 id: " + numInputId);
            System.out.println();
            return;
        }

        // id 매치하여 해당 inventory를 담을 인스턴스
        Inventory updateInventory = new Inventory();

        updateInventory = inventoryMap.get(numInputId);

        System.out.println("--------------------------------");
        System.out.println(updateInventory.getId() + " | " + updateInventory.getName() + " | " + updateInventory.getPrice() + "원" + " | " + updateInventory.getCount() + "개" + " | " + updateInventory.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        try{
            System.out.println("상품명을 입력해주세요.");
            String updateName = sc.nextLine();

            System.out.println("가격을 입력해주세요.");
            String updatePrice = sc.nextLine();

            System.out.println("개수를 입력해주세요.");
            String updateCount = sc.nextLine();

            if (updateName.isEmpty()) {
                System.out.println("Error] 이름이 입력되지 않았습니다.");
                System.out.println();
                return;
            }

            if(updateCount.isEmpty()) {
                updateInventory.setCount(updateInventory.getCount());
            }
            else{
                updateInventory.setCount(Integer.parseInt(updateCount));
            }

            if(updatePrice.isEmpty()) {
                updateInventory.setPrice(updateInventory.getPrice());
            }
            else{
                updateInventory.setPrice(Integer.parseInt(updatePrice));
            }

            updateInventory.setName(updateName.trim());
            inventoryMap.put(numInputId, updateInventory);

            System.out.println("성공 여부: 1");
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("가격과 개수는 숫자가 입력되어야 합니다.");
            System.out.println();
        }
    }

    public static void deleteItem(){
        try{
            System.out.println("선택한 항목의 상품을 삭제합니다.");
            System.out.println("삭제할 상품의 id를 입력해주세요");

            System.out.println("선택");
            String inputId = sc.nextLine();

            if(inputId.isEmpty()){
                System.out.println("id가 입력되지 않았습니다. 메뉴창으로 돌아갑니다.");
            }

            long numInputId = Integer.parseInt(inputId);

            if(inventoryMap.containsKey(numInputId)){
                inventoryMap.remove(numInputId);
                System.out.println("성공 여부: 1");
            }
            else{
                System.out.println("Error] 존재하지 않는 id: " + numInputId);
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("id는 숫자값이어야 합니다.");
            System.out.println();
        }
    }
} // end class

// 에러 발생 시 다시 메뉴로 돌아오기
// id 번호 static으로 관리 하기