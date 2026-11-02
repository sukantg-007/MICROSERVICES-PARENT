package com.vc.showservice.services;

import com.vc.common.dto.ShowDTO;
import com.vc.common.dto.ShowStatus;
import com.vc.showservice.dto.ShowRequest;
import com.vc.showservice.entity.Show;
import com.vc.showservice.exception.ShowNotFoundException;
import com.vc.showservice.mapper.ShowMapper;
import com.vc.showservice.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements IShowService {

    private final ShowRepository showRepository;
    private final ShowMapper showMapper;

    @Override
    public ShowDTO createShow(ShowRequest request) {
        log.info("Creating show with request: {}", request);
        Show entity = showMapper.toShowEntity(request);
        entity.setStatus(ShowStatus.AVAILABLE);
        entity.setAvailableSeats(entity.getTotalSeats());
        Show saved = showRepository.save(entity);
        log.info("Created show with id: {}", saved.getId());
        return showMapper.toShowResponse(saved);
    }

    @Override
    public ShowDTO getShowById(Long id) {
        return showRepository.findById(id)
                .map(showMapper::toShowResponse)
                .orElseThrow(() -> new ShowNotFoundException(id));
    }

    @Override
    public List<ShowDTO> getAllShows() {
        return showRepository.findAll().stream().map(showMapper::toShowResponse).toList();
    }
}
