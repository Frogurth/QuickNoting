package de.frogurth.gotd.quicknoting.util

import de.frogurth.gotd.quicknoting.repo.Repository
import java.text.SimpleDateFormat
import java.util.Date

object LineFormatter {
  def getPrefix(level: String) = {
    val prefix = Repository.current.get.loadConfig.get
    val pattern = prefix.split("\n").filter(_.contains("prefix")).head.split(":")(1)
    val p = pattern.replace("%l", level).replace("%t", getTime)
    println(p)
    p
  }
  
  def getTime = {
    val formatter = new SimpleDateFormat("HH:mm:ss")
    formatter.format(new Date)
  }
}