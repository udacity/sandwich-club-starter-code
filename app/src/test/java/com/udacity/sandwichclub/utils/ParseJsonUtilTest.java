package com.udacity.sandwichclub.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ParseJsonUtilTest {
    @Mock
    private JSONObject jsonObject;

    @Test
    public void parseSingleEntryReturnsValueIfJsonHasThisKey() throws JSONException {
        String key = "key";
        String val = "val";
        given(jsonObject.has(key)).willReturn(true);
        given(jsonObject.getString(key)).willReturn(val);

        String parsedValue = ParseJsonUtil.parseSingleEntry(jsonObject, key);

        assertThat(parsedValue).isEqualTo(val);
    }

    @Test
    public void parseSingleEntryReturnsEmptyStringIfJsonDoesNotHaveKey() throws JSONException {
        given(jsonObject.has("someKey")).willReturn(false);

        String parsedValue = ParseJsonUtil.parseSingleEntry(jsonObject, "someKey");

        assertThat(parsedValue).isEqualTo("");
    }


    @Test
    public void parseListWithEmptyJsonReturnsEmptyList() throws JSONException {
        given(jsonObject.has("arrayKey")).willReturn(false);

        List<String> parsedList = ParseJsonUtil.parseList(jsonObject, "arrayKey");

        assertThat(parsedList).isEmpty();
    }

    @Test
    public void parseListWithEmptyJsonArrayReturnsEmptyList() throws JSONException {
        String arrayKey = "arrayKey";
        JSONArray jsonArray = mock(JSONArray.class);
        given(jsonObject.has(arrayKey)).willReturn(true);
        given(jsonObject.getJSONArray(arrayKey)).willReturn(jsonArray);
        given(jsonArray.length()).willReturn(0);

        List<String> parsedList = ParseJsonUtil.parseList(jsonObject, arrayKey);

        assertThat(parsedList).isEmpty();
    }

    @Test
    public void parseListWithJsonArrayReturnsParsedList() throws JSONException {
        String arrayKey = "arrayKey";
        String value = "value";
        JSONArray jsonArray = mock(JSONArray.class);
        given(jsonObject.has(arrayKey)).willReturn(true);
        given(jsonObject.getJSONArray(arrayKey)).willReturn(jsonArray);
        given(jsonArray.length()).willReturn(1);
        given(jsonArray.getString(0)).willReturn(value);

        List<String> parsedList = ParseJsonUtil.parseList(jsonObject, arrayKey);

        assertThat(parsedList.size()).isEqualTo(1);
        assertThat(parsedList.get(0)).isEqualTo(value);
    }

}