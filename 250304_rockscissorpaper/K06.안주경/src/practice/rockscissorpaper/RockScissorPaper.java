package practice.rockscissorpaper;

import java.util.Scanner;

public class RockScissorPaper {

	// 멤버변수가 필요하면 작성하세요.
	
    public static void main(String[] args) {
        System.out.println("가위 바위 보 게임");
        Scanner sc = new Scanner(System.in);
        
        while(true){
            showMenu(); // 메뉴보여주기
 
            int userChoice = inputChoice(sc); // 사용자 입력
            if(userChoice == 0){
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
		System.out.println("""
				가위 바위 보 게임
				-----------------
				[1] 가위
				[2] 바위
				[3] 보
				[0] 종료
				----------------
				선택: 
				""");
	} // end showMenu()
	
	// method name: inputChoice
	// return: int (유저의 가위(1)/바위(2)/보(3) 선택 내용)
	// arguments: Scanner sc (입력장치)
	public static int inputChoice(Scanner sc) {
		int choice = 0;
		while(true) {
			choice = sc.nextInt();
			if (0 <= choice && choice <= 3) {
				return choice;
			}
			System.out.println("다시 입력하세요: ");
		}
	} // end inputChoice()





	// method name: displayChoice
	// return: void
	// arguments:
	//   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
	//   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
	public static void displayChoice(int user, int com) {
		System.out.println("사용자 vs. 컴퓨터");
		if(user==1) {
			System.out.print("가위 vs. ");
		}else if(user ==2) {
			System.out.print("바위 vs. ");
		}else {
			System.out.print("보 vs. ");
		}
		if(com==1) {
			System.out.print("가위");
		}else if(com ==2) {
			System.out.print("바위");
		}else {
			System.out.print("보");
		}


	
	} // end displayChoice()
	
	
	// method name: computeResult
	// return: void
	// arguments:
	//   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
	//   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
	public static void computeResult(int user, int com) {
		if((user==1&&com==1)||(user==2&&com==2)||(user==3&&com==3)) {
			System.out.println("\n비겼습니다.");
		}else if((user==1&&com==2)||(user==2&&com==3)||(user==3&&com==1)) {
			System.out.println("\nCom win!");
		}else {
			System.out.println("\nUser win!");
		}
	} // end computeResult()

} // end class

