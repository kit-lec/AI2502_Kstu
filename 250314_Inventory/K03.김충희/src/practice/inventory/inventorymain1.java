package practice.inventory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class inventorymain1 {
  //없는 아이디 삭제 하면 다시 메뉴로 돌아와야 함
  // hashmap , hashset

  //1, 폴더 먼저 생성, 파일을 하나 생성해 만든다.
  //2, 생성한 파일에 입력 된 항목들을 하나씩 추가한다.
  //3, 3번에 수정 단계로 가면 파일을 수정한다
  // 4, removeto? 로 파일 내에 있는 것을 삭제 한다.
  static int count;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<inventory1> inv = new ArrayList<>();
    HashMap<Long, inventory1> map = new HashMap();
    Long id = 1L;
    String path = "Lec30_FileIO\\src\\practice\\data";
    File f = new File(path);
    String str = "Lec30_FileIO\\src\\practice\\data/inventory.dat ";
    File f2 = new File(str);

    System.out.println();
    // 폴더, 파일 만들기
    if (!f.exists()) {  // f(폴더)가 존재하지 않는다면 만들어라
      if (f.mkdirs()) {
        System.out.println("data 폴더를 생성하였습니다.\n" +
                "상품정보 파일을 읽어왔습니다.\n");
        try {  // 동시에 파일 만들어주기
          f2.createNewFile();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

      }
    } else {
      System.out.println("data 폴더가 이미 존재합니다\n" +
              "상품정보 파일을 읽어왔습니다.\n");
    }

    //상품정보 입력
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
      int choice = sc.nextInt();  // 1,2,3,4,0 번 중 고르기
      sc.nextLine();

      // 1번 입력
      if (choice == 1) {
        try {

          // 이름 입력
          System.out.println("아이템 항목 입력을 시작합니다.\n" + "아이템 이름을 입력해주세요.\n");
          String name = sc.nextLine();
          if (name.isEmpty()) {
            throw new InputMismatchException("문자열오류 insert() 이름이 입력되지 않았습니다 :");
          }
          // 가격정하기
          System.out.println("아이템 가격을 정해주세요.");
          int price = sc.nextInt();
          if (price < 0) {
            throw new InputMismatchException("입력이 잘못되었습니다.");
          }

          // 갯수 등록
          System.out.println("몇 개 등록하시겠습니까?");
          Integer count = sc.nextInt();
          LocalDateTime date = LocalDateTime.now();
          inv.add(new inventory1(id++, name, price, count, date));


          // hash map 안에 넣기
          map.put(id, new inventory1(id, name, price, count, date));

          // 파일안에 넣기
          saveToFile(map, str);
          FileWriter fw = new FileWriter(str, true);
        } catch (InputMismatchException e) {
          System.out.println(e.getMessage());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

      }

      // 2, 열람
      else if (choice == 2) {
        for (inventory1 i : inv) {
          System.out.println(i);
        }
      }
      //3, 수정
      else if (choice == 3) {
        System.out.println("수정할 상품의 id를 입력해주세요:");
        Long idToUpdate = sc.nextLong();
        inventory1 itemToUpdate = null;

        // 입력된 id에 맞는 항목 찾기
        for (inventory1 item : inv) {
          if (item.getId().equals(idToUpdate)) {
            itemToUpdate = item;
            break;
          }
        }

        if (itemToUpdate != null) {
          System.out.println("상품명을 입력해주세요.");
          String name = sc.next();
          // 이름이 입력되지 않을시
          if (name.isEmpty()) {
            throw new InputMismatchException("문자열오류 insert() 이름이 입력되지 않았습니다 :");
          }
          itemToUpdate.setName(name);
          System.out.println("가격을 입력해주세요.");
          int price = sc.nextInt();
          itemToUpdate.setPrice(price);
          System.out.println("개수를 입력해주세요.");
          int count = sc.nextInt();
          itemToUpdate.setCount(count);
          LocalDateTime date = LocalDateTime.now();

          // 수정된 데이트로 바꾸기
          map.put(idToUpdate, new inventory1(idToUpdate, name, price, count, date));
          // 파일 저장
          saveToFile(map, str);
          System.out.println("상품 수정 완료: " + idToUpdate);
        }
      }


        //삭제
      else if (choice == 4) {
        System.out.println("선택한 항목의 상품을 삭제 합니다.\n" +
                "삭제할 상품 id를 입력해주세요\n");
        Long id3 = sc.nextLong();
        Iterator<inventory1> it = inv.iterator();
        while (it.hasNext()) {
          if (it.next().getId().equals(id3)) {
            it.remove();
            System.out.println(" 상품이 삭제되었습니다");
            saveToFile(map, str);

          } else {
            throw new InputMismatchException("잘못된 입력입니다");
          }
        }
      }


      else if (choice == 0) {
        break;
      } else {
        System.out.println("선택하신 번호의 항목은 존재하지 않습니다. 재입력해주십시오.");
        throw new InputMismatchException();
      }
    }
  }


    private static void saveToFile (HashMap < Long, inventory1 > map, String path){
      // savetofile에 hashmap 만들기
      try (FileWriter fw = new FileWriter(path, false)) {
        // 파일을 덮어쓰기
        for (inventory1 i : map.values()) {
          fw.write(i + "\n");
        }
      } catch (IOException e) {
        System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
      }
    }
  }


