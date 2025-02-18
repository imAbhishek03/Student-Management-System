package me.sms.controller;

import me.sms.entity.Student;
import me.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(){

        return "home"; // view page html file -> home.html
    }

    @GetMapping("/students")
    public String getAllStudents(Model model){

        model.addAttribute("students", studentService.getAllStudents());
        System.out.println("Student list load successfully");
        return "students"; //  view page html file -> students.html
    }

    @GetMapping("/students/new")
    public String createStudent(Model model){

        Student student = new Student();
        model.addAttribute("student", student);
        return "create-student"; // view page html file -> create-student
    }

    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes){

        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("successMessage", "Student saved successfully!");
        return "redirect:/students/new";
    }
    @GetMapping("/students/update/{id}")
    public String updateStudentForm(@PathVariable int id, Model model){

        model.addAttribute("student", studentService.getById(id));

        return "update-student"; // view page html file -> update-student
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student){

        Student existingStudent = studentService.getById(id);

        existingStudent.setStudentId(student.getStudentId());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAddress(student.getAddress());

        studentService.saveStudent(existingStudent);

        return "redirect:/students";// after update redirect to student list page ->students.html
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id){

        studentService.deleteStudent(id);

        return "redirect:/students";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }
}
