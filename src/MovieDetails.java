public class MovieDetails {
    private String name;

    public String getOrigLang() {
        return origLang;
    }

    private String origLang;

    public String getOrigCountry() {
        return origCountry;
    }

    private String origCountry;

    public String getName() {
        return name;
    }


    public MovieDetails(String name, String origLang, String origCountry){
        this.name = name;
        this.origLang = origLang;
        this.origCountry = origCountry;
    }

}
