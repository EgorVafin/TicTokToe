package org.tictactoe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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

    @Column(name = "uUId", unique = true, nullable = false)
    private String uUId;

    @ManyToOne
    @JoinColumn(name = "creator_player_id", referencedColumnName = "id", nullable = false)
    private User creatorPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player_id", referencedColumnName = "id", nullable = true)
    private User secondPlayer;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @Column(name = "creator_symbol", nullable = false)
    @Enumerated(EnumType.STRING)
    private SymbolEnum creatorSymbol;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private GameStatusEnum status;

    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @OrderBy("timestamp asc")
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<GameTurn> gameTurns;

    public void addTurn(GameTurn turn){
        turn.setGame(this);
        gameTurns.add(turn);
    }
}
