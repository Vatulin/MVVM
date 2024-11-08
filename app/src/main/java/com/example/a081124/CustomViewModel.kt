package com.example.a081124

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel : ViewModel() {
    val repo = ListRepositrey()

    val mutableLDList = MutableLiveData<ArrayList<Game>>()
    val mutableLDKeyword = MutableLiveData<String>()
    val mutableLDListText = MutableLiveData<String>().apply { value = repo.getList().toString() }

    fun search() {
        val keyword = mutableLDKeyword.value
        mutableLDList.value = keyword?.let { repo.getList()?.filterByName(it) }
        mutableLDListText.value = mutableLDList.value.toString()
    }


    fun ArrayList<Game>.filterByName(keyword: String): ArrayList<Game> {
        var newList = ArrayList<Game>()
        if (keyword.isNotEmpty()) {
            for (name in this) {
                if (keyword[0] in name.name) {
                    newList.add(name)
                }
            }
        }
        return newList
    }

    fun ArrayList<Game>.filterByPrice(keyword: String): ArrayList<Game> {
        if (keyword.length > 1) {
            if (keyword[0] == '>') { return this.filter { it -> it.price > (keyword.split('>')[1].toInt()) } as ArrayList<Game>}
            else if (keyword[0] == '=') { return this.filter { it -> it.price == (keyword.split('=')[1].toInt()) } as ArrayList<Game>}
            else if (keyword[0] == '<') { return this.filter { it -> it.price < (keyword.split('<')[1].toInt()) } as ArrayList<Game>}
        }
        return this.filter { false } as ArrayList<Game>
    }

    fun ArrayList<Game>.filterByCompany(keyword: String): ArrayList<Game> {
        return this.filter { it -> it.company == keyword } as ArrayList<Game>
    }
}