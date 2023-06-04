package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.to.Employe;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeModel {
    public static boolean add(Employe employe) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, employe.getEmployeId(), employe.getName(), employe.getAddress(), employe.getPhoneNo(),employe.getRoleType(),employe.getSalary());
    }

    public static boolean delete(Employe employe, String employeId) throws SQLException, ClassNotFoundException {
        String sql = "Delete FROM employee WHERE employeId = ?";
        return CrudUtil.execute(sql,employeId);
    }

    public static boolean update(Employe employe, String employeId) throws SQLException, ClassNotFoundException {
        String sql = "update employee set name = ? , address = ? , phoneNo = ? , RoleType = ?, salary = ? where employeId = ? ";
        return CrudUtil.execute(sql, employe.getName(),employe.getAddress(), employe.getPhoneNo(),employe.getRoleType(),employe.getSalary(),employeId);
    }

    public static Employe search(String employeId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM employee WHERE employeId = ?";
        ResultSet result = CrudUtil.execute(sql, employeId);

        if(result.next()) {
            return new Employe(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5),
                    result.getDouble(6)
            );
        }
        return null;
    }

    public static ArrayList<Employe> getEmployeData() throws SQLException, ClassNotFoundException {
        ArrayList<Employe> employeData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from employee");
        while (resultSet.next()){

            employeData.add(new Employe(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)));
        }
        return employeData;
    }

}
