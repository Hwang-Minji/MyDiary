package com.minji.mydiary.presenter;

import com.minji.mydiary.model.db.LocalDatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LocalDatabaseHelper.class)
public class MainPresenterTest {
    private MainPresenter mainPresenter;

    @Mock
    private LocalDatabaseHelper mLocalDatabaseHelper;

    @Before
    public void setUp() {
        mainPresenter = new MainPresenterImpl();
        mockStatic(LocalDatabaseHelper.class);
    }

    @Test
    public void test_requestAllDatas_NoErrorCase() {
        // prepare
        when(LocalDatabaseHelper.getInstance()).thenReturn(mLocalDatabaseHelper);

        // execute
        mainPresenter.requestAllDatas();

        // verify
        Mockito.verify(mLocalDatabaseHelper).getAllDatas();
    }
}