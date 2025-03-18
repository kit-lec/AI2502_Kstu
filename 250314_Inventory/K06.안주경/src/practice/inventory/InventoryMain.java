package practice.inventory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InventoryMain {
	private static final String DATA_DIRECTORY = "data";
	private static final String DATA_FILE = "data/inventory.dat";
	private static Map<Long, Inventory> inventoryMap = new HashMap<>();
	private static long idCounter = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		initializeData();

		int choice;

		while (true) {
			System.out.println("""
					상품정보 관리 프로그램 v1.0
					----------------------------
					[1] 입력
					[2] 열람
					[3] 수정
					[4] 삭제
					[0] 종료
					----------------------------
					선택:
					""");
			choice = sc.nextInt();
			sc.nextLine(); // 버퍼 비우기

			switch (choice) {
				case 1:
					addInventory(sc);
					break;
				case 2:
					viewInventory();
					break;
				case 3:
					updateInventory(sc);
					break;
				case 4:
					deleteInventory(sc);
					break;
				case 0:
					saveData();
					System.out.println("프로그램을 종료, 파일 저장");
					return;
				default:
					System.out.println("선택하신 번호는 존재하지 않습니다. 재입력해주십시오.");
			}
		}
	} //main

	private static void saveData() {
		try(ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(DATA_FILE)))){
			oos.writeObject(inventoryMap);
		} catch (IOException e) {
			System.out.println("데이터 저장 실패 ");
		}
	}

	private static void initializeData() {
		File dir = new File(DATA_DIRECTORY);
		if (!dir.exists()) {
			dir.mkdir();
			System.out.println("data 폴더 생성");
		} else {
			System.out.println("data 폴더 이미 존재.");
		}

		File file = new File(DATA_FILE);
		if (!file.exists()) {
			loadData();
			System.out.println("상품정보 파일 읽어오기");
		}
	}

	private static void loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
			inventoryMap = (Map<Long, Inventory>) ois.readObject();
			if (!inventoryMap.isEmpty()) {
				long maxId = 0;
				for (Long id : inventoryMap.keySet()) {
					if (id > maxId) {
						maxId = id;
					}
				}
				idCounter = maxId + 1;
			}
		} catch (IOException e) {
			System.out.println("데이터 불러오기 실패");
		} catch (ClassNotFoundException e) {
			System.out.println("데이터 불러오기 실패 ");
		}
	}

	public static void addInventory(Scanner sc) {
		System.out.println("아이템 이름을 입력해주세요: ");
		String name = sc.nextLine();
		if (name.isEmpty()) {
			System.out.println("ERR-2)문자열오류 insert() 이름이 입력되지 않았습니다 :");
			return;
		}

		System.out.println("아이템 가격을 정해주세요: ");
		int price = sc.nextInt();
		if (price < 0) {
			System.out.println("ERR-4)가격 오류 insert() 가격에 정상적인 입력이 되지 않았습니다.:");
			sc.nextLine(); // 버퍼 비우기
			return;
		}

		sc.nextLine(); // 버퍼 비우기

		System.out.println("몇 개 등록하시겠습니까? ");
		String countInput = sc.nextLine();
		int count = countInput.isEmpty() ? 0 : Integer.parseInt(countInput);

		Inventory inventory = new Inventory(idCounter, name, price, count, LocalDateTime.now());
		inventoryMap.put(idCounter, inventory);
		idCounter++;
		System.out.println("등록완료");

	}

	public static void viewInventory() {
		System.out.println("모든 아이템 항목을 출력합니다.");
		System.out.println("id \t name \t price \t count \t time");
		for (Inventory i : inventoryMap.values()) {
			System.out.println(i);
		}
	}

	public static void updateInventory(Scanner sc) {
		System.out.println("""
				선택한 항목의 상품의 내용을 변경합니다.
				변경할 상품 id를 입력해주세요
				""");
		long id = sc.nextLong();
		sc.nextLine(); // 버퍼 비우기

		Inventory inventory = inventoryMap.get(id);

		if (inventory == null) {
			System.out.println("ERR-3)ID오류 존재하지 않는 id: " + id);
			return;
		}

		System.out.print("상품명을 입력해주세요: ");
		String name = sc.nextLine();
		if (name.isEmpty()) {
			System.out.println("ERR-2)문자열오류 insert() 이름이 입력되지 않았습니다 :");
			return;
		}

		System.out.print("가격을 입력해주세요: ");
		int price = sc.nextInt();

		System.out.print("개수를 입력해주세요: ");
		int count = sc.nextInt();

		inventory.setName(name);
		inventory.setPrice(price);
		inventory.setCount(count);
		System.out.println("수정 성공");
		sc.nextLine(); // 버퍼 비우기
	}

	private static void deleteInventory(Scanner sc) {
			System.out.print("삭제할 상품 id를 입력해주세요: ");
			long id = Long.parseLong(sc.nextLine());

			if (inventoryMap.remove(id) != null) {
				System.out.println("삭제 성공!");
			} else {
				System.out.println("ERR-3)ID오류 존재하지 않는 id: " + id);
			}
		}



}