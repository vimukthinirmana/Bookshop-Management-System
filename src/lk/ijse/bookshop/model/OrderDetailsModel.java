package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Book;
import lk.ijse.bookshop.to.OrderDetails;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsModel {
    public static ArrayList<OrderDetails> getBookData() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> OrderDetailsData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from orderdetails");
        while (resultSet.next()){

            OrderDetailsData.add(new OrderDetails(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)));
        }
        return OrderDetailsData;
    }
}
