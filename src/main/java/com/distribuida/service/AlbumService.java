package com.distribuida.service;

import com.distribuida.db.Album;
import com.distribuida.repository.AlbumRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.util.List;

@ApplicationScoped
public class AlbumService {

    @Inject
    private AlbumRepository albumRepository;

    public JsonArray getAllAlbumAsJsonArray() {
        List<Album> albums = albumRepository.getAllAlbum();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Album album : albums) {
            jsonArrayBuilder.add(albumToJson(album));
        }

        return jsonArrayBuilder.build();
    }

    public JsonObject getAlbumByIdAsJsonObject(Integer id) {
        Album album = albumRepository.getAlbumById(id);
        if (album != null) {
            return albumToJson(album);
        } else {
            return null;
        }
    }

    public void createAlbum(Album album) {
        albumRepository.createAlbum(album);
    }

    public void updateAlbum(Album album) {
        albumRepository.updateAlbum(album);
    }

    public void deleteAlbum(Integer id) {
        albumRepository.deleteAlbum(id);
    }

    private JsonObject albumToJson(Album album){
        return Json.createObjectBuilder()
                .add("id", album.getId())
                .add("title", album.getTitle())
                .add("release_date", album.getReleaseDate().toString())
                .add("singer_id", album.getSinger().getId())
                .add("version", album.getVersion().toString())
                .build();
    }
}
