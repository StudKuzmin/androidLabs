package com.example.retrofitforecaster_lab9

import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster_lab9.Model.ListAdapter
import com.example.retrofitforecaster_lab9.Model.MyList
import com.example.retrofitforecaster_lab9.Model.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

import timber.log.Timber
import kotlin.collections.ArrayList

import com.example.retrofitforecaster_lab9.Controller.Controller

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())


        val controller = Controller(this)
        controller.send()
    }
}