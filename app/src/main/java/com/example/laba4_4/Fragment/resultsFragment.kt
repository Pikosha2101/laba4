package com.example.laba4_4.Fragment

import  android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laba4_4.DBHelper
import com.example.laba4_4.R
import com.example.laba4_4.databinding.ResultsFragmentBinding

class resultsFragment : Fragment(R.layout.results_fragment) {
    private var _binding: ResultsFragmentBinding? = null
    private val binding get() = _binding!!
    //private val LOG_TAG = "myLogs"
    private var dbHelper: DBHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResultsFragmentBinding.inflate(inflater, container, false)
        dbHelper = DBHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_resultsFragment_to_themeFragment)
        }

        resTV.text = res()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    fun res() : String {
        dbHelper = DBHelper(requireContext())
        val db: SQLiteDatabase = dbHelper!!.writableDatabase
        val c = db.query("user", null, null, null, null, null, null)
        return if (c.moveToFirst()) {
            val loginColIndex = c.getColumnIndex("login")
            val plusColIndex = c.getColumnIndex("plus")
            val minusColIndex = c.getColumnIndex("minus")
            val multiplicationColIndex = c.getColumnIndex("multiplication")
            val divisionColIndex = c.getColumnIndex("division")

            var res = ""
            do {
                res += "login = ${c.getString(loginColIndex)}: \nсложение = ${c.getString(plusColIndex)}б.,\nвычитание = ${c.getInt(minusColIndex)}б.,\nумножение = ${c.getInt(multiplicationColIndex)}б., \nделение = ${c.getInt(divisionColIndex)}б.\n\n"
            } while (c.moveToNext())
            dbHelper!!.close()
            c.close()
            res
        } else {
            c.close()
            dbHelper!!.close()
            "Пусто!"
        }
    }
}