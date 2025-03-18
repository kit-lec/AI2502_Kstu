package practice.rockscissorpaper;

import java.util.Scanner;

// import static RockScissorPaper.inputChoice;

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
            showMenu(); // 메뉴보여주기

            int userChoice = inputChoice(sc); // 사용자 입력
            if (userChoice == 0) {
                System.out.println("[종료]");
                break;  // 0 이면 종료
            }

            // 컴퓨터 선택:   1 - 가위,  2- 바위,  3 - 보
            int computerChoice = (int) Math.floor(Math.random() * 3) + 1;
            displayChoice(userChoice, computerChoice); // 양측의 선택 보여주기
            computeResult(userChoice, computerChoice); // 승부결과 보여주기
        }
        sc.close();
    } // end main()

    // method name: showMenu
    // return: void
    // arguments: none
    // 기능: 유저가 가위/바위/보 선택할 수 있는 메뉴 출력
    public static void showMenu() {
        System.out.print("""
                --------------------
                <가위바위보!>
                --------------------
                [1] 가위
                [2] 바위
                [3] 보
                --------------------
                [0] 종료
                --------------------
                [선택]\t""");

    } // end showMenu()

    // method name: inputChoice
    // return: int (유저의 가위(1)/바위(2)/보(3) 선택 내용)
    // arguments: Scanner sc (입력장치)
    public static int inputChoice(Scanner sc) {
        int choice = 0;
        while (true) {
            choice = sc.nextInt();
            if (0 <= choice && choice <= 3) {
                return choice;
            }
            System.out.println("[오류] 다시 입력해 주세요.");
        }
    } // end inputChoice()


    // method name: displayChoice
    // return: void
    // arguments:
    //   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
    //   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
    public static void displayChoice(int user, int com) {

        String user_pick, com_pick;

        switch (com) {
            case 1 -> com_pick = "가위";
            case 2 -> com_pick = "바위";
            case 3 -> com_pick = "보";
            default -> com_pick = "[오류]";
        }

        switch (user) {
            case 1 -> user_pick = "가위";
            case 2 -> user_pick = "바위";
            case 3 -> user_pick = "보";
            default -> user_pick = "[오류]";
        }
        System.out.println("====================");
        System.out.println("[결과]");
        System.out.println("사용자 vs. 컴퓨터");
        System.out.println(user_pick + " vs. " + com_pick);


    } // end displayChoice()


    // method name: computeResult
    // return: void
    // arguments:
    //   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
    //   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
    public static void computeResult(int user, int com) {
        String result;
        if (user != com) {
            boolean user_win;
            if ((user - com == 1) || (com - user == 2)) {
                user_win = true;
                result = "WIN";
                System.out.println("YOU WIN!");
            } else if ((user - com == 2) || (com - user == 1)) {
                user_win = false;
                result = "LOSE";
                System.out.println("YOU LOSE!");
            }
        } else {
            result = "DRAW";
            System.out.println("DRAW!");
        }
            // System.out.println("YOU " + result + "!");
        System.out.println("====================");
        } // end computeResult()
}   // end class

