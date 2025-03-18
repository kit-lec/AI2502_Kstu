package practice.bookmanager;


import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 필요한 클래스 추가 가능
// 이 패키지 외의 패키지에는 클래스 만들지 마세요.
public class BookManagerMain {
    // 공용으로 사용할 static 변수들
    static Scanner sc = new Scanner(System.in);
    private static String FILEPATH = "data/book.dat";

    // 원본은 정렬해도 그대로 유지되어야 하므로 2개를 만들었다.
    private static HashMap<String, Book> changingBookMap = new HashMap<>();
    private static HashMap<String, Book> forSaveBookMap = new HashMap<>();
    private static List<Book> bookValues = new ArrayList<>();

    public static void main(String[] args) {
        //디렉토리 및 file check
        checkDirOrFiles();

        //파일 읽어오기
        readFile();

        // 0 아닐 때까지 계속 입력받기
        while(true){
            bookValues = getBooks();
            showMenu();
            int num = sc.nextInt();
            sc.nextLine(); // num 입력받고 남은 "\n" 을 없애준다.

            switch(num){
                case 1:
                    addBook();
                    break;
                case 2:
                    showBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 21: // 번호 오름차순
                    Collections.sort(bookValues, (o1, o2) -> o1.getNo().compareTo(o2.getNo()));
//                    for (int i = 0; i < bookValues.size(); i++) {
//                        System.out.print(bookValues.get(i));
//                    }
                    printSortedList();
                    removeList();
                    break;
                case 22: // 번호 내림차순
                    Collections.sort(bookValues, (o1, o2) -> o2.getNo().compareTo(o1.getNo()));
                    printSortedList();
                    removeList();
                    break;
                case 23: // 제목 오름차순
                    Collections.sort(bookValues, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
                    printSortedList();
                    removeList();
                    break;
                case 24: // 제목 내림차순
                    Collections.sort(bookValues, (o1, o2) -> o2.getTitle().compareTo(o1.getTitle()));
                    printSortedList();
                    removeList();
                    break;
                case 25: // 가격 오름차순
                    Collections.sort(bookValues, (o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
                    printSortedList();
                    removeList();
                    break;
                case 26: // 가격 내림차순
                    Collections.sort(bookValues, (o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
                    printSortedList();
                    removeList();
                    break;
                case 27: // 출판일 오름차순
                    Collections.sort(bookValues, (o1, o2) -> o1.getPublishedAt().compareTo(o2.getPublishedAt()));
                    printSortedList();
                    removeList();
                    break;
                case 28: // 출판일 내림차순
                    Collections.sort(bookValues, (o1, o2) -> o2.getPublishedAt().compareTo(o1.getPublishedAt()));
                    printSortedList();
                    removeList();
                    break;
                case 0:
                    // 종료 전에 파일에 쓰고 저장
                    writeFile();

                    System.out.println("데이터를 파일에 저장합니다");
                    System.out.println();
                    System.out.println("프로그램을 종료합니다");
                    break;
                default:
                    System.out.println("재입력해주십시오");
                    System.out.println();
            }
            if(num == 0) break;
        }
    } // end main()

    //파일을 읽어오는 메소드
    public static void readFile(){
        try(
                InputStream in = new FileInputStream(FILEPATH);
                //객체를 읽어오는 스트림에 장착!
                ObjectInputStream oin = new ObjectInputStream(in);
                ) {
            forSaveBookMap = (HashMap<String, Book>) oin.readObject();
            System.out.println("파일을 모두 읽어왔습니다");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(EOFException e){
            System.out.println("파일을 모두 읽어왔습니다(eof)");
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //파일에 써서 저장하는 메소드
    public static void writeFile(){
        try(
                OutputStream out = new FileOutputStream(FILEPATH);
                ObjectOutputStream oout = new ObjectOutputStream(out);
        ) {
            oout.writeObject(forSaveBookMap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //디렉토리 or 파일 존재 확인 및 생성
    public static void checkDirOrFiles(){
        String path = System.getProperty("user.dir") + File.separator + "data";

        //data dir을 표현하는 파일 객체 통해 디렉토리 check
        File dataDir = new File(path);
        if(!dataDir.exists()){
            if(dataDir.mkdir()) System.out.println("data 폴더 생성 완료");
            else System.out.println("data 폴더 생성 실패");
        }
        else System.out.println("data 폴더가 이미 존재합니다");

        // book.dat 파일 생성. 이미 있으면 불러만 오기
        File bookFile = new File(dataDir, "book.dat");

        if(!bookFile.exists()){
            try {
                if(bookFile.createNewFile()) System.out.println("book.dat 파일 생성 성공");
                else System.out.println("book.dat 파일 생성 실패");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            System.out.println("book.dat 파일이 이미 존재합니다");
        }
    }

    //입력 메소드
    public static void addBook(){
        String parseNo;
        String parseTitle;
        Integer parsePrice;
        LocalDate parseDate;
        String regex = "^[A-Z][0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        Book book = new Book();
        System.out.println("책 정보를 입력합니다");

        // 책 번호 입력 루프
        while (true) {
            System.out.print("책 번호 입력 (알파벳1자리 + 숫자4자리 형식): ");
            String inputNo = sc.nextLine();
            matcher = pattern.matcher(inputNo.trim().toUpperCase());

            // 먼저 빈문자열인지 확인. 그 다음 아니면 정규식에 매치
            if (inputNo.trim().isEmpty()) System.out.println("번호는 반드시 입력해야 합니다");
            else if (matcher.matches()) {
                book.setNo(inputNo.trim().toUpperCase());
                break; //형식에 맞으면 넘어가서 다음 입력 받자
            } else System.out.println("책 번호 형식이 아닙니다!");
        }

        // 책 제목 입력 루프
        while (true) {
            System.out.print("책 제목 입력: ");
            String inputTitle = sc.nextLine();

            if (inputTitle.trim().isEmpty()) System.out.println("책 제목은 반드시 입력해야 합니다");
            else {
                book.setTitle(inputTitle.trim());
                break;
            }
        }

        // 가격 입력 루프
        while (true) {
            try {
                System.out.print("가격 입력(0이상 정수): ");
                String inputPrice = sc.nextLine();

                if (inputPrice.trim().isEmpty()) System.out.println("잘못된 입력입니다(빈 값 불가)");
                else {
                    parsePrice = Integer.parseInt(inputPrice);
                    if (parsePrice < 0) System.out.println("잘못된 입력입니다(음수 불가)");
                    else{
                        book.setPrice(parsePrice);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다(숫자 이외 값 불가)");
            }
        }

        // 출판일 입력 루프
        while(true){
            try{
                System.out.print("출판일입력(yyyy-MM-dd): ");
                String inputDate = sc.nextLine();

                parseDate = LocalDate.parse(inputDate.trim(), DateTimeFormatter.ofPattern("yyyy-M-d"));
                book.setPublishedAt(parseDate);
                break;
            } catch(DateTimeParseException e){
                System.out.println("잘못된 입력입니다");
            }
        }

        forSaveBookMap.put(book.getNo(), book);
        System.out.println("데이터가 입력되었습니다");
        System.out.println();
    }

    //메뉴 보여주는 메소드
    public static void showMenu(){
        System.out.println("도서 관리 프로그램 v1.0");
        System.out.println("-----------------------");
        System.out.println("[1] 입력");
        System.out.print("[2] 목록 ");
        System.out.print("[21] 번호_up ");
        System.out.print("[22] 번호_down ");
        System.out.print("[23] 제목_up ");
        System.out.print("[24] 제목_down ");
        System.out.print("[25] 가격_up ");
        System.out.print("[26] 가격_down ");
        System.out.print("[27] 출판일_up ");
        System.out.println("[28] 출판일_down ");
        System.out.println("[3] 수정");
        System.out.println("[4] 삭제");
        System.out.println("[0] 종료");
        System.out.println("-----------------------");
        System.out.print("선택: ");
    }

    //열람 메소드
    public static void showBooks(){
        System.out.println("모든 아이템 항목을 출력합니다.");
        System.out.printf("  Uid | Title               |      Price | Publication \n");

        Set<String> kSet = forSaveBookMap.keySet();
        for(String k : kSet){
            Book showBook = forSaveBookMap.get(k);
            System.out.print(showBook);
        }
    }

    //수정 메소드
    public static void updateBook(){
        String parseNo;
        String parseTitle;
        Integer parsePrice;
        LocalDate parseDate;
        String regex = "^[A-Z][0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        Book updateBook = new Book();

        System.out.println("책 정보 수정");

        //키로 수정할 객체 가져오는 것 말고는 추가메소드와 동일하게 작동~
        //번호 입력받아서 수정할 객체 가져오기
        while(true){
            System.out.print("수정할 책 번호를 입력하세요: ");
            String inputNo = sc.nextLine();
            matcher = pattern.matcher(inputNo.trim().toUpperCase());

            // 먼저 빈문자열인지 확인. 그 다음 아니면 정규식에 매치
            if (inputNo.trim().isEmpty()) System.out.println("번호는 반드시 입력해야 합니다");
            else if (matcher.matches()) {
                // 맞으면 키로 일치하는 Book 객체를 찾아오자
                updateBook = forSaveBookMap.get(inputNo.trim().toUpperCase());
                if(updateBook == null){
                    System.out.println("존재하지 않는 아이디입니다.");
                }
                else{
                    updateBook.setNo(inputNo.trim().toUpperCase());
                    break;
                }
            } else System.out.println("책 번호 형식이 아닙니다!");
        }

        // 책 제목 입력 루프
        while (true) {
            System.out.print("책 제목 입력: ");
            String inputTitle = sc.nextLine();

            if (inputTitle.trim().isEmpty()) System.out.println("책 제목은 반드시 입력해야 합니다");
            else {
                updateBook.setTitle(inputTitle.trim());
                break;
            }
        }

        // 가격 입력 루프
        while (true) {
            try {
                System.out.print("가격 입력(0이상 정수): ");
                String inputPrice = sc.nextLine();

                if (inputPrice.trim().isEmpty()) System.out.println("잘못된 입력입니다(빈 값 불가)");
                else {
                    parsePrice = Integer.parseInt(inputPrice);
                    if (parsePrice < 0) System.out.println("잘못된 입력입니다(음수 불가)");
                    else{
                        updateBook.setPrice(parsePrice);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다(숫자 이외 값 불가)");
            }
        }

        // 출판일 입력 루프
        while(true){
            try{
                System.out.print("출판일입력(yyyy-MM-dd): ");
                String inputDate = sc.nextLine();

                parseDate = LocalDate.parse(inputDate.trim(), DateTimeFormatter.ofPattern("yyyy-M-d"));
                updateBook.setPublishedAt(parseDate);
                break;
            } catch(DateTimeParseException e){
                System.out.println("잘못된 입력입니다");
            }
        }

        System.out.println("수정되었습니다");
    }

    //삭제 메소드
    public static void deleteBook(){
        String parseNo;
        String regex = "^[A-Z][0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        Book deleteBook = new Book();

        System.out.println("책 삭제");

        while(true){
            System.out.print("삭제할 책 번호를 입력하세요: ");
            String inputNo = sc.nextLine();
            matcher = pattern.matcher(inputNo.trim().toUpperCase());

            // 먼저 빈문자열인지 확인. 그 다음 아니면 정규식에 매치
            if (inputNo.trim().isEmpty()) System.out.println("번호는 반드시 입력해야 합니다");
            else if (matcher.matches()) {
                // 맞으면 키로 일치하는 Book 객체를 찾아오자
                deleteBook = forSaveBookMap.get(inputNo.trim().toUpperCase());
                if(deleteBook == null){
                    System.out.println("존재하지 않는 아이디입니다.");
                }
                else{
                    forSaveBookMap.remove(inputNo.trim().toUpperCase());
                    System.out.println("삭제하였습니다");
                    break;
                }
            } else System.out.println("책 번호 형식이 아닙니다!");
        }
    }

    public static List<Book> getBooks(){
        Set<String> keys = forSaveBookMap.keySet();

        Set<Map.Entry<String, Book>> books = forSaveBookMap.entrySet();

        for(Map.Entry<String, Book> book : books){
            bookValues.add(book.getValue());
        }

        return bookValues;
    }

    public static void printSortedList(){
        for (int i = 0; i < bookValues.size(); i++) {
            System.out.print(bookValues.get(i));
        }
    }

    public static void removeList(){
        for (int i = 0; i < bookValues.size(); i++) {
            bookValues.remove(i);
        }
    }
} // end class

//정렬 comparator
//TODO
