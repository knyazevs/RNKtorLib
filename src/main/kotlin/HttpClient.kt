import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
object TestHttpClient {
    private val httpClient = HttpClient(Js.create())

    suspend fun get(url: String): String {
        val result = httpClient.get(url)
        return result.body()
    }
}
