package com.example.ritesh.networkinfo.Beans;

/**
 * Created by ritesh on 15/2/17.
 */

public class BeanMainCategory {

    private String Catid;
    private String CatName;
    private String CatPassword;
    private String CatMobile;
    private String CatStatus;
    private String CatRegas;
    private String CatPhoto;
    private String CatResult;

    public String getId() {
        return Catid;
    }

    public void setId(String id) {
        this.Catid = id;
    }

    public String getUsername() {
        return CatName;
    }

    public void setUsername(String username) {
        this.CatName = username;
    }

    public String getPassword() {
        return CatPassword;
    }

    public void setPassword(String Password) {
        this.CatPassword = Password;
    }

    public String getMobile() {
        return CatMobile;
    }

    public void setMobile(String Mobile) {
        this.CatMobile = Mobile;
    }

    public String getStatus() {
        return CatStatus;
    }

    public void setStatus(String Status) {
        this.CatStatus = Status;
    }

    public String getRegas() {
        return CatRegas;
    }

    public void setRegas(String Regas) {
        this.CatRegas = Regas;
    }

    public String getPhoto() {
        return CatPhoto;
    }

    public void setPhoto(String Photo) {
        this.CatPhoto = Photo;
    }

    public String getResult() {
        return CatResult;
    }

    public void setResult(String Result) {
        this.CatResult = Result;
    }

}
