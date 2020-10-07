/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Login;
import classes.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import utils.SessionUtils;

/**
 *
 * @author aldai
 */
public class DAOUser {

    private static DAOUser dao = null;
    private final Connection con;

    private DAOUser() {
        con = ConnectionSQL.getConnection();
    }

    public static DAOUser getDAO() {
        if (dao == null) {
            dao = new DAOUser();
        }
        return dao;
    }
    
    
    public void deleteUser(int id) throws SQLException{
        PreparedStatement ps = con.prepareStatement("DELETE from users WHERE id=?");
        ps.setInt(1, id);
        ps.execute();
    }

    public void createUser(Login user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password, name, last_name, type) VALUES (?,?,?,?,?)");
        ps.setString(1, user.getUser());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getNombre());
        ps.setString(4, user.getApellidos());
        ps.setInt(5, Integer.parseInt(user.getTipo()));
        ps.execute();
    }

    public void tutoAssign(int user_id, int tutor_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE users SET tutor_id=? WHERE id=?");
        ps.setInt(1, tutor_id);
        ps.setInt(2, user_id);
        ps.execute();
    }

    public void deleteStudent(int student_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE users SET tutor_id=0 WHERE id=?");
        ps.setInt(1, student_id);
        ps.execute();
    }

    public ArrayList<User> getStudents() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE type=2");
        ResultSet rs = ps.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getInt("type"),
                    rs.getString("name"), rs.getString("last_name"), ""));
        }
        return users;
    }

    public ArrayList<User> getTeachers() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE type=1");
        ResultSet rs = ps.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getInt("type"),
                    rs.getString("name"), rs.getString("last_name"), ""));
        }
        return users;
    }

    public ArrayList<User> getTutoredStudents(int tutor_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE tutor_id=?");
        ps.setInt(1, tutor_id);
        ResultSet rs = ps.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getInt("type"),
                    rs.getString("name"), rs.getString("last_name"), ""));
        }
        return users;
    }

    public User getUserById(int id) throws SQLException, Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"), rs.getInt("type"),
                    rs.getString("name"), rs.getString("last_name"), rs.getString("password"));
        }
        throw new Exception("Usuario no encontrado");
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE users SET username = ?,password=?, name=?, last_name=? WHERE id=?");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getLast_name());
        ps.setInt(5, user.getId());
        ps.execute();
    }

    public String login(Login user) throws SQLException, Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password = ?");
        ps.setString(1, user.getUser());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("id", rs.getInt("id"));
            session.setAttribute("username", rs.getString("username"));
            if (rs.getInt("type") == 1) {
                return "tutor_view";
            } else if (rs.getInt("type") == 2) {
                return "user_view";
            } else {
                return "secretary_view";
            }

        }
        throw new Exception("Usuario no encontrado");
    }

}
