package com.homeandroid.movie.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homeandroid.movie.databinding.ItemBinding
import com.homeandroid.movie.model.Search

class Recyclerview_Adapter(private val List_Movie: List<Search>?) :RecyclerView.Adapter<Recyclerview_Adapter.MyViewHolder>()

{


     var clickItem:Click_Item?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)    }

    override fun getItemCount()= List_Movie?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item= List_Movie?.get(position)
        holder.binding.data=item
        holder.binding.item.setOnClickListener {

            item?.imdbID?.let { it1 -> clickItem?.item_movie(it1) }
        }
    }
   interface Click_Item{
       fun item_movie(id:String)
   }
    fun OnClickListener(clickItem: Click_Item){
        this.clickItem=clickItem
    }

    class MyViewHolder(val binding:ItemBinding) : RecyclerView.ViewHolder(binding.root)

}