package com.project.mvvmsample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.mvvmsample.R
import com.project.mvvmsample.data.db.entities.User
import com.project.mvvmsample.databinding.ActivitySignupBinding
import com.project.mvvmsample.ui.MainActivity
import com.project.mvvmsample.ui.Utils.hide
import com.project.mvvmsample.ui.Utils.show
import com.project.mvvmsample.ui.Utils.toast
import com.project.mvvmsample.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignupBinding = DataBindingUtil.
        setContentView(this,R.layout.activity_signup)

        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.vm = viewModel

        viewModel.authListener = this

        viewModel.getUserLoggedIn().observe(this, Observer{
                user->
            if(user!=null){
                Intent(this, HomeActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })


    }

    override fun onStarted() {
//        toast("started")
        progressBar.show()
    }

    override fun onSuccess(user: User) {
        toast("${user.name} is signed up")
        progressBar.hide()
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
        progressBar.hide()
        toast("failed : $message")
    }


}