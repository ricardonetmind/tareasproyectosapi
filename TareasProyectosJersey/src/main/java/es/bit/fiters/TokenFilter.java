package es.bit.fiters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import es.bit.models.StatusMessage;
import es.bit.rest.ServiceUtility;


//@WebFilter(filterName = "TokenFilter", urlPatterns = { "/api/pedidos/*" })
public class TokenFilter implements Filter {
	private static final Logger logger = Logger.getLogger(TokenFilter.class.getName());

	public TokenFilter() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token=((HttpServletRequest) request).getHeader("token");
		logger.info("Filtrando requests: TOKEN:"+token);
		
		String email=ServiceUtility.getUserEmailFromToken(token);
		
		if(email!=null) {
			chain.doFilter(request, response);
		}else {
			logger.severe("Intento de acceso sin token v�lido");
			byte[] responseToSend = restResponseBytes(Response.status(403).entity(new StatusMessage(403, "Prohibido")).build());
			((HttpServletResponse)response).setStatus(403);
			response.getOutputStream().write(responseToSend);						
		}
				
	}

	
	private byte[] restResponseBytes(Response eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
