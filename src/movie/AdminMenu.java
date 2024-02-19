package movie;

import java.util.ArrayList;

public class AdminMenu extends AbstractMenu {

	private static final AdminMenu instance = new AdminMenu(null);
	private static final String MAIN_MENU_TEXT = "1: 영화 등록 하기\n" + "2: 영화 목록 보기\n" + "3: 영화 삭제하기\n" + "b: 메인메뉴 이동하기\n\n"
			+ "메뉴를 선택하세요: ";

	private AdminMenu(Menu prevMenu) {
		super(MAIN_MENU_TEXT, prevMenu);
	}

	public static AdminMenu getInstance() {

		return instance; // MainMenu 객체 생성 주소반환(싱클턴생성)
	}

	@Override
	public Menu next() {
		switch (sc.nextLine()) {
		case "1":
			createMovie(); // 영화 등록하기
			return this;
		case "2":
			printAllMovies(); // 영화 목록 출력
			return this; // adminNenu(관지라) 반환
		case "3":
			deleteMovie(); // 영화 삭제
			return this;
		case "b":
			return prevMenu;
		default:
			return this; // 자기자신(AdminMenu) ==> new AdminMenu(null)
		}
	}

	private void createMovie() {

		System.out.println("제목: ");
		String title = sc.nextLine();

		System.out.println("장르: ");
		String genre = sc.nextLine();

		Movie movie = new Movie(title, genre);

		movie.save();
		System.out.println(">> 저장되었습니다.");

	}

	private void printAllMovies() {

		ArrayList<Movie> movies = Movie.findAll();

		for (Movie movie : movies) {
			System.out.println(movie);
		}
	}

	private void deleteMovie() {
		printAllMovies();
		System.out.println("삭제할 영화의 id값을 선택해주세요");

		Movie.delete(sc.nextLine());
		System.out.println(">> 삭제 되었습니다.");

	}

}
