package practice.bookmanager;


import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 필요한 클래스 추가 가능
// 이 패키지 외의 패키지에는 클래스 만들지 마세요.
public class BookManagerMain {

    private static long ID = 1;
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "book.dat";

    private static HashMap<Long, Book> bookList = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CreateDirFile();

        // 파일 읽어오기
        readFile();


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
                        saveFile();
                        break;

                    case 1:
                        // [1] 입력: book 추가
                        addBook(sc);
                        break;

                    case 2:
                        // [2] 목록: 전체 book 출력
                        showBooks();
                        break;

                    case 21:
                        showBooksNoAsc();
                        break;

                    case 22:
                        showBooksNoDesc();
                        break;

                    case 23:
                        showBooksTitleAsc();
                        break;

                    case 24:
                        showBooksTitleDesc();
                        break;

                    case 25:
                        showBooksPriceAsc();
                        break;

                    case 26:
                        showBooksPriceDesc();
                        break;

                    case 27:
                        showBooksPublishedAsc();
                        break;

                    case 28:
                        showBooksPublishedDesc();
                        break;

                    case 3:
                        // [3] 수정: book 1개 내용 변경
                        setBook(sc);
                        break;
//
                    case 4:
                        // [4] 삭제: book 1개 삭제
                        deleteBook(sc);
                        break;

                    default:
                        System.out.println("선택하신 번호의 항목은 존재하지 않습니다.");
                        System.out.println("재입력해주십시오.");
                }

                if (choice == 0) break;
            }
            catch (InputMismatchException e) {
                System.out.println("범위 내의 숫자를 입력해주세요. (0 ~ 4)");
                sc.nextLine();  // 버퍼 지우기
            }
        }

    } // end main()


    // 폴더 및 파일 생성
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

    // 파일 읽기

    public static void readFile() {
        try (
                InputStream in = new FileInputStream(DIRECTORY_NAME + "/" + FILE_NAME);
                ObjectInputStream oin = new ObjectInputStream(in);
        ) {
            bookList = (HashMap<Long, Book>) oin.readObject();

            // TODO: 파일 읽을 때 key(id) 설정
            long maxId = 0;
            for (Long id : bookList.keySet()) {
                if (id > maxId) {
                    maxId = id;
                }
            }
            BookManagerMain.ID = maxId + 1;

            System.out.println("상품 정보 파일을 읽어왔습니다.\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("상품 정보 파일을 모두 읽어왔습니다.\n");
            System.out.println();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일 저장
    public static void saveFile() {
        try (
                OutputStream out = new FileOutputStream(DIRECTORY_NAME + "/" + FILE_NAME);
                ObjectOutputStream oout = new ObjectOutputStream(out);
        ) {
            oout.writeObject(bookList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("프로그램을 종료합니다.");
        System.out.println("파일을 저장합니다.");
    }


    // 전체 메뉴 출력
    public static void display() {
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
    }


    // [1] 입력: book 추가
    public static void addBook(Scanner sc) {
        Book book = new Book();
        String num;
        String title;
        String price;
        String publishedDate;

        try {
            System.out.println("\n책 정보를 입력합니다.");

            // 책 번호
            while (true) {
                System.out.println("책 번호 입력 (알파벳1자리 + 숫자4자리 형식): ");
                num = sc.nextLine();

                // 입력값 검증 - 책 번호 (no)
                if (num.isEmpty()) {
                    System.out.println("[에러] 번호는 반드시 입력해야 합니다.\n");
                    continue;
                }
                Matcher matcher = Pattern.compile("^([a-zA-Z]){1}([0-9]){4}$").matcher(num);
                if (!matcher.find()) {
                    System.out.println("[에러] 번호를 양식에 맞춰 작성해주세요.");
                    continue;
                }

                book.setNo(num.toUpperCase());
                break;
            }

            // 책 제목
            while (true) {
                System.out.println("책 제목 입력: ");
                title = sc.nextLine();

                // 입력값 검증 - 책 이름 (title)
                if (title.isEmpty()) {
                    System.out.println("[에러] 책 제목은 반드시 입력해야 합니다.\n");
                    continue;
                }

                book.setTitle(title);
                break;
            }

            // 책 가격
            while (true) {
                System.out.println("가격 입력 (0 이상 정수): ");
                price = sc.nextLine();

                // 입력값 검증 - 가격 (price)
                if (price.isEmpty()) {
                    System.out.println("[에러] 가격은 반드시 입력해야 합니다.\n");
                    continue;
                }
                else {
                    try {
                        if (Integer.parseInt(price) < 0) {
                            System.out.println("[에러] 잘못된 입력입니다.\n");
                            continue;
                        }
                        book.setPrice(Integer.parseInt(price));
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("[에러] 잘못된 입력입니다.\n");
                        continue;
                    }
                }
            }

            // 책 출판일
            while (true) {
                System.out.println("출판일 입력 (yyyy-MM-dd): ");
                publishedDate = sc.nextLine();

                // 입력값 검증 - 출판일 (publishedDate)
                if (publishedDate.isEmpty()) {
                    System.out.println("[에러] 출판일은 반드시 입력해야 합니다.");
                    continue;
                }
                if (!(isDateMM(publishedDate) & isDateM(publishedDate))) {
                    System.out.println("[에러] 가격과 출판일을 양식에 맞게 입력하세요.\n");
                    continue;
                }
                book.setPublishedAt(formatDate(publishedDate));
                break;
            }


            // bookList 에 추가 (key = 책 번호(no), value = Book 객체)
            BookManagerMain.bookList.put(BookManagerMain.ID, book);
            BookManagerMain.ID++;

            System.out.println("데이터가 입력되었습니다.\n");
        } catch (NumberFormatException e) {
            System.out.println("[에러] 가격과 출판일을 양식에 맞게 입력하세요.\n");
            System.out.println("다시 입력하세요.\n");
        }
    }


    // [2] 목록: 전체 book 출력 - 입력 순서대로
    public static void showBooks() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        // 입력한 순서대로 출력 (key(id) 오름차순 정렬)
        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        Collections.sort(keySet);
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.println(book.toString());
        }

        System.out.println();
    }

    // [21] 목록: 번호 오름차순
    public static void showBooksNoAsc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getNo()));

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();
    }

    // [22] 목록: 번호 내림차순
    public static void showBooksNoDesc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getNo()).reversed());

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();
    }

    // [23] 목록: 제목 오름차순
    public static void showBooksTitleAsc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getTitle()));

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();
    }

    // [24] 목록: 제목 내림차순
    public static void showBooksTitleDesc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getTitle()).reversed());

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();
    }

    // [25] 목록: 가격 오름차순
    private static void showBooksPriceAsc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getPrice()));

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();
    }

    // [26] 목록: 가격 내림차순
    private static void showBooksPriceDesc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getPrice()).reversed());

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();
    }

    // [27] 출판일 오름차순
    private static void showBooksPublishedAsc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getPublishedAt()));

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();
    }

    // [28] 출판일 내림차순
    private static void showBooksPublishedDesc() {
        System.out.println("\n모든 아이템 항목을 출력합니다.");
        System.out.printf("%5s| %-20s| %10s| %10s\n", "Uid", "Title", "Price", "Publication");

        List<Long> keySet = new ArrayList<>(BookManagerMain.bookList.keySet());
        keySet.sort(Comparator.comparing(key -> BookManagerMain.bookList.get(key).getPublishedAt()).reversed());

        // 정렬된 keySet을 순회하며 책 정보 출력
        for (Long key : keySet) {
            Book book = BookManagerMain.bookList.get(key);
            System.out.printf("%5s| %-20s| %10d| %10s\n", book.getNo(), book.getTitle(), book.getPrice(), book.getPublishedAt());
        }

        System.out.println();

    }


    // [3] 수정: book 1개 내용 변경
    public static void setBook(Scanner sc) {
        System.out.println("\n책 정보 수정");
        System.out.println("수정할 책 번호를 입력해주세요.");
        long findNum = sc.nextInt();
        sc.nextLine();  // 버퍼 비우기

        try {
//            if () {
//                System.out.println("[에러] 해당 책은 존재하지 않습니다.\n");
//                return;
//            }

            System.out.println(bookList.get(findNum).toString());

            String num;
            String title;
            String price;
            String publishedDate;

            // 책 번호
            while (true) {
                System.out.println("책 번호 입력 (알파벳1자리 + 숫자4자리 형식): ");
                num = sc.nextLine();

                // 입력값 검증 - 책 번호 (no)
                if (num.isEmpty()) {
                    System.out.println("[에러] 번호는 반드시 입력해야 합니다.\n");
                    continue;
                }
                Matcher matcher = Pattern.compile("^([a-zA-Z]){1}([0-9]){4}$").matcher(num);
                if (!matcher.find()) {
                    System.out.println("[에러] 번호를 양식에 맞춰 작성해주세요.");
                    continue;
                }

                bookList.get(findNum).setNo(num.toUpperCase());
                break;
            }

            while (true) {
                System.out.println("책 제목 입력: ");
                title = sc.nextLine();

                // 입력값 검증 - 책 이름 (title)
                if (title.isEmpty()) {
                    System.out.println("[에러] 책 제목은 반드시 입력해야 합니다.\n");
                    continue;
                }

                bookList.get(findNum).setTitle(title);
                break;
            }

            while (true){
                System.out.print("가격 입력 (0 이상의 정수): ");
                price = sc.nextLine();

                // 입력값 검증 - 가격 (price)
                if (price.isEmpty()) {
                    System.out.println("[에러] 가격은 반드시 입력해야 합니다.\n");
                    continue;
                }
                else {
                    try {
                        if (Integer.parseInt(price) < 0) {
                            System.out.println("[에러] 잘못된 입력입니다.\n");
                            continue;
                        }
                        bookList.get(findNum).setPrice(Integer.parseInt(price));
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("[에러] 잘못된 입력입니다.\n");
                        continue;
                    }
                }
            }

            while (true) {
                System.out.println("출판일 입력 (yyyy-MM-dd): ");
                publishedDate = sc.nextLine();

                // 입력값 검증 - 출판일 (publishedDate)
                if (publishedDate.isEmpty()) {
                    System.out.println("[에러] 출판일은 반드시 입력해야 합니다.");
                    continue;
                }
                if (!(isDateMM(publishedDate) & isDateM(publishedDate))) {
                    System.out.println("[에러] 가격과 출판일을 양식에 맞게 입력하세요.\n");
                    continue;
                }
                bookList.get(findNum).setPublishedAt(formatDate(publishedDate));
                break;
            }

            System.out.println("수정되었습니다.\n");
        } catch (NumberFormatException e) {
            System.out.println("[에러] 가격과 개수는 숫자로 입력하세요.\n");
            System.out.println("다시 입력하세요.\n");
        }
    }


    // [4] 삭제: book 1개 삭제
    public static void deleteBook(Scanner sc) {
        System.out.println("\n책 삭제");
        System.out.print("삭제할 책 번호를 입력해주세요: ");
        String deleteNo = sc.nextLine();

        Long bookIdToDelete = null;
        for (Map.Entry<Long, Book> entry : bookList.entrySet()) {
            if (entry.getValue().getNo().equalsIgnoreCase(deleteNo)) {
                bookIdToDelete = entry.getKey();
                break;
            }
        }
        if (bookIdToDelete == null) {
            System.out.println("[에러] 해당 책은 존재하지 않습니다.\n");
            return;
        }

        bookList.remove(bookIdToDelete);

    }

    public static boolean isDateMM(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isDateM(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String formatPrice(int price) {
        DecimalFormat priceFormat = new DecimalFormat("###,###");
        return priceFormat.format(price);
    }

    public static LocalDate formatDate(String date) {
        String[] splitDate = date.split("-");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
        return localDate;
    }

} // end class
