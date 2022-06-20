package jdbcEx;

import java.sql.*;
import java.util.*;

public class demo {
    public void menuone(){
        System.out.println("1. Add product\n2. Edit product" +
                "\n3. Delete product\n4. View all product (default sort by Name of product)" +
                "\n5. Search product by id\n6. Search product by name\n7. End");
    }
    public static Connection getConnectionone() throws  SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/dbtest";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        return connection;
    }
    public static void createProductone()  throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection conn = getConnectionone();
        String query = "insert into product2 value(?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        System.out.println("nhập ID: ");
        int id = sc.nextInt();
        preparedStatement.setInt(1,id);
        System.out.println("Nhập name: ");
        String name = sc.next();
        preparedStatement.setString(2,name);
        System.out.println("Nhập desc: ");
        String desc = sc.next();
        preparedStatement.setString(3,desc);
        System.out.println("Nhập price: ");
        double price = sc.nextDouble();
        preparedStatement.setDouble(4,price);
        int rowInserted = preparedStatement.executeUpdate();
        if (rowInserted > 0){
            System.out.println("Create thanh cong");
        }
    }
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        demo demo = new demo();
        Connection connection = getConnectionone();
        if(connection != null){
            System.out.println("ket noi thanh cong");
        }
        while (true) {
            demo.menuone();
            int chon = sc.nextInt();
            switch (chon){
                case 1:
                    createProductone();
                    break;
                case 2:
                    updateDataProductone();
                    break;
                case 3:
                    deleteDataProductone();
                    break;
                case 4:
                    readDataProductone();
                    break;
                case 5:
                    searchProductById();
                    break;
                case 6:
                    searchProductByName();
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }
    public static void readDataProductone() throws SQLException{
        Connection connection = getConnectionone();
        String query = "select * from product2 ORDER BY proName DESC";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String desc = resultSet.getString(3);
            double price = resultSet.getDouble(4);
            System.out.println("id: " + id  + " | name: " + name + " | desc: " + desc  +" | price: " + price);
        }
    }
    public static void searchProductById() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Connection connection = getConnectionone();
        String query = "SELECT * from product2 WHERE id = ?";
        PreparedStatement pe  = connection.prepareStatement(query);
        System.out.println("Nhập id cần tìm: ");
        int id2 = sc.nextInt();
        pe.setInt(1,id2);
        ResultSet resultSet = pe.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString("proName");
            String desc = resultSet.getString("proDesc");
            double price = resultSet.getDouble("price");
            System.out.println("id: " + id  + " | name: " + name + " | desc: " + desc  +" | price: " + price);
        }
    }
    public static void searchProductByName() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Connection connection = getConnectionone();
        String query = "SELECT * from product2 WHERE proName = ?";
        PreparedStatement pe = connection.prepareStatement(query);
        System.out.println("Nhập Name cần tìm: ");
        String id2 = sc.next();
        pe.setString(1,id2);
        ResultSet resultSet = pe.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString("proName");
            String desc = resultSet.getString("proDesc");
            double price = resultSet.getDouble("price");
            System.out.println("id: " + id  + " | name: " + name + " | desc: " + desc  +" | price: " + price);
        }
    };
    public static void updateDataProductone() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = getConnectionone();
        String query = "update product2 set proName = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Nhập tên cần đổi : ");
        String name = sc.next();
        System.out.println("Nhập vị trí id: ");
        int id = sc.nextInt();
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,id);
        int rowUpdate = preparedStatement.executeUpdate();
        if (rowUpdate > 0){
            System.out.println("update thanh cong");
        }
    }
    public static void deleteDataProductone() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = getConnectionone();
        String query = "DELETE from product2 WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Nhập id cần xóa :");
        int id = sc.nextInt();
        preparedStatement.setInt(1,id);
        int rowDelete = preparedStatement.executeUpdate();
        if (rowDelete > 0){
            System.out.println("Xoa id Thanh cong");
        }
    }
}
