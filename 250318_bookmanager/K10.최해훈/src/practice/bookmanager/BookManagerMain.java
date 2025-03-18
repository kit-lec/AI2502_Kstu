package practice.bookmanager;

import java.io.*;
import java.util.*;

// 필요한 클래스 추가 가능
// 이 패키지 외의 패키지에는 클래스 만들지 마세요.
public class BookManagerMain {
    public static final String DIRECTORY = "data";
    public static final String FILE = "data.dat";
    public static final Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();
    static Integer idCount = 0;

    public static void main(String[] args) {
        existFile();
        readFile();

        while (true) {
            showMenu();
            Integer number = Integer.valueOf(sc.nextLine());
            System.out.println();

//            ArrayList<Book> sortList = new ArrayList<>(books);

            if (number == 0) {
                saveFile();
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (number == 1) addBook();
            else if (number == 2) showList();
            else if (number == 3) setBook();
            else if (number == 4) removeBook();
//            else if (number == 21) Collections.sort(sortList);
//            else if (number == 22) Collections.sort(sortList(), new Desc());

            else{
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }

    } // end main()

    public static void showMenu() {
        System.out.print("""
                도서관리 v1.0
                ------------------
                [1] 입력
                [2] 목록 [21]번호↓ [22]번호↑ [23]제목↓ [24]제목↑ [25]가격↓ [26]가격↑ [27]출판일↓ [28]출판일↑
                [3] 수정
                [4] 삭제
                [0] 종료
                ------------------
                선택: """);
    }

    private static void existFile() {
        try {
            File dir = new File(DIRECTORY);
            File file = new File(DIRECTORY + File.separator + FILE);

            if (!dir.exists()) dir.mkdir();
            if (!file.exists()) file.createNewFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile() {
        File file = new File(DIRECTORY + File.separator + FILE);
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (data.length == 4) {
                    books.add(new Book(data[0], data[1], Integer.parseInt(data[2]), data[3]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        idCount = books.size();
    }

    public static void addBook() {
        System.out.println("책 정보를 입력합니다.");

        String no;
        while (true) {
            System.out.print("책 번호 입력 (알파벳1자리 + 숫자4자리 형식) : ");
            no = sc.nextLine().toUpperCase();

            if (!no.matches("[a-zA-Z]\\d{4}")) {
                System.out.println("잘못된 형식입니다.");
                continue;
            } else break;
        }

        String title;
        while (true) {
            System.out.print("책 제목 입력 : ");
            title = sc.nextLine().trim();

            if (title.isEmpty()) {
                System.out.println("책 제목은 반드시 입력해야 합니다.");
                continue;
            }
            break;
        }

        Integer price;
        while (true) {
            System.out.print("책 가격 입력 (0이상 정수) : ");
            try {
                price = Integer.parseInt(sc.nextLine());
                if (price < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }
        }

        System.out.print("책 출판일입력 (yyyy-MM-dd) : ");
        String publishedAt = sc.nextLine();

        books.add(new Book(no, title, price, publishedAt));
        System.out.println("데이터가 입력되었습니다.\n");
        idCount++;
    }

    public static void showList() {
        System.out.println("책 목록 출력(" + idCount + "개의 데이터)");

        System.out.println("|    Uid | Title           |      Price | Publication |");
        for (Book i : books) {
            System.out.println(i);
        }
        System.out.println();
    }

    public static void setBook() {
        System.out.print("수정할 책 번호를 입력하세요 : ");
        String no = sc.nextLine().toUpperCase();

        Book findbook = null;
        for (Book i : books) {
            if (i.getNo().equals(no)) {
                findbook = i;
                break;
            }
        }

        System.out.print("수정할 책 제목 입력 : ");
        findbook.setTitle(sc.nextLine().toUpperCase());

        System.out.print("가격 입력 (0이상 정수) : ");
        try {
            findbook.setPrice(Integer.parseInt(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }

        System.out.println("출판일 입력 (yyyy-MM-dd) : ");
        findbook.setPublishedAt(sc.nextLine());

        System.out.println("수정되었습니다.\n");
    }

    public static void removeBook() {
        System.out.print("책 삭제\n삭제할 책번호를 입력하세요 : ");
        String no = sc.nextLine().toUpperCase();

        Book findbook = null;
        for (Book i : books) {
            if (i.getNo().equals(no)) {
                findbook = i;
                break;
            }
        }

        books.remove(findbook);
        System.out.println("삭제하였습니다.\n");
        idCount--;
    }

    public static void saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DIRECTORY + File.separator + FILE))) {
            for (Book book : books) {
                bw.write(String.format("%s\t%s\t%d\t%s" + "\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("데이터를 파일에 저장합니다.");
    }
} // end class