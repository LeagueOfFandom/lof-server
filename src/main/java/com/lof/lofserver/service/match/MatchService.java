package com.lof.lofserver.service.match;

import com.lof.lofserver.service.match.response.MatchView;

public interface MatchService {
    MatchView getLiveMatchList(Long userId);
}
