package com.iodigital.tedtalks.entity.wrapper;

import com.iodigital.tedtalks.entity.Talks;

import java.util.List;

public class TalksListWrapper {
    public List<Talks> talks;
    public long total;

    public TalksListWrapper(List<Talks> talks,
                            long total) {
        this.talks = talks;
        this.total = total;
    }
}
