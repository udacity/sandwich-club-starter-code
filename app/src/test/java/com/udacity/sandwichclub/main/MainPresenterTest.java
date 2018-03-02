package com.udacity.sandwichclub.main;


import com.udacity.sandwichclub.architecture.ViewNotAttachedException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    private ResourceProvider resourceProvider;
    @Mock
    private MainView mainView;

    @Test(expected = ViewNotAttachedException.class)
    public void throwExceptionIfViewIsNotAttachedAndMethodIsCalled() {
        MainPresenter presenter = new MainPresenter(resourceProvider);

        presenter.sandwichSelected("someName");
    }

    @Test
    public void bindSandwichesAfterAttachingView() {
        given(resourceProvider.readSandwichesFromResources()).willReturn(new String[]{"OneSandwich"});
        MainPresenter presenter = new MainPresenter(resourceProvider);

        presenter.attachView(mainView);

        verify(resourceProvider).readSandwichesFromResources();
        verify(mainView).bindSandwiches(any(String[].class));
    }

    @Test
    public void showSandwichDetailsWhenSandwichIsSelected() {
        given(resourceProvider.readSandwichesFromResources()).willReturn(new String[]{"OneSandwich"});
        MainPresenter presenter = new MainPresenter(resourceProvider);

        presenter.attachView(mainView);
        presenter.sandwichSelected("OneSandwich");

        verify(mainView).showSandwichDetails("OneSandwich");
    }

}