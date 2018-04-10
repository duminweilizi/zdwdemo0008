package url;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/1/13/013.
 */

public class GetURL {
    public static String getData(String path){
        String data = "";
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            int i = urlConnection.getResponseCode();
            if (i == 200){
                InputStream inputStream = urlConnection.getInputStream();
                int len = 0;
                byte[] b = new byte[1024*1024];
                while ((len = inputStream.read(b))!=-1){
                    String s = new String(b, 0, len);
                    data += s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
