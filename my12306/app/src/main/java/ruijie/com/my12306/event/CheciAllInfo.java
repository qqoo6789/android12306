package ruijie.com.my12306.event;

import java.util.ArrayList;
import java.util.List;

import ruijie.com.my12306.bean.Checi;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.Station;

/**
 * Created by Administrator on 2016/9/2.
 */

public class CheciAllInfo {

    private CheciData checiData;
    private List<Station> station;

    public List<Station> getStation() {
        return station;
    }

    public void setStation(List<Station> station) {
        this.station = station;
    }

    public CheciData getCheciData() {
        return checiData;
    }

    public void setCheciData(CheciData checiData) {
        this.checiData = checiData;
    }
}
