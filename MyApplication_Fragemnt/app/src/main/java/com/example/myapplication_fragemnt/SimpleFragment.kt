package com.example.myapplication_fragemnt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SimpleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimpleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var radioYes: RadioButton
    private lateinit var radioNo: RadioButton
    private lateinit var radioGroup:RadioGroup
    val YES = 0;
    val NO = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_simple, container, false)

        radioYes= rootView.findViewById(R.id.radio_button_yes)
        radioNo=rootView.findViewById(R.id.radio_button_no)
        radioGroup=rootView.findViewById(R.id.radio_group)

        radioGroup.setOnCheckedChangeListener(this::radioClicked)

        return rootView;
    }

    private fun radioClicked(group:RadioGroup, checkedId:Int){

        when(checkedId) {
            R.id.radio_button_yes -> { (this::changeName)(YES) }
            R.id.radio_button_no -> { (this::changeName)(NO) }
        }
    }
    fun newInstance(): SimpleFragment {
        return SimpleFragment()
    }

    private fun changeName(number:Int){
        when(number){
            0->{
              radioYes.setText("Liked")
                radioNo.visibility=View.INVISIBLE

            }
            1->{
                radioNo.setText("Not Liked")
                radioYes.visibility=View.INVISIBLE
              ;
            }
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SimpleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SimpleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

