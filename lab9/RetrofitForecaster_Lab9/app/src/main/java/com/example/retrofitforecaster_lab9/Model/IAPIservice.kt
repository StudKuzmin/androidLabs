package com.example.retrofitforecaster_lab9.Model

import retrofit2.Call
import retrofit2.http.GET

interface IAPIservice {
    @GET("forecast?lat=54.21&lon=30.28&appid=e01f49fa1945221fd71219295eff249e")
    fun getForecast(): Call<Wrapper>
}