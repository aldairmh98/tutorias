/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author aldai
 */
@ManagedBean(name = "login")
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    private String userMessage = "";
    private String user;
    private String password;
    private String msg;
    private String nombre;
    private String apellidos;
    private String tipo;
    private String loginMessage = "";

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String login(boolean isLogging) {
        if (isLogging) {
            try {
                return DAO.DAOUser.getDAO().login(this);
            } catch (SQLException ex) {
                userMessage = "Usuario ya existe con esta matricula";
            } catch (Exception ex) {
                loginMessage = "Usuario o contraseña incorrectos";
            }

            return "login";
        }
        try {
            DAO.DAOUser.getDAO().createUser(this);
            return DAO.DAOUser.getDAO().login(this);
        } catch (SQLException ex) {
            userMessage = "Usuario ya existe con esta matricula";
        } catch (Exception ex) {
        }
        return "login";
    }

}
