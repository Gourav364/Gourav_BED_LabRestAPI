package com.gl.student.controller;

import com.gl.student.entity.Student;
import com.gl.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @RequestMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "list-students";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Student student = new Student();
        theModel.addAttribute("student", student);
        return "student-form";
    }

    @RequestMapping("/showFormForUpdate")
    public String showFormForAdd(@RequestParam("studentId") int id, Model Model) {
        Student student = studentService.findById(id);
        Model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/save")
    public String saveTicket(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/student/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("studentId") int theId) {
        studentService.deleteById(theId);
        return "redirect:/student/list";
    }

    @RequestMapping(value = "/403")
    public ModelAndView accesssDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;
    }
}
