package playermp3gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import playermp3.Music;
import playermp3.Resource;
import registersystem.Register;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
public class PlayerGUI {

	private JFrame frmPlayermp;
	private File songFile;
	
	private ArrayList<Music> songs = new ArrayList<Music>();
	private DefaultListModel<String> names = new DefaultListModel<String>();
	
	
	/**
	 * Inicia a aplica��o.
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
	 * Cria a aplica��o.
	 */
	public PlayerGUI() {
	
		
		initialize();
	}

	/**
	 * Inicializa o conte�do a ser exibido na tela.
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
		
		JLabel lblMsicas = new JLabel("Biblioteca de M\u00FAsicas");
		lblMsicas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsicas.setBounds(167, 17, 157, 14);
		frmPlayermp.getContentPane().add(lblMsicas);
		
		JButton btnAddPlaylist = new JButton("add playlist");
		btnAddPlaylist.setBounds(623, 351, 123, 23);
		frmPlayermp.getContentPane().add(btnAddPlaylist);
		
		//Chamada da Fun��o que carrega os dados para o sistema.
		startMusicList();
		
		JScrollPane scrollPaneMusicLibrary = new JScrollPane();
		scrollPaneMusicLibrary.setBounds(167, 44, 159, 296);
		frmPlayermp.getContentPane().add(scrollPaneMusicLibrary);
		
		
		JList<String> listMusicLibrary = new JList<String>();
		scrollPaneMusicLibrary.setViewportView(listMusicLibrary);
		listMusicLibrary.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMusicLibrary.setModel(names);
		listMusicLibrary.repaint();
		
		JScrollPane scrollPanePlaylistsSongs = new JScrollPane();
		scrollPanePlaylistsSongs.setBounds(388, 44, 159, 296);
		frmPlayermp.getContentPane().add(scrollPanePlaylistsSongs);	
		
		JList listPlayistsNames = new JList();
		scrollPanePlaylistsSongs.setViewportView(listPlayistsNames);
		
		JScrollPane scrollPanePlaylists = new JScrollPane();
		scrollPanePlaylists.setBounds(613, 153, 133, 183);
		frmPlayermp.getContentPane().add(scrollPanePlaylists);
		
		JList listPlaylists = new JList();
		scrollPanePlaylists.setViewportView(listPlaylists);
		
		JButton btnAddSong = new JButton("Add M\u00FAsica");
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Caminho da musica
				if (addSongs() != -1) {
					
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
						
						Resource.play(songs.get(0).getMusicPath());
						listMusicLibrary.setSelectedIndex(0);
						Resource.writeInFile("txtFiles//teste.txt", songs.get(0).getMusicPath());
						
					}else {
						
						Resource.stop();
						Resource.play(songs.get(listMusicLibrary.getSelectedIndex()).getMusicPath());
						Resource.writeInFile("txtFiles//teste.txt", songs.get(listMusicLibrary.getSelectedIndex()).getMusicPath());
						
					}
					
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null,"A lista de m�sicas est� vazia", "ERRO", JOptionPane.ERROR_MESSAGE);
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
						
						Resource.play(songs.get(0).getMusicPath());
						listMusicLibrary.setSelectedIndex(0);
						
					}else {
						
						Resource.stop();
						int next = (listMusicLibrary.getSelectedIndex()+1) % listMusicLibrary.getModel().getSize();
						
						Resource.play(songs.get(next).getMusicPath());
						listMusicLibrary.setSelectedIndex(next);
						
					}
					
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null,"A lista de m�sicas est� vazia", "ERRO", JOptionPane.ERROR_MESSAGE);
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
						
						Resource.play(songs.get(songs.size()-1).getMusicPath());
						listMusicLibrary.setSelectedIndex(0);
						
					}else {
						
						Resource.stop();
						int prev = (Math.abs(listMusicLibrary.getSelectedIndex()-1)) % listMusicLibrary.getModel().getSize();
						
						Resource.play(songs.get(prev).getMusicPath());
						listMusicLibrary.setSelectedIndex(prev);
						
					}
					
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null,"A lista de m�sicas est� vazia", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnPrev.setBounds(164, 351, 69, 23);
		frmPlayermp.getContentPane().add(btnPrev);
		
		JButton btnRemMsica = new JButton("Rem M\u00FAsica");
		btnRemMsica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
				if( listMusicLibrary.getSelectedValue() == null) {
					
					throw new NoSuchFieldException() ;
				}
				int i = Resource.deleteSong("txtFiles//songs.txt", listMusicLibrary.getSelectedValue()+";"+songs.get(listMusicLibrary.getSelectedIndex()).getMusicPath());
				
				if(i!= -1) {
					names.removeElementAt(i);
					songs.remove(songs.get(i));
					listMusicLibrary.setSelectedIndex(i);
					listMusicLibrary.repaint();
					
				}
				}catch(Exception e) {
					
					JOptionPane.showMessageDialog(null,"Selecione um arquivo para poder delet�-lo", "Arquivo n�o selecionado", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		btnRemMsica.setBounds(10, 94, 105, 23);
		frmPlayermp.getContentPane().add(btnRemMsica);
		
		JButton btnAddDirectory = new JButton("Add Diret\u00F3rio");
		btnAddDirectory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (addDirectory() != -1) {
					
					listMusicLibrary.repaint();
				}
			}
		});
		btnAddDirectory.setBounds(10, 124, 105, 23);
		frmPlayermp.getContentPane().add(btnAddDirectory);
		
		JButton btnRegister = new JButton("Cadastrar");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				Register.main(null);
				
			}
		});
		btnRegister.setBounds(623, 13, 123, 104);
		frmPlayermp.getContentPane().add(btnRegister);
		
		
		
			
		
	}
	
	/**
	 * Adiciona uma ou m�ltiplas m�sicas � lista de reprodu��o
	 * @return um inteiro que indica se a tela foi fechada antes da sele��o de alguma m�sica.
	 */
	private int addSongs() {
		try {
				
			JFileChooser chooser = new JFileChooser("D:\\M�sicas");
			chooser.setDialogTitle("Escolha de arquivo");
			chooser.setFileFilter(new FileNameExtensionFilter("Music files", "mp3"));
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setMultiSelectionEnabled(true);
			chooser.showOpenDialog(null);
			
			File selectedFiles[] = chooser.getSelectedFiles();
			
			for (int i = 0; i < selectedFiles.length; i++) {
				
				songFile = selectedFiles[i];	
			
				
				if(!names.contains(songFile.getName())) {
				
					songs.add(new Music(songFile.getAbsolutePath(),songFile.getName()));
					names.addElement(songFile.getName());
					refreshMusicList();	
				
				}
				
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,"Nenhum arquivo selecionado", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		return JOptionPane.CLOSED_OPTION;
	}
	
	/**
	 * Adiciona todas as m�sicas de um diret�rio � biblioteca de m�sicas
	 * @return um inteiro que indica se a tela foi fechada antes da sele��o de alguma m�sica.
	 */
	private int addDirectory() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("MP3 files", "mp3"));
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.showOpenDialog(null);
        
        	File file = new File(chooser.getSelectedFile().getAbsolutePath());
        	
        	//Array do tipo File que armazena os arquivos
        	File aFile[] = file.listFiles();
        
        	for (int i = 0; i < aFile.length;  i++) {
        	
        		songFile = aFile[i];	
			
			
				if(!names.contains(songFile.getName()) && songFile.getAbsolutePath().endsWith(".mp3")) {
				
					songs.add(new Music(songFile.getAbsolutePath(),songFile.getName()));
					names.addElement(songFile.getName());
					refreshMusicList();	
				
				}
            
        	}
		}catch (Exception e2) {
			JOptionPane.showMessageDialog(null,"Nenhum diret�rio selecionado", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		return JOptionPane.CLOSED_OPTION;
	}
	
	/**
	 * Carrega a lista de musicas contida no arquivo songs.txt e as coloca na lista de m�sicas;
	 */
	private void startMusicList() {
		try {
			
			ArrayList<String> musics = Resource.readFile("txtFiles\\songs.txt");
			
			
			for (int i = 0; i < musics.size(); i++) {
				
				String[] nameNpath = musics.get(i).split(";");
				
				songs.add(new Music(nameNpath[1],nameNpath[0]));
				names.addElement(nameNpath[0]);
				
			}
		
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	/**
	 * Atualiza as musicas do arquivo songs.txt
	 */
	private void refreshMusicList() {
		Resource.writeInFile("txtFiles\\songs.txt", songFile.getName()+";"+songFile.getAbsolutePath());
	}
}
