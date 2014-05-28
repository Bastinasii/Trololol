/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

/**
 *
 * @author Alex
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect 
{
    static String host = "jdbc:mysql://localhost:3306/antivirus";
    static String userName = "java";
    static String passwd = "test";
    
    static Connection con;
    
    public static void open()
    {
        try
        {
            con = DriverManager.getConnection(host, userName, passwd);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void close()
    {
        try
        {
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static String getCrc (String path)
    {
        String crc = new String();
        String sql = "SELECT CRC FROM FISIERE WHERE PATH = ?";
        ResultSet result;
        
        try
        {
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            
            st.setString(1, path);
            result = st.executeQuery();
            crc = result.toString();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return crc;
    }
    
    public static void insertFile(String nume, String extensie, String data_c, String data_m, String path, String size, String crc)
    {
        String sql = "INSERT INTO fisiere (NUME, EXTENSIE, DATA_C, DATA_M, PATH, SIZE, CRC) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try
        {
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            
            st.setString(1, nume);
            st.setString(2, extensie);
            st.setString(3, data_c);
            st.setString(4, data_m);
            st.setString(5, path);
            st.setString(6, size);
            st.setString(7, crc);
            st.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean fileExists (String path)
    {
        boolean exists = false;
        
        String sql = "SELECT COUNT(*) FROM FISIERE WHERE PATH = ?";
        ResultSet result;
        
        try
        {
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            
            st.setString(1, path);
            result = st.executeQuery();
            if (result.getInt(1) != 0) exists = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }       
        return exists;
    }
    
    public static boolean fileIsModified (String path, String crc)
    {
        boolean isModified = false;
        
        String sql = "SELECT * FROM FISIERE WHERE PATH = ?";
        ResultSet result;
        
        try
        {
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            
            st.setString(1, path);
            result = st.executeQuery();
            if (!(result.getString(7).equals(crc))) isModified = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }       
        return isModified;
    }
}