package top.smallc.picturebrower.net;

/**
 * 上传进度
 * Created by cao on 2016/4/25.
 */
public class UploadManager {
    private static UploadManager uploadManager;
    private UploadProgressListener uploadProgressListener;
    public static UploadManager getInstance(){
        if(uploadManager == null){
            uploadManager = new UploadManager();
        }
        return uploadManager;
    }

    public interface UploadProgressListener{
        public void uploadProgress(int totalCount, int alreadyWriteCount);
        public void overUpload();
    }

    public void setOnUploadListener(UploadProgressListener uploadListener){
        this.uploadProgressListener = uploadListener;
    }

    public void returnProgress(int totalCount,int alreadyWriteCount){
        if(uploadProgressListener != null){
            uploadProgressListener.uploadProgress(totalCount,alreadyWriteCount);
        }
    }

    public void overUpload(){
        if(uploadProgressListener != null){
            uploadProgressListener.overUpload();
        }
    }

}