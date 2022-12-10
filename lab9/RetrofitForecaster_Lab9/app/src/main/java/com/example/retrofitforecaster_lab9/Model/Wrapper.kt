package com.example.retrofitforecaster_lab9.Model

data class Wrapper(val cod: Float?, val message: Float?, val cnt: Float?, val list: List<MyList>?, val city: City?)  {}

data class MyList(val dt: Int?, val main: Main?, val weather: List<Weather>?, val clouds: Clouds?, val wind: Wind?, val visibility: Int?, val pop: Float?, val sys: Sys?, val dt_txt: String?){}
data class Main(val temp: Float?, val feels_like: Float?, val temp_min: Float?, val temp_max: Float?, val pressure: Float?, val sea_level: Float?, val grnd_level: Float?, val humidity: Float?, val temp_kf: Float?){}
data class Weather(val id: Float?, val main: String?, val desctiption: String?, val icon: String?){}
data class Clouds(val all: Float?){}
data class Wind(val speed: Float?, val deg: Float?, val gust: Float?){}
data class Sys(val pod: String?){}

data class City(val id: Float?, val name: String?, val coord: Coord?, val county: String?, val population: Float?, val timezone: Float?, val sunrise: Float?, val sunset: Float?){}
data class Coord(val lat: Float?, val lon: Float?){}
