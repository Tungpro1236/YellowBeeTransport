/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthFilter implements Filter {

    private final Map<String, String> roleDashboards = new HashMap<>();
    private final List<String> publicPages = Arrays.asList("/login", "/signup", "/homepage", "/profile.jsp", "/home");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Định nghĩa dashboard riêng cho từng role
        roleDashboards.put("Admin", "/admin/");
        roleDashboards.put("Manager", "/manager/");
        roleDashboards.put("SurveyStaff", "/survey/");
        roleDashboards.put("MovingStaff", "/moving/");
        roleDashboards.put("Customer", "/customer/");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        // Lấy đường dẫn request
        String path = request.getServletPath();

        // Lấy role của user nếu đã đăng nhập
        String role = (session != null) ? (String) session.getAttribute("role") : null;

        // Nếu chưa đăng nhập và truy cập dashboard → Chuyển đến login
        if (role == null && path.startsWith("/dashboard")) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Kiểm tra quyền hạn với dashboard (các role thấp hơn không vào được dashboard của role cao hơn)
        if ((path.startsWith("/dashboard/Admin") && !"Admin".equals(role))
                || (path.startsWith("/dashboard/Manager") && !Arrays.asList("Admin", "Manager").contains(role))
                || (path.startsWith("/dashboard/SurveyStaff") && !Arrays.asList("Admin", "Manager", "SurveyStaff").contains(role))
                || (path.startsWith("/dashboard/MovingStaff") && !Arrays.asList("Admin", "Manager", "MovingStaff").contains(role))
                || (path.startsWith("/dashboard/Customer") && !Arrays.asList("Admin", "Manager", "Customer").contains(role))) {
            response.sendRedirect("errorPage.jsp"); // Nếu không có quyền → Chuyển đến trang lỗi
            return;
        }

        // Nếu hợp lệ, cho phép truy cập
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // Không cần xử lý gì khi filter bị hủy
    }
}
