package com.kuria.aftermarket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(var context: Context?, var data:ArrayList<Item>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtName:TextView
        var mTxtProduct:TextView
        var mItemPic:ImageView
        var mPrice:TextView
        init {
            this.mTxtName = row?.findViewById(R.id.mtvName) as TextView
            this.mTxtProduct = row?.findViewById(R.id.mtvItem) as TextView
            this.mItemPic = row?.findViewById(R.id.imageView) as ImageView
            this.mPrice = row?.findViewById(R.id.mTvPrice) as TextView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.item_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Item = getItem(position) as Item
        viewHolder.mTxtName.text = item.user_name
        viewHolder.mTxtProduct.text = item.product_name
        viewHolder.mItemPic.setImageResource(item.image)
        viewHolder.mPrice.text = item.price
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}