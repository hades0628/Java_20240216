package movie;



public class MainApp {

	public static void main(String[] args) {
		System.out.println("프로그램을 시작합니다!");

		Menu menu = MainMenu.getInstance();

		while (menu != null) {
			menu.print();
			//System.out.println("1: " + menu);
			menu = menu.next();
			System.out.println("2: " + menu);
		}
		System.out.println("프로그램을 종료합니다!");

	}

}
