package com.example.loginapp.View

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.example.loginapp.AndroidWrapper.ActUtils
import com.example.loginapp.Remote.RetrofitService
import com.example.loginapp.Remote.dataModel.DefaultModel
import com.example.loginapp.Remote.dataModel.GetApiModel
import com.example.loginapp.Remote.ext.ErrorUtils
import com.example.loginapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("ViewConstructor")
class ViewMainActivity(
    contextInstance: Context,
    private val utils: ActUtils
) : FrameLayout(contextInstance) {
    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))
    fun OnClickHandler(androidId: String) {
        binding.btnSend.setOnClickListener() {
            val email = binding.edtInputEmail.text.toString()
            if (email.isEmpty()) {
                binding.textInputEmail.error = "ایمیل نمی تواند خالی باشد"
                return@setOnClickListener
            } else {
                binding.textInputEmail.error = null
            }
            sendCodeInEmail(email)
            binding.btnSend.visibility = INVISIBLE
            binding.textInputEmail.visibility = INVISIBLE

            binding.txtSendEmail.visibility = VISIBLE
            binding.textInputCode.visibility = VISIBLE
            binding.btnConfirm.visibility = VISIBLE
            binding.txtWrong.visibility = VISIBLE
            binding.txtSendEmail.text = ":ارسال ایمیل$email"
        }
        binding.txtWrong.setOnClickListener() {
            binding.btnSend.visibility = VISIBLE
            binding.textInputEmail.visibility = VISIBLE

            binding.txtSendEmail.visibility = INVISIBLE
            binding.textInputCode.visibility = INVISIBLE
            binding.btnConfirm.visibility = INVISIBLE
            binding.txtWrong.visibility = INVISIBLE

        }
        binding.btnConfirm.setOnClickListener() {
            val code = binding.edtCode.text.toString()
            val email = binding.edtInputEmail.text.toString()

            if (code.length < 6) {
                binding.textInputCode.error = "کد نمی تواند کمتر از 6 رقم باشد باشد"
                return@setOnClickListener

            } else {
                binding.textInputCode.error = null
            }
            verifyCode(androidId, code, email)
        }

    }

    private fun sendCodeInEmail(email: String) {
        val service = RetrofitService.ApiService
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.sendReqest(email)
                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as DefaultModel
                        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()

                    }
                } else
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, ErrorUtils.getError(response), Toast.LENGTH_SHORT)
                            .show()
                    }
            } catch (e: Exception) {
                Log.i("SERVER_ERROR", e.message.toString())
            }
        }

    }

    private fun verifyCode(androidId: String, code: String, email: String) {
        val service = RetrofitService.ApiService
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.verifyCode(androidId, code, email)
                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as GetApiModel
                        data.Api


                        /* Toast.makeText(context,
                            "ورود شما با موفقیت بود", Toast.LENGTH_LONG).show()
                        context.startActivity(Intent(context,HomeActivity::class.java))
                        utils.finished()*/
                    }
                } else
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, ErrorUtils.getError(response), Toast.LENGTH_SHORT)
                            .show()
                    }
            } catch (e: Exception) {
                Log.i("SERVER_ERROR", e.message.toString())
            }
        }

    }
}