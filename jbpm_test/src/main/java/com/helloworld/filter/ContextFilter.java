package com.helloworld.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

public class ContextFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("JBPM filter destroy");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("JBPM filter init");
		JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext(JbpmContext.DEFAULT_JBPM_CONTEXT_NAME);
		try {
			chain.doFilter(req, resp);
		} catch (Exception e) {
			System.out.println("jbpm dofilter exception : "+e.getLocalizedMessage());
		} finally{
			jbpmContext.close();
			System.out.println("jbpm context closed !!!");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("JBPM filter init");
	}

}
