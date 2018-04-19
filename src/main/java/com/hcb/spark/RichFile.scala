package com.hcb.spark

import java.io.File

import scala.io.Source

/**
  * TODO 功能描述
  *
  * @author xu.zhang5
  * @date 2018/4/19 10:19
  * @since V1.0
  *
  */
class RichFile(file:File) {
    def read()={
      println(Source.fromFile(file).mkString)
    }
}
