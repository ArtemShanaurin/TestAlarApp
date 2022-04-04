package com.example.testalarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
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
import retrofit2.http.Url

class ActivityBase : AppCompatActivity() {

    lateinit var binding: ActivityBaseBinding
    lateinit var code: String

    var numberPage = 1

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycleBase.layoutManager = LinearLayoutManager(this)

        val intent = intent

        code = intent.getStringExtra("code").toString()

        getDataByPage(numberPage)

    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun getDataByPage(page: Int) {
        val dataBase = code?.let {
            ApiFactory.apiService.getDataBase(it,numberPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (binding.recycleBase.adapter ==null){
                        binding.recycleBase.adapter = it.data?.let { it1 -> RecyclerBaseAdapter(it1, this) }
                    }else{
                        val itemCount = binding.recycleBase.adapter!!.itemCount
                        it.data?.let { it1 ->
                            (binding.recycleBase.adapter as RecyclerBaseAdapter).data.addAll(
                                it1
                            )
                            binding.recycleBase.adapter!!.notifyItemRangeInserted(itemCount, it1.size)
                        }
                    }

                    Log.d("TEST", it.toString())

                },{
                    it.message?.let { it1 -> Log.d("TEST_DATA", it1) }
                })
        }
        compositeDisposable.add(dataBase)
    }
}