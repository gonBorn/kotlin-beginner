package ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object KtorClient {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            launch {
                val client = HttpClient(CIO)
                val httpResponse = client.get("https://ktor.io/")
                println(httpResponse)
                val rep2 = client.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = "ktor.io"
                        path("docs/welcome.html")
                    }
                }
                println(rep2)
                val rep3 = client.get {
                    url {
                        parameters.append("key", "value")
                    }
                }
                println(rep3)
                val rep4 = client.get {
                    url {
                        fragment = "section"
                    }
                    headers {
                        append(HttpHeaders.Accept, "text/html")
                        append(HttpHeaders.Authorization, "abc123")
                        append(HttpHeaders.UserAgent, "ktor client")
                    }
                    cookies()
                }
                println(rep4)

                val postRes = client.post(
                    "https://ktor.io/"
                ) {
                    setBody("data")
                }
                println(postRes)
                client.close()
            }
        }
    }
}