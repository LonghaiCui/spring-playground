package com.longhai.playground.controller;

import com.longhai.playground.model.Lesson;
import com.longhai.playground.model.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    @Autowired
    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable Long id) {
        Optional<Lesson> lessonOptional = this.repository.findById(id);
        if(lessonOptional.isPresent()) return lessonOptional.get();
        else return null;
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @PostMapping("/list")
    public void create(@RequestBody List<Lesson> lessons) {
         this.repository.saveAll(lessons);
    }

    @PatchMapping("/{id}")
    public Lesson update(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/find/{title}")
    public Lesson findLesson(@PathVariable String title) {
        Lesson lesson = this.repository.findByTitle(title);
       return lesson;
    }

}