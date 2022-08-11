package site.yejin.sbb;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String s);



    @Transactional
    @Modifying
    @Query(value="truncate question", nativeQuery = true)
    void truncateTable();

    @Query(value = "SET FOREIGN_KEY_CHECKS=0",nativeQuery = true)
    void setForeignKeyChecks0();
    @Query(value = "SET FOREIGN_KEY_CHECKS=1",nativeQuery = true)
    void setForeignKeyChecks1();
}