import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer(String libreriaDeAudio)
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary(libreriaDeAudio);
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(indexValid(index)) {
            if(!player.isPlaying()){
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                track.increasePlayCount();
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            }else{
                System.out.println("El reproductor ya esta en marcha, detengalo antes");
            }
        }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * Metodo que muestra todas las canciones almacenadas en el organizador
     * Utiliza un objeto de la clase Iterator
     */
    public void listAllTracksWithIterator(){
        Iterator<Track> iteracion = tracks.iterator();
        while(iteracion.hasNext()){
            Track song = iteracion.next();
            System.out.println(song.getDetails());
        }
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Elimina las canciones cuyo artista coincida con el buscado
     * Utiliza la clase Iterator
     */
    public void removeByArtist(String artista){
        Iterator<Track> iteracion = tracks.iterator();
        while(iteracion.hasNext()){
            Track song = iteracion.next();
            if(song.getArtist().contains(artista)){
                iteracion.remove();
            }
        }
    }
    
    /**
     * Elimina las canciones cuyo titulo coincida con el buscado
     * Utiliza la clase Iterator
     */
    public void removeByTitle(String titulo){
        Iterator<Track> iteracion = tracks.iterator();
        while(iteracion.hasNext()){
            Track song = iteracion.next();
            if(song.getTitle().contains(titulo)){
                iteracion.remove();
            }
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(tracks.size() > 0) {
            if(!player.isPlaying()){
                Track song = tracks.get(0);
                player.startPlaying(song.getFilename());
                song.increasePlayCount();
            }else{
                System.out.println("El reproductor ya esta en marcha, detengalo antes");
            }
        }
    }
    
    /**
     * Reproduce una cancion aleatoria de la lista de canciones
     */
    public void playRandom(){
        Random aleatoria = new Random();
        playTrack(aleatoria.nextInt(tracks.size()));
    }
    
    /**
     * Reproduce aleatoriamente el inicio de todas las canciones de la lista
     */
    public void playShuffle(){
        int largo = tracks.size();
        Boolean played[] = new Boolean[largo];
        for(int i = 0; i < largo; i++){
            played[i] = false;
        }
        Boolean acabado = false;
        Random actual = new Random();
        while(!acabado){
            int seleccion = actual.nextInt(largo);
            if(!played[seleccion]){
                Track song = tracks.get(seleccion);
                System.out.println(song.getDetails());
                song.increasePlayCount();
                player.playSample(song.getFilename());
                played[seleccion] = true;
            }
            acabado = true;
            for(Boolean comprobar : played){
                if(!comprobar){
                    acabado = false;
                }
            }
        }
            
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    /**
     * Muestra por pantalla la informacion de las canciones cuyo titulo contiene el texto buscado
     * Si no encuentra ninguna, avisa de ello
     */
    public void findInTitle(String toFind){
        boolean found = false;
        for(Track song : tracks){
            String songTitle = song.getTitle();
            if(songTitle.contains(toFind)){
                found = true;
                System.out.println(song.getDetails());
            }
        }
        if(!found){
            System.out.println("No se han encontrado canciones con ese texto");
        }
    }
    
    /**
     * Cambia el estilo musical de una cancion de la lista
     * Se debe indicar el indice de la cancion empezando en 0
     * Si la cancion no existe, lo indica
     */
    public void setTrackStyle(int index, String style){
        if(index >= 0 && index < tracks.size()){
            tracks.get(index).setStyle(style);
            System.out.println("Estilo musical actualizado");
        }else{
            System.out.println("Ese indice no existe");
        }
    }
    
    /**
     * Informa al usuario de si el reproductor esta en marcha
     */
    public void playerStarted(){
        if(player.isPlaying()){
            System.out.println("Hay una cancion reproduciendose actualmente");
        }else{
            System.out.println("El reproductor esta detenido");
        }
    }
}
