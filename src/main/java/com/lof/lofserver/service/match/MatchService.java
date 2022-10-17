package com.lof.lofserver.service.match;

import com.lof.lofserver.service.match.response.MatchDetail;
import com.lof.lofserver.service.match.response.MatchView;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    List<MatchView> getLiveMatchList(Long userId);
    List<MatchView> getMatchListByDate(Long userId, LocalDate date, Boolean onlyMyTeam);
    List<MatchView> getMatchListByMonth(Long userId, LocalDate localDate, Boolean onlyMyTeam);
    MatchDetail getMatchDetail(Long userId, Long matchId);
}
