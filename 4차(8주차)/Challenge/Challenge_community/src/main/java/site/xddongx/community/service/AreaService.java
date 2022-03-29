package site.xddongx.community.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.community.dto.AreaDto;
import site.xddongx.community.entity.AreaEntity;
import site.xddongx.community.repository.AreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {
    private static final Logger logger = LoggerFactory.getLogger(AreaService.class);
    private final AreaRepository areaRepository;

    @Autowired
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;

        AreaEntity areaEntityDemo1 = new AreaEntity();
        areaEntityDemo1.setRegionMajor("서울시");
        areaEntityDemo1.setRegionMinor("서초구");
        areaEntityDemo1.setRegionPatch("서초동");
        areaEntityDemo1.setLatitude(37.4877);
        areaEntityDemo1.setLongitude(127.0174);
        this.areaRepository.save(areaEntityDemo1);

        AreaEntity areaEntityDemo2 = new AreaEntity();
        areaEntityDemo2.setRegionMajor("서울시");
        areaEntityDemo2.setRegionMinor("강남구");
        areaEntityDemo2.setRegionPatch("역삼동");
        areaEntityDemo2.setLatitude(37.4999);
        areaEntityDemo2.setLongitude(127.0374);
        this.areaRepository.save(areaEntityDemo2);

        AreaEntity areaEntityDemo3 = new AreaEntity();
        areaEntityDemo3.setRegionMajor("서울시");
        areaEntityDemo3.setRegionMinor("강남구");
        areaEntityDemo3.setRegionPatch("삼성동");
        areaEntityDemo3.setLatitude( 37.5140);
        areaEntityDemo3.setLongitude(127.0565);
        this.areaRepository.save(areaEntityDemo3);
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
}
