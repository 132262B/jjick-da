package app.jjickda.api.admin.controller;

import app.jjickda.domain.role.Role;
import app.jjickda.global.annotation.LoginCheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@Controller
@RequestMapping("/admin")
public class AdminController {

    @LoginCheck(auth = Role.ADMIN)
    @GetMapping("/home")
    public String AdminPage() {
        return "main/admin/home";
    }

    // write-question
    @GetMapping("/write-question")
    public String questionWrite() {
        return "main/admin/writeQuestion";
    }

    @GetMapping("/write-question-form")
    public String questionWriteForm() {
        return "main/admin/writeQuestionForm";
    }

    @GetMapping("/write-main-question")
    public String mainQuestionWriteForm() {
        return "main/admin/writeMainQuestionForm";
    }

    @GetMapping("/write-sub-question")
    public String subQuestionWriteForm() {
        return "main/admin/writeSubQuestionForm";
    }

    @GetMapping("/question-list")
    public String questionList() {
        return "main/admin/questionList";
    }
    @GetMapping("/write-sub-question/subject/{sub_ctg_seq}")
    public String SelectTerm(@PathVariable int sub_ctg_seq, Model model) {
        model.addAttribute("sub_ctg_seq",sub_ctg_seq);
        return "main/admin/writeSubjectForm";
    }


}
