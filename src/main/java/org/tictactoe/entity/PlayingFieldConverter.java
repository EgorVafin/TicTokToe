package org.tictactoe.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.List;

public class PlayingFieldConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> playingField) {

        String playingFieldJson = null;

        try {
            playingFieldJson = objectMapper.writeValueAsString(playingField);
        } catch (JsonProcessingException e) {

        }
        return playingFieldJson;
    }

    @Override
    public List<String> convertToEntityAttribute(String playingFieldJson) {

        List<String> playingField = null;

        try {
            playingField = objectMapper.readValue(playingFieldJson,
                    new TypeReference<List<String>>() {
                    });
        } catch (final IOException e) {

        }
        return playingField;
    }
}
