package com.vc.showservice.mapper;

import com.vc.common.dto.ShowDTO;
import com.vc.showservice.dto.ShowRequest;
import com.vc.showservice.entity.Show;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShowMapper {

    ShowDTO toShowResponse(Show show);

    Show toShowEntity(ShowRequest request);
}
