package com.example.peertutoring.controller;

import com.example.peertutoring.entity.Tutor;
import com.example.peertutoring.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tutors")
public class TutorController {

    private final TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public String getAllTutors(Model model) {
        model.addAttribute("tutors", tutorService.getAllTutors());
        return "tutors/list";
    }

    @GetMapping("/new")
    public String createTutorForm(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutors/form";
    }

    @PostMapping
    public String createTutor(@ModelAttribute Tutor tutor) {
        tutorService.saveTutor(tutor);
        return "redirect:/tutors";
    }

    @GetMapping("/edit/{id}")
    public String editTutorForm(@PathVariable Long id, Model model) {
        Tutor tutor = tutorService.getTutorById(id);
        if (tutor == null) {
            return "redirect:/tutors";
        }
        model.addAttribute("tutor", tutor);
        return "tutors/form";
    }

    @PostMapping("/{id}")
    public String updateTutor(@PathVariable Long id, @ModelAttribute Tutor tutor) {
        Tutor existingTutor = tutorService.getTutorById(id);
        if (existingTutor == null) {
            return "redirect:/tutors";
        }
        tutor.setId(id);
        tutorService.saveTutor(tutor);
        return "redirect:/tutors";
    }

    @GetMapping("/delete/{id}")
    public String deleteTutor(@PathVariable Long id) {
        tutorService.deleteTutor(id);
        return "redirect:/tutors";
    }
}