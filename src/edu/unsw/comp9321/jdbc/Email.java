package edu.unsw.comp9321.jdbc;

public class Email {
	final static private String emailFrom = "unswcomp9321@gmail.com";
    private String emailTo;
    private String emailSubject;
    private String emailText;

    public Email() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getEmailTo() {
        return emailTo;
    }
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
    public String getEmailSubject() {
        return emailSubject;
    }
    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }
    public String getEmailText() {
        return emailText;
    }
    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }
    public String getEmailFrom() {
        return emailFrom;
    }
}
