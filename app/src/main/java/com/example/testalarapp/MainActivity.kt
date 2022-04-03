package com.example.testalarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testalarapp.api.ApiFactory
import com.example.testalarapp.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val userLogin = binding.editTextLogin.text.toString()
            val password = binding.editTextPassword.text.toString()

            val disposable = ApiFactory.apiService.getInfo(userLogin, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           Log.e("TEST_DATA", it.toString())

                    if (it.status == "ok"){

                        binding.textView.setText(R.string.result_success)
                        val code = it.code
                        val intent = Intent(this, ActivityBase::class.java)
                        intent.type = "text/plain"
                        intent.putExtra("code", code)
                        startActivity(intent)

                    } else{
                        binding.textView.setText(R.string.result_failed)
                    }

                },{
                    it.message?.let { it1 -> Log.e("TEST_DATA", it1) }
                })
            compositeDisposable.add(disposable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}