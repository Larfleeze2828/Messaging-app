package com.example.messapp


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private val contactArray: MutableList<ContactData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        read()
        val recyclerView = findViewById<RecyclerView>(R.id.contact_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(contactArray)
        recyclerView.adapter = adapter



    }

    @SuppressLint("Range")
    fun read() {
        val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null, null)
        while (contacts?.moveToNext()!!) {
            val name = contacts?.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val obj = ContactData()
            obj.name = name
            contactArray.add(name)
        }
        contacts.close()
    }

}

