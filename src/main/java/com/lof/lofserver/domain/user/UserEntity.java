package com.lof.lofserver.domain.user;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "lof_user")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;
    @NotNull
    @Column(name = "token")
    private String fcmToken;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "nickname")
    private String nickname;
    @NotNull
    @Column(name = "profile_img")
    private String profileImg;

    @Type(type = "json")
    @Column(name = "team_list" ,columnDefinition = "json")
    private List<Long> teamList = new ArrayList<>();

    @Type(type = "json")
    @Column(name = "league_list" ,columnDefinition = "json")
    private List<Long> leagueList = new ArrayList<>();

    @Type(type = "json")
    @Column(name = "selected", columnDefinition = "json")
    private Map<Long, Boolean> userSelected = new HashMap<>();

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setTeamList(List<Long> teamList) {
        this.teamList = teamList;
    }

    public void setLeagueList(List<Long> leagueList) {
        this.leagueList = leagueList;
    }

    public void setUserSelected(Map<Long, Boolean> userSelected) {
        this.userSelected = userSelected;
    }

    @Builder
    public UserEntity(String fcmToken, String email, String nickname, String profileImg, List<Long> leagueList) {
        this.fcmToken = fcmToken;
        this.email = email;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.leagueList = leagueList;
    }
}
