package com.example.peertutoring.controller;

import com.example.peertutoring.entity.Student;
import com.example.peertutoring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }

    @GetMapping("/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "students/form";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            return "redirect:/students";
        }
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}