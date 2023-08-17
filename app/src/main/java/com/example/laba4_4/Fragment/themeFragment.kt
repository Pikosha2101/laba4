package com.example.laba4_4.Fragment

import android.app.Dialog
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ThemeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        backButton.setOnClickListener{
            findNavController().navigate(R.id.action_themeFragment_to_authorizationFragment)
        }
        resBut.setOnClickListener{

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


    override fun onClick(param: ThemeModel) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.theme_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        but = dialog.findViewById(R.id.butAnswer)
        et = dialog.findViewById(R.id.etAnswer)
        tv = dialog.findViewById(R.id.quest)

        when (param.theme){
            "Сложение" -> {
                tv.text = "2+2=?"
                but.setOnClickListener {
                    if (et.text.toString() == "4") {
                        Toast.makeText(activity, "Красава!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(activity, "Не красава!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
            "Вычитание" -> {
                tv.text = "6-1=?"
                but.setOnClickListener{
                    if (et.text.toString() == "5"){
                        Toast.makeText(activity,"Красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(activity,"Не красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
            "Деление" -> {
                tv.text = "60:10=?"
                but.setOnClickListener{
                    if (et.text.toString() == "6"){
                        Toast.makeText(activity,"Красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(activity,"Не красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
            "Умножение" -> {
                tv.text = "5*5=?"
                but.setOnClickListener{
                    if (et.text.toString() == "25"){
                        Toast.makeText(activity,"Красава!",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
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