package practice.bookmanager;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

// 필요한 클래스 추가 가능
// 이 패키지 외의 패키지에는 클래스 만들지 마세요.
public class BookManagerMain {
    //1, 도서관 책 정보를 입력할 수 있는 메소드 들을 만든다.
    // 2, 일단 2 번을 제외한 다른 걸 일단 만들자.
    //
    static HashMap <String , Book> bookMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static Book book = new Book();
    static String regPattern = "^[a-zA-Z][0-9]{4}$";
    static String datePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    public static void main(String[] args) {
        // 폴더 안에 /data 가 있는지 파악하기

        String bookFile = "book.dat";
        String path = System.getProperty("user.dir") + File.separator + "data";

        File file = new File(path);
        if (!file.exists()) {
            try {
                if(file.createNewFile());
                else System.out.println("파일 생성 실패");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        else{
            System.out.println("파일 이미 존재");
        }

        // TODO
        while(true){
            System.out.println("도서관리 v1.0\n" +
                    "------------------\n" +
                    "[1] 입력\n" +
                    "[2] 목록 [21]번호↓ [22]번호↑ [23]제목↓ [24]제목↑ [25]가격↓ [26]가격↑ [27]출판일↓ [28]출판일↑\n" +
                    "[3] 수정\n" +
                    "[4] 삭제\n" +
                    "[0] 종료\n" +
                    "------------------\n" +
                    "선택:\n");
            int choice = sc.nextInt();
            if (choice == 1) {
                // 입력
                choice1();
            }
            else if (choice == 2) {
                //목록
                choice2();
            }
            else if (choice == 3) {
                choice3();
            }
            else if (choice == 4) {
                choice4();
            }
            else if (choice == 0) {
                choice0();

                try (
                        OutputStream out = new FileOutputStream(bookFile);
                        ObjectOutputStream oout = new ObjectOutputStream(out)
                ) {
                    oout.writeObject(bookMap);
                    System.out.println("책 데이터를 파일에 성공적으로 저장했습니다.");
                } catch (IOException e) {
                    System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
                }
                System.out.println("파일 저장에 성공했습니다");
                break;
            }
            else {
                System.out.println("잘못된 번호 입니다");
                System.out.println("재입력 해주세요");
            }

        }

    } // end main()
    // 입력 창
    public static void choice1 (){
        // 책 번호 입력
        System.out.println("책 정보를 입력합니다");
        System.out.println("책 번호 입력 (알파벳1자리 + 숫자4자리 형식):");
        sc.nextLine();
        String bookNum = sc.nextLine();
        book.setNo(bookNum.trim());
        // 책의 번호가 없으면 리턴
        if (bookNum.isEmpty()) {
            System.out.println("번호는 반드시 입력해야 합니다");
            return;
        }
        // 만약 책 번호에서 regstr,num 이 없을 경우 리턴
        else if (!bookNum.matches(regPattern)) {
            System.out.println("책 번호 형식이 아닙니다!");
            return;
        }
        //책 번호에서 알파벳이 하나만 이어야 하는 경우 (보류)
        // bookNum에 있는 단어 대문자로 만들기
        bookNum = bookNum.toUpperCase();

        // 책 제목 입력
        System.out.println("책 제목은 반드시 입력해야 합니다\n" +
                "책 제목 입력\n");
        String bookName = sc.nextLine();
        if (bookName.isEmpty()) {
            System.out.println("책 제목은 반드시 한글자 이상이여야 합니다");
            return;
        }
        // 가격입력
        System.out.println("가격 입력(0이상 정수):");
        Integer bookPrice = sc.nextInt();
        if (bookPrice < 0) {
            System.out.println("잘못된 입력입니다");
        }
        if (String.format(bookPrice.toString()).isEmpty()) {
            System.out.println("번호는 반드시 입력해야 합니다");
            return;
        }

        //날짜 입력하기
        System.out.println("출판일입력(yyyy-MM-dd)");
        sc.nextLine();
        String dateStr = sc.nextLine().trim();
        if (!dateStr.matches(datePattern)) {
            System.out.println("입력이 잘못되었습니다");
            return;
        }

        // 값 저장하기

        LocalDate date = LocalDate.parse(dateStr);
        Book newBook = new Book(bookNum, bookName, bookPrice, date);
        bookMap.put(bookNum, newBook);
        System.out.println("책이 성공적으로 등록되었습니다!");
    }


    //목록 창
    public static void choice2 (){
        Set<String> kSet = bookMap.keySet();
        System.out.println(" Uid | Title            |     Price| Publication");
        for (String k : kSet) {
            Book book = bookMap.get(k);
            System.out.println(book.getNo() + "| " +  book.getTitle() + "            |     " + book.getPrice() + " | " + book.publishedAt);
        }
        System.out.println();
    }


    // 수정 창
    public static void choice3 (){
        System.out.println("수정할 책 번호를 입력하세요: ");
        sc.next();
        String bookNum = sc.nextLine().trim();
        // 책의 번호가 없으면 리턴
        if (bookNum.isEmpty()) {
            System.out.println("번호는 반드시 입력해야 합니다");
            return;
        }
        // 만약 책 번호에서 regstr,num 이 없을 경우 리턴
        else if (!bookNum.matches(BookManagerMain.regPattern) || !bookNum.matches(regPattern)) {
            System.out.println("책 번호 형식이 아닙니다!");
            return;
        }
        //책 번호에서 알파벳이 하나만 이어야 하는 경우 (보류)
        // bookNum에 있는 단어 대문자로 만들기
        bookNum = bookNum.toUpperCase();

        // 수정할 책 제목 입력
        System.out.println("책 제목 입력:");
        String replaceBookName = sc.nextLine();
        if (replaceBookName.isEmpty()) {
            System.out.println("책 제목은 반드시 한글자 이상이여야 합니다");
            return;
        }
        // 수정할 가격입력
        System.out.println("가격 입력(0이상 정수):");
        Integer replaceBookPrice = sc.nextInt();
        if (replaceBookPrice < 0) {
            System.out.println("잘못된 입력입니다");
        }
        if (String.format(replaceBookPrice.toString()).isEmpty()) {
            System.out.println("번호는 반드시 입력해야 합니다");
            return;
        }

        //수정할 날짜 입력하기
        System.out.println("출판일입력(yyyy-MM-dd)");
        sc.nextLine();
        String replaceDateStr = sc.nextLine().trim();
        if (!replaceDateStr.matches(datePattern)) {
            System.out.println("입력이 잘못되었습니다");
            return;
        }
        LocalDate replaceDate =LocalDate.parse(replaceDateStr);

        // 데이터 수정하기
        book.setTitle(replaceBookName);
        book.setPrice(replaceBookPrice);
        book.setPublishedAt(replaceDate);

        bookMap.put(bookNum, book);


    }

    //삭제 창
    public static void choice4 (){
        System.out.println("삭제할 책 번호를 입력하세요:");
        sc.next();
        String removeBookNum = sc.nextLine().trim();  // next() 제거하고 nextLine()만 사용

        // 책 번호가 없으면 리턴
        if (removeBookNum.isEmpty()) {
            System.out.println("번호는 반드시 입력해야 합니다");
            return;
        }

        // 형식 검사
        if (!removeBookNum.matches(regPattern)) {
            System.out.println("책 번호 형식이 아닙니다!");
            return;
        }

        // 대소문자 통일 (기존 저장된 값이 대문자인 경우)
        removeBookNum = removeBookNum.toUpperCase();

        // 삭제 실행
        if (bookMap.containsKey(removeBookNum)) {
            bookMap.remove(removeBookNum);
            System.out.println(removeBookNum + " 책 정보를 삭제했습니다.");
        } else {
            System.out.println("책 번호를 찾을 수 없습니다.");
        }

    }

    public static void choice0 (){
        System.out.println("데이터를 파일에 저장합니다");
    }
} // end class

























