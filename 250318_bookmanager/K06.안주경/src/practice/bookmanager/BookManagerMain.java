package practice.bookmanager;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

// 필요한 클래스 추가 가능
// 이 패키지 외의 패키지에는 클래스 만들지 마세요.
public class BookManagerMain {
	private static final String FILE_PATH = "books.dat";
	private static List<Book> bookList = new ArrayList<>();
	private static List<Book> sortedList = null;


	public static void main(String[] args) {

		loadBooks();
		Comparator<Book> comparator = null;
		Scanner sc = new Scanner(System.in);
		while (true) {
			comparator = null;
			System.out.print("""
					도서관리 v1.0
					------------------
					[1] 입력
					[2] 목록 [21]번호↓ [22]번호↑ [23]제목↓ [24]제목↑ [25]가격↓ [26]가격↑ [27]출판일↓ [28]출판일↑
					[3] 수정
					[4] 삭제
					[0] 종료
					------------------
					선택:
					""");

			try { //숫자 이외 값 입력 시 예외처리
				String input = sc.nextLine().trim();
				int choice = Integer.parseInt(input);
				switch (choice) {
					case 1:
						addBook(sc);
						break;
					case 2:
						displayBooks(bookList);
						break;
					case 3:
						updateBook(sc);
						break;
					case 4:
						deleteBook(sc);
						break;
					case 21:
//						comparator = new Comparator<Book>() {
//							@Override
//							public int compare(Book o1, Book o2) {
//								// 알파벳 기준 정렬, 동일하면 숫자로 정렬
//								int letterCompare = o1.getNo().substring(0, 1).compareTo(o2.getNo().substring(0, 1)); //0번째 알파벳 비교
//								if (letterCompare != 0) return letterCompare; //알파벳 다르면 알파벳순 정리
//								int num1 = Integer.parseInt(o1.getNo().substring(1)); //알파벳 같으면 숫자로 정리(index 1부터 끝까지)
//								int num2 = Integer.parseInt(o2.getNo().substring(1));
//								return Integer.compare(num1, num2);
//							}
//						};
						comparator = (o1, o2) -> o1.getNo().compareTo(o2.getNo());
						break;
					case 22:
//						comparator = new Comparator<Book>() {
//							@Override
//							public int compare(Book o1, Book o2) {
//								int letterCompare = o2.getNo().substring(0, 1).compareTo(o1.getNo().substring(0, 1));
//								if (letterCompare != 0) return letterCompare;
//								int num1 = Integer.parseInt(o1.getNo().substring(1));
//								int num2 = Integer.parseInt(o2.getNo().substring(1));
//								return Integer.compare(num2, num1);
//							}
//						};
						comparator = (o1, o2) -> -o1.getNo().compareTo(o2.getNo());
						break;
					case 23: //제목오름차순
						comparator = new Comparator<Book>() {
							@Override
							public int compare(Book o1, Book o2) {
								return o1.getTitle().compareToIgnoreCase(o2.getTitle());
							}
						};
						break;
					case 24: //제목내림차순
						comparator = new Comparator<Book>() {
							@Override
							public int compare(Book o1, Book o2) {
								return o2.getTitle().compareToIgnoreCase(o1.getTitle());
							}
						};
						break;
					case 25: //가격오름차순
						comparator = new Comparator<Book>() {
							@Override
							public int compare(Book o1, Book o2) {
								return Integer.compare(o1.getPrice(), o2.getPrice());
							}
						};
						break;
					case 26:
						comparator = new Comparator<Book>() {
							@Override
							public int compare(Book o1, Book o2) {
								return Integer.compare(o2.getPrice(), o1.getPrice());
							}
						};
						break;
					case 27:
						comparator = new Comparator<Book>() {
							@Override
							public int compare(Book o1, Book o2) {
								return o1.getPublishedAt().compareTo(o2.getPublishedAt());
							}
						};
						break;

					case 28:
						comparator = new Comparator<Book>() {
							@Override
							public int compare(Book o1, Book o2) {
								return o2.getPublishedAt().compareTo(o1.getPublishedAt());
							}
						};
						break;
					case 0: {
						saveBooks();
						System.out.println("프로그램 종료");
						return;
					}
					default :
						System.out.println("잘못된 입력입니다. 다시 선택하세요");
				}//switch
				if (comparator != null) {
					sortedList = new ArrayList<>(bookList);
					sortedList.sort(comparator);
					System.out.println("정렬된 목록:");
					displayBooks(sortedList);
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자 입력하세요");
			}//try-catch
		}//while(true)무한루프




	} // end main()

	private static void loadBooks() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			bookList = (List<Book>) ois.readObject(); //데이터 읽어오기
			System.out.println("파일에서 데이터를 불러왔습니다.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("불러올 데이터가 없습니다.");
		}
	}//loadBooks

	private static void addBook(Scanner sc) {
		try {
			System.out.println("책 정보를 입력합니다");
			System.out.print("책 번호 입력 (알파벳1자리 + 숫자4자리 형식): ");
			String no = sc.nextLine().toUpperCase();//무조건 대문자로~
			if(no.trim().isEmpty()) {
				System.out.println("번호는 반드시 입력해야 합니다");
			}
			if (!no.matches("[A-Z][0-9]{4}")) {
				System.out.println("도서번호 형식이 올바르지 않습니다. (예: A1234)");
			}
			System.out.print("책 제목 입력: ");
			String title = sc.nextLine();
			if (title.trim().isEmpty()) {
				System.out.println("책 제목은 반드시 입력해야 합니다.");
			}
			System.out.print("가격 입력(0 이상 정수: ");
			int price;
			try {
				System.out.print("가격 입력(0 이상 정수): ");
				price = sc.nextInt();
				if (price < 0) {
					throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				return;
			}
			sc.nextLine(); // 버퍼 비우기
			System.out.print("출판일(YYYY-MM-DD): ");
			LocalDate publishedAt;
			try {
				String dateInput = sc.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
				publishedAt = LocalDate.parse(dateInput, formatter);
			} catch (DateTimeParseException e) {
				System.out.println("유효한 날짜를 입력하세요.");
				return;
			}

			bookList.add(new Book(no, title, price, publishedAt));
			System.out.println("데이터가 입력되었습니다");
		} catch (Exception e) {
			System.out.println("입력오류: " + e.getMessage());
		}
	}

	private static void displayBooks(List<Book> books) {
		System.out.println("   Uid| Title                |     Price| Publication");
		if (books.isEmpty()) {
			System.out.println("등록된 도서가 없습니다.");
			return;
		}
		books.forEach(System.out::println);
	}

	private static void updateBook(Scanner sc) {
		System.out.println("책 정보 수정");
		System.out.print("수정할 책 번호를 입력하세요: ");
		String no = sc.nextLine().toUpperCase(); //대소문자 구분 X
		for (Book book : bookList) {
			if (book.getNo().equals(no)) { //해당하는(수정할) 책 수정
				try {
					System.out.print("책 제목 입력: ");
					book.setTitle(sc.nextLine());
					System.out.print("가격 입력(0 이상 정수): ");
					book.setPrice(sc.nextInt());
					sc.nextLine();//버퍼 비우기
					System.out.println("출판일입력(yyyy-MM-dd):");
					book.setPublishedAt(LocalDate.parse(sc.nextLine()));
					System.out.println("수정되었습니다");
				} catch (Exception e) {
					System.out.println("형식에 맞게 다시 입력: ");
				}
				return;
			}

		}//for문
	}//updateBook()

	private static void deleteBook(Scanner sc) {
		System.out.println("책 삭제");

		try {
			System.out.print("삭제할 책 번호 입력: ");
			String no = sc.nextLine().toUpperCase();
			Iterator<Book> it = bookList.iterator();
			while (it.hasNext()) {
				Book book = it.next();
				if (book.getNo().equals(no)) {
					it.remove();
					System.out.println("삭제완료");
					break;
				}//if
			}//while
		} catch (Exception e) {
			System.out.println("알맞은 형식 입력: " + e.getMessage());

		}

		System.out.println("삭제하였습니다");
	}//deleteBook


	private static void saveBooks() {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
			oos.writeObject(bookList);
		} catch (FileNotFoundException e) {
			System.out.println("파일 없음");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}//saveBooks()

} // end class
























