/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Tutoria;
import classes.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author aldai
 */
@ManagedBean(name = "tutors_data")
public class Tutors_data {

    /**
     * Creates a new instance of Tutors_data
     */
    private ArrayList<User> teachers;
    private int teacher_id = 99;
    private String userMessage = "";
    private String msg;
    private String loginMessage = "";
    private User currentUser;
    private ArrayList<Tutoria> tutorias;

    public Tutors_data() {
        if (utils.SessionUtils.getSession().getAttribute("id") != null) {
            int id = Integer.parseInt(utils.SessionUtils.getSession().getAttribute("id").toString());
            try {
                currentUser = DAO.DAOUser.getDAO().getUserById(id);
                tutorias = DAO.DAOTutorias.getDAO().tutoriasByTutor(id);
                teachers = DAO.DAOUser.getDAO().getTutoredStudents(id);
            } catch (Exception ex) {
                Logger.getLogger(Students_data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<User> teachers) {
        this.teachers = teachers;
    }

    public void deleteStudent() {
        if (currentUser != null) {
            try {
                DAO.DAOUser.getDAO().deleteStudent(teacher_id);
            } catch (SQLException ex) {
                Logger.getLogger(Students_data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save() {
        try {
            DAO.DAOUser.getDAO().updateUser(this.currentUser);
        } catch (SQLException ex) {
            Logger.getLogger(Students_data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Tutoria> getTutorias() {
        return tutorias;
    }
    
    

    public void setTutorias(ArrayList<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

}
