/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Package;

/**
 *
 * @author 11
 */
public class PackageDAO extends DBContext {

    public Package getPackageByName(String name) {
        String sql = "select * from Package\n"
                + "where Package_Name = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Package p = new Package();
                    p.setId(rs.getInt("Package_ID"));
                    p.setName(rs.getString("Package_Name"));
                    p.setDescription(rs.getString("Package_Description"));
                    p.setValue(rs.getInt("Package_Value"));
                    p.setPrice(rs.getInt("Package_Amount"));
                    p.setCreatedDate(rs.getDate("Created_Date"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

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
            String sql = "select * from Package order by Package_Value asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Package p = new Package();
                p.setName(rs.getString("Package_Name"));
                p.setDescription(rs.getString("Package_Description"));
                p.setValue(rs.getInt("Package_Value"));
                p.setPrice(rs.getInt("Package_Amount"));
                p.setIsActive(rs.getBoolean("Is_Active"));
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
            String sql = "SELECT [Package_ID], [Package_Name], [Package_Description], [Package_Value], [Package_Amount], [Is_Active], [Created_Date] "
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
                p.setIsActive(rs.getBoolean("Is_Active"));
                p.setCreatedDate(rs.getDate("Created_Date"));
                packages.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return packages;
    }

    public boolean addPackage(Package p) {
        try {
            String sql = "INSERT INTO [EasyQuiz].[dbo].[Package] ([Package_Name], [Package_Description], [Package_Value], [Package_Amount]) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setString(2, p.getDescription());
            stm.setInt(3, p.getValue());
            stm.setInt(4, p.getPrice());
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean updatePackage(Package p) {
        try {
            String sql = "UPDATE [EasyQuiz].[dbo].[Package] "
                    + "SET [Package_Name] = ?, [Package_Description] = ?, [Package_Value] = ?, [Package_Amount] = ? "
                    + "WHERE [Package_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setString(2, p.getDescription());
            stm.setInt(3, p.getValue());
            stm.setInt(4, p.getPrice());
            stm.setInt(5, p.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void activePackage(int id) {
        try {
            String sql = "UPDATE [EasyQuiz].[dbo].[Package] "
                    + "SET [Is_Active] = 1 "
                    + "WHERE [Package_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deactivePackage(int id) {
        try {
            String sql = "UPDATE [EasyQuiz].[dbo].[Package] "
                    + "SET [Is_Active] = 0 "
                    + "WHERE [Package_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
