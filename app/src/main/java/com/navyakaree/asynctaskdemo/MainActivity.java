package com.navyakaree.asynctaskdemo;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    String image_url= "http://writm.com/wp-content/uploads/2016/08/Cat-hd-wallpapers.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(image_url);
            }
        });
    }

    class DownloadTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            int fileLength = 0;
            try {
                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
             urlConnection.connect();
                fileLength = urlConnection.getContentLength();
                File new_folder = new File("sdcard/photoaldum"); //to create a folder in the phone to save image
                if(!new_folder.exists()){ // to check if the folder is available or not
                    new_folder.mkdir();
                }

                File input_file = new File(new_folder,"downloaded_image.jpg"); //create another folder inside that folder
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192); //to read data from URL
                byte[] data = new byte[1024];
                int total = 0;
                int count = 0;

                OutputStream outputStream = new FileOutputStream(input_file);// to write the data

                while ((count=inputStream.read(data))!=-1){

                    total+=count;
                    outputStream.write(data, 0, count);
                    int progress = total *100/fileLength;
                    publishProgress(progress);

                }

                inputStream.close();
                outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download Completed";
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Download in progress..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            String path = "sdcard/photoaldum/downloaded_image.jpg";
            imageView.setImageDrawable(Drawable.createFromPath(path));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }
    }

}
