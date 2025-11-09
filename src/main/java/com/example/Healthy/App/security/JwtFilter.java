package com.example.Healthy.App.security;

import java.io.IOException;

import com.example.Healthy.App.security.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class JwtFilter extends OncePerRequestFilter{

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    com.example.Healthy.App.security.CustomUserDetailService jwtDetailServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 1. Lấy JWT từ request
            String jwt = parseJwt(request);

            // 2. Nếu có token VÀ token hợp lệ
            if (jwt != null && jwtUtil.validateToken(jwt)) {
                // 3. Lấy username từ token
                String username = jwtUtil.extractUsername(jwt);

                // 4. Tải thông tin UserDetails
                UserDetails userDetails = jwtDetailServiceImpl.loadUserByUsername(username);

                // 5. Tạo đối tượng Authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 6. Lưu vào SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            sendError(response, "Invalid JWT signature");
            return; // Dừng lại
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            sendError(response, "Invalid JWT token");
            return; // Dừng lại
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            sendError(response, "JWT token is expired");
            return; // Dừng lại
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            sendError(response, "JWT token is unsupported");
            return; // Dừng lại
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            sendError(response, "JWT claims string is empty");
            return; // Dừng lại
        } catch (Exception e) {
//            logger.error("Cannot set user authentication: {}", e.getMessage());
            logger.error(e.toString(), e.getMessage());
            sendError(response, "Cannot set user authentication");
            return; // Dừng lại
        }

        // 7. Cho phép request tiếp tục chạy
        filterChain.doFilter(request, response);
    }

    // Hàm helper để lấy token từ header
    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // Bỏ "Bearer "
        }
        return null;
    }

    // Hàm helper để gửi lỗi 401
    private void sendError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"" + message + "\"}");
    }

    //
    // --- KHÔNG CẦN CÁC HÀM CŨ NÀY NỮA ---
    //
    // public String parseJwt(String headerAuth) { ... }
    //
    // public String extractUsername(String headerAuth) { ... }
    //
}