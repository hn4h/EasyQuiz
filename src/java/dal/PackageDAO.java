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
            String sql = "select * from Package order by Package_Value desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Package p = new Package();
                p.setId(rs.getInt("Package_ID"));
                p.setName(rs.getString("Package_Name"));
                p.setDescription(rs.getString("Package_Description"));
                p.setValue(rs.getInt("Package_Value"));
                p.setPrice(rs.getInt("Package_Amount"));
                p.setCreatedDate(rs.getDate("Created_Date"));
                packages.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return packages;
    }

    public List<Package> getAllPackagesForPurchase() {
        List<Package> packages = new ArrayList<>();
        try {
            String sql = "SELECT [Package_Name]\n"
                    + "      ,[Package_Description]\n"
                    + "      ,[Package_Amount]\n"
                    + "  FROM [EasyQuiz].[dbo].[Package]\n"
                    + "  order by Package_Value asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Package p = new Package();
                p.setName(rs.getString("Package_Name"));
                p.setDescription(rs.getString("Package_Description"));
                p.setPrice(rs.getInt("Package_Amount"));
                packages.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return packages;
    }

    public List<Package> getAllPackagesForDashboard() {
        List<Package> packages = new ArrayList<>();
        try {
            String sql = "SELECT [Package_ID], [Package_Name], [Package_Description], [Package_Value], [Package_Amount], [Created_Date] "
                    + "FROM [EasyQuiz].[dbo].[Package] "
                    + "ORDER BY [Package_Value] ASC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Package p = new Package();
                p.setId(rs.getInt("Package_ID"));
                p.setName(rs.getString("Package_Name"));
                p.setDescription(rs.getString("Package_Description"));
                p.setValue(rs.getInt("Package_Value"));
                p.setPrice(rs.getInt("Package_Amount"));
                p.setCreatedDate(rs.getDate("Created_Date"));
                packages.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return packages;
    }
}
