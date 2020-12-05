package com.hiscat.flink.connector.jdbc

import java.sql.{Connection, Driver, DriverManager, PreparedStatement}
import java.util.Properties

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.table.data.RowData
import org.apache.flink.table.types.DataType
import org.apache.flink.util.ChildFirstClassLoader
import org.apache.phoenix.jdbc.PhoenixDriver

import scala.util.{Failure, Success, Try}

case class JdbcSInkFunction(
                             options: JdbcConnectionOptions,
                             fieldNames: Array[String],
                             rowDataType: DataType
                           ) extends RichSinkFunction[RowData] {

  println("sink")
  lazy val connection: Connection = new PhoenixDriver().connect("jdbc:phoenix:hadoop102", new Properties())


  //  lazy private val parameters = fieldNames.map(_ => "?").mkString("(", ",", ")")
  //  lazy private val upsertStatement: PreparedStatement = connection.prepareStatement(s"UPSERT INTO ${options.tableName} VALUES $parameters")
  //  lazy private val insertStatement: PreparedStatement = connection.prepareStatement(s"INSERT INTO ${options.tableName} VALUES $parameters")
  //  lazy private val deleteStatement: PreparedStatement = connection.prepareStatement(s"DELETE FROM ${options.tableName} ")

  override def open(parameters: Configuration): Unit = {
    Try {
      //      Class.forName(options.driver)
      //      val driver = new PhoenixDriver()
      //      val connection = driver.connect("jdbc:phoenix:hadoop102", new Properties())
      //      println(JdbcConnectionProvider().getConnection)
      //      Thread.sleep(10000)
    } match {
      case Failure(exception) =>
        println(exception)
      //        exception.printStackTrace()
      case Success(value) => println(value)
    }
    println("open")
  }


  override def invoke(value: RowData, context: SinkFunction.Context[_]): Unit = {
    println("invoke ")
    println(connection)
    //    import scala.collection.JavaConverters._
    //    rowDataType.getChildren.asScala.zipWithIndex
    //      .map(e => RowData.createFieldGetter(e._1.getLogicalType, e._2))
    //      .map(g => g.getFieldOrNull(value))
    //      .map {
    //        case e: StringData => new String(e.toBytes)
    //        case e: DecimalData => e.toBigDecimal
    //        case e: TimestampData => new Timestamp(e.getMillisecond)
    //        case e => e
    //      }.zipWithIndex
    //      .foreach {
    //        case (value, i) =>
    //          if (options.enableUpsert)
    //            upsertStatement.setObject(i + 1, value)
    //          else
    //            insertStatement.setObject(i + 1, value)
    //      }
    //    if (options.enableUpsert) {
    //      upsertStatement.execute()
    //    } else {
    //      insertStatement.execute()
    //    }
  }

  override def close(): Unit = {
    println("close")
    //    deleteStatement.close()
    //    upsertStatement.close()
    //    insertStatement.close()
    //    connection.close()
  }
}
