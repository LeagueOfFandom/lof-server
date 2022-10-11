package com.lof.lofserver.domain.game;

import com.lof.lofserver.domain.game.sub.Player;
import com.lof.lofserver.domain.game.sub.Team;
import com.lof.lofserver.domain.game.sub.Winner;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "match_detail_list")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class GameEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "begin_at")
    private LocalDateTime beginAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "status")
    private String status;

    @Column(name = "length")
    private Long length;

    @Type(type = "json")
    @Column(name = "players", columnDefinition = "json")
    private List<Player> players;

    @Type(type = "json")
    @Column(name = "winner", columnDefinition = "json")
    private Winner winner;

    @Type(type = "json")
    @Column(name = "teams", columnDefinition = "json")
    private List<Team> teams;

    @Column(name = "position")
    private Long position;
}
