package logger

import timber.log.Timber

actual object Logger {

    actual fun d(tag: String, message: String) {
        Timber.tag(tag).d(message)
    }

    actual fun i(tag: String, message: String) {
        Timber.tag(tag).i(message)
    }

    actual fun e(tag: String, message: String) {
        Timber.tag(tag).e(message)
    }
}