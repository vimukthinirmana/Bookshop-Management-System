package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Employe;
import lk.ijse.bookshop.to.Publisher;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PublisherModel {
    public static boolean add(Publisher publisher) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO publisher VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, publisher.getPublisherId(), publisher.getName(), publisher.getEmail(), publisher.getPhoneNo(),publisher.getAddress());
    }

    public static boolean delete(Publisher publisher, String publisherId) throws SQLException, ClassNotFoundException {
        String sql = "Delete FROM publisher WHERE publisherId = ?";
        return CrudUtil.execute(sql,publisherId);
    }

    public static boolean update(Publisher publisher, String publisherId) throws SQLException, ClassNotFoundException {
        String sql = "update publisher set name = ? , email = ? , phoneNo = ? , address = ? where publisherId = ? ";
        return CrudUtil.execute(sql, publisher.getName(), publisher.getEmail(), publisher.getPhoneNo(),publisher.getAddress(),publisherId);
    }

    public static Publisher search(String publisherId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM publisher WHERE publisherId = ?";
        ResultSet result = CrudUtil.execute(sql, publisherId);

        if(result.next()) {
            return new Publisher(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5)
            );
        }
        return null;
    }

    public static ArrayList<Publisher> getPublisherData() throws SQLException, ClassNotFoundException {
        ArrayList<Publisher> publisherData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from publisher");
        while (resultSet.next()){

            publisherData.add(new Publisher(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)));
        }
        return publisherData;
    }

}
