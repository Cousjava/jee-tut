package com.sleightholme.jeetut.validation;

import java.util.Date;
import javax.validation.constraints.*;

/**
 *
 * @author jonathan coustick
 */
public class TestedBean {

    @Min(1)
    int id;  
    
    @NotNull
    String name;
    
    @Pattern(regexp="\\w*[\\w\\+\\.]+@[\\w\\.]+\\w*")
    String email;
    
    @Size(max=200)
    String address;
    
    @Future
    Date resend;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Date getResend() {
        return resend;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setResend(Date resend) {
        this.resend = resend;
    }
    
}
