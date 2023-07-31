package org.tictactoe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "game_turn")
@Getter
@Setter
@NoArgsConstructor
public class GameTurn {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_who_made_turn_id", referencedColumnName = "id",nullable = false)
    private User userWhoMadeTurn;

    @Column(name = "coordinate_i",nullable = false)
    private int coordinateI;

    @Column(name = "coordinate_j", nullable = false)
    private int coordinateJ;

    @Column(name = "symbol", nullable = false)
    @Enumerated(EnumType.STRING)
    private SymbolEnum symbol;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date timestamp;
}
