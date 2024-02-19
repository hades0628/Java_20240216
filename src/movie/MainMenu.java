package movie;

public class MainMenu extends AbstractMenu {

	private static final MainMenu instance = new MainMenu(null);
	private static final String MAIN_MENU_TEXT = "1: 영화 예매하기\n" + "2: 예매 학인하기\n" + "3: 예매 최소하기\n" + "4: 관리자 메뉴로 이동하기\n"
			+ "q: 종료\n\n" + "메뉴를 선택하세요: ";

	private MainMenu(Menu prevMenu) {
		super(MAIN_MENU_TEXT, prevMenu); // 부모생성자 호출
	}

	public static MainMenu getInstance() {
		System.out.println("instance : " + instance);
		return instance; // MainMenu 객체 생성 주소반환(싱클턴생성)
	}

	@Override
	public Menu next() {
		switch (sc.nextLine()) {
		case "2":
			checkReservation(); // 예매 확인
			return this;
		case "4":
			if (!checkAdminPassword()) {
				System.out.println(">> 비밀번호가 틀렸습니다.");
				return this;
			}
			AdminMenu adminMenu = AdminMenu.getInstance();
			adminMenu.setPrevMenu(this);
			return adminMenu;
		case "q":
			return prevMenu; // q입력하면, prevMenu를 반환
		default:
			return this; // 그 외 입력하면 MainMenu로 돌어감

		}
	}

	private void checkReservation() {
		System.out.println("예매 번호를 입력하세요");
		try {
			Reservation reservation = Reservation.findById(sc.nextLine());
			if(reservation == null) {
				System.out.println(">> 예매 내역이 없습니다.");
			}else {
				System.out.println(">> [확인 완료]\n" + reservation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkAdminPassword() {
		System.out.println("관리자 비밀번호를 입력하세요 : ");

		String admin = sc.nextLine();

		return "admin".equals(admin);
	}

}
