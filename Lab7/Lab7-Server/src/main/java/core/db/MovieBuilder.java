package core.db;

import core.system.Config;
import shared.enteties.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MovieBuilder extends RecursiveTask<HashSet<Movie>> {

    private final Object lock = new Object();
    private static final int LIMIT = 10;
    private static final Connection connection = Config.getConnection();
    private ResultSet resultSet;
    private User user;

    public MovieBuilder(ResultSet rs, User user) {
        this.resultSet = rs;
        this.user = user;
    }

    @Override
    protected HashSet<Movie> compute() {
        try {

            if (resultSet.getFetchSize() <= LIMIT) {
                return builder();
            } else {
                List<MovieBuilder> subtasks = new ArrayList<>(createSubtasks());

                for (MovieBuilder subtask : subtasks) {
                    subtask.fork();
                }

                HashSet<Movie> joinedMovies = new HashSet<>();
                for(MovieBuilder subtask : subtasks) {
                    joinedMovies.addAll(subtask.join());
                }

                return joinedMovies;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<MovieBuilder> createSubtasks() throws SQLException {

        List<MovieBuilder> subtasks = new ArrayList<>();
        int offset = this.resultSet.getFetchSize() / 2;

        PreparedStatement statement1 = connection.prepareStatement("""
                    select * from movie where owned_by = ? limit ? offset ?;
                """);
        statement1.setInt(1, user.getId());
        statement1.setInt(2, LIMIT);
        statement1.setInt(3, 0);

        PreparedStatement statement2 = connection.prepareStatement("""
                    select * from movie where owned_by = ? limit ? offset ?;
                """);
        statement2.setInt(1, user.getId());
        statement2.setInt(2, LIMIT);
        statement2.setInt(3, offset);

        ResultSet rs1;
        ResultSet rs2;
        synchronized (lock) {
            rs1 = statement1.executeQuery();
            rs2 = statement2.executeQuery();
        }

        MovieBuilder mb1 = new MovieBuilder(rs1, this.user);
        MovieBuilder mb2 = new MovieBuilder(rs2, this.user);

        subtasks.add(mb1);
        subtasks.add(mb2);

        return subtasks;
    }

    private HashSet<Movie> builder() {

        HashSet<Movie> movies = new HashSet<>();

        try {
            while (resultSet.next()) {
                PreparedStatement st = connection.prepareStatement("""
                        select name from mpaa where id = ?;
                        """);
                st.setInt(1, resultSet.getInt(3));
                ResultSet mp = st.executeQuery();
                String mpaa;
                if (mp.next()) {
                    mpaa = mp.getString(1);
                } else {
                    throw new SQLException();
                }
                long movieId = resultSet.getLong(1);
                int coords_x = resultSet.getInt(4);
                long coords_y = resultSet.getLong(5);
                String dirName = resultSet.getString(6);
                long dirHeight = resultSet.getLong(7);
                String locName = resultSet.getString(8);
                int loc_x = resultSet.getInt(9);
                int loc_y = resultSet.getInt(10);
                String movieName = resultSet.getString(11);
                int oscarCount = resultSet.getInt(13);
                int goldenPalmCount = resultSet.getInt(14);
                float tbo = resultSet.getFloat(15);


                Movie m = new Movie(
                        movieName, new Coordinates(coords_x, coords_y), oscarCount,
                        goldenPalmCount, tbo, MpaaRating.valueOf(mpaa), new Person(dirName, dirHeight,
                        new Location(loc_x, loc_y, locName))
                );
                m.setId(movieId);
                movies.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }


}
