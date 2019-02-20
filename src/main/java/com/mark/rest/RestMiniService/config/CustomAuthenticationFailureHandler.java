package com.mark.rest.RestMiniService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.rest.RestMiniService.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


        private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException exception) throws IOException, ServletException {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String, Object> data = new HashMap<>();
            data.put(
                    "timestamp",
                    Calendar.getInstance().getTime());
            data.put(
                    "exception",
                    exception.getMessage());

            response.getOutputStream()
                    .println(objectMapper.writeValueAsString(data));
            response.sendRedirect("/home");
        }
    }
