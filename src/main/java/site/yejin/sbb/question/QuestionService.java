package site.yejin.sbb.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.yejin.sbb.global.exception.DataNotFoundException;
import site.yejin.sbb.member.entity.Member;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Page<Question> list(int page) {
        log.debug("int page "+page);
        Pageable pageable = PageRequest.of(page, 8, Sort.by("CreateDate").descending());
        return questionRepository.findAll(pageable);
    }

    public Question findById(int id) {
        return this.questionRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("%d question not found".formatted(id)));
    }

    public Optional<Integer> create(String subject, String content, Member author) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setModifyDate(LocalDateTime.now());
        question.initAnswerList();
        question.setAuthor(author);
        questionRepository.save(question);
        return Optional.ofNullable(question.getId());
    }
    public Optional<Integer> update(Question question, String subject, String content, Member author) {
        if(!question.getSubject().equals(subject)) {
            question.setSubject(subject);
        }
        if(!question.getContent().equals(content)){
            question.setContent(content);
        }
        question.setModifyDate(LocalDateTime.now());
        questionRepository.save(question);
        return Optional.ofNullable(question.getId());
    }

    public void delete(Integer id) {
        questionRepository.delete(findById(id));
    }
}