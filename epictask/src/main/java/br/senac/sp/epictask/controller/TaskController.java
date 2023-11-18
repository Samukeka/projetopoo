
package br.senac.sp.epictask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senac.sp.epictask.model.Task;
import br.senac.sp.epictask.repository.TakeRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired
    TakeRepository repository;

    @GetMapping
    public String index(Model model){
        var list = repository.findAll();
        model.addAttribute("tasks", list);
        return "task/index";
    }

    @GetMapping("new") //localhost:8080/new
    public String form(Task task){
        return "task/form";
    }
    @PostMapping("new")
    public String save(@Valid Task task, BindingResult result){
        if(result.hasErrors()) return "task/form";
        repository.save(task);
        return "redirect:/task";
    }
}

