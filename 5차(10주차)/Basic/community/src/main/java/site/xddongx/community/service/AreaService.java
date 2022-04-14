package site.xddongx.community.service;

import site.xddongx.community.controller.dto.AreaDto;
import site.xddongx.community.entity.AreaEntity;
import site.xddongx.community.repository.AreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {
    private static final Logger logger = LoggerFactory.getLogger(AreaService.class);
    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public AreaDto createArea(AreaDto areaDto){
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setRegionMajor(areaDto.getRegionMajor());
        areaEntity.setRegionMinor(areaDto.getRegionMinor());
        areaEntity.setRegionPatch(areaDto.getRegionPatch());
        areaEntity.setLatitude(areaDto.getLatitude());
        areaEntity.setLongitude(areaDto.getLongitude());
        areaEntity = areaRepository.save(areaEntity);

        return new AreaDto(areaEntity);
    }

    public AreaDto readArea(Long id) {
        Optional<AreaEntity> areaEntityOptional = areaRepository.findById(id);
        if (areaEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return new AreaDto(areaEntityOptional.get());
    }

    public List<AreaDto> readAreaAll(){
        List<AreaDto> areaDtoList = new ArrayList<>();
        areaRepository.findAll().forEach(areaEntity -> areaDtoList.add(new AreaDto(areaEntity)));
        return areaDtoList;
    }

    public AreaDto closeArea(Double latitude, Double longitude){
        List<AreaEntity> areaEntityList = areaRepository.findByCloseRange(latitude, longitude);
        areaEntityList.forEach(areaEntity -> {
            logger.info("Entity Area: {}", areaEntity.getRegionPatch());
            logger.info("distance diff: {}",
                    Math.sqrt(Math.pow(areaEntity.getLatitude() - latitude, 2) + Math.pow(areaEntity.getLongitude() - longitude, 2)));
        });

        AreaEntity closestEntity = areaRepository.findTop1ByClosest(latitude, longitude);

        logger.info(areaEntityList.get(0).getRegionPatch());
        logger.info(closestEntity.getRegionPatch());

        return new AreaDto(closestEntity);
    }
}
