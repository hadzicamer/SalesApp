package com.example.salesapp

import android.app.Activity
import android.content.Context
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.itemview.view.*
import java.util.ArrayList

class ItemAdapter(var context: Context, var list:ArrayList<Item>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v:View=LayoutInflater.from(context).inflate(R.layout.itemview,parent,false)
        return ItemHolder(v)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(list[position].name,list[position].price,list[position].photo,list[position].id)
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        fun bind(s:String,p:Double,u:String,_itemID:Int){
            itemView.txtItemName.text=s
            itemView.txtItemPrice.text=p.toString()
            var web:String="http://192.168.1.3/eCommerce/PHPFiles/pictures/"+u
            web=web.replace(" ","%20")
            Picasso.get().load(web).into(itemView.itemPhoto)

            itemView.itemAddPhoto.setOnClickListener {
                UserInfo.itemID=_itemID
                var obj=QtyFragment()
                var manager=(itemView.context as FragmentActivity).supportFragmentManager // = show manager
            obj.show(manager,"Qty")



            }
        }

    }
}