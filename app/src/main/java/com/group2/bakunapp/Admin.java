package com.group2.bakunapp;

public class Admin {
    String fullname, adminemail, adminpassword,contact,isadmin;

    public Admin(){

    }

    public Admin(String fullname, String adminemail, String adminpassword, String contact, String isadmin) {
        this.fullname = fullname;
        this.adminemail = adminemail;
        this.adminpassword = adminpassword;
        this.contact = contact;
        this.isadmin = isadmin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }
}
