package com.project.mvvmsample.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.mvvmsample.R
import com.project.mvvmsample.data.db.AppDatabase
import com.project.mvvmsample.data.db.entities.User
import com.project.mvvmsample.data.network.MyApi
import com.project.mvvmsample.data.network.NetworkConnectionInterceptor
import com.project.mvvmsample.data.repositories.UserRepository
import com.project.mvvmsample.databinding.ActivityLoginBinding
import com.project.mvvmsample.ui.MainActivity
import com.project.mvvmsample.ui.Utils.hide
import com.project.mvvmsample.ui.Utils.show
import com.project.mvvmsample.ui.Utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    //
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.
        setContentView(this,R.layout.activity_login)

//        Before kodeIn DI
//        val nci = NetworkConnectionInterceptor(this)
//
//        val api = MyApi(nci)
//        val db = AppDatabase(this)
//
//        val repository = UserRepository(api, db)
//        val factory = AuthViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.vm = viewModel

        viewModel.authListener = this

        viewModel.getUserLoggedIn().observe(this,Observer{
            user->
            if(user!=null){
                Intent(this,MainActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

//        before factory and before fixing dependency

//        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
//        binding.vm = viewModel
//
//        viewModel.authListener = this
    }

    override fun onStarted() {
//        toast("started")
        progressBar2.show()
    }

    override fun onSuccess(user: User) {
        toast("${user.name} is logged In")
        progressBar2.hide()
    }

    // before coroutine optimisation
//    override fun onSuccess(loginResponse: LiveData<String>) {
//        toast("success")
//        loginResponse.observe(this, Observer{
//            progressBar2.hide()
//            toast(it)
//        })
//    }

    override fun onFailure(message: String) {
        progressBar2.hide()
        toast("failed : $message")
    }
}