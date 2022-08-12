package site.yejin.sbb;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import site.yejin.sbb.base.RepositoryUtil;

public interface AnswerRepository extends JpaRepository<Answer,Integer>, RepositoryUtil {
    @Transactional
    @Modifying
  //  @Query(value="truncate answer", nativeQuery = true)
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)  // auto increment를 1로 만들어서 새로 insert 하는 튜플이 덮어쓰게 한다.
    void truncateTable();


}
