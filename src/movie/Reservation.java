package movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Reservation {
	private long id; // 예매 번호
	private long movieId; // 영화 구별 번호
	private String movieTitle;// 영화 제목
	private String SeatName;// 좌석

	private static final File file = new File("src/movie/reservations.txt");

//	public Reservation(long id, long movieId, String movieTitle, String SeatName) {
//		this.id = id;
//		this.movieId = movieId;
//		this.movieTitle = movieTitle;
//		this.SeatName = SeatName;
//	} = @AllArgsConstructor

	public static Reservation findById(String reservationsId) {

		Reservation reservation = null;
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(",");
				if (reservationsId.equals(temp[0])) {
					reservation = new Reservation(
							Long.parseLong(temp[0]),
							Long.parseLong(temp[1]),
							temp[2],
							temp[3]);
					break;
				}
			}
			br.close();
			return reservation;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public String toString() {
		return String.format("영화 : %s, 좌석: %s", movieTitle, SeatName);
	}

}
