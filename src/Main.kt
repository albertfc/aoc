import org.apache.commons.lang3.time.StopWatch

fun main() {
    val watch = StopWatch()
    watch.start()
    Day07b().run()
    watch.stop()
    println("\nElapsed time: ${watch.time} ms")
}