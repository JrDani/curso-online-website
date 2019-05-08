package br.com.fabricaon.cursos.builder;

import java.util.List;

import br.com.fabricaon.cursos.model.Playlist;
import br.com.fabricaon.cursos.model.Video;
import br.com.fabricaon.cursos.model.VideosSumario;

public class SumarioBuilder {
	
	/*Product class attributes */
	//private Map<Integer, List<Video>> videosDaPlaylist = new LinkedHashMap<Integer, List<Video>>();	
	private Playlist playlist;
	private Integer capitulo;
	private List<Video> videos;
	
	public Playlist getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	public Integer getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(Integer capitulo) {
		this.capitulo = capitulo;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	/* Builder's methods */
	/*private void RelacionaCapituloVideo() {		
		
		//pega os capítulos excluindo os que se repetem
		ArrayList<Integer> keys = new ArrayList<Integer>(new HashSet<Integer>(Arrays.asList(capitulos)));
		
		//Para cada capitulo adicione uma array list vazia (espaço para os videos)
		keys.stream().forEach(key->this.videosDaPlaylist.put(key, new ArrayList<Video>()));
		
		//adicione os videos em seus respectivos capitulos
		int i = 0; //se usar Guava refatore este trecho para map with index
		for (Integer cap : capitulos) {
			this.videosDaPlaylist.get(cap).add(this.videos.get(i));
			i++;
		}
	}
	*/
	public VideosSumario build(){
		
		VideosSumario videosSumario = new VideosSumario();
		videosSumario.setCapitulo(capitulo);
		videosSumario.setPlaylist(playlist);
		videosSumario.setVideos(videos);
		
		return videosSumario;
	}

}
