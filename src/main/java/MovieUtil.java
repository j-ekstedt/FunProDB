import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieUtil {


    // Hur många filmer gjordes 1975
   static Long countMovies(List<Movie> movies) {
        return movies.stream()
                .filter(movie -> movie.getYear() == 1975)
                .count();
    }

    static Long countMoviesHof(List<Movie> movies, Predicate<Movie> predicate) {
       return movies.stream()
               .filter(predicate)
               .count();

    }

     // Hitta längden på den film som var längst

    Integer longestRuntime(List<Movie> movies) {
        return movies.stream()
                .mapToInt(Movie::getRuntime)
                .max()
                .orElse(0);
    }


    // Hur många UNIKA genrer hade filmerna från 1975

    Long uniqueGenres(List<Movie> movies) {
        return movies.stream()
                .flatMap(m -> m.getGenres().stream())
                .distinct()
                .count();
    }

    // Vilka skådisar som spelade i den film som hade högst imdb-rating

    public List<String> actorsInHighestRatedFilms(List<Movie> movies) {
        return movies.stream()
                .max(Comparator.comparingDouble(Movie::getImdbRating))
                .map(movie -> Collections.unmodifiableList(movie.getCast()))  // Gör skådespelarlistan immutabel
                .orElse(Collections.emptyList());
    }

    // Vad är titeln på den film som hade minst antal skådisar listade?

    String leastAmountOfActors(List<Movie> movies) {
        return movies.stream()
                .min(Comparator.comparingInt(m -> m.getCast().size()))
                .map(Movie::getTitle)
                .orElse("");
    }


    //Hur många skådisar var med i mer än 1 film

    Long actorsInMultipleMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(m -> Collections.unmodifiableList(m.getCast()).stream())  // Gör listan immutabel
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .values().stream()
                .filter(count -> count > 1)
                .count();
    }

    // Vad hette den skådis som var med i flest filmer

    String mostFrequentActor(List<Movie> movies) {
        return movies.stream()
                .flatMap(c->c.getCast().stream())
                .collect(Collectors.groupingBy(m->m, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public static  String mostFrequentActorHof(List<Movie> movies, IMovieProcessor operator) {
        return movies.stream()
                .filter(operator::apply)  // Använder operatorn för att filtrera filmer
                .flatMap(m -> m.getCast().stream())  // Skapar en stream av alla skådespelare
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Något blev fel");
    }


    // Hur många UNIKA språk har filmerna

    Long uniqueLanguages(List<Movie> movies) {
        return movies.stream()
                .filter(movie -> movie.getYear() == 1975)
                .flatMap(l->l.getLanguages().stream())
                .distinct()
                .count();
    }


    // Finns det någon titel som mer än en film har
    Boolean movieWithTheSameTitel(List<Movie> movies) {
        return movies.stream()
                .collect(Collectors.groupingBy(Movie::getTitle, Collectors.counting()))
                .values().stream()
                .anyMatch(count -> count > 1);
    }



}
