package com.quiz.Project.Service;

import com.quiz.Project.Model.Question;
import com.quiz.Project.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepo.save(question);
            return new ResponseEntity<>("Question added", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to add question", HttpStatus.BAD_GATEWAY);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try {
            questionRepo.save(question);
            return new ResponseEntity<>("Question updated", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to Update question", HttpStatus.BAD_GATEWAY);
    }

    public ResponseEntity<String> deleteQuestionById(int id) {
        try {
            Optional<Question> question1=questionRepo.findById(id);
            if(question1.isPresent()){
                questionRepo.deleteById(id);
                return new ResponseEntity<>("Question deleted Successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to delete question", HttpStatus.BAD_GATEWAY);
    }
}
