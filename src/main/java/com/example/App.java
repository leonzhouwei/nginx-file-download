package com.example;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.config.AppConfig;
import com.google.common.io.Files;

@ComponentScan
@EnableAutoConfiguration
public class App {

	public static final String VERSION = "0.0.2";

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static ConfigurableApplicationContext cac;
	private static App app;

	@Autowired
	private AppConfig appConfig;

	public static void main(String[] args) {
		try {
			cac = SpringApplication.run(App.class, args);
			app = cac.getBean(App.class);
			app.logStatus();
		} catch (Exception e) {
			logger.warn("", e);
		}
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer()
			throws UnknownHostException {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(appConfig.getHttpPort());
		factory.setSessionTimeout(appConfig.getHttpSessionTimeout(),
				TimeUnit.SECONDS);
		factory.setUriEncoding(StandardCharsets.UTF_8.name());
		return factory;
	}

	@Bean
	public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(
					ConfigurableEmbeddedServletContainer servletContainer) {
				((TomcatEmbeddedServletContainerFactory) servletContainer)
						.addConnectorCustomizers(new TomcatConnectorCustomizer() {
							@Override
							public void customize(Connector connector) {
								AbstractHttp11Protocol<?> httpProtocol = (AbstractHttp11Protocol<?>) connector
										.getProtocolHandler();
								httpProtocol.setCompression("on");
								httpProtocol.setCompressionMinSize(256);
								String mimeTypes = httpProtocol
										.getCompressableMimeTypes();
								String mimeTypesWithJson = mimeTypes + ","
										+ MediaType.APPLICATION_JSON_VALUE;
								httpProtocol
										.setCompressableMimeTypes(mimeTypesWithJson);
							}
						});
			}
		};
	}

	/**
	 * for more related details, please refer to
	 * https://github.com/spring-projects/spring-boot/issues/1182
	 * 
	 * @return
	 */
	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	public static <T> T getBean(Class<T> clazz) {
		return cac.getBean(clazz);
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		return cac.getBean(name, clazz);
	}

	void logStatus() throws IOException {
		logger.info("----- Status Info START ----- ");
		logger.info("version: " + VERSION);
		String pid = System.getProperty("PID");
		String pidFilePath = AppConfig.CURRENT_WORKING_DIR
				+ AppConfig.FILE_SEPARATOR + appConfig.getWorkDirPath()
				+ AppConfig.FILE_SEPARATOR + "app.pid";
		File pidFile = new File(pidFilePath);
		if (!pidFile.getParentFile().exists()) {
			pidFile.mkdirs();
		}
		Files.write(pid, pidFile, StandardCharsets.UTF_8);
		logger.info("process id: " + pid);
		logger.info("port: " + appConfig.getHttpPort());
		logger.info("session timeout: " + appConfig.getHttpSessionTimeout()
				+ " seconds");
		logger.info("current working directory: '"
				+ AppConfig.CURRENT_WORKING_DIR + "'");
		logger.info("----- Status Info  END  ----- ");
	}
}

@Configuration
@ImportResource("/spring/applicationContext.xml")
class XmlImportingConfiguration {
}
