package com.example.wbdvsp2102mianzhaoserverjava.repositories;

import com.example.wbdvsp2102mianzhaoserverjava.models.Widget;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WidgetRepository extends CrudRepository<Widget, Long> {

  @Query(value = "SELECT * FROM widgets WHERE topicId=:tid", nativeQuery = true)
  List<Widget> findWidgetsForTopic(@Param("tid") String tid);

  @Query(value = "SELECT * FROM widgets WHERE id=:wid", nativeQuery = true)
  Widget findWidgetById(@Param("wid") Long wid);
}
