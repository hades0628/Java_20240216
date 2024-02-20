package movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {
	
	private long id; //영화 고유번호
	private String title; //영화제목
	private String genre; //영화 장르

	
	private static final File file = new File("src/movie/movies.txt");
	
	
//	public Movie(long id, String title, String genre) {
//		this.id = id;
//		this.title = title;
//		this.genre = genre;
//	}
	
	public Movie(String title, String genre) {
		this(Instant.now().getEpochSecond(), title, genre);
		//long id = Instant.now().getEpochSecond();
	}
	
	
	public static ArrayList<Movie> findAll(){
		
		ArrayList<Movie> movies = new  ArrayList<Movie>();
		BufferedReader br = null;
		String line = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			while( (line = br.readLine()) != null) {
				String[] temp = line.split(",");
				/*
				 * temp[0] = 1627175707
				 * temp[1] = 어벤져스
				 * temp[2] = 판타지
				 * */
				Movie m = new Movie(Long.parseLong(temp[0]),temp[1],temp[2]);
				//파일에서 읽어온 데이터는 문자로 인식해서
				//숫자도 문자다로 변환 시켜주기 위해서
				//Long.parseLong으로 변환 작업해줌
				
				movies.add(m);  //Movie.ArrayList에 파일 저정된 영화 목록 추가
			}
			br.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return movies;
	}

	@Override
	public String toString() {
		return String.format("[%d]: %s(%s)", id, title, genre);
		//					  정수형   실수형
	}


	public void save() {
		try {
			FileWriter fw = new FileWriter(file, true);
								//moives.txt 파일에 이어서 쓰기 실행
			fw.write(this.toFileString() + "\n");
			fw.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
						
		
	}


	private String toFileString() {
		
		return String.format("%d,%s,%s", id, title, genre);
	}


	public static void delete(String movieID) {
		BufferedReader br = null;
		String text = "";
		String line = "";
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			while((line = br.readLine() )!= null) {
				String[] temp = line.split(",");
				if(movieID.equals(temp[0])) {
					continue;
				}
				text += line + "\n";
			}
			br.close();
			
			FileWriter fw = new FileWriter(file);
			fw.write(text);
			
			fw.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}


	public static Movie findAll(String movieID) {
		
		Movie movie = null;
		BufferedReader br = null;
						
		try{
			br = new BufferedReader(new FileReader(file));
			String line = null;
			
			
			while((line = br.readLine()) !=null) {
				String[] temp = line.split(",");
				if(movieID.equals(temp[0])) {
					movie = new Movie(
							Long.parseLong(temp[0]),
							temp[1],
							temp[2]);
					break;
				}
				
			}
			br.close();
			
			return movie;
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return movie;
	}
	
	
}
