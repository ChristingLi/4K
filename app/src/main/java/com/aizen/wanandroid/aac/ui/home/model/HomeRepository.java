package com.aizen.wanandroid.aac.ui.home.model;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ld on 2019/1/3.
 *
 * @author ld
 * @date 2019/1/3
 * 描    述：
 */
public class HomeRepository {


}
@Module
class HomeProvider {

    @Provides
    public HomeRepository provider() {
        return new HomeRepository();
    }
}
@Component(modules = HomeProvider.class)
interface HomeComponent {

    void inject(HomeViewModel model);

}
