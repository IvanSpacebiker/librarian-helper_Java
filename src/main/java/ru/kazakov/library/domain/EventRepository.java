package ru.kazakov.library.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kazakov.library.entity.Event;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {
    @Query("select e.bookid from Event e " +
            "where e.time between :#{#from} and :#{#to} and e.type='TAKE'" +
            "group by e.bookid order by count(e.bookid) desc")
    List<UUID> findTopBook(@Param("from") Timestamp from,
                           @Param("to") Timestamp to);

    @Query("select e.readerid from Event e " +
            "where e.time between :#{#from} and :#{#to} and e.type='TAKE'" +
            "group by e.readerid order by count(e.readerid) desc")
    List<UUID> findTopReader(@Param("from") Timestamp from,
                               @Param("to") Timestamp to);

}
