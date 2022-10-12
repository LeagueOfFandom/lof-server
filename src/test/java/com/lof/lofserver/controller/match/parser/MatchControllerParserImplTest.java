package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.CommonItemDto;
import com.lof.lofserver.service.community.response.TextArrowView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatchControllerParserImplTest {

    @InjectMocks
    MatchControllerParserImpl matchControllerParser;

    @Test
    @DisplayName("매치 정보 파싱 테스트")
    void parseMatchInfo(){
        //given
        List<Object> objectList = createObjectListTest();

        //when
        List<CommonItemDto> commonItemDtoList = matchControllerParser.parseObjectListToCommonItemDtoList(objectList);

        //then
        assertThat(commonItemDtoList.get(0).viewType()).isEqualTo("TEXT_ARROW_VIEW");
        assertThat(commonItemDtoList.get(0).viewObject()).isInstanceOf(TextArrowView.class);
    }

    private List<Object> createObjectListTest() {
        List<Object> objectList = new ArrayList<>();
        objectList.add(new TextArrowView("test"));
        return objectList;
    }

}