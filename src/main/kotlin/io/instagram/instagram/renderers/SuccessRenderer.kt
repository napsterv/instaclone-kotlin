package io.instagram.instagram.renderers

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import java.util.*

@ControllerAdvice
class SuccessRenderer : ResponseBodyAdvice<Any> {
    override fun supports(p0: MethodParameter, p1: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(
            any: Any?,
            methodParameter: MethodParameter,
            p2: MediaType,
            p3: Class<out HttpMessageConverter<*>>,
            p4: ServerHttpRequest,
            p5: ServerHttpResponse
    ): Any? {
        if (methodParameter.containingClass.isAnnotationPresent(RestController::class.java)) {
            print(any)
            return SuccessResponse(any)
        }
        return any
    }
}