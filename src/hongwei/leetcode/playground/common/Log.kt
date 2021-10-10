package hongwei.leetcode.playground.common

object Log {
    fun d(message: String) {
        println(message)
    }

    fun pass(message: String) {
        println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + message + ANSI_RESET)
    }

    fun fail(message: String) {
        println(ANSI_RED + message + ANSI_RESET)
    }

    private const val ANSI_RESET = "\u001B[0m"
    private const val ANSI_BLACK = "\u001B[30m"
    private const val ANSI_RED = "\u001B[31m"
    private const val ANSI_GREEN = "\u001B[32m"
    private const val ANSI_YELLOW = "\u001B[33m"
    private const val ANSI_BLUE = "\u001B[34m"
    private const val ANSI_PURPLE = "\u001B[35m"
    private const val ANSI_CYAN = "\u001B[36m"
    private const val ANSI_WHITE = "\u001B[37m"

    private const val ANSI_BLACK_BACKGROUND = "\u001B[40m"
    private const val ANSI_RED_BACKGROUND = "\u001B[41m"
    private const val ANSI_GREEN_BACKGROUND = "\u001B[42m"
    private const val ANSI_YELLOW_BACKGROUND = "\u001B[43m"
    private const val ANSI_BLUE_BACKGROUND = "\u001B[44m"
    private const val ANSI_PURPLE_BACKGROUND = "\u001B[45m"
    private const val ANSI_CYAN_BACKGROUND = "\u001B[46m"
    private const val ANSI_WHITE_BACKGROUND = "\u001B[47m"
}