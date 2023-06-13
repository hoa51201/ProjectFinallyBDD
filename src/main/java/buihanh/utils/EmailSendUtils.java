package buihanh.utils;
import buihanh.mail.EmailAttachmentsSender;
import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static buihanh.mail.EmailConfig.*;

public class EmailSendUtils {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public static void sendEmail() {

        if (FrameworkConstants.SEND_EMAIL_TO_USERS.trim().equalsIgnoreCase(FrameworkConstants.YES)) {
            System.out.println("****************************************");
            System.out.println("Send Email - START");
            System.out.println("****************************************");

            String messageBody = getTestCasesCountInFormat();

            //attachments
            String[] attachFiles = new String[3];
            attachFiles[0] = "ZipReport.zip";
            attachFiles[1] = "ZipVideo.zip";
            attachFiles[2] = "ZipLog.zip";

            System.out.println("File name: " + "ZipReport.zip");
            System.out.println("File name: " + "ZipVideo.zip");
            System.out.println("File name: " + "ZipLog.zip");

            try {
                EmailAttachmentsSender.sendEmailWithAttachments(SERVER, PORT, FROM, PASSWORD, TO, SUBJECT, messageBody, attachFiles);

                System.out.println("****************************************");
                System.out.println("Email sent successfully.");
                System.out.println("Send Email - END");
                System.out.println("****************************************");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }

    }

    private static String getTestCasesCountInFormat() {

    	return "<html>\n"
    			+ "<body>\n"
    			+ "    <table class=\"container\" style=\"padding-top:5px\">\n"
    			+ "        <tr align=\"center\">\n"
    			+ "            <td colspan=\"4\">\n"
    			+ "                <div style=\"font-size: 20px;font-weight: 700\">\n"
    			+ "                    DAILY PROJECT PROGRESS - PROJECT DETAIL\n"
    			+ "                </div>\n"
    			+ "                <div style=\"font-size: 18px;font-weight: 700; color: cadetblue\">\n"
    			+ "                    Tester: Bui Thi Hong Hanh\n"
    			+ "                </div>\n"
    			+ "            </td>\n"
    			+ "        </tr>\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px;font-weight: 700\">Project name:</td>\n"
    			+ "            <td>Automation test</td>\n"
    			+ "        </tr>\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px;font-weight: 700\">Datetime: </td>\n"
    			+ "            <td>"+dateFormat.format(new Date())+"</td>\n"
    			+ "        </tr>\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px;font-weight: 700\">1. Actual Task & Progress:</td>\n"
    			+ "        </tr>\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px\">Task: Sign in, Sign up, Clients management</td>\n"
    			+ "        </tr>\n"
    			+ "    </table>\n"
    			+ "    \n"
    			+ "    <table style=\"padding-top:5px\">\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px; font-weight: 700\" align=\"left\">2. Issues & action:</td>\n"
    			+ "            <td style=\"width: 190px\"></td>\n"
    			+ "        </tr>\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px\">N/A</td>\n"
    			+ "        </tr>\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px; font-weight: 700\" align=\"left\">3. Next plan:</td>\n"
    			+ "            <td style=\"width: 190px\"></td>\n"
    			+ "        </tr>\n"
    			+ "        <tr>\n"
    			+ "            <td style=\"font-size: 18px\">N/A</td>\n"
    			+ "            \n"
    			+ "        </tr>\n"
    			+ "    </table>\n"
    			+ "</body>\n"
    			+ "</html>";
    }

}
