package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;


public class AccountDAO {
    
   

    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next() && account.getUsername()!="" && account.getPassword().length()>=4){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(),account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    public Account uLogin(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username=? AND password=?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            
             ResultSet pResultSet = preparedStatement.executeQuery();
            if(pResultSet.next()){
                return new Account(account.getAccount_id()+1, account.getUsername(), account.getPassword());
             }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
        
    }


    




}
