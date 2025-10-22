package com.example.peertutoring.controller;

import com.example.peertutoring.entity.Session;
import com.example.peertutoring.entity.Student;
import com.example.peertutoring.entity.Tutor;
import com.example.peertutoring.service.SessionService;
import com.example.peertutoring.service.StudentService;
import com.example.peertutoring.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;
    private final TutorService tutorService;
    private final StudentService studentService;

    @Autowired
    public SessionController(SessionService sessionService, 
                           TutorService tutorService,
                           StudentService studentService) {
        this.sessionService = sessionService;
        this.tutorService = tutorService;
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllSessions(Model model) {
        model.addAttribute("sessions", sessionService.getAllSessions());
        return "sessions/list";
    }

    @GetMapping("/new")
    public String createSessionForm(Model model) {
        model.addAttribute("session", new Session());
        model.addAttribute("tutors", tutorService.getAllTutors());
        model.addAttribute("students", studentService.getAllStudents());
        return "sessions/form";
    }

    @PostMapping
    public String createSession(@ModelAttribute Session session, 
                              @RequestParam Long tutorId, 
                              @RequestParam Long studentId) {
        Tutor tutor = tutorService.getTutorById(tutorId);
        Student student = studentService.getStudentById(studentId);
        
        if (tutor == null || student == null) {
            throw new IllegalArgumentException("Invalid tutor or student ID");
        }
        
        session.setTutor(tutor);
        session.setStudent(student);
        sessionService.saveSession(session);
        return "redirect:/sessions";
    }

    @GetMapping("/edit/{id}")
    public String editSessionForm(@PathVariable Long id, Model model) {
        Session session = sessionService.getSessionById(id);
        if (session == null) {
            return "redirect:/sessions";
        }
        
        model.addAttribute("session", session);
        model.addAttribute("tutors", tutorService.getAllTutors());
        model.addAttribute("students", studentService.getAllStudents());
        return "sessions/form";
    }

    @PostMapping("/update/{id}")
    public String updateSession(@PathVariable Long id, 
                              @ModelAttribute Session session, 
                              @RequestParam Long tutorId, 
                              @RequestParam Long studentId) {
        Session existingSession = sessionService.getSessionById(id);
        if (existingSession == null) {
            return "redirect:/sessions";
        }
        
        Tutor tutor = tutorService.getTutorById(tutorId);
        Student student = studentService.getStudentById(studentId);
        
        if (tutor == null || student == null) {
            throw new IllegalArgumentException("Invalid tutor or student ID");
        }
        
        session.setId(id);
        session.setTutor(tutor);
        session.setStudent(student);
        sessionService.saveSession(session);
        return "redirect:/sessions";
    }

    @GetMapping("/delete/{id}")
    public String deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return "redirect:/sessions";
    }
}