package net.kathir.mviarchitecture.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.kathir.mviarchitecture.R
import net.kathir.mviarchitecture.data.api.ApiHelper
import net.kathir.mviarchitecture.data.api.ApiServiceImpl
import net.kathir.mviarchitecture.ui.base.ViewModelFactory
import net.kathir.mviarchitecture.ui.main.adapter.MainAdapter
import net.kathir.mviarchitecture.ui.main.viewevent.MainEvent
import net.kathir.mviarchitecture.ui.main.viewmodel.MainViewModel
import net.kathir.mviarchitecture.util.Status

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupViewEvents()

    }

    private fun setupUI()
    {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setupViewEvents()
    {
        mainViewModel.setEventValue(MainEvent.UserLoadEvent)
    }

    private fun setupViewModel()
    {
        mainViewModel = ViewModelProviders.of(this,ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(MainViewModel::class.java)
    }

    private fun observeViewModel()
    {
        mainViewModel.dataValueState.observe(this, Observer { result ->
            when(result.status)
            {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    result.data?.users.let {
                        it?.let {
                            users -> mainViewModel.loadUser(users)
                        }
                    }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
                }
            }

        })

        mainViewModel.viewState.observe(this, Observer { it ->

            it.users.let {users ->

                recyclerView.visibility = View.VISIBLE
                users.let { listofUsers -> listofUsers ?.let { adapter.addData(it) } }
                adapter.notifyDataSetChanged()

            }
        }

        )
    }

}
