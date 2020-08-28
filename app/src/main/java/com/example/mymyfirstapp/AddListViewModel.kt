package com.example.mymyfirstapp

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.mymyfirstapp.database.AddList
import com.example.mymyfirstapp.database.AddListDao
import com.example.mymyfirstapp.databinding.FragmentAboutmeBinding
import com.example.mymyfirstapp.databinding.FragmentContactBinding
import kotlinx.coroutines.*
import java.lang.StringBuilder

class AddListViewModel(
    val database: AddListDao,
    private val binding:FragmentContactBinding,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val contacts = database.get()
    val contactString = Transformations.map(contacts) { contacts ->
        formatContact(contacts)
    }

    private fun formatContact(contact: List<AddList>): Spanned {
        val sb = StringBuilder()
        sb.apply {
            //append(resources.getString(R.string.title))
            contact.forEach {
                append(it.id)
                append(" : ")
                append(it.name)
                append(", ")
                append(it.phone)
                append("<br>")
            }
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
             HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onContactAdd() {
        uiScope.launch {
            val newContact = AddList()
            newContact.name = binding.editTextTextPersonName.text.toString()
            newContact.phone = binding.editTextTextPersonPhone.text.toString()
            insert(newContact)
        }
    }

    private suspend fun insert(contact: AddList) {
        withContext(Dispatchers.IO) {
            database.insert(contact)
        }
    }

}
