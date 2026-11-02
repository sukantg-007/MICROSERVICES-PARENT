package com.vc.showservice.services;

import com.vc.common.dto.ShowDTO;
import com.vc.showservice.dto.ShowRequest;

import java.util.List;

public interface IShowService {
    ShowDTO createShow(ShowRequest request);

    ShowDTO getShowById(Long id);

    List<ShowDTO> getAllShows();
}
