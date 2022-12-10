//package com.example.retrofitforecaster_lab9.Model
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.retrofitforecaster_lab9.R
//
//
//sealed class DataModel {
//    data class Header(
//        val bgColor: Int,
//        val title: String
//    ) : DataModel()
//    data class Family(
//        val name: String,
//        val relationship: String
//    ) : DataModel()
//    data class Friend(
//        val name: String,
//        val gender: String
//    ) : DataModel()
//    data class Colleague(
//        val name: String,
//        val organization: String,
//        val designation: String
//    ) : DataModel()
//}
//
//open class MultipleViewHolder(view: View) : RecyclerView.ViewHolder(view){
//    class ViewHolderCold (view: View) : MultipleViewHolder(view) {
//        val time: TextView = view.findViewById(R.id.time)
//        val temp: TextView = view.findViewById(R.id.temp)
//    }
//    class ViewHolderHot (view: View) : MultipleViewHolder(view) {
//        val time: TextView = view.findViewById(R.id.time)
//        val temp: TextView = view.findViewById(R.id.temp)
//    }
//}
//class DataAdapter(private val context: Context,
//                  private val list: ArrayList<MyList>) : RecyclerView.Adapter<MultipleViewHolder>() {
//    private val TYPE_HOT = 1
//    private val TYPE_COLD = 2
//
//
////    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
////
////    }
//
//
//    override fun getItemViewType(position: Int): Int {
//        if(list.get(position).main?.temp!! <= 0) return TYPE_COLD
//        else return TYPE_HOT
//    }
//    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): MultipleViewHolder {
//        if (viewType == TYPE_COLD) {
//            val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
//            return MultipleViewHolder.ViewHolderCold(view)
//        }
//        else {
//            val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
//            return MultipleViewHolder.ViewHolderHot(view)
//        }
//
//    }
//
//    override fun onBindViewHolder(holder: MultipleViewHolder, position: Int
//    ) {
//        holder.time.text = list[position].dt_txt
//        holder.temp.text = String.format("%.2f °С", list[position].main?.temp?.minus(272.15))
//    }
//
//    override fun getItemCount(): Int = list.count()
//
////    fun setData(data: List<DataModel>) {
////        adapterData.apply {
////            clear()
////            addAll(data)
////        }
////    }
//}