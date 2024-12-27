package com.example.loginapp.Presenter

import android.graphics.ColorSpace.Model
import com.example.loginapp.Model.ModelMainActivity
import com.example.loginapp.View.ViewMainActivity

class PresenterMainActivity(
    private val view:ViewMainActivity,
    private val model:ModelMainActivity
) {
    fun OnCreatePresenter(){
        view.OnClickHandler(model.getId())

    }
}