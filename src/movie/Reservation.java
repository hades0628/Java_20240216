package movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
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

	public Reservation(long movieId, String movieTitle, String SeatName) {
		this(Instant.now().toEpochMilli(), movieId, movieTitle, SeatName);
	}
	public void save() throws IOException{
		FileWriter fw = new FileWriter(file,true);
		
		fw.write(this.toFileString() + "\n");
		fw.close();
	}

	private String toFileString() {
		
		return String.format("%d,%d %s,%s", id, movieId, movieTitle, SeatName);
	}
	public static Reservation findById(String reservationsId) {

		Reservation reservation = null;
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(",");
				if (reservationsId.equals(temp[0])) {
					reservation = new Reservation(Long.parseLong(temp[0]), Long.parseLong(temp[1]), temp[2], temp[3]);
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

	public static Reservation cancel(String reservationID) {
		Reservation canceled = null;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String text = "";
			String line = null;

			while ((line = br.readLine()) != null) {
				String[] temp = line.split(",");

				if (reservationID.equals(temp[0])) {
					canceled = new Reservation(Long.parseLong(temp[0]), Long.parseLong(temp[1]), temp[2], temp[3]);
					continue;
				}

				text += line + "\n";
			}
			br.close();

			FileWriter fw = new FileWriter(file);
			fw.write(text);
			fw.close();
			return canceled;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format("영화 : %s, 좌석: %s", movieTitle, SeatName);
	}

	// (movieId)영화 예매 현황
	public static ArrayList<Reservation> findMovieId(String movieId) throws Exception {

		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = null;
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(",");
			if (movieId.equals(temp[1])) {// 영화 id
				long id = Long.parseLong(temp[0]);// 예매 id
				long mId = Long.parseLong(temp[1]);// 영화 id
				String movieTitle = temp[2];
				String seatName = temp[3];

				Reservation r = new Reservation(id, mId, movieTitle, seatName);

				reservations.add(r);
			}
		}
		br.close();
		return reservations;
	}

}
