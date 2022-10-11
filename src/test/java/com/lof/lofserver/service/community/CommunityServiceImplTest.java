package com.lof.lofserver.service.community;

import com.lof.lofserver.service.community.response.BannerView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommunityServiceImplTest {

    @InjectMocks
    CommunityServiceImpl communityService;

    @Test
    @DisplayName("배너 리스트 가져오기")
    void getBannerList() {
        //given

        //when
        Object result = communityService.getBannerList();

        //then
        assertThat(result).isInstanceOf(BannerView.class);

    }

}