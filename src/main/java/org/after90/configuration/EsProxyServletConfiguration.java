package org.after90.configuration;

import lombok.extern.slf4j.Slf4j;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaogj
 */
@Configuration
@Slf4j
public class EsProxyServletConfiguration {

  private String servletUrl = "/es/*";
  private String targetUrl = "http://172.16.43.17:9200";

  @Bean
  public ServletRegistrationBean esServletRegistrationBean() {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
        new ProxyServlet(), servletUrl);
    servletRegistrationBean
        .addInitParameter("targetUri", targetUrl);
    servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG,
        "true");
    log.info("EsServletRegistrationBean set is ok.");
    return servletRegistrationBean;
  }
}
