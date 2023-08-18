package com.example.laba4_4.Fragment

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laba4_4.DBHelper
import com.example.laba4_4.R
import com.example.laba4_4.databinding.AuthorizationFragmentBinding

class authorizationFragment : Fragment(R.layout.authorization_fragment) {
    private var _binding: AuthorizationFragmentBinding? = null
    private val binding get() = _binding!!
    private var dbHelper: DBHelper? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthorizationFragmentBinding.inflate(inflater, container, false)
        dbHelper = DBHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        button.setOnClickListener{
            input(bundle)
        }

        button2.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun input(bundle : Bundle){
        if (binding.login.text.isEmpty() || binding.password.text.isEmpty()){
            Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_LONG).show()
        } else{
            if (check()) {
                /*val bundle = Bundle().apply {
                    putString("userLogin", binding.login.text.toString())
                }
                themeFragment().arguments = bundle
                val fragmentManager = requireActivity().supportFragmentManager*/


                bundle.putString("userLogin", binding.login.text.toString())

                findNavController().navigate(R.id.action_authorizationFragment_to_themeFragment, bundle)
            } else{
                Toast.makeText(requireContext(), "Неверный логин или пароль!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun check(): Boolean {
        val db: SQLiteDatabase = dbHelper?.readableDatabase as SQLiteDatabase
        val log: String = binding.login.text.toString()
        val pas: String = binding.password.text.toString()

        val str = "Select * from user where login = '$log' and password = '$pas'"
        val cursor = db.rawQuery(str, null)
        return if (cursor.count <= 0) {
            //аккаунта нет
            cursor.close()
            false
        } else {
            cursor.close()
            true
        }
    }
}