package tutorial.cs5551.com.translateapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslateActivity extends AppCompatActivity implements OnItemSelectedListener {

    String API_URL = "https://api.fullcontact.com/v2/person.json?";
    String API_KEY = "b29103a702edd6a";
    String sourceText;
    TextView outputTextView;
    Context mContext;
    HashMap<String,String> lang=new HashMap<String,String>();
    String itemsel,destlang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        outputTextView = (TextView) findViewById(R.id.txt_Result);
        //creating key alue pairs
        lang.put("Telugu","te");lang.put("Tamil","ta");lang.put("Spanish","es");lang.put("urudu","ur");
        lang.put("Azerbaijan", "az");
        lang.put("Malayalam", "ml");
        lang.put("Albanian", "sq");
        lang.put("Maltese", "mt");
        lang.put("Amharic", "am");
        lang.put("Macedonian", "mk");
        lang.put("Maori", "mi");
        lang.put("Arabic", "ar");
        lang.put("Marathi", "mr");
        lang.put("Armenian", "hy");
        lang.put("Mari", "mhr");
        lang.put("Afrikaans", "af");
        lang.put("Mongolian", "mn");
        lang.put("Basque", "eu");
        lang.put("German", "de");
        lang.put("Bashkir", "ba");
        lang.put("Nepali", "ne");
        lang.put("Belarusian", "be");
        lang.put("Bengali", "bn");
        lang.put("Norwegian", "no");
        lang.put("Punjabi", "pa");
        lang.put("Burmese", "my");
        lang.put("Papiamento", "pap");
        lang.put("Bulgarian", "bg");
        lang.put("Persian", "fa");
        lang.put("Bosnian", "bs");
        lang.put("Polish", "pl");
        lang.put("Welsh", "cy");
        lang.put("Portuguese", "pt");
        lang.put("Hungarian", "hu");
        lang.put("Romanian", "ro");
        lang.put("Vietnamese", "vi");
        lang.put("Russian", "ru");
        lang.put("Haitian", "ht");
        lang.put("Cebuano", "ceb");
        lang.put("Galician", "gl");
        lang.put("Serbian", "sr");
        lang.put("Dutch", "nl");
        lang.put("Sinhala", "si");
        lang.put("Hill Mari", "mrj");
        lang.put("Slovakian", "sk");
        lang.put("Greek", "el");
        lang.put("Slovenian", "sl");
        lang.put("Georgian", "ka");
        lang.put("Swahili", "sw");
        lang.put("Gujarati", "gu");
        lang.put("Sundanese", "su");
        lang.put("Danish", "da");
        lang.put("Tajik", "tg");
        lang.put("Hebrew", "he");
        lang.put("Thai", "th");
        lang.put("Yiddish", "yi");
        lang.put("Tagalog", "tl");
        lang.put("Indonesian", "id");
        lang.put("Tamil", "ta");
        lang.put("Irish", "ga");
        lang.put("Tatar", "tt");
        lang.put("Italian", "it");
        lang.put("Telugu", "te");
        lang.put("Icelandic", "is");
        lang.put("Turkish", "tr");
        lang.put("Udmurt", "udm");
        lang.put("Spanish", "es");
        lang.put("Kazakh", "kk");
        lang.put("Uzbek", "uz");
        lang.put("Kannada", "kn");
        lang.put("Ukrainian", "uk");
        lang.put("Catalan", "ca");
        lang.put("Urdu", "ur");
        lang.put("Kyrgyz", "ky");
        lang.put("Finnish", "fi");
        lang.put("Chinese", "zh");
        lang.put("French", "fr");
        lang.put("Korean", "ko");
        lang.put("Hindi", "hi");
        lang.put("Xhosa", "xh");
        lang.put("Croatian", "hr");
        lang.put("Khmer", "km");
        lang.put("Czech", "cs");
        lang.put("Laotian", "lo");
        lang.put("Swedish", "sv");
        lang.put("Latin", "la");
        lang.put("Scottish", "gd");
        lang.put("Latvian", "lv");
        lang.put("Estonian", "et");
        lang.put("Lithuanian", "lt");
        lang.put("Esperanto", "eo");
        lang.put("Luxembourgish", "lb");
        lang.put("Javanese", "jv");
        lang.put("Malagasy", "mg");
        lang.put("Japanese", "ja");
        lang.put("Malay", "ms");
        List<String> ls=new ArrayList<String>(lang.keySet());
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> langadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ls);
        langadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(langadapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void translateText(View v) {
        TextView sourceTextView = (TextView) findViewById(R.id.txt_Email);

        sourceText = sourceTextView.getText().toString();
        String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                "key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e." +
                "c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=" + sourceText +"&" +
                "lang=en-"+destlang+"&[format=plain]&[options=1]&[callback=set]";//The API service URL
        final String response1 = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText(convertedText);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());

        }

    }
    public void logout(View v){
        Intent redirect = new Intent(TranslateActivity.this, LoginActivity.class);
        startActivity(redirect);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        itemsel=adapterView.getItemAtPosition(i).toString();
        destlang=lang.get(itemsel).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
