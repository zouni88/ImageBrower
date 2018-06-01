package top.smallc.picturebrower.net.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.net.ApiBase;
import top.smallc.picturebrower.net.CustomHttpUtils;
import top.smallc.picturebrower.net.utils.BaseResponse;
import top.smallc.picturebrower.net.utils.UrlConstant;

/**
 *
 * Created by cao on 2018/05/29
 */
public class ApiDetail extends ApiBase {
    private Context mContext;
    private TreeMap<String,Object> map  = new TreeMap<>();
    private TreeMap<String,String> headers  = new TreeMap<>();

    public ApiDetail(Context context, int id){
        this.mContext = context;
        map.put("id",id);

    }

    public static class ApiDetailResponse extends BaseResponse {
        public List<Item> items;
    }

    public ApiDetailResponse getList(){
        BaseResponse br = CustomHttpUtils.getInstance(mContext).getResponseForGet(UrlConstant.URL_DETAIL, headers,map, UrlConstant.TIMEOUT);
        ApiDetailResponse response = new ApiDetailResponse();
        response.setRetCode(br.getRetCode());
        response.setRetInfo(br.getRetInfo());
        if(response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK){
            JSONObject json = null;
            try {
                json = new JSONObject(br.getContent());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Item>>() {
                }.getType();
                response.items = gson.fromJson(json.get("data").toString(), type);
            } catch (Exception e) {
                e.printStackTrace();
                response.setRetCode(BaseResponse.RET_HTTP_STATUS_ERROR);
                try {
                    response.setRetInfo(json.get("data").toString());
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return response;
    }

}
