package com.example.internettest_lab4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt1: Button = findViewById(R.id.btnHTTP)
        val bt2: Button = findViewById(R.id.btnOkHTTP)

        bt1.setOnClickListener() {
            Log.v("TEST1", "OK httpURLconnection")
            Thread {
                val timeDif = measureTimeMillis {
                    useHttpURLConnection()
                }
                Log.d("TIME: ", timeDif.toString())
            }.start()
        }

        bt2.setOnClickListener() {
            Log.v("TEST2", "OK httpOK")
            Thread {
                val timeDif = measureTimeMillis {
                    useHttpOK()
                }
                Log.d("TIME: ", timeDif.toString())
            }.start()

        }
    }
}

@WorkerThread
fun useHttpOK() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val json = response?.body?.string()
                //val txt = (JSONObject(json).getJSONObject("value").get("joke")).toString()

                Log.i("Flickr OkCats", json.toString())
            }
        }
    })
}


@WorkerThread
fun useHttpURLConnection(){
    val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
    val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

    try {
        urlConnection.apply {
            connectTimeout = 10000
            requestMethod = "GET"
            doInput = true
        }

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK){
            Log.d("Flickr cats: ", urlConnection.responseCode.toString())
            return
        }

        val streamReader = InputStreamReader(urlConnection.inputStream)

        var data : String = ""
        streamReader.use{
            data = it.readText()
        }

        Log.d("Flickr cats: ", data)


    } catch (ex : Exception) {
        Log.d("Flickr cats: ", ex.toString())
    } finally {
        urlConnection.disconnect()
    }
}