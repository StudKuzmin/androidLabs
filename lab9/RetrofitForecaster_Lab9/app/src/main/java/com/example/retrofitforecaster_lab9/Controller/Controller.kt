package com.example.retrofitforecaster_lab9.Controller

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster_lab9.MainActivity
import com.example.retrofitforecaster_lab9.Model.*
import com.example.retrofitforecaster_lab9.Model.RetrofitClient
import com.example.retrofitforecaster_lab9.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class Controller(mainActivity: MainActivity) {
    private var apiservice: IAPIservice
    private var mainActivity: MainActivity

    init {
        apiservice = RetrofitClient
            .getClient("https://api.openweathermap.org/data/2.5/")
            .create(IAPIservice::class.java)

        this.mainActivity = mainActivity
    }

    fun send(){
        apiservice.getForecast().enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                Timber.i("RESPONSE IS ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! $t")
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                //Timber.i("RESPONSE IS SUCC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ${response.body()}")
                val recyclerView: RecyclerView = mainActivity.findViewById(R.id.rView)
                val adapter = ListAdapter(mainActivity, response.body()!!.list as ArrayList<MyList>)

                mainActivity.runOnUiThread(java.lang.Runnable {
                    recyclerView.layoutManager = LinearLayoutManager(mainActivity)
                    recyclerView.adapter = adapter
                })
            }
        })
    }


}