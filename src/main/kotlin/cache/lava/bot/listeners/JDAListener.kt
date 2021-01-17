package cache.lava.bot.listeners

import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.LoggerFactory

class JDAListener : ListenerAdapter() {
  private val logger = LoggerFactory.getLogger(JDAListener::class.java)
  
  override fun onReady(event: ReadyEvent) {
    logger.info("{} is ready!", event.jda.selfUser.asTag)
  }
}