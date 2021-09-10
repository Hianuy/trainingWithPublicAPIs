package com.hianuy.rickandmortyapimvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hianuy.rickandmortyapimvvm.R
import com.hianuy.rickandmortyapimvvm.model.Result
import com.hianuy.rickandmortyapimvvm.ui.adapter.CharacterAdapter
import com.hianuy.rickandmortyapimvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private  val characterViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        characterViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        observerEvents()
    }

//    private fun initObserver() {
//        characterViewModel.liveDataListMovie.observe(this, { list ->
//            if (list.isNotEmpty()) {
//                settingRecyclerView(list)
//                loadingVisibility(false)
//            }
//        })
//    }
    private fun observerEvents(){
        characterViewModel.loading.observe(this, Observer { isLoading ->
            if (isLoading){
                progressBar.visibility = VISIBLE

            }else{
                progressBar.visibility = GONE

            }

            characterViewModel.errorMessage.observe(this, {message->
                message?.let {
                    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
                }
            })

            characterViewModel.results.observe(this,{results->
                results?.let {
                    settingRecyclerView(it)


                }
            })


        })
    }

    private fun settingRecyclerView(list: List<Result>) {
        recyclerViewMovies.apply {
            hasFixedSize()
            adapter = CharacterAdapter(list)
        }
    }


    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        characterViewModel.init()

    }

}