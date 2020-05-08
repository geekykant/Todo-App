package com.paavam.todoapp.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.paavam.todoapp.R

class OnBoardingFragment2 : Fragment() {

    private lateinit var textViewDone: TextView
    private lateinit var textViewBack: TextView
    private lateinit var onOptionClick: OnOptionClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onOptionClick = context as OnOptionClick
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.on_boarding_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view: View) {
        textViewDone = view.findViewById(R.id.textViewNext)
        textViewBack = view.findViewById(R.id.textViewBack)
        clickListener()
    }

    private fun clickListener() {
        textViewBack.setOnClickListener { onOptionClick.onOptionBack() }
        textViewDone.setOnClickListener() {
            onOptionClick.onOptionDone()
        }
    }

    interface OnOptionClick {
        fun onOptionBack()
        fun onOptionDone()
    }
}