package studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import studyandroid.it.vietung.amthucbonphuong.DTO.ItemNews;
import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.adapters.RSSFeedAdapter;

public class KienThucAmthucActivity extends Activity {

    private ListView listMainView;
    private String tagname;
    private String tesst;
    private ProgressDialog pDialog;
    private boolean check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintuc_amthuc);



        new Load_listNews().execute("http://dinhduong.com.vn/rss/kien-thuc.rss");

    }

    class Load_listNews extends AsyncTask<String,Void,InputStream> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //tạo 1 progressdialog
            pDialog = new ProgressDialog(KienThucAmthucActivity.this);
            //cài đặt tin nhắn cho progressdialog
            pDialog.setMessage("Loading.........");
            //hiển thị progressdialog
            pDialog.show();
        }

        @Override
        protected InputStream doInBackground(String... params) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            InputStream stream = null;

            URL url = null;
            try {
                url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.connect();
                stream = con.getInputStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return stream;
        }

        @Override
        protected void onPostExecute(InputStream stream) {
            super.onPostExecute(stream);

            ArrayList<ItemNews> arrItems = new ArrayList<ItemNews>();
            XmlPullParserFactory factory = null;
            XmlPullParser parser = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                parser = factory.newPullParser();

            parser.setInput(stream,"UTF-8");
            tesst = parser.toString();

            int eventType = parser.getEventType();
            ItemNews item = new ItemNews();
            String text = "";
                Log.d("SSSS:","Da chay");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            item = new ItemNews();
                            check = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
//                            Log.d("info", text);
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            arrItems.add(item);
                            check = false;
                        } else
                        {
                            if (check) {
                                if (tagname.equalsIgnoreCase("title")) {
                                    item.setTitle(text);
                                    Log.d("info", text);
                                } else if (tagname.equalsIgnoreCase("description")) {
                                    item.setDescription(text);

                                } else if (tagname.equalsIgnoreCase("link")) {
                                    item.setLink(text);
                                } else if (tagname.equalsIgnoreCase("pubDate")) {
                                    item.setPubdate(text);
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                stream.close();
                eventType = parser.next();

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


            pDialog.dismiss();
            try
            {
                listMainView = (ListView) findViewById(R.id.listMainView);
                RSSFeedAdapter adater = new RSSFeedAdapter(KienThucAmthucActivity.this,R.layout.item_news_layout,arrItems);
                listMainView.setAdapter(adater);
            }catch (Exception e)
            {
                Log.e("error",e.getMessage());
            }
        }
    }

}
