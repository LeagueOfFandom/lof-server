package com.lof.lofserver.service.match;

import com.lof.lofserver.service.match.response.MatchView;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    List<MatchView> getLiveMatchList(Long userId);
    List<MatchView> getMatchListByDate(Long userId, LocalDate date);
}
