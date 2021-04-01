package com.tweetapp.tweetproject;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tweetapp.tweetproject.dao.TweetDao;
import com.tweetapp.tweetproject.dao.UserDao;
import com.tweetapp.tweetproject.model.UserModel;

public class App {
    public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException {
        int ch, ch2;
        String firstName;
        String lastName;
        String gender;
        Date dob;
        String email;
        String password;
        String newpassword;
        String tweet;
        do {
            System.out.println("************Tweet application**************");
            System.out.print("Select the option\n");
            System.out.println("1.Register  2.Login  3.Exit");
            Scanner sh = new Scanner(System.in);
            UserModel user = new UserModel();
            UserDao userDao = new UserDao();
            TweetDao tweetDao = new TweetDao();
            String regex = "^(.+)@(.+)$"; 
            Pattern pattern = Pattern.compile(regex);  
            ch = sh.nextInt();
            switch (ch) {
            case 1:
                System.out.println("Enter Your Details");
                System.out.print("First name:");
                sh.nextLine();
                firstName = sh.nextLine();
                System.out.print("Last name:");
                lastName = sh.nextLine();
                System.out.print("Gender:");
                gender = sh.nextLine();
                System.out.print("DOB: In yyyy-mm-dd ");
                String date = sh.nextLine();
                dob = Date.valueOf(date);
                System.out.print("EmailId:");
                email = sh.nextLine(); 
                Matcher matcher = pattern.matcher(email); 
                if(matcher.matches() == false){
                  System.out.println("Invalid Email!!! Try Registering Again");
                  continue;
                }
                System.out.print("Password:");
                password = sh.nextLine();
                user = new UserModel(firstName, lastName, gender, dob, email, password);
                System.out.println("1.Register 2.Cancel");
                ch2 = sh.nextInt();
                switch (ch2) {
                case 1:
                    userDao.registerUser(user);
                    break;
                case 2:
                    System.exit(0);
                    break;
                }
                break;
            case 2:
                System.out.println("Enter the Details");
                sh.nextLine();
                System.out.print("Email Id:");
                email = sh.nextLine();
                Matcher matcher1 = pattern.matcher(email); 
                if(matcher1.matches() == false){
                   System.out.println("Invalid Email!! Try Login Again");
                   System.exit(0);
                }
                System.out.print("Password:");
                password = sh.nextLine();
                int val = userDao.loginUser(email, password);
                if (val == 0) {
                    System.out.println("Invalid username and password");
                } else if (val == 1) {
                    System.out.println("Logged In successfully");
                    do {
                        System.out.println("1.View All My Tweets 2.Tweet 3.View All Registered User Tweets 4.Update Password 5.Exit");
                        ch2 = sh.nextInt();
                        switch (ch2) {
                        case 1:
                            tweetDao.viewAllMyTweets(email);
                            break;
                        case 2:
                            System.out.println("Add your tweet:");
                            sh.nextLine();
                            tweet = sh.nextLine();
                            tweetDao.addTweet(email, tweet);
                            break;
                        case 3:
                            tweetDao.getAllTweets();
                            break;
                        case 4:
                            System.out.print("Enter the old password:");
                            sh.nextLine();
                            password = sh.nextLine();
                            System.out.print("Enter the new password:");
                            newpassword = sh.nextLine();
                            userDao.changePassword(password, newpassword, email);
                            break;
                        case 5:
                            System.exit(0);
                        }
                    } while (ch2 != 5);
                }
                break;

            case 3:
                sh.close();
                System.exit(0);
            default:
                System.out.println("Select 1 or 2 option");
            }
        } while (ch != 3);

    }
}
