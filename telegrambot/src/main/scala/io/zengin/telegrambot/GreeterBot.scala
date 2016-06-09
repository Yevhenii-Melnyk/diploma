package io.zengin.telegrambot

import io.zengin.telegrambot.types.Message
import scalaj.http.{Http, Token}

object GreeterBot extends TelegramBot with Polling with Declarative {

  val info: String = "/opinion [tag,tag,...] - Gives percentage opinion on subject\n\n" +
    "/sample [tag,tag,...] - Gives 5 random tweets with sentiment according to subject\n\n" +
    "/check [text] -  Makes opinion on your message"
  val serverUrl = "http://localhost:8080/bot"

  on("/start") { implicit message: Message =>
    send(info)
  }

  on("/info") { implicit message: Message =>
    send(info)
  }

  on("/sample") { implicit message: Message =>
    Http(serverUrl + "/sample").postForm(Seq("tags" -> message.text.get, "count" -> "5")).asString
  }

  on("/opinion") { implicit message: Message =>
    Http(serverUrl + "/opinion").postForm(Seq("tags" -> message.text.get)).asString
  }

  on("/check") { implicit message: Message =>
    Http(serverUrl + "/check").postForm(Seq("text" -> message.text.get)).asString
  }

  def main(args: Array[String]) {
    GreeterBot.run()

  }
}