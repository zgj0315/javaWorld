package org.after90.configuration;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaogj
 */
@Configuration
public class KibanaProxyServletConfiguration {

  private String servletUrl = "/kibana/*";
  private String targetUrl = "http://172.16.43.17:15601";

  @Bean
  public ServletRegistrationBean servletRegistrationBean() {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
        new ProxyServlet(), servletUrl);
    servletRegistrationBean
        .addInitParameter("targetUri", targetUrl);
    servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG,
        "true");
    return servletRegistrationBean;
  }
}
