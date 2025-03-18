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
                ---------------
                [1] 가위
                [2] 바위
                [3] 보
                [0] 종료
                ---------------
                선택:""");
		
		// TODO
	
	} // end showMenu()
	
	// method name: inputChoice
	// return: int (유저의 가위(1)/바위(2)/보(3) 선택 내용)
	// arguments: Scanner sc (입력장치)
	public static int inputChoice(Scanner sc) {
		int choice;
		
		
		while(true){
            choice = sc.nextInt();
            if(choice >= 0 && choice <= 3) return choice; // 0 ~ 3 내의 범위면 해당값을 main 의 UserChoice(호출한 곳) 로 값 리턴

            System.out.println("다시 입력하세요~"); //다시 입력 메시지 후 다시 입력창으로 돌아감
        }
	} // end inputChoice()

	
	// method name: displayChoice
	// return: void
	// arguments:
	//   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
	//   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
	public static void displayChoice(int user, int com) {
        int[] results = {user, com}; // 정수형 결과를 우선 int 배열에 담기
        String[] resultsToString = new String[2]; // int 를 해당되는 가위, 바위, 보 중 하나로 변환하기 위한 String 형 배열
        for (int i = 0; i < results.length; i++) {
            switch (results[i]) { // user 와 com 에 대한 switch 반복 작성 귀찮아서 이렇게 했음...
                case 1 -> resultsToString[i] = "가위 ";
                case 2 -> resultsToString[i] = "바위 ";
                case 3 -> resultsToString[i] = "보 ";
            }
        }
        System.out.println("사용자 vs. 컴퓨터");
        System.out.println(resultsToString[0] + "vs. " + resultsToString[1]); //최종적으로 결과 보여주는 출력문
	} // end displayChoice()
	
	
	// method name: computeResult
	// return: void
	// arguments:
	//   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
	//   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
	public static void computeResult(int user, int com) {
		// 1. 같은 수가 나오면 비긴다 (1, 1), (2, 2), (3, 3)
        // 2. 사용자가 이기는 경우: (1, 3), (2, 1), (3, 2)
        // 3. 컴퓨터가 이기는 경우: 나머지 3가지
        // 따라서 3가지 조건문으로 해결
        if (user == com)
            System.out.println("비겼습니다.");
        else if (user == 1 && com == 3 || user == 2 && com == 1 || user == 3 && com == 2) {
            System.out.println("User Win!");
        }
        else {
            System.out.println("Com Win!");
        }
		
		
	} // end computeResult()

} // end class

