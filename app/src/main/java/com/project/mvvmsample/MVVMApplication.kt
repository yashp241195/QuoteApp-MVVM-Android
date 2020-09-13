package com.project.mvvmsample

import android.app.Application
import com.project.mvvmsample.data.db.AppDatabase
import com.project.mvvmsample.data.network.MyApi
import com.project.mvvmsample.data.network.NetworkConnectionInterceptor
import com.project.mvvmsample.data.repositories.UserRepository
import com.project.mvvmsample.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication:Application(), KodeinAware {
    override val kodein = Kodein.lazy{

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance())  }
        bind() from singleton { MyApi(instance())  }
        bind() from singleton { AppDatabase(instance())  }
        bind() from singleton { UserRepository(instance(),instance())  }
        bind() from provider { AuthViewModelFactory(instance())  }

    }
}