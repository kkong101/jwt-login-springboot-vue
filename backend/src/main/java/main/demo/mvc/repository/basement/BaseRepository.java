package main.demo.mvc.repository.basement;

import com.querydsl.jpa.impl.JPAQueryFactory;
import main.demo.utilization.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseRepository {

    @Autowired
    protected JPAQueryFactory query;

    @Autowired
    protected MapperUtils mapper;
}
