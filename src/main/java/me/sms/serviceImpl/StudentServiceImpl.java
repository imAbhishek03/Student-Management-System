package me.sms.serviceImpl;

import me.sms.entity.Student;
import me.sms.repository.StudentRepository;
import me.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;

    @Override
    public List<Student> getAllStudents() {

        return studentRepo.findAll();
    }

    @Override
    public Student saveStudent(Student student) {

        return studentRepo.save(student);
    }

    @Override
    public Student getById(int id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public void deleteStudent(int id) {
        studentRepo.deleteById(id);
    }
}
