package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Author;
import lk.ijse.bookshop.to.Employe;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorModel {
    public static boolean add(Author author) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO author VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, author.getAuthorId(), author.getName(), author.getEmail(), author.getPhoneNo());
    }

    public static boolean delete(Author author, String authorId) throws SQLException, ClassNotFoundException {
        String sql = "Delete FROM author WHERE authorId = ?";
        return CrudUtil.execute(sql,authorId);
    }

    public static boolean update(Author author, String authorId) throws SQLException, ClassNotFoundException {
        String sql = "update author set name = ? , email = ? , phoneNo = ? ,where authorId = ? ";
        return CrudUtil.execute(sql, author.getName(),author.getEmail(), author.getPhoneNo(),authorId);
    }

    public static Author search(String authorId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM author WHERE authorId = ?";
        ResultSet result = CrudUtil.execute(sql, authorId);

        if(result.next()) {
            return new Author(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)

            );
        }
        return null;
    }

    public static ArrayList<Author> getAuthorData() throws SQLException, ClassNotFoundException {
        ArrayList<Author> authorData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from author");
        while (resultSet.next()){

            authorData.add(new Author(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)));
        }
        return authorData;
    }

}
