package playermp3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Resource {

	static FileInputStream fis;
	static BufferedInputStream bis;
	
	public static Player player;
	public static long pauseLocattion;
	public static long songTotalLength;
	
	Thread thread;
	public static String fileLocattion; 
	
	private static PrintWriter writer; 
	
	private static FileReader reader;
	private static BufferedReader bReader;
	
	/**
	 * 
	 */
	public static void stop() {
		
		if(player != null) {
			player.close();
			pauseLocattion = 0;
			songTotalLength = 0;
		}
	}
	/**
	 * 
	 */
	public static void pause() {
		try {
			
			if(player != null) {
				pauseLocattion = fis.available();
				player.close();
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	
	/**
	 * 
	 */
	public static void resume() {
		try {
			
			
			fis = new FileInputStream(fileLocattion);
			bis = new BufferedInputStream(fis);
			
			player = new Player(bis);
			
			fis.skip(songTotalLength - pauseLocattion);
			
		} catch (FileNotFoundException | JavaLayerException ex ) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(){
			
			public void run(){
				try {
					
					player.play();
					
				} catch (JavaLayerException e) {
					
					System.out.println("Erro ao dar o play ");
					
				}
			}
			
		}.start();
		
	}
	
	/**
	 * 
	 * @param path Contém o caminho do diretório
	 */
	public static void play(String songPath) {
		try {
			
			fis = new FileInputStream(songPath);
			bis = new BufferedInputStream(fis);
			
			player = new Player(bis);
			
			songTotalLength = fis.available();
			fileLocattion = songPath + "";
			
		} catch (FileNotFoundException | JavaLayerException ex ) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(){
			
			public void run(){
				try {
					
					player.play();
					
				} catch (JavaLayerException e) {
					
					System.out.println("Erro ao dar o play ");
					
				}
			}
			
		}.start();
		
	}
	
	public static int deleteSong(String pathOfSongFile, String fileToDelete) {
		return 0;
	}
	
	public static ArrayList<String> readFile(String filePath) {
		
		try {
			
			ArrayList<String> content = new ArrayList<String>();
			reader = new FileReader(filePath);
			bReader = new  BufferedReader(reader);
			
			while(bReader.ready()) {
				
				content.add(bReader.readLine());
				
			}
			return content;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static String writeInFile(String filePath, String content) {
		try {
			
			FileWriter fileWriter = new FileWriter(filePath, true);
			writer = new PrintWriter(fileWriter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		writer.println(content);
		writer.close();
		return content;
	}
	
}
