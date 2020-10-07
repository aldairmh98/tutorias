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

@ManagedBean(name = "student_controller")
@ViewScoped
public class StudentController {
    
    private String url = "/templates/tutorias_student.xhtml";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
     
    public void navigate(int url){
        switch(url){
            case 0:
                this.url = "/templates/PersonalData.xhtml";
                break;
            case 1:
                this.url = "/templates/tutorias_student.xhtml";
                break;
            default:
                throw new AssertionError(url);
            
        }
    }
}
