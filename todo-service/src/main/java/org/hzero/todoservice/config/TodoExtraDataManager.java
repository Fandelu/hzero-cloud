package org.hzero.todoservice.config;

import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/10 16:10
 */
@ChoerodonExtraData
public class TodoExtraDataManager implements ExtraDataManager {
    @Override
    public ExtraData getData() {
        ChoerodonRouteData choerodonRouteData = new ChoerodonRouteData();
        choerodonRouteData.setName("htdo");
        choerodonRouteData.setPath("/todo/**");
        choerodonRouteData.setServiceId("todo-service");
        extraData.put(ExtraData.ZUUL_ROUTE_DATA, choerodonRouteData);
        return extraData;
    }
}
