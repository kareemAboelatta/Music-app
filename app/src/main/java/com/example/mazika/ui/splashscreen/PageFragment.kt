package com.example.mazika.ui.splashscreen
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mazika.R
import com.example.mazika.commen.extension.hideStatusBar
import com.example.mazika.ui.MainActivity

class PageFragment : Fragment() {
    var position : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("POSITION")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = when (position) {
            1 -> {
                R.layout.fragment_splash1
            }
            2 -> {
                R.layout.fragment_splash2
            }
            3 -> {
                R.layout.fragment_splash3
            }
            4 -> {
                R.layout.fragment_splash4
            }
            5 -> {
                R.layout.fragment_splash5
            }
            else -> R.layout.fragment_page_number
        }


        val view=layoutInflater.inflate(layoutId, container, false)

        if (position==6){
            view.findViewById<TextView>(R.id.let_s_go).setOnClickListener {
                requireContext().startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.hideStatusBar()

        when (position) {
            1 -> return
            2 -> return
            3 -> return
            4 -> return
            5 -> return
            6 -> return
            else->{

            }
        }

    }


    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)


    }
}