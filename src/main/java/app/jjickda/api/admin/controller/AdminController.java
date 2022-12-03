package app.jjickda.api.admin.controller;

import app.jjickda.domain.role.Role;
import app.jjickda.global.annotation.LoginCheck;
import app.jjickda.global.config.exception.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/dashboard")
    public String AdminPage() {
        return "main/admin/dashboard";
    }

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/write-question")
    public String questionWrite() {
        return "main/admin/writeQuestion";
    }

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/write-question-form")
    public String questionWriteForm() {
        return "main/admin/writeQuestionForm";
    }

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/write-main-question")
    public String mainQuestionWriteForm() {
        return "main/admin/writeMainQuestionForm";
    }

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping(value = {"/write-sub-question/{searchSort}/{searchObject}","/write-sub-question"})
    public String subQuestionWriteForm(@PathVariable(required = false) Optional<String> searchSort, @PathVariable(required = false) Optional<String>searchObject, Model model) {
    if(searchSort.isPresent() && searchObject.isPresent()){
        model.addAttribute("sort",searchSort.get());
        model.addAttribute("searchTarget",searchObject.get());
    }
        return "main/admin/writeSubQuestionForm";
    }

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/question-list")
    public String questionList() {
        return "main/admin/questionList";
    }

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/write-sub-question/subject/{subIdx}")
    public String SelectTerm(@PathVariable long subIdx, Model model) {
        model.addAttribute("subIdx", subIdx);
        return "main/admin/writeSubjectForm";
    }

    @LoginCheck(auth = Role.ADMIN, type = Type.PAGE)
    @GetMapping("/confirm-exam")
    public String confirmExam() {
        return "main/admin/confirm";
    }
}
