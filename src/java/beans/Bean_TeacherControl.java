/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aldai
 */
@ManagedBean(name="bean_teacher_control")
@RequestScoped
public class Bean_TeacherControl {

    /**
     * Creates a new instance of Bean_TeacherControl
     */
    private ArrayList<User> students;
    private ArrayList<User> teachers;
    private int user_id;

    public Bean_TeacherControl() {

        try {
            students = DAO.DAOUser.getDAO().getStudents();
            teachers = DAO.DAOUser.getDAO().getTeachers();
        } catch (SQLException ex) {
            Logger.getLogger(Bean_TeacherControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<User> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<User> students) {
        this.students = students;
    }

    public ArrayList<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<User> teachers) {
        this.teachers = teachers;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
    

    public String delete() {
        try {
            DAO.DAOUser.getDAO().deleteUser(user_id);

        } catch (SQLException ex) {
            Logger.getLogger(Bean_TeacherControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/faces/secretary_view.xhtml";
    }

}
