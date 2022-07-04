package com.shubham.okhttpissues.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.okhttpissues.R
import com.shubham.okhttpissues.data.entity.ListOfIssues
import com.shubham.okhttpissues.databinding.ActivityMainBinding
import com.shubham.okhttpissues.utils.hide
import com.shubham.okhttpissues.utils.show
import com.shubham.okhttpissues.utils.snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity() ,KodeinAware,CallBackListener{
    override val kodein by kodein()
    private val factory: MainViewModelFactory by instance()
    private var mList = ArrayList<ListOfIssues>()
    private var mIssueListAdapter: IssueListAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewModel = viewModel
        }
        viewModel.callBackListener=this
        viewModel.getIssueListFromDb().observe(this, Observer { details ->
            if (details.isNullOrEmpty()) {
                /*Check if data not available in database then execute API call*/
                viewModel.fetchIssueList()
            } else {
                mList.clear()
                mList.addAll(details)
                mIssueListAdapter?.notifyDataSetChanged()
            }
        })


        /*
      * SetUp Recyclerview to display list of issue
      */
        recyclerListOfIssues.also {
            mIssueListAdapter=IssueListAdapter(mList)
            val layoutManager = LinearLayoutManager(this)
            it.layoutManager = layoutManager
            it.adapter = mIssueListAdapter
            it.addItemDecoration(
                DividerItemDecoration(
                    it.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }


    }


    override fun onStarted() {
        if (progressBar != null) {
            progressBar.show()
        }
    }

    override fun onSuccess() {
        if (progressBar != null) {
            progressBar.hide()
        }
    }

    override fun onFailed(message: String) {
        if (progressBar != null) {
            progressBar.hide()
        }
        if (containerMainActivity != null) {
            containerMainActivity.snackbar(message)
        }
    }
}