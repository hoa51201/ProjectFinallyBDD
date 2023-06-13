package buihanh.report;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import buihanh.helpers.Helpers;
import buihanh.utils.Log;
import static buihanh.utils.FrameworkConstants.*;
import java.io.File;

public class TelegramManager {
	private static String Token = TELEGRAM_TOKEN;
	// https://api.telegram.org/bot6092050575:AAGHcnI92jT4LVZKobs7KRIYYffQRy5eAEQ/getUpdates

	private static String ChatId = TELEGRAM_CHATID;
	private static TelegramBot bot = new TelegramBot(Token);
	private static File input = new File(EXTENT_REPORT_FILE_PATH);
	public static boolean sendFilePath() {
		File[] filePath = new File[3];
		filePath[0] = new File(Helpers.getCurrentDir() + "ZipReport.zip");
		filePath[1] = new File(Helpers.getCurrentDir() + "ZipVideo.zip");
		filePath[2] = new File(Helpers.getCurrentDir() + "ZipLog.zip");
		boolean success = false;
		try {
			for (int i = 0; i < filePath.length; i++) {
				SendDocument request = new SendDocument(ChatId, filePath[i]).parseMode(ParseMode.HTML)
						.disableNotification(true);
				SendResponse sendResponse = bot.execute(request);
				boolean ok = sendResponse.isOk();
				success = ok;
				if (ok != true) {
					Message message = sendResponse.message();
					Log.warn("Message response from Telegram: " + message);
				}
			}
			
		} catch (Exception e) {
			Log.error("Error Send Report HTML to Telegram: " + e.getMessage());
		}
		return success;
	}

	public static void sendReportPath() {
		if (SEND_REPORT_TO_TELEGRAM.toLowerCase().trim().equals(YES)) {
			boolean Success = false;
			try {
				SendDocument request = new SendDocument(ChatId, input).parseMode(ParseMode.HTML)
						.disableNotification(true);
				SendResponse sendResponse = bot.execute(request);
				boolean ok = sendResponse.isOk();
				Success = ok;
				if (ok != true) {
					Message message = sendResponse.message();
					Log.warn("Message response from Telegram: " + message);
				}
			} catch (Exception e) {
				Log.error("Error Send Report HTML to Telegram: " + e.getMessage());
			}
		}
	}

	// chỗ này check nếu gửi thành công thì xóa file luôn
//    public static boolean deleteReportFile() {
//        boolean success = false;
//        success = sendReportPath();
//        if (success == true) {
//            input.delete();
//        }
//        return success;
//    }

	public static boolean sendMessageText() {
		String messageText = "Send report!";
		SendMessage request = new SendMessage(ChatId, messageText);
		SendResponse sendResponse = bot.execute(request);
		boolean ok = sendResponse.isOk();
		if (ok == true) {
			Log.info("Send message to Telegram: " + messageText);
		} else {
			Log.info("Send message to Telegram: " + ok);
		}
		return ok;
	}

}
