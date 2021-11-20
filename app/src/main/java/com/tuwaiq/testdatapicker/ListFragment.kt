package com.tuwaiq.testdatapicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.testdatapicker.Database.BookingData
import com.tuwaiq.testdatapicker.R

const val KEY = "id"

const val dateFormat = "dd/MM/yyyy"
class ListFragment : Fragment() {

    private lateinit var listRC:RecyclerView
    private val listFrVM by lazy { ViewModelProvider(this).get(ListFragmentViewModel::class.java) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_list, container, false)

        listRC =view.findViewById(R.id.RecyclerView)
        val linearLM =LinearLayoutManager(context)
        listRC.layoutManager= linearLM

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFrVM.liveDataInfo.observe(
            viewLifecycleOwner, Observer {
                updateAD(it)

            }
        )
    }

    private inner class ViewHolderList(view: View):RecyclerView.ViewHolder(view),View.OnClickListener{

        private lateinit var info:BookingData

        private var name:TextView =itemView.findViewById(R.id.dr_name)
        private var descrep:TextView =itemView.findViewById(R.id.dececription)
        private var createDate:TextView=itemView.findViewById(R.id.createDate)


        fun bind(info:BookingData){

            this.info=info

            name.text = info.names
            descrep.text =info.title
            createDate.text = android.text.format.DateFormat.format(dateFormat,info.date)
        }

        init {
           itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (p0==itemView){
                val arg=Bundle()
                arg.putSerializable(KEY,info.id)
                val fragment= BookingFragment()
                fragment.arguments=arg
                activity?.let {
                    it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }

        }


    }

    private inner class AdapterList(var info: List<BookingData>):RecyclerView.Adapter<ViewHolderList>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
            val view =layoutInflater.inflate(R.layout.fragment_item_list,parent,false)

            return ViewHolderList(view)
        }

        override fun onBindViewHolder(holder: ViewHolderList, position: Int) {

            val x=info[position]

            holder.bind(x)

        }

        override fun getItemCount(): Int {
            return info.size
        }

    }
    private fun updateAD(info: List<BookingData>){
        val adapterList =AdapterList(info)
        listRC.adapter=adapterList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

}