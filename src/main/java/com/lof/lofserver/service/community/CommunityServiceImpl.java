package com.lof.lofserver.service.community;

import com.lof.lofserver.service.community.response.BannerView;
import com.lof.lofserver.service.community.response.TextArrowView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Override
    public BannerView getBannerList() {
        List<String> bannerList = new ArrayList<>();
        bannerList.add("https://event.img.afreecatv.com/esports/news/fomos/2021/08/29/2965612a5d855f259.jpg");

        return new BannerView(bannerList);
    }

    @Override
    public TextArrowView getTextArrowView(String text) {
        return new TextArrowView(text);
    }
}