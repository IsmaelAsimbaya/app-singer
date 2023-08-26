package com.distribuida.service;

import com.distribuida.dto.SingerDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.distribuida.db.Singer;
import com.distribuida.repository.SingerRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SingerService {

    @Inject
    private SingerRepository singerRepository;

    public List<SingerDto> getAllSingersDto() {
        List<Singer> singers = singerRepository.getAllSingers();
        List<SingerDto> singersDtos = new ArrayList<>();
        for (Singer singer : singers) {
            singersDtos.add(singerToDto(singer));
        }
        return singersDtos;
    }

    public SingerDto getSingerDtoById(Integer id) {
        Singer singer = singerRepository.getSingerById(id);
        if (singer != null) {
            return singerToDto(singer);
        } else {
            return null;
        }
    }

    public void createSinger(Singer singer) {
        singerRepository.createSinger(singer);
    }

    public void updateSinger(Singer singer) {
        singerRepository.updateSinger(singer);
    }

    public void deleteSinger(Integer id) {
        singerRepository.deleteSinger(id);
    }

    private SingerDto singerToDto(Singer singer) {
        SingerDto dto = new SingerDto();
        dto.setId(singer.getId());
        dto.setFirst_name(singer.getFirst_name());
        dto.setLast_name(singer.getLast_name());
        dto.setBirth_date(singer.getBirth_date());
        dto.setVersion(singer.getVersion());
        return dto;
    }
}
