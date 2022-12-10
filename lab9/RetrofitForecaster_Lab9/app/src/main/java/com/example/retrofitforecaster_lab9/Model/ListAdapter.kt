package com.example.retrofitforecaster_lab9.Model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster_lab9.R
import timber.log.Timber



open class MultipleViewHolder(view: View) : RecyclerView.ViewHolder(view){
    open val time: TextView = view.findViewById(R.id.time)
    open val temp: TextView = view.findViewById(R.id.temp)

    class ViewHolderCold (view: View) : MultipleViewHolder(view) {
        override val time: TextView = view.findViewById(R.id.time)
        override val temp: TextView = view.findViewById(R.id.temp)
    }
    class ViewHolderHot (view: View) : MultipleViewHolder(view) {
        override val time: TextView = view.findViewById(R.id.time)
        override val temp: TextView = view.findViewById(R.id.temp)
    }
}

class ListAdapter(private val context: Context,
                  private val list: ArrayList<MyList>) : RecyclerView.Adapter<MultipleViewHolder>() {

    private val TYPE_HOT = 1
    private val TYPE_COLD = 2

//    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val time: TextView = view.findViewById(R.id.time)
//        val temp: TextView = view.findViewById(R.id.temp)
//    }

    override fun getItemViewType(position: Int): Int {
        if(list.get(position).main?.temp!! <= 0) return TYPE_COLD
        else return TYPE_HOT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
        if (viewType == TYPE_COLD) {
            return MultipleViewHolder.ViewHolderHot(view)
        }
        else {
            return MultipleViewHolder.ViewHolderCold(view)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: MultipleViewHolder, position: Int) {

        holder.time.text = list[position].dt_txt
        holder.temp.text = String.format("%.2f °С", list[position].main?.temp?.minus(272.15))

        holder.itemView.setOnClickListener(){

        }

    }

}