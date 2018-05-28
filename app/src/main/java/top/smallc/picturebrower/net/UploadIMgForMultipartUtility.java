package top.smallc.picturebrower.net;


import android.annotation.TargetApi;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import static java.lang.System.currentTimeMillis;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.URLConnection.guessContentTypeFromName;
import static java.text.MessageFormat.format;
import static java.util.logging.Level.INFO;
import static java.util.logging.Logger.getLogger;

/**
 * Created by cao on 2016/3/10.
 */
public class UploadIMgForMultipartUtility {
    private static final Logger log = getLogger(UploadIMgForMultipartUtility.class
            .getName());

    private static final String CRLF = "\r\n";
    private static final String CHARSET = "UTF-8";

    private static final int CONNECT_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 10000;

    private final HttpURLConnection connection;
    private final OutputStream outputStream;
    private final PrintWriter writer;
    private final String boundary;

    // for log formatting only
    private final URL url;
    private final long start;

    private UploadManager uploadManager;

    public UploadIMgForMultipartUtility(final URL url, final int timeout) throws IOException {
        start = currentTimeMillis();
        this.url = url;

        boundary = "---------------------------" + currentTimeMillis();

        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        if (timeout < 0) {
            connection.setReadTimeout(READ_TIMEOUT);
        } else {
            connection.setReadTimeout(timeout);
        }
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        outputStream = connection.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, CHARSET),
                true);
    }

    public void addFormField(final String name, final String value) {
        writer.append("--").append(boundary).append(CRLF)
                .append("Content-Disposition: form-data; name=\"").append(name)
                .append("\"").append(CRLF)
                .append("Content-Type: text/plain; charset=").append(CHARSET)
                .append(CRLF).append(CRLF).append(value).append(CRLF);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void addFilePart(final String fieldName, final File files, final int maxCount)
            throws IOException {
        //获取上传图片实例
        uploadManager = UploadManager.getInstance();
        final String fileName = files.getName();
        writer.append("--").append(boundary).append(CRLF)
                .append("Content-Disposition: form-data; name=\"")
                .append(fieldName).append("\"; filename=\"").append(fileName)
                .append("\"").append(CRLF).append("Content-Type: ")
                .append(guessContentTypeFromName(fileName)).append(CRLF)
                .append("Content-Transfer-Encoding: binary").append(CRLF)
                .append(CRLF);

        writer.flush();
        outputStream.flush();
        try (final FileInputStream inputStream = new FileInputStream(files)) {
            int totalCount = inputStream.available();
            final byte[] buffer = new byte[2048];
            int bytesRead;
            int alreadyWrite = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                alreadyWrite += bytesRead;
                outputStream.write(buffer, 0, bytesRead);
                outputStream.flush();
                uploadManager.returnProgress(totalCount, alreadyWrite);
            }
        }
        writer.append(CRLF);
    }

    public void addHeaderField(String name, String value) {
        writer.append(name).append(": ").append(value).append(CRLF);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public byte[] finish() throws IOException {
        writer.append(CRLF).append("--").append(boundary).append("--").append(CRLF);
        writer.close();

        final int status = connection.getResponseCode();
        if (status != HTTP_OK) {
            throw new IOException(format("{0} failed with HTTP status: {1}",
                    url, status));
        }

        try (final InputStream is = connection.getInputStream()) {
            final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            final byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                bytes.write(buffer, 0, bytesRead);
            }
            uploadManager.overUpload();
            log.log(INFO, format("{0} took {4} ms", url, (currentTimeMillis() - start)));
            return bytes.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
}
