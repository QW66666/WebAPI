public class Movie {

    private String title;
    private int id;
    private String posterPath;
    private boolean isAdult;
    private String releaseDate;
    private Number popularity;

    public Movie(String title, int id, String releaseDate, Number popularity, boolean isAdult, String posterPath)
    {
        this.title = title;
        this.id = id;
        this.posterPath = posterPath;
        this.isAdult = isAdult;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    public String getTitle()
    {
        return title;
    }

    public int getID()
    {
        return id;
    }

    public boolean isAdult(){
        return isAdult;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public Number getPopularity(){
        return  popularity;
    }
}