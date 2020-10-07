/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Tutoria;
import classes.User;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aldai
 */
@ManagedBean(name = "bean_tutoria")
@ViewScoped
public class Bean_tutoria {

    /**
     * Creates a new instance of Tutoria
     */
    private Tutoria tutoria;
    private ArrayList<Tutoria> tutorias;
    private int tutoria_id = 0;
    private ArrayList<User> teachers;
    private int day = 0, month = 0, year = 0;

    public Bean_tutoria() {
        tutoria = new Tutoria();
        tutorias = new ArrayList<>();
        teachers = new ArrayList<>();

        try {
            tutorias = DAO.DAOTutorias.getDAO().getAll();
            teachers = DAO.DAOUser.getDAO().getTeachers();
        } catch (SQLException ex) {
            Logger.getLogger(Bean_tutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTutoria_id() {
        return tutoria_id;
    }

    public ArrayList<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<User> teachers) {
        this.teachers = teachers;
    }

    public void setTutoria_id(int tutoria_id) {
        this.tutoria_id = tutoria_id;
    }

    public ArrayList<Tutoria> getTutorias() {
        return tutorias;
    }

    public void setTutorias(ArrayList<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public Tutoria getTutoria() {
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }

    public void send() throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
        tutoria.setDate(sqlDate);
        if (tutoria_id != 0) {

            try {
                DAO.DAOTutorias.getDAO().update(tutoria);
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            } catch (SQLException ex) {
                Logger.getLogger(Bean_tutoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                DAO.DAOTutorias.getDAO().insertTutoria(tutoria);
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            } catch (SQLException ex) {
                Logger.getLogger(Bean_tutoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tutoria = new Tutoria();
        tutoria_id = 0;

    }

    public String delete() {
        try {
            DAO.DAOTutorias.getDAO().deleteTutoria(tutoria_id);
            tutoria_id = 0;
        } catch (SQLException ex) {
            Logger.getLogger(Bean_tutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/faces/secretary_view.xhtml?faces-redirect=true";
    }

    public void prepareToUpdate() {
        try {
            tutoria = DAO.DAOTutorias.getDAO().getTutoria(tutoria_id);
            Calendar cal = Calendar.getInstance();
            cal.setTime(tutoria.getDate());
            day = cal.get(Calendar.DAY_OF_MONTH);
            month = cal.get(Calendar.MONTH)+1;
            year = cal.get(Calendar.YEAR);
        } catch (SQLException ex) {
            Logger.getLogger(Bean_tutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
