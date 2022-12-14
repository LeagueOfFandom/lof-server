package com.lof.lofserver.service.match;

import com.lof.lofserver.service.match.response.MatchView;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    List<MatchView> getLiveMatchList(Long userId);
    List<MatchView> getMatchListByDate(Long userId, LocalDate date, Boolean onlyMyTeam);
    List<MatchView> getMatchListByMonth(Long userId, LocalDate localDate, Boolean onlyMyTeam);
    ResponseEntity<?> getMatchDetail(Long matchId);

    List<MatchView> testLiveMatchViewList(Long userId);
    List<MatchView> testMatchViewList();
}
