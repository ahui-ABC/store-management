package test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName 测试接口$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin:manage')") // 权限注解
    public String adminOnly() {
        return "Admin Access";
    }
}
