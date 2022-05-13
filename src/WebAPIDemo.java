import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class WebAPIDemo {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String APIkey = "879658fa17cffabf341a5ed99904cf48";

        String urlNowPlaying = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + APIkey + "&language=en-US";
        String response = makeAPICall(urlNowPlaying);

        ArrayList<Movie> movies = new ArrayList<Movie>();
        if (response != null)
        {
            parseJSON(response, movies);
        }
        for (Movie movie : movies)
        {
            System.out.println(movie.getID() + " " + movie.getTitle() + " " + movie.getReleaseDate() + " " + movie.getPopularity() + " " + movie.isAdult() + " " + movie.getPosterPath());
        }
        System.out.print("Which movie would you like to learn more about? ");
        String movieID = scan.nextLine();

        String urlDetails = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + APIkey + "&language=en-US";
        MovieDetails[] list = new MovieDetails[1];
        parseJSONDetails(urlDetails, list);
        System.out.println(list[0].getName() + list[0].getOrigLang() + list[0].getOrigCountry());


    }

    private static void parseJSON(String json, ArrayList<Movie> movies) {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray movieList = jsonObj.getJSONArray("results");

        for (int i = 0; i < movieList.length(); i++)
        {
            JSONObject movieObj = movieList.getJSONObject(i);
            String movieTitle = movieObj.getString("title");
            int movieID = movieObj.getInt("id");
            String releaseDate = movieObj.getString("release_date");
            Number popularity = movieObj.getNumber("popularity");
            boolean isAdult = movieObj.getBoolean("adult");
            String posterPath = movieObj.getString("poster_path");
            String fullPosterPath = "https://image.tmdb.org/t/p/w500" + posterPath;

            Movie movie = new Movie(movieTitle, movieID, releaseDate, popularity, isAdult, fullPosterPath);
            movies.add(movie);
        }
    }

    private static void parseJSONDetails(String json, MovieDetails[] list){
        JSONObject jsonObj = new JSONObject(json);
        JSONArray prodComp = new JSONArray("production_companies");
        JSONArray genre = new JSONArray("genres");
        JSONArray prodCount = new JSONArray("production_countries");
        String name = prodComp.getJSONObject(0).getString("name");
        String origCountry =prodCount.getJSONObject(0).getString("origin_country");
        String origLang = genre.getJSONObject(0).getString("original_language");

        MovieDetails detail = new MovieDetails(name, origLang, origCountry);
        list[0] = detail;
    }

    public static String makeAPICall(String url)
    {
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // UNCOMMENT TO PRINT OTHER PARTS OF THE RESPONSE
            //System.out.println(response.statusCode());
            //System.out.println(response.headers());
            return response.body();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}