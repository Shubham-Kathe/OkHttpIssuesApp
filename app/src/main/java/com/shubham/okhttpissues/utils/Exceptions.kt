package com.shubham.okhttpissues.utils

import java.io.IOException
class ApiException(message: String):IOException(message)
class NoInternetException(message: String):IOException(message)
class ServerUnreachableException(message: String):IOException(message)
