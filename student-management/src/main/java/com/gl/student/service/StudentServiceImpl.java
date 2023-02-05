package com.gl.student.service;

import com.gl.student.entity.Student;
import com.gl.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).orElseGet(null);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }
}
