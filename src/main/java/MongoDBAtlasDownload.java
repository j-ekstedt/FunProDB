import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MongoDBAtlasDownload {
    public MongoDBAtlasDownload() {

        //Skriv in rätt url!
        String pw = System.getenv("dbpw");
        String uri = "mongodb+srv://dyssos:" + pw + "@cluster0.ivgv6.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            // Skriver ut alla filmer
            for (Movie movie : movieList) {
                System.out.println(movie);
            }

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften

            MovieUtil movieUtil = new MovieUtil();

            System.out.println("\nAntal filmer från 1975: " + movieUtil.countMovies(movieList));
            System.out.println();
            System.out.println("Längsta filmen är: " + movieUtil.longestRuntime(movieList) + " minuter");
            System.out.println();
            System.out.println("Antal unika genrer: " + movieUtil.uniqueGenres(movieList));
            System.out.println();
            System.out.println("Skådespelare i filmen med högst IMDb-rating: " + movieUtil.actorsInHighestRatedFilms(movieList));
            System.out.println();
            System.out.println("Filmen med minst antal skådespelare: " + movieUtil.leastAmountOfActors(movieList));
            System.out.println();
            System.out.println("Antal skådespelare som var med i mer än en film: " + movieUtil.actorsInMultipleMovies(movieList));
            System.out.println();
            System.out.println("Skådespelaren som var med i flest filmer: " + movieUtil.mostFrequentActor(movieList));
            System.out.println();
            System.out.println("Antal unika språk: " + movieUtil.uniqueLanguages(movieList));
            System.out.println();
            System.out.println("Finns det några filmer med samma titel? " + movieUtil.movieWithTheSameTitel(movieList));

            System.out.println("\nHof");
            Predicate<Movie> isFrom1975 = movie -> movie.getYear() == 1975;
            Long count1975 = MovieUtil.countMoviesHof(movieList, isFrom1975);
            System.out.println("Antal filmer från 1975: " + count1975);

            IMovieProcessor yearFilter = m -> m.getYear() == 1975;
            String actorInMostMovies = MovieUtil.mostFrequentActorHof(movieList, yearFilter);
            System.out.println("Skådespelaren som var med i flest filmer: " + actorInMostMovies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoDBAtlasDownload m = new MongoDBAtlasDownload();
    }
}

