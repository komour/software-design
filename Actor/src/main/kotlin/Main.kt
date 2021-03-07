import actors.DuckDuckGoActor
import actors.MasterActor
import actors.YaHooActor
import actors.YandexActor
import akka.actor.*


fun main() {
    println("Enter the query:")
    val query = readLine()
    val system = ActorSystem.create("ActorSystem")
    val actors = listOf(
        system.actorOf(Props.create(DuckDuckGoActor::class.java), "DuckDuckGo"),
        system.actorOf(Props.create(YaHooActor::class.java), "YaHoo"),
        system.actorOf(Props.create(YandexActor::class.java), "Yandex")
    )

    val master = system.actorOf(Props.create(
        MasterActor::class.java,
        actors,
        { answerMap: HashMap<String, List<String>> ->
            for ((name, list) in answerMap) {
                println("$name:\n${list.joinToString(separator = "\n")}\n\n")
            }
        }
    ), "master")
    master.tell(query, null)
}