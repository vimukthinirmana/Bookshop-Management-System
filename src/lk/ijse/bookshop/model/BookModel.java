package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.Book;
import lk.ijse.bookshop.to.CartDetail;
import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

public class BookModel {
    public static boolean add(Book book) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, book.getBookId(), book.getName(), book.getCategory(), book.getYear(),book.getPrice(),book.getQtyOnStock(),book.getEmployeId());
    }

    public static Book search(String bookId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Book WHERE bookId = ?";
        ResultSet result = CrudUtil.execute(sql, bookId);

        if(result.next()) {
            return new Book(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getDouble(5),
                    result.getInt(6),
                    result.getString(7)

            );
        }
        return null;
    }

    public static boolean delete(Book book, String bookId) throws SQLException, ClassNotFoundException {
        String sql = "Delete FROM Book WHERE bookId = ?";
        return CrudUtil.execute(sql,bookId);
    }


    public static boolean update(Book book, String bookId) throws SQLException, ClassNotFoundException {
        String sql = "update Book set name = ? , category = ? , year = ? ,price = ?,QtyOnStock = ?, employeId = ? where bookId = ? ";
        return CrudUtil.execute(sql, book.getName(),book.getCategory(), book.getYear(), book.getPrice(),book.getQtyOnStock(),book.getEmployeId(),bookId);
    }

    public static ArrayList<Book> getBookData() throws SQLException, ClassNotFoundException {
        ArrayList<Book> bookData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from Book");
        while (resultSet.next()){

            bookData.add(new Book(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)));
        }
        return bookData;
    }

    public static ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT bookId FROM Book";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }


    public static boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQty(new Book(cartDetail.getBookId(),cartDetail.getName(),cartDetail.getCategory() ,cartDetail.getYear(),cartDetail.getUnitPrice(),cartDetail.getQty(),cartDetail.getOrderId()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(Book book) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE book SET QtyOnStock = QtyOnStock - ? WHERE bookId= ?";
        return CrudUtil.execute(sql, book.getQtyOnStock(), book.getBookId());
    }

}
