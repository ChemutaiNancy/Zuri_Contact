package com.example.contact_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.contact_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpData(binding)
    }

    fun setUpData(binding: ActivityMainBinding){
        binding.contactRv.adapter = adapter
        binding.contactRv.addItemDecoration(DividerItemDecoration(this, GridLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.nameEt)
        val phone = view.findViewById<TextView>(R.id.phoneNumberEt)
        val saveBtn = view.findViewById<Button>(R.id.saveBt)
        phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                saveBtn.isEnabled = p0?.length == 11
//                if (p0?.length == 11){
//                    saveBtn.isEnabled = true
//                } else{
//                    saveBtn.isEnabled = false
//                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        val alertDialog = builder.create()

        saveBtn.setOnClickListener{
            val contact = Contact(name.text.toString(), phone.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContact(contacts)
            alertDialog.dismiss()
        }



        binding.fab.setOnClickListener{
            alertDialog.show()
        }
    }
}