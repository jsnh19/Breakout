package me.zero.cc.SQLite;

public class SqliteDb extends Db
{
  String sDriverForClass = "org.sqlite.JDBC";

  public SqliteDb(String sUrlKey) throws Exception { init(this.sDriverForClass, sUrlKey); }

}