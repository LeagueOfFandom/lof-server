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
        bannerList.add("https://d654rq93y7j8z.cloudfront.net/lof_banner/1.jpg");
        bannerList.add("https://d654rq93y7j8z.cloudfront.net/lof_banner/2.jpg");
        bannerList.add("https://d654rq93y7j8z.cloudfront.net/lof_banner/3.jpg");

        return new BannerView(bannerList);
    }

    @Override
    public TextArrowView getTextArrowView(String text) {
        return new TextArrowView(text);
    }
}