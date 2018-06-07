package com.example.nizer01.goplay.listeners;

import com.example.nizer01.goplay.domain.Profile;

public interface OnGetProfileListener {
    void onFinded(Profile pf);
    void onNotFinded();
    void onError(Object er);
}
