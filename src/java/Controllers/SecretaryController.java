/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author aldai
 */
@ManagedBean(name = "secretary_controller")
@ViewScoped
public class SecretaryController {

    /**
     * Creates a new instance of SecretaryController
     */
    private String url = "/templates/TutoriaControl.xhtml";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void navigate(int url) {
        switch (url) {
            case 0:
                this.url = "/templates/TutoriaControl.xhtml";
                break;
            case 1:
                this.url = "/templates/TeacherControl.xhtml";
                break;
            default:
                throw new AssertionError(url);

        }
    }
}
