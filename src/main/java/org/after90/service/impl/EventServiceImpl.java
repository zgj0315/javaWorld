package org.after90.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.after90.model.Threat;
import org.after90.service.EventService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

  @Override
  public void checkEvent() {
    List<Threat> listThreat = new ArrayList<Threat>(1024);
    //模拟从HBase中获取
    Map<String, List> hbase = new HashMap<String, List>();
    for (int i = 0; i < 10000; i++) {
      String strUdid = "udid" + i % 10;
      Threat threat = new Threat();
      threat.setStrId("es" + i);
      threat.setNTime(1234567890 + i);
      threat.setStrStartId("startid" + i);
      threat.setStrType("type" + 1);
      listThreat = hbase.get(strUdid);
      if (listThreat == null) {
        listThreat = new ArrayList(1024);
        listThreat.add(threat);
      } else {
        for (int j = 0; j < listThreat.size(); j++) {
          // 排序插入
          if (threat.getNTime() < listThreat.get(j).getNTime()) {
            listThreat.add(j, threat);
            return;
          }
          if (j == listThreat.size() - 1) {
            listThreat.add(threat);
          }
        }
      }

      hbase.put(strUdid, listThreat);
    }
  }


}
