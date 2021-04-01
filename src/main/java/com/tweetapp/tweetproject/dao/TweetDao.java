package com.tweetapp.tweetproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tweetapp.tweetproject.util.MySqlConnection;

public class TweetDao {

    public void getAllTweets() throws SQLException, ClassNotFoundException {
        Connection con = null;
        String query = "select * from tweets";
        con = MySqlConnection.jdbcConnection();
        Statement st = con.createStatement();
        Statement st2 = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        int flag = 0;
        if (con != null) {
            while (rs.next()) {
                String userEmail = "select email from users where userId ='" + rs.getString("userId") + "'";
                ResultSet rs2 = st2.executeQuery(userEmail);
                rs2.next();
                String emailId = rs2.getString("email");
                System.out.println("emailId:" + " " + emailId + " " + "Tweet:" + " " + rs.getString("tweet"));
                flag = 1;
            }
            if (flag == 0) {
                System.out.println("No Tweets");
            }
        }

    }

    public void addTweet(String email, String tweet) throws SQLException, ClassNotFoundException {
        Connection con = null;
        String query = "select * from users";
        con = MySqlConnection.jdbcConnection();
        Statement st = con.createStatement();
        Statement st1 = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (con != null) {
            while (rs.next()) {
                if (email.equalsIgnoreCase(rs.getString("email"))) {
                    int userId = rs.getInt("userId");
                    String insertQuery = "INSERT INTO tweets (userId,tweet) VALUES ('" + userId + "','" + tweet + "')";
                    int i = st1.executeUpdate(insertQuery);
                    if (i > 0) {
                        System.out.println("Tweeted Successsfully");
                    } else {
                        System.out.println("Error in Posting tweet");
                    }
                }
            }
        }
    }

    public void viewAllMyTweets(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        String query = "select * from users";
        con = MySqlConnection.jdbcConnection();
        Statement st = con.createStatement();
        Statement st1 = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        int flag = 0;
        if (con != null) {
            while (rs.next()) {
                if (email.equalsIgnoreCase(rs.getString("email"))) {
                    int userId = rs.getInt("userId");
                    String selectQuery = "select userId,tweet from tweets where userId ='" + userId + "'";
                    ResultSet rs2 = st1.executeQuery(selectQuery);
                    while (rs2.next()) {
                        System.out.println("Tweet:" + " " + rs2.getString("tweet"));
                        flag = 1;
                    }
                }
            }
            if (flag == 0) {
                System.out.println("You have not tweeted yet");
            }
        }
    }
}
