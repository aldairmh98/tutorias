/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.Tutoria;
import classes.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aldai
 */
public class DAOTutorias {

    private static DAOTutorias dao = null;
    private final Connection con;

    private DAOTutorias() {
        con = ConnectionSQL.getConnection();
    }

    public static DAOTutorias getDAO() {
        if (dao == null) {
            dao = new DAOTutorias();
        }
        return dao;
    }

    public void insertTutoria(Tutoria tutoria) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO `tutorias`(`room`, `building`, `date`, `time`, `group`, `tutor_id`) VALUES (?,?,?,?,?,?)");
        ps.setInt(1, tutoria.getRoom());
        ps.setInt(2, tutoria.getBuilding());
        ps.setDate(3, tutoria.getDate());
        ps.setInt(4, tutoria.getTime());
        ps.setInt(5, tutoria.getGroup());
        ps.setInt(6, tutoria.getTeacher_id());
        ps.execute();
    }

    public void deleteTutoria(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM tutorias WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public Tutoria getTutoria(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tutorias WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Tutoria t = new Tutoria(rs.getInt("id"), 
                    rs.getInt("room"), 
                    rs.getInt("building"), 
                    rs.getDate("date"), 
                    rs.getInt("time"), 
                    rs.getInt("group"));
            t.setTeacher_id(rs.getInt("tutor_id"));
            return t;        
        }
        return null;
    }

    public void update(Tutoria tutoria) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE `tutorias` SET "
                + "`room`=?,`building`=?,`date`=?,`time`=?,`group`=?,`tutor_id`=? WHERE id = ?");
        ps.setInt(1, tutoria.getRoom());
        ps.setInt(2, tutoria.getBuilding());
        ps.setDate(3, tutoria.getDate());
        ps.setInt(4, tutoria.getTime());
        ps.setInt(5, tutoria.getGroup());
        ps.setInt(6, tutoria.getTeacher_id());
        ps.setInt(7, tutoria.getId());
        ps.execute();
    }

    public ArrayList<Tutoria> tutoriasByTutor(int tutor) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tutorias WHERE tutor_id=?");
        ps.setInt(1, tutor);
        ResultSet rs = ps.executeQuery();
        ArrayList<Tutoria> tutorias = new ArrayList<>();
        while (rs.next()) {
            tutorias.add(new Tutoria(rs.getInt("id"), rs.getInt("room"), rs.getInt("building"),
                    rs.getDate("date"), rs.getInt("time"), rs.getInt("group")));
        }
        return tutorias;
    }

    public ArrayList<Tutoria> tutoriasByUser(int user_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tutorias WHERE tutor_id= (SELECT tutor_id from users WHERE id = ?)");
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Tutoria> tutorias = new ArrayList<>();
        while (rs.next()) {
            tutorias.add(new Tutoria(rs.getInt("id"), rs.getInt("room"), rs.getInt("building"),
                    rs.getDate("date"), rs.getInt("time"), rs.getInt("group")));
        }
        return tutorias;
    }

    public ArrayList<Tutoria> getAll() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tutorias");
        ResultSet rs = ps.executeQuery();
        ArrayList<Tutoria> tutorias = new ArrayList<>();
        while (rs.next()) {
            tutorias.add(new Tutoria(rs.getInt("id"), rs.getInt("room"), rs.getInt("building"),
                    rs.getDate("date"), rs.getInt("time"), rs.getInt("group")));
        }
        return tutorias;
    }
}
