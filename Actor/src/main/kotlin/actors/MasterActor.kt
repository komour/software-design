package actors

import akka.actor.*
import scala.concurrent.duration.Duration

class MasterActor(
    private val actors: List<ActorRef>,
    private val callback: (HashMap<String, List<String>>) -> Unit
) :
    UntypedAbstractActor() {
    private val answerMap: HashMap<String, List<String>> = HashMap()
    private val timeout = Duration.fromNanos(3_000_000_000)

    @Suppress("UNCHECKED_CAST")
    override fun onReceive(message: Any?) {
        when (message) {
            is String -> {
                println("Query: '$message'")
                context.setReceiveTimeout(timeout)
                actors.forEach { it.tell(message, self) }
            }
            is List<*> -> {
                answerMap[sender.path().name()] = message as List<String>
                if (answerMap.size == actors.size) {
                    sender.tell(answerMap, self())
                    println("DONE!\n")
                    stop()
                }
            }
            is ReceiveTimeout -> {
                answerMap[sender.path().name()] = listOf("TIMEOUT!\n")
                if (answerMap.size == actors.size) {
                    sender.tell(answerMap, self())
                    println("DONE!\n")
                    stop()
                }
            }
        }
    }

    private fun stop() {
        actors.forEach { context.stop(it) }
        callback(answerMap)
        context.stop(self)
        context.system().terminate()
    }
}