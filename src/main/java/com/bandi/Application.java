package com.bandi;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Run Command:
 * 
 * java -jar target/springboot-0.0.1-SNAPSHOT.jar --server.port=8745
 *
 *
 * 
 * docker build -t springboot .
 * docker run -d --net=host -v /home/kishore.bandi/springboot/logs/:/home/kishore.bandi/springboot/logs/ --name springboot springboot
 *
 * @author kishore
 *
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@Slf4j
public class Application extends SpringBootServletInitializer {

	public static volatile AtomicInteger totalCalls = new AtomicInteger(0);
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
	    return new ServletRegistrationBean(dispatcherServlet(),"/test/*");
	}

	@SuppressWarnings("serial")
	//@Bean
	public Servlet dispatcherServlet() {
		return new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				customService(req, resp);
			}

			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				customService(req, resp);
			}

			@Override
			protected void doHead(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				customService(req, resp);
			}

			@Override
			protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				customService(req, resp);
			}

			@Override
			protected void doPut(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				customService(req, resp);
			}

			@Override
			protected void doTrace(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				customService(req, resp);
			}

			@Override
			protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				customService(req, resp);
			}

			private void customService(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException {
				StringBuilder sb = new StringBuilder("\n");
				sb.append("Content-Type : " + req.getContentType()).append("\n");
				sb.append("Method : " + req.getMethod()).append("\n");
				sb.append("Path : " + req.getContextPath()).append("\n");
				sb.append("Path Info : " + req.getPathInfo()).append("\n");
				sb.append("Servlet Path : " + req.getServletPath()).append("\n");
				sb.append("Query String : " + req.getQueryString()).append("\n");
				sb.append("Header Names : " + String.join(",", Collections.list(req.getHeaderNames()))).append("\n");
				sb.append("Protocol : " + req.getProtocol()).append("\n");
				sb.append("Remote Addr : " + req.getRemoteAddr()).append("\n");
				sb.append("Remote Host : " + req.getRemoteHost()).append("\n");
				sb.append("Remote Port : " + req.getRemotePort()).append("\n");
				sb.append("Remote User : " + req.getRemoteUser()).append("\n");
				sb.append("Scheme : " + req.getScheme()).append("\n");
				sb.append("Server name : " + req.getServerName()).append("\n");
				sb.append("Server Port : " + req.getServerPort()).append("\n");
				sb.append("Attributes : " + req.getAttributeNames()).append("\n");
				sb.append("ParameterMap : " + req.getParameterMap()).append("\n");
				sb.append("Body : " + IOUtils.toString(req.getInputStream())).append("\n");
				sb.append("Total Calls so far : " + totalCalls.incrementAndGet()).append("\n\n");
				log.error(sb.toString());
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("text/html");
				res.getWriter().print("<html><body>hello</body></html>");
				/*
				res.getWriter().append("<html><body>hello</body></html>");
				res.getWriter().close();*/
			}
		};
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
