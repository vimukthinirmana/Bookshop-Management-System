package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Employe;
import lk.ijse.bookshop.to.Supplier;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public static boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, supplier.getSupplierId(), supplier.getName(), supplier.getEmail(), supplier.getPhoneNo(),supplier.getEmployeId());
    }

    public static boolean delete(Supplier supplier, String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "Delete FROM supplier WHERE supplierId = ?";
        return CrudUtil.execute(sql,supplierId);
    }

    public static boolean update(Supplier supplier, String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "update supplier set name = ? , email = ? , phoneNo = ? , employeId = ? , where supplierId = ? ";
        return CrudUtil.execute(sql, supplier.getName(),supplier.getEmail(), supplier.getPhoneNo(),supplier.getEmployeId(),supplierId);
    }

    public static Supplier search(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM supplier WHERE supplierId = ?";
        ResultSet result = CrudUtil.execute(sql, supplierId);

        if(result.next()) {
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static ArrayList<Supplier> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> supplierData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from supplier");
        while (resultSet.next()){

            supplierData.add(new Supplier(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)));
        }
        return supplierData;
    }
}
