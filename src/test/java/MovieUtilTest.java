
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieUtilTest {
    private List<Movie> movies;
    private MovieUtil movieUtil;

    @BeforeEach
    void setUp() {
        movieUtil = new MovieUtil();

        movies = Arrays.asList(
                new Movie("1", "Film A", 1975, Arrays.asList("Action", "Drama"), "Director 1", Arrays.asList("Actor 1", "Actor 2"), 8.2, Arrays.asList("English", "French"), 120),
                new Movie("2", "Film B", 1975, Arrays.asList("Comedy"), "Director 2", Arrays.asList("Actor 2", "Actor 3"), 7.5, Arrays.asList("English"), 150),
                new Movie("3", "Film C", 1975, Arrays.asList("Thriller"), "Director 3", Arrays.asList("Actor 1", "Actor 4"), 9.0, Arrays.asList("Spanish"), 110),
                new Movie("4", "Film D", 1975, Arrays.asList("Horror", "Thriller"), "Director 4", Arrays.asList("Actor 5", "Actor 6"), 7.8, Arrays.asList("English", "German"), 180),
                new Movie("5", "Film E", 1975, Arrays.asList("Drama"), "Director 5", Arrays.asList("Actor 7", "Actor 9"), 6.9, Arrays.asList("Swedish"), 140),
                new Movie("6", "Film F", 1975, Arrays.asList("Sci-Fi", "Adventure"), "Director 6", Arrays.asList("Actor 8", "Actor 9", "Actor 10"), 9.1, Arrays.asList("Japanese"), 200),
                new Movie("7", "Film G", 1975, Arrays.asList("Comedy"), "Director 7", Arrays.asList("Actor 3", "Actor 7", "Actor 8"), 5.5, Arrays.asList("English", "French"), 90),
                new Movie("8", "Film H", 1975, Arrays.asList("Action"), "Director 8", Arrays.asList("Actor 10"), 6.0, Arrays.asList("Korean"), 95),
                new Movie("9", "Film I", 1975, Arrays.asList("Crime", "Drama"), "Director 9", Arrays.asList("Actor 11", "Actor 12"), 7.2, Arrays.asList("Italian"), 105),
                new Movie("10", "Film J", 1975, Arrays.asList("Drama", "Mystery"), "Director 10", Arrays.asList("Actor 1", "Actor 13"), 7.0, Arrays.asList("English"), 130),
                new Movie("11", "Film K", 1975, Arrays.asList("Fantasy"), "Director 11", Arrays.asList("Actor 14", "Actor 15", "Actor 16"), 8.5, Arrays.asList("Chinese"), 150)
        );
    }


    // Hur många filmer gjordes 1975
    @Test
    void countMovies(){
        assertEquals(11L, movieUtil.countMovies(movies));
}

    // Hitta längden på den film som var längst
    @Test
    void longestRuntime(){
        assertEquals(200, movieUtil.longestRuntime(movies));
    }

    // Hur många UNIKA genrer hade filmerna från 1975
    @Test
    void uniqueGenres(){
        assertEquals(10L, movieUtil.uniqueGenres(movies));
        assertNotEquals(8L, movieUtil.uniqueGenres(movies));
}

    // Vilka skådisar som spelade i den film som hade högst imdb-rating
    @Test
    void actorsInHighestRatedFilms(){
        assertEquals(Arrays.asList("Actor 8", "Actor 9", "Actor 10"),movieUtil.actorsInHighestRatedFilms(movies));
        assertNotEquals(Arrays.asList("Actor 8", "Actor 9", "Actor 11"),movieUtil.actorsInHighestRatedFilms(movies));
    }

    // Vad är titeln på den film som hade minst antal skådisar listade?
    @Test
    void leastAmountOfActors(){
        assertEquals("Film H", movieUtil.leastAmountOfActors(movies));
        assertNotEquals("Film A", movieUtil.leastAmountOfActors(movies));
 }

    //Hur många skådisar var med i mer än 1 film
    @Test
    void actorsInMultipleMovies(){
        assertEquals(7L, movieUtil.actorsInMultipleMovies(movies));
        assertNotEquals(3L, movieUtil.actorsInMultipleMovies(movies));
    }

    // Vad hette den skådis som var med i flest filmer
    @Test
    void mostFrequentActor(){
        assertEquals("Actor 1", movieUtil.mostFrequentActor(movies));
        assertNotEquals("Actor 8", movieUtil.mostFrequentActor(movies));
    }

    // Hur många UNIKA språk har filmerna
    @Test
    void uniqueLanguages(){
        assertEquals(9L, movieUtil.uniqueLanguages(movies));
        assertNotEquals(5L, movieUtil.uniqueLanguages(movies));
    }

    // Finns det någon titel som mer än en film har
    @Test
    void movieWithTheSameTitel(){
        assertFalse(movieUtil.movieWithTheSameTitel(movies));
        assertNotEquals(true, movieUtil.movieWithTheSameTitel(movies));
    }

    @Test
    void countMoviesHof() {
        Predicate<Movie> isFrom1975 = movie -> movie.getYear() == 1975;
        assertEquals(11L, movieUtil.countMoviesHof(movies, isFrom1975));
        Predicate<Movie> isFrom1980 = movie -> movie.getYear() == 1980;
        assertEquals(0L, movieUtil.countMoviesHof(movies, isFrom1980));
    }

    @Test
    void mostFrequentActorHof() {
        IMovieProcessor yearFilter = movie -> movie.getYear() == 1975;
        assertEquals("Actor 1", movieUtil.mostFrequentActorHof(movies, yearFilter));

}

}



