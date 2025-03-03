/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import model.Package;
/**
 *
 * @author 11
 */
public class PackageDAO extends DBContext {

    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT \n"
                    + "    t.Transaction_Type_ID,\n"
                    + "    t.Transaction_Type_Name,\n"
                    + "    t.Transaction_Type_Description,\n"
                    + "    t.Transaction_Value,\n"
                    + "    tr.Transaction_Amount,\n"
                    + "    tr.Created_Date\n"
                    + "FROM Transaction_Type t\n"
                    + "JOIN [transaction] tr \n"
                    + "    ON t.Transaction_Type_ID = tr.Transaction_Type_ID\n"
                    + "WHERE tr.Created_Date = (\n"
                    + "    SELECT MAX(tr2.Created_Date)\n"
                    + "    FROM [transaction] tr2\n"
                    + "    WHERE tr2.Transaction_Type_ID = tr.Transaction_Type_ID\n"
                    + ");";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Package p = new Package();
                p.setId(rs.getInt("Transaction_Type_ID"));
                p.setName(rs.getString("Transaction_Type_Name"));
                p.setDescription(rs.getString("Transaction_Type_Description"));
                p.setValue(rs.getInt("Transaction_Value"));
                p.setPrice(rs.getDouble("Transaction_Amount"));
                p.setCreatedDate(rs.getDate("Created_Date"));
                packages.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return packages;
    }
}
