package controller;

import annotation.PreUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("book")
public class BookController {

    @RequestMapping("add")
    @ResponseBody
    public void addBook() {
        System.out.println("Book has been added successfully!");
    }

    @RequestMapping("update")
    @ResponseBody
    @PreUpdate
    public void updateBook() {
        System.out.println("Book has been updated successfully!");
    }
}
