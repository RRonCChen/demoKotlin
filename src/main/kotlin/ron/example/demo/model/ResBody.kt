package ron.example.demo.model

import org.springframework.http.HttpStatus

data class ResBody<T>(
    val statusCode: Int,
    val message: String,
    val data: T?
) {

    companion object {
        fun <T> getSuccessResBody(data: T?): ResBody<T> {
            return ResBody(HttpStatus.OK.value(), "success", data)
        }
    }
}
