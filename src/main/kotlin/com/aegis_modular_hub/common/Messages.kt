package com.aegis_modular_hub.common

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

@Component
class Messages(messageSource: MessageSource) {
    init { instance = messageSource }

    companion object {
        private lateinit var instance: MessageSource

        fun get(code: String, vararg args: Any?): String {
            val raw = instance.getMessage(code, null, LocaleContextHolder.getLocale())
            return String.format(raw, *args)
        }

        fun resolve(code: String): String {
            val cleanCode = code.replace("{", "").replace("}", "")
            return instance.getMessage(cleanCode, null, LocaleContextHolder.getLocale())
        }
    }
}
