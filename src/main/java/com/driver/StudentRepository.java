package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
HashMap<String,Student> studentDb=new HashMap<>();
    HashMap<String,Teacher> teacherDb=new HashMap<>();
    HashMap<String, List<String>>teacherStudentDb=new HashMap<>();//stores teacher name vs all the students name list
    public void addStudent(Student student) {
        studentDb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName) {
        boolean teacherExists=teacherDb.containsKey(teacherName);
        boolean studentExists=studentDb.containsKey(studentName);
        if(teacherExists && studentExists){
            teacherStudentDb.putIfAbsent(teacherName,new ArrayList<>());
            teacherStudentDb.get(teacherName).add(studentName);
            teacherDb.get(teacherName).setNumberOfStudents(teacherStudentDb.get(teacherName).size());
        }
    }

    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacherName) {
        List<String> ans=new ArrayList<>();
        for(String ele:teacherStudentDb.get(teacherName)){
            ans.add(ele);
        }
        return ans;
    }

    public List<String> getAllStudents() {
        List<String> list=new ArrayList<>();
        for(String name: studentDb.keySet()){
            list.add(name);
        }
        return list;
    }

    public void deleteTeacherByName(String teacherName) {
        teacherDb.remove(teacherName);
        teacherStudentDb.remove(teacherName);
    }

    public void deleteAllTeachers() {
        teacherDb.clear();
        teacherStudentDb.clear();
    }
}
