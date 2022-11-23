package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.MainPageResponse;
import com.lof.lofserver.service.community.response.TextArrowView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MatchControllerParserImplTest {

    @InjectMocks
    MatchControllerParserImpl matchControllerParser;



    private List<Object> createObjectListTest() {
        List<Object> objectList = new ArrayList<>();
        objectList.add(new TextArrowView("test"));
        return objectList;
    }

}