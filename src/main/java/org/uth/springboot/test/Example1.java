package org.uth.springboot.test;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Bean;
import org.apache.camel.spring.boot.FatJarRouter;

@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Example1 extends FatJarRouter 
{
  // must have a main method spring-boot can run
  //public static void main(String[] args) {
  //    SpringApplication.run(Application.class, args);
  //}

  @Override
  public void configure() throws Exception 
  {
    from("netty-http:http://0.0.0.0:8080").setBody().simple("ref:buildResponse();
  }

  @Bean
  String buildResponse()
  {
    StringBuilder responseBuilder = new StringBuilder();

    responseBuilder.append( "<html><head><title>RESPONSE FROM CAMEL</title></head>\n" );
    responseBuilder.append( "<body><b>System Time: </b>" + System.currentTimeMillis() + "<p/>\n" );
    responseBuilder.append( "<hr width='100%'><p/>\n" );
    responseBuilder.append( "</body></html>\n" );

    return responseBuilder.toString();
  }
}
