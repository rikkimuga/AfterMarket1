package com.kuria.aftermarket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class HomeFragment : Fragment() {
    var mListItems:ListView? = null
    var adapter:CustomAdapter? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root =  inflater.inflate(R.layout.home_fragment, container, false)
        var users:ArrayList<Item> = ArrayList()
        mListItems = root.findViewById(R.id.mListItems)

        //Start adding items to your array list

        users.add(Item(R.drawable.productone,"Metamorphosized","White Converse","Ksh 400"))
        users.add(Item(R.drawable.producttwo,"Wanted Store","Black Prada heels","Ksh 1600"))
        users.add(Item(R.drawable.productthree,"Cozy Thrift Store","Black Doc Martins","Ksh 2900"))
        users.add(Item(R.drawable.productfour,"Maendeleo Kenya","White Dress","Ksh 600"))

        adapter  = CustomAdapter(context,users)
        mListItems!!.adapter = adapter
        return  root
    }


}