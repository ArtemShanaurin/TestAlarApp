package com.example.testalarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.testalarapp.api.ApiFactory
import com.example.testalarapp.api.ApiService
import com.example.testalarapp.database.AppDataBase
import com.example.testalarapp.database.DataOfDataBase
import com.example.testalarapp.databinding.ActivityBaseBinding
import com.example.testalarapp.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_base.*

class ActivityBase : AppCompatActivity() {

    lateinit var binding: ActivityBaseBinding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        var listOfRow = mutableListOf<TextView>()
        listOfRow.add(textViewNameRow1)
        listOfRow.add(textViewNameRow2)
        listOfRow.add(textViewNameRow3)
        listOfRow.add(textViewNameRow4)
        listOfRow.add(textViewNameRow5)
        listOfRow.add(textViewNameRow6)
        listOfRow.add(textViewNameRow7)
        listOfRow.add(textViewNameRow8)
        listOfRow.add(textViewNameRow9)
        listOfRow.add(textViewNameRow10)



        val code = intent.getStringExtra("code")
        val dataBase = code?.let {
            ApiFactory.apiService.getDataBase(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                       Log.d("TEST", it.toString())



                    binding.textViewNameRow1.text = ("${it.data?.get(0)?.name}" + " ${it.data?.get(0)?.country}")
                    binding.textViewNameRow2.text = it.data?.get(1)?.name
                    binding.textViewNameRow2.setOnClickListener {
                        showInfo()
                        val country2 = AppDataBase().data?.get(2)?.country
                    }
                    binding.textViewNameRow3.text = it.data?.get(2)?.name
                    binding.textViewNameRow4.text = it.data?.get(3)?.name
                    binding.textViewNameRow5.text = it.data?.get(4)?.name
                    binding.textViewNameRow6.text = it.data?.get(5)?.name
                    binding.textViewNameRow7.text = it.data?.get(6)?.name
                    binding.textViewNameRow8.text = it.data?.get(7)?.name
                    binding.textViewNameRow9.text = it.data?.get(8)?.name
                    binding.textViewNameRow10.text = it.data?.get(9)?.name



                },{
                    it.message?.let { it1 -> Log.d("TEST_DATA", it1) }
                })
        }
        compositeDisposable.add(dataBase)
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun showInfo() {
        val intent = Intent(this, DetailInfoActivity::class.java)
        startActivity(intent)

    }
}