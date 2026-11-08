package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.DuplicateRollNumberException;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student addStudent(Student student) {
        if (repository.existsByRollNumber(student.getRollNumber())) {
            throw new DuplicateRollNumberException(
                    "Roll number already exists: " + student.getRollNumber());
        }
        student.setId(null); // make sure this is always an insert
        return repository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student updateStudent(Long id, Student updated) {
        Student existing = getStudentById(id);

        repository.findByRollNumber(updated.getRollNumber()).ifPresent(other -> {
            if (!other.getId().equals(id)) {
                throw new DuplicateRollNumberException(
                        "Roll number already exists: " + updated.getRollNumber());
            }
        });

        existing.setName(updated.getName());
        existing.setRollNumber(updated.getRollNumber());
        existing.setMarks(updated.getMarks());
        return repository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        repository.delete(existing);
    }
}
