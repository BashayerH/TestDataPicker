package com.tuwaiq.testdatapicker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.tuwaiq.testdatapicker.Database.BookingData
import com.tuwaiq.testdatapicker.ListFragment
import java.util.*

const val KEY_DATE = "date"

class BookingFragment : Fragment() , BookingDataPicker.DataPickerCallback {

   private val bookingFrVM by lazy { ViewModelProvider(this).get(BookingViewModel::class.java) }

private lateinit var info:BookingData

  private  lateinit var dateBtn: Button
  private  lateinit var moreInfoBtn:EditText
  private  lateinit var drNamesText: EditText
  private lateinit var addBtn:Button




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        dateBtn = view.findViewById(R.id.date)
        moreInfoBtn= view.findViewById(R.id.moerInfo)
        drNamesText =view.findViewById(R.id.enterDr)
        addBtn=view.findViewById(R.id.addbtn)

    return view
    }

    override fun onStart() {
        super.onStart()

        dateBtn.setOnClickListener {
            val args = Bundle()
            args.putSerializable(KEY_DATE,info.date)
            val dataPicker=BookingDataPicker()
            dataPicker.arguments=args
            dataPicker.setTargetFragment(this,0)
            dataPicker.show(this.parentFragmentManager,"data picker")
        }

        addBtn.setOnClickListener {
            bookingFrVM.added(info)
            val fragment = ListFragment()
            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView,fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        val nameDrWatcher =object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               info.names = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
        drNamesText.addTextChangedListener(nameDrWatcher)

        val infoWatcher =object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              info.title = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
        moreInfoBtn.addTextChangedListener(infoWatcher)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        info= BookingData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookingFrVM.infoLiveData.observe(
            viewLifecycleOwner,{
                it?.let {
                    info=it
                    drNamesText.setText(it.names)
                    moreInfoBtn.setText(it.title)
                    dateBtn.visibility=it.date.time.toInt()
                }
            }
        )
    }

    override fun onDateSelected(date: Date) {
      info.date= date
        dateBtn.visibility= date.time.toInt()
    }

    override fun onStop() {
        super.onStop()
        bookingFrVM.saveUpdate(info)
    }

}