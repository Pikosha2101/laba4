package com.example.laba4_4.Fragment

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laba4_4.DBHelper
import com.example.laba4_4.R
import com.example.laba4_4.databinding.RegistrationFragmentBinding

class registrationFragment : Fragment(R.layout.registration_fragment) {
    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!
    private val LOG_TAG = "myLogs"
    private var dbHelper: DBHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        dbHelper = DBHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        backButton.setOnClickListener{
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
        button.setOnClickListener{
            create()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun create(){

        if (binding.editTextTextPassword.text.toString() == binding.editTextTextPassword2.text.toString()) {
            val db: SQLiteDatabase = dbHelper?.writableDatabase as SQLiteDatabase
            val log: String = binding.login.text.toString()
            val pas: String = binding.editTextTextPassword.text.toString()
            if (check("mytable", log, pas, db)) {
                    Toast.makeText(requireContext(), "Аккаунт уже существует!", Toast.LENGTH_LONG).show()
            } else {
                val cv = ContentValues()
                cv.put("login", log)
                cv.put("password", pas)
                cv.put("count", 0)
                val rowId = db.insert("mytable", null, cv)
                Log.d(LOG_TAG, "row inserted, ID = $rowId")
                Toast.makeText(requireContext(), "Аккаунт создан!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "Пароли не совпадают!", Toast.LENGTH_LONG).show()
        }
    }

    fun check(tableName: String, value1: String, value2: String, db: SQLiteDatabase): Boolean {
        val str =
            "Select * from $tableName where login = $value1 and password = $value2"
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