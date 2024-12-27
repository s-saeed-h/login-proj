package com.example.loginapp.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginapp.AndroidWrapper.ActUtils
import com.example.loginapp.Model.ModelMainActivity
import com.example.loginapp.Presenter.PresenterMainActivity
import com.example.loginapp.View.ViewMainActivity
import com.example.loginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),ActUtils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val View = ViewMainActivity(this,this)
        setContentView(View.binding.root)
        val presenter = PresenterMainActivity(View, ModelMainActivity(this))
        presenter.OnCreatePresenter()
    }

    override fun finished() {
       finish()
    }
}