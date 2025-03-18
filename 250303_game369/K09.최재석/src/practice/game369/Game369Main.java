package practice.game369;

public class Game369Main {

	public static void main(String[] args) {
		System.out.println("369 게임");
		int i = 1;
		while(i <= 100) {
			boolean star = false;

			int num = i;
			while (num > 0) {
				int digit = num % 10;
				if (digit == 3 || digit == 6 || digit == 9){
					System.out.print("* ");
					star = true;
					break;
				}
				num /= 10;
			}
			if(!star){
				System.out.print(i + "");
			}

			if(i % 10 == 0){
				System.out.println();
			}
			i++;
		}


	} // end main()

} // end class Game369Main








