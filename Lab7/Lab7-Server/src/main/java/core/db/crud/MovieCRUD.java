package core.db.crud;

import core.db.MovieBuilder;
import shared.enteties.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class MovieCRUD {

    private static final Object lock = new Object();

    public static void addMovie(Connection connection, User user, Movie movie) throws SQLException {

        int mpaaId = MpaaCRUD.getMpaaIdByName(connection, movie.getMpaaRating().name());

        PreparedStatement statement = connection.prepareStatement("""
                insert into Movie (id, owned_by, mpaa_id,
                coords_x, coords_y, dir_name, dir_height,
                loc_name, loc_x, loc_y,
                name, creationdate, oscarcount, goldenpalmcount, totalboxoffice)
                values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default, ?, ?, ?);
                     """);

        statement.setInt(1, user.getId());
        statement.setInt(2, mpaaId);
        statement.setLong(3, movie.getCoordinates().getCoords()[0]);
        statement.setLong(4, movie.getCoordinates().getCoords()[1]);
        statement.setString(5, movie.getDirector().getName());
        statement.setLong(6, movie.getDirector().getHeight());
        statement.setString(7, movie.getDirector().getLocation().getName());
        statement.setInt(8, movie.getDirector().getLocation().getCoords()[0]);
        statement.setInt(9, movie.getDirector().getLocation().getCoords()[1]);
        statement.setString(10, movie.getName());
        statement.setInt(11, movie.getOscarsCount());
        statement.setLong(12, movie.getGoldenPalmCount());
        statement.setFloat(13, movie.getTotalBoxOffice());

        statement.execute();
        statement.close();


    }

    public static void updateById(Connection connection, User user, Movie movie, long movieToUpdate) throws SQLException {

        int mpaaId = MpaaCRUD.getMpaaIdByName(connection, movie.getMpaaRating().name());

        PreparedStatement statement = connection.prepareStatement("""
                update movie set mpaa_id = ?, coords_x = ?, coords_y = ?,
                dir_name = ?, dir_height = ?, loc_name = ?, loc_x = ?, loc_y = ?,
                name = ?, oscarcount = ?, goldenpalmcount = ?, totalboxoffice = ?
                where id = ? and owned_by = ?;
                """);

        statement.setInt(1, mpaaId);
        statement.setLong(2, movie.getCoordinates().getCoords()[0]);
        statement.setLong(3, movie.getCoordinates().getCoords()[1]);
        statement.setString(4, movie.getDirector().getName());
        statement.setLong(5, movie.getDirector().getHeight());
        statement.setString(6, movie.getDirector().getLocation().getName());
        statement.setInt(7, movie.getDirector().getLocation().getCoords()[0]);
        statement.setInt(8, movie.getDirector().getLocation().getCoords()[1]);
        statement.setString(9, movie.getName());
        statement.setInt(10, movie.getOscarsCount());
        statement.setLong(11, movie.getGoldenPalmCount());
        statement.setFloat(12, movie.getTotalBoxOffice());
        statement.setLong(13, movieToUpdate);
        statement.setInt(14, user.getId());

        statement.execute();
        statement.close();

    }

    public static void removeById(Connection connection, User user, long idToDel) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("""
                delete from movie where owned_by = ? and id = ?
                """);
        statement.setInt(1, user.getId());
        statement.setLong(2, idToDel);

        statement.execute();
    }

    public static synchronized void deleteAll(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("""
                delete from movie where owned_by = ?
                """);
        statement.setInt(1, user.getId());

        statement.execute();
        statement.close();

    }


    /**
     * for 'show' command
     *
     * @return all movies
     */
    public static HashSet<Movie> getAllMoviesByUser(Connection connection, User user) throws SQLException {

        HashSet<Movie> movies = new HashSet<>();

        PreparedStatement statement = connection.prepareStatement("""
                    select * from movie where owned_by = ?;
                """);

        statement.setInt(1, user.getId());
        ResultSet rs = statement.executeQuery();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MovieBuilder movieBuilder = new MovieBuilder(rs, user);
        movies = forkJoinPool.invoke(movieBuilder);
        return movies;
    }

    public static HashSet<Movie> getAllMovies(Connection connection, User user) throws SQLException {

        HashSet<Movie> movies;

        PreparedStatement statement = connection.prepareStatement("""
                    select * from movie;
                """);

        ResultSet rs = statement.executeQuery();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MovieBuilder movieBuilder = new MovieBuilder(rs, user);
        movies = forkJoinPool.invoke(movieBuilder);
        return movies;
    }
}
