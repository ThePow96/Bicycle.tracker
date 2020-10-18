package com.example.bicycletracker.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.view.adapter.ContactAdapter
import com.example.bicycletracker.view.interfaces.ActionButton
import kotlinx.android.synthetic.main.contact_layout.*

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_layout)
        buildContactList()
    }

    private fun buildContactList(){
        val contactList = resources.getStringArray(R.array.contactList)
        val contactPhones = resources.getStringArray(R.array.contactPhones)
        val adapter = ContactAdapter(this, contactList, contactPhones)
        contact_list_recycler.adapter = adapter

        adapter.setOnItemClickListener(object : ActionButton {
            override fun buttonPressed(text: String) {
                makePhoneDial(text)
            }
        })
    }

    private fun makePhoneDial(phoneNumber: String){
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(callIntent)
    }
}
