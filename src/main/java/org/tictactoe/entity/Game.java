package org.tictactoe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "game")
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uId")
    private String uId;

    @Column(name = "playing_field", columnDefinition = "JSON")
    @Convert(converter = PlayingFieldConverter.class)
    private List<String> playingField;

    @Column(name = "first_player_id")
    private long firstPlayerId;

    @Column(name = "second_player_id")
    private long secondPlayerId;

    @Column(name = "whose_turn")
    private long whoseTurn;

    @Column(name = "game_status")
    private String gameStatus;

}
