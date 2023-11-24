package com.example.lect_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.lect_retrofit.filesretro.ApiInterface
import com.example.lect_retrofit.filesretro.RetrofitClient

import android.util.Log
//import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivity : AppCompatActivity() {


    private lateinit var txtData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtData = findViewById(R.id.txtData)
        getUserList()


    }

    private fun getUserList(){

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch{
            try {
                val response = apiInterface.getAllUsers()
                if(response.isSuccessful){
                    if((response.body()?.data?.size?:0) <=0){
                        Toast.makeText(
                            this@MainActivity,
                                    "No Data",
                            Toast.LENGTH_LONG


                        ).show()
                    }else   {
                        response.body()?.let {
                            txtData.text = it.data.joinToString("\n\n")
                        }

                    }

                }
                else{
                        Toast.makeText(
                            this@MainActivity,
                            response.errorBody().toString(),
                            Toast.LENGTH_LONG

                        ).show()
                }



            } catch (Ex:Exception){
                Ex.localizedMessage?.let { Log.e("Error",it) }
            }



        }



    }




}