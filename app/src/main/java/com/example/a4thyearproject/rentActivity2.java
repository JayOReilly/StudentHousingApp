package com.example.a4thyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class rentActivity2 extends AppCompatActivity {
    //List 1
    ListView listRss;
    ArrayList<String> titles;
    ArrayList<String> links;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent2);

        listRss = (ListView) findViewById(R.id.daftChannel);
        titles = new ArrayList<String>();
        links = new ArrayList<String>();







        listRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(links.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        new rentActivity2.ProcessBackground().execute();
    }
    public InputStream getInputStream(URL url)
    {
        try
        {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }
    public class ProcessBackground extends AsyncTask<Integer, Void, Exception>
    {


        Exception exception = null;
        ProgressDialog progressDialog = new ProgressDialog(rentActivity2.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading . . . .");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... integers) {
            try {
                URL url = new URL("http://api.daft.ie/v2/wsdl.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;

                int eventType = xpp.getEventType();


                while(eventType!= XmlPullParser.END_DOCUMENT)
                {
                    if(eventType== XmlPullParser.START_TAG)
                    {
                        if(xpp.getName().equalsIgnoreCase("item")  )
                        {
                            insideItem = true;

                        }
                        else if (xpp.getName().equalsIgnoreCase("Title"))
                        {
                            if (insideItem )
                            {
                                titles.add(xpp.nextText());


                            }
                            else if (xpp.getName().equalsIgnoreCase("Links") )
                            {
                                if (insideItem )
                                {
                                    links.add(xpp.nextText());

                                }
                            }

                        }
                        else if(eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                        {
                            insideItem = false;

                        }
                    }
                    eventType = xpp.next();

                }

            } catch(MalformedURLException e){
                exception = e;

            } catch (XmlPullParserException e){
                exception = e;

            } catch (IOException e){

            }


            return exception;
        }




        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(rentActivity2.this, android.R.layout.simple_list_item_1, titles);
            listRss.setAdapter(adapter);

            progressDialog.dismiss();

        }
    }
}

