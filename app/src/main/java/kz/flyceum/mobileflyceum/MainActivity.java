package kz.flyceum.mobileflyceum;

import androidx.appcompat.app.AppCompatActivity;
import kz.flyceum.mobileflyceum.net.LoadNews;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private JSONObject jsonNews;
    private JSONArray arrNews, arrDate, arrPicture, arrMainNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadNews news = new LoadNews();
        news.execute("http://flyceum.ru/mobile/get.php");

        try {
            jsonNews = new JSONObject(news.get());
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        TextView tmp = findViewById(R.id.tmpText);
        try {
            arrNews = jsonNews.getJSONArray("news");
            tmp.setText(arrNews.getString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}