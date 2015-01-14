/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    // Veces que se ha reproducido la cancion en la sesion
    private int playCount;
    // Genero de la cancion (estilo musical)
    private String style;
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     * @param style El estilo musical de la cancion
     */
    public Track(String artist, String title, String filename, String style)
    {
        setDetails(artist, title, filename, style);
        playCount = 0;
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename, "unknown");
    }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
    
    /**
     * Devuelve el estilo musical de la cancion
     */
    public String getStyle(){
        return style;
    }
    
    /**
     * Cambia el estilo musical de la cancion
     */
    public void setStyle(String style){
        this.style = style;
    }
    
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ")\n-> Times played: "
        + playCount + ", Music Style: " + style;
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename, String style)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
        this.style = style;
    }
    
    /**
     * Devuelve las veces que se ha reproducido la cancion en esta sesion
     */
    public int getPlayCount(){
        return playCount;
    }
    
    /**
     * Incrementa el contador de reproducciones
     */
    public void increasePlayCount(){
        playCount++;
    }
    
    /**
     * Reinicia el contador de reproducciones
     */
    public void resetPlayCount(){
        playCount = 0;
    }
}
