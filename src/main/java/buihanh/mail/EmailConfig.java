/*
 * Copyright (c) 2022.
 * Automation Framework Selenium - Anh Tester
 */

package buihanh.mail;

/**
 * Data for Sending email after execution
 */
public class EmailConfig {

    //OVERRIDE_REPORTS=yes
    //send_email_to_users=yes

    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "587";

    public static final String FROM = "hanh74873@gmail.com";
    public static final String PASSWORD = "hkspoqjkjowgvimu";

    public static final String[] TO = {"hanh74873@gmail.com"};
    public static final String SUBJECT = "DAILY REPORT - AUTOMATION FRAMEWORK";
    public static final String message = "I have some attachments for you.";
}