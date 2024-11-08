package com.example.a081124

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel : ViewModel() {
    val repo = ListRepositrey()
    private var appContext: Context? = null

    val mutableLDList = MutableLiveData<ArrayList<Game>>()
    val mutableLDKeyword = MutableLiveData<String>()
    val mutableLDListText = MutableLiveData<String>().apply { value = repo.getList().toString() }
    val mutableLDListButtonText = MutableLiveData<String>()

    fun initcontext(context: Context) {
        appContext = context.applicationContext
        mutableLDListButtonText.value = appContext?.getString(R.string.state_of_search_1) // Инициализация здесь
    }

    fun search() {
        val keyword = mutableLDKeyword.value

        if (mutableLDListButtonText.value == appContext?.getString(R.string.state_of_search_1)) {
                mutableLDList.value = keyword?.let { repo.getList()?.filterByName(it) }
                mutableLDListText.value = mutableLDList.value.toString()
        }

        else if (mutableLDListButtonText.value == appContext?.getString(R.string.state_of_search_2)) {
            mutableLDList.value = keyword?.let { repo.getList()?.filterByPrice(it) }
            mutableLDListText.value = mutableLDList.value.toString()
        }

        else if (mutableLDListButtonText.value == appContext?.getString(R.string.state_of_search_3)) {
            mutableLDList.value = keyword?.let { repo.getList()?.filterByCompany(it) }
            mutableLDListText.value = mutableLDList.value.toString()
        }
    }

    fun changeModeOfSearch() {
        if (mutableLDListButtonText.value == appContext?.getString(R.string.state_of_search_1)) {
            mutableLDListButtonText.value = appContext?.getString(R.string.state_of_search_2)
        }

        else if (mutableLDListButtonText.value == appContext?.getString(R.string.state_of_search_2)) {
            mutableLDListButtonText.value = appContext?.getString(R.string.state_of_search_3)
        }

        else if (mutableLDListButtonText.value == appContext?.getString(R.string.state_of_search_3)) {
            mutableLDListButtonText.value = appContext?.getString(R.string.state_of_search_1)
        }
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