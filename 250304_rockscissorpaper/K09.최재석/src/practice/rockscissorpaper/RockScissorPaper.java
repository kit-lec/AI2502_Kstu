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
                break;  // 0 이면(입력시) 종료
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
                ================
                [1] 가위
                [2] 바위
                [3] 보
                [0] 종료
                ================
                선택:""");
	} // end showMenu()
	
	// method name: inputChoice
	// return: int (유저의 가위(1)/바위(2)/보(3) 선택 내용)
	// arguments: Scanner sc (입력장치)
	public static int inputChoice(Scanner sc) {
		int choice = 0;

        while(true){
            choice = sc.nextInt();
            if(0 <= choice && choice <= 3){  // 0부터 3까지 선택범위
                return choice;
            } else {
                System.out.println("다시 입력하세요~");
            }
        }

	} // end inputChoice()

	
	// method name: displayChoice
	// return: void
	// arguments:
	//   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
	//   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
	public static void displayChoice(int user, int com) {
        String[] choices = {"", "가위", "바위", "보"};  // 인덱스 1부터 시작하도록 "" 추가
        System.out.println("유저의 선택: " + choices[user] + ", 컴퓨터의 선택: " + choices[com]);

	} // end displayChoice()
	
	
	// method name: computeResult
	// return: void
	// arguments:
	//   1) int user: 유저의 선택(가위(1)/바위(2)/보(3))
	//   2) int com: 컴퓨터의 선택(가위(1)/바위(2)/보(3))
	public static void computeResult(int user, int com) {
        if(user == com){
            System.out.println("비겼습니다.");  // ↓ ||(or) 연산자, &&(and) 연산자
        } else if((user == 1 && com == 3) ||  // 가위-보
                  (user == 2 && com == 1) ||  // 바위-가위
                  (user == 3 && com == 2)){   // 보-바위
            System.out.println("User Win!");  // ↑ user가 이기는 경우만 모아놓음
        } else {
            System.out.println("Com Win!");
        }


    } // end computeResult()

} // end class

