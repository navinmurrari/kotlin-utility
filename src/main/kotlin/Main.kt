import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.Git.open
import java.io.File
import java.util.*

val list: List<String> = mutableListOf(
    "sfm",
    "user-authentication",
    "jigsaw",
    "config",
    "service_manager",
    "sv",
    "user-profile",
    "ums",
    "lta",
    "txn",
    "oms",
    "shulka",
    "ams",
    "auth",
    "notification-service",
    "otp-service"
)
fun main(args: Array<String>) {

    printCommitIds()
}

fun printCommitIds() {
    val builder = StringBuilder()
    list.forEach { component ->
        val git: Git = open(File(String.format("F:\\MFS\\%s", component)))
        val stack: Stack<String> = Stack();
        git.log().call().forEach {
            if (it.fullMessage.contains("APIs added to generate")) {
                stack.push(it.name)
            }
        }
        if (stack.size > 0) {
            builder.append(component).appendLine()
            while (!stack.empty()) {
                builder.append(stack.pop()).appendLine()
            }
            builder.appendLine()
        }
    }
    println(builder)

}


