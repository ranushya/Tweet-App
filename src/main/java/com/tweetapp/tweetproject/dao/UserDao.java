package com.tweetapp.tweetproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tweetapp.tweetproject.model.UserModel;
import com.tweetapp.tweetproject.util.MySqlConnection;

public class UserDao {
    public void registerUser(UserModel user) throws SQLException, ClassNotFoundException {
        Connection con = null;
        int flag = 0;
        String query = "select * from users";
        con = MySqlConnection.jdbcConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (con != null) {
            while (rs.next()) {
                if (user.getEmailId().equalsIgnoreCase(rs.getString("email"))) {
                    System.out.println("Email Id already exists");
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                String insertQuery = "INSERT INTO USERS (FirstName,LastName,gender,dob,email,password) VALUES ('"
                        + user.getFirstName() + "','" + user.getLastName() + "','" + user.getGender() + "','"
                        + user.getDob() + "','" + user.getEmailId() + "','" + user.getPassword() + "')";
                int i = st.executeUpdate(insertQuery);
                if (i > 0) {
                    System.out.println("Registered Successfully");
                } else {
                    System.out.println("Error while registering Try again");
                }
            }
        }

    }

    public int loginUser(String emailId, String pass) throws SQLException, ClassNotFoundException {
        Connection con = null;
        int flag = 0;
        String query = "select * from users";
        con = MySqlConnection.jdbcConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (con != null) {
            while (rs.next()) {
                if (emailId.equalsIgnoreCase(rs.getString("email"))
                        && pass.equalsIgnoreCase(rs.getString("password"))) {
                    flag = 1;
                    break;
                }
            }
        }
        return flag;
    }

    public void changePassword(String password, String newpassword, String email)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        int flag = 0;
        String query = "select * from users";
        con = MySqlConnection.jdbcConnection();
        Statement st = con.createStatement();
        Statement st1 = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (con != null) {
            while (rs.next()) {
                if (email.equalsIgnoreCase(rs.getString("email"))
                        && password.equalsIgnoreCase(rs.getString("password"))) {
                    String updateQuery = "update users set password = '" + newpassword + "'" + "where email = '" + email
                            + "'";
                    int i = st1.executeUpdate(updateQuery);
                    if (i > 0) {
                        System.out.println("Password updated sucessfully");
                        flag = 1;
                    } else {
                        System.out.println("Error in updating the password");
                        flag = 1;
                    }
                }
            }
            if (flag == 0) {
                System.out.println("Incorrect Old Password");
            }
        }
    }
}
