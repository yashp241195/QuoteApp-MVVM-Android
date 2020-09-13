package com.project.mvvmsample.ui.Utils

import java.io.IOException

class ApiExceptions(message:String): IOException(message)
class NoInternetExceptions(message:String): IOException(message)