package playermp3gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import javazoom.jl.player.Player;
import playermp3.Music;
import playermp3.Resource;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;

public class PlayerGUI {

	private JFrame frmPlayermp;
	private File songFile;
	
	private DefaultListModel<Music> songs = new DefaultListModel<Music>();
	private DefaultListModel<String> names = new DefaultListModel<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					PlayerGUI window = new PlayerGUI();
					window.frmPlayermp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayerGUI() {
	
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlayermp = new JFrame();
		frmPlayermp.setTitle("PlayerMP3");
		frmPlayermp.setBounds(100, 100, 772, 446);
		frmPlayermp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlayermp.setLocationRelativeTo(null);
		frmPlayermp.getContentPane().setLayout(null);
		
		JLabel lblPlaylists = new JLabel("Playlists");
		lblPlaylists.setBounds(658, 128, 46, 14);
		frmPlayermp.getContentPane().add(lblPlaylists);
		
		JLabel lblNomeDaPlaylist = new JLabel("Nome da Playlist");
		lblNomeDaPlaylist.setBounds(425, 17, 89, 14);
		frmPlayermp.getContentPane().add(lblNomeDaPlaylist);
		
		JLabel lblMsicas = new JLabel("M\u00FAsicas");
		lblMsicas.setBounds(224, 17, 46, 14);
		frmPlayermp.getContentPane().add(lblMsicas);
		
		JButton btnAddPlaylist = new JButton("add playlist");
		btnAddPlaylist.setBounds(623, 351, 123, 23);
		frmPlayermp.getContentPane().add(btnAddPlaylist);
		
		JList<String> listMusicLibrary = new JList<String>();
		listMusicLibrary.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMusicLibrary.setBounds(167, 44, 159, 296);
		frmPlayermp.getContentPane().add(listMusicLibrary);
		startMusicList();
		listMusicLibrary.setModel(names);
		listMusicLibrary.repaint();
		
		
		JList listPlayistsNames = new JList();
		listPlayistsNames.setBounds(388, 44, 159, 296);
		frmPlayermp.getContentPane().add(listPlayistsNames);
		
		JList listPlaylists = new JList();
		listPlaylists.setBounds(613, 153, 133, 183);
		frmPlayermp.getContentPane().add(listPlaylists);
		
		JButton btnAddSong = new JButton("Add M\u00FAsica");
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Caminho da musica
				if (open() != -1) {
					
					listMusicLibrary.setModel(names);
					listMusicLibrary.repaint();
				}
			}
		});
		btnAddSong.setBounds(10, 61, 105, 23);
		frmPlayermp.getContentPane().add(btnAddSong);
		
		JButton btnPlay = new JButton("PLay");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					
					if(listMusicLibrary.getSelectedValue()==null) {
						
						Resource.play(songs.getElementAt(0).getMusicPath());
						listMusicLibrary.setSelectedIndex(0);
						
					}else {
						
						Resource.stop();
						Resource.play(songs.getElementAt(listMusicLibrary.getSelectedIndex()).getMusicPath());
						
					}
					
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null,"A lista de músicas está vazia", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnPlay.setBounds(322, 351, 69, 23);
		frmPlayermp.getContentPane().add(btnPlay);
		
		
		JButton btnPause = new JButton("Pause");
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Resource.pause();
			}
		});
		btnPause.setBounds(398, 351, 69, 23);
		frmPlayermp.getContentPane().add(btnPause);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Resource.stop();
			}
		});
		btnStop.setBounds(243, 351, 69, 23);
		frmPlayermp.getContentPane().add(btnStop);
		
		JButton btnNext = new JButton("Next");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				try {
					
					if(listMusicLibrary.getSelectedValue()==null) {
						
						Resource.play(songs.getElementAt(0).getMusicPath());
						listMusicLibrary.setSelectedIndex(0);
						
					}else {
						
						Resource.stop();
						int next = (listMusicLibrary.getSelectedIndex()+1) % listMusicLibrary.getModel().getSize();
						
						Resource.play(songs.getElementAt(next).getMusicPath());
						listMusicLibrary.setSelectedIndex(next);
						
					}
					
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null,"A lista de músicas está vazia", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnNext.setBounds(475, 351, 69, 23);
		frmPlayermp.getContentPane().add(btnNext);
		
		JButton btnPrev = new JButton("Prev");
		btnPrev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				try {
					
					if(listMusicLibrary.getSelectedValue()==null) {
						
						Resource.play(songs.getElementAt(songs.size()-1).getMusicPath());
						listMusicLibrary.setSelectedIndex(0);
						
					}else {
						
						Resource.stop();
						int prev = (Math.abs(listMusicLibrary.getSelectedIndex()-1)) % listMusicLibrary.getModel().getSize();
						
						Resource.play(songs.getElementAt(prev).getMusicPath());
						listMusicLibrary.setSelectedIndex(prev);
						
					}
					
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null,"A lista de músicas está vazia", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnPrev.setBounds(164, 351, 69, 23);
		frmPlayermp.getContentPane().add(btnPrev);
		
		/**
		 * Botão remover músicas
		 */
		JButton btnRemMsica = new JButton("Rem M\u00FAsica");
		btnRemMsica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
				if( listMusicLibrary.getSelectedValue() == null) {
					
					throw new NoSuchFieldException() ;
				}
				int i = Resource.deleteSong("txtFiles//songs.txt", listMusicLibrary.getSelectedValue());
				Resource.deleteSong("txtFiles//songsPaths.txt", songs.getElementAt(listMusicLibrary.getSelectedIndex()).getMusicPath());
				
				if(i!= -1) {
					names.removeElementAt(i);
					songs.removeElementAt(i);
					listMusicLibrary.repaint();
				}
				}catch(Exception e) {
					
					JOptionPane.showMessageDialog(null,"Selecione um arquivo para poder deletá-lo", "Arquivo não selecionado", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		btnRemMsica.setBounds(10, 94, 105, 23);
		frmPlayermp.getContentPane().add(btnRemMsica);
		
		JButton btnAddDirectory = new JButton("Add Diret\u00F3rio");
		btnAddDirectory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("MP3 files", "mp3"));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
	            chooser.showOpenDialog(null);
	           
	            File file = new File(chooser.getSelectedFile().getAbsolutePath());
	            
	            File aFile[] = file.listFiles();
	            
	            for (int i = 0; i < aFile.length;  i++) {
	            	
	            	songFile = aFile[i];	
	    			
					
					if(!names.contains(songFile.getName())) {
					
						songs.addElement(new Music(songFile.getAbsolutePath(),songFile.getName()));
						names.addElement(songFile.getName());
						refreshMusicList();	
					
					}
	                
	            }
			}
		});
		btnAddDirectory.setBounds(10, 124, 105, 23);
		frmPlayermp.getContentPane().add(btnAddDirectory);
		
			
		
	}
	
	private int open() {
		try {
				
			JFileChooser chooser = new JFileChooser("D:\\Músicas");
			chooser.setDialogTitle("Escolha de arquivo");
			chooser.setFileFilter(new FileNameExtensionFilter("Music files", "mp3"));
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setMultiSelectionEnabled(true);
			chooser.showOpenDialog(null);
			
			File selectedFiles[] = chooser.getSelectedFiles();
			
			for (int i = 0; i < selectedFiles.length; i++) {
				
				songFile = selectedFiles[i];	
			
				
				if(!names.contains(songFile.getName())) {
				
					songs.addElement(new Music(songFile.getAbsolutePath(),songFile.getName()));
					names.addElement(songFile.getName());
					refreshMusicList();	
				
				}
				
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,"Nenhum arquivo selecionado", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		return JOptionPane.CLOSED_OPTION;
	}
	
	
	private void startMusicList() {
		try {
			
			ArrayList<String> musics = Resource.readFile("txtFiles\\songs.txt");
			ArrayList<String> musicsPaths = Resource.readFile("txtFiles\\songsPaths.txt");
			
			for (int i = 0; i < musics.size(); i++) {
				
				songs.addElement(new Music(musicsPaths.get(i),musics.get(i)));
				names.addElement(musics.get(i));
				
			}
		
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	private void refreshMusicList() {
		Resource.writeInFile("txtFiles\\songsPaths.txt", songFile.getAbsolutePath());
		Resource.writeInFile("txtFiles\\songs.txt", songFile.getName());
	}
}
