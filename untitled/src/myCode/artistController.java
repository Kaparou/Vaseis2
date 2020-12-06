package myCode;

import com.sun.rowset.JdbcRowSetImpl;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;


public class artistController {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/music_wiki?serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASS = "1234";
    private JdbcRowSet rowSet = null;

    public artistController() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(DB_USER);
            rowSet.setPassword(DB_PASS);
            rowSet.setCommand("SELECT * FROM artists");
            rowSet.execute();

        }catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        public artists create(artists p) {
            try {
                rowSet.moveToInsertRow();
                rowSet.updateString("name", p.getName());
                rowSet.updateString("genre", p.getGenre());
                rowSet.updateString("description", p.getDescription());
                rowSet.updateString("discography", p.getDiscography());
                rowSet.insertRow();
                rowSet.moveToCurrentRow();
            } catch (SQLException ex) {
                try {
                    rowSet.rollback();
                    p = null;
                } catch (SQLException e) {

                }
                ex.printStackTrace();
            }
            return p;
        }

        public artists update(artists p) {
            try {
                rowSet.updateString("genre", p.getGenre());
                rowSet.updateString("description", p.getDescription());
                rowSet.updateString("discography", p.getDiscography());
                rowSet.updateRow();
                rowSet.moveToCurrentRow();
            } catch (SQLException ex) {
                try {
                    rowSet.rollback();
                } catch (SQLException e) {

                }
                ex.printStackTrace();
            }
            return p;
        }

        public void delete() {
            try {
                rowSet.moveToCurrentRow();
                rowSet.deleteRow();
            } catch (SQLException ex) {
                try {
                    rowSet.rollback();
                }catch (SQLException e) { }
                ex.printStackTrace();
            }

        }

        public artists moveFirst() {
            artists p = new artists();
            try {
                rowSet.first();
                p.setName(rowSet.getString("name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists moveLast() {
            artists p = new artists();
            try {
                rowSet.last();
                p.setName(rowSet.getString("name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists moveNext() {
            artists p = new artists();
            try {
                if (rowSet.next() == false)
                    rowSet.previous();
                p.setName(rowSet.getString("name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists movePrevious() {
            artists p = new artists();
            try {
                if (rowSet.previous() == false)
                    rowSet.next();
                p.setName(rowSet.getString("name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists getCurrent() {
            artists p = new artists();
            try {
                rowSet.moveToCurrentRow();
                p.setName(rowSet.getString("name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }
}

