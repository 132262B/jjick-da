package app.jjickda.api.admin.controller;

import app.jjickda.global.config.enumerated.Role;
import app.jjickda.global.annotation.LoginCheck;
import app.jjickda.global.config.enumerated.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // 어드민 대시보드
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/dashboard")
    public String AdminPage() {
        return "main/admin/dashboard";
    }

    // 어드민 시험 등록 선택 페이지
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/choice")
    public String questionWrite() {
        return "main/admin/choice";
    }

    // 어드민 메인 카테고리 등록 페이지
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/main")
    public String mainQuestionWriteForm() {
        return "main/admin/main";
    }

    // 어드민 서브 카테고리 등록 페이지
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/sub")
    public String subQuestionWriteForm(@RequestParam(value="search",required = false) String search,
                                       @RequestParam(value="sort",required = false) String sort,
                                       Model model) {
        model.addAttribute("sort",sort);
        model.addAttribute("searchTarget",search);
        return "main/admin/sub";
    }

    // 어드민 과목 등록 페이지
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/subject/{subIdx}")
    public String SelectTerm(@PathVariable long subIdx, Model model) {
        model.addAttribute("subIdx", subIdx);
        return "main/admin/subject";
    }

    // 어드민 시험 등록 페이지
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/exam")
    public String questionWriteForm() {
        return "main/admin/exam";
    }

    // 어드민 시험 결재 페이지
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/confirm")
    public String confirmExam() {
        return "main/admin/confirm";
    }

    // 어드민 시험 목록 페이지
    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/exam/list")
    public String questionList() {
        return "main/admin/examList";
    }
}
