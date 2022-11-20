package com.example.gson_lab5

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import timber.log.Timber
import timber.log.Timber.Forest.plant
import java.io.IOException
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plant(Timber.DebugTree());

//        val topics = listOf<String>("Education","Finance","Government","Entertainment","Technology","Math","Biology","Physics","Chemistry","Space","Sports","Music","Animal","Countries","Weather","Politics","Traffic","Poverty","Social Media","Internet","Корпус")


        doGetRequest(this)

        Timber.v("TEST1")

    }
}

fun doGetRequest(
    mainActivity: MainActivity
) {

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

                val jsonText = response?.body?.string()
                val gsBuilder = GsonBuilder()
                val gs = gsBuilder.create()
                val wrapper: Wrapper = gs.fromJson(jsonText, Wrapper::class.java)

                val sizeOfJson: Int? = wrapper.photos?.photo?.size

                print5Elements(wrapper, sizeOfJson, gs)


//                for (i in 0 until sizeOfJson)
//                    Timber.d("https://farm${wrapper.photos!!.photo[i].farm}.staticflickr.com/" +
//                            "${wrapper.photos!!.photo[i].server}/" +
//                            "${wrapper.photos!!.photo[i].id}_" +
//                            "${wrapper.photos!!.photo[i].secret}_z.jpg")

                val listPNG = arrayListOf<Drawable>()
                val listLinks = arrayListOf<String>()
                fillLists(listPNG, listLinks, wrapper)

                Timber.d("SIZEEE " + listPNG.size.toString() )

                val recycler_view: RecyclerView = mainActivity.findViewById(R.id.rView)
                val gridLayoutManager = GridLayoutManager(mainActivity,2)
                val gridListAdapter = GridListAdapter(listPNG, listLinks)
                recycler_view.setHasFixedSize(true)

                mainActivity.runOnUiThread(java.lang.Runnable {
                    recycler_view.layoutManager = gridLayoutManager
                    recycler_view.adapter = gridListAdapter
                })

            }
        }
    })
}

fun print5Elements(wrapper: Wrapper, sizeOfJson: Int?, gs: Gson){
    for(i in 0 until sizeOfJson!!)
        if (i % 4 == 0) Timber.d(gs.toJson(wrapper.photos?.photo?.get(i)))
}
fun fillLists(
    listPNG: ArrayList<Drawable>,
    listLinks: ArrayList<String>,
    wrapper: Wrapper
) {
    var limit: Int = 5
    for (i in 0..limit) {
        try {
            var urlString =
                "https://farm${wrapper.photos!!.photo[i].farm}.staticflickr.com/" +
                        "${wrapper.photos!!.photo[i].server}/" +
                        "${wrapper.photos!!.photo[i].id}_" +
                        "${wrapper.photos!!.photo[i].secret}_z.jpg"

            var url = URL(urlString).content as InputStream
            var d = Drawable.createFromStream(url, "src name")

            listPNG.add(d)
            listLinks.add(urlString)
        } catch (ex: java.lang.Exception) {
            Timber.d("ERRRROR: " + "https://farm${wrapper.photos!!.photo[i].farm}.staticflickr.com/" +
                    "${wrapper.photos!!.photo[i].server}/" +
                    "${wrapper.photos!!.photo[i].id}_" +
                    "${wrapper.photos!!.photo[i].secret}_z.jpg")
            limit++
            continue
        }
    }
}