package assemble.api.auth.jwt.filter;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.CommonErrorStatus;
import assemble.api.apiPayload.status.ErrorReason;
import ch.qos.logback.core.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        }
        catch (GeneralException ge) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, request, response, ge.getErrorReason());
        }

    }

    public void setErrorResponse(HttpStatus status, HttpServletRequest req,
                                 HttpServletResponse res, ErrorReason e) throws IOException {
        res.setStatus(status.value());
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        CommonResponse<?> apiResponse = CommonResponse.onFailure(e, null);
        res.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
    }

}