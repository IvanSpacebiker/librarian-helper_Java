package ru.kazakov.labaratory.library.domain;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Event;


import java.sql.Timestamp;
import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {
    @Query("select e.bookid from Event e " +
            "where e.time between :#{#from} and :#{#to} and e.type='TAKE'" +
            "group by e.bookid order by count(e.bookid) desc")
    Iterable<UUID> findTopBook(@Param("from") Timestamp from,
                               @Param("to") Timestamp to);

    @Query("select e.readerid from Event e " +
            "where e.time between :#{#from} and :#{#to} and e.type='TAKE'" +
            "group by e.readerid order by count(e.readerid) desc")
    Iterable<UUID> findTopReader(@Param("from") Timestamp from,
                               @Param("to") Timestamp to);

}
