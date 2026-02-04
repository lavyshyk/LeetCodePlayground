package coroutine

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.time.Duration.Companion.milliseconds

fun main() {

    val emptyContext = EmptyCoroutineContext

    runBlocking(CoroutineName("main")) {
        log("111")
         val d = async() {

         }.invokeOnCompletion { it ->


         }
        d.dispose()
//        d.getCompleted()
//        val jd: Job = d

//        jd.join()
        println("1. About to start a new coroutine.")
        val j : Job = launch( Dispatchers.IO) {
            log("222")
            // CoroutineStart. DEFAULT is the launch's default start mode
            println("2. The body will be executed immediately")
            delay(50.milliseconds) // give up the thread to the outer coroutine
            println("4. When the thread is next available, this coroutine proceeds further")
        }

        log("333")


        println("3. After the initial suspension, the thread does other work.")
    }


}


suspend fun new() = coroutineScope {
    val coContext = this.coroutineContext

}

suspend fun boo(block: suspend CoroutineScope.() -> Int): Int {
    return block(return 42)
}

suspend fun foo(): Int = coroutineScope {

    delay(100)

    val a = async { 21 }
    val b = async { 42 }

    a.await() + b.await()
}

fun CoroutineScope.coLog(message: String): Unit {
    val name = this.coroutineContext[CoroutineName]?.name
    val job = this.coroutineContext[Job]
    val dispatcher = this.coroutineContext[CoroutineDispatcher]
    val errorHandler = this.coroutineContext[CoroutineExceptionHandler]

    Log.d(
        "EBS",
        "Coroutine : \n Name : $name \n Job : $job \n Dispatcher : $dispatcher \n ErrorHandler : $errorHandler \n " +
                "Message : $message"
    )
}

fun CoroutineScope.log(msg: String) {
    val name = coroutineContext[CoroutineName]?.name
    val dispatcher= coroutineContext[ContinuationInterceptor]
    println("[$name] $msg")
    println("Dispatcher : $dispatcher")
}

