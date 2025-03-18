package practice.bookmanager;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

// 필요한 클래스 추가 가능
// 이 패키지 외의 패키지에는 클래스 만들지 마세요.
public class BookManagerMain {

    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "book.dat";
    private static List<Book> bookList = new ArrayList<Book>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CreateDirFile();
        loadBooks();  // 프로그램 시작시 파일 가져오기
        while (true) {
            showMenu();  // 메뉴 보여주기
            int choice = getUserChoice();
            if (choice == 0) {
                saveBooks();  // 종료 선택시 파일 저장하기
                System.out.println("프로그램을 종료합니다.");
                break;
            }  // end if
            gotMenu(choice);  // 선택한 메뉴 처리하기
        }  // end while
    } // end main()

    // {현재작업경로}/data/inventory.dat 생성
    public static void CreateDirFile() {
        String dirPath = System.getProperty("user.dir") + File.separator + BookManagerMain.DIRECTORY_NAME;
        String filePath = System.getProperty("user.dir") + File.separator + BookManagerMain.DIRECTORY_NAME + File.separator + BookManagerMain.FILE_NAME;

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

        // book.dat 파일 생성
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


    // 메뉴 출력
    private static void showMenu() {
        System.out.println("도서 관리 v1.0");
        System.out.println("------------------");
        System.out.println("[1] 입력 \n[2] 목록 [21]번호↓ [22]번호↑ [23]제목↓ [24]제목↑ [25]가격↓ [26]가격↑ [27]출판일↓ [28]출판일↑");
        System.out.println("[3] 수정 \n[4] 삭제");
        System.out.println("[0] 종료");
        System.out.println("------------------");
        System.out.print("선택: ");
    }

    // 사용자 입력 처리하기
    private static int getUserChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if ((choice >= 0 && choice <= 4) || (choice >= 21 && choice <= 28)) {
                    return choice;
                }
                System.out.println("올바른 숫자를 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    // 메뉴 처리하기
    private static void gotMenu(int choice) {
        if (choice == 1) {
            System.out.println("책 정보를 입력합니다");
            addBook();
        } else if (choice == 2) {
            System.out.println("책 정보 출력");
            displayBooks();
        } else if (choice == 3) {
            System.out.println("책 정보 수정");
            editBook();
        } else if (choice == 4) {
            System.out.println("책 삭제");
            deleteBook();
        } else {

        }
    }  // end gotMenu

    private static void addBook() {
        String no;
        while (true) {
            System.out.println("책 번호 입력 (알파벳1자리 + 숫자4자리 형식): ");
            no = scanner.nextLine().toUpperCase();
            if (no.matches("[A-Z][0-9]{4}")  && !no.isEmpty()) {
                break;
            }
            System.out.println("책 번호 형식이 아닙니다!");
        }
        String title;
        while(true) {
            System.out.println("책 제목 입력: ");
            title = scanner.nextLine();
            if (!title.isEmpty()) {
                break;
            }
            System.out.println("책 제목은 반드시 입력해야 합니다");
        }

        int price;
        while(true) {
            System.out.println("가격 입력(0이상 정수): ");
            price = Integer.parseInt(scanner.nextLine());
            try {
                if (price >= 0) break;
                System.out.println("잘못된 입력입니다");
            } catch (NumberFormatException e) {
                System.out.println("가격 입력(0이상 정수): ");
            }
        }

        LocalDate publishedAt;
        while (true) {
            System.out.println("출판일입력(yyyy-MM-dd): ");
            try {
                publishedAt = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다");
            }
        }

        bookList.add(new Book(no, title, price, publishedAt));
        System.out.println("데이터가 입력되었습니다");
    }  // end addBook

    private static void displayBooks() {
        if (bookList.isEmpty()) {
            System.out.println("저장된 도서가 없습니다.");
            return;
        }
        System.out.println("책 목록 출력");
        for(Book book : bookList) {
            System.out.println(book);

        }
    }

    private static void editBook() {
        System.out.println("수정할 책번호 를 입력하세요: ");
        String no = scanner.nextLine();

        for (Book book : bookList) {
            if (book.getNo().equals(no)) {
                System.out.println("책 제목 입력: ");
                String title = scanner.nextLine();
                System.out.println("가격 입력(0이상 정수): ");
                int price = Integer.parseInt(scanner.nextLine());
                System.out.println("출판일입력(yyyy-MM-dd): ");
                LocalDate publishedAt = LocalDate.parse(scanner.nextLine());

                book.setPrice(price);
                book.setPublishedAt(publishedAt);
                book.setTitle(title);

                System.out.println("수정되었습니다");
                return;
            }
        }
        System.out.println("해당되는 책번호가 없습니다 다시 입력해주세요");
    }



    private static void deleteBook() {
        System.out.print("삭제할 책번호를 입력하세요: ");
        String no = scanner.nextLine().toUpperCase();
        Iterator<Book> iterator = bookList.iterator();  // bookList.iterator() -> bookList 에서 순차적으로 탐색할 수 있는 iterator 객체 생성

        while (iterator.hasNext()) {  // iterator 가 남은게 있을때까지 탐색 없으면 false 리턴
            if (iterator.next().getNo().equals(no)) {  // getNo 한 값이 no 값이랑 일치하는것이 있으면 아래 if 구문을 실행시킨다.
                iterator.remove();
                System.out.println("삭제하였습니다.");
                return;
            }
        }
        System.out.println("해당되는 책번호가 없습니다 다시 입력해주세요");
    }

    private static void saveBooks() {
    }

    private static void loadBooks() {
    }

    private static void sortBooks(int choice) {

    }

} // end class























