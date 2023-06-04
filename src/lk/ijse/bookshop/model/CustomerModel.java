package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getNic(), customer.getName(), customer.getAddress(), customer.getPhoneNo(),customer.getEmployeId());

    }

    public static Customer search(String nic) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Customer WHERE nic = ?";
        ResultSet result = CrudUtil.execute(sql, nic);

        if(result.next()) {
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5)
            );
        }
        return null;
    }

    public static boolean update(Customer customer,String nic) throws SQLException, ClassNotFoundException {
        String sql = "update customer set name = ? , address = ? , phoneNo = ? , employeId = ? where nic = ? ";
         return CrudUtil.execute(sql, customer.getName(), customer.getAddress(), customer.getPhoneNo(),customer.getEmployeId(),nic);
    }


    public static boolean delete(Customer customer,String nic) throws SQLException, ClassNotFoundException {
        String sql = "Delete FROM Customer WHERE nic = ?";
        return CrudUtil.execute(sql,nic);
    }

    public static ArrayList<Customer> getCustomerData() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from customer");
        while (resultSet.next()){

            customerData.add(new Customer(resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getInt(4),
            resultSet.getString(5)));
        }
            return  customerData;
        }
    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT nic FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }



}
