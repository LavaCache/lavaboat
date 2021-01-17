package cache.lava.bot.commands.general

import me.devoxin.flight.api.Context
import me.devoxin.flight.api.annotations.Command
import me.devoxin.flight.api.entities.Cog

class General : Cog {
  @Command(aliases = ["latency"], description = "Displays the clients latency")
  fun ping(ctx: Context) {
    ctx.jda.restPing.queue {
      ctx.send {
        setColor("3377de".toInt(16))
        appendDescription("\uD83D\uDC93 Gateway: **${ctx.jda.gatewayPing}ms**")
        appendDescription("\n")
        appendDescription("⏱️ Roundtrip: **${it}ms**")
      }
    }
  }
}