package com.appvolution.sharedpreferenceapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.appvolution.sharedpreferenceapp.databinding.ActivityMainBinding

const val MyPREFERENCES = "MyPrefs"
const val FirstName = "firstNameKey"
const val LastName = "lastnameKey"
const val Email = "emailKey"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        binding.ed1.text = sharedPreferences.getString(FirstName, "empty")?.toEditable()
        binding.ed2.text = sharedPreferences.getString(LastName, "empty")?.toEditable()
        binding.ed3.text = sharedPreferences.getString(Email, "empty")?.toEditable()
        binding.save.setOnClickListener {
            val fNAME = binding.ed1.text.toString()
            val lName = binding.ed2.text.toString()
            val eMail = binding.ed3.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply {
                putString(FirstName, fNAME)
                putString(LastName, lName)
                putString(Email, eMail)
            }
            val result = editor.commit()
            Toast.makeText(this, "ThanksForSavingData:$result", Toast.LENGTH_SHORT).show()
        }
    }
    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}