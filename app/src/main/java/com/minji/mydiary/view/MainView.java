package com.minji.mydiary.view;

import com.minji.mydiary.model.db.dao.PostDAO;

import java.util.List;

public interface MainView {
    void updateTimeline(List<String> dateList);
    void newOpenPostActivity(PostDAO post);
}
