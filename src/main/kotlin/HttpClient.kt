import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.promise
import kotlin.js.Promise


private val scope = CoroutineScope(Dispatchers.Default)

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getIp(): Promise<String> {
    return scope.promise {
        TestHttpClient.get("http://ip.jsontest.com/")
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getIpUsingWindow(): Promise<String> {
    return scope.promise {
        JSON.stringify(
            Promise.resolve(window.fetch("http://ip.jsontest.com/").then { response ->
                response.json()
            }.then { it }).await())
    }
}

object TestHttpClient {
    private val httpClient = HttpClient(Js.create())

    suspend fun get(url: String): String {
        val result = httpClient.get(url)
        return result.body()
    }
}
