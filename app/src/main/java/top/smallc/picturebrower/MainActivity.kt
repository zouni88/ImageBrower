package top.smallc.picturebrower

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import top.smallc.picturebrower.view.HomeActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun home(view: View) {
        view.toString()
        val intent = Intent(this,HomeActivity().javaClass)
        startActivity(intent)
    }

    fun abc(view:View ) {
        view.toString()
        val view:ImageView = super.findViewById(R.id.iv_media)
        val gliderUrl = GlideUrl("http://i.meizitu.net/2018/05/12c10.jpg", Headers {
            val header = HashMap<String, String>()
            //不一定都要添加，具体看原站的请求信息
            header["Referer"] = "http://i.meizitu.net"
            header["User-Agent"] = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)"
            header["Referer"] = "http://www.mzitu.com"
            header
        })

        //显示图片
        Glide.with(view.getContext()).load(gliderUrl).into(view)
    }
}
