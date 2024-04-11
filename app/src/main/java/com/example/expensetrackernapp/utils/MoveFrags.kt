package com.example.expensetrackernapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MoveFrags {
    companion object
    {
        fun replaceFrags(frameLayoutID : Int, fragManager : FragmentManager, frag : Fragment)
        {
            fragManager.beginTransaction().apply {
                replace(frameLayoutID, frag)
                commit()
            }
        }
    }
}