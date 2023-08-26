package com.distribuida.service;

import com.distribuida.db.Instrument;
import com.distribuida.dto.InstrumentDto;
import com.distribuida.repository.InstrumentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InstrumentService {

    @Inject
    private InstrumentRepository instrumentRepository;

    public List<InstrumentDto> getAllInstrumentsDto() {
        List<Instrument> instruments = instrumentRepository.getAllInstruments();
        List<InstrumentDto> instrumentDtos = new ArrayList<>();
        for (Instrument instrument : instruments) {
            instrumentDtos.add(instrumentToDto(instrument));
        }
        return instrumentDtos;
    }

    public InstrumentDto getInstrumentDtoById(Integer id) {
        Instrument instrument = instrumentRepository.getInstrumentById(id);
        if (instrument != null) {
            return instrumentToDto(instrument);
        } else {
            return null;
        }
    }

    public void createInstrument(Instrument instrument) {
        instrumentRepository.createInstrument(instrument);
    }

    public void updateInstrument(Instrument instrument) {
        instrumentRepository.updateInstrument(instrument);
    }

    public void deleteInstrument(Integer id) {
        instrumentRepository.deleteInstrument(id);
    }

    private InstrumentDto instrumentToDto(Instrument instrument) {
        InstrumentDto dto = new InstrumentDto();
        dto.setId(instrument.getId());
        dto.setName(instrument.getName());
        return dto;
    }
}
