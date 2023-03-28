// ================ Hadoop Path
// <dependency>
//     <groupId>org.apache.hadoop</groupId>
//     <artifactId>hadoop-client-api</artifactId>
//     <version>3.3.1</version>
// </dependency>

val pathStr = "hdfs://addr/path/to/file"
val path = new Path(pathStr)
println(path.toUri) // hdfs://addr/path/to/file
println(path.toUri.getPath) // /path/to/file
println(path.toUri.getRawPath) // /path/to/file
println(path.toUri.getScheme) // hdfs


