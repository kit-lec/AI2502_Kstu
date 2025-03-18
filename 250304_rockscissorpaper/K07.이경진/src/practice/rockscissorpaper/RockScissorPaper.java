package practice.rockscissorpaper;

import java.util.Scanner;

/*
	 간단한 가위, 바위, 보 게임을 만듭니다.
	(실행화면은 다음 페이지에)
	
	showMenu / inputChoice / displayChoice
	/ computeResult  메소드들을 어떻게 
	구현해볼수 있을까요? 
	
	
	main() 메소드는 오른쪽과 같이 구성하고
	변경하지 않는 상태에서
	나머지 메소드들을 구현하여 완성해보세요
	
	필요하다면 클래스의 멤버변수등을 추가해도
	좋습니다
 */
public class RockScissorPaper {

	// 멤버변수가 필요하면 작성하세요.
	
    public static void main(String[] args) {
        System.out.println("가위 바위 보 게임");
        Scanner sc = new Scanner(System.in);



        while (true) {
        showMenu(); // 메뉴 보여주기

        int userChoice = inputChoice(sc); // 사용자 입력
        if (userChoice == 0) {
            break;  // 0 이면 종료
        }

        int computerChoice = (int) (Math.random() * 3) + 1;  // 컴퓨터 선택
        displayChoice(userChoice, computerChoice); // 양측 선택 보여주기
        computeResult(userChoice, computerChoice); // 승부 결과 보여주기
    }
        sc.close();
}


    public static void showMenu() {
        System.out.println("-------------");
        System.out.println("[1] 가위");
        System.out.println("[2] 바위");
        System.out.println("[3] 보");
        System.out.println("[0] 종료");
        System.out.println("-------------");
        System.out.print("선택: ");
    }


        public static int inputChoice(Scanner sc) {
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice >= 0 && choice <= 3) {
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력하세요.");
                }
            }
            return choice;
        }

        public static void displayChoice(int user, int com) {
            String[] choices = {"", "가위", "바위", "보"};
            System.out.println("사용자 vs. 컴퓨터");
            System.out.println(choices[user] + " vs. " + choices[com]);
        }

        public static void computeResult(int user, int com) {
            if (user == com) {
                System.out.println("비겼습니다.");
            } else if ((user == 1 && com == 3) || (user == 2 && com == 1) || (user == 3 && com == 2)) {
                System.out.println("User Win!");
            } else {
                System.out.println("Com Win!");
            }
        }
}
