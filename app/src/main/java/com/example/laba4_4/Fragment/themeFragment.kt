package com.example.laba4_4.Fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.laba4_4.Adapter.ThemeAdapter
import com.example.laba4_4.DBHelper
import com.example.laba4_4.Listener
import com.example.laba4_4.Model.ThemeModel
import com.example.laba4_4.R
import com.example.laba4_4.databinding.ThemeFragmentBinding

class themeFragment : Fragment(R.layout.theme_fragment),
    Listener<ThemeModel> {
    private var _binding: ThemeFragmentBinding? = null
    private val binding get() = _binding!!

    private val themeAdapter = ThemeAdapter(this)
    private lateinit var but: Button
    private lateinit var et: EditText
    private lateinit var tv: TextView
    private var dbHelper: DBHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ThemeFragmentBinding.inflate(inflater, container, false)

        dbHelper = DBHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        backButton.setOnClickListener{
            findNavController().navigate(R.id.action_themeFragment_to_authorizationFragment)
        }
        resBut.setOnClickListener{
            findNavController().navigate(R.id.action_themeFragment_to_resultsFragment)
        }
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        themeAdapter.setList(
            arrayListOf(
                ThemeModel("Сложение"),
                ThemeModel("Вычитание"),
                ThemeModel("Умножение"),
                ThemeModel("Деление"),
                ThemeModel("Ещё что-то"),
                ThemeModel("И ещё что-то"),
                ThemeModel("Какая-то тема")
            )
        )
        recyclerView.adapter = themeAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    @SuppressLint("SetTextI18n")
    override fun onClick(param: ThemeModel) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.theme_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        but = dialog.findViewById(R.id.butAnswer)
        et = dialog.findViewById(R.id.etAnswer)
        tv = dialog.findViewById(R.id.quest)
        val userLogin = arguments?.getString("userLogin").toString()

        val db: SQLiteDatabase = dbHelper?.writableDatabase as SQLiteDatabase
        when (param.theme){
            "Сложение" -> {
                tv.text = "2+2=?"
                but.setOnClickListener {
                    if (et.text.toString() == "4") {
                        val contentValues = ContentValues()
                        contentValues.put("plus", 1)
                        db.update("user", contentValues, "login=?", arrayOf(userLogin))
                        db.close()
                        Toast.makeText(activity, "Красава!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        val contentValues = ContentValues()
                        contentValues.put("plus", 0)
                        db.update("user", contentValues, "login=?", arrayOf(userLogin))
                        db.close()
                        Toast.makeText(activity, "Не красава!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
            "Вычитание" -> {
                tv.text = "6-1=?"
                but.setOnClickListener{
                    if (et.text.toString() == "5"){
                        val contentValues = ContentValues()
                        contentValues.put("minus", 1)
                        db.update("user", contentValues, "login=?", arrayOf("pikosha"))
                        Toast.makeText(activity,"Красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        val contentValues = ContentValues()
                        contentValues.put("minus", 0)
                        db.update("user", contentValues, "login=?", arrayOf("pikosha"))
                        Toast.makeText(activity,"Не красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
            "Деление" -> {
                tv.text = "60:10=?"
                but.setOnClickListener{
                    if (et.text.toString() == "6"){
                        val contentValues = ContentValues()
                        contentValues.put("multiplication", 1)
                        db.update("user", contentValues, "login=?", arrayOf("pikosha"))
                        Toast.makeText(activity,"Красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        val contentValues = ContentValues()
                        contentValues.put("multiplication", 0)
                        db.update("user", contentValues, "login=?", arrayOf("pikosha"))
                        Toast.makeText(activity,"Не красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
            "Умножение" -> {
                tv.text = "5*5=?"
                but.setOnClickListener{
                    if (et.text.toString() == "25"){
                        val contentValues = ContentValues()
                        contentValues.put("division", 1)
                        db.update("user", contentValues, "login=?", arrayOf("pikosha"))
                        Toast.makeText(activity,"Красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        val contentValues = ContentValues()
                        contentValues.put("division", 0)
                        db.update("user", contentValues, "login=?", arrayOf("pikosha"))
                        Toast.makeText(activity,"Не красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
            else -> {
                but.setOnClickListener{
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }
}