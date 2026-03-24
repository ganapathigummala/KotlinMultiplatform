package logger

import platform.Foundation.NSLog

actual object Logger {

    actual fun d(tag: String, message: String) {
        NSLog("DEBUG [$tag]: $message")
    }

    actual fun i(tag: String, message: String) {
        NSLog("INFO [$tag]: $message")
    }

    actual fun e(tag: String, message: String) {
        NSLog("ERROR [$tag]: $message")
    }
}