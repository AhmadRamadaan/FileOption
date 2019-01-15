package com.alazhar.fileOption;

public interface UiCallback {

    void onProcessStarted();

    void updateProgress(int value);

    void onProcessEnd();
}
