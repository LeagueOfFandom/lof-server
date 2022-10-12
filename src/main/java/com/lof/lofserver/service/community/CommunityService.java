package com.lof.lofserver.service.community;

import com.lof.lofserver.service.community.response.BannerView;
import com.lof.lofserver.service.community.response.TextArrowView;

public interface CommunityService {
    BannerView getBannerList();
    TextArrowView getTextArrowView(String text);
}
