package buihanh.utils;
import org.zeroturnaround.zip.ZipUtil;
import buihanh.helpers.Helpers;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static buihanh.utils.FrameworkConstants.*;

public class ZipUtils {
	public static final String LOG_REPORT_FOLDER_PATH = "ExportLog";
    private ZipUtils() {
        super();
    }
    /* Make Zip file of Extent Reports in Project Root folder */
    public static void zip() {
        zipVideo();
        //zipImage();
        zipLog();
        zipReport();
    }
    public static void zipVideo() {
        if (ZIP_FOLDER.toLowerCase().trim().equals(YES)) {
        	 ZipUtil.pack(new File(VIDEO_REPORT_FOLDER_PATH), new File("ZipVideo.zip"));
             Log.info("Zipped video successfully !!");
        }
    }
    public static void zipImage() {
        if (ZIP_FOLDER.toLowerCase().trim().equals(YES)) {
        	 ZipUtil.pack(new File(IMAGE_REPORT_FOLDER_PATH), new File("ZipImage.zip"));
             Log.info("Zipped image successfully !!");
        }
    }
    public static void zipLog() {
        if (ZIP_FOLDER.toLowerCase().trim().equals(YES)) {
        	ZipUtil.pack(new File(LOG_REPORT_FOLDER_PATH), new File("ZipLog.zip"));
            Log.info("Zipped log successfully !!");
        }
    }
    public static void zipReport() {
        if (ZIP_FOLDER.toLowerCase().trim().equals(YES)) {
        	ZipUtil.pack(new File(Helpers.getCurrentDir()+ "Reports"), new File("ZipReport.zip"));
            Log.info("Zipped report successfully !!");
        }
    }
    public static void zipFolder(String FolderPath, String ZipName) {
        ZipUtil.pack(new File(FolderPath), new File(ZipName + ".zip"));
        Log.info("Zipped " + FolderPath + " successfully !!");
    }

    public static void zipFile(String FilePath, String ZipName) {
        String sourceFile = FilePath;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ZipName + ".zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);
            FileInputStream fis = null;
            fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }

            zipOut.close();
            fis.close();
            fos.close();

            Log.info("Zipped " + FilePath + " successfully !!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unZip(String FileZipPath, String FolderOutput) {
        ZipUtil.unpack(new File(FileZipPath), new File(FolderOutput));
        Log.info("Unzipped " + FileZipPath + " successfully !!");
    }

    public static void unZipFile(String FileZipPath, String FolderOutput) {
        try {
            String fileZip = FileZipPath;
            File outputDir = new File(FolderOutput);

            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(outputDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            Log.info("Unzipped " + FileZipPath + " successfully !!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) {
        try {
            File destFile = new File(destinationDir, zipEntry.getName());
            String destDirPath = destinationDir.getCanonicalPath();
            String destFilePath = destFile.getCanonicalPath();
            if (!destFilePath.startsWith(destDirPath + File.separator)) {
                throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
            }
            return destFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
