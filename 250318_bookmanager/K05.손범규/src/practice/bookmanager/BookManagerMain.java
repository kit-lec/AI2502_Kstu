package practice.bookmanager;

// Imports

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// 필요한 클래스 추가 가능
// 이 패키지 외의 패키지에는 클래스 만들지 마세요.

public class BookManagerMain {

    // Fields (static)

    private static final String DATA_FILE = "data/book.dat";
    public static Scanner sc = new Scanner(System.in);
    public static LocalDate now = LocalDate.now();
    public static List<Book> books = new ArrayList<>();
    public static List<Book> sorted = new ArrayList<>();
    private static final DateTimeFormatter[] DATE_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("yyyy-MM-d"),
            DateTimeFormatter.ofPattern("yyyy-M-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    // Methods (static)

    ///  main() Method
    public static void main(String[] args) {
        loadBook();
        while (true) {
            int input = startPage();
            switch (input) {
                case 0:
                    saveBook();
                    sc.nextLine();
                    return;
                case 1:
                    createBook();
                    break;
                case 2:
                    readBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 21: // 번호 오름차순
                    sorted.sort((b1, b2) -> b1.getNo().compareTo(b2.getNo()));
                    sortBook();
                    break;
                case 22: // 번호 내림차순
                    sorted.sort((b1, b2) -> b2.getNo().compareTo(b1.getNo()));
                    sortBook();
                    break;
                case 23: // 제목 오름차순
                    sorted.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
                    sortBook();
                    break;
                case 24: // 제목 내림차순
                    sorted.sort((b1, b2) -> b2.getTitle().compareTo(b1.getTitle()));
                    sortBook();
                    break;
                case 25: // 가격 오름차순
                    sorted.sort((b1, b2) -> b1.getPrice() - b2.getPrice());
                    sortBook();
                    break;
                case 26: // 가격 내림차순
                    sorted.sort((b1, b2) -> b2.getPrice() - b1.getPrice());
                    sortBook();
                    break;
                case 27: // 출판일 오름차순
                    sorted.sort((b1, b2) -> b1.getPublishedAt().compareTo(b2.getPublishedAt()));
                    sortBook();
                    break;
                case 28: // 출판일 내림차순
                    sorted.sort((b1, b2) -> b2.getPublishedAt().compareTo(b1.getPublishedAt()));
                    sortBook();
                    break;
                default:
                    // error("입력오류", "화면에 적힌 숫자만 입력해 주세요.");
                    p("잘못된 입력입니다");
                    break;
            }
        }
    } // end main()

    /// Function (CRUD) Methods

    public static void createBook() {

        Book book = new Book();
        p("책 정보를 입력합니다");

        // set no
        p("책 번호 입력 (알파벳1자리 + 숫자4자리 형식):");
        String no = sc.nextLine().trim().toUpperCase();
        while (no.isBlank()) {
            p("번호는 반드시 입력해야 합니다");
            // error("입력오류", "공백입니다.");
            no = sc.nextLine().trim().toUpperCase();
            if (!no.isBlank()) break;
        }
        while (!no.matches("^[A-Z][0-9]{4}$")) {
            p("책 번호 형식이 아닙니다!");
            no = sc.nextLine();
            if (no.matches("^[A-Z][0-9]{4}$")) break;
        }
        book.setNo(no);

        // set title
        p("책 제목 입력:");
        String title = sc.nextLine();
        while (title.isBlank()) {
            p("책 제목은 반드시 입력해야 합니다");
            // error("입력오류","공백입니다.");
            title = sc.nextLine();
            if (!title.isBlank()) {
                break;
            }
        }
        book.setTitle(title);

        // set price
        p("가격 입력(0이상 정수):");
        int price;
        while (true) {
            try {
                price = parseInt(sc.nextLine());
                while (price < 0) {
                    // error("입력오류", "가격은 0 이상이어야 합니다.");
                    p("잘못된 입력입니다");
                    price = parseInt(sc.nextLine());
                }
                book.setPrice(price);
                break;
            } catch (NumberFormatException e) {
                // error("입력오류", "숫자만 입력해 주세요.");
                p("잘못된 입력입니다");
            }
        }

        // set publishedAt
        p("출판일입력(yyyy-MM-dd):");
        LocalDate date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        while (true) {
//            try {
//                for (DateTimeFormatter formatter : DATE_FORMATS) {
//                    try {
//
//                    } catch (DateTimeParseException ignored) {
//                    }
//                }
//            } catch (Exception e) {
//                p("잘못된 입력입니다\n");
//            }
//        }

        book.setPublishedAt(date);

        books.add(book);
        sorted.add(book);
        p("데이터가 입력되었습니다");

    }

    public static void readBook() {
        p("책 목록 출력");
//        System.out.printf("%d 개의 데이터 \n", books.size());
        pf("%d 개의 데이터 \n", books.size());
        p("Uid| Title | Price | Publication");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public static void sortBook() {
        p("책 목록 출력");
//        System.out.printf("%d 개의 데이터 \n", books.size());
        pf("%d 개의 데이터 \n", sorted.size());
        p("Uid| Title | Price | Publication");
        for (Book book : sorted) {
            System.out.println(book.toString());
        }

    }

    public static void updateBook() {
        p("책 정보 수정");
        readBook();
        p("수정할 책번호 를 입력하세요:");
        String choice = sc.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getNo().equals(choice)) {
                found = true;
                System.out.println(book.toString());

                String title = sc.nextLine();
                book.setTitle(title);

                Integer price = parseInt(sc.nextLine());
                book.setPrice(price);

//                TODO
//                String publishedAt = (sc.nextLine());
//                book.setPublishedAt();
                p("수정되었습니다");
            }
        }
        if (!found) {
            p("잘못된 입력입니다");
        }
    }

    public static void deleteBook() {
        p("책 삭제");
        readBook();
        p("삭제할 책번호 를 입력하세요:");
        String choice = sc.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getNo().equals(choice)) {
                found = true;
                books.remove(book);
                p("삭제하였습니다");
            }
        }
        if (!found) {
            p("잘못된 입력입니다");
        }
    }

    /// File Methods

    public static void saveBook() {
        File directory = new File("data");
        File file = new File(DATA_FILE);

        if (!directory.exists()) directory.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(books);
            p("데이터를 파일에 저장합니다.");
        } catch (IOException e) {
            p("파일 저장 오류: " + e.getMessage());
        }
    }

    public static void loadBook() {
        File file = new File(DATA_FILE);
        books = new ArrayList<>();

        if (!file.exists()) {
            try {
                if (file.createNewFile()) p("새 파일 생성");
            } catch (IOException e) {
                p("파일 생성 오류: " + e.getMessage());
            }
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                books = (List<Book>) obj;
                p("파일로부터 데이터를 읽어왔습니다.");
            }
        } catch (IOException | ClassNotFoundException e) {
            p("파일 읽기 오류: " + e.getMessage());
        }
    }


    /// IO Methods
    /// / Input Methods


    //// Output Methods

    ///  Simple Prints
    public static void p(String s) {
        System.out.println(s);
    }


    public static void pf(String s, Object t) {
        System.out.printf(s, t);
    }


    public static void pt(String s) {
        System.out.print(s);
    }


    public static int startPage() {
        while (true) {
            System.out.println("""
                    도서관리 v1.0
                    ------------------
                    [1] 입력
                    [2] 목록 [21]번호↓ [22]번호↑ [23]제목↓ [24]제목↑ [25]가격↓ [26]가격↑ [27]출판일↓ [28]출판일↑
                    [3] 수정
                    [4] 삭제
                    [0] 종료
                    ------------------
                    선택:""");
            try {
                int input = parseInt(sc.nextLine());
                return input;
            } catch (NumberFormatException e) {
                error("입력오류", "숫자를 입력해 주세요.");
            }
        }
    }

    public static void error(String eCode, String eMsg) {
        System.out.printf("""
                [ERROR] %s
                %s
                <다시 입력해 주세요.>:""", eCode, eMsg);
    }

} // end class

interface Books {
}






















