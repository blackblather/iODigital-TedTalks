package com.iodigital.tedtalks.entity.wrapper;

import com.iodigital.tedtalks.entity.Talk;

import java.util.List;

public class TalksListWrapper {
    public List<Talk> talks;
    public long total;

    public TalksListWrapper(List<Talk> talks,
                            long total) {
        this.talks = talks;
        this.total = total;
    }
}
