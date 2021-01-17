package cache.lava.bot

import cache.lava.bot.listeners.FlightListener
import cache.lava.bot.listeners.JDAListener
import me.devoxin.flight.api.CommandClient
import me.devoxin.flight.api.CommandClientBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag

object Launcher {
  private val config = Configuration()
  
  lateinit var commandClient: CommandClient
    private set
  
  lateinit var jda: JDA
    private set
  
  @ExperimentalStdlibApi
  @JvmStatic
  fun main(args: Array<String>) {
    commandClient = CommandClientBuilder()
      .registerDefaultParsers()
      .addEventListeners(FlightListener())
      .setOwnerIds(* config.get<List<String>>("bot.owners").toTypedArray())
      .setPrefixes(config.get<List<String>>("bot.prefixes"))
      .configureDefaultHelpCommand { enabled = true }
      .build()
    
    commandClient.commands.register("cache.lava.bot.commands")
    
    jda = JDABuilder.createLight(config.get("bot.token"))
      .addEventListeners(JDAListener(), commandClient)
      .enableIntents(GatewayIntent.GUILD_MESSAGES)
      .disableCache(
        CacheFlag.ACTIVITY,
        CacheFlag.CLIENT_STATUS,
        CacheFlag.EMOTE,
        CacheFlag.MEMBER_OVERRIDES
      ).build()
  }
}