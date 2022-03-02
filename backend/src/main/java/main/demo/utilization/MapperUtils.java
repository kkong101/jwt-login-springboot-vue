package main.demo.utilization;

import main.demo.domain.dto.response.Response_User;
import main.demo.domain.entity.user.B_User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtils {

    private final ModelMapper mapper;

    public MapperUtils(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <D, E> D map(E entity, Class<D> dto){
        return mapper.map(entity, dto);
    }

    public <D, E> List<D> mapAll(Collection<E> entities, Class<D> dto){
        return entities.stream()
                .map(entity -> map(entity, dto))
                .collect(Collectors.toList());
    }

}
