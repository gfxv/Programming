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

        synchronized (lock) {
            statement.execute();
            statement.close();
        }

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

        synchronized (lock) {
            statement.execute();
            statement.close();
        }
    }

    public static void removeById(Connection connection, User user, long idToDel) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("""
                delete from movie where owned_by = ? and id = ?
                """);
        statement.setInt(1, user.getId());
        statement.setLong(2, idToDel);

        synchronized (lock) {
            statement.execute();
            statement.close();
        }

    }

    public static synchronized void deleteAll(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("""
                delete from movie where owned_by = ?
                """);
        statement.setInt(1, user.getId());

        synchronized (lock) {
            statement.execute();
            statement.close();
        }
    }


    /**
     * for 'show' command
     * @return all movies
     */
    public static HashSet<Movie> getAllMovies(Connection connection, User user) throws SQLException {

        HashSet<Movie> movies = new HashSet<>();

        PreparedStatement statement = connection.prepareStatement("""
                    select * from movie where owned_by = ?;
                """);

        statement.setInt(1, user.getId());
        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            PreparedStatement st = connection.prepareStatement("""
//                    select name from mpaa where id = ?;
//                    """);
//            st.setInt(1, rs.getInt(3));
//            ResultSet mp = st.executeQuery();
//            String mpaa;
//            if (mp.next()) {
//                mpaa = mp.getString(1);
//            } else {
//                throw new SQLException();
//            }
//            long movieId = rs.getLong(1);
//            int coords_x = rs.getInt(4);
//            long coords_y = rs.getLong(5);
//            String dirName = rs.getString(6);
//            long dirHeight = rs.getLong(7);
//            String locName = rs.getString(8);
//            int loc_x = rs.getInt(9);
//            int loc_y = rs.getInt(10);
//            String movieName = rs.getString(11);
//            int oscarCount = rs.getInt(13);
//            int goldenPalmCount = rs.getInt(14);
//            float tbo = rs.getFloat(15);
//
//
//            Movie m = new Movie(
//                    movieName, new Coordinates(coords_x, coords_y), oscarCount,
//                    goldenPalmCount, tbo, MpaaRating.valueOf(mpaa), new Person(dirName, dirHeight,
//                    new Location(loc_x, loc_y, locName))
//            );
//            m.setId(movieId);
//            movies.add(m);
//        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MovieBuilder movieBuilder = new MovieBuilder(rs, user);
        movies = forkJoinPool.invoke(movieBuilder);
        return movies;
    }
}
