package com.distribuida.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import com.distribuida.db.Singer;
import com.distribuida.repository.SingerRepository;

import java.util.List;

@ApplicationScoped
public class SingerService {

    @Inject
    private SingerRepository singerRepository;

    public JsonArray getAllSingersAsJsonArray() {
        List<Singer> singers = singerRepository.getAllSingers();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Singer singer : singers) {
            jsonArrayBuilder.add(singerToJson(singer));
        }

        return jsonArrayBuilder.build();
    }

    public JsonObject getSingerByIdAsJsonObject(Long id) {
        Singer singer = singerRepository.getSingerById(id);
        if (singer != null) {
            return singerToJson(singer);
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

    public void deleteSinger(Long id) {
        singerRepository.deleteSinger(id);
    }

    // Método auxiliar para convertir un objeto Singer a JsonObject
    private JsonObject singerToJson(Singer singer) {
        return Json.createObjectBuilder()
                .add("id", singer.getId())
                .add("first_Name", singer.getFirst_name())
                .add("last_Name", singer.getLast_name())
                .add("birth_Date", singer.getBirth_date().toString())  // Convertir a formato adecuado
                .build();
    }
}
